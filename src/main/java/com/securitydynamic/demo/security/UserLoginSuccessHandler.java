package com.securitydynamic.demo.security;


import com.securitydynamic.demo.constant.ResponseCode;
import com.securitydynamic.demo.exception.BusinessException;
import com.securitydynamic.demo.result.RestResult;
import com.securitydynamic.demo.util.ServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component("UserLoginSuccessHandler")
public class UserLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //System.out.println("UserLoginSuccessHandler");
        ServletUtil.printRestResult(RestResult.success(0,"登录成功"));
    }
}
