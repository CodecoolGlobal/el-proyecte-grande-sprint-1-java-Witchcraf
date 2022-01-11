package com.codecool.PawPrint.controller;

import com.codecool.PawPrint.model.entity.PetType;
import com.codecool.PawPrint.model.service.ServiceOffered;
import com.codecool.PawPrint.model.service.ServiceSubtype;
import com.codecool.PawPrint.model.service.ServiceType;
import com.codecool.PawPrint.service.ServiceService;
import com.codecool.PawPrint.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/search")
    @ResponseBody
    public Set<ServiceOffered> getServices(@RequestParam PetType petType, @RequestParam String country,
                                           @RequestParam String city, @RequestParam String district,
                                           @RequestParam ServiceType serviceType, @RequestParam(required = false) ServiceSubtype serviceSubtype,
                                           @RequestParam(required = false) int userId) {

        return serviceService.findServices(petType, country, city, district, serviceType, serviceSubtype);

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
