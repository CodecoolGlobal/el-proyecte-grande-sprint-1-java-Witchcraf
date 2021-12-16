package com.codecool.PawPrint.service;

import com.codecool.PawPrint.model.entity.User;
import com.codecool.PawPrint.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class UserService {

    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllUser() {
        return userDao.getAll();
    }

    public void registerUser(User user) {
        userDao.add(user);
    }

    public User findUserById(int id) {
       return userDao.findById(id);
    }
}
