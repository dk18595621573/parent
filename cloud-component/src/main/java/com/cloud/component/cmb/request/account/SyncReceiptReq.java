package com.cloud.component.cmb.request.account;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 电子回单异步查询参数
 */
@Data
@Accessors(chain = true)
public class SyncReceiptReq implements Serializable {
    /**
     *  最小金额
     */
    private String begamt;
    /**
     *  起始日期
     */
    private String begdat;
    /**
     *  文件格式 (PDF 或者 OFD)
     */
    private String primod;
    /**
     *  回单代码
     */
    private String rrccod;
    /**
     *  打印标志 (1:未打印 2:已打印)
     */
    private String rrcflg;
    /**
     *  账号
     */
    private String eacnbr;
    /**
     *  最大金额
     */
    private String endamt;
    /**
     *  结束日期
     */
    private String enddat;

    /**
     *
     */
    private String clientid;
    /**
     *
     */
    private String daltag;
    /**
     *
     */
    private String nxtdat;
    /**
     *
     */
    private String nxtnbr;
    /**
     *
     */
    private String nxttim;
    /**
     *
     */
    private String oprtyp;
    /**
     *
     */
    private String pagcnt;
    /**
     *
     */
    private String pattyp;
    /**
     *
     */
    private String plafor;
    /**
     *
     */
    private String predat;
    /**
     *
     */
    private String prenbr;
    /**
     *
     */
    private String pretim;
    /**
     *
     */
    private String spc100;
}
