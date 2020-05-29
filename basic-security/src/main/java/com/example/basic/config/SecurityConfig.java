package com.example.basic.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/index")
                //和login.html中表单提交的一直必须一样，这样才能让springsecurity帮你处理请求
                .loginProcessingUrl("/login")
                .successForwardUrl("/hello")
                .and()
                .authorizeRequests()
                .antMatchers("/index", "/login", "/error", "/favicon.ico").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf()//防止xss攻击的，但是我们不需要，所以我们禁掉
                .disable();
    }

    /**
     * 自定义数据查询
     * 内存中的数据源
     * 以这种方式定义密码的时候，要在密码前面加上{noop}这个前缀或者配置一个密码加密器的bean，否则验证会出错。
     * 另外还有一点就是一定要添加roles或者authorities，否则springsecurity不予通过
     *s
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*auth.userDetailsService(new InMemoryUserDetailsManager(
                User.builder().username("admin").password("{noop}123456").authorities("admin").build()
        ));*/
        super.configure(auth);
    }

    /**
     * 配置一个密码加密器
     * @return
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("123456"));
    }
}
