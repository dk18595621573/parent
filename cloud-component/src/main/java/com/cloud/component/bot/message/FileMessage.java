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

    public String name;//	string	是	文件名
    public String url;//	string	是	文件地址
    public Integer size;//	number	否	文件大小

    public FileMessage(final String name, final String url) {
        this.name = name;
        this.url = url;
    }
}
