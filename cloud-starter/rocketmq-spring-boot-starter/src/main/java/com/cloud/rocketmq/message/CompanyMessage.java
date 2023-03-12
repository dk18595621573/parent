package com.cloud.rocketmq.message;

import com.cloud.rocketmq.base.BaseEvent;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class CompanyMessage extends BaseEvent {

    /**
     * 企业id
     */
    private Long companyId;

    /**
     * 消息推送时间
     */
    private Date pushDate;


}
