package com.company.microservice.controller;

import com.company.microservice.model.Autor;
import com.company.microservice.service.interfaces.IAutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/autores")
public class Controller {
    private final IAutorService autorService;

    @Autowired
    public Controller(IAutorService autorService) {
        this.autorService = autorService;
    }

    @GetMapping
    public ResponseEntity<List<Autor>> getAllAutores() {
        try {
            List<Autor> autores = autorService.getAllAutores();
            return ResponseEntity.ok(autores);
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/insertarAutor")
    public ResponseEntity<String> insertAutor() {
        try {
            autorService.insertAutor("Nuevo Autor");
            return ResponseEntity.ok("Autor insertado correctamente");
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}
