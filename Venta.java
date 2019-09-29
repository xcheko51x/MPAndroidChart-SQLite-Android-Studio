package com.xcheko51x.mpandroidchartsqlite;

public class Venta {

    String idVenta;
    String dia;
    String totalVendido;

    public Venta(String idVenta, String dia, String totalVendido) {
        this.idVenta = idVenta;
        this.dia = dia;
        this.totalVendido = totalVendido;
    }

    public String getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(String idVenta) {
        this.idVenta = idVenta;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getTotalVendido() {
        return totalVendido;
    }

    public void setTotalVendido(String totalVendido) {
        this.totalVendido = totalVendido;
    }
}
