package com.cloud.component.fadada.params;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.fadada.sdk.api.AbstractApiParams;
import com.fadada.sdk.utils.crypt.MsgDigestUtil;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 个人三要素认证
 * @author peijiawei
 * @date 2/7/23 3:59 PM
 */
@Data
public class PersonThreeEleAuthParams extends AbstractApiParams {
    public static final Long serialVersionUID = 1L;

    @JSONField(
            name = "customer_id"
    )
    private String customerId;

    @JSONField(
            name = "id_card"
    )
    private String idCard;

    @JSONField(
            name = "person_name"
    )
    private String personName;

    @JSONField(
            name = "mobile"
    )
    private String mobile;

    @JSONField(
            name = "sms_verification_code"
    )
    private String smsVerificationCode;

    @JSONField(
            name = "is_need_face_auth"
    )
    private String isNeedFaceAuth = "0";

    @Override
    public String joinContentStr() {
        String str = JSON.toJSONString(this);
        Map<String, String> map = (Map)JSON.parseObject(str, HashMap.class);
        return MsgDigestUtil.sortString(map);
    }
}
