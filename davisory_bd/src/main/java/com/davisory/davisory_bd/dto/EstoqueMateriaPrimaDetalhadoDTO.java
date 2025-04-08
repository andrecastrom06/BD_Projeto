package com.davisory.davisory_bd.dto;

import com.davisory.davisory_bd.model.EstoqueMateriaPrima;
import com.davisory.davisory_bd.model.MateriaPrima;

public class EstoqueMateriaPrimaDetalhadoDTO {
    private EstoqueMateriaPrima estoque;
    private MateriaPrima materiaPrima;

    public EstoqueMateriaPrimaDetalhadoDTO(EstoqueMateriaPrima estoque, MateriaPrima materiaPrima) {
        this.estoque = estoque;
        this.materiaPrima = materiaPrima;
    }

    public EstoqueMateriaPrima getEstoque() {
        return estoque;
    }

    public MateriaPrima getMateriaPrima() {
        return materiaPrima;
    }
}