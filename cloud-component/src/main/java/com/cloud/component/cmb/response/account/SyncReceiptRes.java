package com.cloud.component.cmb.response.account;

import com.cloud.component.cmb.request.account.SyncReceiptReq;
import com.cloud.component.cmb.response.BaseResponse;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 *  电子回单异步查询结果
 */
@Data
@Accessors(chain = true)
public class SyncReceiptRes extends BaseResponse {
    /**
     * 回单结果（单条记录）
     */
    private List<Receipt> asycalhdz1;

    /**
     * 续传键值(有该对象返回时表明需要续传，将该对象下所有内容拷贝到请求报文的body继续查询)
     */
    private SyncReceiptReq ctnkeyz2;

    @Data
    @Accessors(chain = true)
    public class Receipt{
        /**
         *  返回码
         */
        private String rtncod;
        /**
         * 返回信息
         */
        private String rtnmsg;
        /**
         * 打印任务编号（返回的任务ID，请根据该ID查询PDF文件）
         */
        private String rtndat;
    }
}
