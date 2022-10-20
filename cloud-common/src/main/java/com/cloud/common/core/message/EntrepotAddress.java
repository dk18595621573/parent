package com.cloud.common.core.message;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class EntrepotAddress {
    /**
     * 无仓名称
     */
    private String entrepotName;

    /**
     * 仓库编码
     */
    private String entrepotCode;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 收货人姓名
     */
    private String realname;

    /**
     * 收货人手机号
     */
    private String phone;
}
