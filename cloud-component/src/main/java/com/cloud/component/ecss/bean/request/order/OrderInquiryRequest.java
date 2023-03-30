package com.cloud.component.ecss.bean.request.order;

import com.cloud.component.ecss.bean.request.BaseECSSRequest;
import com.cloud.component.ecss.bean.response.order.OrderInquiryResponse;
import com.cloud.component.ecss.consts.ECSSEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 订单 查询（获取） 接口请求参数.
 *
 * @author Luo
 * @date 2023-03-17 13:31
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@JacksonXmlRootElement(localName = "request")
public class OrderInquiryRequest extends BaseECSSRequest<OrderInquiryResponse> implements Serializable {

    private static final long serialVersionUID = 8843183695867375069L;

    /**
     * 需要返回的字段列表，多个字段用半角逗号分
     * 隔，可选值为返回示例中能看到的所有字段. 请求格式：
     * orderInfo.orderId,orderInfo.orderCode,productInfo
     * .barCode,productInfo.productInfoExpansion.proC
     * ode,sendGoodsInfo.mobile.
     * 是否必填：否
     */
    @JacksonXmlProperty(localName = "fields")
    private String fields;

    /**
     * 要显示的页数.
     * 默认值：1
     * 是否必填：否
     */
    @JacksonXmlProperty(localName = "pageNo")
    private Integer pageNo;

    /**
     * 每页显示的条数（默认 50，最大 50）.
     * 默认值：50
     * 是否必填：否
     */
    @JacksonXmlProperty(localName = "pageSize")
    private Integer pageSize;

    /**
     * 关联店铺 id，如需查询关联店铺的订单，必填.
     * 默认值：50
     * 是否必填：否
     */
    @JacksonXmlProperty(localName = "associatedStore")
    private String associatedStore;

    /**
     * 外部订单编码(平台上的订单号).
     * 是否必填：否
     */
    @JacksonXmlProperty(localName = "outOrderNo")
    private String outOrderNo;

    /**
     * ECSS系统上的订单号.
     * 是否必填：否
     */
    @JacksonXmlProperty(localName = "orderCode")
    private String orderCode;

    /**
     * 快递快递单号.
     * 是否必填：否
     */
    @JacksonXmlProperty(localName = "expressOrderNo")
    private String expressOrderNo;

    /**
     * 店铺 ID (如果填写，需要与应用参数的店铺 Id 一致) 保留字段(作废，与系统参数冲突).
     * 是否必填：否
     */
    @JacksonXmlProperty(localName = "shopId")
    private String shopId;

    /**
     * 库房 ID.
     * 是否必填：否
     */
    @JacksonXmlProperty(localName = "storeNo")
    private String storeNo;

    /**
     * 订单状态：-1：异常单 0：草稿 1:待审单 2：不
     * 用发货 3：已取消 4：缺货 5：待拣货 6：拣货中
     * 7：校验不通过 8：校验通过 9：待发运 10：配
     * 送中 11：已换货 12：部分退货 13：全部退货
     * 14：理赔中 15：理赔完成 16：已签收 17：完
     * 成 18：待制单 19：充值成功 20：充值失败 21：
     * 充值中.
     * 是否必填：否
     */
    @JacksonXmlProperty(localName = "orderStatus")
    private Integer orderStatus;

    /**
     * 配送方式 1：送货上门 2：自提.
     * 是否必填：否
     */
    @JacksonXmlProperty(localName = "supplyType")
    private Integer supplyType;

    /**
     * 订单获取开始时间（ECSS 中订单获取的时间）.
     * 是否必填：否
     */
    @JacksonXmlProperty(localName = "getStartTime")
    private String getStartTime;

    /**
     * 订单获取结束时间(时间差不能大于).
     * 是否必填：否
     */
    @JacksonXmlProperty(localName = "getEndTime")
    private String getEndTime;

    /**
     * 订单创建时间（开始时间）.
     * 是否必填：否
     */
    @JacksonXmlProperty(localName = "greateStartTime")
    private String greateStartTime;

    /**
     * 订单创建时间（结束时间）.
     * 是否必填：否
     */
    @JacksonXmlProperty(localName = "greateEndTime")
    private String greateEndTime;

    /**
     * 订单最后修改时间（ecss 系统的最后修改时间）.
     * 是否必填：否
     */
    @JacksonXmlProperty(localName = "lastUpdateStartTime")
    private String lastUpdateStartTime;

    /**
     * 订单最后修改时间（结束时间）.
     * 是否必填：否
     */
    @JacksonXmlProperty(localName = "lastUpdateEndTime")
    private String lastUpdateEndTime;

    /**
     * 请求扩展字段.
     * 是否必填：否
     */
    @JacksonXmlProperty(localName = "expansion")
    private String expansion;

    /**
     * 获取请求方法.
     *
     * @return 请求方法
     */
    @Override
    @JsonIgnore
    public String getMethod() {
        return ECSSEnum.Method.ORDER_INQUIRY.getCode();
    }

}
