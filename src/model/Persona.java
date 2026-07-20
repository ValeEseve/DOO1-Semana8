package model;

import interfaces.Registrable;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase base para todas las personas del sistema (clientes, empleados,
 * guías, proveedores, colaboradores externos).
 */
public abstract class Persona implements Registrable {
    private String nombre;
    private Rut rut;
    private String telefono;
    private Direccion direccion;
    private List<Producto> productosRegistrados;

    public Persona(String nombre, Rut rut, String telefono, Direccion direccion) {
        this.nombre = nombre;
        this.rut = rut;
        this.telefono = telefono;
        this.direccion = direccion;
        this.productosRegistrados = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Rut getRut() {
        return rut;
    }

    public void setRut(Rut rut) {
        this.rut = rut;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public List<Producto> getProductosRegistrados() {
        return productosRegistrados;
    }

    @Override
    public void registrar(Producto producto) {
        productosRegistrados.add(producto);
    }

    @Override
    public String mostrarResumen() {
        return "Nombre: " + nombre +
                "\nRUT: " + rut +
                "\nTeléfono: " + telefono;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", rut='" + rut + '\'' +
                ", telefono='" + telefono + '\'' +
                ", direccion=" + direccion +
                '}';
    }
}