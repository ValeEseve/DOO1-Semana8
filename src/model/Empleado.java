package model;

public class Empleado extends Persona {

    private String cargo;

    public Empleado(String nombre, Rut rut, String telefono, Direccion direccion, String cargo) {
        super(nombre, rut, telefono, direccion);
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
    public String mostrarResumen() {
        return super.mostrarResumen() + "\nCargo: " + cargo;
    }

    @Override
    public String toString() {
        return "Empleado{cargo='" + cargo + "'} " + super.toString();
    }
}