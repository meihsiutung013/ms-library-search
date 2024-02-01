package com.company.microservice.controller;

import com.company.microservice.model.Libro;
import com.company.microservice.service.interfaces.ILibroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/libros")
@Tag(name = "Libros", description = "APIs para gestión de libros")
public class LibroController {

    private final ILibroService libroService;

    @Autowired
    public LibroController(ILibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping
    @Operation(summary = "Recupera una lista de libros.")
    public ResponseEntity<List<Libro>> getAllLibros(
            @RequestParam(required = false) String libNombre,
            @RequestParam(required = false) Double libPrecioAlquiler,
            @RequestParam(required = false) String libAnioPublicacion,
            @RequestParam(required = false) String libISBN
    ) {
        try {
            List<Libro> libros = libroService.searchLibros(libNombre, libPrecioAlquiler, libAnioPublicacion, libISBN);
            return ResponseEntity.ok(libros);
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping
    @Operation(
            summary = "Registra un nuevo libro.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del libro.",
                    required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Libro.class))))
    public ResponseEntity<String> insertLibro(@RequestBody Libro libro) {
        try {
            libroService.insertLibro(libro);
            return ResponseEntity.ok("Libro insertado correctamente");
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Recupera un libro específico por ID.")
    public ResponseEntity<Libro> getLibroById(@PathVariable int id) {
        try {
            Libro libro = libroService.getLibroById(id);
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
    @Operation(
            summary = "Modifica todos los datos de un libro.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del libro a actualizar.",
                    required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Libro.class))))
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
    @Operation(summary = "Elimina un libro específico por ID.")
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
