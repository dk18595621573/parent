package com.cloud.core.exception.file;

import com.cloud.core.exception.BaseException;

/**
 * 文件信息异常类
 *
 * @author author
 */
public class FileException extends BaseException {
    private static final long serialVersionUID = 1L;

    public FileException(String code, Object[] args) {
        super("file", code, args, null);
    }

}
