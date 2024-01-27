package com.company.microservice.service.implementation;

import com.company.microservice.model.Autor;
import com.company.microservice.model.Categoria;
import com.company.microservice.repository.interfaces.ICategoriaRepository;
import com.company.microservice.service.interfaces.ICategoriaService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class CategoriaService implements ICategoriaService {
    private final ICategoriaRepository categoriaRepository;

    public CategoriaService(ICategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }
    @Override
    public Categoria getCategoriaById(int id) throws SQLException {
        return categoriaRepository.getCategoriaById(id);
    }

    @Override
    public List<Categoria> getAllCategorias() throws SQLException {
        return categoriaRepository.getAllCategorias();
    }
    @Override
    public void insertCategoria(String nombre) throws SQLException {
        categoriaRepository.insertCategoria(nombre);
    }
    @Override
    public void updateCategoria(int catId, String nombre) throws SQLException {
        categoriaRepository.updateCategoria(catId,nombre);
    }
    @Override
    public void deleteCategoria(int catId) throws SQLException {
        categoriaRepository.deleteCategoria(catId);
    }
}
