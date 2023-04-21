package com.cloud.component.ecss.config;

/**
 * 广移配置策略.
 *
 * @author Luo
 * @date 2023-4-20 10:58:53
 */
public class EcssConfigHolder {

    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 获取当前配置策略.
     *
     * @return 当前配置策略
     */
    public static String get() {
        return THREAD_LOCAL.get();
    }

    /**
     * 设置当前配置策略.
     *
     * @param label 策略名称
     */
    public static void set(final String label) {
        THREAD_LOCAL.set(label);
    }

    /**
     * 此方法需要用户根据自己程序代码，在适当位置手动触发调用.
     */
    public static void remove() {
        THREAD_LOCAL.remove();
    }

}
