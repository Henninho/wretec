package com.wrede.controller;

import com.google.common.collect.Lists;
import com.wrede.domain.PaintSupplier;
import com.wrede.domain.Ship;
import com.wrede.repository.PaintSupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/paintSuppliers")
public class PaintSupplierController {

    @Autowired
    private PaintSupplierRepository repository;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PaintSupplier>> getPaintSuppliers() {

        return new ResponseEntity<>(Lists.newArrayList(repository.findAll()), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaintSupplier> addPaintSupplier(@RequestBody @Valid PaintSupplier paintSupplier) {

        if (paintSupplier.getId() != null) {
            PaintSupplier loadedEntry = repository.findOne(paintSupplier.getId());
            if (loadedEntry != null) {
                loadedEntry = paintSupplier;
                repository.save(loadedEntry);
                return new ResponseEntity<>(loadedEntry, HttpStatus.OK);
            } else {
                paintSupplier.setId(null);
                PaintSupplier savedEntry = repository.save(paintSupplier);
                return new ResponseEntity<>(savedEntry, HttpStatus.CREATED);
            }
        } else {
            PaintSupplier savedEntry = repository.save(paintSupplier);
            return new ResponseEntity<>(savedEntry, HttpStatus.CREATED);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaintSupplier> getPaintSupplier(@PathVariable long id) {
        PaintSupplier paintSupplier = repository.findOne(id);
        if (paintSupplier != null) {
            return new ResponseEntity<>(repository.findOne(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{id}/ships", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Ship>> getShipsForPaintSupplier(@PathVariable long id) {
        PaintSupplier paintSupplier = repository.findOne(id);
        if (paintSupplier != null) {
            return new ResponseEntity<>(paintSupplier.getShips(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{id}/ships", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType
            .APPLICATION_JSON_VALUE)
    public ResponseEntity<Ship> addShipForPaintSupplier(@PathVariable long id, @RequestBody @Valid Ship ship) {
        PaintSupplier paintSupplier = repository.findOne(id);
        if (paintSupplier != null) {
            paintSupplier.addShip(ship);
            repository.save(paintSupplier);
            return new ResponseEntity<>(ship, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
