package com.ebook.util.extension;

import com.ebook.util.constant.BaseEnumType;

import java.io.Serializable;

/**
 * 自定义异常包装类
 */
public class ApplicationException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -1354043731046864103L;

    private String code;

    public ApplicationException() {
    }

    public ApplicationException(BaseEnumType baseEnumType) {
        super(baseEnumType.getMsg());
        this.code = baseEnumType.getCode();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}