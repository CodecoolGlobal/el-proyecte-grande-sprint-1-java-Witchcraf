package com.codecool.PawPrint.repository;

import com.codecool.PawPrint.model.entity.Search;
import com.codecool.PawPrint.model.entity.User;
import java.util.List;


public interface UserDao {

    void add(User user);
    void add(User user, Search search);
    User findById(int id);
    User findByName(String name);
    User checkUser(String email, String password);
    List<User> getAll();


    User findByMail(String email);
}
