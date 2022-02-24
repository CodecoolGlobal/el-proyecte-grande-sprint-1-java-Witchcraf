package com.codecool.PawPrint.controller;

import com.codecool.PawPrint.model.controllerEntity.SaveSearchEntity;
import com.codecool.PawPrint.model.controllerEntity.SaveServiceEntity;
import com.codecool.PawPrint.model.controllerEntity.SearchService;
import com.codecool.PawPrint.model.entity.Search;
import com.codecool.PawPrint.model.entity.User;
import com.codecool.PawPrint.model.service.ServiceOffered;
import com.codecool.PawPrint.model.service.ServiceSubtype;
import com.codecool.PawPrint.model.service.ServiceType;
import com.codecool.PawPrint.service.ServiceService;
import com.codecool.PawPrint.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class ServiceController {

    private ServiceService serviceService;
    private UserService userService;

    @Autowired
    public ServiceController(ServiceService serviceService, UserService userService) {
        this.serviceService = serviceService;
        this.userService = userService;
    }

    @PostMapping(value = "/search")
    public Set<ServiceOffered> findServices(@RequestBody SearchService searchService) {
        String country = searchService.getCountry();
        String city = searchService.getCity();
        String district = searchService.getDistrict();
        ServiceType serviceType = searchService.getServiceType();
        ServiceSubtype serviceSubtype = searchService.getServiceSubtype();
        boolean isDogOnly = searchService.isDogOnly();
        boolean isCatOnly = searchService.isCatOnly();
        boolean isBothOnly = searchService.isBothOnly();
        boolean isAllDog = searchService.isAllDog();
        boolean isAllCat = searchService.isAllCat();

        if (serviceSubtype == null && district.equals("")) {   // search without serviceSubtype and district
            return serviceService.findServices(country, city, serviceType, isDogOnly, isCatOnly, isBothOnly, isAllDog, isAllCat);
        } else if (serviceSubtype == null && !district.equals("")) {    // search without serviceSubtype
            return serviceService.findServices(country, city, district, serviceType, isDogOnly, isCatOnly, isBothOnly, isAllDog, isAllCat);
        } else if (serviceSubtype != null && district.equals("")) {    // search without district
            return serviceService.findServices(country, city, serviceType, serviceSubtype, isDogOnly, isCatOnly, isBothOnly, isAllDog, isAllCat);
        } else {    // search by all
            return serviceService.findServices(country, city, district, serviceType, serviceSubtype, isDogOnly, isCatOnly, isBothOnly, isAllDog, isAllCat);
        }
    }

    @PostMapping(value = "/search/save")
    @ResponseBody
    public Search saveSearch(@RequestBody SaveSearchEntity saveSearchEntity) {
        System.out.println(saveSearchEntity);
        User user = userService.findUserByName(saveSearchEntity.getUsername());
        Set<ServiceOffered> services = new HashSet<>();
        saveSearchEntity.getSearchedServices().forEach(serviceId -> services.add(serviceService.findServiceById(serviceId)));
        Search search = userService.saveSearch(user,services, saveSearchEntity.getSearchName(), saveSearchEntity.getDescription());
        System.out.println(search);
        return search;
    }

//    @PostMapping(value = "/service/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PostMapping(value = "/service/save")
    @ResponseBody
    public ServiceOffered saveService(@RequestBody SaveServiceEntity saveServiceEntity) {
        System.out.println(saveServiceEntity);
        User user = userService.findUserByName(saveServiceEntity.getUsername());
        ServiceOffered service = serviceService.convertSaveServiceEntityToServiceOffered(saveServiceEntity);
        service.setUser(user);
        ServiceOffered savedService = serviceService.saveService(service);
        System.out.println(savedService);
        return savedService;
    }
}
