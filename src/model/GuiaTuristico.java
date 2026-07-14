package model;

import java.util.List;

public class GuiaTuristico  extends Persona{

    private String descripcion;
    private List<String> idiomas;

    public GuiaTuristico(String nombre, String rut, String telefono, Direccion direccion, String descripcion, List<String> idiomas) {
        super(nombre, rut, telefono, direccion);
        this.descripcion = descripcion;
        this.idiomas = idiomas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    @Override
    public String toString() {
        return "GuiaTuristico{" +
                "nombre='" + getNombre() + '\'' +
                ", rut='" + getRut() + '\'' +
                ", telefono='" + getTelefono() + '\'' +
                ", direccion=" + getDireccion() +
                ", descripcion='" + descripcion + '\'' +
                ", idiomas=" + idiomas +
                '}';
    }
}
