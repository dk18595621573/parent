package com.cloud.tencent.model;

import cn.hutool.core.util.StrUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dk185
 * 发票识别类型
 */
public class VatInvoiceMap {
    /** 发票识别名称对应数据库字段 */
    private static final Map<String, String> NAME_MAP = new HashMap<>();
    /** 符合去除map */
    private static final Map<String, String> MONEY_SYMBOL = new HashMap<>();
    static {
        MONEY_SYMBOL.put("合计金额", "¥");
        MONEY_SYMBOL.put("小写金额", "¥");
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
     * 去除需要清空的符号
     * @param key 识别名称
     * @param value 去除值
     * @return 去除符号
     */
    public static String cleanSymbol(String key, String value){
        if (StrUtil.isBlank(value)){
            return value;
        }
        String symbol = MONEY_SYMBOL.get(key);
        return StrUtil.replace(value, symbol, "");
    }
    private VatInvoiceMap(){}
}
