package xyz.ddlnt.serviceuser.service.impl;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.ddlnt.commonutil.constant.RoleConstants;
import xyz.ddlnt.commonutil.enumeration.ResultCode;
import xyz.ddlnt.commonutil.exception.GlobalException;
import xyz.ddlnt.model.entity.User;
import xyz.ddlnt.model.dto.UserLoginDTO;
import xyz.ddlnt.model.vo.LoginVO;
import xyz.ddlnt.serviceuser.mapper.UserMapper;
import xyz.ddlnt.serviceuser.service.UserService;

/**
 * @author ZZULI_SE 210910
 * @data 2025/7/13 8:37
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public String   userLogin(UserLoginDTO userLoginVO) {
        // 校验参数
        if (userLoginVO.getUsername() == null || userLoginVO.getUsername().isEmpty() ||
                userLoginVO.getPassword() == null || userLoginVO.getPassword().isEmpty()) {
            throw new GlobalException(ResultCode.PARAM_ERROR);
        }
        // 获取用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, userLoginVO.getUsername());
        User user = userMapper.selectOne(queryWrapper);
        // 登录失败
        if (user == null) {
            throw new GlobalException(ResultCode.USER_NOT_EXIST);
        }
        if (!user.getPassword().equals(userLoginVO.getPassword())) {
            throw new GlobalException(ResultCode.USER_PASSWORD_ERROR);
        }
        // 登录成功，设置用户角色
        StpUtil.login(user.getId());
        StpUtil.getSession()
                .set(RoleConstants.USERNAME, user.getUsername())
                .set(RoleConstants.ROLE, user.getRole());
        return StpUtil.getTokenValue();
    }
}
