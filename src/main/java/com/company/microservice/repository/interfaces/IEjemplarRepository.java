// IEjemplarRepository.java
package com.company.microservice.repository.interfaces;

import com.company.microservice.model.Ejemplar;

import java.sql.SQLException;
import java.util.List;

public interface IEjemplarRepository {
    List<Ejemplar> getAllEjemplares() throws SQLException;
    Ejemplar getEjemplarById(int id) throws SQLException;
    void insertEjemplarProcedure(int libroId, boolean estado) throws SQLException;
    void updateEjemplarProcedure(int ejemId, int libroId, boolean estado) throws SQLException;
    void deleteEjemplar(int ejemId) throws SQLException ;

}
