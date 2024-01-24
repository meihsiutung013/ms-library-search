package com.company.microservice.service.implementation;

import com.company.microservice.model.Libro;
import com.company.microservice.repository.interfaces.ILibroRepository;
import com.company.microservice.service.interfaces.ILibroService;

import java.sql.SQLException;
import java.util.List;

public class LibroService implements ILibroService {
    private final ILibroRepository libroRepository;

    public LibroService(ILibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @Override
    public List<Libro> getAllLibros() throws SQLException {
        return libroRepository.getAllLibros();
    }

    @Override
    public void insertLibro(Libro oLibroE) throws SQLException {
        libroRepository.insertLibro(oLibroE);
    }

    @Override
    public void updateLibro(int libId, Libro oLibroE) throws SQLException {
        libroRepository.updateLibro(libId,oLibroE);
    }

    @Override
    public void deleteLibro(int libId) throws SQLException {
        libroRepository.deleteLibro(libId);
    }
}
