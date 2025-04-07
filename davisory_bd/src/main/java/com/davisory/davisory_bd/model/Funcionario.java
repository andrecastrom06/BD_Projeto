package com.davisory.davisory_bd.model;

import java.sql.Date;

public class Funcionario {
    private int idFuncionario;
    private String nomeFuncionario;
    private double salarioFuncionario;
    private Date dataContratacaoFuncionario;
    private Integer chefeFuncionario;

    // Getters e Setters
    public int getIdFuncionario() { return idFuncionario; }
    public void setIdFuncionario(int idFuncionario) { this.idFuncionario = idFuncionario; }

    public String getNomeFuncionario() { return nomeFuncionario; }
    public void setNomeFuncionario(String nomeFuncionario) { this.nomeFuncionario = nomeFuncionario; }

    public double getSalarioFuncionario() { return salarioFuncionario; }
    public void setSalarioFuncionario(double salarioFuncionario) { this.salarioFuncionario = salarioFuncionario; }

    public Date getDataContratacaoFuncionario() { return dataContratacaoFuncionario; }
    public void setDataContratacaoFuncionario(Date dataContratacaoFuncionario) {
        this.dataContratacaoFuncionario = dataContratacaoFuncionario;
    }

    public Integer getChefeFuncionario() { return chefeFuncionario; }
    public void setChefeFuncionario(Integer chefeFuncionario) { this.chefeFuncionario = chefeFuncionario; }

    private boolean empregado;
    public boolean isEmpregado() { return empregado; }
    public void setEmpregado(boolean empregado) { this.empregado = empregado; }

}
