package com.dj.scaffold.service;

import com.dj.scaffold.common.Result;
import com.dj.scaffold.model.vo.LoginVo;

public interface UserService {

    Result getUserDetail(Long userId);

    Result getMaxId();

    Result login(LoginVo loginVo);
}
