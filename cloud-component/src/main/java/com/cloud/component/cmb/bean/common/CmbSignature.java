package com.cloud.component.cmb.bean.common;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 招商银行 签名 信息.
 *
 * @author Luo
 * @date 2023-03-06 14:48
 */
@Data
@Accessors(chain = true)
public class CmbSignature implements Serializable {

    private static final long serialVersionUID = 5426718092510342817L;

    /**
     * 签名数据.
     */
    private String sigdat;

    /**
     * 签名时间.
     */
    private String sigtim;

}
