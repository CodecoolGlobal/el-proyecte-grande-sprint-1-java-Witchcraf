package com.codecool.PawPrint.repository;

import com.codecool.PawPrint.model.entity.Search;
import com.codecool.PawPrint.model.entity.User;
import com.codecool.PawPrint.model.service.ServiceOffered;

import java.util.List;
import java.util.Set;

public interface UserDao {

    void add(User user);
    void add(User user, Search search);
    User findById(int id);
    User findByName(String name);
    List<User> getAll();
}
