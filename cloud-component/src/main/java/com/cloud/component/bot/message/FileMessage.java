package com.cloud.component.bot.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文件消息.
 *
 * @author zenghao
 * @date 2022/12/8
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileMessage implements Message {

    /**
     * 文件名
     */
    public String name;
    /**
     * 文件地址
     */
    public String url;
    /**
     * 文件大小
     *  非必填
     */
    public Integer size;

    public FileMessage(final String name, final String url) {
        this.name = name;
        this.url = url;
    }
}
