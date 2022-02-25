package com.codecool.PawPrint.repository;

import com.codecool.PawPrint.model.entity.Search;

public interface SearchDao {
    void add(Search search);

    Search findById(int searchId);
}
