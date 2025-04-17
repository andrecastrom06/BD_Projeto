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

    public void inserir(Endereco endereco) {
        String sql = "INSERT INTO Endereco (estado, cidade, bairro, rua, complemento, numero) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, endereco.getEstado());
            stmt.setString(2, endereco.getCidade());
            stmt.setString(3, endereco.getBairro());
            stmt.setString(4, endereco.getRua());
            stmt.setString(5, endereco.getComplemento());
            stmt.setInt(6, endereco.getNumero());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void atualizar(Endereco endereco) {
        String sql = "UPDATE Endereco SET estado=?, cidade=?, bairro=?, rua=?, complemento=?, numero=? WHERE idEndereco=?";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, endereco.getEstado());
            stmt.setString(2, endereco.getCidade());
            stmt.setString(3, endereco.getBairro());
            stmt.setString(4, endereco.getRua());
            stmt.setString(5, endereco.getComplemento());
            stmt.setInt(6, endereco.getNumero());
            stmt.setInt(7, endereco.getIdEndereco());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }    
}