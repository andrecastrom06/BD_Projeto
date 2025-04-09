package com.davisory.davisory_bd.dao;

import com.davisory.davisory_bd.model.Pedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
}