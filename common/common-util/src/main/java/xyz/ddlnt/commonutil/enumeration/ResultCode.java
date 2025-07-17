package xyz.ddlnt.commonutil.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ZZULI_SE 210910
 * @data 2025/7/9 17:37
 */
@Getter
@AllArgsConstructor
public enum ResultCode {

    INSUFFICIENT_USER_PRIVILEGES(50, "用户权限不足"),
    SUCCESS(200, "成功"),
    CLIENT_SIDE_ERROR(400, "客户端错误"),
    USER_NOT_LOGIN(401, "用户未登录"),
    USER_NOT_AUTHORIZED(403, "用户未授权"),
    RESOURCE_NOT_FOUND(404, "资源不存在"),
    SERVER_ERROR(500, "服务端错误"),

    USER_EXIST(10001, "用户已存在"),
    USER_NOT_EXIST(10002, "用户不存在"),
    USER_PASSWORD_ERROR(10003, "用户密码错误"),
    PARAM_ERROR(10006, "参数错误"),
    THE_REMOTE_PROCEDURE_CALL_FAILED(10010, "远程过程调用失败"),
    CONTENT_IS_EMPTY(10015, "内容为空");

    private final Integer code;
    private final String message;

}
