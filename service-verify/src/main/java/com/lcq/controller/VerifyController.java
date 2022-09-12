package com.lcq.controller;

import com.lcq.pojo.Company;
import com.lcq.pojo.Lawyer;
import com.lcq.service.VerifyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("verify")
public class VerifyController {

    @Resource
    VerifyService verifyService;

    /**
     * 给openfeign调用的，获取企业信息
     * @param id
     * @return
     */
    @GetMapping("company/{id}")
    public Company getCompanyInfo(@PathVariable("id") String id){
        return verifyService.getCompanyInfo(id);
    }

    /**
     * 给openfeign调用的。获取律师信息
     * @param id
     * @return
     */
    @GetMapping("lawyer/{id}")
    Lawyer getLawyerInfo(@PathVariable("id") String id){
        return verifyService.getLawyerInfo(id);
    }
}
