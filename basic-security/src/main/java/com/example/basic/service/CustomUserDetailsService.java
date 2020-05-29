package com.example.basic.service;

import com.example.basic.entity.AuthUser;
import com.example.basic.entity.User;
import com.example.basic.repository.RoleRepository;
import com.example.basic.repository.UserRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;

@Service
@Primary
public class CustomUserDetailsService implements UserDetailsService {

    @Resource
    private UserRepository UserRepository;

    @Resource
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = UserRepository.findByUsername(username);

        //如果没有找到用户，要抛出 UsernameNotFoundException，然后springsecurity会处理。
        if(user == null){
            throw new UsernameNotFoundException("user：" + username + " is not found !");
        }

        return new AuthUser(user.getUsername(), user.getPassword(),
                roleRepository.findAllById(Collections.singletonList(user.getRoleId())));
    }
}
