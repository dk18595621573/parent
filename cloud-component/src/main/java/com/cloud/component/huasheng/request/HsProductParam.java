package com.cloud.component.huasheng.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Data
public class HsProductParam implements Serializable {


    private List<SkuListDTO> skuList;
    private String method;
    private String format;
    private String sign;
    private Integer gdsId;
    private Integer gdsStock;
    private List<String> imageUrlList;
    private String appKey;
    private String v;
    private String signMethod;
    private Integer guidePrice;
    private String mainImageUrl;
    private String gdsName;
    private String isExtendedInsurance;
    private String timestamp;
    private String mainCatgs;

    @NoArgsConstructor
    @Data
    public static class SkuListDTO {
        private String skuProps;
        private Integer gdsCost;
        private Integer skuStock;
        private Integer skuPrice;
        private Integer skuId;
        private List<String> skuImageUrlList;
        private String status;
    }
}
