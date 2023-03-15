package com.cloud.component.fadada.api;

import com.fadada.sdk.api.AbstractAPI;
import com.fadada.sdk.api.ApiParams;

/**
 * 合同撤销
 * @author peijiawei
 * @date 2/7/23 4:09 PM
 */
public class CancellationOfContractApi extends AbstractAPI {
    private String url;

    public CancellationOfContractApi() {
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url + "cancellation_of_contract.api";
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

}
