package com.davisory.davisory_bd.dao;

import com.davisory.davisory_bd.model.Solicitacao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SolicitacaoRepositorio {

    public List<Solicitacao> listar() {
        List<Solicitacao> lista = new ArrayList<>();
        String sql = "SELECT * FROM Solicita";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Solicitacao s = new Solicitacao();
                s.setCnpjFornecedor(rs.getString("fk_Fornecedor_cnpjFornecedor"));
                s.setIdMateriaPrima(rs.getInt("fk_MateriaPrima_idMateriaPrima"));
                s.setIdFuncionarioAdministrativo(rs.getInt("fk_Administrativo_Funcionario_idFuncionario"));
                s.setDataSolicitacao(rs.getTimestamp("dataSolicitacao"));
                lista.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}