package com.codecool.PawPrint.repository;

import com.codecool.PawPrint.model.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoMem implements UserDao{
    private final List<User> data = new ArrayList<>();

    private UserDaoMem() {
    }

    @Override
    public void add(User user) {
        data.add(user);

    }

    @Override
    public User findById(int id) {
        User currentUser = null;
        for (User user : data) {
            if(user.getId() == id){
                currentUser = user;
            }
        }
        return currentUser;
    }

    @Override
    public List<User> getAll() {
        return data;
    }
}
