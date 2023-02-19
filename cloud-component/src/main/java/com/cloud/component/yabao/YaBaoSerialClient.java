package com.cloud.component.yabao;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.cloud.common.constant.Constants;
import com.cloud.common.enums.EnumUtil;
import com.cloud.common.exception.ServiceException;
import com.cloud.component.properties.YaBaoProperties;
import com.cloud.component.serial.consts.SerialConstant;
import com.cloud.component.serial.domain.Serial;
import com.cloud.component.yabao.consts.BrandEnum;
import com.cloud.component.yabao.consts.InterfaceEnum;
import com.cloud.component.yabao.domain.YabaoVerifySerial;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * 手机序列号查询客户端（3203DATA）
 */
@Slf4j
public class YaBaoSerialClient {

    private final YaBaoProperties yaBaoProperties;

    public YaBaoSerialClient(final YaBaoProperties yaBaoProperties) {
        this.yaBaoProperties = yaBaoProperties;
    }

    /**
     * 查询手机序列号
     *
     * @param brand 手机品牌
     * @param sn         手机串码
     * @return
     */
    public YabaoVerifySerial query(String brand, String sn) {
        log.info("YaBaoSerialClient query start ...");
        YabaoVerifySerial verifySerial = new YabaoVerifySerial();
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("key", yaBaoProperties.getKey());
        queryMap.put("sn", sn);
        queryMap.put("apiname", EnumUtil.containsCode(InterfaceEnum.class, EnumUtil.containsMsg(BrandEnum.class, brand)));
        log.info("YaBaoSerialClient query start, {}", JSON.toJSONString(queryMap));
        String url = yaBaoProperties.getUrl();
        try {
            log.info("YaBaoSerialClient手机序列号查询:[{}]:{}", url, queryMap);
            String response = HttpUtil.get(url, queryMap, SerialConstant.TIMEOUT);
            log.info("YaBaoSerialClient手机序列号查询结果[{}]:{}", url, response);
            JSONObject resultJson = JSONUtil.parseObj(response);
            if (Constants.SUCCESS_I.equals(resultJson.get("code"))) {
                verifySerial = JSONUtil.toBean(resultJson.getJSONObject("data"), YabaoVerifySerial.class);
                verifySerial.setCode(Constants.SUCCESS_I);
            } else {
                String message = resultJson.getStr("message");
                log.info("手机序列号查询失败：{}", message);
                verifySerial.setMessage(message);
                verifySerial.setCode(Integer.parseInt(resultJson.get("code") + ""));
                return verifySerial;
            }
        } catch (Exception e) {
            log.error("手机序列号查询失败！接口连接异常", e);
            verifySerial.setCode(-1);
            verifySerial.setMessage("接口连接异常");
        }
        return verifySerial;
    }

    public static void main(String[] args) {
        YabaoVerifySerial yy = tt("余额", "0000000000000038");
        System.out.println(JSON.toJSONString(yy));
        yy = tt("华为", "863536060023586");
        System.out.println(JSON.toJSONString(yy));
        yy = tt("余额", "0000000000000038");
        System.out.println(JSON.toJSONString(yy));
    }

    public static YabaoVerifySerial tt(String brand, String sn) {
        log.info("YaBaoSerialClient query start ...");
        YabaoVerifySerial verifySerial = new YabaoVerifySerial();
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("key", "97b7c0af067848e2c95314e01d88161b");
        queryMap.put("sn", sn);
        queryMap.put("apiname", EnumUtil.containsCode(InterfaceEnum.class, EnumUtil.containsMsg(BrandEnum.class, brand)));
        log.info("YaBaoSerialClient query start, {}", JSON.toJSONString(queryMap));
        String url = "https://www.phpdream.net/api/api.php";
        try {
            log.info("YaBaoSerialClient手机序列号查询:[{}]:{}", url, queryMap);
            String response = HttpUtil.get(url, queryMap, SerialConstant.TIMEOUT);
            log.info("YaBaoSerialClient手机序列号查询结果[{}]:{}", url, response);
            JSONObject resultJson = JSONUtil.parseObj(response);
            if (Constants.SUCCESS_I.equals(resultJson.get("code"))) {
                verifySerial = JSONUtil.toBean(resultJson.getJSONObject("data"), YabaoVerifySerial.class);
                verifySerial.setCode(Constants.SUCCESS_I);
            } else {
                String message = resultJson.getStr("message");
                log.info("手机序列号查询失败：{}", message);
                verifySerial.setMessage(message);
                verifySerial.setCode(Integer.parseInt(resultJson.get("code") + ""));
            }
        } catch (Exception e) {
            log.error("手机序列号查询失败！接口连接异常", e);
            verifySerial.setCode(-1);
            verifySerial.setMessage("接口连接异常");
        }
        return verifySerial;
    }
}
