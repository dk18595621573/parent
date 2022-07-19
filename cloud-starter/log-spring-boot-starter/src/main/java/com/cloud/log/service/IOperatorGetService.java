package com.cloud.log.service;


import com.cloud.log.model.Operateor;

public interface IOperatorGetService {

    /**
     * 可以在里面外部的获取当前登陆的用户，比如 UserContext.getCurrentUser()
     *
     * @return 转换成Operator返回
     */
    Operateor getUser();
}