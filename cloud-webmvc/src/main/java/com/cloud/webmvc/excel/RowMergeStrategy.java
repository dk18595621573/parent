package com.cloud.webmvc.excel;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.merge.AbstractMergeStrategy;
import com.cloud.webmvc.excel.model.RowRange;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.List;
import java.util.Map;

/**
 * 行合并策略.
 *
 * @author zenghao
 * @date 2021/12/26
 */
public class RowMergeStrategy extends AbstractMergeStrategy {

    /**
     * 合并策略.
     */
    private final Map<String, List<RowRange>> strategyMap;

    public RowMergeStrategy(final Map<String, List<RowRange>> strategyMap) {
        this.strategyMap = strategyMap;
    }

    @Override
    protected void merge(final Sheet sheet, final Cell cell, final Head head, final Integer relativeRowIndex) {
        if (cell.getRowIndex() == 1) {
            //获取合并策略
            List<RowRange> list = strategyMap.get(head.getFieldName());
            if (CollectionUtils.isNotEmpty(list)) {
                list.forEach(rowRange -> {
                    //添加一个合并请求
                    sheet.addMergedRegionUnsafe(new CellRangeAddress(rowRange.getStart(),
                        rowRange.getEnd(), cell.getColumnIndex(), cell.getColumnIndex()));
                });
            }
        }
    }
}
