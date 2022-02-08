package com.codecool.PawPrint.controller;

import com.codecool.PawPrint.model.controllerEntity.UserLogEntity;
import com.codecool.PawPrint.model.controllerEntity.UserRegEntity;
import com.codecool.PawPrint.model.entity.User;
import com.codecool.PawPrint.model.entity.UserType;
import com.codecool.PawPrint.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class LoginController {
    private UserService userService;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public LoginController(UserService userService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping(value = "/checkPreviousReg")
    public boolean checkUserInDataBase(@RequestBody UserRegEntity userRegEntity) {
        User user = userService.findUserByEmail(userRegEntity.getEmail());
        return user != null;
    }


    @PostMapping(value = "/registerUser")
    public User registerNewUser(@RequestBody UserRegEntity userRegEntity) {
        String name = userRegEntity.getUsername();
        String email = userRegEntity.getEmail();
        String password = userRegEntity.getPassword();
        User newUser = new User(name, email, password, UserType.NORMAL);

        return userService.registerUser(newUser);
    }

}