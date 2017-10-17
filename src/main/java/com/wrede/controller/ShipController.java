package com.wrede.controller;

import com.google.common.collect.Lists;
import com.wrede.domain.Measurement;
import com.wrede.domain.Ship;
import com.wrede.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ships")
public class ShipController {

    @Autowired
    private ShipRepository repository;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Ship>> getShips() {
        return new ResponseEntity<>(Lists.newArrayList(repository.findAll()), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ship> addShip(@RequestBody @Valid Ship ship) {

        if (ship.getId() != null) {
            Ship loadedEntry = repository.findOne(ship.getId());
            if (loadedEntry != null) {
                loadedEntry = ship;
                repository.save(loadedEntry);
                return new ResponseEntity<>(loadedEntry, HttpStatus.OK);
            } else {
                ship.setId(null);
                Ship savedEntry = repository.save(ship);
                return new ResponseEntity<>(savedEntry, HttpStatus.CREATED);
            }
        } else {
            Ship savedEntry = repository.save(ship);
            return new ResponseEntity<>(savedEntry, HttpStatus.CREATED);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ship> getShip(@PathVariable long id) {
        Ship ship = repository.findOne(id);
        if (ship != null) {
            return new ResponseEntity<>(repository.findOne(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{id}/measurements", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Measurement>> getMeasurementsForShip(@PathVariable long id) {
        Ship ship = repository.findOne(id);
        if (ship != null) {
            return new ResponseEntity<>(ship.getMeasurements(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{id}/measurements", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType
            .APPLICATION_JSON_VALUE)
    public ResponseEntity<Measurement> addMeasurementForShip(@PathVariable long id, @RequestBody @Valid Measurement measurement) {
        Ship ship = repository.findOne(id);
        if (ship != null) {
            ship.addMeasurement(measurement);
            repository.save(ship);
            return new ResponseEntity<>(measurement, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
