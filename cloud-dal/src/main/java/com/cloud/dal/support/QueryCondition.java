package com.cloud.dal.support;

import com.cloud.common.annotation.Operator;
import com.cloud.common.annotation.QueryField;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 动态查询条件.
 *
 * @author Luo
 * @date 2021-9-7 09:58
 */
@Data
@Accessors(chain = true)
public class QueryCondition {

    private Operator operator;

    private String filedName;

    private boolean queryEmpty;

    private boolean ignore;

    /**
     * 默认条件.
     *
     * @return QueryCondition
     */
    public static QueryCondition defaultCondition() {
        QueryCondition condition = new QueryCondition();
        condition.setOperator(Operator.EQ).setQueryEmpty(false).setIgnore(false);
        return condition;
    }

    /**
     * 构建条件.
     *
     * @param queryField 查询字段
     * @return QueryCondition
     */
    public static QueryCondition of(final QueryField queryField) {
        QueryCondition condition = new QueryCondition();
        condition.setOperator(queryField.operator()).setFiledName(queryField.field())
            .setQueryEmpty(queryField.queryEmpty()).setIgnore(queryField.ignore());
        return condition;
    }

}
