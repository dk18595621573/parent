package com.cloud.dal.handler;





import com.cloud.common.enums.ConvertEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 李岩昊
 * @date 2023/4/9
 */

public class BaseEnumTypeHandler<E extends Enum<?> & ConvertEnum> extends BaseTypeHandler<ConvertEnum> {
    private Class<E> type;
    private E[] enums;

    public BaseEnumTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
        this.enums = type.getEnumConstants();
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, ConvertEnum convertEnum,
                                    JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, (int) convertEnum.getValue());
    }

    @Override
    public ConvertEnum getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        if (resultSet.getObject(columnName) == null) {
            return null;
        }
        return this.convertToEnum(resultSet.getInt(columnName));
    }

    @Override
    public ConvertEnum getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        if (resultSet.getObject(columnIndex) == null) {
            return null;
        }
        return this.convertToEnum(resultSet.getInt(columnIndex));
    }

    @Override
    public ConvertEnum getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        return null;
    }

    private ConvertEnum convertToEnum(Integer enumValue) {
        for (Enum eachEnum : this.enums) {
            if (((ConvertEnum) eachEnum).getValue().equals(enumValue)) {
                return (ConvertEnum) eachEnum;
            }
        }
        return null;
    }
}
