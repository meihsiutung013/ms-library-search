// IEjemplarRepository.java
package com.company.microservice.repository.interfaces;

import com.company.microservice.model.Ejemplar;

import java.sql.SQLException;
import java.util.List;

public interface IEjemplarRepository {
    List<Ejemplar> getAllEjemplares() throws SQLException;

    /*Ejemplar getEjemplarById(int id) throws SQLException;

    void insertEjemplar(Ejemplar ejemplar) throws SQLException;

    void updateEjemplar(Ejemplar ejemplar) throws SQLException;

    void deleteEjemplar(int id) throws SQLException;

    // Otros métodos específicos para Ejemplar, si es necesario*/
}
