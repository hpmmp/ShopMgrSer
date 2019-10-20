package com.shop.dao.sys;

import com.shop.entity.sys.SysUser;
import com.shop.message.SysUserReq;

import tk.mybatis.mapper.common.Mapper;

public interface SysUserMapper extends Mapper<SysUser> {
	SysUser getByAccount(SysUserReq req); 
}