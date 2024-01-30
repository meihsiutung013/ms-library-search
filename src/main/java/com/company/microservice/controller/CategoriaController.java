package com.company.microservice.controller;

import com.company.microservice.model.Autor;
import com.company.microservice.model.Categoria;
import com.company.microservice.service.interfaces.IAutorService;
import com.company.microservice.service.interfaces.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {
    private final ICategoriaService categoriaService;

    @Autowired
    public CategoriaController(ICategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
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
