package com.codecool.PawPrint.model.entity;

import com.codecool.PawPrint.model.service.ServiceOffered;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@JsonIgnoreProperties(value = {"user", "searchedServices"})
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
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonProperty("user")
    @JsonIgnoreProperties("savedSearches")
    @ToString.Exclude
    private User user;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "saved_searches",
            joinColumns = @JoinColumn(name = "search_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id"))
    @JsonProperty("searchedServices")
    @JsonIgnoreProperties("searches")
    @ToString.Exclude
    private Set<ServiceOffered> searchedServices = new HashSet<>();

//    public Search(Set<ServiceOffered> searchedServices) {
//        this.searchedServices = searchedServices;
//    }


}
