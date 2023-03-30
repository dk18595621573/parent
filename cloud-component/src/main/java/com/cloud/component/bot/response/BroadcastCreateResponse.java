package com.cloud.component.bot.response;

import com.cloud.component.bot.request.BroadcastCreateRequest;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 群发接口 创建群发 响应数据.
 *
 * @author Luo
 * @date 2023-03-26 10:52
 */
@Data
@Accessors(chain = true)
public class BroadcastCreateResponse implements Serializable {

    private static final long serialVersionUID = -5711853175759649932L;

    /**
     * 群发id.
     * 是否必传：否
     */
    private String id;

    /**
     * 群发对象人数.
     * 是否必传：是
     */
    private Integer memberNumber;

    /**
     * （毫秒）定时任务时存在.
     * 是否必传：否
     */
    private Long scheduledTimestamp;

    /**
     * 该群发是否创建成功.
     * 是否必传：否
     */
    private Boolean isCreated;

    /**
     * 发送消息数.
     * 是否必传：否
     */
    private Integer messageNumber;

    /**
     * 未创建成功的成员,参数参照请求的members字段.
     * 是否必传：否
     */
    private List<BroadcastCreateRequest.Member> failedMembers;

    /**
     * 未创建成功的成员数量.
     * 是否必传：否
     */
    private Integer failedMemberCount;

}

