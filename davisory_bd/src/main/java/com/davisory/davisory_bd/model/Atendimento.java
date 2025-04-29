package com.davisory.davisory_bd.model;

import java.sql.Timestamp;

public class Atendimento {
    private String nomeCliente;
    private Integer idFuncionarioAdministrativo;
    private Timestamp dataAtendimento;
    private String cpfCnpjCliente;

    public String getNomeCliente() { return nomeCliente; }
    public void setNomeCliente(String nomeCliente) { this.nomeCliente = nomeCliente; }

    public Integer getIdFuncionarioAdministrativo() { return idFuncionarioAdministrativo; }
    public void setIdFuncionarioAdministrativo(Integer idFuncionarioAdministrativo) {
        this.idFuncionarioAdministrativo = idFuncionarioAdministrativo;
    }

    public Timestamp getDataAtendimento() { return dataAtendimento; }
    public void setDataAtendimento(Timestamp dataAtendimento) { this.dataAtendimento = dataAtendimento; }

    public String getCpfCnpjCliente() { return cpfCnpjCliente; }
    public void setCpfCnpjCliente(String cpfCnpjCliente) {
        if (cpfCnpjCliente != null && cpfCnpjCliente.matches("\\d{11}|\\d{14}")) {
            this.cpfCnpjCliente = cpfCnpjCliente;
        } else {
            throw new IllegalArgumentException("CPF/CNPJ inválido: deve conter apenas números e ter 11 (CPF) ou 14 (CNPJ) dígitos.");
        }        
    }    
}