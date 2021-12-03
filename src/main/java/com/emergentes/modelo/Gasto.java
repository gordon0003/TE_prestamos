
package com.emergentes.modelo;

public class Gasto {
    private int idgasto;
    private int idusuario;
    private String fecha;
    private String concepto;
    private Double gasto;
    private String usuario;

    public Gasto() {
    }

    public int getIdgasto() {
        return idgasto;
    }

    public void setIdgasto(int idgasto) {
        this.idgasto = idgasto;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Double getGasto() {
        return gasto;
    }

    public void setGasto(Double gasto) {
        this.gasto = gasto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Gasto{" + "idgasto=" + idgasto + ", idusuario=" + idusuario + ", fecha=" + fecha + ", concepto=" + concepto + ", gasto=" + gasto + ", usuario=" + usuario + '}';
    }

    
    
}
