package com.cloud.component.fadada.api;

import com.fadada.sdk.api.AbstractAPI;
import com.fadada.sdk.api.ApiParams;

/**
 * @author peijiawei
 * @date 2/7/23 4:09 PM
 */
public class CompanyRemittanceSubmitAuthApi extends AbstractAPI {
    private String url;

    public CompanyRemittanceSubmitAuthApi() {
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url + "company_remittance_submit.api";
    }

    public String getHttpMethod() {
        return "POST";
    }

    public void setAPIParams(ApiParams apiParams) {
        this.apiParams = apiParams;
    }

    public Class getParamModelClass() {
        return null;
    }

    public boolean hasFile() {
        return true;
    }
}
