package com.cloud.component.huasheng.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class SkuMessageParam implements Serializable {

    //skuId
    private String skuId;
    //skuProps单品属性（颜色，内存等）
    private String skuProps;
    //skuPrice单品价格
    private String skuPrice;
    //skuStock单品库存
    private String skuStock;
    //gdsCost结算价
    private String gdsCost;
    //status0待上架 1上架
    private String status;
    //skuImageUrlList单品相册图片地址
    private String skuImageUrlList;

}
