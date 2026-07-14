package model;

public class Vehiculo implements Registrable{
    public String patente;
    public String estado;

    public Vehiculo(String patente, String estado) {
        this.patente = patente;
        this.estado = estado;
    }

    @Override
    public void mostrarResumen() {

    }
}
