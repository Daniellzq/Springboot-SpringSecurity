package com.example.basic.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/index")
                //和login.html中表单提交的一直必须一样，这样才能让springsecurity帮你处理请求
                .loginProcessingUrl("/login")
                .successForwardUrl("/hello")
                .and()
                .authorizeRequests()
                .antMatchers("/index", "/login", "/error").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf()
                .disable();
    }
}
