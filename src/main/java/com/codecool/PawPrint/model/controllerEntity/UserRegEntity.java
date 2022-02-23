package com.codecool.PawPrint.model.controllerEntity;

import com.codecool.PawPrint.model.entity.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserRegEntity {

    private String username;
    private String email;
    private String password;
    private String fullname;
    private String birthday;
    private Gender gender;
    @JsonProperty
    private boolean isService;

}
