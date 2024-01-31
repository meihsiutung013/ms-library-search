package com.company.microservice.service.implementation;

import com.company.microservice.model.Libro;
import com.company.microservice.repository.interfaces.ILibroRepository;
import com.company.microservice.service.interfaces.ILibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.SQLException;
import java.util.List;

@Service
public class LibroService implements ILibroService {
    private final ILibroRepository libroRepository;

    @Autowired
    public LibroService(ILibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @Override
    public List<Libro> searchLibros(String libNombre, Double libPrecioAlquiler, String libAnioPublicacion, String libISBN) throws SQLException {
        return libroRepository.searchLibros(libNombre, libPrecioAlquiler, libAnioPublicacion, libISBN);
    }

    @Override
    public List<Libro> getAllLibros() throws SQLException {
        return libroRepository.getAllLibros();
    }

    @Override
    public Libro getLibroById(int id) throws SQLException {
        return libroRepository.getLibroById(id);
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
