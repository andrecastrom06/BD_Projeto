package com.davisory.davisory_bd.model;

import java.time.LocalDateTime;

public class Solicitacao {
    private String cnpjFornecedor;
    private int idMateriaPrima;
    private int idFuncionario; // Administrativo
    private LocalDateTime dataSolicitacao;

    public Solicitacao() {}

    public Solicitacao(String cnpjFornecedor, int idMateriaPrima, int idFuncionario, LocalDateTime dataSolicitacao) {
        this.cnpjFornecedor = cnpjFornecedor;
        this.idMateriaPrima = idMateriaPrima;
        this.idFuncionario = idFuncionario;
        this.dataSolicitacao = dataSolicitacao;
    }

    public String getCnpjFornecedor() {
        return cnpjFornecedor;
    }

    public void setCnpjFornecedor(String cnpjFornecedor) {
        this.cnpjFornecedor = cnpjFornecedor;
    }

    public int getIdMateriaPrima() {
        return idMateriaPrima;
    }

    public void setIdMateriaPrima(int idMateriaPrima) {
        this.idMateriaPrima = idMateriaPrima;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public LocalDateTime getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(LocalDateTime dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }
}