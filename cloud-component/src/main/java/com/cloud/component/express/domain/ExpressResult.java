package com.cloud.component.express.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 快递响应结果
 *
 * @author bejson.com (i@bejson.com)
 * @link https://api.kuaidi100.com/document/5f0ffb5ebc8da837cbd8aefc
 */
@Data
public class ExpressResult implements Serializable {

    public static final String STATE_SIGN  = "3";

    /**
     * 单号
     */
    private String nu;

    /**
     * 快递公司编码,一律用小写字母
     */
    private String com;

    /**
     * 快递单当前状态，默认为0在途，1揽收，2疑难，3签收，4退签，5派件，8清关，14拒签等10个基础物流状态，如需要返回高级物流状态，请参考 resultv2 传值
     */
    private String state;

    /**
     * 最新查询结果，数组，包含多项，全量，倒序（即时间最新的在最前），每项都是对象，对象包含字段请展开
     */
    private List<ExpressItem> data;

    /**
     * 是否已签收
     * @return true:已签收 false:未签收
     */
    public boolean signed() {
        return "3".equals(getState());
    }


    @Data
    public static class ExpressItem implements Serializable {

        /**
         * 格式化后时间.
         */
        private String ftime;

        /**
         * 内容.
         */
        private String context;

    }
}