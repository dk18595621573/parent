package com.cloud.common.enums;

import java.util.Objects;

/**
 * 发票详情表 状态
 *
 * @date 2023.02.23
 */
public enum PaymentSummaryInvoicePlanStatus implements BaseEnum {

	UN_REACH(10, "待核验"),
	SOME_REACH(20, "核验成功"),
	WAIT_CHECK(30, "核验失败"),
	CHECK_SUCCESS(40, "作废"),
	CHECK_FAIL(50, "异常发票"),
	;

	Integer code;

	String msg;

	PaymentSummaryInvoicePlanStatus(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	@Override
	public Integer getCode() {
		return code;
	}

	@Override
	public String getMsg() {
		return msg;
	}

	public static PaymentSummaryInvoicePlanStatus fromCode(Integer code) {
		if (Objects.nonNull(code)) {
			for (PaymentSummaryInvoicePlanStatus status : PaymentSummaryInvoicePlanStatus.values()) {
				if (status.getCode().equals(code)) {
					return status;
				}
			}
		}
		return null;
	}

}
