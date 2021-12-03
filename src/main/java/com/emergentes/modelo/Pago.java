package com.emergentes.modelo;

public class Pago {
    private int idpago;
    private int idprestamo;
    private String usuario;
    private String fecha;
    private Double cuota;
    private String cliente;

    public Pago() {
    }

    public int getIdpago() {
        return idpago;
    }

    public void setIdpago(int idpago) {
        this.idpago = idpago;
    }

    public int getIdprestamo() {
        return idprestamo;
    }

    public void setIdprestamo(int idprestamo) {
        this.idprestamo = idprestamo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Double getCuota() {
        return cuota;
    }

    public void setCuota(Double cuota) {
        this.cuota = cuota;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Pago{" + "idpago=" + idpago + ", idprestamo=" + idprestamo + ", usuario=" + usuario + ", fecha=" + fecha + ", cuota=" + cuota + ", cliente=" + cliente + '}';
    }
    
    
}
