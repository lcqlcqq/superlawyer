package com.lcq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.lcq.mapper.ProjectMapper;
import com.lcq.param.AssignParam;
import com.lcq.param.HandleParam;
import com.lcq.param.ProjectParam;
import com.lcq.pojo.Project;
import com.lcq.pojo.User;
import com.lcq.service.ProjectService;
import com.lcq.service.client.UserClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectMapper projectMapper;
    @Resource
    UserClient userClient;

    @Override
    public List<Project> getAllProjectsIncludeDeleted() {
        return projectMapper.selectList(null);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectMapper.selectList(new LambdaQueryWrapper<Project>().eq(Project::getIsDeleted,0));
    }

    @Override
    public Project getProjectById(Long id) {
        LambdaQueryWrapper<Project> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Project::getIsDeleted, 0);
        wrapper.eq(Project::getId, id);
        Project project = projectMapper.selectOne(wrapper);
        return project;
    }

    @Override
    public Integer updateProject(Project project) {
        return projectMapper.updateById(project);
    }

    @Override
    public Integer deleteProject(Long id) {
        LambdaUpdateWrapper<Project> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Project::getId, id);
        wrapper.set(Project::getIsDeleted, 1);
        return projectMapper.update(null,wrapper);
    }

    @Override
    public Integer addProject(Project project) {
        return projectMapper.insert(project);
    }

    @Override
    public List<Project> getProjectsByCompanyId(String id) {
        Long companyId = Long.parseLong(id);
        LambdaQueryWrapper<Project> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Project::getCompanyId, companyId);
        wrapper.eq(Project::getIsDeleted, 0);
        return projectMapper.selectList(wrapper);
    }

    @Override
    public List<Project> getUnassignedProjects() {
        LambdaQueryWrapper<Project> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Project::getIsFinished, 0);
        queryWrapper.eq(Project::getIsDeleted, 0);
        return projectMapper.selectList(queryWrapper);
    }

    @Override
    public List<Project> getAssignedProjects() {
        LambdaQueryWrapper<Project> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Project::getIsFinished, 1);
        queryWrapper.eq(Project::getIsDeleted, 0);
        return projectMapper.selectList(queryWrapper);
    }

    @Override
    public List<Project> getEndedProjects() {
        LambdaQueryWrapper<Project> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Project::getIsFinished, 2);
        queryWrapper.eq(Project::getIsDeleted, 0);
        return projectMapper.selectList(queryWrapper);
    }

    @Override
    public Integer assignToLawyer(AssignParam assignParam) {
        if(StringUtils.isBlank(assignParam.getLawyerId()) || StringUtils.isBlank(assignParam.getProjectId())){
            return -1;
        }
        Long lawyerId = Long.parseLong(assignParam.getLawyerId());
        Long projectId = Long.parseLong(assignParam.getProjectId());

        LambdaQueryWrapper<Project> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Project::getId,projectId);
        queryWrapper.eq(Project::getIsDeleted, 0);
        Project project = projectMapper.selectOne(queryWrapper);

        User lawyer = userClient.getUserById(assignParam.getLawyerId());

        if(project == null || lawyer == null){
            return -2;
        }
        if(project.getIsFinished() == 1){
            return -3;
        }

        LambdaUpdateWrapper<Project> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Project::getId, projectId);
        wrapper.set(Project::getLawyerId, lawyerId);
        int update = projectMapper.update(null, wrapper);//第一个参数一定要设置null，这样就只会更新set的字段
        return update;
    }

    @Override
    public Integer handle(HandleParam handleParam) {
        String projectId = handleParam.getProjectId();
        Integer handleResult = handleParam.getHandleResult();
        int update=0;
        if(handleResult == 1){  //承接
            LambdaUpdateWrapper<Project> wrapper = new LambdaUpdateWrapper<>();
            wrapper.set(Project::getIsFinished, 1);  //改成已分配状态
            wrapper.eq(Project::getId, projectId);
            wrapper.eq(Project::getIsDeleted, 0);
            update = projectMapper.update(null, wrapper);
        }else if(handleResult == 0){
            LambdaUpdateWrapper<Project> wrapper = new LambdaUpdateWrapper<>();
            wrapper.set(Project::getLawyerId, 0);  //律师id改成 0，项目再次变成未分配状态
            wrapper.eq(Project::getId, projectId);
            wrapper.eq(Project::getIsDeleted, 0);
            update = projectMapper.update(null, wrapper);
        }
        return update;
    }

    @Override
    public Integer addProject(ProjectParam projectParam) {
        Project project = new Project();
        project.setProjectName(projectParam.getProjectName());
        project.setProjectType(projectParam.getProjectType());
        project.setCompanyId(Long.parseLong(projectParam.getCompanyId()));
        project.setServiceId(Long.parseLong(projectParam.getServiceId()));
        project.setLawyerId(0L);
        project.setContent(projectParam.getContent());
        project.setEndingTime(Long.parseLong(projectParam.getEndingTime()));  //项目结束时间

        project.setCreateTime(System.currentTimeMillis());  //创建时间
        project.setIsPaid(0);
        project.setIsFinished(0);
        project.setIsDeleted(0);
        int insert = projectMapper.insert(project);
        return insert;
    }

    @Override
    public List<Project> getNewProjects(String id) {
        User user = userClient.getUserById(id);
        if(user.getType() == 1){
            return null;
        }
        Long lawyerId = user.getId();
        LambdaQueryWrapper<Project> wrapper = new LambdaQueryWrapper<>();
        wrapper.gt(Project::getLawyerId, lawyerId);  //新分配的，律师还没确认承接
        wrapper.eq(Project::getIsFinished, 0);  //0是未确定分配，待律师确认
        wrapper.eq(Project::getIsDeleted, 0);
        List<Project> list = projectMapper.selectList(wrapper);
        return list;
    }
    @Override
    public List<Project> getRunningProjects(String id) {
        User user = userClient.getUserById(id);
        if(user.getType() == 1){
            return null;
        }
        Long lawyerId = user.getId();
        LambdaQueryWrapper<Project> wrapper = new LambdaQueryWrapper<>();
        wrapper.gt(Project::getLawyerId, lawyerId);
        wrapper.eq(Project::getIsFinished, 1);  //1是已分配的进行中的项目
        wrapper.eq(Project::getIsDeleted, 0);
        List<Project> list = projectMapper.selectList(wrapper);
        return list;
    }
    @Override
    public List<Project> getEndProjects(String id) {
        User user = userClient.getUserById(id);
        if(user.getType() == 1){
            return null;
        }
        Long lawyerId = user.getId();
        LambdaQueryWrapper<Project> wrapper = new LambdaQueryWrapper<>();
        wrapper.gt(Project::getLawyerId, lawyerId);
        wrapper.eq(Project::getIsFinished, 2);  //2是已结束的项目
        wrapper.eq(Project::getIsDeleted, 0);
        List<Project> list = projectMapper.selectList(wrapper);
        return list;
    }
}
