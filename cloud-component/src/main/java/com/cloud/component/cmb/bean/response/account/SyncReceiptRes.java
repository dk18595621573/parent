package com.cloud.component.cmb.bean.response.account;

import com.cloud.component.cmb.bean.request.account.SyncReceiptReq;
import com.cloud.component.cmb.bean.response.base.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 电子回单异步查询结果.
 *
 * @author nlsm
 * @dade 2023-3-8 09:24:04
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class SyncReceiptRes extends BaseResponse {

    private static final long serialVersionUID = -8952765507018151980L;

    /**
     * 回单结果（单条记录）.
     */
    private Receipt asycalhdz1;

    /**
     * 续传键值(有该对象返回时表明需要续传，将该对象下所有内容拷贝到请求报文的body继续查询).
     */
    private SyncReceiptReq ctnkeyz2;

    /**
     * 回单记录.
     */
    @Data
    @Accessors(chain = true)
    public static class Receipt implements Serializable {

        private static final long serialVersionUID = -9061421348092845857L;

        /**
         * 返回码.
         */
        private String rtncod;

        /**
         * 返回信息.
         */
        private String rtnmsg;

        /**
         * 打印任务编号（返回的任务ID，请根据该ID查询PDF文件）.
         */
        private String rtndat;

        /**
         * 任务Id.
         * 下载的文件名
         */
        private String taskid;

    }

}
