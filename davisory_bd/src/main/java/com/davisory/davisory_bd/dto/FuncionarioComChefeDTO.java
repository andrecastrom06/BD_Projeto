package com.davisory.davisory_bd.dto;

public class FuncionarioComChefeDTO {
    private String nomeFuncionario;
    private String nomeChefe;

    public FuncionarioComChefeDTO(String nomeFuncionario, String nomeChefe) {
        this.nomeFuncionario = nomeFuncionario;
        this.nomeChefe = nomeChefe;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public String getNomeChefe() {
        return nomeChefe;
    }
}