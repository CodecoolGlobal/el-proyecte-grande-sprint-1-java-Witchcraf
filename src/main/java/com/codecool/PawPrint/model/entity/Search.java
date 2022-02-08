package com.codecool.PawPrint.model.entity;

import com.codecool.PawPrint.model.service.ServiceOffered;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"user", "searchedServices"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "search")
public class Search {

    @Id
    @SequenceGenerator(
            name = "search_sequence",
            sequenceName = "search_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "search_sequence"
    )

    private int id;
    private String name = "MySearch";
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("savedSearches")
    private User user;
    @ManyToMany
    @JoinTable(
            name = "saved_searches",
            joinColumns = @JoinColumn(name = "search_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id"))
    @JsonIgnoreProperties("searches")
    private Set<ServiceOffered> searchedServices = new HashSet<>();

//    public Search(Set<ServiceOffered> searchedServices) {
//        this.searchedServices = searchedServices;
//    }


}
