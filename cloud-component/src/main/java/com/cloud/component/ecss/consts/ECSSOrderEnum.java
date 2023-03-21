package com.cloud.component.ecss.consts;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * ECSS订单枚举.
 *
 * @author Luo
 * @date 2023-03-07 9:13
 */
public class ECSSOrderEnum {

    /**
     * 订单类型.
     */
    @Getter
    @AllArgsConstructor
    public enum Type {

        /**
         * 1：普通订单.
         */
        CODE_1(1, "普通订单"),

        /**
         * 2：促销订单.
         */
        CODE_2(2, "促销订单"),

        /**
         * 3：合约机订单.
         */
        CODE_3(3, "合约机订单"),

        /**
         * 4：分销订单.
         */
        CODE_4(4, "分销订单"),

        /**
         * 5：外呼订单.
         */
        CODE_5(5, "外呼订单"),

        /**
         * 6：换货订单.
         */
        CODE_6(6, "换货订单"),

        /**
         * 7：流量订单.
         */
        CODE_7(7, "流量订单"),

        /**
         * 8：话费订单.
         */
        CODE_8(8, "话费订单"),

        /**
         * 9：号卡订单.
         */
        CODE_9(9, "号卡订单"),

        /**
         * 10：无实物订单.
         */
        CODE_10(10, "无实物订单"),

        /**
         * 11：代发订单.
         */
        CODE_11(11, "代发订单"),

        /**
         * 12：合约机订单.
         */
        CODE_12(12, "合约机订单"),

        /**
         * 13：待确认订单.
         */
        CODE_13(13, "待确认订单");

        /**
         * code.
         */
        private final Integer code;

        /**
         * 描述.
         */
        private final String desc;

        /**
         * 根据code获取.
         *
         * @param code code
         * @return 结果
         */
        public static PayType getByCode(final Integer code) {
            return Arrays.stream(PayType.values()).filter(i -> i.getCode().equals(code)).findFirst().orElse(null);
        }

    }

    /**
     * 订单状态.
     */
    @Getter
    @AllArgsConstructor
    public enum Status {

        /**
         * -1：异常单.
         */
        CODE_EXCEPTION(-1, "异常单"),

        /**
         * 0：草稿.
         */
        CODE_0(0, "草稿"),

        /**
         * 1：待审单.
         */
        CODE_1(1, "待审单"),

        /**
         * 2：不用发货.
         */
        CODE_2(2, "不用发货"),

        /**
         * 3：已取消.
         */
        CODE_3(3, "已取消"),

        /**
         * 4：缺货.
         */
        CODE_4(4, "缺货"),

        /**
         * 5：待拣货.
         */
        CODE_5(5, "待拣货"),

        /**
         * 6：拣货中.
         */
        CODE_6(6, "拣货中"),

        /**
         * 7：校验不通过.
         */
        CODE_7(7, "校验不通过"),

        /**
         * 8：校验通过.
         */
        CODE_8(8, "校验通过"),

        /**
         * 9：待发运.
         */
        CODE_9(9, "待发运"),

        /**
         * 10：配送中.
         */
        CODE_10(10, "配送中"),

        /**
         * 11：已换货.
         */
        CODE_11(11, "已换货"),

        /**
         * 12：部分退货.
         */
        CODE_12(12, "部分退货"),

        /**
         * 13：全部退货.
         */
        CODE_13(13, "全部退货"),

        /**
         * 14：理赔中.
         */
        CODE_14(14, "理赔中"),

        /**
         * 15：理赔完成.
         */
        CODE_15(15, "理赔完成"),

        /**
         * 16：已签收.
         */
        CODE_16(16, "已签收"),

        /**
         * 17：完成（未支付的
         * 单为草稿状态，已支付的是待发货；如果
         * 是货到付款的，订单状态应该为 1，支付
         * 状态为：未支付）.
         */
        CODE_17(17, "完成"),

        /**
         * 18：待制单.
         */
        CODE_18(18, "待制单"),

        /**
         * 19：充值成功.
         */
        CODE_19(19, "充值成功"),

        /**
         * 20：充值失败.
         */
        CODE_20(20, "充值失败"),

        /**
         * 21：充值中.
         */
        CODE_21(21, "充值中");

        /**
         * code.
         */
        private final Integer code;

        /**
         * 描述.
         */
        private final String desc;

        /**
         * 根据code获取.
         *
         * @param code code
         * @return 结果
         */
        public static Status getByCode(final Integer code) {
            return Arrays.stream(Status.values()).filter(i -> i.getCode().equals(code)).findFirst().orElse(null);
        }

    }

