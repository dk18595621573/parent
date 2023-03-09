package com.cloud.component.cmb.bean.response.pay;

import com.cloud.component.cmb.bean.response.base.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 支付结果.
 *
 * @author nlsm
 * @date 2023-3-9 13:13:57
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class PaymentInfoRes extends BaseResponse {

    private static final long serialVersionUID = -7783389626359970902L;

    /**
     * 系统内标志(Y:收方为招商银行账号 N:收方为他行账户).
     */
    private String bnkFlg;

    /**
     * 币种(10:人民币).
     */
    private String ccyNbr;

    /**
     * 收方帐号.
     */
    private String crtAcc;

    /**
     * 收方户名.
     */
    private String crtNam;

    /**
     * 付方帐号.
     */
    private String dbtAcc;

    /**
     * 转出分行号.
     */
    private String dbtBbk;

    /**
     * 付方帐户名.
     */
    private String dbtNam;

    /**
     * 用途.
     */
    private String nusAge;

    /**
     * 结算通道(G 普通; Q 快速; R 实时-超网 ;).
     */
    private String stlChn;

    /**
     * 交易金额.
     */
    private String trsAmt;

    /**
     * 业务参考号.
     */
    private String yurRef;

    /**
     * 付方记账子单元编号.
     */
    private String dmaNbr;

    /**
     * 收方开户行名称.
     */
    private String crtBnk;

    /**
     * 收方开户行地址.
     */
    private String crtAdr;

    /**
     * 期望日.
     */
    private String eptDat;

    /**
     * 期望时间.
     */
    private String eptTim;

    /**
     * 业务摘要.
     */
    private String busNar;

    /**
     * 通知方式一（邮箱）.
     */
    private String ntfCh1;

    /**
     * 通知方式二（手机号）.
     */
    private String ntfCh2;

    /**
     * 业务种类(100001:普通汇兑 （默认值） 101001:慈善捐款  101002:其他 ).
     */
    private String trsTyp;

    /**
     * 业务编码.
     */
    private String busCod;

    /**
     * 业务模式.
     */
    private String busMod;

    /**
     * 收方分行号.
     */
    private String crtBbk;

    /**
     * 用户名.
     */
    private String lgnNam;

    /**
     * 经办日期.
     */
    private String oprDat;

    /**
     * 收方大额行号.
     */
    private String rcvBrd;

    /**
     * 流程实例号.
     */
    private String reqNbr;

    /**
     * 请求状态(AUT:等待审批  NTE:终审完毕  BNK:银行处理中  FIN:完成  OPR:数据接收中  APW:银行人工审批  WRF:可疑 ，表示状态未知，需要人工介入处理).
     */
    private String reqSts;

    /**
     * 业务处理结果.
     */
    private String rtnFlg;

    /**
     * 失败原因.
     */
    private String rtnNar;

    /**
     * 账务套号.
     */
    private String trxSet;

    /**
     * 用户姓名.
     */
    private String usrNam;

}
