package com.codecool.PawPrint.model.controllerEntity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SaveSearchEntity {

    private String username;
    private String searchName;
    private String description;
    private List<Integer> searchedServices = new ArrayList<>();
}
