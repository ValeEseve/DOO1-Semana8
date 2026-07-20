package data;

import interfaces.Registrable;
import model.ExcursionCultural;
import model.GuiaTuristico;
import model.OrdenDeCompra;
import model.Producto;
import model.ServicioTuristico;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class GestorEntidades {
    private List<Registrable> registrables;
    private HashMap<String, Producto> productos;
    private Stack<OrdenDeCompra> ordenes;
    private List<ServicioTuristico> servicios;

    public GestorEntidades() {
        this.registrables = new ArrayList<>();
        this.productos = new HashMap<>();
        this.ordenes = new Stack<>();
        this.servicios = new ArrayList<>();
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

    // Filtra solo los guías turísticos de la lista polimórfica
    public void mostrarGuias() {
        for (Registrable registrable : registrables) {
            if (registrable instanceof GuiaTuristico guia) {
                System.out.println(guia.mostrarResumen());
                System.out.println("--------------------");
            }
        }
    }

    public List<Registrable> getRegistrables() {
        return registrables;
    }

    public void agregarProducto(Producto producto) {
        productos.put(producto.getCodigo(), producto);
    }

    public void agregarProducto(String codigo, String nombre, String descripcion, double precio, int stock) {
        agregarProducto(new Producto(codigo, nombre, descripcion, precio, stock));
    }

    public Producto buscarProductoPorCodigo(String codigo) {
        return productos.get(codigo);
    }

    public List<Producto> filtrarProductosPorPrecioMaximo(double precioMax) {
        List<Producto> resultado = new ArrayList<>();
        for (Producto p : productos.values()) {
            if (p.getPrecio() <= precioMax) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    public void agregarOrden(OrdenDeCompra orden) {
        ordenes.push(orden);
    }

    public void mostrarOrdenes() {
        for (OrdenDeCompra orden : ordenes) {
            System.out.println(orden);
        }
    }

    public List<OrdenDeCompra> getOrdenes() {
        return new ArrayList<>(ordenes);
    }

    // Devuelve solo los GuiaTuristico de la lista polimórfica, como lista (para tablas u otros usos)
    public List<GuiaTuristico> obtenerListaGuias() {
        List<GuiaTuristico> resultado = new ArrayList<>();
        for (Registrable r : registrables) {
            if (r instanceof GuiaTuristico guia) {
                resultado.add(guia);
            }
        }
        return resultado;
    }

    // Devuelve solo las ExcursionCultural dentro de la jerarquía ServicioTuristico
    public List<ExcursionCultural> obtenerListaExcursiones() {
        List<ExcursionCultural> resultado = new ArrayList<>();
        for (ServicioTuristico s : servicios) {
            if (s instanceof ExcursionCultural excursion) {
                resultado.add(excursion);
            }
        }
        return resultado;
    }

    // ---- Paquetes / servicios turísticos ----

    public void agregarServicio(ServicioTuristico servicio) {
        servicios.add(servicio);
    }

    public List<ServicioTuristico> getServicios() {
        return servicios;
    }

    public String obtenerServiciosComoTexto() {
        StringBuilder sb = new StringBuilder();
        for (ServicioTuristico servicio : servicios) {
            sb.append(servicio.toString()).append("\n--------------------\n");
        }
        return sb.length() == 0 ? "No hay servicios turísticos registrados." : sb.toString();
    }

    // Filtra solo las excursiones culturales, usando instanceof sobre la jerarquía de ServicioTuristico
    public String obtenerExcursionesComoTexto() {
        StringBuilder sb = new StringBuilder();
        for (ServicioTuristico servicio : servicios) {
            if (servicio instanceof ExcursionCultural excursion) {
                sb.append(excursion.toString()).append("\n--------------------\n");
            }
        }
        return sb.length() == 0 ? "No hay excursiones culturales registradas." : sb.toString();
    }

    public String obtenerRegistrablesComoTexto() {
        StringBuilder sb = new StringBuilder();
        for (Registrable registrable : registrables) {
            sb.append(registrable.mostrarResumen()).append("\n--------------------\n");
        }
        return sb.toString();
    }

    public String obtenerOrdenesComoTexto() {
        StringBuilder sb = new StringBuilder();
        for (OrdenDeCompra orden : ordenes) {
            sb.append(orden).append("\n");
        }
        return sb.toString();
    }

    public String obtenerGuiasComoTexto() {
        StringBuilder sb = new StringBuilder();
        for (Registrable registrable : registrables) {
            if (registrable instanceof model.GuiaTuristico guia) {
                sb.append(guia.mostrarResumen()).append("\n--------------------\n");
            }
        }
        return sb.toString();
    }

    public String obtenerProductosFiltradosComoTexto(double precioMax) {
        StringBuilder sb = new StringBuilder();
        for (Producto p : filtrarProductosPorPrecioMaximo(precioMax)) {
            sb.append(p).append("\n");
        }
        return sb.toString();
    }
}