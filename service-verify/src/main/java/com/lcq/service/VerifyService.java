package com.lcq.service;

import com.lcq.dto.Result;
import com.lcq.pojo.Company;
import com.lcq.pojo.Lawyer;

import java.util.List;

public interface VerifyService {

    /**
     * 获取所有企业认证信息
     * @return
     */
    List<Company> getAllCompanyVerifyInfo();

    /**
     * 获取所有律师认证信息
     * @return
     */
    List<Lawyer> getAllLawyerVerifyInfo();

    /**
     * 添加企业认证信息
     * @param company
     * @return
     */
    Integer addCompanyVerifyInfo(Company company);

    /**
     * 添加律师认证信息
     * @param lawyer
     * @return
     */
    Integer addLawyerVerifyInfo(Lawyer lawyer);

    /**
     * 删除企业认证
     * @param id
     * @return
     */
    Integer deleteCompanyVerifyInfo(Long id);

    /**
     * 删除律师认证
     * @param id
     * @return
     */
    Integer deleteLawyerVerifyInfo(Long id);

    /**
     * 更新企业认证信息
     * @param company
     * @return
     */
    Integer updateCompanyVerifyInfo(Company company);

    /**
     * 更新律师认证信息
     * @param lawyer
     * @return
     */
    Integer updateLawyerVerifyInfo(Lawyer lawyer);

    /**
     * 获取认证未审核或已审核的企业
     * 0未审核通过，1已审核认证
     */
    List<Company> getUnAuthCompanyList(int state);
    /**
     * 获取认证未审核或已审核的律师
     * 0未审核通过，1已审核认证
     */
    List<Lawyer> getUnAuthLawyerList(int state);

    /**
     * 获取企业信息
     * @return
     */
    Company getCompanyInfo(String id);

    /**
     * 获取律师信息
     * @param id
     * @return
     */
    Lawyer getLawyerInfo(String id);
}
