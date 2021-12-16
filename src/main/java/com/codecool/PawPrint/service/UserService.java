package com.codecool.PawPrint.service;

import com.codecool.PawPrint.model.entity.User;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class UserService {
    private final List<User> users = new LinkedList<>();


    public List<User> getAllUser() {
        return users;
    }

    public void registerUser(User user) {
        users.add(user);
    }

    public User findUserById(int id) {
        User currentUser = null;
        for (User user : users) {
            if(user.getId() == id){
                currentUser = user;
            }
        }
        return currentUser;
    }
}
