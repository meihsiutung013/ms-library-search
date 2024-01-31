package com.company.microservice.repository.implementation;

import com.company.microservice.data.DatabaseConfig;
import com.company.microservice.model.Autor;
import com.company.microservice.repository.interfaces.IAutorRepository;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AutorRepository implements IAutorRepository {
    private final Connection connection;

    public AutorRepository(DatabaseConfig databaseConfig) throws SQLException {
        this.connection = databaseConfig.getConnection();
    }

    @Override
    public List<Autor> getAllAutores() throws SQLException {
        List<Autor> autores = new ArrayList<>();
        try (CallableStatement callStmt = connection.prepareCall("{call sp_GetAllAutores()}")) {
            ResultSet resultSet = callStmt.executeQuery();
            while (resultSet.next()) {
                Autor autor = new Autor();
                autor.setId(resultSet.getInt("AUT_ID"));
                autor.setNombre(resultSet.getString("AUT_NOMBRE"));
                autores.add(autor);
            }
        }
        return autores;
    }

    @Override
    public Autor getAutorById(int id) throws SQLException {
        Autor autor = null;
        try (CallableStatement callStmt = connection.prepareCall("{call sp_GetAutorById(?)}")) {
            callStmt.setInt(1, id);
            ResultSet resultSet = callStmt.executeQuery();
            if (resultSet.next()) {
                autor = new Autor();
                autor.setId(resultSet.getInt("AUT_ID"));
                autor.setNombre(resultSet.getString("AUT_NOMBRE"));
            }
        }
        return autor;
    }

    @Override
    public void insertAutor(String nombre) throws SQLException {
        try (CallableStatement callStmt = connection.prepareCall("{call sp_InsertAutor(?)}")) {
            callStmt.setString(1, nombre);
            callStmt.executeUpdate();
        }
    }

    @Override
    public void deleteAutor(int autorId) throws SQLException {
        try (CallableStatement callStmt = connection.prepareCall("{call sp_DeleteAutor(?)}")) {
            callStmt.setInt(1, autorId);
            callStmt.executeUpdate();
        }
    }

    @Override
    public void updateAutor(int autorId, String nombre) throws SQLException {
        try (CallableStatement callStmt = connection.prepareCall("{call sp_UpdateAutor(?, ?)}")) {
            callStmt.setInt(1, autorId);
            callStmt.setString(2, nombre);
            callStmt.executeUpdate();
        }
    }
}
