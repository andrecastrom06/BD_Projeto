package com.davisory.davisory_bd.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    public static Connection conectar() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/davisory?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = System.getenv("DB_PASSWORD"); // <-- vem do .env via variável de ambiente

        return DriverManager.getConnection(url, user, password);
    }
}