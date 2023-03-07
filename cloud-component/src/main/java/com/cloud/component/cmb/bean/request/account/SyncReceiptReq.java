package com.cloud.component.cmb.bean.request.account;

import com.cloud.component.cmb.bean.request.BaseCmbRequest;
import com.cloud.component.cmb.bean.response.account.SyncReceiptRes;
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
public class SyncReceiptReq extends BaseCmbRequest<SyncReceiptRes> implements Serializable {
    private static final long serialVersionUID = -207202527643402927L;

    /**
     * 最小金额.
     */
    private String begamt;

    /**
     * 起始日期.
     */
    private String begdat;

    /**
     * 文件格式 (PDF 或者 OFD).
     */
    private String primod;

    /**
     * 回单代码.
     */
    private String rrccod;

    /**
     * 打印标志 (1:未打印 2:已打印).
     */
    private String rrcflg;

    /**
     * 账号.
     */
    private String eacnbr;

    /**
     * 最大金额.
     */
    private String endamt;

    /**
     * 结束日期.
     */
    private String enddat;

    /**
     * .
     */
    private String clientid;

    /**
     * .
     */
    private String daltag;

    /**
     * .
     */
    private String nxtdat;

    /**
     * .
     */
    private String nxtnbr;

    /**
     * .
     */
    private String nxttim;

    /**
     * .
     */
    private String oprtyp;

    /**
     * .
     */
    private String pagcnt;

    /**
     * .
     */
    private String pattyp;

    /**
     * .
     */
    private String plafor;

    /**
     * .
     */
    private String predat;

    /**
     * .
     */
    private String prenbr;

    /**
     * .
     */
    private String pretim;

    /**
     * .
     */
    private String spc100;

    @Override
    public String getFuncode() {
        return FunCodeEnum.AccountQuery.ASYCALHD.name();
    }

}
