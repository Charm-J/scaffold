package com.dj.scaffold.dao;

import com.dj.scaffold.model.entity.SysUser;
import tk.mybatis.mapper.common.Mapper;

public interface SysUserMapper extends Mapper<SysUser> {

    Long getMaxId();
}