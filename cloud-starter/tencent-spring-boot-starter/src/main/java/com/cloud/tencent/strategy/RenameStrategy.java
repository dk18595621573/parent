package com.cloud.tencent.strategy;

import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * 重命名规则.
 *
 * @author zenghao
 */
@Getter
public final class RenameStrategy {

    /**
     * 重命名文件名方法.
     */
    private final Rename fileName;

    /**
     * 重命名路径方法.
     */
    private final Rename filePath;

    private RenameStrategy(final Rename fileName, final Rename filePath) {
        this.fileName = fileName;
        this.filePath = filePath;
    }

    /**
     * 使用UUID作为文件名、日期作为文件路径.
     * @return 默认策略
     */
    public static RenameStrategy defaultStrategy() {
        return of((f) -> UUID.randomUUID().toString());
    }

    /**
     * 使用日期作为文件路径.
     * @param fileName 新文件名
     * @return 重命名策略
     */
    public static RenameStrategy of(final String fileName) {
        return of(f -> fileName, (p) -> LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
    }

    /**
     * 使用日期作为文件路径.
     * @param fileName 新文件名重命名方法
     * @return 重命名策略
     */
    public static RenameStrategy of(final Rename fileName) {
        return of(fileName, (p) -> LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
    }

    /**
     * 固定重名策略.
     * @param fileName 新文件名
     * @param filePath 文件保存路径
     * @return 重命名策略
     */
    public static RenameStrategy of(final String fileName, final String filePath) {
        return of(f -> fileName, (p) -> filePath);
    }

    /**
     * 自定义重命名策略.
     * @param fileName 文件名重命名方法
     * @param filePath 文件名重命名方法
     * @return 重命名策略
     */
    public static RenameStrategy of(final Rename fileName, final Rename filePath) {
        return new RenameStrategy(fileName, filePath);
    }

    /**
     * 自定义重命名.
     */
    @FunctionalInterface
    public interface Rename {

        /**
         * 重命名.
         * @param rawName 源名称
         * @return 重命名后的名称
         */
        String rename(String rawName);
    }
}
