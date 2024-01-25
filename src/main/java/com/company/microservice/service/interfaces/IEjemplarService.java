package com.company.microservice.service.interfaces;

import com.company.microservice.model.Ejemplar;

import java.sql.SQLException;
import java.util.List;

public interface IEjemplarService {
    List<Ejemplar> getAllEjemplares() throws SQLException;


    void insertEjemplarProcedure(int libroId, boolean estado) throws SQLException;    /*
    Ejemplar getEjemplarById(int id) throws SQLException;
    void updateEjemplar(Ejemplar ejemplar) throws SQLException;
    void deleteEjemplar(int id) throws SQLException;*/
}
