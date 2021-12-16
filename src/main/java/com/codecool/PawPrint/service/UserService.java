package com.codecool.PawPrint.service;

import com.codecool.PawPrint.model.entity.Search;
import com.codecool.PawPrint.model.entity.User;
import com.codecool.PawPrint.model.service.ServiceOffered;
import com.codecool.PawPrint.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    private final UserDao userDao;

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

    public User findUserByName(String name) {
       return userDao.findByName(name);
    }

    public void saveSearch(int userId, Set<ServiceOffered> services) {
        User user = findUserById(userId);
        Search search = new Search(services);
        userDao.add(user, search);
    }

}
