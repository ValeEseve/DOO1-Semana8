package model;

import interfaces.Registrable;

public class Vehiculo implements Registrable {
    private String patente;
    private String estado;

    public Vehiculo(String patente, String estado) {
        this.patente = patente;
        this.estado = estado;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String mostrarResumen() {
        return "Patente: " + patente + "\nEstado: " + estado;
    }

    @Override
    public void registrar(Producto producto) {
        System.out.println("Vehículo " + patente + " registrado para transportar: " + producto.getNombre());
    }
}