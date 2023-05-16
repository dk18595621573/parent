package com.cloud.shadow.bean.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 影刀RPA API 响应.
 *
 * @author Luo
 * @date 2023-05-16 13:29
 */
@Data
@Accessors(chain = true)
public abstract class BaseResponse implements Serializable {

    private static final long serialVersionUID = -1L;

}
