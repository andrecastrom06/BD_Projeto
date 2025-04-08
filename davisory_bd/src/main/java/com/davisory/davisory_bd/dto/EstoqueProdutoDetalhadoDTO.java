package com.davisory.davisory_bd.dto;

import com.davisory.davisory_bd.model.EstoqueProduto;
import com.davisory.davisory_bd.model.Produto;

public class EstoqueProdutoDetalhadoDTO {
    private EstoqueProduto estoque;
    private Produto produto;

    public EstoqueProdutoDetalhadoDTO(EstoqueProduto estoque, Produto produto) {
        this.estoque = estoque;
        this.produto = produto;
    }

    public EstoqueProduto getEstoque() {
        return estoque;
    }

    public Produto getProduto() {
        return produto;
    }
}