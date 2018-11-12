package com.dj.scaffold.service.impl;

import com.dj.scaffold.common.Result;
import com.dj.scaffold.common.ResultHelper;
import com.dj.scaffold.common.exception.ExceptionEnum;
import com.dj.scaffold.dao.SysUserMapper;
import com.dj.scaffold.model.entity.SysUser;
import com.dj.scaffold.service.UserService;
import com.dj.scaffold.util.ParamUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public Result getUserDetail(Long userId) {
        if (ParamUtil.isEmpty(userId)) {
            return ResultHelper.error(ExceptionEnum.WRONG_REQ);
        }
        SysUser sysUser = null;
        try {
            sysUser = sysUserMapper.selectByPrimaryKey(userId);
        } catch (Exception e) {
            logger.error("获取用户详情异常,具体原因={}", e);
        }

        return ResultHelper.ok(sysUser);
    }

    @Override
    public Result getMaxId() {
        return ResultHelper.ok(sysUserMapper.getMaxId());
    }
}
