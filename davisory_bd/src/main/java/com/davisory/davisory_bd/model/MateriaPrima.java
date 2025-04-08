package com.davisory.davisory_bd.model;

import java.sql.Date;

public class MateriaPrima {
    private int idMateriaPrima;
    private String nomeMateriaPrima;
    private double valorMateriaPrima;
    private String codigoEntregaMateriaPrima;
    private Date dataEstimadaEntregaMateriaPrima;

    public int getIdMateriaPrima() { return idMateriaPrima; }
    public void setIdMateriaPrima(int idMateriaPrima) { this.idMateriaPrima = idMateriaPrima; }

    public String getNomeMateriaPrima() { return nomeMateriaPrima; }
    public void setNomeMateriaPrima(String nomeMateriaPrima) { this.nomeMateriaPrima = nomeMateriaPrima; }

    public double getValorMateriaPrima() { return valorMateriaPrima; }
    public void setValorMateriaPrima(double valorMateriaPrima) { this.valorMateriaPrima = valorMateriaPrima; }

    public String getCodigoEntregaMateriaPrima() { return codigoEntregaMateriaPrima; }
    public void setCodigoEntregaMateriaPrima(String codigoEntregaMateriaPrima) {
        this.codigoEntregaMateriaPrima = codigoEntregaMateriaPrima;
    }

    public Date getDataEstimadaEntregaMateriaPrima() { return dataEstimadaEntregaMateriaPrima; }
    public void setDataEstimadaEntregaMateriaPrima(Date dataEstimadaEntregaMateriaPrima) {
        this.dataEstimadaEntregaMateriaPrima = dataEstimadaEntregaMateriaPrima;
    }
}