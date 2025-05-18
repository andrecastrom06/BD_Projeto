package com.davisory.davisory_bd.dao;

import com.davisory.davisory_bd.dto.AtendimentoDTO;
import com.davisory.davisory_bd.model.Atendimento;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AtendimentoRepositorio {

    public List<AtendimentoDTO> listarAtendimentosComFuncionario() {
        List<AtendimentoDTO> lista = new ArrayList<>();

        String sql = """
            SELECT a.fk_Cliente_cpfCnpjCliente AS cpfCliente,
                a.fk_Administrativo_Funcionario_idFuncionario AS idFuncionario,
                c.nomeCliente AS nomeCliente,
                f.nomeFuncionario,
                a.dataAtendimento
            FROM Atende a
            JOIN Cliente c ON a.fk_Cliente_cpfCnpjCliente = c.cpfCnpjCliente
            JOIN Administrativo ad ON a.fk_Administrativo_Funcionario_idFuncionario = ad.fk_Funcionario_idFuncionario
            JOIN Funcionario f ON ad.fk_Funcionario_idFuncionario = f.idFuncionario
        """;

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                AtendimentoDTO dto = new AtendimentoDTO(
                    rs.getString("nomeCliente"),
                    rs.getString("nomeFuncionario"),
                    rs.getTimestamp("dataAtendimento").toLocalDateTime()
                );
                dto.setCpfCliente(rs.getString("cpfCliente"));
                dto.setIdFuncionario(rs.getInt("idFuncionario"));
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
                    a.setNomeCliente(rs.getString("fk_Cliente_cpfCnpjCliente"));
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
            stmt.setString(3, a.getNomeCliente());
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

    public void deletar(String cpfCliente, int idFuncionario) {
        String sql = "DELETE FROM Atende WHERE fk_Cliente_cpfCnpjCliente = ? AND fk_Administrativo_Funcionario_idFuncionario = ?";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpfCliente);
            stmt.setInt(2, idFuncionario);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<String, Integer> contarAtendimentosPorDia(LocalDate inicio, LocalDate fim) {
    Map<String, Integer> resultado = new LinkedHashMap<>();

    String sql = """
        SELECT DATE(dataAtendimento) AS dia, COUNT(*) AS total
        FROM Atende
        WHERE dataAtendimento BETWEEN ? AND ?
        GROUP BY dia
        ORDER BY dia
    """;

    try (Connection conn = ConexaoBD.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setDate(1, Date.valueOf(inicio));
        stmt.setDate(2, Date.valueOf(fim.plusDays(1)));  

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            resultado.put(rs.getString("dia"), rs.getInt("total"));
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return resultado;
}
        
}