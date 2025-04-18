package com.davisory.davisory_bd.dto;

import java.time.LocalDateTime;

public class SolicitacaoDTO {
    private String cnpjFornecedor;
    private String nomeMateriaPrima;
    private String nomeFuncionarioAdministrativo;
    private LocalDateTime dataSolicitacao;
    private String nomeFornecedor;

    public SolicitacaoDTO() {}

    public SolicitacaoDTO(String nomeFornecedor, String nomeMateriaPrima, String nomeFuncionarioAdministrativo, LocalDateTime dataSolicitacao) {
        this.nomeFornecedor = nomeFornecedor;
        this.nomeMateriaPrima = nomeMateriaPrima;
        this.nomeFuncionarioAdministrativo = nomeFuncionarioAdministrativo;
        this.dataSolicitacao = dataSolicitacao;
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
}