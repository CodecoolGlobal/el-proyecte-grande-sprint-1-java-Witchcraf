package com.codecool.PawPrint.controller;

import com.codecool.PawPrint.model.entity.PetType;
import com.codecool.PawPrint.model.service.Service;
import com.codecool.PawPrint.model.service.ServiceType;
import com.codecool.PawPrint.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class ServiceController {

    private ServiceService serviceProvider;

    @Autowired
    public ServiceController(ServiceService serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    @GetMapping(value = "/search/{petType}/{country}/{city}/{district}/{service}/{serviceType}/{userId}")
    public List<Service> getServices(@PathVariable PetType petType, @PathVariable String country,
                                     @PathVariable String city, @PathVariable String district,
                                     @PathVariable String service, @PathVariable ServiceType serviceType,
                                     @PathVariable Optional<Integer> userId) {


        return null;
    }
}
