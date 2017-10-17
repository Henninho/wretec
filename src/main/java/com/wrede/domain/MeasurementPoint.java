package com.wrede.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class MeasurementPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private double value;

    private double correctedValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id")
    private Section section;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="picture_id")
    private Picture picture;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "measurement_device_id")
    private MeasurementDevice measurementDevice;

}
