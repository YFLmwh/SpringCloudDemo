package xyz.ddlnt.commonutil.handle;



import cn.dev33.satoken.exception.NotRoleException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.ddlnt.commonutil.enumeration.ResultCode;
import xyz.ddlnt.commonutil.exception.GlobalException;
import xyz.ddlnt.commonutil.result.Result;
/**
 * 异常处理类
 *
 * @author ZZULI_SE 210910
 * @data 2025/7/13 8:54
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 自定义异常处理方法
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(GlobalException.class)
    public Result error(GlobalException e){
        log.error("自定义异常处理: {}", e.getMessage(), e);
        log.error("原因: ", e.getCause());
        return Result.fail(e.getCode(), e.getMessage());
    }

    /**
     * 无角色权限处理方法
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(NotRoleException.class)
    public Result handlerException(NotRoleException e) {
        e.printStackTrace();
        log.error("异常: {}", e.getMessage(), e);
        log.error("原因: ", e.getCause());
        return Result.fail(ResultCode.INSUFFICIENT_USER_PRIVILEGES);
    }
}
