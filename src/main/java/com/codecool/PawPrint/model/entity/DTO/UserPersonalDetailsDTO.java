package com.codecool.PawPrint.model.entity.DTO;

import com.codecool.PawPrint.model.contact.Contact;
import com.codecool.PawPrint.model.entity.*;
import com.codecool.PawPrint.model.service.ServiceOffered;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class UserPersonalDetailsDTO {
    private int id;
    private String userName;
    private LocalDateTime registrationTime;
    private String email;
    private String password;
    private UserType type;;

}
