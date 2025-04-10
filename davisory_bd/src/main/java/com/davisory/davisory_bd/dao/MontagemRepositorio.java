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
}