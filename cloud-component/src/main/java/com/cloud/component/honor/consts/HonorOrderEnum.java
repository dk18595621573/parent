package com.cloud.component.honor.consts;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 荣耀订单枚举.
 *
 * @author Luo
 * @date 2023-03-07 9:13
 */
public class HonorOrderEnum {

    /**
     * 请求方法枚举.
     */
    @Getter
    @AllArgsConstructor
    public enum Status {

        /**
         * 订单已提交/待确认.
         */
        SUBMITTED("Submitted", "订单已提交/待确认"),

        /**
         * 已确认/待发货.
         */
        ACCEPTED("Accepted", "已确认/待发货"),

        /**
         * 订单已变更.
         */
        SIGNED("Signed", "订单已变更"),

        /**
         * 变更完成.
         */
        SIGN_FINISH("SignFinish", "变更完成"),

        /**
         * 商家已确认.
         */
        SELLER_CONFIRMED("SellerConfirmed", "商家已确认"),

        /**
         * 发货中/待收货.
         */
        SHIPPING("Shipping", "发货中/待收货"),

        /**
         * 已完成.
         */
        COMPLETED("Completed", "已完成"),

        /**
         * 已取消.
         */
        CANCELLED("Cancelled", "已取消"),

        /**
         * 已拒绝.
         */
        REJECTED("Rejected", "已拒绝"),

        /**
         * 已关闭.
         */
        CLOSED("Closed", "已关闭"),

        ;

        /**
         * code.
         */
        private final String code;

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
        public static Status getByCode(final String code) {
            return Arrays.stream(Status.values()).filter(i -> i.getCode().equals(code)).findFirst().orElse(null);
        }

    }

}
