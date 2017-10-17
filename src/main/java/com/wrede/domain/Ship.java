package com.wrede.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Ship {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String mmsi;

    private String paint;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paint_supplier_id")
    @JsonIgnore
    private PaintSupplier paintSupplier;

    @OneToMany(mappedBy = "ship", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<ContactPerson> contactPersons = new ArrayList<>();

    @OneToMany(mappedBy = "ship", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Measurement> measurements = new ArrayList<>();

    public void addContactPerson(ContactPerson contactPerson) {
        contactPersons.add(contactPerson);
        contactPerson.setShip(this);
    }

    public void removeContactPerson(ContactPerson contactPerson) {
        contactPersons.remove(contactPerson);
        contactPerson.setShip(null);
    }

    public void addMeasurement(Measurement measurement) {
        measurements.add(measurement);
        measurement.setShip(this);
    }

    public void removeMeasurement(Measurement measurement) {
        measurements.remove(measurement);
        measurement.setShip(null);
    }

}
