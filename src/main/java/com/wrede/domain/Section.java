package com.wrede.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "measurement_id")
    private Measurement measurement;

    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MeasurementPoint> measurementPoints = new ArrayList<>();

    public void addMeasurementPoint(MeasurementPoint measurementPoint) {
        measurementPoints.add(measurementPoint);
        measurementPoint.setSection(this);
    }

    public void removeMeasurementPoint(MeasurementPoint measurementPoint) {
        measurementPoints.remove(measurementPoint);
        measurementPoint.setSection(null);
    }

}
