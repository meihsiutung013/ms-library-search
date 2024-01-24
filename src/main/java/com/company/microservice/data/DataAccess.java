package com.company.microservice.data;

import java.sql.*;

public class DataAccess {
    private static final String URL = "jdbc:mysql://localhost:3306/ms-library";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static ResultSet executeQuery(String query) throws SQLException {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            return statement.executeQuery(query);
        }
    }

    public static void executeUpdate(String query) throws SQLException {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        }
    }

    // Métodos específicos para llamar a los stored procedures
    public static ResultSet getAllAutores() throws SQLException {
        return executeQuery("CALL sp_GetAllAutores()");
    }

    public static void insertAutor(String nombre) throws SQLException {
        executeUpdate("CALL sp_InsertAutor('" + nombre + "')");
    }

    // Repite estos métodos para las otras tablas y SPs
}
