package model;

/** Clase base para los distintos servicios/paquetes turísticos ofrecidos. */
public class ServicioTuristico {
    private String nombre;
    private int duracionHoras;
    private Direccion direccion;

    public ServicioTuristico(String nombre, int duracionHoras, Direccion direccion) {
        this.nombre = nombre;
        this.duracionHoras = duracionHoras;
        this.direccion = direccion;
    }

    public void mostrarInformacion() {
        System.out.println("Servicio Turístico");
        System.out.println("Servicio: " + nombre);
        System.out.println("Duración Horas: " + duracionHoras);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDuracionHoras() {
        return duracionHoras;
    }

    public void setDuracionHoras(int duracionHoras) {
        this.duracionHoras = duracionHoras;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "ServicioTuristico{" +
                "nombre='" + nombre + '\'' +
                ", duracionHoras=" + duracionHoras +
                ", direccion=" + direccion +
                '}';
    }
}