package com.codecool.PawPrint.repository;

import com.codecool.PawPrint.model.entity.PetType;
import com.codecool.PawPrint.model.service.ServiceOffered;
import com.codecool.PawPrint.model.service.ServiceSubtype;
import com.codecool.PawPrint.model.service.ServiceType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Qualifier("serviceDaoMem")
public class ServiceDaoMem implements ServiceDao {

    private final Set<ServiceOffered> services = new HashSet<>();


    @Override
    public void add(ServiceOffered service) {
        services.add(service);
    }


    @Override
    public ServiceOffered findByName(String name) {
        return services.stream().filter(service -> service.getName().equals(name)).findFirst().orElse(null);
    }

    // search by all
    @Override
    public Set<ServiceOffered> findServices(Set<PetType> petTypeSet, String country, String city, String district, ServiceType serviceType, ServiceSubtype serviceSubtype) {
        Set<ServiceOffered> foundServices = new HashSet<>();

        for (ServiceOffered serviceOffered : services) {
            if (checkSearchCondition(serviceOffered, petTypeSet, country, city, district, serviceType, serviceSubtype)) {
                foundServices.add(serviceOffered);
            }
        }
        return foundServices;
    }

    // search without serviceSubtype
    @Override
    public Set<ServiceOffered> findServices(Set<PetType> petTypeSet, String country, String city, String district, ServiceType serviceType) {
        Set<ServiceOffered> foundServices = new HashSet<>();

        for (ServiceOffered serviceOffered : services) {
            if (checkSearchCondition(serviceOffered, petTypeSet, country, city, district, serviceType)) {
                foundServices.add(serviceOffered);
            }
        }
        return foundServices;
    }

    // search without district
    @Override
    public Set<ServiceOffered> findServices(Set<PetType> petTypeSet, String country, String city, ServiceType serviceType, ServiceSubtype serviceSubtype) {
        Set<ServiceOffered> foundServices = new HashSet<>();

        for (ServiceOffered serviceOffered : services) {
            if (checkSearchCondition(serviceOffered, petTypeSet, country, city, serviceType, serviceSubtype)) {
                foundServices.add(serviceOffered);
            }
        }
        return foundServices;
    }

    // search without serviceSubtype and district
    @Override
    public Set<ServiceOffered> findServices(Set<PetType> petTypeSet, String country, String city, ServiceType serviceType) {
        Set<ServiceOffered> foundServices = new HashSet<>();

        for (ServiceOffered serviceOffered : services) {
            if (checkSearchCondition(serviceOffered, petTypeSet, country, city, serviceType)) {
                foundServices.add(serviceOffered);
            }
        }
        return foundServices;
    }

    // all
    private boolean checkSearchCondition(ServiceOffered serviceOffered, Set<PetType> petTypeEquivalentSet, String country, String city, String district, ServiceType serviceType, ServiceSubtype serviceSubtype) {
        return serviceOffered.getServiceType().equals(serviceType)
                && serviceOffered.getServiceSubtype().equals(serviceSubtype)
                && petTypeEquivalentSet.contains(serviceOffered.getPetType())
                && serviceOffered.getContact().getAddress().getCountry().equals(country)
                && serviceOffered.getContact().getAddress().getCity().equals(city)
                && serviceOffered.getContact().getAddress().getDistrict().equals(district);
    }

    // without serviceSubtype
    private boolean checkSearchCondition(ServiceOffered serviceOffered, Set<PetType> petTypeEquivalentSet, String country, String city, String district, ServiceType serviceType) {
        return serviceOffered.getServiceType().equals(serviceType)
                && petTypeEquivalentSet.contains(serviceOffered.getPetType())
                && serviceOffered.getContact().getAddress().getCountry().equals(country)
                && serviceOffered.getContact().getAddress().getCity().equals(city)
                && serviceOffered.getContact().getAddress().getDistrict().equals(district);
    }

    // without district
    private boolean checkSearchCondition(ServiceOffered serviceOffered, Set<PetType> petTypeEquivalentSet, String country, String city, ServiceType serviceType, ServiceSubtype serviceSubtype) {
        return serviceOffered.getServiceType().equals(serviceType)
                && serviceOffered.getServiceSubtype().equals(serviceSubtype)
                && petTypeEquivalentSet.contains(serviceOffered.getPetType())
                && serviceOffered.getContact().getAddress().getCountry().equals(country)
                && serviceOffered.getContact().getAddress().getCity().equals(city);
    }

    // without serviceSubtype and district
    private boolean checkSearchCondition(ServiceOffered serviceOffered, Set<PetType> petTypeEquivalentSet, String country, String city, ServiceType serviceType) {
        return serviceOffered.getServiceType().equals(serviceType)
                && petTypeEquivalentSet.contains(serviceOffered.getPetType())
                && serviceOffered.getContact().getAddress().getCountry().equals(country)
                && serviceOffered.getContact().getAddress().getCity().equals(city);
    }

    private boolean checkSearchCondition(ServiceOffered serviceOffered, Set<PetType> petTypeEquivalentSet, String country) {
        return petTypeEquivalentSet.contains(serviceOffered.getPetType())
                && serviceOffered.getContact().getAddress().getCountry().equals(country);
    }

    @Override
    public List<ServiceOffered> getAll() {
        return null;
    }

    @Override
    public ServiceOffered findById(int id) {
        return services.stream().filter(service -> service.getId() == id).findFirst().orElse(null);
    }
}
