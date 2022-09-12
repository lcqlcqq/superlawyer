package com.lcq.service.client;

import com.lcq.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "userservice")
public interface UserClient {
    @RequestMapping("/user/{id}")
    User getUserById(@PathVariable("id") String id);
}