package com.lcq.service;

import com.lcq.dto.Result;
import com.lcq.param.AssignParam;
import com.lcq.param.HandleParam;
import com.lcq.param.ProjectParam;
import com.lcq.pojo.Project;

import java.util.List;

public interface ProjectService {
    /**
     * 超级管理员，获取所有项目，包括已删除的
     * @return
     */
    List<Project> getAllProjectsIncludeDeleted();

    /**
     * 管理员，获取所有有效项目
     * @return
     */
    List<Project> getAllProjects();

    /**
     * 管理员、企业，通过项目id获取项目详情
     * @param id
     * @return
     */
    Project getProjectById(Long id);

    /**
     * 管理员，更新项目
     * @param project
     * @return
     */
    Integer updateProject(Project project);

    /**
     * 管理员、企业，删除项目
     * @param id
     * @return
     */
    Integer deleteProject(Long id);

    /**
     * 管理员，添加项目
     * @param project
     * @return
     */
    Integer addProject(Project project);

    /**
     * 管理员、企业，获取对应企业所有的项目
     * @param id
     * @return
     */
    List<Project> getProjectsByCompanyId(String id);
    /**
     * 管理员，获取未分配项目
     * @return
     */
    List<Project> getUnassignedProjects();
    /**
     * 管理员，获取已分配项目
     * @return
     */
    List<Project> getAssignedProjects();
    /**
     * 管理员，获取已结束项目
     * @return
     */
    List<Project> getEndedProjects();

    /**
     * 管理员，分配项目给律师承接
     * @param assignParam
     * @return
     */
    Integer assignToLawyer(AssignParam assignParam);

    /**
     * 律师，同意/不同意承接项目
     * @param handleParam
     * @return
     */
    Integer handle(HandleParam handleParam);

    /**
     * 企业，新建项目
     * @param projectParam
     * @return
     */
    Integer addProject(ProjectParam projectParam);

    /**
     * 律师，获取新分配的项目
     * @return
     * @param id
     */
    List<Project> getNewProjects(String id);

    /**
     * 律师，获取进行中的项目
     * @param id
     * @return
     */
    List<Project> getRunningProjects(String id);

    /**
     * 律师，获取已结束项目
     * @param id
     * @return
     */
    List<Project> getEndProjects(String id);


}
