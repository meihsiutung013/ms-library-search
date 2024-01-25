// EjemplarController.java
package com.company.microservice.controller;

import com.company.microservice.model.Ejemplar;
import com.company.microservice.request.InsertEjemplarRequest;
import com.company.microservice.service.interfaces.IEjemplarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/ejemplares")
public class EjemplarController {
    private final IEjemplarService ejemplarService;

    @Autowired
    public EjemplarController(IEjemplarService ejemplarService) {
        this.ejemplarService = ejemplarService;
    }

    @GetMapping
    public ResponseEntity<List<Ejemplar>> getAllEjemplares() {
        try {
            List<Ejemplar> ejemplares = ejemplarService.getAllEjemplares();
            return ResponseEntity.ok(ejemplares);
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/insertarProcedure")
    public ResponseEntity<String> insertarEjemplarProcedure(@RequestBody InsertEjemplarRequest request) {
        try {
            ejemplarService.insertEjemplarProcedure(request.getLibroId(), request.isEstado());
            return ResponseEntity.ok("Ejemplar insertado correctamente");
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
/*
    @GetMapping("/{id}")
    public ResponseEntity<Ejemplar> getEjemplarById(@PathVariable int id) {
        try {
            Ejemplar ejemplar = ejemplarService.getEjemplarById(id);
            return ResponseEntity.ok(ejemplar);
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping
    public ResponseEntity<String> insertEjemplar(@RequestBody Ejemplar ejemplar) {
        try {
            ejemplarService.insertEjemplar(ejemplar);
            return ResponseEntity.ok("Ejemplar insertado correctamente");
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEjemplar(@PathVariable int id, @RequestBody Ejemplar ejemplar) {
        try {
            ejemplarService.updateEjemplar(ejemplar);
            return ResponseEntity.ok("Ejemplar actualizado correctamente");
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEjemplar(@PathVariable int id) {
        try {
            ejemplarService.deleteEjemplar(id);
            return ResponseEntity.ok("Ejemplar eliminado correctamente");
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }*/
}
