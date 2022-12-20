package com.cloud.webmvc.excel.strategy;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;

/**
 * @author 杜款
 * 导出excel列名称重命名
 */
@Slf4j
public class ColumnRenameStrategy implements CellWriteHandler {

    /** 需要修改的字段集合 */
    private final Map<String, String> renameMap;

    public ColumnRenameStrategy(Map<String, String> renameMap) {
        this.renameMap = renameMap;
    }

    @Override
    public void beforeCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row, Head head, Integer columnIndex, Integer relativeRowIndex, Boolean isHead) {
        Field field = head.getField();
        if (Objects.isNull(field) || CollUtil.isEmpty(renameMap)){
            return;
        }
        String fieldName = renameMap.get(field.getName());
        if (Objects.nonNull(fieldName)){
            log.debug("修改导出Excel标题名称【{}-->{}】", field.getName(), fieldName);
            head.setHeadNameList(CollUtil.newArrayList(fieldName));
        }
    }

}
