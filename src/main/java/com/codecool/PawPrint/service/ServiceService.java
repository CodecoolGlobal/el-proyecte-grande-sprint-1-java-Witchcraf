package com.codecool.PawPrint.service;

import com.codecool.PawPrint.repository.ServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceService {

    private ServiceDao serviceDao;

    @Autowired
    public ServiceService(ServiceDao serviceDao) {
        this.serviceDao = serviceDao;
    }
}
