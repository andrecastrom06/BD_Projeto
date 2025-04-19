package com.davisory.davisory_bd.dao;

import com.davisory.davisory_bd.dto.SolicitacaoDTO;
import com.davisory.davisory_bd.model.Solicitacao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SolicitacaoRepositorio {

    public List<SolicitacaoDTO> listarSolicitacoesComNomes() {
        List<SolicitacaoDTO> lista = new ArrayList<>();

        String sql = """
            SELECT f.nomeFornecedor,
                mp.nomeMateriaPrima,
                fun.nomeFuncionario,
                s.dataSolicitacao
            FROM Solicita s
            JOIN Fornecedor f ON s.fk_Fornecedor_cnpjFornecedor = f.cnpjFornecedor
            JOIN MateriaPrima mp ON s.fk_MateriaPrima_idMateriaPrima = mp.idMateriaPrima
            JOIN Administrativo ad ON s.fk_Administrativo_Funcionario_idFuncionario = ad.fk_Funcionario_idFuncionario
            JOIN Funcionario fun ON ad.fk_Funcionario_idFuncionario = fun.idFuncionario
        """;

        try (Connection conn = ConexaoBD.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                SolicitacaoDTO dto = new SolicitacaoDTO(
                    rs.getString("nomeFornecedor"),
                    rs.getString("nomeMateriaPrima"),
                    rs.getString("nomeFuncionario"),
                    rs.getTimestamp("dataSolicitacao").toLocalDateTime()
                );
                lista.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    public void inserir(Solicitacao solicitacao) {
        String sql = """
            INSERT INTO Solicita (
                fk_Fornecedor_cnpjFornecedor, 
                fk_MateriaPrima_idMateriaPrima, 
                fk_Administrativo_Funcionario_idFuncionario, 
                dataSolicitacao
            ) VALUES (?, ?, ?, ?)
        """;

        try (Connection conn = ConexaoBD.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, solicitacao.getCnpjFornecedor());
            stmt.setInt(2, solicitacao.getIdMateriaPrima());
            stmt.setInt(3, solicitacao.getIdFuncionario());
            stmt.setTimestamp(4, Timestamp.valueOf(solicitacao.getDataSolicitacao()));

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Idealmente use um logger aqui
        }
    }
}