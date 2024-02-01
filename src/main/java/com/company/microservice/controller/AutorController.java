package com.company.microservice.controller;

import com.company.microservice.model.Autor;
import com.company.microservice.service.interfaces.IAutorService;
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
@RequestMapping("/api/autores")
@Tag(name = "Autores", description = "APIs para gestión de autores")
public class AutorController {

    private final IAutorService autorService;

    @Autowired
    public AutorController(IAutorService autorService) {
        this.autorService = autorService;
    }

    @GetMapping
    @Operation(summary = "Recupera una lista de autores de libros.")
    public ResponseEntity<List<Autor>> getAllAutores() {
        try {
            List<Autor> autores = autorService.getAllAutores();
            return ResponseEntity.ok(autores);
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Recupera un autor específico por ID.")
    public ResponseEntity<Autor> getAutorById(@PathVariable int id) {
        try {
            Autor autor = autorService.getAutorById(id);
            if(autor != null){
                return ResponseEntity.ok(autor);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping
    @Operation(
            summary = "Registra un nuevo autor.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del autor.",
                    required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Autor.class))))
    public ResponseEntity<String> insertAutor(@RequestBody Autor autor) {
        try {
            autorService.insertAutor(autor.getNombre());
            return ResponseEntity.ok("Autor insertado correctamente");
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Modifica todos los datos de un autor.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del autor a actualizar.",
                    required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Autor.class))))
    public ResponseEntity<String> updateAutor(@PathVariable int id, @RequestBody Autor autor) {
        try {
            autorService.updateAutor(id, autor.getNombre());
            return ResponseEntity.ok("Autor actualizado correctamente");
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un autor específico por ID.")
    public ResponseEntity<String> deleteAutor(@PathVariable int id) {
        try {
            autorService.deleteAutor(id);
            return ResponseEntity.ok("Autor eliminado correctamente");
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}
