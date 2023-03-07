package com.cloud.component.cmb.bean.request.account;

import com.cloud.component.cmb.bean.request.BaseCmbRequest;
import com.cloud.component.cmb.bean.response.account.ReceiptRes;
import com.cloud.component.cmb.consts.FunCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 电子回单异步查询参数.
 *
 * @author nlsm
 * @date 2023-3-6 19:01:44
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class ReceiptReq extends BaseCmbRequest<ReceiptRes> implements Serializable {
    private static final long serialVersionUID = -207202527643402927L;

    /**
     * 账号.
     */
    private String eacnbr;

    /**
     * 查询日期.
     * yyyy-MM-dd格式
     */
    private String quedat;

    /**
     * 交易流水号.
     */
    private String trsseq;

    /**
     * 打印模式.
     * 空时默认PDF  PDF 或者 OFD
     */
    private String primod;

    @Override
    public String getFuncode() {
        return FunCodeEnum.AccountQuery.DCSIGREC.name();
    }

}
