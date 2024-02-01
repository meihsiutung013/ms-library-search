package com.company.microservice.controller;

import com.company.microservice.model.Categoria;
import com.company.microservice.service.interfaces.ICategoriaService;
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
@RequestMapping("/api/categorias")
@Tag(name = "Categorías", description = "APIs para gestión de categorías")
public class CategoriaController {
    private final ICategoriaService categoriaService;

    @Autowired
    public CategoriaController(ICategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    @Operation(summary = "Recupera una lista de categorías de libros.")
    public ResponseEntity<List<Categoria>> getAllCategorias() {
        try {
            List<Categoria> categorias = categoriaService.getAllCategorias();
            return ResponseEntity.ok(categorias);
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Recupera una categoría específica por ID.")
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable int id) {
        try {
            Categoria categoria = categoriaService.getCategoriaById(id);
            if(categoria != null){
                return ResponseEntity.ok(categoria);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("")
    @Operation(
            summary = "Registra una nueva categoría.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos de la categoría.",
                    required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Categoria.class))))
    public ResponseEntity<String> insertCategoria(@RequestBody Categoria categoria) {
        try {
            categoriaService.insertCategoria(categoria.getCatNombre());
            return ResponseEntity.ok("Categoría insertada correctamente");
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Modifica todos los datos de una categoría.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos de la categoría a actualizar.",
                    required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Categoria.class))))
    public ResponseEntity<String> updateCategoria(@PathVariable int id, @RequestBody Categoria categoria) {
        try {
            categoriaService.updateCategoria(id, categoria.getCatNombre());
            return ResponseEntity.ok("Categoria actualizada correctamente");
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina una categoría específica por ID.")
    public ResponseEntity<String> deleteCategoria(@PathVariable int id) {
        try {
            categoriaService.deleteCategoria(id);
            return ResponseEntity.ok("Categoría eliminada correctamente");
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}
