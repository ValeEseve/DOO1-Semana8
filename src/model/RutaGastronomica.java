package model;

public class RutaGastronomica extends ServicioTuristico {
    private int numeroDeParadas;

    public RutaGastronomica(String nombre, int duracionHoras, int numeroDeParadas) {
        super(nombre, duracionHoras);
        this.numeroDeParadas = numeroDeParadas;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Servicio Turistico");
        System.out.println("Ruta Gastronómica");
        System.out.println(getNombre());
        System.out.println(getDuracionHoras() + " horas de duración");
        System.out.println(getNumeroDeParadas() + " paradas");
    }

    public int getNumeroDeParadas() {
        return numeroDeParadas;
    }

    public void setNumeroDeParadas(int numeroDeParadas) {
        this.numeroDeParadas = numeroDeParadas;
    }

    @Override
    public String toString() {
        return "RutaGastronomica{" +
                "numeroDeParadas=" + numeroDeParadas +
                '}';
    }
}
