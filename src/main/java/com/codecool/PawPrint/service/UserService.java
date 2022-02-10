package com.codecool.PawPrint.service;

import com.codecool.PawPrint.model.controllerEntity.UserRegEntity;
import com.codecool.PawPrint.model.entity.Gender;
import com.codecool.PawPrint.model.entity.User;
import com.codecool.PawPrint.model.entity.UserType;
import com.codecool.PawPrint.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUser() {
        return userDao.getAll();
    }

    public User registerUser(UserRegEntity userRegEntity) {
        String name = userRegEntity.getUsername();
        String email = userRegEntity.getEmail();
        String password = userRegEntity.getPassword();
        String fullname = userRegEntity.getFullname();
        Gender gender = userRegEntity.getGender();
        String isService = userRegEntity.getIsService();
        UserType type;
        if(isService.equals("true")){
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
       return userDao.findByUserName(name);
    }

    public boolean checkRegEmailAndName(String email, String fullName){
        if(userDao.existsByEmail(email) && userDao.existsByFullName(fullName)){
            return false;
        }
        return true;
    }

//    public void saveSearch(int userId, Set<ServiceOffered> services) {
//        User user = findUserById(userId);
//        Search search = new Search(services);
//        userDao.add(user, search);
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUserName(username);
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
