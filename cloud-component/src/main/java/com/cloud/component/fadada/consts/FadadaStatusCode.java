package com.cloud.component.fadada.consts;

import com.cloud.common.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 快递接口错误码.
 * 200	查询成功	查询成功
 * 400	找不到对应公司	提交数据不完整或者账号未充值, 检查提交的格式是否为x-www-form-urlencoded的post格式
 * 500	查询无结果，请隔段时间再查	表示查询失败，或没有POST提交
 * 501	服务器错误	快递100的服务器出现间歇或临时性异常，有时如果因为不按规范提交请求，比如快递公司参数没有按照文档规定填写等，也会报此错误
 * 502	服务器繁忙	快递100的服务器出现间歇或临时性异常，请联系快递100排查原因
 * 503	验证签名失败	请检查加密方式，param + key + customer 的顺序进行MD5加密，加密后字符串转大写
 * 601	key已过期	没有可用单量，账号需要充值使用
 *
 * @author zenghao
 */
@Getter
@AllArgsConstructor
public enum FadadaStatusCode {

    /**
     *
     */
    PUBLIC_SUCCESS("1","成功"),
    PUBLIC_ERROR("0","失败"),
    SMS_SUCCESS("1000", "自定义短信发送成功"),



    ;

    private final String code;

    private final String msg;

    public static FadadaStatusCode fromCode(String code) {
        if (StringUtils.isNotBlank(code)) {
            for (FadadaStatusCode value : FadadaStatusCode.values()) {
                if (value.getCode().equals(code)) {
                    return value;
                }
            }
        }
        return PUBLIC_SUCCESS;
    }
}