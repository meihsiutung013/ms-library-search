package com.company.microservice.repository.interfaces;

import com.company.microservice.model.Libro;

import java.sql.SQLException;
import java.util.List;

public interface ILibroRepository {

    List<Libro> getAllLibros() throws SQLException;

    void insertLibro(Libro oLibroE) throws SQLException;

    void updateLibro(int libId, Libro OLibroE) throws SQLException;

    void deleteLibro(int libId) throws SQLException;
}
