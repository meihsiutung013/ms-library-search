package com.company.microservice.repository.implementation;

import com.company.microservice.data.DatabaseConfig;
import com.company.microservice.model.Libro;
import com.company.microservice.repository.interfaces.ILibroRepository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibroRepository implements ILibroRepository {

    private final Connection connection;

    public LibroRepository(DatabaseConfig databaseConfig) throws SQLException {
        this.connection = databaseConfig.getConnection();
    }

    @Override
    public List<Libro> getAllLibros() throws SQLException {
        List<Libro> libros = new ArrayList<>();
        try (CallableStatement callStmt = connection.prepareCall("{call sp_get_all_libros()}")) {
            ResultSet resultSet = callStmt.executeQuery();
            while (resultSet.next()) {
                Libro libro = new Libro();
                libro.setLibId(resultSet.getInt("lib_id"));
                libro.setAutId(resultSet.getInt("aut_id"));
                libro.setCatId(resultSet.getInt("cat_id"));
                libro.setLibNombre(resultSet.getString("lib_nombre"));
                libro.setLibPrecioAlquiler(resultSet.getDouble("lib_precio_alquiler"));
                libro.setLibAnioPublicacion(resultSet.getString("lib_anio_publicacion"));
                libro.setLibISBN(resultSet.getString("lib_isbn"));
                libros.add(libro);
            }
        }
        return libros;
    }

    @Override
    public void insertLibro(Libro oLibroE) throws SQLException {
        try (CallableStatement callStmt = connection.prepareCall("{call sp_insert_libro(?, ?, ?, ?, ?, ?)}")) {
            callStmt.setInt(1, oLibroE.getAutId());
            callStmt.setInt(2, oLibroE.getCatId());
            callStmt.setString(3, oLibroE.getLibNombre());
            callStmt.setDouble(4, oLibroE.getLibPrecioAlquiler());
            callStmt.setString(5, oLibroE.getLibAnioPublicacion());
            callStmt.setString(6, oLibroE.getLibISBN());
            callStmt.executeUpdate();
        }
    }

    @Override
    public void updateLibro(int libId, Libro oLibroE) throws SQLException {
        try (CallableStatement callStmt = connection.prepareCall("{call sp_update_libro(?, ?, ?, ?, ?, ?, ?)}")) {
            callStmt.setInt(1, libId);
            callStmt.setInt(2, oLibroE.getAutId());
            callStmt.setInt(3, oLibroE.getCatId());
            callStmt.setString(4, oLibroE.getLibNombre());
            callStmt.setDouble(5, oLibroE.getLibPrecioAlquiler());
            callStmt.setString(6,oLibroE.getLibAnioPublicacion());
            callStmt.setString(7, oLibroE.getLibISBN());
            callStmt.executeUpdate();
        }
    }

    @Override
    public void deleteLibro(int libId) throws SQLException {
        try (CallableStatement callStmt = connection.prepareCall("{call sp_delete_libro(?)}")) {
            callStmt.setInt(1, libId);
            callStmt.executeUpdate();
        }
    }
}
