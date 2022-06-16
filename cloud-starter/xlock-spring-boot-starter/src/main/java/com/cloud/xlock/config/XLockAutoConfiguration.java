package com.cloud.xlock.config;

import com.cloud.xlock.XLockInterceptor;
import com.cloud.xlock.XLockSpelResolver;
import com.cloud.xlock.annotation.EnableXLock;
import com.cloud.xlock.exception.UnknownLoadBalancerException;
import com.cloud.xlock.exception.UnknownReadModeException;
import com.cloud.xlock.exception.UnknownSubscriptionModeException;
import com.cloud.xlock.lock.FairLock;
import com.cloud.xlock.lock.LockFactory;
import com.cloud.xlock.lock.MultiLock;
import com.cloud.xlock.lock.ReadLock;
import com.cloud.xlock.lock.RedLock;
import com.cloud.xlock.lock.ReentrantLock;
import com.cloud.xlock.lock.WriteLock;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.BaseMasterSlaveServersConfig;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.redisson.config.MasterSlaveServersConfig;
import org.redisson.config.ReadMode;
import org.redisson.config.ReplicatedServersConfig;
import org.redisson.config.SentinelServersConfig;
import org.redisson.config.SingleServerConfig;
import org.redisson.config.SslProvider;
import org.redisson.config.SubscriptionMode;
import org.redisson.connection.balancer.LoadBalancer;
import org.redisson.connection.balancer.RandomLoadBalancer;
import org.redisson.connection.balancer.RoundRobinLoadBalancer;
import org.redisson.connection.balancer.WeightedRoundRobinBalancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 自动装配.
 *
 * @author breggor
 */
@Configuration
@ConditionalOnBean(annotation = EnableXLock.class)
@EnableConfigurationProperties(XLockProperties.class)
public class XLockAutoConfiguration {

    @Autowired
    private XLockProperties xLockProperties;

    /**
     * 创建redisSonClient.
     *
     * @return RedissonClient Redisson客户端
     * @throws MalformedURLException 格式错误的URL异常
     */
    @Bean(name = "xLockRedissonClient", destroyMethod = "shutdown")
    public RedissonClient redissonClient() throws MalformedURLException {
        Config config = new Config();
        ServerPattern serverPattern = ServerPatternFactory.getServerPattern(xLockProperties.getPattern());

        if (serverPattern == ServerPattern.SINGLE) {
            SingleServerConfig singleServerConfig = config.useSingleServer();
            initSingleConfig(singleServerConfig);
        }
        if (serverPattern == ServerPattern.CLUSTER) {
            ClusterServersConfig clusterConfig = config.useClusterServers();
            initClusterConfig(clusterConfig);
        }
        if (serverPattern == ServerPattern.MASTER_SLAVE) {
            MasterSlaveServersConfig masterSlaveConfig = config.useMasterSlaveServers();
            initMasterSlaveConfig(masterSlaveConfig);
        }
        if (serverPattern == ServerPattern.REPLICATED) {
            ReplicatedServersConfig replicatedServersConfig = config.useReplicatedServers();
            initReplicatedServersConfig(replicatedServersConfig);
        }
        if (serverPattern == ServerPattern.SENTINEL) {
            SentinelServersConfig sentinelServersConfig = config.useSentinelServers();
            initSentinelServersConfig(sentinelServersConfig);
        }
        return Redisson.create(config);
    }

    /**
     * 锁服务工厂.
     *
     * @return 锁服务工厂
     */
    @Bean
    @ConditionalOnMissingBean
    public LockFactory serviceBeanFactory() {
        return new LockFactory();
    }

    /**
     * 可重入锁加锁服务.
     *
     * @return 可重入锁加锁服务.
     */
    @Bean
    @ConditionalOnMissingBean
    public ReentrantLock reentrantLock() {
        return new ReentrantLock();
    }

    /**
     * 公平锁操作服务.
     *
     * @return 公平锁操作服务
     */
    @Bean
    @ConditionalOnMissingBean
    public FairLock fairLock() {
        return new FairLock();
    }

    /**
     * 联锁操作服务.
     *
     * @return 联锁操作服务
     */
    @Bean
    @ConditionalOnMissingBean
    public MultiLock multiLock() {
        return new MultiLock();
    }

