package com.cloud.component.honor.bean.request.order;

import cn.hutool.core.annotation.Alias;
import com.cloud.component.honor.bean.request.BaseHonorRequest;
import com.cloud.component.honor.bean.response.order.OrderOutboundSnResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 荣耀 出库串码查询 请求参数.
 *
 * @author Luo
 * @date 2023-04-25 15:16
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class OrderOutboundSnRequest extends BaseHonorRequest<OrderOutboundSnResponse> implements Serializable {

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
     * 查询开始时间，按创建时间查询。如果未指定荣耀订单号或客户合同号，则需要指定查询时间范围。.
     */
    @Alias("create_time_start")
    @JsonProperty("create_time_start")
    private String createTimeStart;

    /**
     * 查询结束时间，按创建时间查询。如果未指定荣耀订单号或客户合同号，则需要指定查询时间范围。.
     */
    @Alias("create_time_end")
    @JsonProperty("create_time_end")
    private String createTimeEnd;

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
        return "/order/outboundsn";
    }

}
