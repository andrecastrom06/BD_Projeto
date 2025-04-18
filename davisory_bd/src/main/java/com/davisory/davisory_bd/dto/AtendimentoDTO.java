package com.davisory.davisory_bd.dto;

import java.time.LocalDateTime;

public class AtendimentoDTO {
    private String nomeCliente;
    private String nomeFuncionario;
    private LocalDateTime dataAtendimento;
    private String cpfCliente;
    private Integer idFuncionario;
    public AtendimentoDTO() {}
    
    public AtendimentoDTO(String nomeCliente, String nomeFuncionario, LocalDateTime dataAtendimento) {
        this.nomeCliente = nomeCliente;
        this.nomeFuncionario = nomeFuncionario;
        this.dataAtendimento = dataAtendimento;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public LocalDateTime getDataAtendimento() {
        return dataAtendimento;
    }

    public void setDataAtendimento(LocalDateTime dataAtendimento) {
        this.dataAtendimento = dataAtendimento;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public Integer getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }
}