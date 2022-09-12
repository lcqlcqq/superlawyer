package com.lcq.service.client;

import com.lcq.pojo.Company;
import com.lcq.pojo.Lawyer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("verifyservice")
public interface VerifyClient {

    @RequestMapping("company/{id}")
    Company getCompanyInfo(@PathVariable("id") String id);

    @RequestMapping("lawyer/{id}")
    Lawyer getLawyerInfo(@PathVariable("id") String id);
}
