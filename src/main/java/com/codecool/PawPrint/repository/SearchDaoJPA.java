package com.codecool.PawPrint.repository;

import com.codecool.PawPrint.model.entity.Search;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("searchDaoJPA")
@Primary
@RequiredArgsConstructor
public class SearchDaoJPA implements SearchDao {

    private final SearchRepository searchRepository;

    @Override
    public void add(Search search) {
        searchRepository.save(search);
    }
}
