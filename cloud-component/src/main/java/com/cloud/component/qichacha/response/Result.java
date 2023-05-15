package com.cloud.component.qichacha.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author m
 */
@Data
public class Result implements Serializable {

    /**
     * KeyNo
     */
    public String keyNo;

    /**
     * 企业名称
     */
    public String name;

    /**
     * 统一社会信用代码
     */
    public String creditCode;

    /**
     * 成立日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date startDate;

    /**
     * 法定代表人姓名
     */
    public String operName;

    /**
     * 状态
     */
    public String status;

    /**
     * 注册号
     */
    public String no;

    /**
     * 注册地址
     */
    public String address;
}
