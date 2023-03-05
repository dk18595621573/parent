package com.cloud.component.bot.config;

/**
 * 微信机器人配置策略.
 *
 * @author zenghao
 * @date 2021/3/12
 */
public class WxworkBotConfigHolder {

  private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

  /**
   * 获取当前配置策略.
   * @return 当前配置策略
   */
  public static String get() {
    return THREAD_LOCAL.get();
  }

  /**
   * 设置当前配置策略.
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
