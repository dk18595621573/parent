package com.cloud.component.fadada.params;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.fadada.sdk.api.AbstractApiParams;
import com.fadada.sdk.utils.crypt.MsgDigestUtil;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 合同撤销
 * @author peijiawei
 * @date 3/15/23 1:26 PM
 */
@Data
public class CancellationOfContractParams extends AbstractApiParams {
    public static final Long serialVersionUID = 1L;

    @JSONField(
            name = "contract_id"
    )
    private String contractId;

    @Override
    public String joinContentStr() {
        String str = JSON.toJSONString(this);
        Map<String, String> map = (Map)JSON.parseObject(str, HashMap.class);
        return MsgDigestUtil.sortString(map);
    }
}
