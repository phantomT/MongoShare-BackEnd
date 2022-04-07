package com.ustc.utils;

import org.springframework.util.Assert;

/**
 * 全局统一结果处理工具类
 * @author 田宝宁
 * @date 2022/03/07
 */
public class CommonResultUtils {

    /**
     * 将传入的result对象, 转换为另一个泛型对象的结果
     * @param result 结果
     * @param <T> 返回的泛型
     * @return 返回新的CommonResult
     */
    public static <T> CommonResult<T> error(CommonResult<T> result) {
        return error(result.getCode(), result.getMessage());
    }

    /**
     * 返回全局统一的错误结果
     * @param code 错误码
     * @param message 错误提示信息
     * @param <T> 实体数据类型
     * @return 封装好的统一的错误结果
     */
    public static <T> CommonResult<T> error(Integer code, String message) {
        Assert.isTrue(!CommonResult.CODE_SUCCESS.equals(code),"错误状态码必须非0");
        return new CommonResult<>(code, message);
    }

    /**
     * 返回全局统一的正确结果
     * @param data 返回的数据
     * @param <T> data的类型
     * @return 封装好的统一的正确结果
     */
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<>(data);
    }
}
