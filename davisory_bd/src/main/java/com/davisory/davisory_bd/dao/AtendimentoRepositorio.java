package com.davisory.davisory_bd.dao;

import com.davisory.davisory_bd.dto.AtendimentoDTO;

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
}
