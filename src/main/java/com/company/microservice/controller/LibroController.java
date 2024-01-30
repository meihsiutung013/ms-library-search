package com.company.microservice.controller;

import com.company.microservice.model.Libro;
import com.company.microservice.service.interfaces.IAutorService;
import com.company.microservice.service.interfaces.ILibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final ILibroService libroService;

    @Autowired
    public LibroController(ILibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping
    public ResponseEntity<List<Libro>> getAllLibros() {
        try {
            List<Libro> libros = libroService.getAllLibros();
            return ResponseEntity.ok(libros);
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("")
    public ResponseEntity<String> insertLibro(@RequestBody Libro libro) {
        try {
            libroService.insertLibro(libro);
            return ResponseEntity.ok("Libro insertado correctamente");
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/{libId}")
    public ResponseEntity<Libro> getLibroById(@PathVariable int libId) {
        try {
            Libro libro = libroService.getLibroById(libId);
            if (libro != null) {
                return ResponseEntity.ok(libro);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateLibro(@PathVariable int id, @RequestBody Libro libro) {
        try {
            libroService.updateLibro(id, libro);
            return ResponseEntity.ok("Libro actualizado correctamente");
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLibro(@PathVariable int id) {
        try {
            libroService.deleteLibro(id);
            return ResponseEntity.ok("Libro eliminado correctamente");
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}
