package com.lcq.controller;

import com.lcq.dto.Result;
import com.lcq.pojo.Serv;
import com.lcq.service.ServService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("service")
public class ServController {
    @Autowired
    ServService servService;

    @GetMapping("all")
    public Result getAllService(){
        return Result.success(servService.getAllService());
    }
    @PostMapping("{id}")
    public Result getServiceById(@PathVariable("id") Long id){
        return Result.success(servService.getServiceById(id));
    }
    @PostMapping("update")
    public Result updateService(@RequestBody Serv serv){
        return Result.success(servService.updateService(serv));
    }
    @PostMapping("del/{id}")
    public Result deleteService(@PathVariable("id") Long id){
        return Result.success(servService.deleteService(id));
    }
    @PostMapping("add")
    public Result addService(@RequestBody Serv serv){
        return Result.success(servService.addService(serv));
    }
}
