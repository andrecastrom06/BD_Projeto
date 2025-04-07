package com.davisory.davisory_bd.model;

import java.sql.Timestamp;

public class Pedido {
    private int idPedido;
    private Timestamp dataPedido;
    private String codigoEntregaPedido;
    private int quantidadePedido;
    private double precoUnitarioPedido;
    private int idProduto;
    private int idFuncionario;
    private String cpfCnpjCliente;

    public int getIdPedido() { return idPedido; }
    public void setIdPedido(int idPedido) { this.idPedido = idPedido; }

    public Timestamp getDataPedido() { return dataPedido; }
    public void setDataPedido(Timestamp dataPedido) { this.dataPedido = dataPedido; }

    public String getCodigoEntregaPedido() { return codigoEntregaPedido; }
    public void setCodigoEntregaPedido(String codigoEntregaPedido) { this.codigoEntregaPedido = codigoEntregaPedido; }

    public int getQuantidadePedido() { return quantidadePedido; }
    public void setQuantidadePedido(int quantidadePedido) { this.quantidadePedido = quantidadePedido; }

    public double getPrecoUnitarioPedido() { return precoUnitarioPedido; }
    public void setPrecoUnitarioPedido(double precoUnitarioPedido) { this.precoUnitarioPedido = precoUnitarioPedido; }

    public int getIdProduto() { return idProduto; }
    public void setIdProduto(int idProduto) { this.idProduto = idProduto; }

    public int getIdFuncionario() { return idFuncionario; }
    public void setIdFuncionario(int idFuncionario) { this.idFuncionario = idFuncionario; }

    public String getCpfCnpjCliente() { return cpfCnpjCliente; }
    public void setCpfCnpjCliente(String cpfCnpjCliente) { this.cpfCnpjCliente = cpfCnpjCliente; }
}