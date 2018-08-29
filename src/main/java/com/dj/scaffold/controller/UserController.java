package com.dj.scaffold.controller;

import com.dj.scaffold.common.Result;
import com.dj.scaffold.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/detail/{userId}")
    public Result getUserDetail(@PathVariable("userId") Long userId){
        return userService.getUserDetail(userId);
    }
}
