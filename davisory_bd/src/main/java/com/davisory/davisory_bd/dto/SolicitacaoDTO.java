package com.davisory.davisory_bd.dto;

import java.time.LocalDateTime;

public class SolicitacaoDTO {
    private String cnpjFornecedor;
    private String nomeMateriaPrima;
    private String nomeFuncionarioAdministrativo;
    private LocalDateTime dataSolicitacao;
    private String nomeFornecedor;
    private int idMateriaPrima;
    private int idFuncionario;

    public SolicitacaoDTO() {}

    public SolicitacaoDTO(String nomeFornecedor, String nomeMateriaPrima, String nomeFuncionarioAdministrativo, LocalDateTime dataSolicitacao, int idMateriaPrima, int idFuncionario) {
        this.nomeFornecedor = nomeFornecedor;
        this.nomeMateriaPrima = nomeMateriaPrima;
        this.nomeFuncionarioAdministrativo = nomeFuncionarioAdministrativo;
        this.dataSolicitacao = dataSolicitacao;
        this.idMateriaPrima = idMateriaPrima;
        this.idFuncionario = idFuncionario;
    }    

    public String getCnpjFornecedor() {
        return cnpjFornecedor;
    }

    public void setCnpjFornecedor(String cnpjFornecedor) {
        this.cnpjFornecedor = cnpjFornecedor;
    }

    public String getNomeMateriaPrima() {
        return nomeMateriaPrima;
    }

    public void setNomeMateriaPrima(String nomeMateriaPrima) {
        this.nomeMateriaPrima = nomeMateriaPrima;
    }

    public String getNomeFuncionarioAdministrativo() {
        return nomeFuncionarioAdministrativo;
    }

    public void setNomeFuncionarioAdministrativo(String nomeFuncionarioAdministrativo) {
        this.nomeFuncionarioAdministrativo = nomeFuncionarioAdministrativo;
    }

    public LocalDateTime getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(LocalDateTime dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }
    
    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
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
}