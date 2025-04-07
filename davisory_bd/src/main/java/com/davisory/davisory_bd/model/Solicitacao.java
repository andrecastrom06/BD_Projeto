package com.davisory.davisory_bd.model;

import java.sql.Timestamp;

public class Solicitacao {
    private String cnpjFornecedor;
    private int idMateriaPrima;
    private int idFuncionarioAdministrativo;
    private Timestamp dataSolicitacao;

    public String getCnpjFornecedor() { return cnpjFornecedor; }
    public void setCnpjFornecedor(String cnpjFornecedor) { this.cnpjFornecedor = cnpjFornecedor; }

    public int getIdMateriaPrima() { return idMateriaPrima; }
    public void setIdMateriaPrima(int idMateriaPrima) { this.idMateriaPrima = idMateriaPrima; }

    public int getIdFuncionarioAdministrativo() { return idFuncionarioAdministrativo; }
    public void setIdFuncionarioAdministrativo(int idFuncionarioAdministrativo) {
        this.idFuncionarioAdministrativo = idFuncionarioAdministrativo;
    }

    public Timestamp getDataSolicitacao() { return dataSolicitacao; }
    public void setDataSolicitacao(Timestamp dataSolicitacao) { this.dataSolicitacao = dataSolicitacao; }
}