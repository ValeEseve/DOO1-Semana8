package model;

public class PaseoLacustre extends ServicioTuristico {
    private String tipoEmbarcacion;

    public PaseoLacustre(String nombre, int duracionHoras, Direccion direccion, String tipoEmbarcacion) {
        super(nombre, duracionHoras, direccion);
        this.tipoEmbarcacion = tipoEmbarcacion;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Servicio Turístico");
        System.out.println("Paseo Lacustre");
        System.out.println(getNombre() + " - " + getDuracionHoras() + " horas de duración");
        System.out.println("Tipo Embarcación: " + tipoEmbarcacion);
    }

    public String getTipoEmbarcacion() {
        return tipoEmbarcacion;
    }

    public void setTipoEmbarcacion(String tipoEmbarcacion) {
        this.tipoEmbarcacion = tipoEmbarcacion;
    }

    @Override
    public String toString() {
        return "PaseoLacustre{tipoEmbarcacion='" + tipoEmbarcacion + "'} " + super.toString();
    }
}