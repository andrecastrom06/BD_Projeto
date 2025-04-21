package com.davisory.davisory_bd.dto;

public class LoginDTO {
    private String nome;
    private String data_contratacao;

    public LoginDTO(String nome, String data_contratacao) {
        this.nome = nome;
        this.data_contratacao = data_contratacao;
    }

    public String getNome() {
        return nome;
    }

    public String getData_contratacao() {
        return data_contratacao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setData_contratacao(String data_contratacao) {
        this.data_contratacao = data_contratacao;
    }
}