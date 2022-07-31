package com.cloud.component.express;

import cn.hutool.core.convert.ConvertException;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.ContentType;
import cn.hutool.http.Header;
import cn.hutool.http.HttpException;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.cloud.common.exception.ServiceException;
import com.cloud.common.utils.json.JsonUtil;
import com.cloud.component.express.consts.ErrorCode;
import com.cloud.component.express.domain.JuheExpress;
import com.cloud.component.express.domain.JuheResult;
import com.cloud.component.express.domain.KuaiDi100Result;
import com.cloud.component.express.exception.ExpressException;
import com.cloud.component.properties.ExpressProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 快递信息查询.
 *
 * @author zenghao
 * @date 2022/7/19
 */
@Slf4j
public class ExpressClient {

    /**
     * 接口超时时间
     */
    private static final int TIMEOUT = 5000;

    private final ExpressProperties expressProperties;

    public ExpressClient(final ExpressProperties expressProperties) {
        this.expressProperties = expressProperties;
    }

    /**
     * 快递100 查询快递信息接口
     *
     * @param expressCode
     * @param expressNo
     * @param cellphone
     * @return
     */
    public KuaiDi100Result findExpress2(final String expressCode, final String expressNo, final String cellphone) {
        HashMap paramMap = new HashMap<>() {{
            // 快递公司
            put("com", expressCode);
            // 快递单号
            put("num", expressNo);
            // 手机号码
            put("phone", cellphone);
        }};
        String param = JsonUtil.toJson(paramMap);
        Map<String, Object> requestBody = new HashMap<>() {{
            // 授权码，请申请企业版获取
            put("customer", expressProperties.getCustomer());
            put("sign", sign(param + expressProperties.getKey() + expressProperties.getCustomer()));
            put("param", param);
        }};
        KuaiDi100Result kuaiDi100Result = null;
        try {
            String result = HttpRequest.post(expressProperties.getUrl()).header(Header.CONTENT_TYPE, ContentType.FORM_URLENCODED.getValue())
                .timeout(TIMEOUT).form(requestBody).execute().body();
            log.info("调用快递100API返回单号[{}]的快递信息数据：{}", expressNo, result);
            if (StrUtil.isBlank(result)) {
                throw new ExpressException(ErrorCode.API_ERROR);
            }
            // 转换 对象
            kuaiDi100Result = JsonUtil.parse(result, KuaiDi100Result.class);

        } catch (HttpException e) {
            log.warn("调用快递API接口异常[{}]:{}", expressNo, e.getMessage());
            throw new ExpressException(ErrorCode.API_ERROR);
        } catch (ConvertException e) {
            log.warn("调用快递API返回单号[{}]响应的快递数据转换异常:{}", expressNo, e);
            throw new ExpressException(ErrorCode.API_ERROR);
        }
        if (Objects.isNull(kuaiDi100Result) || !Objects.equals(kuaiDi100Result.getStatus(), KuaiDi100Result.SUCCESS_CODE)) {
            if (Objects.equals(KuaiDi100Result.QUERY_ERROR, kuaiDi100Result.getReturnCode())) {
                throw new ExpressException(ErrorCode.NOT_FOUND);
            } else if (Objects.equals(KuaiDi100Result.KEY_ERROR, kuaiDi100Result.getReturnCode())) {
                throw new ServiceException(kuaiDi100Result.getMessage());
            } else {
                throw new ExpressException(ErrorCode.QUERY_ERROR);
            }
        }
        //查询失败状态处理
        return kuaiDi100Result;
    }

    /**
     * 快递100加密方式统一为MD5后转大写
     *
     * @param msg
     * @return
     */
    public static String sign(String msg) {
        return DigestUtils.md5Hex(msg).toUpperCase();
    }

}
