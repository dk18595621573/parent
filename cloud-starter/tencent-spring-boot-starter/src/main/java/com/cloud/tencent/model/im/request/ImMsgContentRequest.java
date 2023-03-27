package com.cloud.tencent.model.im.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ImMsgContentRequest implements Serializable {

    @JsonProperty("Text")
    private String text;

}
