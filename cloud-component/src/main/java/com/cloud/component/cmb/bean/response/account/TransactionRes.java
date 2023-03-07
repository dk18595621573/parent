package com.cloud.component.cmb.bean.response.account;

import com.cloud.component.cmb.bean.response.base.BaseResponse;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 账户交易信息结果
 */
@Data
@Accessors(chain = true)
public class TransactionRes extends BaseResponse {
    /**
     * 账户信息
     */
    private List<TradeAccount> ntrbptrsz1;

    /**
     * 交易信息
     */
    private List<Trade> ntqactrsz2;


    @Data
    @Accessors(chain = true)
    public class TradeAccount{
        /**
         *  未传完标记
         */
        private String cotflg;
        /**
         *  末位记账序号
         */
        private String trsseq;
        /**
         *  借方笔数
         */
        private String dbtnbr;
        /**
         *  借方金额
         */
        private String dbtamt;
        /**
         *  贷方笔数
         */
        private String crtnbr;
        /**
         *  贷方金额
         */
        private String crtamt;
    }


    @Data
    @Accessors(chain = true)
    public class Trade{
        /**
         *  交易日
         */
        private String etydat;
        /**
         *  交易时间
         */
        private String etytim;
        /**
         *  起息日
         */
        private String vltdat;
        /**
         *  交易类型
         */
        private String trscod;
        /**
         *  摘要
         */
        private String naryur;
        /**
         *  交易金额
         */
        private String trsamt;
        /**
         *  借贷标记
         */
        private String amtcdr;
        /**
         *  余额
         */
        private String trsblv;
        /**
         *  流水号
         */
        private String refnbr;
        /**
         *  流程实例号
         */
        private String reqnbr;
        /**
         *  业务名称
         */
        private String busnam;
        /**
         *  用途
         */
        private String nusage;
        /**
         *  业务参考号
         */
        private String yurref;
        /**
         *  业务摘要
         */
        private String busnar;
        /**
         *  其它摘要
         */
        private String otrnar;
        /**
         *  收/付方开户地区分行号
         */
        private String rpybbk;
        /**
         *  收/付方名称
         */
        private String rpynam;
        /**
         *  收/付方帐号
         */
        private String rpyacc;
        /**
         *  收/付方开户行行号
         */
        private String rpybbn;
        /**
         *  收/付方开户行名
         */
        private String rpybnk;
        /**
         *  收/付方开户行地址
         */
        private String rpyadr;
        /**
         *  母/子公司所在地区分行
         */
        private String gsbbbk;
        /**
         *  母/子公司帐号
         */
        private String gsbacc;
        /**
         *  母/子公司名称
         */
        private String gsbnam;
        /**
         *  信息标志
         */
        private String infflg;
        /**
         *  有否附件信息标志
         */
        private String athflg;
        /**
         *  票据号
         */
        private String chknbr;
        /**
         *  冲帐标志
         */
        private String rsvflg;
        /**
         *  扩展摘要
         */
        private String narext;
        /**
         *  交易分析码
         */
        private String trsanl;
        /**
         *  商务支付订单号
         */
        private String refsub;
        /**
         *  企业识别码
         */
        private String frmcod;
    }

}
