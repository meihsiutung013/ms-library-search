package com.company.microservice.repository.implementation;

import com.company.microservice.data.DatabaseConfig;
import com.company.microservice.model.Autor;
import com.company.microservice.model.Categoria;
import com.company.microservice.repository.interfaces.ICategoriaRepository;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoriaRepository implements ICategoriaRepository {
    private final Connection connection;

    public CategoriaRepository(DatabaseConfig databaseConfig) throws SQLException {
        this.connection = databaseConfig.getConnection();
    }

    @Override
    public List<Categoria> getAllCategorias() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        try (CallableStatement callStmt = connection.prepareCall("{call sp_GetAllCategorias()}")) {
            ResultSet resultSet = callStmt.executeQuery();
            while (resultSet.next()) {
                Categoria categoria = new Categoria();
                categoria.setCatId(resultSet.getInt("CAT_ID"));
                categoria.setCatNombre(resultSet.getString("CAT_NOMBRE"));
                categorias.add(categoria);
            }
        }
        return categorias;
    }

    @Override
    public Categoria getCategoriaById(int id) throws SQLException{
        Categoria categoria = null;
        try (CallableStatement callStmt = connection.prepareCall("{call sp_GetCategoriById(?)}")) {
            callStmt.setInt(1, id);
            ResultSet resultSet = callStmt.executeQuery();
            if (resultSet.next()) {

                categoria = new Categoria();
                categoria.setCatId(resultSet.getInt("CAT_ID"));
                categoria.setCatNombre(resultSet.getString("CAT_NOMBRE"));
            }
        }
        return categoria;
    }


    @Override
    public void insertCategoria(String nombre) throws SQLException {
        try (CallableStatement callStmt = connection.prepareCall("{call sp_InsertCategoria(?)}")) {
            callStmt.setString(1, nombre);
            callStmt.executeUpdate();
        }
    }

    @Override
    public void updateCategoria(int catId, String nombre) throws SQLException {
        try (CallableStatement callStmt = connection.prepareCall("{call sp_UpdateCategoria(?, ?)}")) {
            callStmt.setInt(1, catId);
            callStmt.setString(2, nombre);
            callStmt.executeUpdate();
        }
    }

    @Override
    public void deleteCategoria(int catId) throws SQLException {
        try (CallableStatement callStmt = connection.prepareCall("{call sp_DeleteCategoria(?)}")) {
            callStmt.setInt(1, catId);
            callStmt.executeUpdate();
        }
    }
}
