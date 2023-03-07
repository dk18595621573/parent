package com.cloud.component.cmb.bean.request.account;


import com.cloud.component.cmb.bean.request.BaseCmbRequest;
import com.cloud.component.cmb.bean.response.account.ReceiptUrlRes;
import com.cloud.component.cmb.consts.FunCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 获取回单结果文件路径参数.
 *
 * @author nlsm
 * @date 2023-3-6 20:30:22
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class ReceiptUrlReq extends BaseCmbRequest<ReceiptUrlRes> implements Serializable {

    private static final long serialVersionUID = 5753870453497755162L;

    /**
     * 任务id (电子回单异步查询请求返回报文中的rtndat字段).
     */
    private String taskid;

    @Override
    public String getFuncode() {
        return FunCodeEnum.AccountQuery.DCTASKID.name();
    }

}
