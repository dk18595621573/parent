package com.cloud.core.pdf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartContent {
    /**
     * 文本内容
     */
    private String content;
    /**
     * 截图文件地址
     */
    private String file;
}