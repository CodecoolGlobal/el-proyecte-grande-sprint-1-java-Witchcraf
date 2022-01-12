package com.codecool.PawPrint.config;

import com.codecool.PawPrint.model.contact.Address;
//import com.codecool.PawPrint.model.contact.Contact;
import com.codecool.PawPrint.model.entity.PetType;
import com.codecool.PawPrint.model.entity.User;
import com.codecool.PawPrint.model.entity.UserType;
import com.codecool.PawPrint.model.service.ServiceOffered;
import com.codecool.PawPrint.model.service.ServiceSubtype;
import com.codecool.PawPrint.model.service.ServiceType;
import com.codecool.PawPrint.repository.ServiceDao;
import com.codecool.PawPrint.repository.UserDao;
import com.codecool.PawPrint.service.ServiceService;
import io.swagger.models.Contact;
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
        Address peteAdress = new Address(petecon.getId(), "Hungary", "Budapest", "6");
        Address adminAdd = new Address(admincon.getId(), "Hungary", "Budapest", "6");
        Address eveAddress = new Address(evecon.getId(), "Hungary", "Budapest", "5");

        //set address to contact
        petecon.setAddress(peteAdress);
        admincon.setAddress(adminAdd);
        evecon.setAddress(eveAddress);

        // init ServiceOffered
        ServiceOffered cosP = new ServiceOffered("cosmeticsPete", PetType.CAT, ServiceType.WELLNESS, ServiceSubtype.COSMETICS, petecon);
        ServiceOffered restP = new ServiceOffered("RestaurantPete", PetType.CAT, ServiceType.RESTAURANT, null, petecon);
        ServiceOffered hospP = new ServiceOffered("HospPete", PetType.DOG, ServiceType.HOSPITAL, ServiceSubtype.HOSPITAL, admincon);
        ServiceOffered sheltE = new ServiceOffered("ShelterEve", PetType.CATANDDOG, ServiceType.SHELTER, null, evecon);
        ServiceOffered restE = new ServiceOffered("RestaurantEve", PetType.CATANDDOG, ServiceType.RESTAURANT, null, evecon);

        dao.add(cosP);
        dao.add(restP);
        dao.add(hospP);
        dao.add(sheltE);
        dao.add(restE);

        ServiceOffered firstServ = dao.findByName("cosmeticsPete");
        System.out.println(firstServ);

        /*String country,
        String city,
        String district,
        int userI*/
    }
}
