package com.securitydynamic.demo.security;

import com.securitydynamic.demo.constant.ResponseCode;
import com.securitydynamic.demo.pojo.SysUser;
import com.securitydynamic.demo.result.RestResult;
import com.securitydynamic.demo.util.ServletUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by liuhongdi on 2020/07/08.
 * 登录失败
 */

@Component("UserLoginFailureHandler")
public class UserLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        //System.out.println("UserLoginFailureHandler");
        ServletUtil.printRestResult(RestResult.error(ResponseCode.LOGIN_FAIL));
    }
}