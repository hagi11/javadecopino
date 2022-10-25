/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import conexion.Conexion;
import java.sql.ResultSet;

/**
 *
 * @author hamme
 */
public class CtrLogin {

    public int validarIngreso(String login, String contrasenia) {
        int validar = 0;
        Conexion conectar = new Conexion();
        String sql = "SELECT id FROM mususuarios where estado = 1 && login ='" + login + "' && contrasenia ='" + contrasenia + "'";
        ResultSet rs;
        try {
            rs = conectar.consultar(sql);
            if (rs.next()) {
                validar = (rs.getInt("id"));
                crearLog(validar);
            }
        } catch (Exception e) {
            System.out.println("Error en validar ingreso (controlador usuario): " + e);
        }
        return validar;
    }

    public boolean crearLog(int idUsuario) {
        Conexion conectar = new Conexion();
        String sqlt;
        boolean validar = false;
        sqlt = "INSERT INTO `madsesion`(`plataforma`, `usuario`, `estado`) VALUES "
                + "(4," + idUsuario + ",1)";
        try {
            if (conectar.ejecutar(sqlt)) {
                validar = true;
            }
        } catch (Exception e) {
            System.out.println("Error en Guardado del usuario(controlador usuario): " + e);
        }
        return validar;
    }

    public boolean cerrarSesion(int idUsuario) {
        CtrUtilitario ctrut = new CtrUtilitario();
        String fechaActulizado = ctrut.fechaHoy() +" "+ ctrut.horaHoy();
        Conexion conectar = new Conexion();
        boolean validar = false;
        String sqlt;
        sqlt = "UPDATE `madsesion` SET `estado`=0,`factualizado`='" + fechaActulizado + "' WHERE estado = 1 && usuario=" + idUsuario;
        try {
            if (conectar.ejecutar(sqlt)) {
                validar = true;
            }
        } catch (Exception e) {
            System.out.println("Error en Guardado del usuario(controlador usuario): " + e);
        }
        return validar;
    }
}
