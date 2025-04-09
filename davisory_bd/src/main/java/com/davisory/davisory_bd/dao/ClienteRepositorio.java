package com.davisory.davisory_bd.dao;

import com.davisory.davisory_bd.model.Cliente;
import com.davisory.davisory_bd.model.Endereco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepositorio {

    public List<Cliente> listarTodos() {
        List<Cliente> clientes = new ArrayList<>();
        EnderecoRepositorio enderecoRepositorio = new EnderecoRepositorio();

        try (Connection conn = ConexaoBD.conectar()) {
            String sql = "SELECT * FROM Cliente";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente(
                    rs.getString("cpfCnpjCliente"),
                    rs.getString("nomeCliente"),
                    rs.getString("telefoneCliente"),
                    rs.getString("emailCliente")
                );

                int idEndereco = rs.getInt("fk_Endereco_idEndereco");
                if (!rs.wasNull()) {
                    Endereco endereco = enderecoRepositorio.buscarPorId(idEndereco);
                    cliente.setEndereco(endereco);
                }

                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Clientes encontrados: " + clientes.size());
        return clientes;
    }

    public Cliente buscarPorCpfCnpj(String cpfCnpj) {
        Cliente cliente = null;
        EnderecoRepositorio enderecoRepositorio = new EnderecoRepositorio();

        try (Connection conn = ConexaoBD.conectar()) {
            String sql = "SELECT * FROM Cliente WHERE cpfCnpjCliente = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cpfCnpj);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cliente = new Cliente(
                    rs.getString("cpfCnpjCliente"),
                    rs.getString("nomeCliente"),
                    rs.getString("telefoneCliente"),
                    rs.getString("emailCliente")
                );

                int idEndereco = rs.getInt("fk_Endereco_idEndereco");
                if (!rs.wasNull()) {
                    Endereco endereco = enderecoRepositorio.buscarPorId(idEndereco);
                    cliente.setEndereco(endereco);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cliente;
    }

    public void atualizar(Cliente cliente) {
        try (Connection conn = ConexaoBD.conectar()) {
            String sql = "UPDATE Cliente SET nomeCliente = ?, telefoneCliente = ?, emailCliente = ? WHERE cpfCnpjCliente = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cliente.getNomeCliente());
            stmt.setString(2, cliente.getTelefoneCliente());
            stmt.setString(3, cliente.getEmailCliente());
            stmt.setString(4, cliente.getCpfCnpjCliente());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inserir(Cliente cliente) {
        try (Connection conn = ConexaoBD.conectar()) {
            String sql = "INSERT INTO Cliente (cpfCnpjCliente, nomeCliente, telefoneCliente, emailCliente, fk_Endereco_idEndereco) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cliente.getCpfCnpjCliente());
            stmt.setString(2, cliente.getNomeCliente());
            stmt.setString(3, cliente.getTelefoneCliente());
            stmt.setString(4, cliente.getEmailCliente());
            stmt.setInt(5, cliente.getEndereco().getIdEndereco()); // FK aqui
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}