    /**
     * 销售渠道.
     */
    @Getter
    @AllArgsConstructor
    public enum PaApp {

        /**
         * 0：未区分.
         */
        CODE_0(0, "未区分"),

        /**
         * 1：PC.
         */
        CODE_1(1, "PC"),

        /**
         * 2：APP.
         */
        CODE_2(2, "APP"),

        /**
         * 3：WAP.
         */
        CODE_3(3, "WAP"),

        /**
         * 4：IPAD.
         */
        CODE_4(4, "IPAD"),

        /**
         * 5：TV.
         */
        CODE_5(5, "TV"),

        /**
         * 6：乐芃.
         */
        CODE_6(6, "乐芃");

        /**
         * code.
         */
        private final Integer code;

        /**
         * 描述.
         */
        private final String desc;

        /**
         * 根据code获取.
         *
         * @param code code
         * @return 结果
         */
        public static PaApp getByCode(final Integer code) {
            return Arrays.stream(PaApp.values()).filter(i -> i.getCode().equals(code)).findFirst().orElse(null);
        }

    }

    /**
     * 支付状态.
     */
    @Getter
    @AllArgsConstructor
    public enum PayStatus {

        /**
         * 0：未支付.
         */
        CODE_0(0, "未支付"),

        /**
         * 1：已支付.
         */
        CODE_1(1, "已支付"),

        /**
         * 2：待退款.
         */
        CODE_2(2, "待退款"),

        /**
         * 3：已退款.
         */
        CODE_3(3, "已退款"),

        /**
         * 4：已取消.
         */
        CODE_4(4, "已取消"),

        /**
         * 5：正在支付.
         */
        CODE_5(5, "正在支付");

        /**
         * code.
         */
        private final Integer code;

        /**
         * 描述.
         */
        private final String desc;

        /**
         * 根据code获取.
         *
         * @param code code
         * @return 结果
         */
        public static PayStatus getByCode(final Integer code) {
            return Arrays.stream(PayStatus.values()).filter(i -> i.getCode().equals(code)).findFirst().orElse(null);
        }

    }

    /**
     * 支付方式.
     */
    @Getter
    @AllArgsConstructor
    public enum PayType {

        /**
         * 0：货到付款.
         */
        CODE_0(0, "货到付款"),

        /**
         * 1：支付宝.
         */
        CODE_1(1, "支付宝"),

        /**
         * 2：余额宝.
         */
        CODE_2(2, "余额宝"),

        /**
         * 3：网银支付.
         */
        CODE_3(3, "网银支付"),

        /**
         * 4：消费卡.
         */
        CODE_4(4, "消费卡"),

        /**
         * 5：快捷支付.
         */
        CODE_5(5, "快捷支付"),

        /**
         * 6：积分支付.
         */
        CODE_6(6, "积分支付"),

        /**
         * 7：红包支付.
         */
        CODE_7(7, "红包支付"),

        /**
         * 8：信用卡分期.
         */
        CODE_8(8, "信用卡分期"),

        /**
         * 9：找人代付.
         */
        CODE_9(9, "找人代付"),

        /**
         * 10：网点支付.
         */
        CODE_10(10, "网点支付"),

        /**
         * 11：银行汇款.
         */
        CODE_11(11, "银行汇款"),

        /**
         * 12：财付通.
         */
        CODE_12(12, "财付通"),

        /**
         * 13：线下交易.
         */
        CODE_13(13, "线下交易"),

        /**
         * 14：微信支付.
         */
        CODE_14(14, "微信支付"),

        /**
         * 15：其他.
         */
        CODE_15(15, "其他"),

        /**
         * 16：在线支付.
         */
        CODE_16(16, "在线支付"),

        /**
         * 17：百度钱包支付.
         */
        CODE_17(17, "百度钱包支付"),

        /**
         * 18：联动优势.
         */
        CODE_18(18, "联动优势"),

        /**
         * 19：湖南手机支付.
         */
        CODE_19(19, "湖南手机支付"),

        /**
         * 20：和包支付.
         */
        CODE_20(20, "和包支付"),

        /**
         * 21：话费支付.
         */
        CODE_21(21, "话费支付"),

        /**
         * 22：无忧行支付.
         */
        CODE_22(22, "无忧行支付"),

        /**
         * 23：旧机回收.
         */
        CODE_23(23, "旧机回收"),

