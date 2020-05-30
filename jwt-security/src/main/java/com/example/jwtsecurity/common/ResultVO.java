package com.example.jwtsecurity.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultVO<T> {

    // code请自己定义，例如  10成功，20需要登陆  30无权限等等
    private Integer code;
    private String msg;
    private T data;
}
