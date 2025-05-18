package com.davisory.davisory_bd.dto;

public class DashboardResumo {
    private long totalClientes;
    private long totalPedidos;
    private String produtoMaisVendido;

    // Construtores
    public DashboardResumo() {}

    public DashboardResumo(long totalClientes, long totalPedidos, String produtoMaisVendido) {
        this.totalClientes = totalClientes;
        this.totalPedidos = totalPedidos;
        this.produtoMaisVendido = produtoMaisVendido;
    }

    // Getters e Setters
    public long getTotalClientes() { return totalClientes; }
    public void setTotalClientes(long totalClientes) { this.totalClientes = totalClientes; }

    public long getTotalPedidos() { return totalPedidos; }
    public void setTotalPedidos(long totalPedidos) { this.totalPedidos = totalPedidos; }

    public String getProdutoMaisVendido() { return produtoMaisVendido; }
    public void setProdutoMaisVendido(String produtoMaisVendido) { this.produtoMaisVendido = produtoMaisVendido; }
}
