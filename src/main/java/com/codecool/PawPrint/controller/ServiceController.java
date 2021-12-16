package com.codecool.PawPrint.controller;

import com.codecool.PawPrint.model.entity.PetType;
import com.codecool.PawPrint.model.service.Service;
import com.codecool.PawPrint.model.service.ServiceType;
import com.codecool.PawPrint.service.ServiceService;
import com.codecool.PawPrint.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping(value = "/search/{petType}/{country}/{city}/{district}/{service}/{serviceType}/{userId}")
    public Set<Service> getServices(@PathVariable PetType petType, @PathVariable String country,
                                     @PathVariable String city, @PathVariable String district,
                                     @PathVariable String service, @PathVariable ServiceType serviceType,
                                     @PathVariable Optional<Integer> userId) {


        return null;
    }

    @PostMapping(value = "/search/save/{userId}")
    public String saveSearch(@PathVariable String userId, @RequestBody Set<Service> service) {
        // userService.saveSearch(Integer.parseInt(userId), new Search(service));
        return "redirect:";
    }
}
