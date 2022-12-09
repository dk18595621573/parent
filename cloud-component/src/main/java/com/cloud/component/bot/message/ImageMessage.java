package com.cloud.component.bot.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 图片消息.
 *
 * @author zenghao
 * @date 2022/8/30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageMessage implements Message {

    /**
     * 图片地址
     */
    private String url;

    /**
     * 图片大小
     *  非必填
     */
    public Integer size;

    public ImageMessage(final String url) {
        this.url = url;
    }
}
