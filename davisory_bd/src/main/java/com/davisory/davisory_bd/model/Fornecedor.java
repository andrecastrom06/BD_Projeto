package com.davisory.davisory_bd.model;

public class Fornecedor {
    private String cnpjFornecedor;
    private String nomeFornecedor;
    private String telefoneFornecedor;
    private String emailFornecedor;
    private int fkEnderecoId;

    public String getCnpjFornecedor() { return cnpjFornecedor; }
    public void setCnpjFornecedor(String cnpjFornecedor) { this.cnpjFornecedor = cnpjFornecedor; }

    public String getNomeFornecedor() { return nomeFornecedor; }
    public void setNomeFornecedor(String nomeFornecedor) { this.nomeFornecedor = nomeFornecedor; }

    public String getTelefoneFornecedor() { return telefoneFornecedor; }
    public void setTelefoneFornecedor(String telefoneFornecedor) { this.telefoneFornecedor = telefoneFornecedor; }

    public String getEmailFornecedor() { return emailFornecedor; }
    public void setEmailFornecedor(String emailFornecedor) { this.emailFornecedor = emailFornecedor; }

    public int getFkEnderecoId() { return fkEnderecoId; }
    public void setFkEnderecoId(int fkEnderecoId) { this.fkEnderecoId = fkEnderecoId; }
}