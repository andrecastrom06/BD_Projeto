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
        String sqlProduto = "INSERT INTO Produto (nomeProduto, descricaoProduto, precoProduto) VALUES (?, ?, ?)";
        String sqlEstoque = "INSERT INTO EstoqueProduto (fk_Produto_idProduto, quantidadeProduto) VALUES (?, ?)";
    
        try (Connection conn = ConexaoBD.conectar()) {
            conn.setAutoCommit(false); 
            try (PreparedStatement stmtProduto = conn.prepareStatement(sqlProduto, Statement.RETURN_GENERATED_KEYS)) {
                stmtProduto.setString(1, produto.getNomeProduto());
                stmtProduto.setString(2, produto.getDescricaoProduto());
                stmtProduto.setDouble(3, produto.getPrecoProduto());
                stmtProduto.executeUpdate();
                try (ResultSet generatedKeys = stmtProduto.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int idGerado = generatedKeys.getInt(1);
                        produto.setIdProduto(idGerado);
                        try (PreparedStatement stmtEstoque = conn.prepareStatement(sqlEstoque)) {
                            stmtEstoque.setInt(1, idGerado);
                            stmtEstoque.setInt(2, 0);
                            stmtEstoque.executeUpdate();
                        }
                    } else {
                        conn.rollback();
                        throw new SQLException("Falha ao obter o ID do produto.");
                    }
                }
            }
    
            conn.commit(); // confirma transação
    
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
