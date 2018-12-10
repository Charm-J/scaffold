package com.dj.scaffold.service;

import com.dj.scaffold.common.Result;
import com.dj.scaffold.model.vo.LoginVo;

public interface UserService {

    Result getUserById(Long userId);

    Result getMaxId();

    Result login(LoginVo loginVo);
}
