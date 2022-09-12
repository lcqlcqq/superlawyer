package com.lcq.service;

import com.lcq.dto.CompanyInfo;
import com.lcq.dto.LawyerInfo;
import com.lcq.param.LoginParam;
import com.lcq.param.RegisterParam;
import com.lcq.pojo.Permission;
import com.lcq.pojo.User;

import java.util.List;

public interface UserService {
    /**
     * 获取所有用户，type：0管理员、1企业、2律师
     * @return
     */
    List<User> getAllUsers();

    /**
     * 通过id获取用户信息
     * @param id
     * @return
     */
    User getUserById(Long id);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    Integer updateUser(User user);

    /**
     * 删除用户
     * @param id
     * @return
     */
    Integer deleteUser(Long id);

    /**
     * 新增用户
     * @param user
     * @return
     */
    Integer addUser(User user);

    /**
     * 用户账号登录
     */
    User loginByUsername(LoginParam loginParam);

    /**
     * 用户注册
     */
    User register(RegisterParam registerParam);

    /**
     * 获取企业详细信息
     * @param id
     * @return
     */
    CompanyInfo getCompanyInfo(String id);

    /**
     * 获取企业列表
     * @return
     */
    List<User> getCompanies();

    /**
     * 获取律师列表
     * @return
     */
    List<User> getLawyers();

    /**
     * 获取管理员列表
     * @return
     */
    List<User> getAdmins();

    /**
     * 获取律师详细信息
     * @param id
     * @return
     */
    LawyerInfo getLawyerInfo(String id);

    /**
     * 登录认证、查询用户名密码
     * @param username
     * @return
     */
    User getUserByUsername(String username);

    /**
     * 获取用户的权限
     * @param username
     * @return
     */
    List<Permission> getUserPermissions(String username);

    /**
     * 获取密码
     * @param username
     * @return
     */
    String getPWDByUsername(String username);
}
