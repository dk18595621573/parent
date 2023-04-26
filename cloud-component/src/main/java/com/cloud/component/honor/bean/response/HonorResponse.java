package com.cloud.component.honor.bean.response;

import cn.hutool.core.annotation.Alias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 荣耀 接口 应答报文.
 *
 * @author Luo
 * @date 2023-3-20 10:13:45
 */
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class HonorResponse implements Serializable {

    private static final long serialVersionUID = -6811550517417623460L;

    /**
     * 返回结果true-成功，false-失败.
     */
    @Alias("Result")
    @JsonProperty("Result")
    private Boolean result;

    /**
     * 返回失败原因编码.
     */
    @Alias("ErrCode")
    @JsonProperty("ErrCode")
    private String errCode;

    /**
     * 返回失败原因描述.
     */
    @Alias("ErrMsg")
    @JsonProperty("ErrMsg")
    private String errMsg;

    /**
     * 是否请求成功.
     *
     * @return 结果
     */
    public boolean success() {
        return Boolean.TRUE.equals(getResult());
    }

}
