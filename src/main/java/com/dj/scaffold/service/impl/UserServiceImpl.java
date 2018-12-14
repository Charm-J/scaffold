package com.dj.scaffold.service.impl;

import com.dj.scaffold.common.Const;
import com.dj.scaffold.common.Redis;
import com.dj.scaffold.common.Result;
import com.dj.scaffold.common.ResultHelper;
import com.dj.scaffold.common.exception.AppException;
import com.dj.scaffold.common.exception.ExceptionEnum;
import com.dj.scaffold.dao.SysUserMapper;
import com.dj.scaffold.model.bo.LoginBo;
import com.dj.scaffold.model.entity.SysUser;
import com.dj.scaffold.model.vo.LoginVo;
import com.dj.scaffold.service.UserService;
import com.dj.scaffold.util.MD5Util;
import com.dj.scaffold.util.ParamUtil;
import com.dj.scaffold.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private Redis redis;

    @Override
    @Cacheable(value = "getUserById", sync = true)
    public Result getUserById(Long userId) {
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

    @Override
    public Result login(LoginVo loginVo) {
        SysUser sysUser = sysUserMapper.selectOne(new SysUser(loginVo.getAccount()));
        if (null == sysUser) {
            throw new AppException(ExceptionEnum.USER_NOT_EXIST);
        }
        String password = MD5Util.MD5(loginVo.getPassword());
        if (!password.equals(sysUser.getPassword())) {
            throw new AppException(ExceptionEnum.ERR_USERNAME_OR_PWD);
        }
        String token = UUID.gen();
        redis.set(token, sysUser, Const.EXPIRED);
        redis.hset(Const.RedisKeys.SYS_USER, String.valueOf(sysUser.getUserId()), token);
        return ResultHelper.ok(new LoginBo(sysUser.getUserId(), sysUser.getUsername(), token));
    }
}
