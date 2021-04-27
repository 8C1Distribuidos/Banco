package com.banco.Backend3.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "cuenta_banco")
public class Cuenta {
    @Id
    @Column(name = "no_tarjeta", nullable = false)
    private String noTarjeta;

    @Column(name = "saldo", nullable = false)
    private int saldo;

    @Column(name = "cvv", nullable = false)
    private String cvv;

    @Column(name = "pin", nullable = false)
    private String pin;

    @Column(name = "fechaVencimiento")
    private LocalDate fechaVencimiento;


    public Cuenta() {
    }

    public Cuenta(String noTarjeta, int saldo, String cvv, String pin) {
        this.noTarjeta = noTarjeta;
        this.saldo = saldo;
        this.cvv = cvv;
        this.pin = pin;
    }


    public String getNoTarjeta() {
        return noTarjeta;
    }

    public void setNoTarjeta(String noTarjeta) {
        this.noTarjeta = noTarjeta;
    }


    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }


    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }


    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }


    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }


}
