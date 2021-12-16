package com.codecool.PawPrint.model.entity;

import com.codecool.PawPrint.model.service.Service;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Search {

    private int id;
    private String name = "MySearch";
    private Set<Service> searchedServices = new HashSet<>();

    public Search(Set<Service> searchedServices) {
        this.searchedServices = searchedServices;
    }


}
