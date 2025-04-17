package com.davisory.davisory_bd.model;

public class Funcionario {
    private int idFuncionario;
    private String nomeFuncionario;
    private double salarioFuncionario;
    private String cargo;
    private Integer chefeFuncionario;
    private boolean empregado;

    public int getIdFuncionario() { return idFuncionario; }
    public void setIdFuncionario(int idFuncionario) { this.idFuncionario = idFuncionario; }

    public String getNomeFuncionario() { return nomeFuncionario; }
    public void setNomeFuncionario(String nomeFuncionario) { this.nomeFuncionario = nomeFuncionario; }

    public double getSalarioFuncionario() { return salarioFuncionario; }
    public void setSalarioFuncionario(double salarioFuncionario) { this.salarioFuncionario = salarioFuncionario; }
    
    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public Integer getChefeFuncionario() { return chefeFuncionario; }
    public void setChefeFuncionario(Integer chefeFuncionario) { this.chefeFuncionario = chefeFuncionario; }

    public boolean isEmpregado() { return empregado; }
    public void setEmpregado(boolean empregado) { this.empregado = empregado; }
}