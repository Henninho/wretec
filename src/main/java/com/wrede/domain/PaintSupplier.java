package com.wrede.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class PaintSupplier {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String address;

    private String contactPerson;

    private String phone;

    private String email;

    @OneToMany(mappedBy = "paintSupplier", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Ship> ships = new ArrayList<>();

    public void addShip(Ship ship) {
        ships.add(ship);
        ship.setPaintSupplier(this);
    }

    public void removeShip(Ship ship) {
        ships.remove(ship);
        ship.setPaintSupplier(null);
    }
}
