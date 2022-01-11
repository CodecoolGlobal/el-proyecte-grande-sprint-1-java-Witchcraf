package com.codecool.PawPrint.config;

import com.codecool.PawPrint.model.contact.Address;
import com.codecool.PawPrint.model.contact.Contact;
import com.codecool.PawPrint.model.entity.PetType;
import com.codecool.PawPrint.model.entity.User;
import com.codecool.PawPrint.model.entity.UserType;
import com.codecool.PawPrint.model.service.ServiceOffered;
import com.codecool.PawPrint.model.service.ServiceSubtype;
import com.codecool.PawPrint.model.service.ServiceType;
import com.codecool.PawPrint.repository.UserDao;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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
        User admin = new User("admin", LocalDateTime.now(), "admin", "admin", UserType.ADMIN);
        admin.setId(0);
        User eve = new User("Eve", LocalDateTime.now(), "eve@gmail.com", "1234", UserType.NORMAL);
        eve.setId(2);
        User John = new User("John", LocalDateTime.now(), "john@gmail.com", "1234", UserType.NORMAL);
        John.setId(3);
        User rob = new User("Rob", LocalDateTime.now(), "rob@gmail.com", "1234", UserType.NORMAL);
        rob.setId(4);

        repository.add(pete);
        repository.add(admin);
        repository.add(eve);
        repository.add(John);
        repository.add(rob);


        User first = repository.findById(1);
        System.out.println(first.getUserName());


        //init contact
        Contact petecon = new Contact(pete, null);
        Contact admincon = new Contact(admin, null);
        Contact evecon = new Contact(eve, null);
        Contact johncon = new Contact(John, null);
        Contact robCon= new Contact(rob, null);


        //init address
        Address peteAdress = new Address(petecon, "Hun", "Bud");
        Address amindAdd = new Address(admincon, "Codecool", "Codeccol");
        Address eveAddress = new Address(evecon, "eng", "london");

        //set address to contact
        petecon.setAddress(peteAdress);
        admincon.setAddress(amindAdd);
        evecon.setAddress(eveAddress);

        // init ServiceOffered
        ServiceOffered restA = new ServiceOffered(PetType.CAT, ServiceType.RESTAURANT, ServiceSubtype.COSMETICS, admincon);
        ServiceOffered hospA = new ServiceOffered(PetType.DOG, ServiceType.HOSPITAL, ServiceSubtype.HOSPITAL, petecon);
        ServiceOffered sheltA = new ServiceOffered(PetType.CATANDDOG, ServiceType.SHELTER, ServiceSubtype.WASHANDVAU, evecon);



        /*String country,
        String city,
        String district,
        int userI*/
    }
}
