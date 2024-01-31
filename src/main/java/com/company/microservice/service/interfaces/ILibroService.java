package com.company.microservice.service.interfaces;

import com.company.microservice.model.Libro;

import java.sql.SQLException;
import java.util.List;

public interface ILibroService {

    List<Libro> getAllLibros() throws SQLException;

    Libro getLibroById(int id) throws SQLException;

    void insertLibro(Libro oLibroE) throws SQLException;

    void updateLibro(int libId, Libro oLibroE) throws SQLException;

    void deleteLibro(int libId) throws SQLException;
    List<Libro> searchLibros(String libNombre, Double libPrecioAlquiler, String libAnioPublicacion, String libISBN) throws SQLException;
}
