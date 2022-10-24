/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import conexion.Conexion;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author hamme
 */
public class CtrAuxiliares {
    public ArrayList<String> consultarTipoIdent() {
        ArrayList<String> listaTipoIdent = new ArrayList();
        Conexion conectar = new Conexion();
        String sql = "SELECT `nombre` FROM `madparametros` WHERE `tiparametro`= 1 && `estado` = 1";
        ResultSet rs;
        try {
            rs = conectar.consultar(sql);
            while (rs.next()) {
                listaTipoIdent.add(rs.getString("nombre"));
            }
        } catch (Exception e) {
            System.out.println("Error en consultar Tipo de identificacion(controlador auxiliar): " + e);
        }
        return listaTipoIdent;
    }
    
    public String mostrarTipoIdentNombre(String nombre) {
        String tipoIdent = "";
        Conexion conectar = new Conexion();
        String sql = "SELECT id FROM `madparametros` where estado = 1 && nombre ='"+nombre+"'";
        ResultSet rs;
        try {
            rs = conectar.consultar(sql);
            if (rs.next()) {
                tipoIdent = rs.getString("id");
            }
        } catch (Exception e) {
            System.out.println("Error en consultar id del Tipo de identificacion(controlador auxiliar): " + e);
        }

        return tipoIdent;
    }
    
    public String mostrarTipoIdentId(String id){
        String tipoIdent = "";
        Conexion conectar = new Conexion();
        String sql = "SELECT nombre FROM `madparametros` where estado = 1 && id ="+id;
        ResultSet rs;
        try {
            rs = conectar.consultar(sql);
            if (rs.next()) {
                tipoIdent = rs.getString("nombre");
            }
        } catch (Exception e) {
            System.out.println("Error en consultar nombre del Tipo de identificacion(controlador auxiliar): " + e);
        }

        return tipoIdent;
    }   
    
    
}
