package com.company.microservice.service.implementation;

import com.company.microservice.model.Autor;
import com.company.microservice.repository.interfaces.IAutorRepository;
import com.company.microservice.service.interfaces.IAutorService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class AutorService implements IAutorService {
    private final IAutorRepository autorRepository;

    public AutorService(IAutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @Override
    public List<Autor> getAllAutores() throws SQLException {
        return autorRepository.getAllAutores();
    }

    @Override
    public void insertAutor(String nombre) throws SQLException {
        autorRepository.insertAutor(nombre);
    }
}
