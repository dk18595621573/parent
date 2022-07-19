package com.cloud.component.express;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.cloud.component.express.consts.ErrorCode;
import com.cloud.component.express.domain.JuheExpress;
import com.cloud.component.express.domain.JuheResult;
import com.cloud.component.express.exception.ExpressException;
import com.cloud.component.properties.ExpressProperties;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

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

    public JuheExpress findExpress(final String expressCode, final String expressNo, final String cellphone) {
        //缓存中为空，请求快递接口查询物流信息
        Map<String, Object> paramMap = new HashMap<>(8);
        paramMap.put("com", expressCode);
        paramMap.put("no", expressNo);
        paramMap.put("receiverPhone", cellphone.substring(cellphone.length() - 4));
        paramMap.put("key", expressProperties.getKey());
        paramMap.put("dtype", "json");
        String result = HttpUtil.get(expressProperties.getUrl(), paramMap, TIMEOUT);
        log.info("调用API返回单号[{}]的快递信息数据：{}", expressNo, result);
        if (StrUtil.isBlank(result)) {
            throw new ExpressException(ErrorCode.API_ERROR);
        }
        JuheResult<JuheExpress> juheResult = null;
        try {
            juheResult = JSONUtil.toBean(result, new TypeReference<>() {
                @Override
                public Type getType() {
                    return super.getType();
                }
            }, false);
        } catch (Exception e) {
            log.warn("调用API返回单号[{}]响应的快递数据转换异常:{}", expressNo, e);
            throw new ExpressException(ErrorCode.API_ERROR);
        }
        //查询失败状态处理
        if (juheResult.getErrorCode() != JuheResult.SUCCESS_CODE) {
            log.warn("单号[{}]的快递信息异常，异常码为:{}", expressNo, juheResult.getErrorCode());
            throw new ExpressException(ErrorCode.fromCode(juheResult.getErrorCode()));
        }
        return juheResult.getResult();
    }
}
