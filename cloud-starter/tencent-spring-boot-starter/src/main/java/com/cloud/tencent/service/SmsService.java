package com.cloud.tencent.service;

import com.cloud.tencent.config.SmsProperties;
import com.cloud.tencent.model.SmsType;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import com.tencentcloudapi.sms.v20190711.models.SendStatus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
@AllArgsConstructor
public class SmsService {

    private static final String COUNTRY_CODE = "+86";

    private static final String SUCCESS_CODE = "ok";

    private SmsClient smsClient;

    private SmsProperties smsProperties;

    /**
     * 发送短信
     *
     * @param cellphone 手机号
     * @param smsType  短信类型
     * @param param     短信参数
     * @return 短信发送结果
     */
    public boolean send(final String cellphone, final SmsType smsType, final String[] param) {
        SendSmsRequest smsReq = new SendSmsRequest();
        smsReq.setSmsSdkAppid(smsProperties.getAppid());
        smsReq.setSign(smsType.getSign());
        smsReq.setTemplateID(smsType.getTemplateCode());
        smsReq.setPhoneNumberSet(new String[]{COUNTRY_CODE + cellphone});
        if (param != null && param.length > 0) {
            smsReq.setTemplateParamSet(param);
        }
        try {
            SendSmsResponse res = smsClient.SendSms(smsReq);
            if (Objects.nonNull(res) && Objects.nonNull(res.getSendStatusSet())) {
                SendStatus[] sendStatusSet = res.getSendStatusSet();
                return SUCCESS_CODE.equalsIgnoreCase(sendStatusSet[0].getCode());
            }
            log.warn("腾讯云短信发送失败[{}-{}] :{}", cellphone, smsType, res);
        } catch (Exception e) {
            log.error("腾讯云短信发送异常[{}-{}] :", cellphone, smsType, e);
        }
        return false;
    }

}
