package model;

public class Proveedor extends Persona {

    private String tipoServicio; // ej: "Alojamiento", "Transporte", "Insumos"

    public Proveedor(String nombre, Rut rut, String telefono, Direccion direccion, String tipoServicio) {
        super(nombre, rut, telefono, direccion);
        this.tipoServicio = tipoServicio;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    @Override
    public String mostrarResumen() {
        return super.mostrarResumen() + "\nTipo de servicio: " + tipoServicio;
    }

    @Override
    public String toString() {
        return "Proveedor{tipoServicio='" + tipoServicio + "'} " + super.toString();
    }
}