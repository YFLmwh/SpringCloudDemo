package xyz.ddlnt.serviceuser.controller;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import xyz.ddlnt.commonutil.constant.RoleConstants;
import xyz.ddlnt.commonutil.result.Result;
import xyz.ddlnt.model.dto.UserLoginDTO;
import xyz.ddlnt.model.vo.LoginVO;
import xyz.ddlnt.serviceuser.service.UserService;

/**
 * @author ZZULI_SE 210910
 * @data 2025/7/13 8:31
 */
@Slf4j
@Tag(name = "数据库：用户接口测试")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Operation(summary = "用户登录")
    @PostMapping("login")
    public Result<String> login(@RequestBody UserLoginDTO userLoginVO) {
        String  token = userService.userLogin(userLoginVO);
        log.info("MySQL用户登录token：{}", token);
        return Result.success(token);
    }


    @Operation(summary = "获取登录信息")
    @GetMapping("/loginInfo")
    public Result<LoginVO> getLoginInfo() {
        SaSession session = StpUtil.getSession();
        LoginVO loginVO = LoginVO.builder()
                .username(session.get(RoleConstants.USERNAME).toString())
                .role(session.get(RoleConstants.ROLE).toString())
                .build();
        log.info("获取登录信息结果：{}", loginVO);
        return Result.success(loginVO);
    }

}
