
package com.emergentes.modelo;

public class Prestamo {
    private int idprestamo;
    private int idcliente;
    private int usuario;
    private String fprestamo;
    private Double monto;
    private Double interes;
    private Double saldo;
    private String formapago;
    private String fpago;
    private String plazo;
    private String fplazo;
    private String cliente;
    private String usuarios;

    public Prestamo() {
    }

    public int getIdprestamo() {
        return idprestamo;
    }

    public void setIdprestamo(int idprestamo) {
        this.idprestamo = idprestamo;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public String getFprestamo() {
        return fprestamo;
    }

    public void setFprestamo(String fprestamo) {
        this.fprestamo = fprestamo;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Double getInteres() {
        return interes;
    }

    public void setInteres(Double interes) {
        this.interes = interes;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public String getFormapago() {
        return formapago;
    }

    public void setFormapago(String formapago) {
        this.formapago = formapago;
    }

    public String getFpago() {
        return fpago;
    }

    public void setFpago(String fpago) {
        this.fpago = fpago;
    }

    public String getPlazo() {
        return plazo;
    }

    public void setPlazo(String plazo) {
        this.plazo = plazo;
    }

    public String getFplazo() {
        return fplazo;
    }

    public void setFplazo(String fplazo) {
        this.fplazo = fplazo;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(String usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public String toString() {
        return "Prestamo{" + "idprestamo=" + idprestamo + ", idcliente=" + idcliente + ", usuario=" + usuario + ", fprestamo=" + fprestamo + ", monto=" + monto + ", interes=" + interes + ", saldo=" + saldo + ", formapago=" + formapago + ", fpago=" + fpago + ", plazo=" + plazo + ", fplazo=" + fplazo + ", cliente=" + cliente + ", usuarios=" + usuarios + '}';
    }
    
    
}
