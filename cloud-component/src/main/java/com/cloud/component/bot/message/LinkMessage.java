package com.cloud.component.bot.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * url链接消息.
 *
 * @author zenghao
 * @date 2022/8/30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkMessage implements Message {

    /**
     * 链接地址
     */
    private String sourceUrl;

    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String summary;

    /**
     * 封面图片地址
     */
    private String imageUrl;
}
