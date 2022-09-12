package com.lcq.service;

import com.lcq.dto.Result;
import com.lcq.pojo.Serv;

import java.util.List;

public interface ServService {
    List<Serv> getAllService();
    Serv getServiceById(Long id);
    Integer updateService(Serv serv);
    Integer deleteService(Long id);
    Integer addService(Serv serv);
}
