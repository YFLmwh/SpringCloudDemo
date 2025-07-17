package xyz.ddlnt.serviceuser.service.impl;

/**
 * @author ZZULI_SE 210910
 * @data 2025/7/13 9:56
 */

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import xyz.ddlnt.commonutil.constant.RoleConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色权限加载
 */
@Component
public class StpInterfaceImpl implements StpInterface {
    private static String role = RoleConstants.USER;

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return new ArrayList<>();
    }

    /**
     * 返回一个账号所拥有的角色标识集合
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        List<String> list = new ArrayList<>();
        list.add(role);
        return list;
    }

    /**
     * 角色校验
     * @param request
     * @param roles
     */
    public static void checkRoleOr(HttpServletRequest request, String ...  roles) {
        String satoken = request.getHeader(RoleConstants.TOKEN);
        SaSession session = StpUtil.getSessionByLoginId(StpUtil.getLoginIdByToken(satoken));
        role  = session.get(RoleConstants.ROLE).toString();
        StpUtil.checkRoleOr(roles);
    }

}

