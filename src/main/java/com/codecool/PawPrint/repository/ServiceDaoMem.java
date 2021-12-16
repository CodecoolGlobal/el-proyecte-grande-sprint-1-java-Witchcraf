package com.codecool.PawPrint.repository;

import com.codecool.PawPrint.model.service.Service;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class ServiceDaoMem implements ServiceDao {

    private Set<Service> services = new HashSet<>();


    @Override
    public void add(Service service) {

    }

    @Override
    public Service findById(int id) {
        return null;
    }

    @Override
    public Service findByName(String name) {
        return null;
    }

    @Override
    public Set<Service> getAll() {
        return null;
    }
}
