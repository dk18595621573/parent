package com.cloud.component.huasheng.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 接受物流信息.
 *
 * @author plw
 * @date 2022/08/26
 */

@Data
public class LogisticsMessageParam implements Serializable {

        private OrdExpressInfo ordExpressInfo;

        /**
         * 物流对象
         */
        @Data
        public static class OrdExpressInfo {
            //主订单号
            private String ordId;
            //物流公司名称
            private String expressName;
            //快递单号
            private String expressNo;
            //物流明细列表
            private List<LogisticsOperate> expressTrackList;
            //串码信息
            private List<Imei> imeiList;

            @Data
            public static class LogisticsOperate {
                //操作说明
                private String expressDesc;
                //操作时间
                private String expressTime;
            }

            @Data
            public static class Imei {
                //串码
                private String imei;
                //子订单号
                private String subOrder;
                //Sap单品编码
                private String sapSkuId;
            }
        }

}
