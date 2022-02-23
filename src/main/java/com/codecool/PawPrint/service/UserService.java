package com.codecool.PawPrint.service;

import com.codecool.PawPrint.model.controllerEntity.UserRegEntity;
import com.codecool.PawPrint.model.entity.Gender;
import com.codecool.PawPrint.model.controllerEntity.SaveSearchEntity;
import com.codecool.PawPrint.model.entity.Search;
import com.codecool.PawPrint.model.entity.User;
import com.codecool.PawPrint.model.entity.UserType;
import com.codecool.PawPrint.model.service.ServiceOffered;
import com.codecool.PawPrint.repository.SearchDao;
import com.codecool.PawPrint.repository.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserDao userDao;
    private final SearchDao searchDao;
    private final PasswordEncoder passwordEncoder;

//    @Autowired
//    public UserService(UserDao userDao, PasswordEncoder passwordEncoder) {
//        this.userDao = userDao;
//        this.passwordEncoder = passwordEncoder;
//    }

    public List<User> getAllUser() {
        return userDao.getAll();
    }

    public User registerUser(UserRegEntity userRegEntity) {
        String name = userRegEntity.getUsername();
        String email = userRegEntity.getEmail();
        String password = userRegEntity.getPassword();
        String fullname = userRegEntity.getFullname();
        Gender gender = userRegEntity.getGender();
        boolean isService = userRegEntity.isService();
        UserType type;
        if(isService){
            type = UserType.ADMIN;
        }
        else{
            type = UserType.NORMAL;
        }
        User newUser = new User(name, email, password,type, fullname, gender);
        newUser.setRegistrationTime(LocalDateTime.now());
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userDao.add(newUser);
        return newUser;
    }

    public User findUserById(int id) {
       return userDao.findById(id);
    }

    public User findUserByName(String name) {
       User user = userDao.findByUsername(name);
       return user;
    }

    public boolean checkRegEmailAndName(String email, String fullName){
        if(userDao.existsByEmail(email) && userDao.existsByFullName(fullName)){
            return false;
        }
        return true;
    }

    public Search saveSearch(User user, Set<ServiceOffered> servicesInSearch, String searchName, String description) {
        Search search = new Search(0, searchName, description, user, servicesInSearch);
        user.getSavedSearches().add(search);
        userDao.add(user);
//        searchDao.add(search);
        return search;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        Collection<SimpleGrantedAuthority> userTypes = new ArrayList<>();
        if(user == null){
            throw new UsernameNotFoundException("Not found this User!");
        }
        assert userTypes != null;
        userTypes.add(new SimpleGrantedAuthority("ROLE_" + user.getUserType().toString()));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), userTypes);
    }

    public User findUserByEmail(String email) {
        return userDao.findByMail(email);
    }
}
