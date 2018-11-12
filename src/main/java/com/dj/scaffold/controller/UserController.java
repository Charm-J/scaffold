package com.dj.scaffold.controller;

import com.dj.scaffold.common.Result;
import com.dj.scaffold.common.annotation.Log;
import com.dj.scaffold.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Api(description = "系统用户相关")
public class UserController {

    @Autowired
    private UserService userService;

    @Log
    @GetMapping("/detail/{userId}")
    @ApiOperation(value = "系统用户详情")
    public Result getUserDetail(@PathVariable("userId") Long userId) {
        return userService.getUserDetail(userId);
    }

    @Log
    @GetMapping("/maxId")
    @ApiOperation(value = "用户最大ID")
    public Result getMaxId() {
        return userService.getMaxId();
    }
}
