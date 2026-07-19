package model;

public class ColaboradorExterno extends Persona {

    private String empresa;

    public ColaboradorExterno(String nombre, Rut rut, String telefono, Direccion direccion, String empresa) {
        super(nombre, rut, telefono, direccion);
        this.empresa = empresa;
    }

    @Override
    public String mostrarResumen() {
        return super.mostrarResumen() +
                "\nEmpresa: " + empresa;
    }

    @Override
    public void registrar(Producto producto) {

    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    @Override
    public String toString() {
        return "ColaboradorExterno{" +
                "empresa='" + empresa + '\'' +
                '}';
    }
}
