package com.CDH.myapplication.ui.clases;

public class Personal {
    private String Nombre;
    private String rut;
    private String entradaAm;
    private String entradaPm;
    private String salidaPm;
    private String salidaAm;

    public Personal(String nombre, String rut, String entradaAm, String entradaPm, String salidaPm, String salidaAm) {
        Nombre = nombre;
        this.rut = rut;
        this.entradaAm = entradaAm;
        this.entradaPm = entradaPm;
        this.salidaPm = salidaPm;
        this.salidaAm = salidaAm;
    }

    public Personal() {
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getEntradaAm() {
        return entradaAm;
    }

    public void setEntradaAm(String entradaAm) {
        this.entradaAm = entradaAm;
    }

    public String getEntradaPm() {
        return entradaPm;
    }

    public void setEntradaPm(String entradaPm) {
        this.entradaPm = entradaPm;
    }

    public String getSalidaPm() {
        return salidaPm;
    }

    public void setSalidaPm(String salidaPm) {
        this.salidaPm = salidaPm;
    }

    public String getSalidaAm() {
        return salidaAm;
    }

    public void setSalidaAm(String salidaAm) {
        this.salidaAm = salidaAm;
    }
}
