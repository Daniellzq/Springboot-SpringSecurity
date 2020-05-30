package com.example.jwtsecurity.controller;

import com.example.jwtsecurity.common.ResultVO;
import com.example.jwtsecurity.domain.AuthUser;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class PermissionController {

    @GetMapping("/normal")
    public ResultVO loginTest(@AuthenticationPrincipal AuthUser authUser) {
        ResultVO<String> resultVO = new ResultVO<>();
        resultVO.setCode(0);

        resultVO.setData("你成功访问了该api，这代表你已经登录，你是： " + authUser);
        return resultVO;
    }

    @GetMapping("/role")
    @PreAuthorize("hasRole('user')")
    public ResultVO loginTest() {
        ResultVO<String> resultVO = new ResultVO<>();
        resultVO.setCode(0);

        resultVO.setData("你成功访问了需要有 user 角色的api。");
        return resultVO;
    }

}
