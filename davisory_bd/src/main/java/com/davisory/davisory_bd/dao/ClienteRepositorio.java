package com.davisory.davisory_bd.dao;

import com.davisory.davisory_bd.model.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepositorio {

    public List<Cliente> listarTodos() {
        List<Cliente> clientes = new ArrayList<>();

        try (Connection conn = ConexaoBD.conectar()) {
            String sql = "SELECT * FROM Cliente";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente(
                    rs.getString("cpfCnpjCliente"),
                    rs.getString("nomeCliente"),
                    rs.getString("telefoneCliente"),
                    rs.getString("emailCliente")
                );
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientes;
    }
}
