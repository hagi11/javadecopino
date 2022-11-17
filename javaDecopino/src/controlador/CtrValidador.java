/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public boolean validarCaracteresEspeciales(String text) { // Validador de Nombre y Apellido
       boolean validar = true;
       Pattern pat = Pattern.compile("^[a-zA-Z]$");
       Matcher mat = pat.matcher(text);

              if(mat.matches()){

                     System.out.println("Cadena Valida");
                     validar = true;
              }
              else{
                     System.out.println("Cadena Invalida");
                     validar = false;
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

            for (int i = 0; i < cadena.length(); i++) {   
                String valor = String.valueOf(cadena.charAt(i));
                Integer.parseInt(valor);
            }

            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public boolean validarTelefono(String numero) {
        boolean validar = false;

        if (validarNumero(numero)) {
            if (numero.length() == 7 || numero.length() == 10) {
                validar = true;
            }
        }
        return validar;
    }

    public boolean validarCorreo(String correo) {
        boolean validar = true;
        // Patrón para validar el email
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        // El email a validar
        Matcher mather = pattern.matcher(correo);
        if (!(mather.find() == true)) {
            validar = false;
        }
        return validar;
    }

    public boolean validarDireccion(String direccion) {
        boolean validar = true;

        return validar;
    }

    public boolean validarContrasenia(String text) {
        boolean validar = true;
        return validar;
    }

}
