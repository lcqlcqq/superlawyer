package com.lcq.controller;

import com.lcq.dto.ErrorCode;
import com.lcq.dto.Result;
import com.lcq.param.AssignParam;
import com.lcq.param.HandleParam;
import com.lcq.param.ProjectParam;
import com.lcq.service.ProjectService;
import com.lcq.service.client.UserClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("project")
public class ProjectController {
    @Resource
    ProjectService projectService;

    @Resource
    UserClient userClient;
    /**
     * 获取所有项目
     * @return
     */
    @GetMapping("s")
    public Result getAllProjects(){
        //简易实现并行任务
        //CompletableFuture.allOf(
        //        CompletableFuture.runAsync(()->{
        //            //任务1
        //        }),
        //        CompletableFuture.runAsync(()->{
        //            //任务2
        //        }),
        //        CompletableFuture.runAsync(()->{
        //            //任务3
        //        })
        //).join();
        return Result.success(projectService.getAllProjects());
    }

    /**
     * 获取企业id对应的项目
     * @param id
     * @return
     */
    @GetMapping("s/{id}")
    public Result getEnterpriseProjects(@PathVariable("id") String id){
        return Result.success(projectService.getProjectsByCompanyId(id));
    }
    /**
     * 获取项目信息
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public Result getProjectById(@PathVariable("id") String id){
        return Result.success(projectService.getProjectById(Long.parseLong(id)));
    }

    /**
     * 获取未分配项目
     * @return
     */
    @GetMapping("unassigned")
    public Result getUnassignedProjects(){
        return Result.success(projectService.getUnassignedProjects());
    }

    /**
     * 给律师分配项目
     * @param assignParam
     * @return
     */
    @PostMapping("assign")
    public Result assign(@RequestBody AssignParam assignParam){
        Integer update = projectService.assignToLawyer(assignParam);
        if(update==-1){
            return Result.fail(ErrorCode.PARAMS_ERROR);
        }
        else if(update==-2){
            return Result.fail(ErrorCode.PROJECT_OR_LAWYER_NOT_EXIST);
        }
        else if(update==-3){
            return Result.fail(ErrorCode.PROJECT_ALREADY_ASSIGN);
        }
        return Result.success(update);
    }

    /**
     * 律师（同意：1 不同意：0 ）承接项目
     * @param handleParam
     * @return
     */
    @PostMapping("handle")
    public Result handle(@RequestBody HandleParam handleParam){
        return Result.success(projectService.handle(handleParam));
    }

    @PostMapping("add")
    public Result createNewProject(@RequestBody ProjectParam projectParam){
        return Result.success(projectService.addProject(projectParam));
    }
    @PostMapping("del/{id}")
    public Result deleteProject(@PathVariable("id") String id){
        return Result.success(projectService.deleteProject(Long.parseLong(id)));
    }
    @PostMapping("new/{id}")
    public Result getNewProject(@PathVariable("id") String id){
        return Result.success(projectService.getNewProjects(id));
    }
    @PostMapping("running/{id}")
    public Result getRunningProject(@PathVariable("id") String id){
        return Result.success(projectService.getRunningProjects(id));
    }
    @PostMapping("end/{id}")
    public Result getEndProject(@PathVariable("id") String id){
        return Result.success(projectService.getEndProjects(id));
    }
    @GetMapping("test")
    public Result testRPC(){
        return Result.success(userClient.getUserById("1"));
    }
}
