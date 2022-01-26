package com.codecool.PawPrint.controller;

import com.codecool.PawPrint.model.controllerEntity.UserLogEntity;
import com.codecool.PawPrint.model.controllerEntity.UserRegEntity;
import com.codecool.PawPrint.model.entity.User;
import com.codecool.PawPrint.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin(origins = "http://localhost:3000")
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

    @PostMapping(value = "/registerUser")
    public User registerNewUser(@RequestBody UserRegEntity userRegEntity) {
        String name = userRegEntity.getFullname();
        String email = userRegEntity.getEmail();
        String password = userRegEntity.getPassword();
        if (userService.checkRegEmailAndName(email, name)){
            User newUser = new User(name, email, password);
            return userService.registerUser(newUser);
        }
        return null;
    }

    @PostMapping(value = "/checkLog")
    public User checkUser(@RequestBody UserLogEntity userLogEntity) {
        String email = userLogEntity.getEmail();
        String password = userLogEntity.getPassword();
        User current = userService.findUserByEmail(email);
        boolean isPasswordMatch;
        if (current == null) {
            return null;
        } else {
            isPasswordMatch = passwordEncoder.matches(password, current.getPassword());
            if (isPasswordMatch) {
                return current;
            } else {
                return null;
            }
        }
    }
}