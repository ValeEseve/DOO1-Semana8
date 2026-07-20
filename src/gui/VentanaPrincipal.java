package gui;

import data.GestorEntidades;
import exceptions.RutInvalidoException;
import interfaces.Registrable;
import model.Cliente;
import model.ColaboradorExterno;
import model.Direccion;
import model.Empleado;
import model.ExcursionCultural;
import model.GuiaTuristico;
import model.OrdenDeCompra;
import model.PaseoLacustre;
import model.Persona;
import model.Producto;
import model.Proveedor;
import model.Rut;
import model.RutaGastronomica;
import model.ServicioTuristico;
import model.Vehiculo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Ventana principal del prototipo. Muestra la información cargada
 * en GestorEntidades usando una tabla, y permite registrar nuevos
 * clientes y servicios turísticos.
 */
public class VentanaPrincipal extends JFrame {

    private final GestorEntidades gestor;
    private final JTable tabla;

    public VentanaPrincipal(GestorEntidades gestor) {
        super("Llanquihue Tour - Prototipo de Gestión");
        this.gestor = gestor;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(850, 550);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        tabla = new JTable();
        tabla.setFont(new Font("SansSerif", Font.PLAIN, 13));
        tabla.setRowHeight(22);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        add(crearPanelBotones(), BorderLayout.NORTH);

        // Muestra algo apenas se abre la ventana
        mostrarPersonasComoTabla();
    }

    private JPanel crearPanelBotones() {
        JPanel panel = new JPanel(new GridLayout(3, 3, 5, 5));

        JButton btnPersonas = new JButton("Mostrar personas");
        JButton btnGuias = new JButton("Mostrar guías");
        JButton btnProductos = new JButton("Productos ≤ $30.000");
        JButton btnOrdenes = new JButton("Mostrar órdenes");
        JButton btnServicios = new JButton("Mostrar servicios turísticos");
        JButton btnExcursiones = new JButton("Excursiones culturales");
        JButton btnNuevoCliente = new JButton("Registrar cliente");
        JButton btnNuevoServicio = new JButton("Registrar servicio");
        JButton btnSalir = new JButton("Salir");

        btnPersonas.addActionListener(e -> mostrarPersonasComoTabla());
        btnGuias.addActionListener(e -> mostrarGuiasComoTabla());
        btnProductos.addActionListener(e -> mostrarProductosComoTabla(30000));
        btnOrdenes.addActionListener(e -> mostrarOrdenesComoTabla());
        btnServicios.addActionListener(e -> mostrarServiciosComoTabla());
        btnExcursiones.addActionListener(e -> mostrarExcursionesComoTabla());
        btnNuevoCliente.addActionListener(e -> abrirFormularioCliente());
        btnNuevoServicio.addActionListener(e -> abrirFormularioServicio());
        btnSalir.addActionListener(e -> System.exit(0));

        panel.add(btnPersonas);
        panel.add(btnGuias);
        panel.add(btnProductos);
        panel.add(btnOrdenes);
        panel.add(btnServicios);
        panel.add(btnExcursiones);
        panel.add(btnNuevoCliente);
        panel.add(btnNuevoServicio);
        panel.add(btnSalir);

        return panel;
    }

    // ---- Construcción de tablas ----

