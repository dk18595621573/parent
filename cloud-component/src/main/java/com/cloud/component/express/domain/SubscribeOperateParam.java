package com.cloud.component.express.domain;

import com.cloud.component.express.consts.LogisticsOperateType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 订阅操作param
 * @author nlsm
 */
@Data
@Accessors(chain = true)
public class SubscribeOperateParam implements Serializable {

    /** 操作类型：REOPEN,REPUSH,CLOSE 表示重开、重推和关闭 */
    private LogisticsOperateType type;

    /** 附加参数信息，一次请求最多50个单号 */
    private List<Item> items;

    /**
     * 明细信息
     */
    @Data
    public static class Item implements Serializable {

        /** 订阅的快递公司的编码，一律用小写字母 */
        private String com;

        /** 订阅的快递单号， 单号的最小长度6个字符，最大长度32个字符 */
        private String num;

        public Item(String com, String num) {
            this.com = com;
            this.num = num;
        }
    }
}
