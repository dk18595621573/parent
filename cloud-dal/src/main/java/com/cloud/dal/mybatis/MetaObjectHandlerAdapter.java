package com.cloud.dal.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.handlers.StrictFill;
import com.cloud.dal.model.BaseEntity;
import com.cloud.common.exception.ServiceException;
import com.cloud.common.utils.DateUtils;
import com.cloud.common.threads.RequestThread;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * mybatis-plus自定义填充.
 *
 * @author zenghao
 */
@Slf4j
public class MetaObjectHandlerAdapter implements MetaObjectHandler {

    /**
     * 新增时自动填充默认数据.
     *
     * @param metaObject metaObject
     */
    @Override
    public void insertFill(final MetaObject metaObject) {
        if (metaObject.getOriginalObject() instanceof BaseEntity) {
            Long operatorId;
            try {
                operatorId = RequestThread.getUserId();
            } catch (ServiceException e) {
                log.warn("当前新增记录自动填充时无法获取登录用户信息");
                operatorId = 0L;
            }

            Date now = DateUtils.getNowDate();

            List<StrictFill<?, ?>> list = new ArrayList<>(4);
            list.add(StrictFill.of("createBy", Long.class, operatorId));
            list.add(StrictFill.of("updateBy", Long.class, operatorId));
            list.add(StrictFill.of("createTime", Date.class, now));
            list.add(StrictFill.of("updateTime", Date.class, now));
            this.strictInsertFill(findTableInfo(metaObject), metaObject, list);
        }
    }

    /**
     * 修改时自动填充默认数据.
     *
     * @param metaObject metaObject
     */
    @Override
    public void updateFill(final MetaObject metaObject) {
        if (metaObject.getOriginalObject() instanceof BaseEntity) {
            Long operatorId;
            try {
                operatorId = RequestThread.getUserId();
            } catch (ServiceException e) {
                log.warn("当前修改记录自动填充时无法获取登录用户信息");
                operatorId = 0L;
            }
            List<StrictFill<?, ?>> list = new ArrayList<>(2);
            list.add(StrictFill.of("updateBy", Long.class, operatorId));
            list.add(StrictFill.of("updateTime", Date.class, DateUtils.getNowDate()));

            this.strictUpdateFill(findTableInfo(metaObject), metaObject, list);
        }
    }

}
