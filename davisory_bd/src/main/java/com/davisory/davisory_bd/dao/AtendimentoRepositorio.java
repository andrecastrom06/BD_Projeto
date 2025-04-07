package com.davisory.davisory_bd.dao;

import com.davisory.davisory_bd.model.Atendimento;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AtendimentoRepositorio {

    public List<Atendimento> listar() {
        List<Atendimento> lista = new ArrayList<>();
        String sql = "SELECT * FROM Atende";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Atendimento a = new Atendimento();
                a.setCpfCnpjCliente(rs.getString("fk_Cliente_cpfCnpjCliente"));
                a.setIdFuncionarioAdministrativo(rs.getInt("fk_Administrativo_Funcionario_idFuncionario"));
                a.setDataAtendimento(rs.getTimestamp("dataAtendimento"));
                lista.add(a);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}