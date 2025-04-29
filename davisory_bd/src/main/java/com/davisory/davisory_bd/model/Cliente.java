package com.davisory.davisory_bd.model;

public class Cliente {
    private String cpfCnpjCliente;
    private String nomeCliente;
    private String telefoneCliente;
    private String emailCliente;
    private Endereco endereco;

    public Cliente() {}

    public Cliente(String cpfCnpjCliente, String nomeCliente, String telefoneCliente, String emailCliente) {
        setCpfCnpjCliente(cpfCnpjCliente);
        this.nomeCliente = nomeCliente;
        setTelefoneCliente(telefoneCliente);
        setEmailCliente(emailCliente);
    }

    public String getCpfCnpjCliente() {
        return cpfCnpjCliente;
    }

    public void setCpfCnpjCliente(String cpfCnpjCliente) {
        if (cpfCnpjCliente != null && cpfCnpjCliente.matches("\\d{11}|\\d{14}")) {
            this.cpfCnpjCliente = cpfCnpjCliente;
        } else {
            throw new IllegalArgumentException("CPF/CNPJ deve conter 11 ou 14 dígitos numéricos.");
        }
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getTelefoneCliente() {
        return telefoneCliente;
    }

    public void setTelefoneCliente(String telefoneCliente) {
        if (telefoneCliente != null) {
            String telefoneLimpo = telefoneCliente.replaceAll("\\D", "");
            if (telefoneLimpo.length() == 11) {
                this.telefoneCliente = telefoneLimpo;
            } else {
                throw new IllegalArgumentException("Telefone deve conter 11 dígitos numéricos após remover formatação.");
            }
        } else {
            throw new IllegalArgumentException("Telefone não pode ser nulo.");
        }
    }    

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        if (emailCliente != null && emailCliente.contains("@")) {
            this.emailCliente = emailCliente;
        } else {
            throw new IllegalArgumentException("Email inválido: deve conter '@'.");
        }
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}