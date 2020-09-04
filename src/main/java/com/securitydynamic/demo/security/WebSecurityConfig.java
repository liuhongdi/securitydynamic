package com.securitydynamic.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final static BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder();
    @Resource
    private UserLoginFailureHandler userLoginFailureHandler;//登录失败的处理类
    @Resource
    private UserLoginSuccessHandler userLoginSuccessHandler;//登录成功的处理类
    @Resource
    private UserLogoutSuccessHandler userLogoutSuccessHandler;//退出成功的处理类
    @Resource
    private UserAccessDeniedHandler userAccessDeniedHandler;//无权访问的处理类
    @Resource
    private SecUserDetailService secUserDetailService;     //用户信息类，用来得到UserDetails


    @Resource
    private CustomFilterInvocationSecurityMetadataSource customFilterInvocationSecurityMetadataSource;
    @Resource
    private CustomAccessDecisionManager customAccessDecisionManager;


    //指定加密的方式，避免出现:There is no PasswordEncoder mapped for the id "null"
    @Bean
    public PasswordEncoder passwordEncoder(){//密码加密类
        return  new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //通过数据库的配置，动态判断当前用户是否可以访问当前url
        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setSecurityMetadataSource(customFilterInvocationSecurityMetadataSource);
                        object.setAccessDecisionManager(customAccessDecisionManager);
                        return object;
                    }
                });

        /*
        //static
        http.authorizeRequests()
                .antMatchers("/css/**","/js/**","/img/**")//静态资源等不需要验证
                .permitAll();
        //permitall
        http.authorizeRequests()
                .antMatchers("/home/**")//静态资源等不需要验证
                .permitAll();
        */
        //login
        http.formLogin()
                .loginPage("/login/login")
                .loginProcessingUrl("/login/logined")//发送Ajax请求的路径
                .usernameParameter("username")//请求验证参数
                .passwordParameter("password")//请求验证参数
                .failureHandler(userLoginFailureHandler)//验证失败处理
                .successHandler(userLoginSuccessHandler)//验证成功处理
                .permitAll(); //登录页面用户任意访问
        //logout
        http.logout()
                .logoutUrl("/login/logout")
                .logoutSuccessUrl("/login/logout")
                .logoutSuccessHandler(userLogoutSuccessHandler)//登出处理
                .deleteCookies("JSESSIONID")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .permitAll();

        //其他任何请求,登录后可以访问
        http.authorizeRequests().anyRequest().authenticated();
        //accessdenied
        http.exceptionHandling().accessDeniedHandler(userAccessDeniedHandler);//无权限时的处理
        //user detail
        http.userDetailsService(secUserDetailService);
        //rememberme
        //图形验证码
        //http.csrf().disable();
  }

    @Resource
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(secUserDetailService).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return ENCODER.encode(charSequence);
            }
            //密码匹配，看输入的密码经过加密与数据库中存放的是否一样
            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return ENCODER.matches(charSequence,s);
            }
        });
    }
}