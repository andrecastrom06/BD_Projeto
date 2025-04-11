package com.davisory.davisory_bd.model;

import java.sql.Timestamp;

public class Atendimento {
    private String cpfCnpjCliente;
    private Integer idFuncionarioAdministrativo;
    private Timestamp dataAtendimento;

    public String getCpfCnpjCliente() { return cpfCnpjCliente; }
    public void setCpfCnpjCliente(String cpfCnpjCliente) { this.cpfCnpjCliente = cpfCnpjCliente; }

    public Integer getIdFuncionarioAdministrativo() { return idFuncionarioAdministrativo; }
    public void setIdFuncionarioAdministrativo(Integer idFuncionarioAdministrativo) {
        this.idFuncionarioAdministrativo = idFuncionarioAdministrativo;
    }

    public Timestamp getDataAtendimento() { return dataAtendimento; }
    public void setDataAtendimento(Timestamp dataAtendimento) { this.dataAtendimento = dataAtendimento; }
}