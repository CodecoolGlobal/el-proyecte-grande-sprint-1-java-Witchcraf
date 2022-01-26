package com.codecool.PawPrint.repository;

import com.codecool.PawPrint.model.entity.PetType;
import com.codecool.PawPrint.model.service.ServiceOffered;
import com.codecool.PawPrint.model.service.ServiceSubtype;
import com.codecool.PawPrint.model.service.ServiceType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

public class ServiceDaoJPA implements ServiceDao {

    private ServiceRepository serviceRepository;

    @Autowired
    public ServiceDaoJPA(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public void add(ServiceOffered service) {
        serviceRepository.save(service);
    }

    @Override
    public ServiceOffered findByName(String name) {
        return serviceRepository.findByName(name);
    }

    @Override
    public List<ServiceOffered> findServices(Set<PetType> petTypeSet, String country, String city, String district, ServiceType service, ServiceSubtype serviceType) {
        return null;
    }

    @Override
    public List<ServiceOffered> findServices(Set<PetType> petTypeSet, String country, String city, String district, ServiceType service) {
        return null;
    }

    @Override
    public List<ServiceOffered> getAll() {
        return serviceRepository.findAll();
    }
}