    /**
     * 红锁操作服务.
     *
     * @return 红锁操作服务
     */
    @Bean
    @ConditionalOnMissingBean
    public RedLock redLock() {
        return new RedLock();
    }

    /**
     * 读锁操作服务.
     *
     * @return 读锁操作服务
     */
    @Bean
    @ConditionalOnMissingBean
    public ReadLock readLock() {
        return new ReadLock();
    }

    /**
     * 写锁操作服务.
     *
     * @return 写锁操作服务
     */
    @Bean
    @ConditionalOnMissingBean
    public WriteLock writeLock() {
        return new WriteLock();
    }

    /**
     * spring el解析key策略.
     *
     * @return spring el解析key策略
     */
    @Bean
    @ConditionalOnMissingBean
    public XLockSpelResolver xLockSpelResolver() {
        return new XLockSpelResolver();
    }

    /**
     * 锁拦截器.
     *
     * @return 锁拦截器
     */
    @Bean
    @ConditionalOnMissingBean
    public XLockInterceptor xLockInterceptor() {
        return new XLockInterceptor();
    }

    /**
     * 初始化单机模式参数.
     *
     * @param singleServerConfig 单机模式配置
     * @throws MalformedURLException 格式错误的URL异常
     */
    private void initSingleConfig(final SingleServerConfig singleServerConfig) throws MalformedURLException {
        XLockProperties.SingleConfig singleConfig = xLockProperties.getSingleServer();
        singleServerConfig.setAddress(String.format("%s%s%s%s", XLockConsts.REDIS_URL_PREFIX, singleConfig.getAddress(), XLockConsts.COLON, singleConfig.getPort()));
        singleServerConfig.setClientName(xLockProperties.getClientName());
        singleServerConfig.setConnectionMinimumIdleSize(singleConfig.getConnMinIdleSize());
        singleServerConfig.setConnectionPoolSize(singleConfig.getConnPoolSize());
        singleServerConfig.setConnectTimeout(singleConfig.getConnTimeout());
        singleServerConfig.setDatabase(singleConfig.getDatabase());
        singleServerConfig.setDnsMonitoringInterval(singleConfig.getDnsMonitoringInterval());
        singleServerConfig.setIdleConnectionTimeout(singleConfig.getIdleConnTimeout());
        singleServerConfig.setKeepAlive(singleConfig.isKeepAlive());
        singleServerConfig.setPassword(singleConfig.getPassword());
        singleServerConfig.setRetryAttempts(singleConfig.getRetryAttempts());
        singleServerConfig.setRetryInterval(singleConfig.getRetryInterval());
        singleServerConfig.setSslEnableEndpointIdentification(xLockProperties.isSslEnableEndpointIdentification());
        if (xLockProperties.getSslKeystore() != null) {
            singleServerConfig.setSslKeystore(new URL(xLockProperties.getSslKeystore()));
        }
        if (xLockProperties.getSslKeystorePassword() != null) {
            singleServerConfig.setSslKeystorePassword(xLockProperties.getSslKeystorePassword());
        }
        singleServerConfig.setSslProvider(XLockConsts.JDK.equalsIgnoreCase(xLockProperties.getSslProvider()) ? SslProvider.JDK : SslProvider.OPENSSL);
    }

