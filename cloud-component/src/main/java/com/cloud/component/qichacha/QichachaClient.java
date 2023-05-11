package com.cloud.component.qichacha;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.cloud.common.utils.json.JsonUtil;
import com.cloud.component.properties.QichachaProperties;
import com.cloud.component.qichacha.response.CompanyInfoResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * 企查查接口工具
 *
 * @author mft
 */
@Slf4j
public class QichachaClient {

    private final QichachaProperties qichachaProperties;

    public QichachaClient(QichachaProperties qichachaProperties) {
        this.qichachaProperties = qichachaProperties;
    }

    /**
     * 获取企业注册信息
     *
     * @param keyword 关键字（企业名称、社会信用代码）
     * @return 企业注册信息
     */
    public CompanyInfoResponse getCompanyInfo(String keyword) {
        String reqInterNme = "https://api.qichacha.com/FuzzySearch/GetList";
        String paramStr = "keyword=" + keyword;
        // 获取Auth Code
        String timeSpan = String.valueOf(System.currentTimeMillis() / 1000);
        String[] autherHeader = new String[]{DigestUtils.md5Hex(qichachaProperties.getAppkey().concat(timeSpan).concat(qichachaProperties.getSecretKey())).toUpperCase(), timeSpan};
        final String reqUri = reqInterNme.concat("?key=").concat(qichachaProperties.getAppkey()).concat("&").concat(paramStr);
        String tokenJson = HttpUtil.createGet(reqUri).header("Token", autherHeader[0]).header("Timespan", autherHeader[1]).execute().body();
        log.info("企查查返回参数【{}】", tokenJson);
        if (StrUtil.isBlank(tokenJson)) {
            return null;
        }
        return JsonUtil.parse(tokenJson, CompanyInfoResponse.class);
    }

}
