package com.wrede.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String path;

    private String format;

    private String type;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="measurement_point_id")
    private MeasurementPoint measurementPoint;
}
