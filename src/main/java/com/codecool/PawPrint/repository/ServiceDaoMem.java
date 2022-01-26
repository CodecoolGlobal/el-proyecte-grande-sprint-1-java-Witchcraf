package com.codecool.PawPrint.repository;

import com.codecool.PawPrint.model.entity.PetType;
import com.codecool.PawPrint.model.service.ServiceOffered;
import com.codecool.PawPrint.model.service.ServiceSubtype;
import com.codecool.PawPrint.model.service.ServiceType;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ServiceDaoMem implements ServiceDao {

    private Set<ServiceOffered> services = new HashSet<>();


    @Override
    public void add(ServiceOffered service) {
        services.add(service);
    }


    @Override
    public ServiceOffered findByName(String name) {
        return services.stream().filter(service -> service.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public List<ServiceOffered> findServices(Set<PetType> petTypeSet, String country, String city, String district, ServiceType serviceType, ServiceSubtype serviceSubtype) {
        List<ServiceOffered> foundServices = new LinkedList<>();

        for (ServiceOffered serviceOffered : services) {
            if (checkSearchCondition(serviceOffered, petTypeSet, country, city,district, serviceType, serviceSubtype)) {
                foundServices.add(serviceOffered);
            }
        }
        return foundServices;
    }

    @Override
    public List<ServiceOffered> findServices(Set<PetType> petTypeSet, String country, String city, String district, ServiceType serviceType) {
        List<ServiceOffered> foundServices = new LinkedList<>();

        for (ServiceOffered serviceOffered : services) {
            if (checkSearchCondition(serviceOffered, petTypeSet, country, city,district, serviceType)) {
                foundServices.add(serviceOffered);
            }
        }
        return foundServices;
    }


    private boolean checkSearchCondition(ServiceOffered serviceOffered, Set<PetType> petTypeEquivalentSet, String country, String city, String district, ServiceType serviceType, ServiceSubtype serviceSubtype) {
        return serviceOffered.getServiceType().equals(serviceType)
                && serviceOffered.getServiceSubtype().equals(serviceSubtype)
                && petTypeEquivalentSet.contains(serviceOffered.getPetType())
                && serviceOffered.getContact().getAddress().getCountry().equals(country)
                && serviceOffered.getContact().getAddress().getCity().equals(city)
                && serviceOffered.getContact().getAddress().getDistrict().equals(district);
    }

    private boolean checkSearchCondition(ServiceOffered serviceOffered, Set<PetType> petTypeEquivalentSet, String country, String city, String district, ServiceType serviceType) {
        return serviceOffered.getServiceType().equals(serviceType)
                && petTypeEquivalentSet.contains(serviceOffered.getPetType())
                && serviceOffered.getContact().getAddress().getCountry().equals(country)
                && serviceOffered.getContact().getAddress().getCity().equals(city)
                && serviceOffered.getContact().getAddress().getDistrict().equals(district);
    }

    private boolean checkSearchCondition(ServiceOffered serviceOffered, Set<PetType> petTypeEquivalentSet, String country) {
        return petTypeEquivalentSet.contains(serviceOffered.getPetType())
                && serviceOffered.getContact().getAddress().getCountry().equals(country);
    }

    @Override
    public List<ServiceOffered> getAll() {
        return null;
    }
}
