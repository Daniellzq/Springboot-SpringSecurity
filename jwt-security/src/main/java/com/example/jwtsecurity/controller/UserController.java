package com.example.jwtsecurity.controller;

import com.example.jwtsecurity.common.ResultVO;
import com.example.jwtsecurity.domain.Role;
import com.example.jwtsecurity.domain.User;
import com.example.jwtsecurity.repository.RoleRepository;
import com.example.jwtsecurity.repository.UserRepository;
import com.example.jwtsecurity.util.JwtUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@RestController
public class UserController {

    @Resource
    private UserRepository userRepository;
    @Resource
    private RoleRepository roleRepository;

    @GetMapping("/token")
    public ResultVO login(String username, String password){
        User user = userRepository.findByUsername(username);

        if(user == null || !user.getPassword().equals(password)){
            ResultVO<Object> result = new ResultVO<>();
            result.setCode(10);
            result.setMsg("用户名或密码错误");
            return result;
        }

        ResultVO<Object> success = new ResultVO<>();
        //用户名密码正确，生成token给客户端
        success.setCode(0);
        List<Role> roles = Collections.singletonList(roleRepository.findById(user.getId()).get());
        success.setData(JwtUtil.generateToken(username, roles));
        return success;
    }



}
