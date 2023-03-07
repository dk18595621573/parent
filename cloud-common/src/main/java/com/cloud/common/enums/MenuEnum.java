package com.cloud.common.enums;

import com.cloud.common.constant.Constants;

import java.util.Arrays;
import java.util.stream.Collectors;


/**
 * 菜单权限枚举
 *
 * @author author
 */
public enum MenuEnum {
    INFO("info", "C", "商户管理"),
    SUB_ACCOUNT("subAccount", "C", "账号管理"),
    PAYER("payer", "C", "付款主体"),
    MANAGEMENT("management", "C", "店铺管理"),
    COMPANY_MANAGEMENT("companyManagement", "C", "供应商管理"),
    DEMAND_MANAGEMENT("demandManagement", "C", "采购商管理"),
    READONLY("readonly", "R", "只读");


    private final String path;
    private final String type;
    private final String remark;

    MenuEnum(String path, String type, String remark) {
        this.path = path;
        this.type = type;
        this.remark = remark;
    }


    public String getPath() {
        return path;
    }

    public String getType() {
        return type;
    }

    public String getRemark() {
        return remark;
    }

    public static String getPaths() {
        return Arrays.stream(MenuEnum.values()).map(MenuEnum::getPath).collect(Collectors.joining(Constants.ROLE_DELIMETER));
    }

}
