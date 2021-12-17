package com.springboot.springinit.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    CustomUserDetailsService userDetailsService;
    public static String user = "USER";
    public static String admin = "ADMIN";
    @Resource
    PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/product/student","/login.html").permitAll()   // 放行访问url
                .antMatchers("/product/**").hasRole(user) //添加/product/** 下的所有请求只能由user角色才能访问
                .antMatchers("/admin/**").hasRole(admin) //添加/admin/** 下的所有请求只能由admin角色才能访问
                .anyRequest().authenticated() // 没有定义的请求，所有的角色都可以访问（tmp也可以）。
                .and()
                .formLogin().loginPage("/login.html").and()
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
}
