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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
class Initializer implements CommandLineRunner {

    private final UserDao repository;
    private final ServiceDao dao;
    private final PasswordEncoder bCryptPasswordEncoder;

    public Initializer(UserDao repository, ServiceDao dao, PasswordEncoder bCryptPasswordEncoder) {
        this.repository = repository;
        this.dao = dao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void run(String... strings) {


        User pete = new User("Zoe", LocalDateTime.now(), "cafezoo@gmail.com", bCryptPasswordEncoder.encode("1234"), UserType.NORMAL);
        pete.setId(1);
        User admin = new User("admin", LocalDateTime.now(), "admin", bCryptPasswordEncoder.encode("admin"), UserType.ADMIN);
        admin.setId(0);
        User eve = new User("Eve", LocalDateTime.now(), "eve@gmail.com", bCryptPasswordEncoder.encode("1234"), UserType.NORMAL);
        eve.setId(2);
        User John = new User("John", LocalDateTime.now(), "john@gmail.com", bCryptPasswordEncoder.encode("1234"), UserType.NORMAL);
        John.setId(3);
        User rob = new User("Rob", LocalDateTime.now(), "rob@gmail.com", bCryptPasswordEncoder.encode("1234"), UserType.NORMAL);
        rob.setId(4);
        User washAndVauUser = new User("WashAndVau", LocalDateTime.now(), "info@kutyamoso.hu", bCryptPasswordEncoder.encode("1234"), UserType.NORMAL);
        washAndVauUser.setId(5);
        User washAndVauUserKecskemet = new User("WashAndVauKecskemét", LocalDateTime.now(), "kecskemet@kutyamoso.hu", bCryptPasswordEncoder.encode("1234"), UserType.NORMAL);
        washAndVauUser.setId(6);

        repository.add(pete);
        repository.add(admin);
        repository.add(eve);
        repository.add(John);
        repository.add(rob);
        repository.add(washAndVauUser);
        repository.add(washAndVauUserKecskemet);


        //init contact
        Contact petecon = new Contact(pete);
        Contact admincon = new Contact(admin);
        Contact evecon = new Contact(eve);
        Contact johncon = new Contact(John);
        Contact robCon= new Contact(rob);
        Contact washAndVauCon= new Contact(washAndVauUser, "+36205450145");
        Contact washAndVau2Con= new Contact(washAndVauUser, "+36205450145");
        Contact washAndVau3Con= new Contact(washAndVauUser, "+36205450145");
        Contact washAndVau4Con= new Contact(washAndVauUser, "+36205450145");
        Contact washAndVauKecskemetCon= new Contact(washAndVauUserKecskemet, "+36204805301");


        //init address
        Address peteAddress = new Address(petecon.getId(), "Hungary", "Budapest", "6");
        Address adminAdd = new Address(admincon.getId(), "Hungary", "Budapest", "6");
        Address eveAddress = new Address(evecon.getId(), "Hungary", "Budapest", "5");
        Address johnAddress = new Address(evecon.getId(), "Hungary", "Kecskemét", "5");
        Address robAddress = new Address(evecon.getId(), "Hungary", "Kecskemét", "5");
        Address washAndVauAddress = new Address(washAndVauCon.getId(), "Hungary", "Budapest", "11", "Tétényi út", "31");
        Address washAndVau2Address = new Address(washAndVau2Con.getId(), "Hungary", "Budapest", "3", "Margitliget utca", "1");
        Address washAndVau3Address = new Address(washAndVau3Con.getId(), "Hungary", "Budapest", "3", "Bécsi út", "67");
        Address washAndVau4Address = new Address(washAndVau4Con.getId(), "Hungary", "Budapest", "4", "Kemény Gusztáv utca", "4");
        Address washAndVauKecskemetAddress = new Address(washAndVauKecskemetCon.getId(), "Hungary", "Kecskemét", "0", "Reviczky Gyula utca", "1");

        //set address to contact
        petecon.setAddress(peteAddress);
        admincon.setAddress(adminAdd);
        evecon.setAddress(eveAddress);
        johncon.setAddress(johnAddress);
        robCon.setAddress(robAddress);
        washAndVauCon.setAddress(washAndVauAddress);
        washAndVau2Con.setAddress(washAndVau2Address);
        washAndVau3Con.setAddress(washAndVau3Address);
        washAndVau4Con.setAddress(washAndVau4Address);
        washAndVauKecskemetCon.setAddress(washAndVauKecskemetAddress);

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
        ServiceOffered washAndVau = new ServiceOffered("Wash & Vau Újbuda", PetType.DOG, 4.4, washAndVauCon, "Open 24 hours", ServiceType.WELLNESS, ServiceSubtype.WASHANDVAU, "Önkiszolgáló kutyafürdetés gyorsan és egyszerűen kutyamosónk segítségével a nap 24 órájában! :)", "https://kutyamoso.hu/ujbuda/");
        ServiceOffered washAndVau2 = new ServiceOffered("Wash & Vau Békásmegyer", PetType.DOG, 4.4, washAndVau2Con, "Open 24 hours", ServiceType.WELLNESS, ServiceSubtype.WASHANDVAU, "Önkiszolgáló kutyafürdetés gyorsan és egyszerűen kutyamosónk segítségével a nap 24 órájában! :)", "https://kutyamoso.hu/bekasmegyer/");
        ServiceOffered washAndVau3 = new ServiceOffered("Wash & Vau Óbuda", PetType.DOG, 4.4, washAndVau3Con, "Open 24 hours", ServiceType.WELLNESS, ServiceSubtype.WASHANDVAU, "Önkiszolgáló kutyafürdetés gyorsan és egyszerűen kutyamosónk segítségével a nap 24 órájában! :)", "https://kutyamoso.hu/obuda/");
        ServiceOffered washAndVau4 = new ServiceOffered("Wash & Vau Újpest", PetType.DOG, 4.4, washAndVau4Con, "Open 24 hours", ServiceType.WELLNESS, ServiceSubtype.WASHANDVAU, "Önkiszolgáló kutyafürdetés gyorsan és egyszerűen kutyamosónk segítségével a nap 24 órájában! :)", "https://kutyamoso.hu/ujpest/");
        ServiceOffered washAndVauKecskemet = new ServiceOffered("Wash & Vau Kecskemét", PetType.DOG, 4.4, washAndVauKecskemetCon, "Open 24 hours", ServiceType.WELLNESS, ServiceSubtype.WASHANDVAU, "Önkiszolgáló kutyafürdetés gyorsan és egyszerűen kutyamosónk segítségével a nap 24 órájában! :)", "https://kutyamoso.hu/kecskemet/");

        dao.add(cosP);
        dao.add(restP);
        dao.add(hospP);
        dao.add(sheltE);
        dao.add(restE);
        dao.add(restP2);
        dao.add(garden);
        dao.add(animalHotel);
        dao.add(sheltFutr);
        dao.add(washAndVau);
        dao.add(washAndVau2);
        dao.add(washAndVau3);
        dao.add(washAndVau4);
        dao.add(washAndVauKecskemet);
        System.out.println(repository.getAll());
    }
}
