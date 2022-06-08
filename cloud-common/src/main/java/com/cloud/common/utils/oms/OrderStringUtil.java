package com.cloud.common.utils.oms;

import com.cloud.common.constant.Constants;
import com.cloud.common.utils.DateUtils;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.UUID;

/**
 * 订单号工具类
 */
public class OrderStringUtil {
    /**
     * 生成支付订单号
     * @param orderNo 订单号
     * @return 字符串
     */
    public static String randomPayOrderNo(String orderNo) {
        return orderNo.concat("-").concat(RandomStringUtils.random(7, "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"));
    }

    /**
     * 生成支付订单号
     * @return 字符串
     */
    public static String randomPayOrderNo() {
        return "MP".concat("-").concat(RandomStringUtils.random(10, "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"));
    }

    /**
     * 生成UUID字符串，用于缓存支付信息
     * @return UUID字符串
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 生成随机（重复概率非常低）的订单号
     * @return 订单号
     */
    public static String randomUniqueNumber() {
        return "O".concat(DateUtils.dateTimeNow() + RandomStringUtils.random(5, "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"));
    }

    /**
     *  雪花算法生成订单号
     * @return
     */
    public static String snowflakeNumber(){
        return "O".concat(String.valueOf(Constants.snowflake.nextId()));
    }

    /**
     * 随机（重复概率非常低）的字符串
     * @param prefix 前缀
     * @return 字符串
     */
    public static String randomUniqueNumber(String prefix) {
        return prefix.concat(RandomStringUtils.random(7, "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"));
    }
}
