package com.cloud.component.honor.bean.request.logistics;

import cn.hutool.core.annotation.Alias;
import com.cloud.component.honor.bean.request.BaseHonorRequest;
import com.cloud.component.honor.bean.response.logistics.LogisticsQueryResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 荣耀 实时物流信息查询 请求参数.
 *
 * @author Luo
 * @date 2023-04-25 15:16
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class LogisticsQueryRequest extends BaseHonorRequest<LogisticsQueryResponse> implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * 客户（买家）编码.
     */
    @Alias("cust_id")
    @JsonProperty("cust_id")
    private String custId;

    /**
     * 荣耀订单号。荣耀订单号和客户合同号二选一输入.
     */
    @Alias("honor_contract_no")
    @JsonProperty("honor_contract_no")
    private String honorContractNo;

    /**
     * 客户合同号。荣耀订单号和客户合同号二选一输入.
     */
    @Alias("cust_contract_no")
    @JsonProperty("cust_contract_no")
    private String custContractNo;

    /**
     * 快递公司编码,一律用小写字母.
     */
    @Alias("logis_company")
    @JsonProperty("logis_company")
    private Integer logisCompany;

    /**
     * 快递运单号.
     */
    @Alias("express_no")
    @JsonProperty("express_no")
    private Integer expressNo;

    /**
     * 获取API请求地址.
     *
     * @return API请求地址
     */
    @Override
    public String getApiUrl() {
        return "/logistics/query";
    }

}
