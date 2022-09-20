package com.cloud.component.huasheng.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 异步下单参数.
 *
 * @author wyk
 * @date 2022/8/22
 */
@Data
public class SkuInfoParam implements Serializable {
    /**
     * 签名
     */
    String sign;
    /**
     * appkey
     */
    String appKey;
    /**
     * 时间戳，格式20200715152323
     */
    String timestamp;
    /**
     * 商品编码
     */
    String gdsId;
    /**
     * 商品名称
     */
    String gdsName;
    /**
     * 商品分类
     */
    String mainCatgs;
    /**
     * 商品指导价
     */
    String guidePrice;
    /**
     * 商品总库存
     */
    String gdsStock;
    /**
     * 单品列表信息
     */
    List<Sku> skuList;
    /**
     * 商品详情页
     */
    String introduction;
    /**
     * 商品主图地址
     */
    String mainImageUrl;
    /**
     * 商品相册图片地址
     */
    List<String> imageUrlList;

    public class Sku{
        /**
         * 单品编码
         */
        String skuId;
        /**
         * 单品属性（颜色，内存等）
         */
        String skuProps;
        /**
         * 单品价格
         */
        String skuPrice;
        /**
         * 单品库存
         */
        String skuStock;
        /**
         * 结算价
         */
        String gdsCost;
        /**
         * 0待上架 1上架
         */
        String status;
        /**
         * 单品相册图片地址
         */
        List<String> skuImageUrlList;
    }

}