    private void actualizarTabla(String[] columnas, Object[][] filas) {
        DefaultTableModel modelo = new DefaultTableModel(filas, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // la tabla es solo de lectura
            }
        };
        tabla.setModel(modelo);
    }

    private void mostrarPersonasComoTabla() {
        String[] columnas = {"Tipo", "Nombre", "RUT", "Teléfono", "Dirección", "Detalle"};
        List<Registrable> lista = gestor.getRegistrables();
        Object[][] filas = new Object[lista.size()][columnas.length];

        for (int i = 0; i < lista.size(); i++) {
            Registrable r = lista.get(i);
            String tipo = obtenerTipo(r);
            String detalle = obtenerDetalle(r);

            if (r instanceof Persona persona) {
                filas[i] = new Object[]{
                        tipo, persona.getNombre(), persona.getRut().toString(),
                        persona.getTelefono(), persona.getDireccion().toString(), detalle
                };
            } else if (r instanceof Vehiculo vehiculo) {
                filas[i] = new Object[]{
                        tipo, vehiculo.getPatente(), "-", "-", "-", detalle
                };
            } else {
                filas[i] = new Object[]{tipo, "-", "-", "-", "-", "-"};
            }
        }

        actualizarTabla(columnas, filas);
    }

    private void mostrarGuiasComoTabla() {
        String[] columnas = {"Nombre", "RUT", "Teléfono", "Descripción", "Idiomas"};
        List<GuiaTuristico> lista = gestor.obtenerListaGuias();
        Object[][] filas = new Object[lista.size()][columnas.length];

        for (int i = 0; i < lista.size(); i++) {
            GuiaTuristico g = lista.get(i);
            filas[i] = new Object[]{
                    g.getNombre(), g.getRut().toString(), g.getTelefono(),
                    g.getDescripcion(), String.join(", ", g.getIdiomas())
            };
        }

        actualizarTabla(columnas, filas);
    }

    private void mostrarProductosComoTabla(double precioMax) {
        String[] columnas = {"Código", "Nombre", "Descripción", "Precio", "Stock"};
        List<Producto> lista = gestor.filtrarProductosPorPrecioMaximo(precioMax);
        Object[][] filas = new Object[lista.size()][columnas.length];

        for (int i = 0; i < lista.size(); i++) {
            Producto p = lista.get(i);
            filas[i] = new Object[]{
                    p.getCodigo(), p.getNombre(), p.getDescripcion(), p.getPrecio(), p.getStock()
            };
        }

        actualizarTabla(columnas, filas);
    }

    private void mostrarOrdenesComoTabla() {
        String[] columnas = {"Número", "Cliente", "N° productos", "Tarjeta", "Total"};
        List<OrdenDeCompra> lista = gestor.getOrdenes();
        Object[][] filas = new Object[lista.size()][columnas.length];

        for (int i = 0; i < lista.size(); i++) {
            OrdenDeCompra o = lista.get(i);
            filas[i] = new Object[]{
                    o.getNumero(), o.getPersona().getNombre(),
                    o.getProductos().size(), o.getTarjeta().getNumero(), o.calcularTotal()
            };
        }

        actualizarTabla(columnas, filas);
    }

    private void mostrarServiciosComoTabla() {
        String[] columnas = {"Tipo", "Nombre", "Duración (h)", "Dirección", "Detalle"};
        List<ServicioTuristico> lista = gestor.getServicios();
        Object[][] filas = new Object[lista.size()][columnas.length];

        for (int i = 0; i < lista.size(); i++) {
            ServicioTuristico s = lista.get(i);
            String tipo;
            String detalle;

            if (s instanceof ExcursionCultural ex) {
                tipo = "Excursión cultural";
                detalle = "Lugar histórico: " + ex.getLugarHistorico();
            } else if (s instanceof PaseoLacustre paseo) {
                tipo = "Paseo lacustre";
                detalle = "Embarcación: " + paseo.getTipoEmbarcacion();
            } else if (s instanceof RutaGastronomica ruta) {
                tipo = "Ruta gastronómica";
                detalle = "Paradas: " + ruta.getNumeroDeParadas();
            } else {
                tipo = "Servicio";
                detalle = "-";
            }

            filas[i] = new Object[]{
                    tipo, s.getNombre(), s.getDuracionHoras(), s.getDireccion().toString(), detalle
            };
        }

        actualizarTabla(columnas, filas);
    }

    private void mostrarExcursionesComoTabla() {
        String[] columnas = {"Nombre", "Duración (h)", "Dirección", "Lugar histórico"};
        List<ExcursionCultural> lista = gestor.obtenerListaExcursiones();
        Object[][] filas = new Object[lista.size()][columnas.length];

        for (int i = 0; i < lista.size(); i++) {
            ExcursionCultural ex = lista.get(i);
            filas[i] = new Object[]{
                    ex.getNombre(), ex.getDuracionHoras(), ex.getDireccion().toString(), ex.getLugarHistorico()
            };
        }

        actualizarTabla(columnas, filas);
    }

    // ---- Helpers de tipo/detalle para la tabla de personas ----

    private String obtenerTipo(Registrable r) {
        if (r instanceof Cliente) return "Cliente";
        if (r instanceof Empleado) return "Empleado";
        if (r instanceof Proveedor) return "Proveedor";
        if (r instanceof ColaboradorExterno) return "Colaborador Externo";
        if (r instanceof GuiaTuristico) return "Guía Turístico";
        if (r instanceof Vehiculo) return "Vehículo";
        return "Desconocido";
    }

    private String obtenerDetalle(Registrable r) {
        if (r instanceof Cliente c) return "Categoría: " + c.getCategoria();
        if (r instanceof Empleado e) return "Cargo: " + e.getCargo();
        if (r instanceof Proveedor p) return "Servicio: " + p.getTipoServicio();
        if (r instanceof ColaboradorExterno c) return "Empresa: " + c.getEmpresa();
        if (r instanceof GuiaTuristico g) return "Idiomas: " + g.getIdiomas();
        if (r instanceof Vehiculo v) return "Estado: " + v.getEstado();
        return "";
    }

    // ---- Formularios ----

    private void abrirFormularioCliente() {
        JTextField campoNombre = new JTextField();
        JTextField campoRut = new JTextField(); // formato 12345678-9
        JTextField campoTelefono = new JTextField();
        JTextField campoCalle = new JTextField();
        JTextField campoCiudad = new JTextField();
        JTextField campoRegion = new JTextField();
        JTextField campoCategoria = new JTextField();

        Object[] campos = {
                "Nombre:", campoNombre,
                "RUT (12345678-9):", campoRut,
                "Teléfono:", campoTelefono,
                "Calle:", campoCalle,
                "Ciudad:", campoCiudad,
                "Región:", campoRegion,
                "Categoría:", campoCategoria
        };

        int opcion = JOptionPane.showConfirmDialog(this, campos,
                "Registrar nuevo cliente", JOptionPane.OK_CANCEL_OPTION);

        if (opcion != JOptionPane.OK_OPTION) {
            return;
        }

        try {
            Rut rut = new Rut(campoRut.getText().trim());
            Direccion direccion = new Direccion(
                    campoCalle.getText(), campoCiudad.getText(), campoRegion.getText());

            Cliente cliente = new Cliente(
                    campoNombre.getText(), rut, campoTelefono.getText(),
                    direccion, campoCategoria.getText());

            gestor.agregarRegistrable(cliente);
            mostrarPersonasComoTabla();

            JOptionPane.showMessageDialog(this, "Cliente registrado con éxito.");

        } catch (RutInvalidoException ex) {
            JOptionPane.showMessageDialog(this, "El RUT ingresado no es válido.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(this, "RUT con formato incorrecto (usa 12345678-9).",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirFormularioServicio() {
        JComboBox<String> comboTipo = new JComboBox<>(new String[]{
                "Excursión cultural", "Paseo lacustre", "Ruta gastronómica"
        });
        JTextField campoNombre = new JTextField();
        JTextField campoDuracion = new JTextField();
        JTextField campoCalle = new JTextField();
        JTextField campoCiudad = new JTextField();
        JTextField campoRegion = new JTextField();
        JTextField campoExtra = new JTextField();

        Object[] campos = {
                "Tipo de servicio:", comboTipo,
                "Nombre:", campoNombre,
                "Duración (horas):", campoDuracion,
                "Calle:", campoCalle,
                "Ciudad:", campoCiudad,
                "Región:", campoRegion,
                "Dato extra (lugar histórico / tipo embarcación / N° de paradas):", campoExtra
        };

        int opcion = JOptionPane.showConfirmDialog(this, campos,
                "Registrar nuevo servicio turístico", JOptionPane.OK_CANCEL_OPTION);

        if (opcion != JOptionPane.OK_OPTION) {
            return;
        }

        try {
            String nombre = campoNombre.getText();
            int duracion = Integer.parseInt(campoDuracion.getText().trim());
            Direccion direccion = new Direccion(
                    campoCalle.getText(), campoCiudad.getText(), campoRegion.getText());
            String tipoSeleccionado = (String) comboTipo.getSelectedItem();

            ServicioTuristico servicio = switch (tipoSeleccionado) {
                case "Excursión cultural" ->
                        new ExcursionCultural(nombre, duracion, direccion, campoExtra.getText());
                case "Paseo lacustre" ->
                        new PaseoLacustre(nombre, duracion, direccion, campoExtra.getText());
                case "Ruta gastronómica" ->
                        new RutaGastronomica(nombre, duracion, direccion,
                                Integer.parseInt(campoExtra.getText().trim()));
                default -> null;
            };

            if (servicio != null) {
                gestor.agregarServicio(servicio);
                mostrarServiciosComoTabla();
                JOptionPane.showMessageDialog(this, "Servicio turístico registrado con éxito.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "La duración y el N° de paradas (para rutas) deben ser números enteros.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}