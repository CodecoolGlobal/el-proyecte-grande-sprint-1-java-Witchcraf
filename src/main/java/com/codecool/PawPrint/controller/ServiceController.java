package com.codecool.PawPrint.controller;

import com.codecool.PawPrint.model.controllerEntity.SearchService;
import com.codecool.PawPrint.model.entity.PetType;
import com.codecool.PawPrint.model.service.ServiceOffered;
import com.codecool.PawPrint.model.service.ServiceSubtype;
import com.codecool.PawPrint.model.service.ServiceType;
import com.codecool.PawPrint.service.ServiceService;
import com.codecool.PawPrint.service.UserService;
import com.codecool.PawPrint.util.JsonResponseConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
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

    @GetMapping(value="/ser", produces = "application/json")
    @ResponseBody
    public ServiceOffered getService(@RequestParam String country) {
        ServiceOffered off = serviceService.getService(country);
        return off;
    }

    @PostMapping(value="/searchTest", produces = "application/json")
    @ResponseBody
    public Set<ServiceOffered> getServicesBy3field(@RequestBody Map<String, String> payload) {
        PetType petType = JsonResponseConverter.getPetTypeFromJsonString(payload.get("petType"));
        ServiceType serviceType = JsonResponseConverter.getServiceTypeFromJsonString(payload.get("serviceType"));
        String country = payload.get("country");
        String region = payload.get("region");
        String district = payload.get("district");

        System.out.println(payload);
        Set<ServiceOffered> result = serviceService.findServices(petType, country, region, district, serviceType);
        System.out.println(result);
        return result;
    }


    @PostMapping(value = "/search")
    public Set<ServiceOffered> getServices(@RequestBody SearchService searchService) {

        PetType petType = searchService.getPetType();
        String country = searchService.getCountry();
        String city = searchService.getCity();
        String district = searchService.getDistrict();
        ServiceType serviceType = searchService.getServiceType();
        ServiceSubtype serviceSubtype = searchService.getServiceSubtype();

        if (serviceSubtype != null) {
            return serviceService.findServices(petType, country, city, district, serviceType, serviceSubtype);
        }
        return serviceService.findServices(petType, country, city, district, serviceType);

    }


    @PostMapping(value = "/search/save")
    @ResponseBody
    public String saveSearch(@RequestParam String userId, @RequestBody Set<ServiceOffered> services) {
        userService.saveSearch(Integer.parseInt(userId), services);
        return "redirect:";
    }
}
