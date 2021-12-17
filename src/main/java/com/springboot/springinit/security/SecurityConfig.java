package com.springboot.springinit.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    CustomUserDetailsService userDetailsService;
    public static String user = "USER";
    public static String admin = "ADMIN";
    @Resource
    PasswordEncoder passwordEncoder;
    @Resource
    private JWTProvider jwtProvider;
    @Resource
    private JWTFilter jwtFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //spring security会在默认的情况下将认证信息放到HttpSession中。
                //但是对于我们的前后端分离的情况，如app，小程序，web前后分离等，httpSession就没有用武之地了,可通过如下session配置
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/product/student","/login.html").permitAll()   // 放行访问url
                .antMatchers("/product/**").hasRole(user) //添加/product/** 下的所有请求只能由user角色才能访问
                .antMatchers("/admin/**").hasRole(admin) //添加/admin/** 下的所有请求只能由admin角色才能访问
                .anyRequest().authenticated().and() // 没有定义的请求，所有的角色都可以访问（tmp也可以）。
//                .formLogin().loginPage("/login.html").and()
                .formLogin().successHandler(loginSuccessHandler).and()  //登录成功回调
                .logout().logoutSuccessHandler(logoutSuccessHandler).and()  //登出成功回调
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)  //验证请求头中是否携带auth登录信息
                .httpBasic().and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        // 添加不做权限的URL
//        web.ignoring().antMatchers("/gogo.html");
//    }

    // 登录成功
    private AuthenticationSuccessHandler loginSuccessHandler = new AuthenticationSuccessHandler() {
        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
            System.out.println("onAuthenticationSuccess");
            PrintWriter writer = response.getWriter();
            writer.println(jwtProvider.createToken(authentication, true));
        }
    };

    // 登出成功
    private LogoutSuccessHandler logoutSuccessHandler = new LogoutSuccessHandler() {
        @Override
        public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
            System.out.println("onLogoutSuccess");
            PrintWriter writer = response.getWriter();
            writer.println("logout success");
            writer.flush();
        }
    };
}
