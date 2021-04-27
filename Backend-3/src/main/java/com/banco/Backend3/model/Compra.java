package com.banco.Backend3.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Compra {
    private int monto;
    private String noTarjeta;
    private int cvv;
    private LocalDate fechaVencimiento;

    public Compra(){

    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public String getNoTarjeta() {
        return noTarjeta;
    }

    public void setNoTarjeta(String noTarjeta) {
        this.noTarjeta = noTarjeta;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }
}
