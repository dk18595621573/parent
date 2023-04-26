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
 * 荣耀 出库信息查询 响应参数.
 *
 * @author Luo
 * @date 2023-04-25 16:27
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderOutboundResponse extends HonorResponse implements Serializable {

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
        @Alias("outbound_list")
        @JsonProperty("outbound_list")
        private List<Outbound> outboundList;

    }

    /**
     * 数据.
     */
    @Data
    @Accessors(chain = true)
    public static class Outbound implements Serializable {

        private static final long serialVersionUID = 3761340447083012308L;

        /**
         * 店铺编码.
         */
        @Alias("shop_no")
        @JsonProperty("shop_no")
        private String shopNo;

        /**
         * 客户（买家）编码.
         */
        @Alias("cust_id")
        @JsonProperty("cust_id")
        private String custId;

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
         * 出库人.
         */
        @Alias("outbound_by")
        @JsonProperty("outbound_by")
        private String outboundBy;

        /**
         * 出库时间.
         */
        @Alias("outbound_date")
        @JsonProperty("outbound_date")
        private String outboundDate;

        /**
         * 快递公司编码,一律用小写字母.
         */
        @Alias("logis_company")
        @JsonProperty("logis_company")
        private String logisCompany;

        /**
         * 快递运单号，自提则传“自提”两个汉字.
         */
        @Alias("express_no")
        @JsonProperty("express_no")
        private String expressNo;

        /**
         * 发货仓库编码.
         */
        @Alias("store_house")
        @JsonProperty("store_house")
        private String storeHouse;

        /**
         * 发货仓库名称.
         */
        @Alias("store_house_name")
        @JsonProperty("store_house_name")
        private String storeHouseName;

        /**
         * 收货国家.
         */
        @Alias("country")
        @JsonProperty("country")
        private String country;

        /**
         * 国家ISO二位编码.
         */
        @Alias("country_code")
        @JsonProperty("country_code")
        private String countryCode;

        /**
         * 收货省份.
         */
        @Alias("province")
        @JsonProperty("province")
        private String province;

        /**
         * 省份ISO二位编码.
         */
        @Alias("province_code")
        @JsonProperty("province_code")
        private String provinceCode;

        /**
         * 收货城市.
         */
        @Alias("city")
        @JsonProperty("city")
        private String city;

        /**
         * 城市ISO二位编码.
         */
        @Alias("city_code")
        @JsonProperty("city_code")
        private String cityCode;

        /**
         * 收货乡镇/街道.
         */
        @Alias("region")
        @JsonProperty("region")
        private String region;

        /**
         * 区县ISO二位编码.
         */
        @Alias("region_code")
        @JsonProperty("region_code")
        private String regionCode;

        /**
         * 地址简称.
         */
        @Alias("address_name")
        @JsonProperty("address_name")
        private String addressName;

        /**
         * 地址ID.
         */
        @Alias("address_id")
        @JsonProperty("address_id")
        private String addressId;

        /**
         * 串码信息.
         */
        @Alias("item_lines")
        @JsonProperty("item_lines")
        private List<ItemLines> itemLines;

    }

    /**
     * 串码信息.
     */
    @Data
    @Accessors(chain = true)
    public static class ItemLines implements Serializable {

        private static final long serialVersionUID = -2274220595892975546L;

        /**
         * 荣耀商品编码.
         */
        @Alias("prod_code_sale")
        @JsonProperty("prod_code_sale")
        private String prodCodeSale;

        /**
         * 数量.
         */
        @Alias("quantity")
        @JsonProperty("quantity")
        private Integer quantity;

        /**
         * 备注（例如标识是否是有串码，有串码则放空，无串码则传值“无串码”）.
         */
        @Alias("comment")
        @JsonProperty("comment")
        private String comment;

    }

}