    /**
     * 初始化集群模式参数.
     *
     * @param clusterServerConfig 集群模式配置
     */
    private void initClusterConfig(final ClusterServersConfig clusterServerConfig) {
        XLockProperties.ClusterConfig clusterConfig = xLockProperties.getClusterServer();
        String[] addressArr = clusterConfig.getNodeAddresses().split(XLockConsts.COMMA);
        Arrays.asList(addressArr).forEach(
                address -> clusterServerConfig.addNodeAddress(String.format("%s%s", XLockConsts.REDIS_URL_PREFIX, address))
        );
        clusterServerConfig.setScanInterval(clusterConfig.getScanInterval());

        ReadMode readMode = getReadMode(clusterConfig.getReadMode());
        if (Objects.isNull(readMode)) {
            throw new UnknownReadModeException();
        }
        clusterServerConfig.setReadMode(readMode);

        SubscriptionMode subscriptionMode = getSubscriptionMode(clusterConfig.getSubMode());
        if (Objects.isNull(subscriptionMode)) {
            throw new UnknownSubscriptionModeException();
        }
        clusterServerConfig.setSubscriptionMode(subscriptionMode);

        LoadBalancer loadBalancer = getLoadBalancer(clusterConfig.getLoadBalancer(), clusterConfig.getWeightMaps(), clusterConfig.getDefaultWeight());
        if (Objects.isNull(loadBalancer)) {
            throw new UnknownLoadBalancerException();
        }
        clusterServerConfig.setLoadBalancer(loadBalancer);

        clusterServerConfig.setSubscriptionConnectionMinimumIdleSize(clusterConfig.getSubConnMinIdleSize());
        clusterServerConfig.setSubscriptionConnectionPoolSize(clusterConfig.getSubConnPoolSize());
        clusterServerConfig.setSlaveConnectionMinimumIdleSize(clusterConfig.getSlaveConnMinIdleSize());
        clusterServerConfig.setSlaveConnectionPoolSize(clusterConfig.getSlaveConnPoolSize());
        clusterServerConfig.setMasterConnectionMinimumIdleSize(clusterConfig.getMasterConnMinIdleSize());
        clusterServerConfig.setMasterConnectionPoolSize(clusterConfig.getMasterConnPoolSize());
        clusterServerConfig.setIdleConnectionTimeout(clusterConfig.getIdleConnTimeout());
        clusterServerConfig.setConnectTimeout(clusterConfig.getConnTimeout());
        clusterServerConfig.setTimeout(clusterConfig.getTimeout());
        clusterServerConfig.setRetryAttempts(clusterConfig.getRetryAttempts());
        clusterServerConfig.setRetryInterval(clusterConfig.getRetryInterval());
        clusterServerConfig.setPassword(clusterConfig.getPassword());
        clusterServerConfig.setSubscriptionsPerConnection(clusterConfig.getSubPerConn());
        clusterServerConfig.setClientName(xLockProperties.getClientName());
    }

    /**
     * 初始化哨兵模式参数.
     *
     * @param sentinelServersConfig 哨兵模式配置
     * @throws MalformedURLException 格式错误的URL异常
     */
    private void initSentinelServersConfig(final SentinelServersConfig sentinelServersConfig) throws MalformedURLException {
        XLockProperties.SentinelConfig sentinelConfig = xLockProperties.getSentinelServer();
        String[] addressArr = sentinelConfig.getSentinelAddresses().split(XLockConsts.COMMA);
        Arrays.asList(addressArr).forEach(
                address -> sentinelServersConfig.addSentinelAddress(String.format("%s%s", XLockConsts.REDIS_URL_PREFIX, address))
        );

        ReadMode readMode = getReadMode(sentinelConfig.getReadMode());
        if (Objects.isNull(readMode)) {
            throw new UnknownReadModeException();
        }
        sentinelServersConfig.setReadMode(readMode);

        SubscriptionMode subscriptionMode = getSubscriptionMode(sentinelConfig.getSubMode());
        if (Objects.isNull(subscriptionMode)) {
            throw new UnknownSubscriptionModeException();
        }
        sentinelServersConfig.setSubscriptionMode(subscriptionMode);

        LoadBalancer loadBalancer = getLoadBalancer(sentinelConfig.getLoadBalancer(), sentinelConfig.getWeightMaps(), sentinelConfig.getDefaultWeight());
        if (Objects.isNull(loadBalancer)) {
            throw new UnknownLoadBalancerException();
        }
        sentinelServersConfig.setLoadBalancer(loadBalancer);

        sentinelServersConfig.setMasterName(sentinelConfig.getMasterName());
        sentinelServersConfig.setDatabase(sentinelConfig.getDatabase());
        sentinelServersConfig.setSlaveConnectionPoolSize(sentinelConfig.getSlaveConnectionPoolSize());
        sentinelServersConfig.setMasterConnectionPoolSize(sentinelConfig.getMasterConnectionPoolSize());
        sentinelServersConfig.setSubscriptionConnectionPoolSize(sentinelConfig.getSubscriptionConnectionPoolSize());
        sentinelServersConfig.setSlaveConnectionMinimumIdleSize(sentinelConfig.getSlaveConnectionMinimumIdleSize());
        sentinelServersConfig.setMasterConnectionMinimumIdleSize(sentinelConfig.getMasterConnectionMinimumIdleSize());
        sentinelServersConfig.setSubscriptionConnectionMinimumIdleSize(sentinelConfig.getSubscriptionConnectionMinimumIdleSize());
        sentinelServersConfig.setDnsMonitoringInterval(sentinelConfig.getDnsMonitoringInterval());
        sentinelServersConfig.setSubscriptionsPerConnection(sentinelConfig.getSubscriptionsPerConnection());
        sentinelServersConfig.setPassword(sentinelConfig.getPassword());
        sentinelServersConfig.setRetryAttempts(sentinelConfig.getRetryAttempts());
        sentinelServersConfig.setRetryInterval(sentinelConfig.getRetryInterval());
        sentinelServersConfig.setTimeout(sentinelConfig.getTimeout());
        sentinelServersConfig.setConnectTimeout(sentinelConfig.getConnectTimeout());
        sentinelServersConfig.setIdleConnectionTimeout(sentinelConfig.getIdleConnectionTimeout());
        setLockSslConfigAndClientName(sentinelServersConfig);
    }

