package com.barrios.modelo;

import java.math.BigDecimal;

public class Expensa {
    private Long id;
    private String periodo;
    private BigDecimal monto;
    private String estado;

    public Expensa() {
    }

    public Expensa(Long id, String periodo, BigDecimal monto, String estado) {
        this.id = id;
        this.periodo = periodo;
        this.monto = monto;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
