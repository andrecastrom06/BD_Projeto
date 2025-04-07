package com.davisory.davisory_bd.dao;

import com.davisory.davisory_bd.model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRepositorio {

    public List<Produto> listarProdutos() {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM Produto";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setIdProduto(rs.getInt("idProduto"));
                produto.setNomeProduto(rs.getString("nomeProduto"));
                produto.setDescricaoProduto(rs.getString("descricaoProduto"));
                produto.setPrecoProduto(rs.getDouble("precoProduto"));
                lista.add(produto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Produto buscarPorId(int id) {
        String sql = "SELECT * FROM Produto WHERE idProduto = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Produto produto = new Produto();
                    produto.setIdProduto(rs.getInt("idProduto"));
                    produto.setNomeProduto(rs.getString("nomeProduto"));
                    produto.setDescricaoProduto(rs.getString("descricaoProduto"));
                    produto.setPrecoProduto(rs.getDouble("precoProduto"));
                    return produto;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void inserirProduto(Produto produto) {
        String sql = "INSERT INTO Produto (nomeProduto, descricaoProduto, precoProduto) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getNomeProduto());
            stmt.setString(2, produto.getDescricaoProduto());
            stmt.setDouble(3, produto.getPrecoProduto());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarProduto(Produto produto) {
        String sql = "UPDATE Produto SET nomeProduto = ?, descricaoProduto = ?, precoProduto = ? WHERE idProduto = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getNomeProduto());
            stmt.setString(2, produto.getDescricaoProduto());
            stmt.setDouble(3, produto.getPrecoProduto());
            stmt.setInt(4, produto.getIdProduto());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarProduto(int id) {
        String sql = "DELETE FROM Produto WHERE idProduto = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
