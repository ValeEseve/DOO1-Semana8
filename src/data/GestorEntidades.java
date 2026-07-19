package data;

import interfaces.Registrable;
import model.OrdenDeCompra;
import model.Producto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class GestorEntidades {
    private List<Registrable> registrables;
    private HashMap<String, Producto> productos;
    private Stack<OrdenDeCompra> ordenes;

    public GestorEntidades() {
        this.registrables = new ArrayList<>();
        this.productos = new HashMap<>();
        this.ordenes = new Stack<>();
    }

    public void agregarRegistrable(Registrable registrable) {
        registrables.add(registrable);
    }

    public void mostrarRegistrables() {
        for (Registrable registrable : registrables) {
            System.out.println(registrable.mostrarResumen());
            System.out.println("--------------------");
        }
    }

    public List<Registrable> getRegistrables() {
        return registrables;
    }

    public void agregarProducto(Producto producto) {
        productos.put(producto.getCodigo(), producto);
    }

    public Producto buscarProductoPorCodigo(String codigo) {
        return productos.get(codigo);
    }

    public void agregarOrden(OrdenDeCompra orden) {
        ordenes.push(orden);
    }

    public void mostrarOrdenes() {
        for (OrdenDeCompra orden : ordenes) {
            System.out.println(orden);
        }
    }
}