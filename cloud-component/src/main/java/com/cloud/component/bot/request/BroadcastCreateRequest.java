package com.cloud.component.bot.request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 群发接口 创建群发 请求数据.
 *
 * @author Luo
 * @date 2023-03-26 10:52
 */
@Data
@Accessors(chain = true)
public class BroadcastCreateRequest implements Serializable {

    private static final long serialVersionUID = -5711853175759649932L;

    /**
     * 群发id，分页后续必传.
     * 是否必传：否
     */
    private String id;

    /**
     * 5私聊 6群聊.
     * 是否必传：是
     */
    private Integer type;

    /**
     * 当分页时为true，最后一个是false或者不传.
     * 是否必传：否
     */
    private Boolean hasMore;

    /**
     * （毫秒）当定时任务时必传，否则不传.
     * 是否必传：否
     */
    private Long scheduledTimestamp;

    /**
     * 发送对象.
     * 是否必传：是
     */
    private List<Member> members;

    /**
     * 当hseMore不传或者为false时，必须存在一组messages,多存在多组时,后面会覆盖前面的messages.
     * 是否必传：否
     */
    private List<Message> messages;

    /**
     * 消息.
     */
    @Data
    @Accessors(chain = true)
    public static class Message implements Serializable {

        private static final long serialVersionUID = -5711853175759649932L;

        /**
         * 参考发送消息的messageType.
         * 是否必传：是
         */
        private Integer type;

        /**
         * 参考发送消息的payload.
         * 是否必传：是
         */
        private com.cloud.component.bot.message.Message payload;

        /**
         * 表示该条消息是群公告（需保证一次群发只允许有一条群公告消息），只在type是群聊且机器人为群主时生效.
         * 是否必传：否
         */
        private Boolean isAnnouncement;

        /**
         * 表示该条消息是@所有人（需保证一次群发只允许有一条群公告消息），只在type是群聊且机器人为群主时生效.
         * 是否必传：否
         */
        private Boolean isAtAll;

    }

    /**
     * 发送对象.
     */
    @Data
    @Accessors(chain = true)
    public static class Member implements Serializable {

        private static final long serialVersionUID = -5711853175759649932L;

        /**
         * 机器人的userId, 详见wxUserId.
         * 是否必传：是
         */
        private String botUserId;

        /**
         * 该机器人发送对象的wxid列表,多组时会整合去重. 当wxids存在时, unionId无效, 详见contactWxid.
         * 是否必传：否(和unionIds二选一)
         */
        private List<String> wxids;

        /**
         * 该机器人发送对象的unionId列表,多组时会整合去重. 当wxids存在时, unionId无效, 详见unionId.
         * 是否必传：否(和wxids二选一)
         */
        private List<String> unionIds;

    }

}

