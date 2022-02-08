package com.codecool.PawPrint.repository;

import com.codecool.PawPrint.model.entity.Search;
import com.codecool.PawPrint.model.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface UserDao {

    void add(User user);
//    void add(User user, Search search);
    User findById(int id);
    User findByName(String name);
    User findByFullName(String fullName);
    List<User> getAll();



    boolean existsByEmail(String email);
    boolean existsByFullName(String fullName);
    User findByMail(String email);
}
