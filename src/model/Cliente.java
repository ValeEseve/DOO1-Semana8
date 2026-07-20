package model;

public class Cliente extends Persona {

    private String categoria; // ej: "Nuevo", "Frecuente", "VIP"

    public Cliente(String nombre, Rut rut, String telefono, Direccion direccion, String categoria) {
        super(nombre, rut, telefono, direccion);
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String mostrarResumen() {
        return super.mostrarResumen() + "\nCategoría: " + categoria;
    }

    @Override
    public String toString() {
        return "Cliente{categoria='" + categoria + "'} " + super.toString();
    }
}