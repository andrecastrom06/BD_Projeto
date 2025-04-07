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
}