package com.davisory.davisory_bd.dao;

import com.davisory.davisory_bd.model.Endereco;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnderecoRepositorio {

    public List<Endereco> listar() {
        List<Endereco> lista = new ArrayList<>();
        String sql = "SELECT * FROM Endereco";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Endereco e = new Endereco();
                e.setIdEndereco(rs.getInt("idEndereco"));
                e.setEstado(rs.getString("estado"));
                e.setCidade(rs.getString("cidade"));
                e.setBairro(rs.getString("bairro"));
                e.setRua(rs.getString("rua"));
                e.setComplemento(rs.getString("complemento"));
                e.setNumero(rs.getInt("numero"));
                lista.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Endereco buscarPorId(int id) {
        String sql = "SELECT * FROM Endereco WHERE idEndereco = ?";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Endereco e = new Endereco();
                e.setIdEndereco(rs.getInt("idEndereco"));
                e.setEstado(rs.getString("estado"));
                e.setCidade(rs.getString("cidade"));
                e.setBairro(rs.getString("bairro"));
                e.setRua(rs.getString("rua"));
                e.setComplemento(rs.getString("complemento"));
                e.setNumero(rs.getInt("numero"));
                return e;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}