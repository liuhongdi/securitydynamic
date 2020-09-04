package com.securitydynamic.demo.security;

import com.securitydynamic.demo.constant.ResponseCode;
import com.securitydynamic.demo.result.RestResult;
import com.securitydynamic.demo.util.ServletUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Created by liuhongdi on 2020/07/13.
 *  拒绝访问
 */
@Component("UserAccessDeniedHandler")
public class UserAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                       AccessDeniedException e) throws IOException, ServletException {
        boolean isAjax = ServletUtil.isAjax();
        System.out.println("-----------------------UserAccessDeniedHandler:");
        if (isAjax == true) {
            ServletUtil.printRestResult(RestResult.error(ResponseCode.ACCESS_DENIED));
        } else {
            ServletUtil.printString(ResponseCode.ACCESS_DENIED.getMsg());
        }
    }
}