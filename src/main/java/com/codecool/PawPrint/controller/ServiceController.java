package com.codecool.PawPrint.controller;

import com.codecool.PawPrint.filter.TokenAuthenticationFilter;
import com.codecool.PawPrint.model.controllerEntity.SaveSearchEntity;
import com.codecool.PawPrint.model.controllerEntity.SaveServiceEntity;
import com.codecool.PawPrint.model.controllerEntity.SearchServiceEntity;
import com.codecool.PawPrint.model.entity.Search;
import com.codecool.PawPrint.model.entity.User;
import com.codecool.PawPrint.model.service.ServiceOffered;
import com.codecool.PawPrint.model.service.ServiceSubtype;
import com.codecool.PawPrint.model.service.ServiceType;
import com.codecool.PawPrint.service.SearchService;
import com.codecool.PawPrint.service.ServiceService;
import com.codecool.PawPrint.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class ServiceController {

    private ServiceService serviceService;
    private UserService userService;
    private TokenAuthenticationFilter tokenAuthenticationFilter;
    private SearchService searchService;

    @Autowired
    public ServiceController(ServiceService serviceService, UserService userService, TokenAuthenticationFilter tokenAuthenticationFilter, SearchService searchService) {
        this.serviceService = serviceService;
        this.userService = userService;
        this.tokenAuthenticationFilter = tokenAuthenticationFilter;
        this.searchService = searchService;
    }

    @PostMapping(value = "/search")
    public Set<ServiceOffered> findServices(@RequestBody SearchServiceEntity searchService) {
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
        User user = userService.findUserByName(saveServiceEntity.getUsername());
        ServiceOffered service = serviceService.convertSaveServiceEntityToServiceOffered(saveServiceEntity);
        service.setUser(user);
        ServiceOffered savedService = serviceService.saveService(service);
        return savedService;
    }

    @GetMapping("/getSearch/{searchId}")
    public Search getSearch(HttpServletRequest httpRequest, @PathVariable int searchId) throws Exception {
        User user = userService.findUserByName(tokenAuthenticationFilter.tokenVerification(httpRequest));
        System.out.println(user);
        Search search = searchService.getSearchById(searchId);
        System.out.println(search);
        return search;
    }
}
