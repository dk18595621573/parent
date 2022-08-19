package com.cloud.tencent.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dk185
 * 发票识别类型
 */
public class VatInvoiceMap {
    /** 发票识别名称对应数据库字段 */
    private static final Map<String, String> NAME_MAP = new HashMap<>();
    /** 异常信息返回 */
    private static final Map<String, String> BUSINESS_ERROR_MAP = new HashMap<>();
    static {
        NAME_MAP.put("发票代码", "code");
        NAME_MAP.put("发票号码", "number");
        NAME_MAP.put("发票名称", "name");
        NAME_MAP.put("销售方识别号", "sellerHeadingCode");
        NAME_MAP.put("销售方名称", "sellerName");
        NAME_MAP.put("购买方识别号", "buyerHeadingCode");
        NAME_MAP.put("购买方名称", "buyerName");
        NAME_MAP.put("开票日期", "invoiceDate");
        NAME_MAP.put("购买方地址、电话", "buyerAddressPhone");
        NAME_MAP.put("购买方开户行及账号", "buyerBankAccount");
        NAME_MAP.put("货物或应税劳务、服务名称", "costName");
        NAME_MAP.put("合计金额", "amountWithoutTax");
        NAME_MAP.put("合计税额", "taxAmount");
        NAME_MAP.put("价税合计(大写)", "amountUpper");
        NAME_MAP.put("小写金额", "amount");
        NAME_MAP.put("销售方地址、电话", "sellerAddressPhone");
        NAME_MAP.put("销售方开户行及账号", "sellerBankAccount");
        NAME_MAP.put("收款人", "payee");
        NAME_MAP.put("复核", "review");
        NAME_MAP.put("开票人", "drawer");
        NAME_MAP.put("省", "province");
        NAME_MAP.put("发票类型", "type");
        NAME_MAP.put("备注", "remark");

        BUSINESS_ERROR_MAP.put("FailedOperation.ArrearsError", "帐号已欠费,请联系充值");
        BUSINESS_ERROR_MAP.put("FailedOperation.CountLimitError", "超过该张发票当日查验次数,请明天再试");
        BUSINESS_ERROR_MAP.put("FailedOperation.InvoiceMismatch", "发票数据不一致");
//        BUSINESS_ERROR_MAP.put("FailedOperation.UnKnowError", "腾讯云内部异常");
        BUSINESS_ERROR_MAP.put("FailedOperation.UnOpenError", "服务未开通");
        BUSINESS_ERROR_MAP.put("InvalidParameterValue.InvalidParameterValueLimit", "参数值错误");
        BUSINESS_ERROR_MAP.put("InvalidParameterValue.PriceOrVerificationParameterValueLimit", "开票金额或校验码错误");
        BUSINESS_ERROR_MAP.put("InvalidParameterValue.TicketCodeParameterValueLimit", "发票代码错误");
        BUSINESS_ERROR_MAP.put("InvalidParameterValue.TicketDateParameterValueLimit", "开票日期错误");
        BUSINESS_ERROR_MAP.put("InvalidParameterValue.TicketSnParameterValueLimit", "发票号码错误");
        BUSINESS_ERROR_MAP.put("ResourceNotFound.NoAreaCode", "地区编码不存在");
        BUSINESS_ERROR_MAP.put("ResourceNotFound.NoInvoice", "发票不存在");
        BUSINESS_ERROR_MAP.put("ResourceNotFound.NotSupportCurrentInvoiceQuery", "不支持当天发票查询");
        BUSINESS_ERROR_MAP.put("ResourceUnavailable.TaxNetworkError", "税务局网络异常，请稍后访问");
        BUSINESS_ERROR_MAP.put("ResourcesSoldOut.ChargeStatusException", "计费状态异常");
    }

    /**
     * 根据识别名称获取对应字段名称
     * @param key 识别名称
     * @return 字段名称
     */
    public static String getNameField(String key){
        return NAME_MAP.get(key);
    }

    /**
     * 根据异常编码获取对应的异常信息
     * @param key 异常编码
     * @return 异常说明
     */
    public static String getErrorMessage(String key){
        return BUSINESS_ERROR_MAP.getOrDefault(key, "发票核验失败");
    }

    private VatInvoiceMap(){}
}
