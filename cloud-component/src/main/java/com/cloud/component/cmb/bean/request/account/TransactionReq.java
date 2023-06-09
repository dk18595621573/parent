package com.cloud.component.cmb.bean.request.account;

import com.cloud.component.cmb.bean.request.BaseCmbRequest;
import com.cloud.component.cmb.bean.response.account.TransactionRes;
import com.cloud.component.cmb.consts.FunCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 账户交易信息参数.
 *
 * @author nlsm
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class TransactionReq extends BaseCmbRequest<TransactionRes> implements Serializable {
    private static final long serialVersionUID = 2874825410699282844L;

    /**
     * 分行号.
     */
    private String bbknbr;

    /**
     * 账号.
     */
    private String accnbr;

    /**
     * 交易日(YYYYMMDD).
     */
    private String trsdat;

    /**
     * 起始记账序号:断点续传使用，以上一次查询返回的ntrbptrsz1接口中的记账序号，+1后填入本次查询，首次查询填0或者为空，注意交易日切换后，记账序号又须从0起查。该接口支持增量查询，客户需要记录上次返回报文的末位记账序号，继续查询该序号以后的交易，不用每次都从0开始查.
     */
    private String trsseq;

    @Override
    public String getFuncode() {
        return FunCodeEnum.AccountQuery.DCTRSINF.name();
    }

}
