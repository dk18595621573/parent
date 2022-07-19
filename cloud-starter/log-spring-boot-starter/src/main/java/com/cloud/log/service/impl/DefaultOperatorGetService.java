package com.cloud.log.service.impl;

import com.cloud.log.service.IOperatorGetService;
import com.cloud.log.model.Operateor;

/**
 * TODO  .
 *
 * @author xht.
 * @createTime 2021年12月17日 15:20:00
 */
public class DefaultOperatorGetService implements IOperatorGetService {

    @Override
    public Operateor getUser() {
        return new Operateor();
    }

}