    /**
     * 初始化云托管模式参数.
     *
     * @param replicatedServersConfig 云托管模式配置
     * @throws MalformedURLException 格式错误的URL异常
     */
    private void initReplicatedServersConfig(final ReplicatedServersConfig replicatedServersConfig) throws MalformedURLException {
        XLockProperties.ReplicatedConfig replicatedConfig = xLockProperties.getReplicatedServer();

        String[] addressArr = replicatedConfig.getNodeAddresses().split(XLockConsts.COMMA);
        Arrays.asList(addressArr).forEach(
                address -> replicatedServersConfig.addNodeAddress(String.format("%s%s", XLockConsts.REDIS_URL_PREFIX, address))
        );
        ReadMode readMode = getReadMode(replicatedConfig.getReadMode());
        if (Objects.isNull(readMode)) {
            throw new UnknownReadModeException();
        }
        replicatedServersConfig.setReadMode(readMode);

        SubscriptionMode subscriptionMode = getSubscriptionMode(replicatedConfig.getSubscriptionMode());
        if (Objects.isNull(subscriptionMode)) {
            throw new UnknownSubscriptionModeException();
        }
        replicatedServersConfig.setSubscriptionMode(subscriptionMode);

        LoadBalancer loadBalancer = getLoadBalancer(replicatedConfig.getLoadBalancer(), replicatedConfig.getWeightMaps(), replicatedConfig.getDefaultWeight());
        if (Objects.isNull(loadBalancer)) {
            throw new UnknownLoadBalancerException();
        }
        replicatedServersConfig.setLoadBalancer(loadBalancer);

        replicatedServersConfig.setScanInterval(replicatedConfig.getScanInterval());
        replicatedServersConfig.setDatabase(replicatedConfig.getDatabase());
        replicatedServersConfig.setSlaveConnectionPoolSize(replicatedConfig.getSlaveConnectionPoolSize());
        replicatedServersConfig.setMasterConnectionPoolSize(replicatedConfig.getMasterConnectionPoolSize());
        replicatedServersConfig.setSubscriptionConnectionPoolSize(replicatedConfig.getSubscriptionConnectionPoolSize());
        replicatedServersConfig.setSlaveConnectionMinimumIdleSize(replicatedConfig.getSlaveConnectionMinimumIdleSize());
        replicatedServersConfig.setMasterConnectionMinimumIdleSize(replicatedConfig.getMasterConnectionMinimumIdleSize());
        replicatedServersConfig.setSubscriptionConnectionMinimumIdleSize(replicatedConfig.getSubscriptionConnectionMinimumIdleSize());
        replicatedServersConfig.setDnsMonitoringInterval(replicatedConfig.getDnsMonitoringInterval());
        replicatedServersConfig.setSubscriptionsPerConnection(replicatedConfig.getSubscriptionsPerConnection());
        replicatedServersConfig.setPassword(replicatedConfig.getPassword());
        replicatedServersConfig.setRetryAttempts(replicatedConfig.getRetryAttempts());
        replicatedServersConfig.setRetryInterval(replicatedConfig.getRetryInterval());
        replicatedServersConfig.setTimeout(replicatedConfig.getTimeout());
        replicatedServersConfig.setConnectTimeout(replicatedConfig.getConnectTimeout());
        replicatedServersConfig.setIdleConnectionTimeout(replicatedConfig.getIdleConnectionTimeout());

        setLockSslConfigAndClientName(replicatedServersConfig);
    }

