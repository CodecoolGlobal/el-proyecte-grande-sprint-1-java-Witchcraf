package com.codecool.PawPrint.service;

import com.codecool.PawPrint.model.contact.Address;
import com.codecool.PawPrint.model.contact.Contact;
import com.codecool.PawPrint.model.controllerEntity.SaveServiceEntity;
import com.codecool.PawPrint.model.entity.PetType;
import com.codecool.PawPrint.model.service.ServiceSubtype;
import com.codecool.PawPrint.model.service.ServiceOffered;
import com.codecool.PawPrint.model.service.ServiceType;
import com.codecool.PawPrint.repository.ServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Service
public class ServiceService {

    private final ServiceDao serviceDao;

    @Autowired
    public ServiceService(@Qualifier("serviceDaoJPA") ServiceDao serviceDao) {
        this.serviceDao = serviceDao;
    }

    public void registerService(ServiceOffered service){
        serviceDao.add(service);
    }

    private Set<PetType> convertPetType(
            boolean isDogOnly, boolean isCatOnly, boolean isBothOnly,
            boolean isAllDog, boolean isAllCat) {
        Set<PetType> petTypeEquivalentSet = new HashSet<>();
        if (isDogOnly) {
            petTypeEquivalentSet.add(PetType.DOG);
        } else if (isCatOnly) {
            petTypeEquivalentSet.add(PetType.CAT);
        } else if (isAllDog) {
            petTypeEquivalentSet.add(PetType.DOG);
            petTypeEquivalentSet.add(PetType.CATANDDOG);
        } else if (isAllCat) {
            petTypeEquivalentSet.add(PetType.CAT);
            petTypeEquivalentSet.add(PetType.CATANDDOG);
        } else if (isBothOnly) {
            petTypeEquivalentSet.add(PetType.CATANDDOG);
        } else {
            petTypeEquivalentSet.add(PetType.CAT);
            petTypeEquivalentSet.add(PetType.DOG);
            petTypeEquivalentSet.add(PetType.CATANDDOG);
        }
        return petTypeEquivalentSet;
    }

    // search by all
    public Set<ServiceOffered> findServices(String country, String city, String district,
                                             ServiceType serviceType, ServiceSubtype serviceSubtype,
                                             boolean isDogOnly, boolean isCatOnly, boolean isBothOnly,
                                             boolean isAllDog, boolean isAllCat) {
        Set<PetType> petTypeEquivalentSet = convertPetType(isDogOnly, isCatOnly, isBothOnly, isAllDog, isAllCat);
        return serviceDao.findServices(petTypeEquivalentSet, country, city, district, serviceType, serviceSubtype);
    }

    // search without serviceSubtype
    public Set<ServiceOffered> findServices(String country, String city, String district,
                                            ServiceType serviceType, boolean isDogOnly, boolean isCatOnly,
                                            boolean isBothOnly, boolean isAllDog, boolean isAllCat) {
        Set<PetType> petTypeEquivalentSet = convertPetType(isDogOnly, isCatOnly, isBothOnly, isAllDog, isAllCat);
        return serviceDao.findServices(petTypeEquivalentSet, country, city, district, serviceType);
    }

    // search without district
    public Set<ServiceOffered> findServices(String country, String city,
                                            ServiceType serviceType, ServiceSubtype serviceSubtype,
                                            boolean isDogOnly, boolean isCatOnly, boolean isBothOnly,
                                            boolean isAllDog, boolean isAllCat) {
        Set<PetType> petTypeEquivalentSet = convertPetType(isDogOnly, isCatOnly, isBothOnly, isAllDog, isAllCat);
        return serviceDao.findServices(petTypeEquivalentSet, country, city, serviceType, serviceSubtype);
    }

    // search without serviceSubtype and district
    public Set<ServiceOffered> findServices(String country, String city,
                                            ServiceType serviceType, boolean isDogOnly, boolean isCatOnly,
                                            boolean isBothOnly, boolean isAllDog, boolean isAllCat) {
        Set<PetType> petTypeEquivalentSet = convertPetType(isDogOnly, isCatOnly, isBothOnly, isAllDog, isAllCat);
        return serviceDao.findServices(petTypeEquivalentSet, country, city, serviceType);
    }

    public ServiceOffered getService(String name) {
        return serviceDao.findByName(name);
    }

    public ServiceOffered findServiceById(int id) {
        return serviceDao.findById(id);
    }

    public ServiceOffered convertSaveServiceEntityToServiceOffered(SaveServiceEntity saveServiceEntity) {
        Address address = new Address();
        address.setBell(saveServiceEntity.getBell());
        address.setCity(saveServiceEntity.getCity());
        address.setCountry(saveServiceEntity.getCountry());
        address.setDistrict(saveServiceEntity.getDistrict());
        address.setDoor(saveServiceEntity.getDoor());
        address.setNumber(saveServiceEntity.getNumber());
        address.setFloor(saveServiceEntity.getFloor());
        address.setStreet(saveServiceEntity.getStreet());

        Contact contact = new Contact();
        contact.setAddress(address);
        contact.setEmail(saveServiceEntity.getEmail());
        contact.setPhone(saveServiceEntity.getPhone());

        ServiceOffered service = new ServiceOffered();
        service.setContact(contact);
        service.setServiceType(saveServiceEntity.getServiceType());
        service.setServiceSubtype(saveServiceEntity.getServiceSubtype());
        service.setDescription(saveServiceEntity.getDescription());
        service.setHomepage(saveServiceEntity.getHomepage());
//        // convert incoming Multipart file into byte array
//        MultipartFile file = saveServiceEntity.getImage();
//        service.setImage(file.getBytes());
        service.setName(saveServiceEntity.getServiceName());
        service.setPetType(saveServiceEntity.getPetType());
        service.setOpeningHours(saveServiceEntity.getOpeningHours());

        return service;
    }

    public ServiceOffered saveService(ServiceOffered service) {
        serviceDao.add(service);
        return service;
    }
}
