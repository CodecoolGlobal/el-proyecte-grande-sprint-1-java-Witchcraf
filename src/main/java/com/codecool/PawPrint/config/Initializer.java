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

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
class Initializer implements CommandLineRunner {

    private final UserDao repository;
    private final ServiceDao dao;
    private final PasswordEncoder bCryptPasswordEncoder;

    public Initializer(@Qualifier("userRepository") UserDao repository, ServiceDao dao, PasswordEncoder bCryptPasswordEncoder) {
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
        User BBsUser = new User("BBsBar&Grill", LocalDateTime.now(), "hello@bbzbar.hu", bCryptPasswordEncoder.encode("1234"), UserType.NORMAL);
        BBsUser.setId(7);
        User HokedliUser = new User("Hokedli", LocalDateTime.now(), "hokedlidelivery@gmail.com", bCryptPasswordEncoder.encode("1234"), UserType.NORMAL);
        HokedliUser.setId(8);
        User WanHaoUser = new User("WanHao", LocalDateTime.now(), "info@monoricenter.hu", bCryptPasswordEncoder.encode("1234"), UserType.NORMAL);
        WanHaoUser.setId(9);
        User TerezaUser = new User("Tereza", LocalDateTime.now(), "RESERVATION@TEREZA.HU", bCryptPasswordEncoder.encode("1234"), UserType.NORMAL);
        WanHaoUser.setId(10);
        User NaspolyaUser = new User("Naspolya", LocalDateTime.now(), "info@naspolya.hu", bCryptPasswordEncoder.encode("1234"), UserType.NORMAL);
        WanHaoUser.setId(10);

        repository.add(pete);
        repository.add(admin);
        repository.add(eve);
        repository.add(John);
        repository.add(rob);
        repository.add(washAndVauUser);
        repository.add(washAndVauUserKecskemet);
        repository.add(BBsUser);
        repository.add(HokedliUser);
        repository.add(WanHaoUser);
        repository.add(TerezaUser);
        repository.add(NaspolyaUser);


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
        Contact BBsCon= new Contact(BBsUser, "+36704230899");
        Contact HokedliCon= new Contact(HokedliUser, "+36205871408");
        Contact WanHaoCon= new Contact(WanHaoUser, "+36309126888");
        Contact TerezaCon= new Contact(TerezaUser, "+36703689374");
        Contact NaspolyaCon= new Contact(NaspolyaUser, "+36703808407");


        //init address
        Address peteAddress = new Address(petecon.getId(), "Hungary", "Budapest", "6");
        Address adminAdd = new Address(admincon.getId(), "Hungary", "Budapest", "6");
        Address eveAddress = new Address(evecon.getId(), "Hungary", "Budapest", "5");
        Address johnAddress = new Address(evecon.getId(), "Hungary", "Kecskemét", "5");
        Address robAddress = new Address(evecon.getId(), "Hungary", "Kecskemét", "5");
        Address washAndVauAddress = new Address(washAndVauCon.getId(), "Hungary", "11", "Budapest", "Tétényi út", "31");
        Address washAndVau2Address = new Address(washAndVau2Con.getId(), "Hungary", "3", "Budapest", "Margitliget utca", "1");
        Address washAndVau3Address = new Address(washAndVau3Con.getId(), "Hungary", "3", "Budapest", "Bécsi út", "67");
        Address washAndVau4Address = new Address(washAndVau4Con.getId(), "Hungary", "4", "Budapest", "Kemény Gusztáv utca", "4");
        Address washAndVauKecskemetAddress = new Address(washAndVauKecskemetCon.getId(), "Hungary", "0", "Kecskemét", "Reviczky Gyula utca", "1");
        Address BBsAddress = new Address(BBsCon.getId(), "Hungary", "7", "Budapest", "Király utca", "15");
        Address HokedliAddress = new Address(HokedliCon.getId(), "Hungary", "6", "Budapest", "Nagymező utca", "10");
        Address WanHaoAddress = new Address(WanHaoCon.getId(), "Hungary", "10", "Budapest", "Jegenye utca", "30");
        Address TerezaAddress = new Address(TerezaCon.getId(), "Hungary", "6", "Budapest", "Nagymező utca", "3");
        Address NaspolyaAddress = new Address(NaspolyaCon.getId(), "Hungary", "6", "Budapest", "Káldy Gyula utca", "7");

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
        BBsCon.setAddress(BBsAddress);
        HokedliCon.setAddress(HokedliAddress);
        WanHaoCon.setAddress(WanHaoAddress);
        TerezaCon.setAddress(TerezaAddress);
        NaspolyaCon.setAddress(NaspolyaAddress);

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
        ServiceOffered BBs = new ServiceOffered("BB's Bar & Grill", PetType.DOG, 4.4, BBsCon, "H-Cs: 17:00-00:00 P: 13:00-02:00 Szo: 12:00-02:00 V: 12:00-00:00", ServiceType.RESTAURANT, null, "Relax, party, meet up or just drop by for a beerpong!", "https://bbzbar.hu");
        ServiceOffered Hokedli = new ServiceOffered("Hokedli", PetType.DOG, 4.7, HokedliCon, "H-P 11:30-17:00", ServiceType.RESTAURANT, null, "Ebédelj egészségesen magadért és másokért! Gluténmentes, vegetáriánus és vegán ebédek!", "https://www.hokedli.net");
        ServiceOffered WanHao = new ServiceOffered("Wan Hao Restaurant", PetType.DOG, 4.1, WanHaoCon, "H-V 10:00-22:00", ServiceType.RESTAURANT, null, "Local Chinese community eat, shop, and have fun here. 18 Chinese restaurants all in one place, plus 2 Asian food stores. Real Chinese. Real exotic. Real fun.", "http://www.chinatownbudapest.hu/");
        ServiceOffered Tereza = new ServiceOffered("Tereza Mexican Restaurant", PetType.DOG, 4.3, TerezaCon, "H-P 18:00-00:00 Szo-V: 12:00-00:00", ServiceType.RESTAURANT, null, "MI CASA ES SU CASA! MEXICAN URBAN GARDEN IN DOWNTOWN BUDAPEST. TEREZA HAS TAKEN HER PERMANENT PLACE AND AWAITS ITS GUESTS WITH UNIQUE AMBIENCE, AUTHENTIC MEXICAN CUISINE BY REGIONS AND WILD SELECTION OF MEZCALS AND MARGARITAS.", "https://tereza.hu/");
        ServiceOffered Naspolya = new ServiceOffered("Naspolya Nassolda", PetType.DOG, 4.5, NaspolyaCon, "K-P 12:00-19:00 Szo-V 10:00-19:00", ServiceType.RESTAURANT, null, "Naspolya is a pioneering downtown café where all the sweets and salty snacks are raw vegan, made from organic fair trade ingredients, without additives or empty calories.", "https://naspolya.hu");

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
        dao.add(BBs);
        dao.add(Hokedli);
        dao.add(WanHao);
        dao.add(Tereza);
        dao.add(Naspolya);
        System.out.println(repository.getAll());
    }
}
