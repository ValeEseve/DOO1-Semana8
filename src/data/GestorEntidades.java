package data;

import interfaces.Registrable;
import model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GestorEntidades {

    private final ArrayList<Registrable> entidades;

    public GestorEntidades() {
        entidades = new ArrayList<>();
    }

    public void cargarEntidades() {

        Direccion direccion1 = new Direccion("Puerto Montt", "Centro", "123");
        Direccion direccion2 = new Direccion("Puerto Varas", "Costanera", "456");

        entidades.add(new GuiaTuristico(
                "Pedro Pérez",
                "11.111.111-1",
                "912345678",
                direccion1,
                "Guía de naturaleza",
                Arrays.asList("Español", "Inglés")
        ));

        entidades.add(new GuiaTuristico(
                "María Soto",
                "22.222.222-2",
                "987654321",
                direccion2,
                "Guía gastronómica",
                Arrays.asList("Español", "Portugués")
        ));

        entidades.add(new ColaboradorExterno(
                "Juan Muñoz",
                "33.333.333-3",
                "955555555",
                direccion1,
                "Hotel Patagonia"
        ));

        entidades.add(new Vehiculo(
                "ABCD12",
                "Disponible"
        ));

        entidades.add(new Vehiculo(
                "EFGH34",
                "En mantenimiento"
        ));
    }

    public void mostrarEntidades() {

        for (Registrable entidad : entidades) {

            entidad.mostrarResumen();

            switch (entidad) {
                case GuiaTuristico guiaTuristico -> System.out.println("→ Tipo: Guía Turístico");
                case ColaboradorExterno colaboradorExterno -> System.out.println("→ Tipo: Colaborador Externo");
                case Vehiculo vehiculo -> System.out.println("→ Tipo: Vehículo");
                default -> {
                }
            }

            System.out.println("--------------------------------");

        }

    }

    public void agregarEntidad(Registrable entidad) {
        entidades.add(entidad);
    }

    public ArrayList<Registrable> getEntidades() {
        return entidades;
    }

    public void cargarDatosPrueba() {

        Direccion direccion1 = new Direccion("Puerto Montt", "Benavente", "123");
        Direccion direccion2 = new Direccion("Puerto Varas", "San Pedro", "456");
        Direccion direccion3 = new Direccion("Frutillar", "Costanera", "789");

        agregarEntidad(new GuiaTuristico(
                "Pedro Pérez",
                "11.111.111-1",
                "912345678",
                direccion1,
                "Especialista en turismo aventura",
                List.of("Español", "Inglés")
        ));

        agregarEntidad(new GuiaTuristico(
                "María Soto",
                "22.222.222-2",
                "923456789",
                direccion2,
                "Guía gastronómica",
                List.of("Español", "Portugués")
        ));

        agregarEntidad(new Vehiculo(
                "ABCD12",
                "Disponible"
        ));

        agregarEntidad(new Vehiculo(
                "EFGH34",
                "En mantenimiento"
        ));

        agregarEntidad(new ColaboradorExterno(
                "Juan Muñoz",
                "33.333.333-3",
                "934567890",
                direccion3,
                "Hotel Patagonia"
        ));
    }

}