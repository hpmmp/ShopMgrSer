package com.shop.entity.sys;

import com.fasterxml.jackson.annotation.JsonView;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_user")
public class SysUser implements Serializable{
    public interface Record {}
    @Id
    @JsonView(Record.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 账号名
     */
    @Column(name = "account")
    @JsonView(Record.class)
    private String account;

    /**
     * 创建时间
     */
    @Column(name = "createdTS")
    private Date createdTS;
 
 

    /**
     * 状态：1.有效 2.失效
     */
    @Column(name = "status")
    private Short status;

    /**
     * 真实姓名
     */
    @Column(name = "name")
    @JsonView(Record.class)
    private String name;

    /**
     * 密码
     */
    @Column(name = "password")
    @JsonView(SysUser.Record.class)
    private String password;

   

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取账号名
     *
     * @return Account - 账号名
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置账号名
     *
     * @param account 账号名
     */
    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    /**
     * 获取创建时间
     *
     * @return CreatedTS - 创建时间
     */
    public Date getCreatedTS() {
        return createdTS;
    }

    /**
     * 设置创建时间
     *
     * @param createdTS 创建时间
     */
    public void setCreatedTS(Date createdTS) {
        this.createdTS = createdTS;
    }

     

    /**
     * 获取状态：1.有效 2.失效
     *
     * @return status - 状态：1.有效 2.失效
     */
    public Short getStatus() {
        return status;
    }

    /**
     * 设置状态：1.有效 2.失效
     *
     * @param status 状态：1.有效 2.失效
     */
    public void setStatus(Short status) {
        this.status = status;
    }

    /**
     * 获取真实姓名
     *
     * @return Name - 真实姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置真实姓名
     *
     * @param name 真实姓名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

      
 
}