package com.codecool.PawPrint.repository;

import com.codecool.PawPrint.model.entity.Search;
import com.codecool.PawPrint.model.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Repository("userDaoMem")
public class UserDaoMem implements UserDao{
    private final List<User> data = new ArrayList<>();

    private UserDaoMem() {
    }

    @Override
    public void add(User user) {
        data.add(user);

    }

    @Override
    public void add(User user, Search search) {
        for (User datum : data) {
            if (datum.equals(user)) {
                datum.getSavedSearches().add(search);
            }
        }
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
    public User findByName(String name) {
        User currentUser = null;
        for (User user : data) {
            if(Objects.equals(user.getUsername(), name)){
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
