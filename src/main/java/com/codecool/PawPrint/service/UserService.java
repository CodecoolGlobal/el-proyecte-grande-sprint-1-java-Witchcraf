package com.codecool.PawPrint.service;

import com.codecool.PawPrint.model.entity.DTO.UserPersonalDetailsDTO;
import com.codecool.PawPrint.model.entity.Search;
import com.codecool.PawPrint.model.entity.User;
import com.codecool.PawPrint.model.entity.UserType;
import com.codecool.PawPrint.model.service.ServiceOffered;
import com.codecool.PawPrint.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    /*public List<UserPersonalDetailsDTO> getAllUserPersonalDetails() {
        return ((List<User>) userDao
                .getAll())
                .stream()
                .map(this::convertToUserLocationDTO)
                .collect(Collectors.toList());
    }

    private UserPersonalDetailsDTO convertToUserLocationDTO(User user) {
        UserPersonalDetailsDTO userLocationDTO = new UserPersonalDetailsDTO();
        userLocationDTO.setId(user.getId());
        userLocationDTO.setUserName(user.getUserName());
        userLocationDTO.setEmail(user.getEmail());
        userLocationDTO.setPassword(user.getPassword());
        userLocationDTO.setType(user.getType());
        userLocationDTO.setRegistrationTime(user.getRegistrationTime());
        return userLocationDTO;
    }*/
}
