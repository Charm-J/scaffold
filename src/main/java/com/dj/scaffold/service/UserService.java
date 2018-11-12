package com.dj.scaffold.service;

import com.dj.scaffold.common.Result;

public interface UserService {

    Result getUserDetail(Long userId);

    Result getMaxId();
}
