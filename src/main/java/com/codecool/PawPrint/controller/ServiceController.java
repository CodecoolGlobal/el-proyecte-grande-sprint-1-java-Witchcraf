package com.codecool.PawPrint.controller;

import com.codecool.PawPrint.model.entity.PetType;
import com.codecool.PawPrint.model.service.ServiceOffered;
import com.codecool.PawPrint.model.service.ServiceSubtype;
import com.codecool.PawPrint.model.service.ServiceType;
import com.codecool.PawPrint.service.ServiceService;
import com.codecool.PawPrint.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/")
public class ServiceController {

    private ServiceService serviceService;
    private UserService userService;

    @Autowired
    public ServiceController(ServiceService serviceService, UserService userService) {
        this.serviceService = serviceService;
        this.userService = userService;
    }

    @GetMapping(value = "/search/{petType}/{country}/{city}/{district}/{serviceType}/{serviceSubtype}/{userId}")
    public Set<ServiceOffered> getServices(@PathVariable PetType petType, @PathVariable String country,
                                           @PathVariable String city, @PathVariable String district,
                                           @PathVariable ServiceType serviceType, @PathVariable ServiceSubtype serviceSubtype,
                                           @PathVariable Optional<Integer> userId) {

        return serviceService.findServices(petType, country, city, district, serviceType, serviceSubtype);

    }

    @PostMapping(value = "/search/save/{userId}")
    public String saveSearch(@PathVariable String userId, @RequestBody Set<ServiceOffered> service) {
        // userService.saveSearch(Integer.parseInt(userId), new Search(service));
        return "redirect:";
    }
}
