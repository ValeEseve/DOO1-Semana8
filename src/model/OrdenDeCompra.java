package model;

import java.util.ArrayList;
import java.util.List;

public class OrdenDeCompra {
    private int numero;
    private Persona persona;
    private List<Producto> productos;
    private Tarjeta tarjeta;

    public OrdenDeCompra(int numero, Persona persona, Tarjeta tarjeta) {
        this.numero = numero;
        this.persona = persona;
        this.tarjeta = tarjeta;
        this.productos = new ArrayList<>();
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public Tarjeta getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public double calcularTotal() {
        double total = 0;

        for (Producto producto : productos) {
            total += producto.getPrecio();
        }

        return total;
    }

    @Override
    public String toString() {
        return "OrdenDeCompra{" +
                "numero=" + numero +
                ", persona=" + persona +
                ", productos=" + productos +
                ", tarjeta=" + tarjeta +
                ", total=" + calcularTotal() +
                '}';
    }
}