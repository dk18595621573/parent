package com.cloud.component.ecss.consts;

/**
 * ECSS平台常量.
 *
 * @author nlsm
 * @date 2023-3-6 14:01:45
 */
public interface ECSSConst {

    /*************** 系统级请求参数KEY *****************/

    String METHOD_KEY = "method";
    String TIMESTAMP_KEY = "timestamp";
    String APP_KEY_KEY = "app_key";
    String APP_SECRET_KEY = "app_secret";
    String SIGN_KEY = "sign";
    String SIGN_METHOD_KEY = "sign_method";
    String SESSION_KEY = "session";
    String SHOP_ID_KEY = "shopId";
    String XML_KEY = "xml";

    /*************** 请求参数常量 ****************/

    // MD5加密方式
    String MD5 = "md5";

    /*************** 响应参数常量 ****************/

    String RESULT_KEY = "result";
    String ERR_MSG = "ErrMsg:";

}
