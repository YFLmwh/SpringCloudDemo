package xyz.ddlnt.commonutil.result;

import lombok.Data;
import xyz.ddlnt.commonutil.enumeration.ResultCode;

import java.io.Serializable;

/**
 * @author ZZULI_SE 210910
 * @data 2025/7/9 17:36
 */
@Data
public class Result<T>  implements Serializable {
    private Integer code;
    private String message;
    private T data;

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public static <T>Result<T> build(Integer code, String message, T data ) {
        return new Result<>(code, message, data);
    }

    public static <T>Result<T> success() {
        return build(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), null);
    }
    public static <T>Result<T> success(T data) {
        return build(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    public static <T>Result<T> fail() {
        return build(ResultCode.SERVER_ERROR.getCode(), ResultCode.SERVER_ERROR.getMessage(), null);
    }
    public static <T>Result<T> fail(T data) {
        return build(ResultCode.SERVER_ERROR.getCode(), ResultCode.SERVER_ERROR.getMessage(), data);
    }

    public static <T> Result<T> fail(Integer code, String message) {
        return build(code, message, null);
    }

    public static <T> Result<T> fail(Integer code, String message, T data) {
        return build(code, message, data);
    }

    public static <T> Result<T> fail(ResultCode resultCode) {
        return build(resultCode.getCode(), resultCode.getMessage(), null);
    }

    public static <T> Result<T> fail(ResultCode resultCode, T data) {
        return build(resultCode.getCode(), resultCode.getMessage(), data);
    }
}

