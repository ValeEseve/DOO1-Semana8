package app;

import data.GestorEntidades;
import gui.VentanaPrincipal;
import model.OrdenDeCompra;
import model.Persona;
import model.Producto;
import model.ServicioTuristico;
import model.Tarjeta;
import utils.LectorDatos;

import javax.swing.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        GestorEntidades gestor = new GestorEntidades();
        cargarDatosIniciales(gestor);

        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal(gestor);
            ventana.setVisible(true);
        });
    }

    public static void cargarDatosIniciales(GestorEntidades gestor) {
        List<Persona> personas = LectorDatos.leerPersonas("archivos/personas.txt");
        for (Persona p : personas) {
            gestor.agregarRegistrable(p);
        }

        List<Producto> productos = LectorDatos.leerProductos("archivos/productos.txt");
        for (Producto p : productos) {
            gestor.agregarProducto(p);
        }

        List<ServicioTuristico> servicios = LectorDatos.leerServicios("archivos/servicios.txt");
        for (ServicioTuristico s : servicios) {
            gestor.agregarServicio(s);
        }

        if (!personas.isEmpty() && !productos.isEmpty()) {
            Tarjeta tarjeta = new Tarjeta("4111111111111111", personas.get(0).getNombre(), "Débito", "12/28");
            OrdenDeCompra orden = new OrdenDeCompra(1, personas.get(0), tarjeta);
            orden.agregarProducto(productos.get(0));
            personas.get(0).registrar(productos.get(0));
            gestor.agregarOrden(orden);
        }
    }
}