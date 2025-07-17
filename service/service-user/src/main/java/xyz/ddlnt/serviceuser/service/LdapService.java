package xyz.ddlnt.serviceuser.service;

import xyz.ddlnt.model.dto.UserLoginDTO;
import xyz.ddlnt.model.vo.LoginVO;

/**
 * @author ZZULI_SE 210910
 * @data 2025/7/9 22:04
 */
public interface LdapService {

    /**
     * 用户登录
     * @param userLoginVO
     * @return
     */
    String  userLogin(UserLoginDTO userLoginVO);
}
