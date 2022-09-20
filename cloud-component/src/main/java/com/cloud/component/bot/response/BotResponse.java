package com.cloud.component.bot.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 机器人接口响应数据.
 *
 * @author zenghao
 * @date 2022/8/30
 */
@Data
public class BotResponse implements Serializable {

    public static final int SUCCESS_CODE = 0;

    /**
     * 响应码
     */
    private Integer code;

    /**
     * 响应提示信息
     */
    private String message;

    /**
     * 响应数据
     */
    private Object data;

    /**
     * 分页数据
     */
    private Page page;


    @Data
    public static class Page implements Serializable {

        /**
         * 当前页
         */
        private Integer current;

        /**
         * 总记录数
         */
        private Integer total;
    }
}
