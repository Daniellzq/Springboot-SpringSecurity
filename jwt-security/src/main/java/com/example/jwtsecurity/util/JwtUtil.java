package com.example.jwtsecurity.util;

import com.example.jwtsecurity.domain.AuthUser;
import com.example.jwtsecurity.domain.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.*;
import java.util.stream.Collectors;

public class JwtUtil {

    private static final String secret = "jsbintask@gmail.com";

    public static String generateToken(String username, List<Role> roles){
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", roles.parallelStream().map(Role::getRoleName).collect(Collectors.joining(",")));

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 5 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public static AuthUser parseToken(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        String username = claims.getSubject();
        String roles = (String) claims.get("roles");

        //因为生成的时候没有放入密码，所以不需要密码
        return new AuthUser(username, null, Arrays.stream(roles.split(",")).map(
                name -> {
                    Role role = new Role();
                    role.setRoleName(name);
                    return role;
                }
        ).collect(Collectors.toList()));
    }

}
