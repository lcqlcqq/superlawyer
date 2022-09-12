package com.lcq.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lcq.dto.CompanyInfo;
import com.lcq.dto.LawyerInfo;
import com.lcq.mapper.PermissionMapper;
import com.lcq.mapper.UserMapper;
import com.lcq.param.LoginParam;
import com.lcq.param.RegisterParam;
import com.lcq.pojo.Company;
import com.lcq.pojo.Lawyer;
import com.lcq.pojo.Permission;
import com.lcq.pojo.User;
import com.lcq.service.UserService;
import com.lcq.service.client.VerifyClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Resource
    VerifyClient verifyClient;
    @Resource
    PermissionMapper permissionMapper;
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
    public CompanyInfo getCompanyInfo(String id) {
        //首先获取用户信息，再获取认证信息，返回。
        Long userId = Long.parseLong(id);
        LambdaQueryWrapper<User> userWrapper = new LambdaQueryWrapper<>();
        userWrapper.eq(User::getId, userId);
        userWrapper.eq(User::getIsDeleted,0);

        User company = userMapper.selectOne(userWrapper);
        Company company1 = verifyClient.getCompanyInfo(id);

        CompanyInfo companyInfo = new CompanyInfo();
        companyInfo.setRegisterTime(company.getRegisterTime());
        companyInfo.setNickname(company.getNickname());
        companyInfo.setMail(company.getMail());
        companyInfo.setPhoneNumber(company.getPhoneNumber());
        companyInfo.setAvatar(company.getAvatar());
        companyInfo.setSign(company.getSign());

        companyInfo.setCompanyName(company1.getCompanyName());
        companyInfo.setCompanyType(company1.getCompanyType());
        companyInfo.setCompanySummary(company1.getCompanySummary());
        companyInfo.setCompanyLogo(company1.getCompanyLogo());
        companyInfo.setAddress(company1.getAddress());
        companyInfo.setAuthState(company1.getAuthState());
        companyInfo.setRegisterCapital(company1.getRegisterCapital());
        companyInfo.setLegalRepresentative(company1.getLegalRepresentative());
        return companyInfo;
    }

    @Override
    public List<User> getCompanies() {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getIsDeleted, 0);
        queryWrapper.eq(User::getType, 1);
        return userMapper.selectList(queryWrapper);
    }

    @Override
    public List<User> getLawyers() {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getIsDeleted, 0);
        queryWrapper.eq(User::getType, 2);
        return userMapper.selectList(queryWrapper);
    }

    @Override
    public List<User> getAdmins() {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getIsDeleted, 0);
        queryWrapper.eq(User::getType, 0);
        return userMapper.selectList(queryWrapper);
    }

    @Override
    public LawyerInfo getLawyerInfo(String id) {
        Long lawyerId = Long.parseLong(id);
        LambdaQueryWrapper<User> userWrapper = new LambdaQueryWrapper<>();
        userWrapper.eq(User::getId, lawyerId);
        userWrapper.eq(User::getIsDeleted,0);
        User user = userMapper.selectOne(userWrapper);
        Lawyer lawyer = verifyClient.getLawyerInfo(id);
        LawyerInfo lawyerInfo = new LawyerInfo();
        lawyerInfo.setRegisterTime(user.getRegisterTime());
        lawyerInfo.setNickname(user.getNickname());
        lawyerInfo.setMail(user.getMail());
        lawyerInfo.setPhoneNumber(user.getPhoneNumber());
        lawyerInfo.setAvatar(user.getAvatar());
        lawyerInfo.setSign(user.getSign());

        lawyerInfo.setName(lawyer.getName());
        lawyerInfo.setAuthState(lawyer.getAuthState());
        return lawyerInfo;
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
        String pwd = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username)).getPassword();
        return pwd;
    }
}
