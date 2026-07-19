package interfaces;

import model.Producto;

public interface Registrable {
    String mostrarResumen();
    void registrar(Producto producto);
}
