package com.tangedegushi.login.security;

import com.tangedegushi.login.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// JWTProvider需要至少提供两个方法，一个用来创建我们的token，另一个根据token获取Authentication。
// provider需要保证Key密钥是唯一的，使用init()构建，否则会抛出异常。
@Component
public class JWTProvider {

    private Key key;    // 私钥
    private final long tokenValidityInMilliseconds = 5_60_1000; // 有效时间
    private final long tokenValidityInMillisecondsForRememberMe = 10_60_1000; // 记住我有效时间
    private final String base64Secret = "basesecrettangedegushitangedegushisddsfsdgsdfgsdfgsfdsftyfgjf";

    @PostConstruct
    public void init() {
        byte[] keyBytes = Decoders.BASE64.decode(base64Secret);
        this.key = Keys.hmacShaKeyFor(keyBytes); // 使用mac-sha算法的密钥
    }

    public String createToken(Authentication authentication, boolean rememberMe) {
        long now = (new Date()).getTime();
        Date validity;
        if (rememberMe) {
            validity = new Date(now + this.tokenValidityInMillisecondsForRememberMe);
        } else {
            validity = new Date(now + this.tokenValidityInMilliseconds);
        }
        //实际应查询数据库返回
        User user = new User(authentication.getName());
        Map<String, Object> map = new HashMap<>();
        map.put("sub", authentication.getName());
        map.put("user", user);
        return Jwts.builder()
                .setClaims(map) // 添加body
                .signWith(key, SignatureAlgorithm.HS512) // 指定摘要算法
                .setExpiration(validity) // 设置有效时间
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token).getBody(); // 根据token获取body
        //实际应查询数据库返回
        User principal = new User(claims.getSubject());
        Collection<? extends GrantedAuthority> authorities = principal.getAuthorities();
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }
}
