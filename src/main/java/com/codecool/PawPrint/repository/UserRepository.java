package com.codecool.PawPrint.repository;

import com.codecool.PawPrint.model.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findById(int id);
    List<User> findAll();
    User findUserByUsername(String name);
    User findUserByEmail(String email);
    User findUserByFullName(String fullname);
    boolean existsByEmail(String email);
    boolean existsByFullName(String fullName);
}
