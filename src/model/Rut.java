package model;

import exceptions.RutInvalidoException;

public class Rut {

    private String numero;
    private char digitoVerificador;

    public Rut(String numero, char digitoVerificador) throws RutInvalidoException {
        this.numero = numero;
        this.digitoVerificador = Character.toUpperCase(digitoVerificador);

        if (!validarRut()) {
            throw new RutInvalidoException("El RUT ingresado no es válido.");
        }
    }

    public String getNumero() {
        return numero;
    }

    public char getDigitoVerificador() {
        return digitoVerificador;
    }

    @Override
    public String toString() {
        return numero + "-" + digitoVerificador;
    }

    private boolean validarRut() {

        int suma = 0;
        int multiplicador = 2;

        for (int i = numero.length() - 1; i >= 0; i--) {

            int digito = Character.getNumericValue(numero.charAt(i));

            suma += digito * multiplicador;

            multiplicador++;

            if (multiplicador > 7) {
                multiplicador = 2;
            }
        }

        int resto = suma % 11;
        int resultado = 11 - resto;

        char dvCalculado;

        if (resultado == 11) {
            dvCalculado = '0';
        } else if (resultado == 10) {
            dvCalculado = 'K';
        } else {
            dvCalculado = Character.forDigit(resultado, 10);
        }

        return dvCalculado == digitoVerificador;
    }
}