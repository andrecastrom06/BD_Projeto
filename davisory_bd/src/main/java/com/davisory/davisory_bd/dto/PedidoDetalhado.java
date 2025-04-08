package com.davisory.davisory_bd.dto;

import com.davisory.davisory_bd.model.Funcionario;
import com.davisory.davisory_bd.model.Pedido;
import com.davisory.davisory_bd.model.Produto;

public class PedidoDetalhado {
    private Pedido pedido;
    private Produto produto;
    private Funcionario funcionario;

    public PedidoDetalhado(Pedido pedido, Produto produto, Funcionario funcionario) {
        this.pedido = pedido;
        this.produto = produto;
        this.funcionario = funcionario;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }
}