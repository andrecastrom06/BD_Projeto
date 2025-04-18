package com.davisory.davisory_bd.model;

public class Montagem {
    private int idFuncionarioOperacional;
    private int idPedido;

    private int idFuncionarioOriginal;
    private int idPedidoOriginal;

    public int getIdFuncionarioOperacional() { return idFuncionarioOperacional; }
    public void setIdFuncionarioOperacional(int id) { this.idFuncionarioOperacional = id; }

    public int getIdPedido() { return idPedido; }
    public void setIdPedido(int id) { this.idPedido = id; }

    public int getIdFuncionarioOriginal() { return idFuncionarioOriginal; }
    public void setIdFuncionarioOriginal(int id) { this.idFuncionarioOriginal = id; }

    public int getIdPedidoOriginal() { return idPedidoOriginal; }
    public void setIdPedidoOriginal(int id) { this.idPedidoOriginal = id; }
}