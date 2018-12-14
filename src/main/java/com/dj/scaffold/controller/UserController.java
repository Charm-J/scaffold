package com.dj.scaffold.controller;

import com.dj.scaffold.common.Result;
import com.dj.scaffold.common.annotation.Auth;
import com.dj.scaffold.common.annotation.Log;
import com.dj.scaffold.model.vo.LoginVo;
import com.dj.scaffold.service.UserService;
import com.dj.scaffold.util.ParamUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@Api(description = "系统用户相关")
public class UserController {

    @Autowired
    private UserService userService;

    @Log
    @Auth
    @GetMapping("/{userId}")
    @ApiOperation(value = "系统用户详情")
    public Result getUserById(@PathVariable("userId") Long userId) {
        return userService.getUserById(userId);
    }

    @Log
    @GetMapping("/maxId")
    @ApiOperation(value = "用户最大ID")
    public Result getMaxId() {
        return userService.getMaxId();
    }

    @Log
    @RequestMapping(value = "/login", method = {RequestMethod.PUT, RequestMethod.POST})
    public Result login(@RequestBody @Valid LoginVo loginVo, BindingResult result) {
        ParamUtil.valid(result);
        return userService.login(loginVo);
    }

}
