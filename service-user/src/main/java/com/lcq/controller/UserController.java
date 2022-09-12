package com.lcq.controller;

import com.lcq.dto.ErrorCode;
import com.lcq.dto.Result;
import com.lcq.param.LoginParam;
import com.lcq.param.RegisterParam;
import com.lcq.pojo.Permission;
import com.lcq.pojo.User;
import com.lcq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("login")
    public Result loginByAccount(@RequestBody LoginParam loginParam){
        User user = userService.loginByUsername(loginParam);
        return user == null ? Result.fail(ErrorCode.PARAMS_ERROR) : Result.success(user);
    }
    @PostMapping("register")
    public Result register(@RequestBody RegisterParam registerParam){
        User user = userService.register(registerParam);
        return user == null ? Result.fail(ErrorCode.ACCOUNT_EXIST) : Result.success(user);
    }

    /**
     * openfeign用
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public User getUserById(@PathVariable("id") String id){
        return userService.getUserById(Long.parseLong(id));
    }

    @GetMapping("user/{username}")
    public Result getUserByUsername(@PathVariable("username") String username){
        return Result.success(userService.getUserByUsername(username));
    }
    @GetMapping("pwd/{username}")
    public String getPWDByUsername(@PathVariable("username") String username){
        return userService.getPWDByUsername(username);
    }
    @GetMapping("pms/{username}")
    public List<Permission> getUserPMS(@PathVariable("username") String username){
        return userService.getUserPermissions(username);
    }

    @GetMapping("info/{id}")
    public Result getUser(@PathVariable("id") String id){
        return Result.success(userService.getUserById(Long.parseLong(id)));
    }
    @GetMapping("admins")
    public Result getAdmins(){
        return Result.success(userService.getAdmins());
    }

    /**
     * 律师端获取企业信息
     * @param id
     * @return
     */
    @GetMapping("company/info/{id}")
    public Result getCompanyInfo(@PathVariable("id") String id){
        return Result.success(userService.getCompanyInfo(id));
    }

    @GetMapping("companies")
    public Result getCompanies(){
        return Result.success(userService.getCompanies());
    }

    /**
     * 企业端获取律师信息
     * @param id
     * @return
     */
    @GetMapping("lawyer/info/{id}")
    public Result getLawyerInfo(@PathVariable("id") String id){
        return Result.success(userService.getLawyerInfo(id));
    }

    @GetMapping("lawyers")
    public Result getLawyers(){
        return Result.success(userService.getLawyers());
    }
}
