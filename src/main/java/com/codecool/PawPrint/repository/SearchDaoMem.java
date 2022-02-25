package com.codecool.PawPrint.repository;

import com.codecool.PawPrint.model.entity.Search;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Qualifier("searchDaoMem")
public class SearchDaoMem implements SearchDao {

    private final List<Search> searches = new ArrayList<>();


    @Override
    public void add(Search search) {
        searches.add(search);
    }

    @Override
    public Search findById(int searchId) {
        return searches.stream().filter(search -> search.getId() == searchId).findFirst().orElse(null);
    }
}
