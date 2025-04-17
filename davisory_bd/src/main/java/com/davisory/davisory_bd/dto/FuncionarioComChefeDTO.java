package com.davisory.davisory_bd.dto;

public class FuncionarioComChefeDTO {
    private int idFuncionario;
    private String nomeFuncionario;
    private String nomeChefe;

    public FuncionarioComChefeDTO(int idFuncionario, String nomeFuncionario, String nomeChefe) {
        this.idFuncionario = idFuncionario;
        this.nomeFuncionario = nomeFuncionario;
        this.nomeChefe = nomeChefe;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public String getNomeChefe() {
        return nomeChefe;
    }
}