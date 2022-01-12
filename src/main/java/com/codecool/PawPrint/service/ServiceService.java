package com.codecool.PawPrint.service;

import com.codecool.PawPrint.model.entity.PetType;
import com.codecool.PawPrint.model.service.ServiceSubtype;
import com.codecool.PawPrint.model.service.ServiceOffered;
import com.codecool.PawPrint.model.service.ServiceType;
import com.codecool.PawPrint.repository.ServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ServiceService {

    private ServiceDao serviceDao;

    @Autowired
    public ServiceService(ServiceDao serviceDao) {
        this.serviceDao = serviceDao;
    }

    public void registerService(ServiceOffered service){
        serviceDao.add(service);
    }

    public Set<ServiceOffered> findServices(PetType petType, String country, String city, String district,
                                           ServiceType serviceType, ServiceSubtype serviceSubtype) {
        return serviceDao.findServices(petType, country, city, district, serviceType, serviceSubtype);
    }

    public Set<ServiceOffered> findServices(PetType petType, String country) {
        return serviceDao.findServices(petType, country);
    }

    public Set<ServiceOffered> findServices(String country, String city, String district) {
        return serviceDao.findServices(country, city, district);
    }

    public ServiceOffered getService(String name) {
        return serviceDao.findByName(name);
    }
}
