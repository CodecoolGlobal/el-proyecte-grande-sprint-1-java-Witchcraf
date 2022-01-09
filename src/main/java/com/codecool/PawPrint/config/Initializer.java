package com.codecool.PawPrint.config;

import com.codecool.PawPrint.model.entity.User;
import com.codecool.PawPrint.model.entity.UserType;
import com.codecool.PawPrint.repository.UserDao;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
class Initializer implements CommandLineRunner {

    private final UserDao repository;

    public Initializer(UserDao repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) {
        User pete = new User("Pete", LocalDateTime.now(), "pete@gmail.com", "1234", UserType.NORMAL);
        pete.setId(1);
        repository.add(pete);
        repository.add(new User("admin", LocalDateTime.now(), "admin", "admin", UserType.ADMIN));
        repository.add(new User("Eve", LocalDateTime.now(), "eve@gmail.com", "1234", UserType.NORMAL));
        repository.add(new User("John", LocalDateTime.now(), "john@gmail.com", "1234", UserType.NORMAL));
        repository.add(new User("Rob", LocalDateTime.now(), "rob@gmail.com", "1234", UserType.NORMAL));
        User first = repository.findById(1);
        System.out.println(first.getUserName());
    }
}
