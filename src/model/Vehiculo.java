package model;

import interfaces.Registrable;

public class Vehiculo implements Registrable {
    public String patente;
    public String estado;

    public Vehiculo(String patente, String estado) {
        this.patente = patente;
        this.estado = estado;
    }

    @Override
    public String mostrarResumen() {
        return "Patente: " + patente +
                "\nEstado: " + estado;
    }

    @Override
    public void registrar(Producto producto) {
        
    }
}
