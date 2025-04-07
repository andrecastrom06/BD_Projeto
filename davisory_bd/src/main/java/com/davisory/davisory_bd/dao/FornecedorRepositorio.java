package com.davisory.davisory_bd.dao;

import com.davisory.davisory_bd.model.Fornecedor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FornecedorRepositorio {

    public List<Fornecedor> listar() {
        List<Fornecedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM Fornecedor";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Fornecedor f = new Fornecedor();
                f.setCnpjFornecedor(rs.getString("cnpjFornecedor"));
                f.setNomeFornecedor(rs.getString("nomeFornecedor"));
                f.setTelefoneFornecedor(rs.getString("telefoneFornecedor"));
                f.setEmailFornecedor(rs.getString("emailFornecedor"));
                f.setFkEnderecoId(rs.getInt("fk_Endereco_id"));
                lista.add(f);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}