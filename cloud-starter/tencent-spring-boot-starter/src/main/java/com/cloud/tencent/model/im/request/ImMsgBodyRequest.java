package com.cloud.tencent.model.im.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ImMsgBodyRequest implements Serializable {

    @JsonProperty("MsgType")
    private String msgType;

    @JsonProperty("MsgContent")
    private ImMsgContentRequest msgContent;


}
