package com.cloud.component.cmb.request.account;


import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 *  获取回单结果文件路径参数
 */
@Data
@Accessors(chain = true)
public class ReceiptUrlReq implements Serializable {

    /**
     * 任务id (电子回单异步查询请求返回报文中的rtndat字段)
     */
    private String taskid;

}
