// EjemplarRepository.java
package com.company.microservice.repository.implementation;

import com.company.microservice.data.DatabaseConfig;
import com.company.microservice.model.Autor;
import com.company.microservice.model.Categoria;
import com.company.microservice.model.Ejemplar;
import com.company.microservice.repository.interfaces.IEjemplarRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EjemplarRepository implements IEjemplarRepository {
    private final Connection connection;

    public EjemplarRepository(DatabaseConfig databaseConfig) throws SQLException {
        this.connection = databaseConfig.getConnection();
    }

    @Override
    public List<Ejemplar> getAllEjemplares() throws SQLException {
        List<Ejemplar> ejemplares = new ArrayList<>();
        try (CallableStatement callStmt = connection.prepareCall("{call sp_GetAllEjemplares()}")) {
            ResultSet resultSet = callStmt.executeQuery();
            while (resultSet.next()) {
                Ejemplar ejemplar = new Ejemplar();
                ejemplar.setEjemId(resultSet.getInt("EJEM_ID"));
                ejemplar.setEjemId(resultSet.getInt("LIB_ID"));
                ejemplar.setEjemId(resultSet.getInt("EJEM_ESTADO"));
                ejemplares.add(ejemplar);
            }
        }
        return ejemplares;
    }


    @Override
    public Ejemplar getEjemplarById(int id) throws SQLException {
        Ejemplar ejemplar = null;
        try (CallableStatement callStmt = connection.prepareCall("{call sp_GetEjemplarById(?)}")) {
            callStmt.setInt(1, id);
            ResultSet resultSet = callStmt.executeQuery();
            if (resultSet.next()) {
                ejemplar = new Ejemplar();
                ejemplar.setEjemId(resultSet.getInt("EJEM_ID"));
                ejemplar.setEjemEstado(resultSet.getBoolean("EJEM_ESTADO"));
                ejemplar.setLibId(resultSet.getInt("LIB_ID"));
            }
        }
        return ejemplar;
    }

    @Override
    public void insertEjemplarProcedure(int libroId, boolean estado) throws SQLException {
        try (CallableStatement statement = connection.prepareCall("{call sp_InsertEjemplar(?, ?)}")) {
            statement.setInt(1, libroId);
            statement.setBoolean(2, estado);
            statement.execute();
        }
    }

    @Override
    public void updateEjemplarProcedure(int ejemId, int libroId, boolean estado) throws SQLException {
        try (CallableStatement statement = connection.prepareCall("{call sp_UpdateEjemplar(?, ?, ?)}")) {
            statement.setInt(1, ejemId);
            statement.setInt(2, libroId);
            statement.setBoolean(3, estado);
            statement.execute();
        }
    }

    @Override
    public void deleteEjemplar(int ejemId) throws SQLException {
        try (CallableStatement statement = connection.prepareCall("{call sp_DeleteEjemplar(?)}")) {
            statement.setInt(1, ejemId);
            statement.execute();
        }
    }


}
