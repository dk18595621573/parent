package com.cloud.shadow.bean.model.callback;

import com.cloud.shadow.consts.ShadowBotEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 数据类型，用于回调接口时，三方根据这个字段来确定回调接口透传的字段.
 *
 * @author Luo
 * @date 2023-05-16 14:16
 */
@Data
@Accessors(chain = true)
public class DataTypeResult implements Serializable {

    private static final long serialVersionUID = -1681018461505563873L;

    /**
     * 回调类型.
     *
     * @see ShadowBotEnum.DataType
     */
    private String dataType;

}
