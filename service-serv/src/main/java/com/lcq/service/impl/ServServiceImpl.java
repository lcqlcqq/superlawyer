package com.lcq.service.impl;

import com.lcq.dto.Result;
import com.lcq.pojo.Serv;
import com.lcq.mapper.ServMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.lcq.service.ServService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ServServiceImpl implements ServService {
    @Autowired
    ServMapper servMapper;

    @Override
    public List<Serv> getAllService() {
        return servMapper.selectList(null);
    }

    @Override
    public Serv getServiceById(Long id) {
        return servMapper.selectById(id);
    }

    @Override
    public Integer updateService(Serv serv) {
        return servMapper.updateById(serv);
    }

    @Override
    public Integer deleteService(Long id) {
        return servMapper.deleteById(id);
    }

    @Override
    public Integer addService(Serv serv) {
        return servMapper.insert(serv);
    }
}
