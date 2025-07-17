package xyz.ddlnt.serviceuser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.ddlnt.model.entity.User;
import xyz.ddlnt.model.dto.UserLoginDTO;
import xyz.ddlnt.model.vo.LoginVO;

/**
 * @author ZZULI_SE 210910
 * @data 2025/7/13 8:36
 */
public interface UserService extends IService<User> {
    /**
     * 用户登录
     * @param userLoginVO
     * @return
     */
    String userLogin(UserLoginDTO userLoginVO);
}