    /**
     * 初始化主从模式参数.
     *
     * @param masterSlaveServersConfig 主从模式配置
     * @throws MalformedURLException 格式错误的URL异常
     */
    private void initMasterSlaveConfig(final MasterSlaveServersConfig masterSlaveServersConfig) throws MalformedURLException {
        XLockProperties.MasterSlaveConfig masterSlaveConfig = xLockProperties.getMasterSlaveServer();
        masterSlaveServersConfig.setMasterAddress(String.format("%s%s", XLockConsts.REDIS_URL_PREFIX, masterSlaveConfig.getMasterAddress()));
        String[] addressArr = masterSlaveConfig.getSlaveAddresses().split(XLockConsts.COMMA);

        Arrays.asList(addressArr).forEach(address -> {
            masterSlaveServersConfig.addSlaveAddress(String.format("%s%s", XLockConsts.REDIS_URL_PREFIX, address));
        });

        ReadMode readMode = getReadMode(masterSlaveConfig.getReadMode());
        if (Objects.isNull(readMode)) {
            throw new UnknownReadModeException();
        }
        masterSlaveServersConfig.setReadMode(readMode);

        SubscriptionMode subscriptionMode = getSubscriptionMode(masterSlaveConfig.getSubMode());
        if (Objects.isNull(subscriptionMode)) {
            throw new UnknownSubscriptionModeException();
        }
        masterSlaveServersConfig.setSubscriptionMode(subscriptionMode);

        LoadBalancer loadBalancer = getLoadBalancer(masterSlaveConfig.getLoadBalancer(), masterSlaveConfig.getWeightMaps(), masterSlaveConfig.getDefaultWeight());
        if (Objects.isNull(loadBalancer)) {
            throw new UnknownLoadBalancerException();
        }
        masterSlaveServersConfig.setLoadBalancer(loadBalancer);

        masterSlaveServersConfig.setDatabase(masterSlaveConfig.getDatabase());
        masterSlaveServersConfig.setSlaveConnectionPoolSize(masterSlaveConfig.getSlaveConnectionPoolSize());
        masterSlaveServersConfig.setMasterConnectionPoolSize(masterSlaveConfig.getMasterConnectionPoolSize());
        masterSlaveServersConfig.setSubscriptionConnectionPoolSize(masterSlaveConfig.getSubscriptionConnectionPoolSize());
        masterSlaveServersConfig.setSlaveConnectionMinimumIdleSize(masterSlaveConfig.getSlaveConnectionMinimumIdleSize());
        masterSlaveServersConfig.setMasterConnectionMinimumIdleSize(masterSlaveConfig.getMasterConnectionMinimumIdleSize());
        masterSlaveServersConfig.setSubscriptionConnectionMinimumIdleSize(masterSlaveConfig.getSubscriptionConnectionMinimumIdleSize());
        masterSlaveServersConfig.setDnsMonitoringInterval(masterSlaveConfig.getDnsMonitoringInterval());
        masterSlaveServersConfig.setSubscriptionsPerConnection(masterSlaveConfig.getSubscriptionsPerConnection());
        masterSlaveServersConfig.setPassword(masterSlaveConfig.getPassword());
        masterSlaveServersConfig.setRetryAttempts(masterSlaveConfig.getRetryAttempts());
        masterSlaveServersConfig.setRetryInterval(masterSlaveConfig.getRetryInterval());
        masterSlaveServersConfig.setTimeout(masterSlaveConfig.getTimeout());
        masterSlaveServersConfig.setConnectTimeout(masterSlaveConfig.getConnectTimeout());
        masterSlaveServersConfig.setIdleConnectionTimeout(masterSlaveConfig.getIdleConnectionTimeout());
        setLockSslConfigAndClientName(masterSlaveServersConfig);
    }

