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

/**
 *
 * @author hamme
 */
public class CtrUsuario {
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
            System.out.println("Error en consultar Roles(controlador rol): " + e);
        }
        return listaTipoIdent;
    }
    
}
