package com.davisory.davisory_bd.dao;

import com.davisory.davisory_bd.model.EstoqueMateriaPrima;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstoqueMateriaPrimaRepositorio {

    public List<EstoqueMateriaPrima> listar() {
        List<EstoqueMateriaPrima> lista = new ArrayList<>();
        String sql = "SELECT * FROM EstoqueMateriaPrima";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                EstoqueMateriaPrima e = new EstoqueMateriaPrima();
                e.setIdEstoqueMateriaPrima(rs.getInt("idEstoqueMateriaPrima"));
                e.setFkMateriaPrimaId(rs.getInt("fk_MateriaPrima_idMateriaPrima"));
                e.setQuantidadeMateriaPrima(rs.getInt("quantidadeMateriaPrima"));
                lista.add(e);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    public EstoqueMateriaPrima buscarPorId(int id, int materiaId) {
        String sql = "SELECT * FROM EstoqueMateriaPrima WHERE idEstoqueMateriaPrima = ? AND fk_MateriaPrima_idMateriaPrima = ?";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setInt(2, materiaId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                EstoqueMateriaPrima e = new EstoqueMateriaPrima();
                e.setIdEstoqueMateriaPrima(id);
                e.setFkMateriaPrimaId(materiaId);
                e.setQuantidadeMateriaPrima(rs.getInt("quantidadeMateriaPrima"));
                return e;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void atualizar(EstoqueMateriaPrima estoque) {
        String sql = "UPDATE EstoqueMateriaPrima SET quantidadeMateriaPrima = ? WHERE idEstoqueMateriaPrima = ? AND fk_MateriaPrima_idMateriaPrima = ?";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, estoque.getQuantidadeMateriaPrima());
            stmt.setInt(2, estoque.getIdEstoqueMateriaPrima());
            stmt.setInt(3, estoque.getFkMateriaPrimaId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }    
}