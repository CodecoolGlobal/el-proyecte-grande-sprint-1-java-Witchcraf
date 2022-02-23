package com.codecool.PawPrint.controller;

import com.codecool.PawPrint.model.controllerEntity.UserLogEntity;
import com.codecool.PawPrint.model.controllerEntity.UserRegEntity;
import com.codecool.PawPrint.model.entity.Gender;
import com.codecool.PawPrint.model.entity.User;
import com.codecool.PawPrint.model.entity.UserType;
import com.codecool.PawPrint.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class LoginController {
    private UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping(value = "/checkPreviousReg{email}")
    public boolean checkUserInDataBase(@PathVariable String email) {
        User user = userService.findUserByEmail(email);
        return user != null;
    }

    @PostMapping(value = "/registerUser")
    public User registerNewUser(@RequestBody UserRegEntity userRegEntity) {
        System.out.println(userRegEntity);
        return userService.registerUser(userRegEntity);
    }
}
