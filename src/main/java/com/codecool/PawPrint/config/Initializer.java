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

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
        User pete = new User("Zoe", LocalDateTime.now(), "cafezoo@gmail.com", "1234", UserType.NORMAL);
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


        //init contact
        Contact petecon = new Contact(pete);
        Contact admincon = new Contact(admin);
        Contact evecon = new Contact(eve);
        Contact johncon = new Contact(John);
        Contact robCon= new Contact(rob);


        //init address
        Address peteAddress = new Address(petecon.getId(), "Hungary", "Budapest", "6");
        Address adminAdd = new Address(admincon.getId(), "Hungary", "Budapest", "6");
        Address eveAddress = new Address(evecon.getId(), "Hungary", "Budapest", "5");
        Address johnAddress = new Address(evecon.getId(), "Hungary", "Kecskemét", "5");
        Address robAddress = new Address(evecon.getId(), "Hungary", "Kecskemét", "5");

        //set address to contact
        petecon.setAddress(peteAddress);
        admincon.setAddress(adminAdd);
        evecon.setAddress(eveAddress);
        johncon.setAddress(johnAddress);
        robCon.setAddress(robAddress);

        // init ServiceOffered
        ServiceOffered cosP = new ServiceOffered("WashAndWau", PetType.CAT, ServiceType.WELLNESS, ServiceSubtype.WASHANDVAU, petecon);
        ServiceOffered animalHotel = new ServiceOffered("Pangea", PetType.CATANDDOG, ServiceType.WELLNESS, ServiceSubtype.COSMETICS, johncon);
        ServiceOffered restP = new ServiceOffered("Café Zoo", PetType.CAT, ServiceType.RESTAURANT, null, petecon);
        ServiceOffered restP2 = new ServiceOffered("Zoo", PetType.CAT, ServiceType.RESTAURANT, null, petecon);
        ServiceOffered hospP = new ServiceOffered("Profivet", PetType.DOG, ServiceType.HEALTHCARE, ServiceSubtype.HOSPITAL, admincon);
        ServiceOffered sheltE = new ServiceOffered("Vizsla Bárka", PetType.CATANDDOG, ServiceType.SHELTER, null, evecon);
        ServiceOffered sheltFutr = new ServiceOffered("Futrinka", PetType.CATANDDOG, ServiceType.SHELTER, null, robCon);
        ServiceOffered restE = new ServiceOffered("Fellini Bisztró", PetType.CATANDDOG, ServiceType.RESTAURANT, null, evecon);
        ServiceOffered garden = new ServiceOffered("Kertem", PetType.DOG, ServiceType.RESTAURANT, null, johncon);

        dao.add(cosP);
        dao.add(restP);
        dao.add(hospP);
        dao.add(sheltE);
        dao.add(restE);
        dao.add(restP2);
        dao.add(garden);
        dao.add(animalHotel);
        dao.add(sheltFutr);

    }
}
