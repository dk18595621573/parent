package com.cloud.component.cmb.request.account;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 业务模式请求
 */
@Data
@Accessors(chain = true)
public class BusinessModelReq implements Serializable {

    /**
     * 业务类别
     */
    private String buscod;

}
