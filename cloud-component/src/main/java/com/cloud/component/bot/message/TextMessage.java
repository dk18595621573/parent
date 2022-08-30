package com.cloud.component.bot.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文本消息.
 *
 * @author zenghao
 * @date 2022/8/30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TextMessage implements Message {

    private String text;
}
