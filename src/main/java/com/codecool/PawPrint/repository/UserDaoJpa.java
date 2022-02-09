package com.codecool.PawPrint.repository;

import com.codecool.PawPrint.model.entity.Search;
import com.codecool.PawPrint.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("userDaoJPA")
@Primary
public class UserDaoJpa implements UserDao {

    private final UserRepository userRepository;

    @Autowired
    public UserDaoJpa(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void add(User user) {
        userRepository.save(user);
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public User findByUserName(String name) {
        return userRepository.findUserByUsername(name);
    }

    @Override
    public User findByFullName(String fullName) {
        return userRepository.findUserByFullName(fullName);
    }


    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByFullName(String fullName) {
        return userRepository.existsByFullName(fullName);
    }

    @Override
    public User findByMail(String email) {
        return userRepository.findUserByEmail(email);
    }


}
