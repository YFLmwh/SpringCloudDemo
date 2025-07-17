package xyz.ddlnt.commonutil.exception;

import lombok.Getter;
import xyz.ddlnt.commonutil.enumeration.ResultCode;

/**
 * @author ZZULI_SE 210910
 * @data 2025/7/13 8:52
 */
@Getter
public class GlobalException extends RuntimeException{

    private final Integer code;
    private final String message;

    /**
     * 通过状态码和错误消息创建异常对象
     * @param code
     * @param message
     */
    public GlobalException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    /**
     * 使用枚举创建异常对象
     * @param resultCode
     */
    public GlobalException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }
    @Override
    public String toString() {
        return "自定义全局异常{GlobalException：" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }
}
