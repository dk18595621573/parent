package com.cloud.component.qichacha.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author m
 */
@Data
public class CompanyInfoResponse implements Serializable {

    public String Status;

    public String Message;

    public String OrderNumber;

    public List<Result> Result;

}
