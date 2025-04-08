package com.davisory.davisory_bd.dto;

import java.time.LocalDateTime;

public class AtendimentoDTO {
    private String cpfCnpjCliente;
    private String nomeFuncionario;
    private LocalDateTime dataAtendimento;

    public AtendimentoDTO() {
    }

    public AtendimentoDTO(String cpfCnpjCliente, String nomeFuncionario, LocalDateTime dataAtendimento) {
        this.cpfCnpjCliente = cpfCnpjCliente;
        this.nomeFuncionario = nomeFuncionario;
        this.dataAtendimento = dataAtendimento;
    }

    public String getCpfCnpjCliente() {
        return cpfCnpjCliente;
    }

    public void setCpfCnpjCliente(String cpfCnpjCliente) {
        this.cpfCnpjCliente = cpfCnpjCliente;
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
}