package com.codecool.PawPrint.model.entity;

import com.codecool.PawPrint.model.service.ServiceOffered;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
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
    @ManyToOne
    private User user;
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "searches")
    private Set<ServiceOffered> searchedServices = new HashSet<>();

    public Search(Set<ServiceOffered> searchedServices) {
        this.searchedServices = searchedServices;
    }


}
