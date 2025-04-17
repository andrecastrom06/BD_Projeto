package com.davisory.davisory_bd.dao;

import com.davisory.davisory_bd.dto.AtendimentoDTO;
import com.davisory.davisory_bd.model.Atendimento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AtendimentoRepositorio {

    public List<AtendimentoDTO> listarAtendimentosComFuncionario() {
        List<AtendimentoDTO> lista = new ArrayList<>();

        String sql = """
            SELECT a.fk_Cliente_cpfCnpjCliente AS cpfCnpjCliente,
                f.nomeFuncionario,
                a.dataAtendimento
            FROM Atende a
            JOIN Administrativo ad ON a.fk_Administrativo_Funcionario_idFuncionario = ad.fk_Funcionario_idFuncionario
            JOIN Funcionario f ON ad.fk_Funcionario_idFuncionario = f.idFuncionario
        """;

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                AtendimentoDTO dto = new AtendimentoDTO(
                    rs.getString("cpfCnpjCliente"),
                    rs.getString("nomeFuncionario"),
                    rs.getTimestamp("dataAtendimento").toLocalDateTime()
                );
                lista.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Atendimento buscarPorCpfCliente(String cpf) {
        String sql = "SELECT * FROM Atende WHERE fk_Cliente_cpfCnpjCliente = ?";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Atendimento a = new Atendimento();
                    a.setCpfCnpjCliente(rs.getString("fk_Cliente_cpfCnpjCliente"));
                    a.setIdFuncionarioAdministrativo(rs.getInt("fk_Administrativo_Funcionario_idFuncionario"));
                    a.setDataAtendimento(rs.getTimestamp("dataAtendimento"));
                    return a;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void inserir(Atendimento a) {
        String sql = "INSERT INTO Atende (fk_Cliente_cpfCnpjCliente, fk_Administrativo_Funcionario_idFuncionario, dataAtendimento) VALUES (?, ?, ?)";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, a.getCpfCnpjCliente());
            stmt.setInt(2, a.getIdFuncionarioAdministrativo());
            stmt.setTimestamp(3, a.getDataAtendimento());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
    
    public void atualizar(Atendimento a) {
        String sql = "UPDATE Atende SET fk_Administrativo_Funcionario_idFuncionario = ?, dataAtendimento = ? WHERE fk_Cliente_cpfCnpjCliente = ?";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, a.getIdFuncionarioAdministrativo());
            stmt.setTimestamp(2, a.getDataAtendimento());
            stmt.setString(3, a.getCpfCnpjCliente());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean existe(String cpf) {
        String sql = "SELECT 1 FROM Atende WHERE fk_Cliente_cpfCnpjCliente = ?";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }    
}