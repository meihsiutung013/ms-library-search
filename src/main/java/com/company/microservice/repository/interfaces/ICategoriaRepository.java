package com.company.microservice.repository.interfaces;

import com.company.microservice.model.Categoria;

import java.sql.SQLException;
import java.util.List;

public interface ICategoriaRepository {

    List<Categoria> getAllCategorias() throws SQLException;

    void insertCategoria(String nombre) throws SQLException;

    void updateCategoria(int catId, String nombre) throws SQLException;

    void deleteCategoria(int catId) throws SQLException;
}
