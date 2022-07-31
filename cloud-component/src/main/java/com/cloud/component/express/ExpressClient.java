package com.cloud.component.express;

import cn.hutool.core.convert.ConvertException;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.ContentType;
import cn.hutool.http.Header;
import cn.hutool.http.HttpException;
import cn.hutool.http.HttpRequest;
import com.cloud.common.exception.ServiceException;
import com.cloud.common.utils.json.JsonUtil;
import com.cloud.component.express.consts.ErrorCode;
import com.cloud.component.express.domain.ExpressError;
import com.cloud.component.express.domain.ExpressResult;
import com.cloud.component.express.exception.ExpressException;
import com.cloud.component.properties.ExpressProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

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
    public ExpressResult findExpress(final String expressCode, final String expressNo, final String cellphone) {
        HashMap paramMap = new HashMap<>() {{
            // 快递公司
            put("com", expressCode);
            // 快递单号
            put("num", expressNo);
            // 手机号码
            put("phone", cellphone);
            //
            put("order", "asc");
        }};
        String param = JsonUtil.toJson(paramMap);
        Map<String, Object> requestBody = new HashMap<>() {{
            // 授权码，请申请企业版获取
            put("customer", expressProperties.getCustomer());
            put("sign", sign(param + expressProperties.getKey() + expressProperties.getCustomer()));
            put("param", param);
        }};
        ExpressResult expressResult = null;
        try {
            String result = HttpRequest.post(expressProperties.getUrl()).header(Header.CONTENT_TYPE, ContentType.FORM_URLENCODED.getValue())
                .timeout(TIMEOUT).form(requestBody).execute().body();
            log.info("调用快递100API返回单号[{}]的快递信息数据：{}", expressNo, result);
            if (StrUtil.isBlank(result)) {
                throw new ExpressException(ErrorCode.API_ERROR);
            }
            // 转换 对象
            ExpressError expressError = JsonUtil.parse(result, ExpressError.class);
            if (StrUtil.isNotBlank(expressError.getReturnCode())) {
                if (Objects.equals(ExpressError.QUERY_ERROR_CODE, expressError.getReturnCode())) {
                    throw new ExpressException(ErrorCode.NOT_FOUND);
                }
                throw new ServiceException(expressError.getMessage());
            }
            expressResult = JsonUtil.parse(result, ExpressResult.class);

        } catch (HttpException e) {
            log.warn("调用快递API接口异常[{}]:{}", expressNo, e.getMessage());
            throw new ExpressException(ErrorCode.API_ERROR);
        } catch (ConvertException e) {
            log.warn("调用快递API返回单号[{}]响应的快递数据转换异常:{}", expressNo, e);
            throw new ExpressException(ErrorCode.API_ERROR);
        }
        //查询失败状态处理
        return expressResult;
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

    public static void main(String[] args) {
        ExpressProperties properties = new ExpressProperties();
        properties.setUrl("https://poll.kuaidi100.com/poll/query.do");
        properties.setKey("wBfjOuYf6894");
        properties.setCustomer("C7AF17641A07E84D1C93C43645515C69");

        ExpressClient expressClient = new ExpressClient(properties);
        ExpressResult express2 = expressClient.findExpress("shunfeng", "SF1370500969031", "15325014087");
        System.out.println(express2);
    }
}
