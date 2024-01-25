package com.company.microservice.service.interfaces;

import com.company.microservice.model.Ejemplar;

import java.sql.SQLException;
import java.util.List;

public interface IEjemplarService {
    List<Ejemplar> getAllEjemplares() throws SQLException;
/*
    Ejemplar getEjemplarById(int id) throws SQLException;

    void insertEjemplar(Ejemplar ejemplar) throws SQLException;

    void updateEjemplar(Ejemplar ejemplar) throws SQLException;

    void deleteEjemplar(int id) throws SQLException;*/
}
