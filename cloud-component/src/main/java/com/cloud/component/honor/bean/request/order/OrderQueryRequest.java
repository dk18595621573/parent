package com.cloud.component.honor.bean.request.order;

import cn.hutool.core.annotation.Alias;
import com.cloud.component.honor.bean.request.BaseHonorRequest;
import com.cloud.component.honor.bean.response.order.OrderQueryResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 荣耀 订单查询 请求参数.
 *
 * @author Luo
 * @date 2023-04-25 15:16
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class OrderQueryRequest extends BaseHonorRequest<OrderQueryResponse> implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * 客户（买家）编码.
     */
    @Alias("cust_id")
    @JsonProperty("cust_id")
    private String custId;

    /**
     * 店铺编码(供应商).
     */
    @Alias("shop_no")
    @JsonProperty("shop_no")
    private String shopNo;

    /**
     * ALL：所有订单
     * SubmittedPay：提交支付后的所有订单；
     * Accepted：评审通过后所有订单
     * 默认值为：All.
     */
    @Alias("status")
    @JsonProperty("status")
    private String status;

    /**
     * 查询开始时间。如果未指定荣耀订单号或客户合同号，则需要指定查询时间范围。.
     */
    @Alias("update_time_start")
    @JsonProperty("update_time_start")
    private String updateTimeStart;

    /**
     * 查询结束时间。如果未指定荣耀订单号或客户合同号，则需要指定查询时间范围。.
     */
    @Alias("update_time_end")
    @JsonProperty("update_time_end")
    private String updateTimeEnd;

    /**
     * 荣耀订单号.
     */
    @Alias("honor_contract_no")
    @JsonProperty("honor_contract_no")
    private String honorContractNo;

    /**
     * 客户合同号.
     */
    @Alias("cust_contract_no")
    @JsonProperty("cust_contract_no")
    private String custContractNo;

    /**
     * 每页记录数.
     */
    @Alias("page_size")
    @JsonProperty("page_size")
    private Integer pageSize;

    /**
     * 当前页.
     */
    @Alias("current_page")
    @JsonProperty("current_page")
    private Integer currentPage;

    /**
     * 获取API请求地址.
     *
     * @return API请求地址
     */
    @Override
    public String getApiUrl() {
        return "/order/query";
    }

}
