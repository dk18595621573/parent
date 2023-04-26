package com.cloud.component.honor.bean.response.order;

import cn.hutool.core.annotation.Alias;
import com.cloud.component.honor.bean.response.HonorResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 荣耀 出库串码查询 响应参数.
 *
 * @author Luo
 * @date 2023-04-25 16:27
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderOutboundSnResponse extends HonorResponse implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * 返回结果数量.
     */
    @Alias("TotalNumber")
    @JsonProperty("TotalNumber")
    private Integer totalNumber;

    /**
     * 数据.
     */
    @Alias("Data")
    @JsonProperty("Data")
    private DataDTO data;

    /**
     * 数据模型.
     */
    @Data
    @Accessors(chain = true)
    public static class DataDTO implements Serializable {

        private static final long serialVersionUID = 1181377029868582852L;

        /**
         * 数据集合.
         */
        @Alias(" sn_list")
        @JsonProperty(" sn_list")
        private List<Sn> snList;

    }

    /**
     * 数据.
     */
    @Data
    @Accessors(chain = true)
    public static class Sn implements Serializable {

        private static final long serialVersionUID = 3761340447083012308L;

        /**
         * 订单号.
         */
        @Alias("honor_contract_no")
        @JsonProperty("honor_contract_no")
        private String honorContractNo;

        /**
         * 客户自定义合同号.
         */
        @Alias("cust_contract_no")
        @JsonProperty("cust_contract_no")
        private String custContractNo;

        /**
         * 出库单号.
         */
        @Alias("outbound_no")
        @JsonProperty("outbound_no")
        private String outboundNo;

        /**
         * 荣耀商品编码.
         */
        @Alias("prod_code_sale")
        @JsonProperty("prod_code_sale")
        private String prodCodeSale;

        /**
         * SN/IMEI号.
         */
        @Alias("sn_code")
        @JsonProperty("sn_code")
        private String snCode;

        /**
         * IMEI 1.
         */
        @Alias("IMEI1")
        @JsonProperty("IMEI1")
        private String imei1;

        /**
         * IMEI 2.
         */
        @Alias("IMEI2")
        @JsonProperty("IMEI2")
        private String imei2;

    }

}
