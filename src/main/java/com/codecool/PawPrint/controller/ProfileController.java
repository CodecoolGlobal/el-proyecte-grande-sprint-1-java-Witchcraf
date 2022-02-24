package com.codecool.PawPrint.controller;

import com.codecool.PawPrint.filter.TokenAuthenticationFilter;
import com.codecool.PawPrint.model.controllerEntity.UserRegEntity;
import com.codecool.PawPrint.model.entity.User;
import com.codecool.PawPrint.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class ProfileController {

    private UserService userService;
    private TokenAuthenticationFilter tokenAuthenticationFilter;

    @Autowired
    public ProfileController(UserService userService, TokenAuthenticationFilter tokenAuthenticationFilter) {
        this.userService = userService;
        this.tokenAuthenticationFilter = tokenAuthenticationFilter;
    }

    @GetMapping("/getuseralldata")
    public User getAllUserDataById(HttpServletRequest httpRequest) throws Exception {
        User user = userService.findUserByName(tokenAuthenticationFilter.tokenVerification(httpRequest));
        return user;
    }

    @PostMapping("/updateuser")
    public void updateUser(@RequestBody UserRegEntity userRegEntity){
        userService.updateUser(userRegEntity);
    }
}
