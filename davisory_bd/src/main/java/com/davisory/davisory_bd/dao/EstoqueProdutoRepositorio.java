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
    public EstoqueProduto buscarPorId(int id, int produtoId) {
        String sql = "SELECT * FROM EstoqueProduto WHERE idEstoqueProduto = ? AND fk_Produto_idProduto = ?";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setInt(2, produtoId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                EstoqueProduto ep = new EstoqueProduto();
                ep.setIdEstoqueProduto(id);
                ep.setIdProduto(produtoId);
                ep.setQuantidadeProduto(rs.getInt("quantidadeProduto"));
                return ep;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void atualizar(EstoqueProduto estoque) {
        String sql = "UPDATE EstoqueProduto SET quantidadeProduto = ? WHERE idEstoqueProduto = ? AND fk_Produto_idProduto = ?";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, estoque.getQuantidadeProduto());
            stmt.setInt(2, estoque.getIdEstoqueProduto());
            stmt.setInt(3, estoque.getIdProduto());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}