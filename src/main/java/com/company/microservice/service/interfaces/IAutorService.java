package com.company.microservice.service.interfaces;

import com.company.microservice.model.Autor;
import com.company.microservice.model.AutorDto;
import com.company.microservice.model.Libro;
import com.company.microservice.request.CreateAutorRequest;

import java.sql.SQLException;
import java.util.List;

public interface IAutorService {
    List<Autor> getAllAutores(String nombre) throws SQLException;

    Autor getAutor(String id) throws SQLException;

    Autor insertAutor(CreateAutorRequest nombre) throws SQLException;

    Autor updateAutor(String autorId, String updateRequest) throws SQLException;

    Autor updateAutor(String autorId, AutorDto updateRequest) throws SQLException;

    Boolean deleteAutor(String autorId) throws SQLException;
}
