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
     * 关联的公众号ID(使用小程序appid发送消息, 接受到的小程序无法正常打开). 企微格式要求
     */
    private String appid;//	string	是
    private String description;//	string	是	描述
    private String pagePath;//	string	是	跳转地址
    private String thumbUrl;//	string	是	封面图地址
    private String title;//	string	是	标题
    private String username;//	string	是	小程序ID
    private String iconUrl;//	string	否	icon地址

    public MiniProgramMessage(final String appid, final String description, final String pagePath, final String thumbUrl, final String title, final String username) {
        this.appid = appid;
        this.description = description;
        this.pagePath = pagePath;
        this.thumbUrl = thumbUrl;
        this.title = title;
        this.username = username;
    }
}
