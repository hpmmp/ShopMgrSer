package com.shop.service.sys.impl;

import com.shop.dao.sys.SysUserMapper;
import com.shop.entity.sys.SysUser;
import com.shop.model.AccountModel;
import com.shop.service.sys.ISysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class SysUserImpl implements ISysUser {

    @Autowired
    private SysUserMapper sysUserMapper;  
    @Override
    public SysUser get(Integer userId) {
        return sysUserMapper.selectByPrimaryKey(userId);
    }

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public AccountModel getByAccount(String account, Short status) {

        logger.info("into SysUserImpl");
        SysUser req = new SysUser();
        req.setAccount(account);
        req.setStatus(status);
        SysUser sysUser = sysUserMapper.selectOne(req);
        logger.info("account:"+sysUser.getAccount());
        logger.info("end SysUserImpl-sysUserMapper");
        if(sysUser != null){
            AccountModel accountModel = new AccountModel();
            accountModel.setId(sysUser.getId()); 
            accountModel.setAccount(account);
            accountModel.setName(sysUser.getName());
            accountModel.setPassword(sysUser.getPassword());
            return accountModel;
        }else{
            return null;
        }
         
    }

 
 
}
