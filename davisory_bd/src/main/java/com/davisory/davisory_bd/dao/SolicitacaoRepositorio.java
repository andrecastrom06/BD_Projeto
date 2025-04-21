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
            SELECT 
                s.fk_Fornecedor_cnpjFornecedor,
                s.fk_MateriaPrima_idMateriaPrima,
                s.fk_Administrativo_Funcionario_idFuncionario,
                f.nomeFornecedor,
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
                SolicitacaoDTO dto = new SolicitacaoDTO();
                    dto.setCnpjFornecedor(rs.getString("fk_Fornecedor_cnpjFornecedor"));
                    dto.setIdMateriaPrima(rs.getInt("fk_MateriaPrima_idMateriaPrima"));
                    dto.setIdFuncionario(rs.getInt("fk_Administrativo_Funcionario_idFuncionario"));
                    dto.setNomeFornecedor(rs.getString("nomeFornecedor"));
                    dto.setNomeMateriaPrima(rs.getString("nomeMateriaPrima"));
                    dto.setNomeFuncionarioAdministrativo(rs.getString("nomeFuncionario"));
                    dto.setDataSolicitacao(rs.getTimestamp("dataSolicitacao").toLocalDateTime());
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
            e.printStackTrace();
        }
    }

    public Solicitacao buscarPorId(String cnpjFornecedor, int idMateriaPrima, int idFuncionario) {
        String sql = "SELECT * FROM Solicita WHERE fk_Fornecedor_cnpjFornecedor = ? AND fk_MateriaPrima_idMateriaPrima = ? AND fk_Administrativo_Funcionario_idFuncionario = ?";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setString(1, cnpjFornecedor);
            stmt.setInt(2, idMateriaPrima);
            stmt.setInt(3, idFuncionario);
    
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                return new Solicitacao(
                    rs.getString("fk_Fornecedor_cnpjFornecedor"),
                    rs.getInt("fk_MateriaPrima_idMateriaPrima"),
                    rs.getInt("fk_Administrativo_Funcionario_idFuncionario"),
                    rs.getTimestamp("dataSolicitacao").toLocalDateTime()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }   
    
    public void atualizar(Solicitacao solicitacao, String cnpjAntigo, int idMateriaAntiga, int idFuncAntigo) {
        String sql = """
            UPDATE Solicita
            SET fk_Fornecedor_cnpjFornecedor = ?, fk_MateriaPrima_idMateriaPrima = ?, fk_Administrativo_Funcionario_idFuncionario = ?
            WHERE fk_Fornecedor_cnpjFornecedor = ? AND fk_MateriaPrima_idMateriaPrima = ? AND fk_Administrativo_Funcionario_idFuncionario = ?
        """;
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, solicitacao.getCnpjFornecedor());
            stmt.setInt(2, solicitacao.getIdMateriaPrima());
            stmt.setInt(3, solicitacao.getIdFuncionario());
            stmt.setString(4, cnpjAntigo);     
            stmt.setInt(5, idMateriaAntiga);     
            stmt.setInt(6, idFuncAntigo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deletar(String cnpj, int idMateria, int idFuncionario) {
        String sql = "DELETE FROM Solicita WHERE fk_Fornecedor_cnpjFornecedor = ? AND fk_MateriaPrima_idMateriaPrima = ? AND fk_Administrativo_Funcionario_idFuncionario = ?";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cnpj);
            stmt.setInt(2, idMateria);
            stmt.setInt(3, idFuncionario);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
}