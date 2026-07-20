package model;

public class RutaGastronomica extends ServicioTuristico {
    private int numeroDeParadas;

    public RutaGastronomica(String nombre, int duracionHoras, Direccion direccion, int numeroDeParadas) {
        super(nombre, duracionHoras, direccion);
        this.numeroDeParadas = numeroDeParadas;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Servicio Turístico");
        System.out.println("Ruta Gastronómica");
        System.out.println(getNombre() + " - " + getDuracionHoras() + " horas de duración");
        System.out.println(numeroDeParadas + " paradas");
    }

    public int getNumeroDeParadas() {
        return numeroDeParadas;
    }

    public void setNumeroDeParadas(int numeroDeParadas) {
        this.numeroDeParadas = numeroDeParadas;
    }

    @Override
    public String toString() {
        return "RutaGastronomica{numeroDeParadas=" + numeroDeParadas + "} " + super.toString();
    }
}