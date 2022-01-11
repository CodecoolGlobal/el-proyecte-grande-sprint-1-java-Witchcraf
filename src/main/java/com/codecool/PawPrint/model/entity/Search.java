package com.codecool.PawPrint.model.entity;

import com.codecool.PawPrint.model.service.ServiceOffered;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Search {

    private int id;
    private String name = "MySearch";
    private Set<ServiceOffered> searchedServices = new HashSet<>();

    public Search(Set<ServiceOffered> searchedServices) {
        this.searchedServices = searchedServices;
    }


}
