package com.lcq.service.client;

import com.lcq.pojo.Permission;
import com.lcq.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("userservice")
public interface UserClient {
    @RequestMapping("/user/{id}")
    User getUserById(@PathVariable("id") String id);

    @GetMapping("/user/user/{username}")
    User getUserByUsername(@PathVariable("username") String username);

    @GetMapping("/user/pwd/{username}")
    String getPWDByUsername(@PathVariable("username") String username);

    @RequestMapping("/user/pms/{username}")
    List<Permission> getUserPMS(@PathVariable("username") String username);
}