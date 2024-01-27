package com.company.microservice.repository.interfaces;

import com.company.microservice.model.Autor;

import java.sql.SQLException;
import java.util.List;

public interface IAutorRepository {
    List<Autor> getAllAutores() throws SQLException;
    Autor getAutorById(int id) throws SQLException;
    void insertAutor(String nombre) throws SQLException;
    void updateAutor(int autorId, String nombre) throws SQLException;
    void deleteAutor(int autorId) throws SQLException;
}
