package com.ustc.utils;

import java.io.Serializable;

/**
 * 全局统一返回结果
 *
 * @author 田宝宁
 * @date 2022/03/07
 */
public class CommonResult<T> implements Serializable {
    /**
     * 成功
     */
    public static final Integer CODE_SUCCESS = 0;
    /**
     * 状态码, 0为成功, 非0为失败
     */
    private Integer code;
    /**
     * 错误提示信息
     */
    private String message;
    /**
     * 返回数据
     */
    private T data;

    public CommonResult() {
    }

    public CommonResult(T data) {
        this.code = CODE_SUCCESS;
        this.data = data;
    }

    public CommonResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public CommonResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public CommonResult<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public CommonResult<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public CommonResult<T> setData(T data) {
        this.data = data;
        return this;
    }
}
