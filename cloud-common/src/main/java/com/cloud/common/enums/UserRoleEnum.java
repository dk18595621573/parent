package com.cloud.common.enums;

import com.cloud.common.constant.Constants;

import java.util.Arrays;
import java.util.stream.Collectors;


/**
 * 默认角色
 *
 * @author author
 */
public enum UserRoleEnum {
    ADMIN(1L, "admin", "管理员"),
    FINANCE(2L, "finance", "财务"),
    AFTER(3L, "after", "售后"),
    PURCHASING(4L, "purchasing", "采购"),
    BUSINESS(5L, "business", "商务"),
    WAREHOUSE(6L, "warehouse", "仓库");

    private final Long id;
    private final String code;
    private final String name;

    UserRoleEnum(Long id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static String getCodes() {
        return Arrays.stream(UserRoleEnum.values()).map(UserRoleEnum::getCode).collect(Collectors.joining(Constants.ROLE_DELIMETER));
    }

}
