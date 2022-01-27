package com.codecool.PawPrint.controller;

import com.codecool.PawPrint.model.controllerEntity.SearchService;
import com.codecool.PawPrint.model.service.ServiceOffered;
import com.codecool.PawPrint.model.service.ServiceSubtype;
import com.codecool.PawPrint.model.service.ServiceType;
import com.codecool.PawPrint.service.ServiceService;
import com.codecool.PawPrint.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:3000")
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

        if (serviceSubtype != null && district != null) {   // search without serviceSubtype and district
            return serviceService.findServices(country, city, serviceType, isDogOnly, isCatOnly, isBothOnly, isAllDog, isAllCat);
        } else if (serviceSubtype == null && district != null) {    // search without serviceSubtype
            return serviceService.findServices(country, city, district, serviceType, isDogOnly, isCatOnly, isBothOnly, isAllDog, isAllCat);
        } else if (serviceSubtype != null) {    // search without district
            return serviceService.findServices(country, city, serviceType, serviceSubtype, isDogOnly, isCatOnly, isBothOnly, isAllDog, isAllCat);
        } else {    // search by all
            return serviceService.findServices(country, city, district, serviceType, serviceSubtype, isDogOnly, isCatOnly, isBothOnly, isAllDog, isAllCat);
        }
    }

//    @PostMapping(value = "/search/save")
//    @ResponseBody
//    public String saveSearch(@RequestParam String userId, @RequestBody Set<ServiceOffered> services) {
//        userService.saveSearch(Integer.parseInt(userId), services);
//        return "redirect:";
//    }
}
