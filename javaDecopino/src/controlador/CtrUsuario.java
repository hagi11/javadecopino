/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import conexion.Conexion;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.MdlRol;
import modelo.MdlUsuario;

/**
 *
 * @author hamme
 */
public class CtrUsuario {

    public boolean crearPersonaUsuario(MdlUsuario usuario) {
        boolean confirmacion = true;
        int idpersona =crearPersona(usuario);
        if (idpersona != 0) {
            int idUsuario = crearusuario(usuario, idpersona);
            if (idUsuario != 0) {
                if(!(asignarRol(usuario, idUsuario))){
                    confirmacion=false;
                }
            }else{
                confirmacion = false;
            }
        } else {
            confirmacion = false;
        }
        return confirmacion;
    }

    public int crearPersona(MdlUsuario usuario) {
        int validar = 0;
        Conexion conectar = new Conexion();
        String sqlt;
        sqlt = "INSERT INTO `madpersonas`(`tidentificacion`, `identificacion`, `nombre`, `apellido`, `correo`, `telefono`, `direccion`, `ciudad`, `estado`) VALUES "
                + "('"+usuario.getTidenrificacion()+"','"+usuario.getIdentificacion()+"','"+usuario.getNombre()+"','"+usuario.getApellido()+"','"+usuario.getCorreo()+"',"
                + "'"+usuario.getTelefono()+"','"+usuario.getDireccion()+"','"+usuario.getCiudad().getId()+"',1)";
        try {
            if (conectar.ejecutar(sqlt)) {
                validar = generarId("madpersonas");
            }
        } catch (Exception e) {
            System.out.println("Error en Guardado de persona (controlador Usuario): " + e);
        }
        return validar;
    }

    public int crearusuario(MdlUsuario usuario, int idPersona) {
        int validar = 0;
        Conexion conectar = new Conexion();
        CtrContrasena ctrc = new CtrContrasena();
        usuario.setContrasenia(ctrc.hash(usuario.getContrasenia()));
        String sqlt;
        sqlt = "INSERT INTO `mususuarios`( `login`, `contrasenia`, `persona`, `estado`) VALUES "
                + "('"+usuario.getCorreo()+"','"+usuario.getContrasenia()+"',"+idPersona+",1)";
        try {
            if (conectar.ejecutar(sqlt)) {
                validar = generarId("mususuarios");
            }
        } catch (Exception e) {
            System.out.println("Error en Guardado del usuario(controlador usuario): " + e);
        }
        return validar;
    }
    
    public boolean asignarRol(MdlUsuario usuario, int idUsuario){
        boolean validar = false;
        Conexion conectar = new Conexion();
        String sqlt;
        sqlt = "INSERT INTO `madusuarioroles`(`usuario`, `rol`, `estado`) VALUES "
                + "("+idUsuario+","+usuario.getRol().getId()+",1)";
        try {
            if (conectar.ejecutar(sqlt)) {
                validar = true;
            }
        } catch (Exception e) {
            System.out.println("Error en Guardado del usuario(controlador usuario): " + e);
        }
        return validar;
    }
    
    public int generarId(String peticion) {
        int codigo = 0;
        Conexion conectar = new Conexion();
        ResultSet rs = null;
        String sql;
        sql = "SELECT MAX(id) AS id FROM " + peticion;
        try {
            rs = conectar.consultar(sql);
            if (rs.next()) {
                codigo = rs.getInt("id");
            }
        } catch (Exception e) {
            System.out.println("Error en consulta de codigo de id(controlador user): " + e);
        }
        return codigo;
    }

    public boolean correNoExiste(String usuarioCorreo) {
        boolean validar = false;
        Conexion conectar = new Conexion();
        String sql = "SELECT count(`nombre`) as numero FROM `madpersonas` WHERE correo = '" + usuarioCorreo + "'";
        ResultSet rs;
        try {
            rs = conectar.consultar(sql);
            if (rs.next()) {
                if (rs.getString("numero").toString().equals("0")) {
                    validar = true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error en consultar si el usuario existe(controlador usuario): " + e);
        }
        return validar;
    }

    public boolean identNoExiste(int usuarioIdent) {
        boolean validar = false;
        Conexion conectar = new Conexion();
        String sql = "SELECT count(`nombre`) as numero FROM `madpersonas` WHERE identificacion = '" + usuarioIdent + "'";
        ResultSet rs;
        try {
            rs = conectar.consultar(sql);
            if (rs.next()) {
                if (rs.getString("numero").toString().equals("0")) {
                    validar = true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error en consultar si el identificacion existe(controlador usuario): " + e);
        }
        return validar;
    }

}
