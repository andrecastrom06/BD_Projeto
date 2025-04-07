package com.davisory.davisory_bd.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/davisory?useSSL=false&serverTimezone=UTC",
            "root",
            "nautico2006"
        );
    }
}
