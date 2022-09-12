package com.lcq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lcq.dto.Result;
import com.lcq.mapper.CompanyVerifyMapper;
import com.lcq.mapper.LawyerVerifyMapper;
import com.lcq.pojo.Company;
import com.lcq.pojo.Lawyer;
import com.lcq.service.VerifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VerifyServiceImpl implements VerifyService {
    @Autowired
    CompanyVerifyMapper companyVerifyMapper;
    @Autowired
    LawyerVerifyMapper lawyerVerifyMapper;

    @Override
    public List<Company> getAllCompanyVerifyInfo() {
        return companyVerifyMapper.selectList(null);
    }

    @Override
    public List<Lawyer> getAllLawyerVerifyInfo() {
        return lawyerVerifyMapper.selectList(null);
    }

    @Override
    public Integer addCompanyVerifyInfo(Company company) {
        return companyVerifyMapper.insert(company);
    }

    @Override
    public Integer addLawyerVerifyInfo(Lawyer lawyer) {
        return lawyerVerifyMapper.insert(lawyer);
    }

    @Override
    public Integer deleteCompanyVerifyInfo(Long id) {
        return companyVerifyMapper.deleteById(id);
    }

    @Override
    public Integer deleteLawyerVerifyInfo(Long id) {
        return lawyerVerifyMapper.deleteById(id);
    }

    @Override
    public Integer updateCompanyVerifyInfo(Company company) {
        return companyVerifyMapper.updateById(company);
    }

    @Override
    public Integer updateLawyerVerifyInfo(Lawyer lawyer) {
        return lawyerVerifyMapper.updateById(lawyer);
    }

    @Override
    public List<Company> getUnAuthCompanyList(int state) {
        LambdaQueryWrapper<Company> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Company::getAuthState, state);
        return companyVerifyMapper.selectList(queryWrapper);
    }

    @Override
    public List<Lawyer> getUnAuthLawyerList(int state) {
        LambdaQueryWrapper<Lawyer> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Lawyer::getAuthState, state);
        return lawyerVerifyMapper.selectList(queryWrapper);
    }

    @Override
    public Company getCompanyInfo(String id) {
        Long userId = Long.parseLong(id);
        LambdaQueryWrapper<Company> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Company::getUserId, userId);
        queryWrapper.eq(Company::getIsDeleted, 0);
        Company company = companyVerifyMapper.selectOne(queryWrapper);
        return company;
    }

    @Override
    public Lawyer getLawyerInfo(String id) {
        Long userId = Long.parseLong(id);
        LambdaQueryWrapper<Lawyer> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Lawyer::getUserId, userId);
        queryWrapper.eq(Lawyer::getIsDeleted, 0);
        return lawyerVerifyMapper.selectOne(queryWrapper);
    }
}