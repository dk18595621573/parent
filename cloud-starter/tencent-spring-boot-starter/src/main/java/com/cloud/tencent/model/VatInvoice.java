package com.cloud.tencent.model;

import cn.hutool.core.util.StrUtil;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author dk185
 * 增值税发货返回对象
 */
@Data
public class VatInvoice {
    /** 发票代码 */
    private String code;
    /** 发票号码 */
    private String number;
    /** 发票名称 */
    private String name;
    /** 销售方识别号 */
    private String sellerHeadingCode;
    /** 销售方名称 */
    private String sellerName;
    /** 购买方识别号 */
    private String buyerHeadingCode;
    /** 购买方名称 */
    private String buyerName;
    /** 开票日期 */
    private String invoiceDate;
    /** 购买方地址、电话 */
    private String buyerAddress;
    /** 购买方开户行及账号 */
    private String buyerBank;
    /** 货物或应税劳务、服务名称 */
    private String costName;
    /** 合计金额 */
    private BigDecimal totalMoney;
    /** 合计税额 */
    private BigDecimal totalTaxMoney;
    /** 价税合计(大写) */
    private String amountUpper;
    /** 小写金额 */
    private BigDecimal amount;
    /** 销售方地址、电话 */
    private String sellerAddress;
    /** 销售方开户行及账号 */
    private String sellerBank;
    /** 收款人 */
    private String payee;
    /** 复核 */
    private String review;
    /** 开票人 */
    private String drawer;
    /** 省 */
    private String province;
    /** 发票类型 */
    private String type;

}
