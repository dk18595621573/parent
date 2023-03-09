package com.cloud.component.cmb.consts;

/**
 * 招商银行方法名
 *
 * @author nlsm
 * @date 2023-3-6 14:01:45
 */
public interface CmbConst {

    /**
     * 签名预填充数据.
     */
    String SIGDAT_PRE = "__signature_sigdat__";

    /**
     * 采用国密算法.
     */
    String ALG_SM = "SM";

    /**
     * 招商中文名.
     */
    String ZHAO_SHANG = "招商";

    /*************** 请求参数KEY *****************/
    String UID_KEY = "UID";
    String FUNCODE_KEY = "FUNCODE";
    String ALG_KEY = "ALG";
    String DATA_KEY = "DATA";

    /*************** 签名KEY ********************/
    String SIGDAT_KEY = "sigdat";
    String SIGTIM_KEY = "sigtim";
    String SIGNATURE_KEY = "signature";

    /*************** 请求参数常量 ****************/

    String Y = "Y";

    String N = "N";
    /**
     * 人民币.
     */
    String RMB = "10";

    /**
     * 行内收方账号户名校验.
     */
    String RCV_CHK = "1";

    /**
     * PDF文件后缀名.
     */
    String PDF_SUFFIX = ".pdf";

    /**
     * PNG文件后缀名.
     */
    String PNG_SUFFIX = ".png";

    /*************** 响应参数常量 ****************/

    String CDC_SERVER = "CDCServer:";
    String ERR_MSG = "ErrMsg:";

}
