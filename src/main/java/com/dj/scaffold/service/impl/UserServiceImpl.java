package com.dj.scaffold.service.impl;

import com.dj.scaffold.common.Result;
import com.dj.scaffold.common.exception.ExceptionEnum;
import com.dj.scaffold.dao.SysUserMapper;
import com.dj.scaffold.model.entity.SysUser;
import com.dj.scaffold.service.UserService;
import com.dj.scaffold.util.ParamUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public Result getUserDetail(Long userId) {
        if (ParamUtil.isEmpty(userId)) {
            return new Result(ExceptionEnum.WRONG_REQ.getCode(), ExceptionEnum.WRONG_REQ.getMsg());
        }
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        return new Result(sysUser);
    }
}
