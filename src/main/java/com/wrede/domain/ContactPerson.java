package com.wrede.domain;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ContactPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String surname;

    private String phone;

    private String email;

    private String role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ship_id")
    private Ship ship;
}
