package com.codecool.PawPrint.repository;

import com.codecool.PawPrint.model.entity.PetType;
import com.codecool.PawPrint.model.service.ServiceOffered;
import com.codecool.PawPrint.model.service.ServiceSubtype;
import com.codecool.PawPrint.model.service.ServiceType;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class ServiceDaoMem implements ServiceDao {

    private Set<ServiceOffered> services = new HashSet<>();


    @Override
    public void add(ServiceOffered service) {
        services.add(service);
    }

    @Override
    public ServiceOffered findById(int id) {
        return null;
    }

    @Override
    public ServiceOffered findByName(String name) {
        return services.stream().filter(service -> service.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public Set<ServiceOffered> findServices(PetType petType, String country, String city, String district, ServiceType serviceType, ServiceSubtype serviceSubtype) {
        Set<ServiceOffered> foundServices = new HashSet<>();
        Set<PetType> petTypeEquivalentSet = convertPetType(petType);

        for (ServiceOffered serviceOffered : services) {
            if (checkSearchCondition(serviceOffered, petTypeEquivalentSet, country, city,district, serviceType, serviceSubtype)) {
                foundServices.add(serviceOffered);
            }
        }
        return foundServices;
    }

    @Override
    public Set<ServiceOffered> findServices(PetType petType, String country, String city, String district, ServiceType serviceType) {
        Set<ServiceOffered> foundServices = new HashSet<>();
        Set<PetType> petTypeEquivalentSet = convertPetType(petType);

        for (ServiceOffered serviceOffered : services) {
            if (checkSearchCondition(serviceOffered, petTypeEquivalentSet, country, city,district, serviceType)) {
                foundServices.add(serviceOffered);
            }
        }
        return foundServices;
    }

    @Override
    public Set<ServiceOffered> findServices(PetType petType, String country) {
        Set<ServiceOffered> foundServices = new HashSet<>();
        Set<PetType> petTypeEquivalentSet = convertPetType(petType);

        for (ServiceOffered serviceOffered : services) {
            if (checkSearchCondition(serviceOffered, petTypeEquivalentSet, country)) {
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

    private Set<PetType> convertPetType(PetType petType) {
        Set<PetType> petTypeEquivalentSet = new HashSet<>();
        if (petType == null) {
            petTypeEquivalentSet.add(PetType.CAT);
            petTypeEquivalentSet.add(PetType.DOG);
            petTypeEquivalentSet.add(PetType.CATANDDOG);
        } else {
            switch (petType) {
                case CAT:
                    petTypeEquivalentSet.add(PetType.CAT);
                    petTypeEquivalentSet.add(PetType.CATANDDOG);
                    break;
                case DOG:
                    petTypeEquivalentSet.add(PetType.DOG);
                    petTypeEquivalentSet.add(PetType.CATANDDOG);
                    break;
                case CATANDDOG:
                case NONE:
                    petTypeEquivalentSet.add(PetType.CAT);
                    petTypeEquivalentSet.add(PetType.DOG);
                    petTypeEquivalentSet.add(PetType.CATANDDOG);
                    break;
            }
        }
        return petTypeEquivalentSet;
    }

    @Override
    public Set<ServiceOffered> getAll() {
        return null;
    }
}
