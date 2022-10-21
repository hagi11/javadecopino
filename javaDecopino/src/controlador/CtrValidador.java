/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

/**
 *
 * @author hamme
 */
public class CtrValidador {

    public boolean validarCaracteres(String texto) {
        boolean validar = true;
        for (int x = 0; x < texto.length(); x++) {
            if ((texto.charAt(x) > 63 && texto.charAt(x) < 65)) { // Cuenta laS arrobas
                validar = false;
            }
            if ((texto.charAt(x) > 32 && texto.charAt(x) < 44)) { // Cuenta la cantidad signos
                validar = false;
            }
            if ((texto.charAt(x) > 47 && texto.charAt(x) < 58)) { // Cuenta la cantidad de numero
                validar = false;
            }
            if ((texto.charAt(x) > 64 && texto.charAt(x) < 91)) { // Cuenta la cantidad de mayuscula
                validar = false;
            }
        }
        return validar;
    }

    public boolean validarTamano(String texto, int min, int max) {
        boolean validar = false;
        if (texto.length() > min && !texto.isEmpty() && texto.length() < max) {
            validar = true;
        }
        return validar;
    }

    public boolean validarNumero(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
