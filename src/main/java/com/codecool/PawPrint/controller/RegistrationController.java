package com.codecool.PawPrint.controller;


import com.codecool.PawPrint.model.entity.User;
import com.codecool.PawPrint.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reg")
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/add")
    public void regNewUser(@RequestBody User user){
        userService.registerUser(user);
    }

    @PostMapping("/{username}")
    public boolean checkPreviusUserName(@PathVariable String username){
        List<User> allUser = userService.getAllUser();

        for(User user: allUser){
            return !user.getUserName().equals(username);
        }
        return true;
    }
}
