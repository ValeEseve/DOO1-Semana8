package model;

public class PaseoLacustre extends ServicioTuristico{
    private  String tipoEmbarcacion;

    public PaseoLacustre(String nombre, int duracionHoras, String tipoEmbarcacion) {
        super(nombre, duracionHoras);
        this.tipoEmbarcacion = tipoEmbarcacion;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Servicio Turistico");
        System.out.println("Paseo Lacustre");
        System.out.println(getNombre() + " " + getDuracionHoras() +  " horas de duracion");
        System.out.println("Tipo Embarcacion: " + tipoEmbarcacion);
    }

    public String getTipoEmbarcacion() {
        return tipoEmbarcacion;
    }

    public void setTipoEmbarcacion(String tipoEmbarcacion) {
        this.tipoEmbarcacion = tipoEmbarcacion;
    }

    @Override
    public String toString() {
        return "PaseoLacustre{" +
                "tipoEmbarcacion='" + tipoEmbarcacion + '\'' +
                '}';
    }
}
