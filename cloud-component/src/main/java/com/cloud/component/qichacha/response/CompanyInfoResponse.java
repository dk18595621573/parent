package com.cloud.component.qichacha.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author m
 */
@Data
public class CompanyInfoResponse implements Serializable {

    public String status;
    public String message;
    public String orderNumber;
    public List<Result> result;

}
