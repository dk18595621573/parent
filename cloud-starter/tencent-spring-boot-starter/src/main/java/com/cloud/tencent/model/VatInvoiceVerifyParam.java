package com.cloud.tencent.model;

import lombok.Data;

/**
 * @author dk185
 * 值税发票的准确性核验param入参
 */
@Data
public class VatInvoiceVerifyParam {
    /** 发票号码，8位、20位（全电票） */
    private String invoiceNo;
    /** 开票日期（不支持当天发票查询，支持五年以内开具的发票），格式：“YYYY-MM-DD”，如：2019-12-20 */
    private String invoiceDate;
    /** 发票代码（10或12 位），全电发票为空。查验未成功超过5次后当日无法再查 */
    private String invoiceCode;
    /** 票种类型
     * 01:增值税专用发票，02:货运运输业增值税专用发 票，03:机动车销售统一发票，04:增值税普通发票，08:增值税电子专用发票(含全电)，
     * 10:增值税电子普通发票(含全电)， 11:增值税普通发票(卷式)， 14:增值税电子(通行费)发 票， 15:二手车销售统一发票，
     * 32:深圳区块链发票(云南区块链因业务调整现已下线) */
    private String invoiceKind;
    /** 校验码后 6 位，增值税普通发票、增值税电子普通发票、增值税普通发票(卷式)、增值税电子普通发票(通行费)时必填;区块链为 5 位 */
    private String checkCode;
    /** 不含税金额，增值税专用发票、增值税电子专用发票、机动车销售统一发票、二手车销售统一发票、区块链发票时必填; 全电发票为价税合计(含税金额) */
    private String amount;
}
