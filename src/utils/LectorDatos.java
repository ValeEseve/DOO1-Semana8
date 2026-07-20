package utils;

import exceptions.RutInvalidoException;
import model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Clase utilitaria encargada de leer los archivos .txt de datos
 * y convertirlos en objetos del dominio.
 */
public class LectorDatos {

    // Formato por línea: TIPO;nombre;rutCompleto;telefono;calle;ciudad;region;extra...
    public static List<Persona> leerPersonas(String rutaArchivo) {
        List<Persona> personas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.isBlank()) continue;
                String[] p = linea.split(";");

                try {
                    String tipo = p[0].trim().toUpperCase();
                    String nombre = p[1];
                    Rut rut = new Rut(p[2]); // usa el constructor sobrecargado
                    String telefono = p[3];
                    Direccion direccion = new Direccion(p[4], p[5], p[6]);

                    Persona persona = switch (tipo) {
                        case "CLIENTE" -> new Cliente(nombre, rut, telefono, direccion, p[7]);
                        case "EMPLEADO" -> new Empleado(nombre, rut, telefono, direccion, p[7]);
                        case "PROVEEDOR" -> new Proveedor(nombre, rut, telefono, direccion, p[7]);
                        case "COLABORADOR" -> new ColaboradorExterno(nombre, rut, telefono, direccion, p[7]);
                        case "GUIA" -> new GuiaTuristico(nombre, rut, telefono, direccion, p[7],
                                Arrays.asList(p[8].split("\\|")));
                        default -> null;
                    };

                    if (persona != null) {
                        personas.add(persona);
                    } else {
                        System.out.println("Tipo de persona desconocido, se omite línea: " + linea);
                    }

                } catch (RutInvalidoException e) {
                    System.out.println("RUT inválido, se omite línea: " + linea);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Línea con formato incompleto, se omite: " + linea);
                }
            }
        } catch (IOException e) {
            System.out.println("No se pudo leer el archivo " + rutaArchivo + ": " + e.getMessage());
        }

        return personas;
    }

    // Formato por línea: codigo;nombre;descripcion;precio;stock
    // (también acepta el formato antiguo sin descripción: codigo;nombre;precio;stock)
    public static List<Producto> leerProductos(String rutaArchivo) {
        List<Producto> productos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.isBlank()) continue;
                String[] p = linea.split(";");

                try {
                    if (p.length >= 5) {
                        productos.add(new Producto(p[0], p[1], p[2],
                                Double.parseDouble(p[3]), Integer.parseInt(p[4])));
                    } else {
                        productos.add(new Producto(p[0], p[1], "",
                                Double.parseDouble(p[2]), Integer.parseInt(p[3])));
                    }
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Línea de producto inválida, se omite: " + linea);
                }
            }
        } catch (IOException e) {
            System.out.println("No se pudo leer el archivo " + rutaArchivo + ": " + e.getMessage());
        }

        return productos;
    }

    // Formato por línea: TIPO;nombre;duracionHoras;calle;ciudad;region;extra
    public static List<ServicioTuristico> leerServicios(String rutaArchivo) {
        List<ServicioTuristico> servicios = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.isBlank()) continue;
                String[] p = linea.split(";");

                try {
                    String tipo = p[0].trim().toUpperCase();
                    String nombre = p[1];
                    int duracion = Integer.parseInt(p[2]);
                    Direccion direccion = new Direccion(p[3], p[4], p[5]);

                    ServicioTuristico servicio = switch (tipo) {
                        case "EXCURSION" -> new ExcursionCultural(nombre, duracion, direccion, p[6]);
                        case "PASEO" -> new PaseoLacustre(nombre, duracion, direccion, p[6]);
                        case "RUTA" -> new RutaGastronomica(nombre, duracion, direccion, Integer.parseInt(p[6]));
                        default -> null;
                    };

                    if (servicio != null) {
                        servicios.add(servicio);
                    } else {
                        System.out.println("Tipo de servicio desconocido, se omite línea: " + linea);
                    }

                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Línea de servicio inválida, se omite: " + linea);
                }
            }
        } catch (IOException e) {
            System.out.println("No se pudo leer el archivo " + rutaArchivo + ": " + e.getMessage());
        }

        return servicios;
    }
}