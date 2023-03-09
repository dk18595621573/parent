package com.cloud.component.cmb.bean.response.account;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 账户信息响应参数.
 *
 * @author nlsm
 * @date 2023-3-9 11:40:59
 */
@Data
@Accessors(chain = true)
public class AccountInfoResponse implements Serializable {

    private static final long serialVersionUID = -5287919846152172812L;

    /**
     * 币种.
     */
    private String ccynbr;

    /**
     * 科目.
     */
    private String accitm;

    /**
     * 分行号.
     */
    private String bbknbr;

    /**
     * 帐号.
     */
    private String accnbr;

    /**
     * 注解 一般为户名.
     */
    private String accnam;

    /**
     * 上日余额:当intcod ='S'时，这个字段的值显示为"头寸额度（集团支付子公司余额）"是子公司的虚拟余额.
     */
    private String accblv;

    /**
     * 联机余额.
     */
    private String onlblv;

    /**
     * 冻结余额.
     */
    private String hldblv;

    /**
     * 可用余额:可用余额=联机余额-预期余额-冻结金额+朝朝宝快赎额度.
     */
    private String avlblv;

    /**
     * 透支额度.
     */
    private String lmtovr;

    /**
     * 状态:A=活动，B=冻结，C=关户.
     */
    private String stscod;

    /**
     * 利息码:S=子公司虚拟余额.
     */
    private String intcod;

    /**
     * 年利率.
     */
    private String intrat;

    /**
     * 开户日.
     */
    private String opndat;

    /**
     * 到期日.
     */
    private String mutdat;

    /**
     * 利率类型.
     */
    private String inttyp;

    /**
     * 存期.
     */
    private String dpstxt;

    /**
     * 客户关系号.
     */
    private String relnbr;

}
