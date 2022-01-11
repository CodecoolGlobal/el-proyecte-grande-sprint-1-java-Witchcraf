package com.codecool.PawPrint.config;

import com.codecool.PawPrint.model.contact.Address;
import com.codecool.PawPrint.model.contact.Contact;
import com.codecool.PawPrint.model.entity.PetType;
import com.codecool.PawPrint.model.entity.User;
import com.codecool.PawPrint.model.entity.UserType;
import com.codecool.PawPrint.model.service.ServiceOffered;
import com.codecool.PawPrint.model.service.ServiceSubtype;
import com.codecool.PawPrint.model.service.ServiceType;
import com.codecool.PawPrint.repository.ServiceDao;
import com.codecool.PawPrint.repository.UserDao;
import com.codecool.PawPrint.service.ServiceService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Component
class Initializer implements CommandLineRunner {

    private final UserDao repository;
    private final ServiceDao dao;

    public Initializer(UserDao repository, ServiceDao dao) {
        this.repository = repository;
        this.dao = dao;
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
        Contact petecon = new Contact(pete);
        Contact admincon = new Contact(admin);
        Contact evecon = new Contact(eve);
        Contact johncon = new Contact(John);
        Contact robCon= new Contact(rob);


        //init address
        Address peteAdress = new Address(petecon.getId(), "Hun", "Bud", "6");
        Address amindAdd = new Address(admincon.getId(), "Codecool", "Codecool", "5");
        Address eveAddress = new Address(evecon.getId(), "eng", "london", "4");

        //set address to contact
        petecon.setAddress(peteAdress);
//        admincon.setAddress(amindAdd);
//        evecon.setAddress(eveAddress);

        // init ServiceOffered
        ServiceOffered restA = new ServiceOffered("resta", PetType.CAT, ServiceType.RESTAURANT, ServiceSubtype.COSMETICS, admincon);
        restA.setContact(petecon);
//        ServiceOffered hospA = new ServiceOffered("hospa", PetType.DOG, ServiceType.HOSPITAL, ServiceSubtype.HOSPITAL, petecon);
//        ServiceOffered sheltA = new ServiceOffered("shelta", PetType.CATANDDOG, ServiceType.SHELTER, ServiceSubtype.WASHANDVAU, evecon);

        dao.add(restA);
//        dao.add(hospA);
//        dao.add(sheltA);

        ServiceOffered firstServ = dao.findByName("resta");
        System.out.println(firstServ);

        /*String country,
        String city,
        String district,
        int userI*/
    }
}
