package com.company.microservice.repository.implementation;

import com.company.microservice.data.DatabaseConfig;
import com.company.microservice.model.Autor;
import com.company.microservice.repository.interfaces.IAutorRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM autor");
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
    public void insertAutor(String nombre) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String query = "INSERT INTO AUTOR (AUT_NOMBRE) VALUES ('" + nombre + "')";
            statement.executeUpdate(query);
        }
    }
}
