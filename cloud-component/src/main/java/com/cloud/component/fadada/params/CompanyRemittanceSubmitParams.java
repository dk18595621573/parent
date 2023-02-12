package com.cloud.component.fadada.params;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.fadada.sdk.api.AbstractApiParams;
import com.fadada.sdk.utils.crypt.MsgDigestUtil;
import lombok.Data;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 企业信息提交
 * @author peijiawei
 * @date 2/7/23 3:59 PM
 */
@Data
public class CompanyRemittanceSubmitParams extends AbstractApiParams {
    public static final Long serialVersionUID = 1L;

    @JSONField(
            name = "customer_id"
    )
    private String customerId;

    @JSONField(
            name = "manager_transaction_no"
    )
    private String managerTransactionNo = "ccc7204041a945aeaa8694c3ac555471";

    @JSONField(
            name = "manager_type"
    )
    private String managerType = "1";

    @JSONField(
            name = "license_image_file"
    )
    private File licenseImageFile;

    @JSONField(
            name = "organization_type"
    )
    private String organizationType = "0";

    @JSONField(
            name = "company_name"
    )
    private String companyName = "上海象帝信息技术有限公司";

    @JSONField(
            name = "credit_no"
    )
    private String creditNo = "91310113MA1GNCF155";

    @JSONField(
            name = "legal_name"
    )
    private String legalName = "杜治柱";

    @JSONField(
            name = "verified_way"
    )
    private String verifiedWay = "3";

    @JSONField(
            name = "notify_url"
    )
    private String notifyUrl;

    @Override
    public String joinContentStr() {
        String str = JSON.toJSONString(this);
        Map<String, String> map = (Map)JSON.parseObject(str, HashMap.class);

        map.entrySet().removeIf((entry) -> entry.getKey().equals("license_image_file"));

        return MsgDigestUtil.sortString(map);
    }
}
