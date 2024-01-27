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
@RequestMapping("/api/ejemplar")
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

    @PostMapping("/insertEjemplar")
    public ResponseEntity<String> insertEjemplarProcedure(@RequestBody InsertEjemplarRequest request) {
        try {
            ejemplarService.insertEjemplarProcedure(request.getLibroId(), request.isEstado());
            return ResponseEntity.ok("Ejemplar insertado correctamente");
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @PutMapping("/updateProcedure")
    public ResponseEntity<String> updateEjemplarProcedure(@RequestBody Ejemplar request) {
        try {
            ejemplarService.updateEjemplarProcedure(request.getEjemId(), request.getLibId(), request.isEjemEstado());
            return ResponseEntity.ok("Ejemplar actualizado correctamente");
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/ejemplares/{id}")
    public ResponseEntity<String> deleteEjemplar(@PathVariable int id) {
        try {
            ejemplarService.deleteEjemplar(id);
            return ResponseEntity.ok("Ejemplar eliminado correctamente");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

}
