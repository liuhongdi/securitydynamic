package com.securitydynamic.demo.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class CustomAccessDecisionManager implements AccessDecisionManager {

    //比较用户的权限和url所需要的权限，确定是否可以访问
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        for (ConfigAttribute configAttribute : configAttributes) {
            System.out.println("-------------路径需要的角色:"+configAttribute.getAttribute());
            //如果是all,表示所有的都允许访问
            if ("ALL".equals(configAttribute.getAttribute())) {
                return;
            }
            //是否没有权限要求
            if ("ROLE_def".equals(configAttribute.getAttribute())) {
                if (authentication instanceof AnonymousAuthenticationToken) {
                    System.out.println("匿名用户");
                    throw new AccessDeniedException("权限不足，无法访问！");
                } else {
                    System.out.println("其他类型用户,可以访问");
                    return;
                }
            }
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                String userRole = authority.getAuthority();
                //数据库中没有保存ROLE_,这里添加上
                String menuRole = "ROLE_"+configAttribute.getAttribute();
                //System.out.println("账户所拥有的权限:" + userRole);
                //System.out.println("路径需要的角色:" + menuRole);
                if (userRole.equals(menuRole)) {
                    //System.out.println("进入应用系统");
                    return;
                }
            }
        }
        throw new AccessDeniedException("权限不足，无法访问！");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}