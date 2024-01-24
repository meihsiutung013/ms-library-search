package com.company.microservice.service.interfaces;

import com.company.microservice.model.Autor;

import java.sql.SQLException;
import java.util.List;

public interface IAutorService {
    List<Autor> getAllAutores() throws SQLException;

    void insertAutor(String nombre) throws SQLException;

    void updateAutor(int autorId, String nombre) throws SQLException;

    void deleteAutor(int autorId) throws SQLException;
}
