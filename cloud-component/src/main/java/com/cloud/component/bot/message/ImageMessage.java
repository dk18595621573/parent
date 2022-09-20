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

    private String url;
}
