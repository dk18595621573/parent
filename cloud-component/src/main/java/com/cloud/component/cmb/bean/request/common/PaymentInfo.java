package com.cloud.component.cmb.bean.request.common;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 支付信息.
 *
 * @author nlsm
 * @date 2023-3-12 16:55:17
 */
@Data
@Accessors(chain = true)
public class PaymentInfo implements Serializable {

    private static final long serialVersionUID = -5479888836338076728L;

    /**
     * 转出帐号.
     */
    private String dbtAcc;

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
     * 用途.
     */
    private String nusAge;

    /**
     * 结算通道(G 普通; Q 快速; R 实时-超网 ; 输入空时默认为Q快速;).
     */
    private String stlChn;

    /**
     * 交易金额.
     */
    private String trsAmt;

    /**
     * 业务参考号(必须唯一。企业自定义业务参考号，相同业务的业务参考号要始终保持唯一；重发时请保证业务参考号相同。).
     */
    private String yurRef;

    //************************************** 非必输参数 *************************************/

    /**
     * 记账子单元编号.
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
     * 收方行联行号.
     */
    private String brdNbr;

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
     * 行内收方账号户名校验.
     * 1：校验
     * 空或者其他值：不校验
     * 如果为1，行内收方账号与户名不相符则支付经办失败。
     */
    private String rcvChk;

    /**
     * 直汇普通标志.
     */
    private String drpFlg;

}
