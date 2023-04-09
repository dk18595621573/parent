package com.cloud.dal.handler;


import com.cloud.common.enums.ConvertEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.EnumTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 李岩昊
 * @date 2023/4/9
 */
public class AutoEnumTypeHandler<E extends Enum<E>> extends BaseTypeHandler<E> {
    private BaseTypeHandler baseTypeHandler = null;

    public AutoEnumTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        if (ConvertEnum.class.isAssignableFrom(type)) {
            // 如果实现了 BaseEnum 则使用我们自定义的转换器
            baseTypeHandler = new BaseEnumTypeHandler(type);
        } else {
            // 默认转换器
            baseTypeHandler = new EnumTypeHandler(type);
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, E e,
                                    JdbcType jdbcType) throws SQLException {
        baseTypeHandler.setNonNullParameter(preparedStatement, i, e, jdbcType);
    }

    @Override
    public E getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return (E) baseTypeHandler.getNullableResult(resultSet, s);
    }

    @Override
    public E getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return (E) baseTypeHandler.getNullableResult(resultSet, i);
    }

    @Override
    public E getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return (E) baseTypeHandler.getNullableResult(callableStatement, i);
    }
}
