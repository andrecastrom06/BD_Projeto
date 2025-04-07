package com.davisory.davisory_bd.model;

public class EstoqueMateriaPrima {
    private int idEstoqueMateriaPrima;
    private int fkMateriaPrimaId;
    private int quantidadeMateriaPrima;

    public int getIdEstoqueMateriaPrima() { return idEstoqueMateriaPrima; }
    public void setIdEstoqueMateriaPrima(int idEstoqueMateriaPrima) { this.idEstoqueMateriaPrima = idEstoqueMateriaPrima; }

    public int getFkMateriaPrimaId() { return fkMateriaPrimaId; }
    public void setFkMateriaPrimaId(int fkMateriaPrimaId) { this.fkMateriaPrimaId = fkMateriaPrimaId; }

    public int getQuantidadeMateriaPrima() { return quantidadeMateriaPrima; }
    public void setQuantidadeMateriaPrima(int quantidadeMateriaPrima) { this.quantidadeMateriaPrima = quantidadeMateriaPrima; }
}