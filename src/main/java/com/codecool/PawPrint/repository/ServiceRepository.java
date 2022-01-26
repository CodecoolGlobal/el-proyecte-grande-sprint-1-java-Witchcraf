package com.codecool.PawPrint.repository;

import com.codecool.PawPrint.model.service.ServiceOffered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceOffered, Integer> {

    ServiceOffered findByName(String name);
}
