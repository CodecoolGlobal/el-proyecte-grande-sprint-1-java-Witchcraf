package com.codecool.PawPrint.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public abstract class User {
    private int id;
}
