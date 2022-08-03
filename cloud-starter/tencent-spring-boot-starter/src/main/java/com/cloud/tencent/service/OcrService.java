package com.cloud.tencent.service;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.cloud.tencent.exception.TencentException;
import com.cloud.tencent.model.*;
import com.cloud.tencent.model.VatInvoice;
import com.google.gson.JsonObject;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.ocr.v20181119.OcrClient;
import com.tencentcloudapi.ocr.v20181119.models.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONException;

import java.util.Arrays;
import java.util.List;

/**
 * OCR服务.
 *
 * @author zenghao
 * @date 2022/8/2
 */
@Slf4j
@AllArgsConstructor
public class OcrService {
    private final OcrClient ocrClient;

    /**
     * 增值税发票识别
     * @param imageUrl cos文件地址
     * @return 识别成功数据
     */
    public VatInvoiceDTO vatInvoice(String imageUrl) {
        VatInvoiceOCRRequest req = new VatInvoiceOCRRequest();
        VatInvoiceDTO invoiceDTO = new VatInvoiceDTO();
        req.setImageUrl(imageUrl);
        req.setIsPdf(true);
        try {
            VatInvoiceOCRResponse ocrResponse = ocrClient.VatInvoiceOCR(req);
            //发票信息
            TextVatInvoice[] invoiceInfos = ocrResponse.getVatInvoiceInfos();
            JSONObject jsonObject = new JSONObject();
            for (TextVatInvoice info : invoiceInfos) {
                String name = info.getName();
                jsonObject.set(VatInvoiceMap.getNameField(name), VatInvoiceMap.cleanSymbol(name, info.getValue()));
            }
            invoiceDTO.setVatInvoice(JSONUtil.toBean(jsonObject, VatInvoice.class));
            //项目明细数据
            invoiceDTO.setVatInvoiceDetailList(Arrays.asList(ocrResponse.getItems()));
        } catch (TencentCloudSDKException e) {
            throw new TencentException(e.getMessage(), e.getErrorCode(), e);
        }
        return invoiceDTO;
    }

    /**
     * 增值税发票的准确性核验
     * @param verifyParam 入参
     */
    public InvoiceVerifyDTO invoiceVerifyNew(VatInvoiceVerifyParam verifyParam){
        //发送参数
        VatInvoiceVerifyNewRequest req = new VatInvoiceVerifyNewRequest();
        req.setAmount(verifyParam.getAmount());
        req.setInvoiceNo(verifyParam.getInvoiceNo());
        req.setInvoiceCode(verifyParam.getInvoiceCode());
        req.setInvoiceDate(verifyParam.getInvoiceDate());
        req.setInvoiceKind(verifyParam.getInvoiceKind());
        req.setCheckCode(verifyParam.getCheckCode());
        try {
            ocrClient.VatInvoiceVerifyNew(req);
            return InvoiceVerifyDTO.success();
        } catch (TencentCloudSDKException e) {
            return InvoiceVerifyDTO.error(e.getErrorCode(), e.getMessage());
        }
    }

}
