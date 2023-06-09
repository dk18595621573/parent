package com.cloud.shadow.bean.model;

import com.cloud.shadow.consts.ShadowBotEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 应用运行参数.
 *
 * @author Luo
 * @date 2023-05-16 14:16
 */
@Data
@Accessors(chain = true)
public class RobotParam implements Serializable {

    private static final long serialVersionUID = -7735215983115208702L;

    /**
     * 参数名称.
     */
    private String name;

    /**
     * 参数值.
     */
    private String value;

    /**
     * 参数类型.
     *
     * @see ShadowBotEnum.Type
     */
    private String type;

    /**
     * 构建.
     *
     * @param name  name
     * @param value value
     * @param type  type
     * @return 结果
     */
    public static RobotParam of(final String name, final String value, final ShadowBotEnum.Type type) {
        RobotParam robotParam = new RobotParam();
        robotParam.setName(name);
        robotParam.setValue(value);
        robotParam.setType(type.getCode());
        return robotParam;
    }

}
