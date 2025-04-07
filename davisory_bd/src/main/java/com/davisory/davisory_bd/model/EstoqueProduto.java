package com.davisory.davisory_bd.model;

public class EstoqueProduto {
    private int idEstoqueProduto;
    private int idProduto;
    private int quantidadeProduto;

    public int getIdEstoqueProduto() {
        return idEstoqueProduto;
    }

    public void setIdEstoqueProduto(int idEstoqueProduto) {
        this.idEstoqueProduto = idEstoqueProduto;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(int quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }
}