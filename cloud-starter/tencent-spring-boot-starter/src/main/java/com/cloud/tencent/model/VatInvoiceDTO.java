package com.cloud.tencent.model;

import com.tencentcloudapi.ocr.v20181119.models.VatInvoiceItem;
import lombok.Data;
import java.util.List;

/**
 * @author dk185
 * 增值税发票数据传输对象
 */
@Data
public class VatInvoiceDTO {
    /** 发票数据 */
    private VatInvoice vatInvoice;
    /** 发票项目详情数据 */
    private List<VatInvoiceItem> vatInvoiceDetailList;
}
