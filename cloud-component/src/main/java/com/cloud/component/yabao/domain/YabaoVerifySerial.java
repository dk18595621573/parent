package com.cloud.component.yabao.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 鸭宝查询返回实体类
 * @author: zhushanshuo
 * @create: Thu Feb 16 11:18:17 CST 2023
 **/
@Data
public class YabaoVerifySerial implements Serializable {
    private Long id;     //主键id

    private Long orderId;     //订单id

    private String brandType;     //品牌

    private String category;     //类别

    private String productModel;     //产品型号

    private String commonName;     //通用名

    private String commonSku;     //通用sku

    private String commonCode;     //通用编码

    private String imei;     //串码

    private String sn;     //sn

    private String model;     //型号

    private String description;     //零件说明（存在有的机器只有零件说明没有型号）（华为荣耀、三星）

    private String brand;     //品牌（华为荣耀）

    private String skuItemCode;     //skuItemCode（华为荣耀）

    private String product;     //产品代码一般在包装盒上面的型号后面（华为荣耀）

    private String productType;     //产品代码的全写，上面的是缩写（华为荣耀）

    private String productOffering;     //productOffering（华为荣耀）

    private String network;     //支持的网络版本（华为荣耀）

    private String lifeCycleFlag;     //lifeCycleFlag（华为荣耀）

    private String purchase;     //{"date": "2020/6/11",激活日期"country": "中国"购买国家}（华为荣耀）

    private String warrStatus;     //W是在保 OOW是过保（华为荣耀）

    private String covered;     //covered（华为荣耀）

    private String coverage;     //保修截止日期（华为荣耀）

    private String replaced;     //是否官换机，枚举值（空代表不是，1代表是）（华为荣耀、苹果、三星）

    private String activatedReason;     //机器说明（华为荣耀）

    private String isAutoActive;     //是否空中激活，枚举值（空代表不是，1代表是）（华为荣耀）

    private String personal;     //是否定制机，该字段只有为1的时候才准确，为0不代表不是定制机，枚举值（空代表不是，1代表是）（华为荣耀）

    private String isRFB;     //是否官翻机，枚举值（空代表不是，1代表是）（华为荣耀）

    private String isDemo;     //是否展示机，枚举值（空代表不是，1代表是）（华为荣耀）

    private String isRepair;     //是否维修，枚举值（空代表不是，1代表是）（华为荣耀）

    private String format_records;     //{"激活日期": "已于 2020/6/11 激活","出厂日期": "2020/3/26","电子保卡日期": "2020/6/11"}（华为荣耀）

    private String activatedStatus;     //激活状态，枚举值（已激活、未激活）（华为荣耀）

    private String score;     //机器得分0或者100，枚举值（0、100）（华为荣耀）

    private String meid;     //meid（苹果）

    private String registered;     //是否已注册设备，已注册设备代表保修已经开始（苹果）

    private String activated;     //激活状态，枚举值：已激活、未激活（苹果、小米）

    private String validPurchaseDate;     //是否已验证购买日期，未验证一般都是未激活的机器，已激活未验证的比较少，但是也有（苹果）

    private String thumbnail;     //官方图片（苹果）

    private String loaner;     //是否借出机器，去官方维修的时候，一般会给你一部手机用（苹果）

    private String acEligible;     //是否有ac的购买资格，0/1（苹果）

    private String technicalSupport;     //是否还有电话技术支持（苹果）

    private String repairCoverage;     //是否还在保（苹果）

    private String repairExpiry;     //保修截止日期（苹果）

    private String warrantyStatus;     //保修状态（苹果）

    private String appleCare;     //是否购买AC（苹果）

    private String preActivated;     //是否预激活机器（苹果）

    private String introduction;     //机器说明（苹果）

    private String coverageStartDate;     //保修开始日期（该字段不一定一直有，特殊机器不返回）（苹果）

    private String estPurchaseDate;     //激活日期（该字段不一定一直有，特殊机器不返回）（苹果）

    private String name;     //推广型号（三星）

    private String carrier;     //购买运营商，国行可无视此字段为固定值（三星）

    private String carrierName;     //购买地点（三星）

    private String activationDate;     //激活日期（三星）

    private String production;     //{"date": "2022-12-21",生产日期 "country": "China (Open China)"国行无视此字段，和购买运营商}（三星）

    private String country;     //购买地点，如果不是China则不是国行（小米）

    private String manufacture;     //生产日期（小米）

    private String activateDate;     //激活日期（小米）

    private Integer code;  // code返回码

    private String message; // 返回描述

    private Long createBy;     //创建人id

    private String createName;     //创建人名称

    private Date createTime;     //创建时间

    private Long updateBy;     //修改人

    private String updateName;     //修改人名称

    private Date updateTime;     //修改时间

    private Integer deleted;     //删除标识：0-未删除

    private Long deleteBy;     //删除人

    private Date deleteTime;     //删除时间

}
