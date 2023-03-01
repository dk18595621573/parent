package com.cloud.idempotent.service;

import com.cloud.idempotent.model.IdempotentResult;

/**
 * 幂等操作接口.
 *
 * @author breggor
 */
public interface IdempotentService {

    /**
     * 幂等结束:进行业务数据保存.
     *
     * @param module    业务id
     * @param id     业务id
     * @param result 模块名
     */
    void save(String module, String id, Object result);

    /**
     * 加载已处理的业务数据.
     *
     * @param id 业务id
     * @return 返回业务数据
     */
    IdempotentResult load(String module, String id, Class clazz);

}
