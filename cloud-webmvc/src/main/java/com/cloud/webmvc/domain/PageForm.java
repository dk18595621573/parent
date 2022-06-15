package com.cloud.webmvc.domain;

import com.cloud.common.core.page.PageParam;
import com.cloud.common.utils.StringUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 分页参数.
 *
 * @author zenghao
 * @date 2022/6/1
 */
@Data
@ApiModel("分页参数")
public class PageForm implements Serializable {

    @ApiModelProperty("页码，从1开始 默认为1")
    private Integer pageNum;

    @ApiModelProperty("每页数据条数 默认为10")
    private Integer pageSize;

    @ApiModelProperty("排序字段")
    private String orderByColumn;

    @ApiModelProperty("排序的方向 desc 或者 asc")
    private String isAsc;

    @ApiModelProperty("分页参数合理化")
    private Boolean reasonable;

    public void setIsAsc(String isAsc) {
        if (StringUtils.isNotEmpty(isAsc)) {
            // 兼容前端排序类型
            if ("ascending".equals(isAsc)) {
                isAsc = "asc";
            } else if ("descending".equals(isAsc)) {
                isAsc = "desc";
            }
            this.isAsc = isAsc;
        }
    }

    public Boolean getReasonable() {
        return StringUtils.isNull(reasonable) ? Boolean.TRUE : reasonable;
    }

    public PageParam toPageParam() {
        String orderBy = StringUtils.isEmpty(orderByColumn) ? StringUtils.EMPTY : StringUtils.toUnderScoreCase(orderByColumn) + " " + isAsc;
        return new PageParam(pageNum, pageSize, orderBy, getReasonable());
    }
}
