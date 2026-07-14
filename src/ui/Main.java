package ui;

import data.GestorEntidades;
import model.Direccion;
import model.GuiaTuristico;
import model.Registrable;
import model.Vehiculo;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        GestorEntidades gestor = new GestorEntidades();

        gestor.cargarDatosPrueba();

        String opcion;

        do {

            opcion = JOptionPane.showInputDialog("""
                    ===== SISTEMA TURÍSTICO =====
                    
                    1. Agregar guía turístico
                    2. Agregar vehículo
                    3. Mostrar entidades
                    4. Salir
                    
                    Ingrese una opción:
                    """);

            if (opcion == null) {
                break;
            }

            switch (opcion) {

                case "1":

                    String nombre = JOptionPane.showInputDialog("Nombre:");
                    String rut = JOptionPane.showInputDialog("RUT:");
                    String telefono = JOptionPane.showInputDialog("Teléfono:");

                    String ciudad = JOptionPane.showInputDialog("Ciudad:");
                    String calle = JOptionPane.showInputDialog("Calle:");
                    String numero = JOptionPane.showInputDialog("Número:");

                    Direccion direccion = new Direccion(ciudad, calle, numero);

                    String descripcion = JOptionPane.showInputDialog("Descripción:");

                    String idioma = JOptionPane.showInputDialog("Idioma:");

                    List<String> idiomas = new ArrayList<>();
                    idiomas.add(idioma);

                    GuiaTuristico guia = new GuiaTuristico(
                            nombre,
                            rut,
                            telefono,
                            direccion,
                            descripcion,
                            idiomas
                    );

                    gestor.agregarEntidad(guia);

                    JOptionPane.showMessageDialog(
                            null,
                            "Guía turístico agregado correctamente."
                    );

                    break;

                case "2":

                    String patente = JOptionPane.showInputDialog("Patente:");
                    String estado = JOptionPane.showInputDialog("Estado:");

                    Vehiculo vehiculo = new Vehiculo(
                            patente,
                            estado
                    );

                    gestor.agregarEntidad(vehiculo);

                    JOptionPane.showMessageDialog(
                            null,
                            "Vehículo agregado correctamente."
                    );

                    break;

                case "3":

                    if (gestor.getEntidades().isEmpty()) {

                        JOptionPane.showMessageDialog(
                                null,
                                "No hay entidades registradas."
                        );

                    } else {

                        StringBuilder resumen = new StringBuilder();

                        for (Registrable entidad : gestor.getEntidades()) {

                            resumen.append("-------------------------\n");

                            if (entidad instanceof GuiaTuristico) {

                                resumen.append("GUÍA TURÍSTICO\n");

                            } else if (entidad instanceof Vehiculo) {

                                resumen.append("VEHÍCULO\n");

                            }

                            resumen.append(entidad.mostrarResumen());
                            resumen.append("\n\n");

                        }

                        JOptionPane.showMessageDialog(
                                null,
                                resumen.toString()
                        );

                    }

                    break;

                case "4":

                    JOptionPane.showMessageDialog(
                            null,
                            "¡Hasta luego!"
                    );

                    break;

                default:

                    JOptionPane.showMessageDialog(
                            null,
                            "Opción inválida."
                    );

            }

        } while (!opcion.equals("4"));

    }

}