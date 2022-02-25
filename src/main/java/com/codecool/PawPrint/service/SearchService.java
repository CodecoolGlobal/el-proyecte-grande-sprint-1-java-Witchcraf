package com.codecool.PawPrint.service;

import com.codecool.PawPrint.model.entity.Search;
import com.codecool.PawPrint.repository.SearchDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

    private final SearchDao searchDao;

    @Autowired
    public SearchService(@Qualifier("searchDaoJPA") SearchDao searchDao) {
        this.searchDao = searchDao;
    }

    public Search getSearchById(int searchId) {
        return searchDao.findById(searchId);
    }
}
