package com.securitydynamic.demo.security;

import com.securitydynamic.demo.pojo.SysUser;
import com.securitydynamic.demo.service.SysUserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by liuhongdi on 2020/07/09.
*/
@Component("SecUserDetailService")
public class SecUserDetailService implements UserDetailsService{
    @Resource
    private SysUserService sysUserService;

    //查库,得到用户的权限信息
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SysUser oneUser = sysUserService.getOneUserByUsername(s);//数据库查询 看用户是否存在
        String encodedPassword = oneUser.getPassword();
        Collection<GrantedAuthority> collection = new ArrayList<>();//权限集合
        //用户权限：需要加 ROLE_
        List<String> roles = oneUser.getRoles();
        //System.out.println(roles);
        for (String roleone : roles) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_"+roleone);
            //GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(roleone);
            collection.add(grantedAuthority);
       }
        //增加用户的userid,nickname
        SecUser user = new SecUser(s,encodedPassword,collection);
        user.setUserid(oneUser.getUserId());
        user.setNickname(oneUser.getNickName());
        return user;
    }
}