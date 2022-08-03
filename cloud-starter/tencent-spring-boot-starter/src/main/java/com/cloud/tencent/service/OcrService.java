package com.cloud.tencent.service;

import com.cloud.tencent.exception.TencentException;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.ocr.v20181119.OcrClient;
import com.tencentcloudapi.ocr.v20181119.models.VatInvoiceOCRRequest;
import com.tencentcloudapi.ocr.v20181119.models.VatInvoiceOCRResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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

    public void vatInvoice(String imageUrl) {
        VatInvoiceOCRRequest req = new VatInvoiceOCRRequest();
        req.setImageUrl(imageUrl);
        try {
            VatInvoiceOCRResponse ocrResponse = ocrClient.VatInvoiceOCR(req);
        } catch (TencentCloudSDKException e) {
            throw new TencentException(e.getMessage(), e.getErrorCode(), e);
        }
    }
}
