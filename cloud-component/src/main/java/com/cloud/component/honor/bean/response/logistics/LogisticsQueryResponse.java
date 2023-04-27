package com.cloud.component.honor.bean.response.logistics;

import cn.hutool.core.annotation.Alias;
import com.cloud.component.honor.bean.response.HonorResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 荣耀 实时物流信息查询 响应参数.
 *
 * @author Luo
 * @date 2023-04-25 16:27
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LogisticsQueryResponse extends HonorResponse implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * 返回结果数量.
     */
    @Alias("TotalNumber")
    @JsonProperty("TotalNumber")
    private Integer totalNumber;

    /**
     * 数据.
     */
    @Alias("Data")
    @JsonProperty("Data")
    private DataDTO data;

    /**
     * 数据模型.
     */
    @Data
    @Accessors(chain = true)
    public static class DataDTO implements Serializable {

        private static final long serialVersionUID = 1181377029868582852L;

        /**
         * 数据集合.
         */
        @Alias("logis_list")
        @JsonProperty("logis_list")
        private List<Logis> logisList;

    }

    /**
     * 数据.
     */
    @Data
    @Accessors(chain = true)
    public static class Logis implements Serializable {

        private static final long serialVersionUID = 3761340447083012308L;

        /**
         * 快递公司编码,一律用小写字母.
         */
        @Alias("logis_company")
        @JsonProperty("logis_company")
        private String logisCompany;

        /**
         * 运单号.
         */
        @Alias("express_no")
        @JsonProperty("express_no")
        private String expressNo;

        /**
         * 快递单当前状态，包括0在途，1揽收，2疑难，3签收，4退签，5派件，6退回，7转单，10待清关，11清关中，12已清关，13清关异常，14收件人拒签等13个状态.
         */
        @Alias("state")
        @JsonProperty("state")
        private String state;

        /**
         * 是否签收标记.
         */
        @Alias("ischeck")
        @JsonProperty("ischeck")
        private String ischeck;

        /**
         * 事件.
         */
        @Alias("event_list")
        @JsonProperty("event_list")
        private List<Event> eventList;

    }

    /**
     * 事件.
     */
    @Data
    @Accessors(chain = true)
    public static class Event implements Serializable {

        private static final long serialVersionUID = -2274220595892975546L;

        /**
         * 内容.
         */
        @Alias("context")
        @JsonProperty("context")
        private String context;

        /**
         * 时间.
         */
        @Alias("time")
        @JsonProperty("time")
        private String time;

        /**
         * 本事件的状态，取值：在途，揽收，疑难，签收，退签，派件，退回，转单，拒签.
         */
        @Alias("status")
        @JsonProperty("status")
        private String status;

        /**
         * 本数据元对应的行政区域的编码，在订阅接口中提交resultv2 = 1字段后才会出现.
         */
        @Alias("area_code")
        @JsonProperty("area_code")
        private String areaCode;

        /**
         * 本数据元对应的行政区域的名称，在订阅接口中提交resultv2 = 1字段后才会出现.
         */
        @Alias("area_name")
        @JsonProperty("area_name")
        private String areaName;

    }

}
