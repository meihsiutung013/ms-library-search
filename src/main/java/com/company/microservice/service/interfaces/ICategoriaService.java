package com.company.microservice.service.interfaces;

import com.company.microservice.model.Autor;
import com.company.microservice.model.Categoria;

import java.sql.SQLException;
import java.util.List;

public interface ICategoriaService {
    List<Categoria> getAllCategorias() throws SQLException;
    Categoria getCategoriaById(int id) throws SQLException;
    void insertCategoria(String nombre) throws SQLException;
    void updateCategoria(int catId, String nombre) throws SQLException;
    void deleteCategoria(int catId) throws SQLException;
}
