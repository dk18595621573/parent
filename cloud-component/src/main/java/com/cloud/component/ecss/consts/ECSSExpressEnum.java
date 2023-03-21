package com.cloud.component.ecss.consts;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 快递公司枚举.
 *
 * @author Luo
 * @date 2023-03-07 9:13
 */
@Getter
@AllArgsConstructor
public enum ECSSExpressEnum {

    /**
     * 1：顺丰速运.
     */
    CODE_1(1, "顺丰速运"),

    /**
     * 2：科捷.
     */
    CODE_2(2, "科捷"),

    /**
     * 3：中通.
     */
    CODE_3(3, "中通"),

    /**
     * 4：帐务查询.
     */
    CODE_4(4, "韵达"),

    /**
     * 5：圆通.
     */
    CODE_5(5, "圆通"),

    /**
     * 6：全峰.
     */
    CODE_6(6, "全峰"),

    /**
     * 7：汇通.
     */
    CODE_7(7, "汇通"),

    /**
     * 8：天天.
     */
    CODE_8(8, "天天"),

    /**
     * 9：宅急送.
     */
    CODE_9(9, "宅急送"),

    /**
     * 10：国通.
     */
    CODE_10(10, "国通"),

    /**
     * 11：龙邦物流.
     */
    CODE_11(11, "龙邦物流"),

    /**
     * 12：中国邮政.
     */
    CODE_12(12, "中国邮政"),

    /**
     * 13：安能.
     */
    CODE_13(13, "安能"),

    /**
     * 14：申通.
     */
    CODE_14(14, "申通"),

    /**
     * 15：EMS.
     */
    CODE_15(15, "EMS"),

    /**
     * 16：德邦.
     */
    CODE_16(16, "德邦"),

    /**
     * 17：京东快递.
     */
    CODE_17(17, "京东快递"),

    /**
     * 18：兆航物流.
     */
    CODE_18(18, "兆航物流"),

    /**
     * 19：南方传媒物流.
     */
    CODE_19(19, "南方传媒物流"),

    /**
     * 20：万象物流.
     */
    CODE_20(20, "万象物流"),

    /**
     * 21：鸿讯物流.
     */
    CODE_21(21, "鸿讯物流"),

    /**
     * 22：顺捷丰达物流.
     */
    CODE_22(22, "顺捷丰达物流"),

    /**
     * 23：客户自提.
     */
    CODE_23(23, "客户自提"),

    /**
     * 26：中山日报.
     */
    CODE_26(26, "中山日报"),

    /**
     * 27：全一快递.
     */
    CODE_27(27, "全一快递"),

    /**
     * 28：百世快递.
     */
    CODE_28(28, "百世快递"),

    /**
     * 29：宏递快递.
     */
    CODE_29(29, "宏递快递"),

    /**
     * 30：安时递.
     */
    CODE_30(30, "安时递"),

    /**
     * 31：天津远达和顺.
     */
    CODE_31(31, "天津远达和顺"),

    /**
     * 32：特急送.
     */
    CODE_32(32, "特急送"),

    /**
     * 33：丹鸟.
     */
    CODE_33(33, "丹鸟"),

    /**
     * 34：万家通.
     */
    CODE_34(34, "万家通"),

    /**
     * 35：秦邦物流.
     */
    CODE_35(35, "秦邦物流"),

    /**
     * 36：速必达.
     */
    CODE_36(36, "速必达"),

    /**
     * 37：海辰物流.
     */
    CODE_37(37, "海辰物流"),

    /**
     * 38：美团.
     */
    CODE_38(38, "美团");

    /**
     * code.
     */
    private final Integer code;

    /**
     * 说明.
     */
    private final String explain;

    /**
     * 根据code获取.
     *
     * @param code code
     * @return 结果
     */
    public static ECSSExpressEnum getByCode(final Integer code) {
        return Arrays.stream(ECSSExpressEnum.values()).filter(i -> i.getCode().equals(code)).findFirst().orElse(null);
    }

}
