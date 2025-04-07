package com.davisory.davisory_bd.dao;

import com.davisory.davisory_bd.model.EstoqueProduto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstoqueProdutoRepositorio {

    public List<EstoqueProduto> listar() {
        List<EstoqueProduto> lista = new ArrayList<>();
        String sql = "SELECT * FROM EstoqueProduto";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                EstoqueProduto ep = new EstoqueProduto();
                ep.setIdEstoqueProduto(rs.getInt("idEstoqueProduto"));
                ep.setIdProduto(rs.getInt("fk_Produto_idProduto"));
                ep.setQuantidadeProduto(rs.getInt("quantidadeProduto"));
                lista.add(ep);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}