        /**
         * 24：浦发支付.
         */
        CODE_24(24, "浦发支付"),

        /**
         * 25：云闪付.
         */
        CODE_25(25, "云闪付"),

        /**
         * 26：平安支付.
         */
        CODE_26(26, "平安支付"),

        /**
         * 27：农行支付.
         */
        CODE_27(27, "农行支付"),

        /**
         * 99：默认.
         */
        CODE_99(99, "默认");

        /**
         * code.
         */
        private final Integer code;

        /**
         * 描述.
         */
        private final String desc;

        /**
         * 根据code获取.
         *
         * @param code code
         * @return 结果
         */
        public static PayType getByCode(final Integer code) {
            return Arrays.stream(PayType.values()).filter(i -> i.getCode().equals(code)).findFirst().orElse(null);
        }

    }

    /**
     * 支付机构.
     */
    @Getter
    @AllArgsConstructor
    public enum PayInstitution {

        /**
         * 1：支付宝.
         */
        CODE_1(1, "支付宝"),

        /**
         * 2：网银.
         */
        CODE_2(2, "网银"),

        /**
         * 3：财付通.
         */
        CODE_3(3, "财付通"),

        /**
         * 4：快钱.
         */
        CODE_4(4, "快钱"),

        /**
         * 5：壹卡会.
         */
        CODE_5(5, "壹卡会"),

        /**
         * 6：湖南手机支付平台.
         */
        CODE_6(6, "湖南手机支付平台"),

        /**
         * 7：微信支付平台.
         */
        CODE_7(7, "微信支付平台"),

        /**
         * 8：工商银行.
         */
        CODE_8(8, "工商银行"),

        /**
         * 9：建设银行.
         */
        CODE_9(9, "建设银行"),

        /**
         * 10：10085.
         */
        CODE_10(10, "10085"),

        /**
         * 11：集团.
         */
        CODE_11(11, "集团"),

        /**
         * 12：中国移动手机支付平台.
         */
        CODE_12(12, "中国移动手机支付平台"),

        /**
         * 13：中国移动统一支付平台.
         */
        CODE_13(13, "中国移动统一支付平台"),

        /**
         * 14：银联.
         */
        CODE_14(14, "银联"),

        /**
         * 15：广州银行.
         */
        CODE_15(15, "广州银行"),

        /**
         * 16：京东.
         */
        CODE_16(16, "京东"),

        /**
         * 17：和包.
         */
        CODE_17(17, "和包");

        /**
         * code.
         */
        private final Integer code;

        /**
         * 描述.
         */
        private final String desc;

        /**
         * 根据code获取.
         *
         * @param code code
         * @return 结果
         */
        public static PayInstitution getByCode(final Integer code) {
            return Arrays.stream(PayInstitution.values()).filter(i -> i.getCode().equals(code)).findFirst().orElse(null);
        }

    }

    /**
     * 合约机类型.
     */
    @Getter
    @AllArgsConstructor
    public enum BusinessType {

        /**
         * 0：无.
         */
        CODE_0(0, "无"),

        /**
         * 1：信用购.
         */
        CODE_1(1, "信用购"),

        /**
         * 2：优惠购机.
         */
        CODE_2(2, "优惠购机"),

        /**
         * 3：大顺差.
         */
        CODE_3(3, "大顺差"),

        /**
         * 4：积分购.
         */
        CODE_4(4, "积分购"),

        /**
         * 5：信用购服务包.
         */
        CODE_5(5, "信用购服务包"),

        /**
         * 6：鉴权优惠.
         */
        CODE_6(6, "鉴权优惠"),

        /**
         * 7：号码换卡.
         */
        CODE_7(7, "号码换卡"),

        /**
         * 8：号码补卡.
         */
        CODE_8(8, "号码补卡"),

        /**
         * 9：5G金币.
         */
        CODE_9(9, "5G金币"),

        /**
         * 10：其他合约.
         */
        CODE_10(10, "其他合约"),

        /**
         * 11：顺差让利.
         */
        CODE_11(11, "顺差让利"),

        /**
         * 12：顺差和金币.
         */
        CODE_12(12, "顺差和金币");

        /**
         * code.
         */
        private final Integer code;

        /**
         * 描述.
         */
        private final String desc;

        /**
         * 根据code获取.
         *
         * @param code code
         * @return 结果
         */
        public static PayInstitution getByCode(final Integer code) {
            return Arrays.stream(PayInstitution.values()).filter(i -> i.getCode().equals(code)).findFirst().orElse(null);
        }

    }


}
