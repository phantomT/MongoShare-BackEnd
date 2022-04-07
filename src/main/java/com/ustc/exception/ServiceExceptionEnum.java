package com.ustc.exception;

/**
 * @author 田宝宁
 * @date 2022/03/07
 */
// TODO 检查是否有新的异常未添加
public enum ServiceExceptionEnum {
    /**
     * 第1位, 异常类型:
     *      1 - 业务级别异常
     *      2 - 系统级别异常
     * 第2-4位, 异常模块:
     *      001 - 上传模块
     *      002 - 下载模块
     *      003 - 用户模块
     *      004 - 工具类
     *      005 - 通用模块
     * 第5-7位, 错误码:
     */
    SUCCESS(0, "成功"),
    SYSTEM_ERROR(2001000, "服务端发生异常"),
    // 上传模块
    CHUNK_NOT_NULL(1001000,"切块不能为空"),
    UPLOAD_PARAM_ERROR(1001001, "上传参数不正确"),
    FILE_NOT_COMPLETE(1001002,"上传文件不完整"),
    FOLDER_CREATE_FAIL(1001002, "服务器创建文件夹失败"),
    // 下载模块
    // 用户模块
    PASSWORD_WRONG(1003000,"密码错误"),
    // 工具类
    VALIDATE_ERROR(2004000, "参数校验异常"),
    //通用模块
    SAME_NAME_EXIST(1005000, "存在同名同类型记录"),
    MOVE_PARAM_ERROR(1005001, "移动请求参数不正确"),
    RENAME_PARAM_ERROR(1005002, "移动请求参数不正确"),
    FILE_NOT_EXIST(1005003,"后台不存在相关文件记录")
    ;

    /**
     * 错误码
     */
    private int code;
    /**
     * 错误提示信息
     */
    private String message;

    ServiceExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public ServiceExceptionEnum setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ServiceExceptionEnum setMessage(String message) {
        this.message = message;
        return this;
    }
}
