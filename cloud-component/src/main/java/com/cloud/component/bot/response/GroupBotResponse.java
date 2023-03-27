package com.cloud.component.bot.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 企业级接口 获取托管账号所在微信分组的信息 响应数据.
 *
 * @author Luo
 * @date 2023-03-26 10:52
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class GroupBotResponse extends ApiResponse {

    private static final long serialVersionUID = -5711853175759649932L;

    /**
     * 小组集合.
     */
    private List<Group> groups;

    /**
     * 小组信息.
     */
    @Data
    @Accessors(chain = true)
    public static class Group implements Serializable {

        private static final long serialVersionUID = 4771376244143640255L;

        /**
         * 小组id.
         * 是否必传：是
         */
        private String id;

        /**
         * 小组名.
         * 是否必传：是
         */
        private String name;

        /**
         * 托管账号集合.
         */
        private List<Bot> bots;

    }

    /**
     * 托管账号信息.
     */
    @Data
    @Accessors(chain = true)
    public static class Bot implements Serializable {

        private static final long serialVersionUID = 4771376244143640255L;

        /**
         * 托管账号id, 详见botId.
         * 是否必传：是
         */
        private String id;

        /**
         * 托管账号wxid, 详见imBotId.
         * 是否必传：是
         */
        private String wxid;

        /**
         * 	托管账号的企微userId, 详见wxUserId.
         * 是否必传：是
         */
        private String wecomUserId;

        /**
         * 托管账号状态.
         * 是否必传：是
         */
        private Integer status;

        /**
         * 托管账号名字.
         * 是否必传：是
         */
        private String name;

        /**
         * 托管账号头像.
         * 是否必传：是
         */
        private String avatar;

    }

}

