package com.codecool.PawPrint.repository;

import com.codecool.PawPrint.model.service.Service;

import java.util.Set;

public interface ServiceDao {

    void add(Service service);
    Service findById(int id);
    Service findByName(String name);
    Set<Service> getAll();
}
