package com.davisory.davisory_bd.dao;

import com.davisory.davisory_bd.model.Montagem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MontagemRepositorio {

    public List<Montagem> listar() {
        List<Montagem> lista = new ArrayList<>();
        String sql = "SELECT * FROM Monta";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Montagem m = new Montagem();
                m.setIdFuncionarioOperacional(rs.getInt("fk_Operacional_Funcionario_idFuncionario"));
                m.setIdPedido(rs.getInt("fk_Pedido_idPedido"));
                lista.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void salvar(Montagem montagem) {
        String sql = "INSERT INTO Monta (fk_Operacional_Funcionario_idFuncionario, fk_Pedido_idPedido) VALUES (?, ?)";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, montagem.getIdFuncionarioOperacional());
            stmt.setInt(2, montagem.getIdPedido());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Montagem buscarPorIds(int idFuncionario, int idPedido) {
        String sql = "SELECT * FROM Monta WHERE fk_Operacional_Funcionario_idFuncionario = ? AND fk_Pedido_idPedido = ?";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idFuncionario);
            stmt.setInt(2, idPedido);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Montagem m = new Montagem();
                    m.setIdFuncionarioOperacional(rs.getInt("fk_Operacional_Funcionario_idFuncionario"));
                    m.setIdPedido(rs.getInt("fk_Pedido_idPedido"));
                    return m;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void atualizar(Montagem montagem) {
        String sql = "UPDATE Monta SET fk_Operacional_Funcionario_idFuncionario = ?, fk_Pedido_idPedido = ? " +
                     "WHERE fk_Operacional_Funcionario_idFuncionario = ? AND fk_Pedido_idPedido = ?";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, montagem.getIdFuncionarioOperacional());
            stmt.setInt(2, montagem.getIdPedido());
            stmt.setInt(3, montagem.getIdFuncionarioOriginal());
            stmt.setInt(4, montagem.getIdPedidoOriginal());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }        
}