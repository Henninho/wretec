package com.wrede.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class MeasurementDevice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String type;

    private String supplier;

    private double calibrationFactor;

    @OneToMany(mappedBy = "measurementDevice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MeasurementPoint> measurementPoints = new ArrayList<>();

    public void addMeasurementPoints(MeasurementPoint measurementPoint) {
        measurementPoints.add(measurementPoint);
        measurementPoint.setMeasurementDevice(this);
    }

    public void removeMeasurementPoints(MeasurementPoint measurementPoint) {
        measurementPoints.remove(measurementPoint);
        measurementPoint.setMeasurementDevice(this);
    }
}
