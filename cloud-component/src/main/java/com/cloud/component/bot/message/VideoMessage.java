package com.cloud.component.bot.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * video消息.
 *
 * @author zenghao
 * @date 2022/12/8
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoMessage implements Message {

    private String url;
}
