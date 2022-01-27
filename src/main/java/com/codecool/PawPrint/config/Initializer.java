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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
class Initializer implements CommandLineRunner {

    private final UserDao userDao;
    private final ServiceDao serviceDao;
    private final PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public Initializer(@Qualifier("userDaoJPA") UserDao userDao, @Qualifier("serviceDaoJPA") ServiceDao serviceDao, PasswordEncoder bCryptPasswordEncoder) {
        this.userDao = userDao;
        this.serviceDao = serviceDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void run(String... strings) {

        // create user
        User washAndVauUser = new User("WashAndVau", LocalDateTime.now(), "info@kutyamoso.hu",2, bCryptPasswordEncoder.encode("1234"), UserType.NORMAL);
        User washAndVauUserKecskemet = new User("WashAndVauKecskemét", LocalDateTime.now(), "kecskemet@kutyamoso.hu",1, bCryptPasswordEncoder.encode("1234"), UserType.NORMAL);
        User bbsUser = new User("BBsBar&Grill", LocalDateTime.now(), "hello@bbzbar.hu", 2,bCryptPasswordEncoder.encode("1234"), UserType.NORMAL);
        User hokedliUser = new User("Hokedli", LocalDateTime.now(), "hokedlidelivery@gmail.com",2, bCryptPasswordEncoder.encode("1234"), UserType.NORMAL);
        User wanHaoUser = new User("WanHao", LocalDateTime.now(), "info@monoricenter.hu",2, bCryptPasswordEncoder.encode("1234"), UserType.NORMAL);
        User terezaUser = new User("Tereza", LocalDateTime.now(), "RESERVATION@TEREZA.HU",2, bCryptPasswordEncoder.encode("1234"), UserType.NORMAL);
        User naspolyaUser = new User("Naspolya", LocalDateTime.now(), "info@naspolya.hu",2, bCryptPasswordEncoder.encode("1234"), UserType.NORMAL);

        // create contact
        Contact washAndVauCon= new Contact("+36205450145");
        Contact washAndVau2Con= new Contact("+36205450145");
        Contact washAndVau3Con= new Contact("+36205450145");
        Contact washAndVau4Con= new Contact("+36205450145");
        Contact washAndVauKecskemetCon= new Contact("+36204805301");
        Contact bbsCon= new Contact("+36704230899");
        Contact hokedliCon= new Contact("+36205871408");
        Contact wanHaoCon= new Contact("+36309126888");
        Contact terezaCon= new Contact("+36703689374");
        Contact naspolyaCon= new Contact("+36703808407");


//        washAndVauCon.setUser(washAndVauUser);
//        washAndVau2Con.setUser(washAndVauUser);
//        washAndVau3Con.setUser(washAndVauUser);
//        washAndVau4Con.setUser(washAndVauUser);
//        washAndVauKecskemetCon.setUser(washAndVauUserKecskemet);
//        bbsCon.setUser(bbsUser);
//        hokedliCon.setUser(hokedliUser);
//        wanHaoCon.setUser(wanHaoUser);
//        terezaCon.setUser(terezaUser);
//        naspolyaCon.setUser(naspolyaUser);

        // create address
        Address washAndVauAddress = new Address(washAndVauCon.getId(), "Hungary", "11", "Budapest", "Tétényi út", "31");
        Address washAndVau2Address = new Address(washAndVau2Con.getId(), "Hungary", "3", "Budapest", "Margitliget utca", "1");
        Address washAndVau3Address = new Address(washAndVau3Con.getId(), "Hungary", "3", "Budapest", "Bécsi út", "67");
        Address washAndVau4Address = new Address(washAndVau4Con.getId(), "Hungary", "4", "Budapest", "Kemény Gusztáv utca", "4");
        Address washAndVauKecskemetAddress = new Address(washAndVauKecskemetCon.getId(), "Hungary", "0", "Kecskemét", "Reviczky Gyula utca", "1");
        Address bbsAddress = new Address(bbsCon.getId(), "Hungary", "7", "Budapest", "Király utca", "15");
        Address hokedliAddress = new Address(hokedliCon.getId(), "Hungary", "6", "Budapest", "Nagymező utca", "10");
        Address wanHaoAddress = new Address(wanHaoCon.getId(), "Hungary", "10", "Budapest", "Jegenye utca", "30");
        Address terezaAddress = new Address(terezaCon.getId(), "Hungary", "6", "Budapest", "Nagymező utca", "3");
        Address naspolyaAddress = new Address(naspolyaCon.getId(), "Hungary", "6", "Budapest", "Káldy Gyula utca", "7");

        // add address to contact
        washAndVauCon.setAddress(washAndVauAddress);
        washAndVau2Con.setAddress(washAndVau2Address);
        washAndVau3Con.setAddress(washAndVau3Address);
        washAndVau4Con.setAddress(washAndVau4Address);
        washAndVauKecskemetCon.setAddress(washAndVauKecskemetAddress);
        bbsCon.setAddress(bbsAddress);
        hokedliCon.setAddress(hokedliAddress);
        wanHaoCon.setAddress(wanHaoAddress);
        terezaCon.setAddress(terezaAddress);
        naspolyaCon.setAddress(naspolyaAddress);

        // create service
        ServiceOffered washAndVau = new ServiceOffered("Wash & Vau Újbuda", PetType.DOG, 4.4, washAndVauCon, "Open 24 hours", ServiceType.WELLNESS, ServiceSubtype.WASHANDVAU, "Önkiszolgáló kutyafürdetés gyorsan és egyszerűen kutyamosónk segítségével a nap 24 órájában! :)", "https://kutyamoso.hu/ujbuda/");
        ServiceOffered washAndVau2 = new ServiceOffered("Wash & Vau Békásmegyer", PetType.DOG, 4.4, washAndVau2Con, "Open 24 hours", ServiceType.WELLNESS, ServiceSubtype.WASHANDVAU, "Önkiszolgáló kutyafürdetés gyorsan és egyszerűen kutyamosónk segítségével a nap 24 órájában! :)", "https://kutyamoso.hu/bekasmegyer/");
        ServiceOffered washAndVau3 = new ServiceOffered("Wash & Vau Óbuda", PetType.DOG, 4.4, washAndVau3Con, "Open 24 hours", ServiceType.WELLNESS, ServiceSubtype.WASHANDVAU, "Önkiszolgáló kutyafürdetés gyorsan és egyszerűen kutyamosónk segítségével a nap 24 órájában! :)", "https://kutyamoso.hu/obuda/");
        ServiceOffered washAndVau4 = new ServiceOffered("Wash & Vau Újpest", PetType.DOG, 4.4, washAndVau4Con, "Open 24 hours", ServiceType.WELLNESS, ServiceSubtype.WASHANDVAU, "Önkiszolgáló kutyafürdetés gyorsan és egyszerűen kutyamosónk segítségével a nap 24 órájában! :)", "https://kutyamoso.hu/ujpest/");
        ServiceOffered washAndVauKecskemet = new ServiceOffered("Wash & Vau Kecskemét", PetType.DOG, 4.4, washAndVauKecskemetCon, "Open 24 hours", ServiceType.WELLNESS, ServiceSubtype.WASHANDVAU, "Önkiszolgáló kutyafürdetés gyorsan és egyszerűen kutyamosónk segítségével a nap 24 órájában! :)", "https://kutyamoso.hu/kecskemet/");
        ServiceOffered bbs = new ServiceOffered("BB's Bar & Grill", PetType.DOG, 4.4, bbsCon, "H-Cs: 17:00-00:00 P: 13:00-02:00 Szo: 12:00-02:00 V: 12:00-00:00", ServiceType.RESTAURANT, null, "Relax, party, meet up or just drop by for a beerpong!", "https://bbzbar.hu");
        ServiceOffered hokedli = new ServiceOffered("Hokedli", PetType.DOG, 4.7, hokedliCon, "H-P 11:30-17:00", ServiceType.RESTAURANT, null, "Ebédelj egészségesen magadért és másokért! Gluténmentes, vegetáriánus és vegán ebédek!", "https://www.hokedli.net");
        ServiceOffered wanHao = new ServiceOffered("Wan Hao Restaurant", PetType.DOG, 4.1, wanHaoCon, "H-V 10:00-22:00", ServiceType.RESTAURANT, null, "Local Chinese community eat, shop, and have fun here. 18 Chinese restaurants all in one place, plus 2 Asian food stores. Real Chinese. Real exotic. Real fun.", "http://www.chinatownbudapest.hu/");
        ServiceOffered tereza = new ServiceOffered("Tereza Mexican Restaurant", PetType.DOG, 4.3, terezaCon, "H-P 18:00-00:00 Szo-V: 12:00-00:00", ServiceType.RESTAURANT, null, "MI CASA ES SU CASA! MEXICAN URBAN GARDEN IN DOWNTOWN BUDAPEST. TEREZA HAS TAKEN HER PERMANENT PLACE AND AWAITS ITS GUESTS WITH UNIQUE AMBIENCE, AUTHENTIC MEXICAN CUISINE BY REGIONS AND WILD SELECTION OF MEZCALS AND MARGARITAS.", "https://tereza.hu/");
        ServiceOffered naspolya = new ServiceOffered("Naspolya Nassolda", PetType.DOG, 4.5, naspolyaCon, "K-P 12:00-19:00 Szo-V 10:00-19:00", ServiceType.RESTAURANT, null, "Naspolya is a pioneering downtown café where all the sweets and salty snacks are raw vegan, made from organic fair trade ingredients, without additives or empty calories.", "https://naspolya.hu");

        // add user to service        // circular reference
        washAndVau.setUser(washAndVauUser);
        washAndVau2.setUser(washAndVauUser);
        washAndVau3.setUser(washAndVauUser);
        washAndVau4.setUser(washAndVauUser);
        washAndVauKecskemet.setUser(washAndVauUserKecskemet);
        bbs.setUser(bbsUser);
        hokedli.setUser(hokedliUser);
        wanHao.setUser(wanHaoUser);
        tereza.setUser(terezaUser);
        naspolya.setUser(naspolyaUser);

        // add service to user services
        washAndVauUser.getServices().add(washAndVau);
        washAndVauUser.getServices().add(washAndVau2);
        washAndVauUser.getServices().add(washAndVau3);
        washAndVauUser.getServices().add(washAndVau4);
        washAndVauUserKecskemet.getServices().add(washAndVauKecskemet);
        bbsUser.getServices().add(bbs);
        hokedliUser.getServices().add(hokedli);
        wanHaoUser.getServices().add(wanHao);
        terezaUser.getServices().add(tereza);
        naspolyaUser.getServices().add(naspolya);

        // add user to memory/db
        userDao.add(washAndVauUser);
        userDao.add(washAndVauUserKecskemet);
        userDao.add(bbsUser);
        userDao.add(hokedliUser);
        userDao.add(wanHaoUser);
        userDao.add(terezaUser);
        userDao.add(naspolyaUser);

        // add service to memory/db
        serviceDao.add(washAndVau);
        serviceDao.add(washAndVau2);
        serviceDao.add(washAndVau3);
        serviceDao.add(washAndVau4);
        serviceDao.add(washAndVauKecskemet);
        serviceDao.add(bbs);
        serviceDao.add(hokedli);
        serviceDao.add(wanHao);
        serviceDao.add(tereza);
        serviceDao.add(naspolya);
    }
}
