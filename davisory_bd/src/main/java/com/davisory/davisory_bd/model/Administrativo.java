package com.davisory.davisory_bd.model;

public class Administrativo extends Funcionario {
    private String cargoFuncionarioAdministrativo;

    public String getCargoFuncionarioAdministrativo() {
        return cargoFuncionarioAdministrativo;
    }

    public void setCargoFuncionarioAdministrativo(String cargoFuncionarioAdministrativo) {
        this.cargoFuncionarioAdministrativo = cargoFuncionarioAdministrativo;
    }
}