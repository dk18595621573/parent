package com.cloud.component.bot.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 小程序消息.
 *
 * @author zenghao
 * @date 2022/12/7
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MiniProgramMessage implements Message {

    /**
     * 小程序原始id
     */
    private String appid;
    /**
     * 描述
     */
    private String description;
    /**
     * 跳转地址
     */
    private String pagePath;
    /**
     * 封面图地址
     */
    private String thumbUrl;
    /**
     * 标题
     */
    private String title;
    /**
     * 小程序APPID
     */
    private String username;
    /**
     * icon地址
     *  非必填
     */
    private String iconUrl;

    public MiniProgramMessage(final String appid, final String description, final String pagePath, final String thumbUrl, final String title, final String username) {
        this.appid = appid;
        this.description = description;
        this.pagePath = pagePath;
        this.thumbUrl = thumbUrl;
        this.title = title;
        this.username = username;
    }
}
