package xyz.ddlnt.servergateway.config;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ZZULI_SE 210910
 * @data 2025/7/13 10:26
 */
@Slf4j
@Configuration
public class SaTokenConfigure {
    // 注册 Sa-Token全局过滤器
    @Bean
    public SaReactorFilter getSaReactorFilter() {
        return new SaReactorFilter()
                // 拦截地址
                .addInclude("/**")    /* 拦截全部path */
                // 开放地址
                .addExclude("/favicon.ico")
                // 鉴权方法：每次访问进入

                //获取请求路径

                .setAuth(obj -> {
                    // 登录校验 -- 拦截所有路由，开放登录接口
                    SaRouter.match("/**")
                            .notMatch("/service-user/user/login")
                            .notMatch("/service-user/ldap_user/login")
                            .notMatch("/service-user/github_user/callback")
                            .check(r -> StpUtil.checkLogin());;
                })
                // 异常处理方法：每次setAuth函数出现异常时进入
                .setError(e -> {
                    String requestPath = SaHolder.getRequest().getRequestPath();
                    log.error("Sa-Token全局过滤器：\n\nToken信息：{}\n异常信息：{}\n请求路径：{}\n", StpUtil.getTokenInfo(), e.getMessage(), requestPath);
                    return SaResult.error("Sa-Token全局过滤器："+e.getMessage());
                })
                ;
    }
}