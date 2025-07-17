package xyz.ddlnt.serviceuser.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.ddlnt.commonutil.constant.HttpConstants;
import xyz.ddlnt.commonutil.constant.RoleConstants;
import xyz.ddlnt.commonutil.util.HttpUtils;
import xyz.ddlnt.serviceuser.conf.configProperties.Oauth2ConfigProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZZULI_SE 210910
 * @data 2025/7/13 9:00
 */
@Slf4j
@Tag(name = "Github：用户接口测试")
@RestController
@RequestMapping("/github_user")
public class GithubUserController {
    @Resource
    private Oauth2ConfigProperties oauth2ConfigProperties;
    @RequestMapping(value = "/callback")
    public void callBack(@RequestParam("code")String code, HttpServletResponse response) throws Exception{

        //获取accessToken
        String getAccessTokenUrl=HttpConstants.HTTPS_LOGIN_OAUTH_ACCESS_TOKEN;
        JSONObject param=new JSONObject();
        param.put(HttpConstants.CLIENT_ID,oauth2ConfigProperties.getClientId());
        param.put(HttpConstants.CLIENT_SECRET,oauth2ConfigProperties.getClientSecret());
        param.put(HttpConstants.CODE,code);

        Map<String, String> headers=new HashMap<>(2);
        headers.put(HttpConstants.CONTENT_TYPE,HttpConstants.APPLICATION_JSON);
        headers.put(HttpConstants.ACCEPT,HttpConstants.APPLICATION_JSON);
        String resultStr= HttpUtils.postJsonStr(getAccessTokenUrl,headers,param.toJSONString());
        JSONObject accessToken=(JSONObject) JSON.parse(resultStr);
        System.out.println("accessToken:"+accessToken.toJSONString());

        //获取用户信息
        String getUserInfoUrl=HttpConstants.HTTPS__USER;
        Map<String, String> headers2=new HashMap<>(2);
        String token=HttpConstants.BEARER+" "+accessToken.getString(HttpConstants.ACCESS_TOKEN);
        headers2.put(HttpConstants.AUTHORIZATION,token);
        String userInfoStr = HttpUtils.httpGet(getUserInfoUrl,headers2,null);
        System.out.println("userInfo:"+userInfoStr);
        JSONObject userInfo=(JSONObject) JSON.parse(userInfoStr);
        StpUtil.login(userInfo.getString("id"));
        log.info("用户登录成功{},{}", userInfo.getString("id"), userInfo.getString("login"));
        StpUtil.getSession()
                .set(RoleConstants.USERNAME, userInfo.getString("login"))
                .set(RoleConstants.ROLE, RoleConstants.EDITOR);
        log.error("用户登录TokenValue{}", StpUtil.getTokenValue());

        // 跳转临时中间界面
        String html = "<html><script>" +
                "localStorage.setItem('luban-springcloud-demo-token', '" + StpUtil.getTokenValue() + "');" +
                "window.location.replace = '" + oauth2ConfigProperties.getSuccessUrl() + "';" +
                "</script></html>";
        response.getWriter().write(html);

    }
}
