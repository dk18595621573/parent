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
    public String KeyNo;

    /**
     * 企业名称
     */
    public String Name;

    /**
     * 统一社会信用代码
     */
    public String CreditCode;

    /**
     * 成立日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date StartDate;

    /**
     * 法定代表人姓名
     */
    public String OperName;

    /**
     * 状态
     */
    public String Status;

    /**
     * 注册号
     */
    public String No;

    /**
     * 注册地址
     */
    public String Address;
}
