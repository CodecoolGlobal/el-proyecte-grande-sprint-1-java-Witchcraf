package com.codecool.PawPrint.controller;

import com.codecool.PawPrint.model.entity.User;
import com.codecool.PawPrint.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path="/all", produces = "application/json")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping(path="/{id}", produces = "application/json")
    public User getUserById(@PathVariable int id) {
        return userService.findUserById(id);
    }

    @PostMapping("/add")
    public void registerNewUser( @RequestBody User user) {
        userService.registerUser(user);
    }

}
