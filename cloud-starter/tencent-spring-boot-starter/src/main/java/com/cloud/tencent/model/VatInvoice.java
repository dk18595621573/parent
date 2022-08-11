package com.cloud.tencent.model;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    private String buyerAddressPhone;
    /** 购买方开户行及账号 */
    private String buyerBankAccount;
    /** 货物或应税劳务、服务名称 */
    private String costName;
    /** 合计金额 */
    private String amountWithoutTax;
    /** 合计税额 */
    private String taxAmount;
    /** 价税合计(大写) */
    private String amountUpper;
    /** 小写金额 */
    private String amount;
    /** 销售方地址、电话 */
    private String sellerAddressPhone;
    /** 销售方开户行及账号 */
    private String sellerBankAccount;
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

    /**
     * 转换日期格式为：yyyy-MM-dd格式
     * @param invoiceDate 需要转换入参
     */
    public void setInvoiceDate(String invoiceDate) {
        try {
            DateTime dateTime = DateUtil.parse(invoiceDate, "yyyy年MM月dd日");
            this.invoiceDate = DateUtil.format(dateTime, "yyyy-MM-dd");
        }catch (Exception e){
            this.invoiceDate = invoiceDate;
        }
    }

    /**
     * 去除前缀No
     * @param number 发票号
     */
    public void setNumber(String number) {
        this.number = StrUtil.replace(number, "No", "");
    }

    public void setAmountWithoutTax(String amountWithoutTax) {
        this.amountWithoutTax = StrUtil.replace(amountWithoutTax, "¥", "");
    }

    public void setTaxAmount(String taxAmount) {
        this.taxAmount = StrUtil.replace(taxAmount, "¥", "");
    }

    public void setAmount(String amount) {
        this.amount = StrUtil.replace(amount, "¥", "");
    }
}
