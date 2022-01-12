package com.codecool.PawPrint.controller;

import com.codecool.PawPrint.model.entity.PetType;
import com.codecool.PawPrint.model.entity.Search;
import com.codecool.PawPrint.model.service.ServiceOffered;
import com.codecool.PawPrint.model.service.ServiceSubtype;
import com.codecool.PawPrint.model.service.ServiceType;
import com.codecool.PawPrint.service.ServiceService;
import com.codecool.PawPrint.service.UserService;
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

    @PostMapping(value="/sertest", produces = "application/json")
    @ResponseBody
    public Set<ServiceOffered> getServicesBy3field(@RequestBody Map<String, String> payload) {
        String country = payload.get("country");
        String region = payload.get("region");
        String district = payload.get("district");
        String serviceType = payload.get("serviceType");
        String serviceSubType = payload.get("serviceSubType");
        String petType = payload.get("petType");
        System.out.println(payload);
        System.out.println(serviceType);
        System.out.println(serviceSubType);
        System.out.println(petType);

        Set<ServiceOffered> off = serviceService.findServices(country, region, district);
        return off;
    }


    @PostMapping(value = "/search")
    public Set<ServiceOffered> getServices(@RequestParam PetType petType, @RequestParam String country,
                                           @RequestParam String city, @RequestParam String district,
                                           @RequestParam ServiceType serviceType, @RequestParam(required = false) ServiceSubtype serviceSubtype,
                                           @RequestParam(required = false) Integer userId) {

        if (serviceSubtype != null) {
            return serviceService.findServices(petType, country, city, district, serviceType, serviceSubtype);
        }
        return serviceService.findServices(petType, country, city, district, serviceType);

    }

    @GetMapping(value = "/searchT")
    @ResponseBody
    public Set<ServiceOffered> getServices(@RequestParam PetType petType, @RequestParam String country) {

        return serviceService.findServices(petType, country);

    }

    @PostMapping(value = "/search/save")
    @ResponseBody
    public String saveSearch(@RequestParam String userId, @RequestBody Set<ServiceOffered> services) {
        userService.saveSearch(Integer.parseInt(userId), services);
        return "redirect:";
    }
}
