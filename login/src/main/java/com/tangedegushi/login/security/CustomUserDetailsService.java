package com.tangedegushi.login.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Resource
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        System.out.println("CustomUserDetailsService login name = "+login);
        //可以根据登录名login去数据库查询对应密码，
        String password = "123456";
        // 对应的角色ROLE_USER，ROLE_ADMIN可以保持在数据库
        // 默认角色前缀必须是ROLE_，因为spring-security会在授权的时候自动使用match中的角色加上ROLE_后进行比较。
        // 数据库和SecurityConfig HttpSecurity设置搭配使用
        SimpleGrantedAuthority role_user = new SimpleGrantedAuthority("ROLE_"+SecurityConfig.user);
        SimpleGrantedAuthority role_admin = new SimpleGrantedAuthority("ROLE_"+SecurityConfig.admin);
//        new User(login, passwordEncoder.encode(password), Arrays.asList(role_admin));
        return new User(login, passwordEncoder.encode(password), Arrays.asList(role_user,role_admin));
    }

}
