package com.davisory.davisory_bd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.davisory.davisory_bd.model.Pedido;

public class PedidoRepositorio {

    public List<Pedido> listarPedidos() {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM Pedido";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setIdPedido(rs.getInt("idPedido"));
                pedido.setDataPedido(rs.getTimestamp("dataPedido"));
                pedido.setCodigoEntregaPedido(rs.getString("codigoEntregaPedido"));
                pedido.setQuantidadePedido(rs.getInt("quantidadePedido"));
                pedido.setPrecoUnitarioPedido(rs.getDouble("precoUnitarioPedido"));
                pedido.setIdProduto(rs.getInt("fk_Produto_idProduto"));
                pedido.setIdFuncionario(rs.getInt("fk_Funcionario_idFuncionario"));
                pedido.setCpfCnpjCliente(rs.getString("fk_Cliente_cpfCnpjCliente"));
                pedidos.add(pedido);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidos;
    }

    public Pedido buscarPorId(int id) {
        String sql = "SELECT * FROM Pedido WHERE idPedido = ?";
        Pedido p = null;
    
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    p = new Pedido();
                    p.setIdPedido(rs.getInt("idPedido"));
                    p.setDataPedido(rs.getTimestamp("dataPedido"));
                    p.setCodigoEntregaPedido(rs.getString("codigoEntregaPedido"));
                    p.setQuantidadePedido(rs.getInt("quantidadePedido"));
                    p.setPrecoUnitarioPedido(rs.getDouble("precoUnitarioPedido"));
                    p.setIdProduto(rs.getInt("fk_Produto_idProduto"));
                    p.setIdFuncionario(rs.getInt("fk_Funcionario_idFuncionario"));
                    p.setCpfCnpjCliente(rs.getString("fk_Cliente_cpfCnpjCliente")); // ← esse aqui
                }
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return p;
    }    

    public void inserir(Pedido pedido) {
        String sql = "INSERT INTO Pedido (dataPedido, codigoEntregaPedido, quantidadePedido, precoUnitarioPedido, fk_Produto_idProduto, fk_Funcionario_idFuncionario, fk_Cliente_cpfCnpjCliente) VALUES (?, ?, ?, ?, ?, ?, ?)";
    
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setTimestamp(1, pedido.getDataPedido());
            stmt.setString(2, pedido.getCodigoEntregaPedido());
            stmt.setInt(3, pedido.getQuantidadePedido());
            stmt.setDouble(4, pedido.getPrecoUnitarioPedido());
            stmt.setInt(5, pedido.getIdProduto());
            stmt.setInt(6, pedido.getIdFuncionario());
            stmt.setString(7, pedido.getCpfCnpjCliente());
    
            stmt.executeUpdate();
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void atualizar(Pedido pedido) {
        String sql = "UPDATE Pedido SET codigoEntregaPedido = ?, quantidadePedido = ?, precoUnitarioPedido = ?, fk_Produto_idProduto = ?, fk_Funcionario_idFuncionario = ?, fk_Cliente_cpfCnpjCliente = ? WHERE idPedido = ?";
    
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setString(1, pedido.getCodigoEntregaPedido());
            stmt.setInt(2, pedido.getQuantidadePedido());
            stmt.setDouble(3, pedido.getPrecoUnitarioPedido());
            stmt.setInt(4, pedido.getIdProduto());
            stmt.setInt(5, pedido.getIdFuncionario());
            stmt.setString(6, pedido.getCpfCnpjCliente());
            stmt.setInt(7, pedido.getIdPedido());
    
            stmt.executeUpdate();
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    
}