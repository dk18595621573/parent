package com.cloud.webmvc.utils.ip;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.cloud.common.constant.Constants;
import com.cloud.common.utils.StringUtils;
import com.cloud.core.utils.SpringUtils;
import com.cloud.webmvc.properties.SystemProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 获取地址类
 *
 * @author author
 */
public class AddressUtils {
    private static final Logger log = LoggerFactory.getLogger(AddressUtils.class);

    // IP地址查询
    private static final String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp";

    // 未知地址
    private static final String UNKNOWN = "XX XX";

    public static String getRealAddressByIP(String ip) {
        // 内网不查询
        if (IpUtils.internalIp(ip)) {
            return "内网IP";
        }
        boolean enabled = SpringUtils.getBean(SystemProperties.class).isAddressEnabled();
        if (enabled) {
            try {
                String rspStr = HttpUtil.createGet(IP_URL).setConnectionTimeout(1000).charset(Constants.GBK)
                    .form("ip", ip, "json", "true").execute().body();
                if (StringUtils.isBlank(rspStr)) {
                    log.error("获取地理位置异常 {}", ip);
                    return UNKNOWN;
                }
                JSONObject jsonObject = JSONUtil.parseObj(rspStr);
                return String.format("%s %s", jsonObject.getStr("pro"), jsonObject.getStr("city"));
            } catch (Exception e) {
                log.error("获取地理位置异常 {}", ip);
            }
        }
        return UNKNOWN;
    }
}
