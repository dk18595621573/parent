package com.cloud.component.honor.bean.response.order;

import cn.hutool.core.annotation.Alias;
import com.cloud.component.honor.bean.response.HonorResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 荣耀 订单创建 响应参数.
 *
 * @author Luo
 * @date 2023-04-25 16:27
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class OrderCreateResponse extends HonorResponse implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * 创建成功后，会返回荣耀订单号.
     */
    @Alias("honor_contract_no")
    @JsonProperty("honor_contract_no")
    private String honorContract_no;

}
