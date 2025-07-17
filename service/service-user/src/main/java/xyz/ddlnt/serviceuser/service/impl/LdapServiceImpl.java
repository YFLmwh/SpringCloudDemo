package xyz.ddlnt.serviceuser.service.impl;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import jakarta.annotation.Resource;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.stereotype.Service;
import xyz.ddlnt.commonutil.constant.RoleConstants;
import xyz.ddlnt.commonutil.enumeration.ResultCode;
import xyz.ddlnt.commonutil.exception.GlobalException;
import xyz.ddlnt.model.dto.UserLoginDTO;
import xyz.ddlnt.model.entity.User;
import xyz.ddlnt.model.vo.LoginVO;
import xyz.ddlnt.serviceuser.service.LdapService;

/**
 * @author ZZULI_SE 210910
 * @data 2025/7/9 22:04
 */
@Service
public class LdapServiceImpl implements LdapService {
    @Resource
    private LdapTemplate ldapTemplate;


    @Override
    public String  userLogin(UserLoginDTO userLoginVO) {
        // 校验参数
        if (userLoginVO.getUsername() == null || userLoginVO.getUsername().isEmpty() ||
                userLoginVO.getPassword() == null || userLoginVO.getPassword().isEmpty()) {
            throw new GlobalException(ResultCode.PARAM_ERROR);
        }
        // 获取用户信息
        LdapQuery query = LdapQueryBuilder.query()
                .base("ou=users")
                .where("uid").is(userLoginVO.getUsername());
        User user = ldapTemplate.search(query, (AttributesMapper<User>) attrs -> User.builder()
                .username(attrs.get("uid").get().toString())
                .role(attrs.get("description").get().toString())
                .build()).stream().findFirst().orElse(null);
        if (user == null) {
            throw new GlobalException(ResultCode.USER_NOT_EXIST);
        }
        // 验证密码
        ldapTemplate.authenticate(query, userLoginVO.getPassword());

        // 登录成功，设置用户角色
        StpUtil.login(user.getUsername());
        StpUtil.getSession()
                .set(RoleConstants.USERNAME, user.getUsername())
                .set(RoleConstants.ROLE, user.getRole());
        return StpUtil.getTokenValue();
    }
}
