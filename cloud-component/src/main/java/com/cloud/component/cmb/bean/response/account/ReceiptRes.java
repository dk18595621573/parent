package com.cloud.component.cmb.bean.response.account;

import com.cloud.component.cmb.bean.response.base.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 单笔回单查询.
 *
 * @author nlsm
 * @date 2023-3-7 14:29:21
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class ReceiptRes extends BaseResponse {
    private static final long serialVersionUID = -6437196926061362186L;

    /*************** 打印模式为PDF时返回如下内容 ********************/

    /**
     * 验证码.
     */
    private String checod;

    /**
     * 单笔回单返回的数据流.
     * 返回Base64后的数据，Base64解码后写入文件可得到PDF
     */
    private String fildat;

    /**
     * 回单实例号.
     */
    private String istnbr;

    /*************** 打印模式为OFD时返回如下内容 ********************/

    /**
     * 响应码.
     */
    private String rtncod;

    /**
     * 响应信息.
     */
    private String rtnmsg;

    /**
     * 打印任务编号.
     * 返回的任务ID，请根据该ID查询文件。
     */
    private String rtndat;

}
