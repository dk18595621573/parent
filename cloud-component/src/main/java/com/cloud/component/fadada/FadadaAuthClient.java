package com.cloud.component.fadada;

import com.cloud.component.fadada.api.CompanyRemittanceSubmitAuthApi;
import com.cloud.component.fadada.api.PersonThreeEleAuthApi;
import com.cloud.component.fadada.params.CompanyRemittanceSubmitParams;
import com.cloud.component.fadada.params.PersonThreeEleAuthParams;
import com.fadada.sdk.extra.model.api.QuerySignResult;
import com.fadada.sdk.extra.model.req.QuerySignResultParams;
import com.fadada.sdk.http.handler.DefaultFddClient;

/**
 * 法大大企业认证接口版
 *
 * @author peijiawei
 * @date 2/7/23 3:53 PM
 */
public class FadadaAuthClient extends DefaultFddClient {

    public FadadaAuthClient(String appId, String secret, String version, String url) {
        super(appId, secret, version, url);
    }

    public String invokePersonThreeEleAuth(PersonThreeEleAuthParams params) {
        try {
            return super.invoke(new PersonThreeEleAuthApi(), params);
        } catch (Exception var3) {
            throw new RuntimeException(var3);
        }
    }

    public String invokeCompanyRemittanceSubmit(CompanyRemittanceSubmitParams params) {
        try {
            CompanyRemittanceSubmitAuthApi submitAuthApi = new CompanyRemittanceSubmitAuthApi();
            return super.invoke(submitAuthApi, params);
        } catch (Exception var3) {
            throw new RuntimeException(var3);
        }
    }
}
