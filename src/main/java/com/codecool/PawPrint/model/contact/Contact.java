package com.codecool.PawPrint.model.contact;

import com.codecool.PawPrint.model.entity.User;

public class Contact {

    private int id;
    private User user;
    private Address address;
    private String phone;
    private String businessPhone;
    private String businessEmail;

    public Contact(User user, Address address) {
        this.user = user;
        this.address = address;
    }
}
