package com.cloud.component.cmb;

import com.cloud.component.cmb.consts.CMBConst;
import com.cloud.component.cmb.exception.CMBException;
import com.cloud.component.cmb.request.BaseRequest;
import com.cloud.component.cmb.request.account.*;
import com.cloud.component.cmb.response.BaseResponse;
import com.cloud.component.cmb.response.account.*;
import com.cloud.component.cmb.util.CMBUtils;
import com.cloud.component.properties.CMBProperties;
import lombok.extern.slf4j.Slf4j;
import java.util.*;

/**
 *  招商银行客户端
 */
@Slf4j
public class CMBClient {
    // 配置属性
    private final CMBProperties cmbProperties;

    public CMBClient(CMBProperties cmbProperties){
        this.cmbProperties = cmbProperties;
    }


    /**
     * 银行接口公共请求方法
     * @param baseRequest  请求入参
     * @return
     * @throws Exception
     */
    public <T> BaseResponse doProcess(BaseRequest<T> baseRequest, Class respClass) {
        try {
            // 校验参数
            String requestString = CMBUtils.getRequestString(baseRequest, cmbProperties);
            log.info("招商银行调用参数：{}", requestString);

            // 发送请求
            HashMap<String, String> map = new HashMap<>();
            map.put("UID", cmbProperties.getUID());
            map.put("FUNCODE", baseRequest.getFuncode());
            map.put("DATA", requestString);
            String response = CMBUtils.doPostForm(cmbProperties.getURL(), map);
            log.info("招商银行返回结果：{}", response);

            // 封装返回参数
            return CMBUtils.getResponse(response, respClass);
        } catch (Exception e) {
            throw new CMBException( "招商银行接口失败："+ e.getMessage());
        }
    }

    /**
     * 可经办业务模式查询
     * @param businessModelReq
     * @return
     */
    public BusinessModelRes getBusinessModel(BusinessModelReq businessModelReq){
        BaseRequest<BusinessModelReq> baseRequest = new BaseRequest<>();
        baseRequest.setFuncode(CMBConst.DCLISMOD);
        baseRequest.setBody(businessModelReq);
        return (BusinessModelRes) doProcess(baseRequest, BusinessModelRes.class);
    }

    /**
     * 查询可经办的账户列表
     * @param accountReq
     * @return
     */
    public AccountRes getAccountList(AccountReq accountReq){
        BaseRequest<AccountReq> baseRequest = new BaseRequest<>();
        baseRequest.setFuncode(CMBConst.DCLISACC);
        baseRequest.setBody(accountReq);
        return (AccountRes) doProcess(baseRequest, AccountRes.class);
    }

    /**
     * 账户详细信息查询
     * @param accountInfoReq
     * @return
     */
    public AccountInfoRes getAccountInfo(AccountInfoReq accountInfoReq){
        BaseRequest<AccountInfoReq> baseRequest = new BaseRequest<>();
        baseRequest.setFuncode(CMBConst.NTQACINF);
        baseRequest.setBody(accountInfoReq);
        return (AccountInfoRes) doProcess(baseRequest, AccountInfoRes.class);
    }

    /**
     * 查询账户历史余额
     * @param historyBalanceReq
     * @return
     */
    public HistoryBalanceRes getHistoryBalance(HistoryBalanceReq historyBalanceReq){
        BaseRequest<HistoryBalanceReq> baseRequest = new BaseRequest<>();
        baseRequest.setFuncode(CMBConst.NTQABINF);
        baseRequest.setBody(historyBalanceReq);
        return (HistoryBalanceRes) doProcess(baseRequest, HistoryBalanceRes.class);
    }

    /**
     * 批量查询余额
     * @param batchBalanceReq
     * @return
     */
    public BatchBalanceRes batchBalance(BatchBalanceReq batchBalanceReq){
        BaseRequest<BatchBalanceReq> baseRequest = new BaseRequest<>();
        baseRequest.setFuncode(CMBConst.NTQADINF);
        baseRequest.setBody(batchBalanceReq);
        return (BatchBalanceRes) doProcess(baseRequest, BatchBalanceRes.class);
    }

    /**
     * 账户交易信息查询
     * @param transactionReq
     * @return
     */
    public TransactionRes getTransaction(TransactionReq transactionReq){
        BaseRequest<TransactionReq> baseRequest = new BaseRequest<>();
        baseRequest.setFuncode(CMBConst.DCTRSINF);
        baseRequest.setBody(transactionReq);
        return (TransactionRes) doProcess(baseRequest, TransactionRes.class);
    }

    /**
     * 电子回单异步查询
     * @param syncReceiptReq
     * @return
     */
    public SyncReceiptRes getSyncReceipt(SyncReceiptReq syncReceiptReq){
        BaseRequest<SyncReceiptReq> baseRequest = new BaseRequest<>();
        baseRequest.setFuncode(CMBConst.ASYCALHD);
        baseRequest.setBody(syncReceiptReq);
        return (SyncReceiptRes) doProcess(baseRequest, SyncReceiptRes.class);
    }

    /**
     * 回单异步打印结果查询
     * @param receiptUrlReq
     * @return
     */
    public ReceiptUrlRes getReceiptUrl(ReceiptUrlReq receiptUrlReq){
        BaseRequest<ReceiptUrlReq> baseRequest = new BaseRequest<>();
        baseRequest.setFuncode(CMBConst.DCTASKID);
        baseRequest.setBody(receiptUrlReq);
        return (ReceiptUrlRes) doProcess(baseRequest, ReceiptUrlRes.class);
    }


}
