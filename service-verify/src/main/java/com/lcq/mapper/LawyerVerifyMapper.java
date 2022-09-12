package com.lcq.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lcq.pojo.Company;
import com.lcq.pojo.Lawyer;
import org.springframework.stereotype.Repository;

@Repository
public interface LawyerVerifyMapper extends BaseMapper<Lawyer> {

}
