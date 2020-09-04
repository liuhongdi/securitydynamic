package com.securitydynamic.demo.security;

import com.securitydynamic.demo.result.RestResult;
import com.securitydynamic.demo.util.ServletUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by liuhongdi on 2020/07/12.
 * 用户退出
 */
@Component("UserLogoutSuccessHandler")
public class UserLogoutSuccessHandler implements LogoutSuccessHandler{
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletRequest.getSession().invalidate();
        ServletUtil.printRestResult(RestResult.success(0,"退出成功"));
    }
}
