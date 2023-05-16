package com.cloud.component.shadow.bean.model.callback;

import com.cloud.component.shadow.consts.ShadowBotEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataTypeResult implements Serializable {

    private static final long serialVersionUID = -1681018461505563873L;

    /**
     * 回调类型.
     *
     * @see ShadowBotEnum.DataType
     */
    private String dataType;

}
