package com.cloud.component.express.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: duk
 * @Date: 2022-05-19 11:37
 * @Description: 聚合快递信息数据.
 */
@Data
public class JuheExpress implements Serializable {

    /**
     * 1表示 快递单的物流信息不会发生变化.
     */
    public static final String STATUS_END = "1";

    /**
     * 快递公司名字.
     */
    private String company;

    /**
     * 快递公司编码.
     */
    private String com;

    /**
     * 快递单号.
     */
    private String no;

    /**
     * 快递状态 1表示此快递单的物流信息不会发生变化，此时您可缓存下来；0表示有变化的可能性.
     */
    private String status;

    /**
     * 详细的状态信息，可能为null，仅作参考 见 ExpressStatus.
     */
    private String statusDetail;

    /**
     * 快递信息.
     */
    private List<ExpressItem> list;


    /**
     * 快递明细.
     *
     * @author zenghao
     */
    @Data
    public static class ExpressItem implements Serializable {

        /**
         * 物流事件发生的时间.
         */
        private String datetime;

        /**
         * 物流事件的描述.
         */
        private String remark;

        /**
         * 快件当时所在区域，由于快递公司升级，现大多数快递不提供此信息.
         */
        private String zone;
    }

}
