package com.cloud.component.yabao;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.cloud.common.constant.Constants;
import com.cloud.common.enums.EnumUtil;
import com.cloud.component.properties.YaBaoProperties;
import com.cloud.component.serial.consts.SerialConstant;
import com.cloud.component.yabao.consts.BrandEnum;
import com.cloud.component.yabao.consts.InterfaceEnum;
import com.cloud.component.yabao.domain.YabaoVerifySerial;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 手机序列号查询客户端（3203DATA）
 */
@Slf4j
public class YaBaoSerialClient {

    public static final int ERROR_CODE = -1;

    private final YaBaoProperties yaBaoProperties;

    public YaBaoSerialClient(final YaBaoProperties yaBaoProperties) {
        this.yaBaoProperties = yaBaoProperties;
    }

    public YabaoVerifySerial queryBalance() {
        return privateQuery(BrandEnum.BALANCE.getMsg(), "");
    }

    /**
     * 查询手机序列号
     *
     * @param brand 手机品牌
     * @param sn    手机串码
     * @return
     */
    public YabaoVerifySerial query(String brand, String sn) {
        return privateQuery(brand, sn);
    }

    private YabaoVerifySerial privateQuery(String brand, String sn) {
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

             Integer code = resultJson.getInt("code");
            if (Constants.SUCCESS_I.equals(code)) {
                verifySerial = JSONUtil.toBean(resultJson.getJSONObject("data"), YabaoVerifySerial.class);
                verifySerial.setCode(Constants.SUCCESS_I);
            } else {
                String message = resultJson.getStr("message");
                log.info("手机序列号查询失败：{}", message);
                verifySerial.setMessage(message);
                verifySerial.setCode(code);

                Integer errCode = resultJson.getInt("errCode");
                if (Objects.isNull(errCode)) {
                    verifySerial.setErrCode(errCode);
                }
                return verifySerial;
            }
        } catch (Exception e) {
            log.error("手机序列号查询失败！接口连接异常", e);
            verifySerial.setCode(ERROR_CODE);
            verifySerial.setMessage("接口连接异常");
        }
        return verifySerial;
    }

}
