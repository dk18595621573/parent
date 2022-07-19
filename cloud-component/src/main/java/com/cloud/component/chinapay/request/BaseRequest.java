package com.cloud.component.chinapay.request;

import com.cloud.common.utils.json.JsonUtil;
import com.cloud.component.chinapay.response.BaseResponse;
import com.cloud.component.chinapay.util.Base64;
import lombok.Data;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * 基本请求体.
 *
 * @author zenghao
 * @date 2022/6/20
 */
@Data
public abstract class BaseRequest<T extends BaseResponse> implements Serializable {

    /**
     * ChinaPay 分配的入网商户号
     * 必填
     */
    private String merNo;

    /**
     * ChinaPay 分配的入网机构号 15 位数字
     * 当为“机构”时，必填。 其它情况时，不填。
     */
    private String instId;

    /**
     * 订单日期
     * 8位数字
     * 必填
     */
    private String orderDate;
    /**
     * 订单号
     * 1-32位数字
     * 必填
     */
    private String orderId;

    /**
     * 敏感数据
     */
    private String sensData;

    /**
     * 业务编号
     * 4位数字
     * 必填
     */
    public abstract String getBusiType();

    /**
     * 本次请求接口地址
     * @return
     */
    public abstract String requestUrl();

    /**
     * 接口响应类型
     * @return
     */
    public abstract Class<T> responseClass();

    /**
     * 构建敏感数据
     * @return 敏感信息
     */
    public abstract Map<String, Object> buildSensData();

    public String buildBase64ReqData() {
        return new String(Base64.encode(JsonUtil.toJson(this).getBytes(StandardCharsets.UTF_8)));
    }

}
