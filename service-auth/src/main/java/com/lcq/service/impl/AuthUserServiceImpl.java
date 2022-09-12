package com.lcq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lcq.mapper.AuthPermissionMapper;
import com.lcq.mapper.AuthUserMapper;
import com.lcq.param.LoginParam;
import com.lcq.param.RegisterParam;
import com.lcq.pojo.Permission;
import com.lcq.pojo.User;
import com.lcq.service.AuthUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthUserServiceImpl implements AuthUserService {
    @Autowired
    AuthUserMapper userMapper;
    @Autowired
    AuthPermissionMapper permissionMapper;

    @Override
    public List<User> getAllUsers() {
        return userMapper.selectList(null);
    }

    @Override
    public User getUserById(Long id) {
        User user = userMapper.selectById(id);
        return user;
    }

    @Override
    public Integer updateUser(User user) {
        return userMapper.updateById(user);
    }

    @Override
    public Integer deleteUser(Long id) {
        return userMapper.deleteById(id);
    }

    @Override
    public Integer addUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public User loginByUsername(LoginParam loginParam) {
        String username = loginParam.getUsername();
        String password = loginParam.getPassword();
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return null;
        }
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getIsDeleted, 0);
        wrapper.eq(User::getUsername, username);
        User user = userMapper.selectOne(wrapper);
        if(user.getPassword().equals(password)){
            user.setPassword("");
            return user;
        }
        return null;
    }

    @Override
    public User register(RegisterParam registerParam) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, registerParam.getUsername());
        User user1 = userMapper.selectOne(wrapper);
        if(user1!=null){
            return null;
        }
        User user = new User();
        user.setUsername(registerParam.getUsername());
        user.setPassword(registerParam.getPassword());
        user.setType(registerParam.getType());
        user.setRegisterTime(System.currentTimeMillis());
        user.setNickname(registerParam.getNickname());
        user.setMail(registerParam.getMail());
        user.setPhoneNumber(registerParam.getPhoneNumber());
        user.setGender(registerParam.getGender());
        user.setAvatar(registerParam.getAvatar());
        user.setMemberRank(0);
        user.setLastLogin(System.currentTimeMillis());
        user.setLastPos("");
        user.setSign(registerParam.getSign());
        user.setIsDeleted(0);
        int insert = userMapper.insert(user);
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username);
        wrapper.eq(User::getIsDeleted, 0);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public List<Permission> getUserPermissions(String username) {
        return permissionMapper.getUserPermission(username);
    }

    @Override
    public String getPWDByUsername(String username) {
        return userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username)).getPassword();
    }
}
