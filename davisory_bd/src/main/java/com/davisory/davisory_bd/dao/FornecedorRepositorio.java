package com.davisory.davisory_bd.dao;

import com.davisory.davisory_bd.model.Fornecedor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FornecedorRepositorio {

    public List<Fornecedor> listar() {
        List<Fornecedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM Fornecedor";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Fornecedor f = new Fornecedor();
                f.setCnpjFornecedor(rs.getString("cnpjFornecedor"));
                f.setNomeFornecedor(rs.getString("nomeFornecedor"));
                f.setTelefoneFornecedor(rs.getString("telefoneFornecedor"));
                f.setEmailFornecedor(rs.getString("emailFornecedor"));
                f.setFkEnderecoId(rs.getInt("fk_Endereco_id"));
                lista.add(f);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Fornecedor buscarPorCnpj(String cnpj) {
        String sql = "SELECT * FROM Fornecedor WHERE cnpjFornecedor = ?";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, cnpj);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Fornecedor f = new Fornecedor();
                    f.setCnpjFornecedor(rs.getString("cnpjFornecedor"));
                    f.setNomeFornecedor(rs.getString("nomeFornecedor"));
                    f.setTelefoneFornecedor(rs.getString("telefoneFornecedor"));
                    f.setEmailFornecedor(rs.getString("emailFornecedor"));
                    f.setFkEnderecoId(rs.getInt("fk_Endereco_id"));
                    return f;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void atualizar(Fornecedor fornecedor) {
        String sql = "UPDATE Fornecedor SET nomeFornecedor = ?, telefoneFornecedor = ?, emailFornecedor = ?, fk_Endereco_id = ? WHERE cnpjFornecedor = ?";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setString(1, fornecedor.getNomeFornecedor());
            stmt.setString(2, fornecedor.getTelefoneFornecedor());
            stmt.setString(3, fornecedor.getEmailFornecedor());
            stmt.setInt(4, fornecedor.getFkEnderecoId());
            stmt.setString(5, fornecedor.getCnpjFornecedor());
    
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void inserir(Fornecedor fornecedor) {
        String sql = "INSERT INTO Fornecedor (cnpjFornecedor, nomeFornecedor, telefoneFornecedor, emailFornecedor, fk_Endereco_id) VALUES (?, ?, ?, ?, ?)";
    
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setString(1, fornecedor.getCnpjFornecedor());
            stmt.setString(2, fornecedor.getNomeFornecedor());
            stmt.setString(3, fornecedor.getTelefoneFornecedor());
            stmt.setString(4, fornecedor.getEmailFornecedor());
            stmt.setInt(5, fornecedor.getFkEnderecoId());
    
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }    
}