package com.codecool.PawPrint.model.contact;

import com.codecool.PawPrint.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contact")
public class Contact {

    @Id     // better way?
    @SequenceGenerator(
            name = "contact_sequence",
            sequenceName = "contact_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "contact_sequence"
    )

    private int id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    @ToString.Exclude
    private Address address;
    private String phone;
    private String email;

    public Contact(String phone) {
        this.phone = phone;
    }

}
