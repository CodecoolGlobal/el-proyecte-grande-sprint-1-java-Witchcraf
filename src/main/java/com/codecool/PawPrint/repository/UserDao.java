package com.codecool.PawPrint.repository;

import com.codecool.PawPrint.model.entity.User;

import java.util.List;

public interface UserDao {

    void add(User user);
    User findById(int id);
    List<User> getAll();
}
