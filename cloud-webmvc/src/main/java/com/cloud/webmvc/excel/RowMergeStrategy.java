package com.cloud.webmvc.excel;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.merge.AbstractMergeStrategy;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * 自定义行合并策略.
 *
 * @author zenghao
 * @date 2022/7/29
 */
@Slf4j
public class RowMergeStrategy extends AbstractMergeStrategy {

    private final List<String> mergeFields;

    private final boolean mergeWithFirstField;

    public RowMergeStrategy(final List<String> mergeFields, final boolean mergeWithFirstField) {
        this.mergeFields = mergeFields;
        this.mergeWithFirstField = mergeWithFirstField;
    }

    public RowMergeStrategy(final List<String> mergeFields) {
        this.mergeFields = mergeFields;
        this.mergeWithFirstField = false;
    }

    @Override
    protected void merge(final Sheet sheet, final Cell cell, final Head head, final Integer relativeRowIndex) {
        if (mergeFields.contains(head.getFieldName())) {
            if (mergeWithFirstField) {
                Row row = sheet.getRow(cell.getRowIndex());
                Cell basicCell = row.getCell(0);
                mergeWithPrevRow(sheet, basicCell, cell.getRowIndex(), cell.getColumnIndex());
            } else {
                mergeWithPrevRow(sheet, cell, cell.getRowIndex(), cell.getColumnIndex());
            }
        }
    }

    /**
     * 合并行
     *
     * @param sheet       当前sheet
     * @param cell        合并基准数据（可以指定根据某列数据合并，也可以根据当前单元格和上一个单元格数据自动合并）
     * @param curRowIndex 当前单元格行索引
     * @param curColIndex 当前单元格列索引
     */
    private void mergeWithPrevRow(Sheet sheet, Cell cell, int curRowIndex, int curColIndex) {
        //获取当前行的当前列的数据和上一行的当前列列数据，通过上一行数据是否相同进行合并
        Object curData = cell.getCellType() == CellType.STRING ? cell.getStringCellValue() : cell.getNumericCellValue();
        int preRowIndex = curRowIndex - 1;
        Row preRow = cell.getSheet().getRow(preRowIndex);
        Cell preCell = preRow.getCell(cell.getColumnIndex());
        Object preData = preCell.getCellType() == CellType.STRING ? preCell.getStringCellValue() : preCell.getNumericCellValue();
        // 比较当前行的第一列的单元格与上一行是否相同，相同合并当前单元格与上一行
        if (Objects.equals(curData, preData)) {
            List<CellRangeAddress> mergeRegions = sheet.getMergedRegions();
            Optional<CellRangeAddress> optional = mergeRegions.stream()
                .filter(c -> c.isInRange(preRowIndex, curColIndex)).findFirst();

            CellRangeAddress address;
            if (optional.isPresent()) {
                address = optional.get();
                sheet.removeMergedRegion(mergeRegions.indexOf(address));
                address.setLastRow(curRowIndex);
            } else {
                address = new CellRangeAddress(preRowIndex, curRowIndex, curColIndex, curColIndex);
            }
            sheet.addMergedRegion(address);
        }
    }
}
