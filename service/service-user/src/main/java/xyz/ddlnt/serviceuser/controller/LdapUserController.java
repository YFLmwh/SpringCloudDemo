package xyz.ddlnt.serviceuser.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.ddlnt.commonutil.result.Result;
import xyz.ddlnt.model.dto.UserLoginDTO;
import xyz.ddlnt.model.vo.LoginVO;
import xyz.ddlnt.serviceuser.service.LdapService;

/**
 * @author ZZULI_SE 210910
 * @data 2025/7/13 9:00
 */
@Slf4j
@Tag(name = "Ldap：用户接口测试")
@RestController
@RequestMapping("/ldap_user")
public class LdapUserController {
    @Resource
    private LdapService ldapService;

    @Operation(summary = "用户登录")
    @PostMapping("login")
    public Result<String> login(@RequestBody UserLoginDTO userLoginVO) {
        String  token = ldapService.userLogin(userLoginVO);
        log.info("Ldap用户登录token：{}", token);
        return Result.success(token);
    }
}
