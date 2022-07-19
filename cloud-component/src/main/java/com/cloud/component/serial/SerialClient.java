package com.cloud.component.serial;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.cloud.common.constant.Constants;
import com.cloud.common.exception.ServiceException;
import com.cloud.component.properties.SerialProperties;
import com.cloud.component.serial.consts.SerialConstant;
import com.cloud.component.serial.domain.Serial;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * 手机序列号查询客户端（3203DATA）
 */
@Slf4j
public class SerialClient {

    private final SerialProperties serialProperties;

    public SerialClient(final SerialProperties serialProperties) {
        this.serialProperties = serialProperties;
    }

    /**
     * 查询手机序列号
     *
     * @param serialType 手机品牌
     * @param sn         手机串码
     * @return
     */
    public Serial querySerial(String serialType, String sn) {
        Serial serial = new Serial();
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("key", serialProperties.getKey());
        queryMap.put("sn", sn);
        String url = serialProperties.getUrl() + serialType + "/coverage";
        try {
            log.debug("手机序列号查询:[{}]:{}", url, queryMap);
            String response = HttpUtil.get(url, queryMap, SerialConstant.TIMEOUT);
            log.debug("手机序列号查询结果[{}]:{}", url, response);
            JSONObject resultJson = JSONUtil.parseObj(response);
            if (Constants.SUCCESS_I.equals(resultJson.get("code"))) {
                serial = JSONUtil.toBean(SerialConstant.APPLE.equals(serialType) ? resultJson : resultJson.getJSONObject("data"), Serial.class);
            } else {
                String message = resultJson.getStr("message");
                log.warn("手机序列号查询失败：{}", message);
                serial.setMessage(message);
                return serial;
            }
        } catch (Exception e) {
            log.error("手机序列号查询失败！接口连接异常", e);
            serial.setMessage("接口连接异常");
            throw new ServiceException("手机序列号查询失败!接口连接异常");
        }
        return serial;
    }
}
