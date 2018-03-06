package com.wf2311.flog.example.controller;

import com.wf2311.flog.example.entity.User;
import com.wf2311.log.annotation.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangfeng
 * @time 2018/02/07 11:23.
 */
@RestController
public class TestController {

    @Log("用户登录")
    @PostMapping("/login")
    public String login(User user){
        return "login";
    }
}
