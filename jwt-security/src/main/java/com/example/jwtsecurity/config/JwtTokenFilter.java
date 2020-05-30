package com.example.jwtsecurity.config;

import com.example.jwtsecurity.domain.AuthUser;
import com.example.jwtsecurity.util.JwtUtil;
import io.jsonwebtoken.Jwt;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = httpServletRequest.getHeader("token");

        //获取token，并且解析token，如果解析成功，则放入 SecurityContext
        if(token != null){
            try{
                AuthUser authUser = JwtUtil.parseToken(token);
                //todo: 如果此处不放心解析出来的 authuser，可以再从数据库查一次，验证用户身份

                //解析成功
                if(SecurityContextHolder.getContext().getAuthentication() == null){
                    //依然使用原来filter中的token对象
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(authUser,
                            null,
                            authUser.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            } catch (Exception e){
                logger.info("解析失败，可能是伪造的或者该token已经失效了（我们设置失效5分钟）。");
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }
}
