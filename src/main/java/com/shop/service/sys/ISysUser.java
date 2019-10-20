package com.shop.service.sys;


import com.shop.entity.sys.SysUser;
import com.shop.model.AccountModel;

import java.util.List;

/**
 * @author Cater.Tian
 */
public interface ISysUser {
    /**
     * 根据ID获实体
     */
    public SysUser get(Integer userId);

    /**
     * 根据登录账号获实体
     */
    public AccountModel getByAccount(String account, Short status);
    
    
 
}