    /**
     * 根据用户的配置类型设置对应的LoadBalancer.
     *
     * @param loadBalancerType   负载均衡算法类名
     * @param customerWeightMaps 权重值设置，当负载均衡算法是权重轮询调度算法时该属性有效
     * @param defaultWeight      默认权重值，当负载均衡算法是权重轮询调度算法时该属性有效
     * @return LoadBalancer OR NULL
     */
    private LoadBalancer getLoadBalancer(final String loadBalancerType, final String customerWeightMaps, final int defaultWeight) {
        if (XLockConsts.RANDOM_LOAD_BALANCER.equals(loadBalancerType)) {
            return new RandomLoadBalancer();
        }
        if (XLockConsts.ROUND_ROBIN_LOAD_BALANCER.equals(loadBalancerType)) {
            return new RoundRobinLoadBalancer();
        }
        if (XLockConsts.WEIGHTED_ROUND_ROBIN_BALANCER.equals(loadBalancerType)) {
            Map<String, Integer> weights = new HashMap<>(16);
            String[] weightMaps = customerWeightMaps.split(XLockConsts.SEMICOLON);
            Arrays.asList(weightMaps).forEach(
                    weightMap -> weights.put(XLockConsts.REDIS_URL_PREFIX + weightMap.split(XLockConsts.COMMA)[0], Integer.parseInt(weightMap.split(XLockConsts.COMMA)[1]))
            );
            return new WeightedRoundRobinBalancer(weights, defaultWeight);
        }
        return null;
    }

    /**
     * 根据readModeType返回ReadMode.
     *
     * @param readModeType 读取操作的负载均衡模式类型
     * @return ReadMode OR NULL
     */
    private ReadMode getReadMode(final String readModeType) {
        if (XLockConsts.SLAVE.equals(readModeType)) {
            return ReadMode.SLAVE;
        }
        if (XLockConsts.MASTER.equals(readModeType)) {
            return ReadMode.MASTER;
        }
        if (XLockConsts.MASTER_SLAVE.equals(readModeType)) {
            return ReadMode.MASTER_SLAVE;
        }
        return null;
    }

    /**
     * 根据subscriptionModeType返回SubscriptionMode.
     *
     * @param subscriptionModeType 订阅操作的负载均衡模式类型
     * @return SubscriptionMode OR NULL
     */
    private SubscriptionMode getSubscriptionMode(final String subscriptionModeType) {
        if (XLockConsts.SLAVE.equals(subscriptionModeType)) {
            return SubscriptionMode.SLAVE;
        }
        if (XLockConsts.MASTER.equals(subscriptionModeType)) {
            return SubscriptionMode.MASTER;
        }
        return null;
    }

    /**
     * 设置SSL配置.
     *
     * @param lockAutoConfig lockAutoConfig
     * @param <T>            lockAutoConfig
     * @throws MalformedURLException 格式错误的URL异常
     */
    private <T extends BaseMasterSlaveServersConfig> void setLockSslConfigAndClientName(final T lockAutoConfig) throws MalformedURLException {
        lockAutoConfig.setClientName(xLockProperties.getClientName());
        lockAutoConfig.setSslEnableEndpointIdentification(xLockProperties.isSslEnableEndpointIdentification());
        if (xLockProperties.getSslKeystore() != null) {
            lockAutoConfig.setSslKeystore(new URL(xLockProperties.getSslKeystore()));
        }
        if (xLockProperties.getSslKeystorePassword() != null) {
            lockAutoConfig.setSslKeystorePassword(xLockProperties.getSslKeystorePassword());
        }
        if (xLockProperties.getSslTruststore() != null) {
            lockAutoConfig.setSslTruststore(new URL(xLockProperties.getSslTruststore()));
        }
        if (xLockProperties.getSslTruststorePassword() != null) {
            lockAutoConfig.setSslTruststorePassword(xLockProperties.getSslTruststorePassword());
        }
        lockAutoConfig.setSslProvider(XLockConsts.JDK.equalsIgnoreCase(xLockProperties.getSslProvider()) ? SslProvider.JDK : SslProvider.OPENSSL);
    }

}
