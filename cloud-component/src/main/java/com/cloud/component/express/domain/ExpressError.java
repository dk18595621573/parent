/**
  * Copyright 2022 bejson.com 
  */
package com.cloud.component.express.domain;

import lombok.Data;

/**
 * Auto-generated: 2022-07-31 14:35:58
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class ExpressError {

    public static final String SUCCESS_CODE = "200";
    public static final String QUERY_ERROR_CODE = "500";

    private boolean result;

    private String returnCode;

    private String message;


}