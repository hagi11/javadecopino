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
public class CtrRol {
    public ArrayList<MdlRol> consultar() {
        ArrayList<MdlRol> listaRoles = new ArrayList();
        Conexion conectar = new Conexion();
        String sql = "SELECT * FROM madroles ";
        ResultSet rs;
        try {
            rs = conectar.consultar(sql);
            while (rs.next()) {
                MdlRol rol = new MdlRol();
                rol.setId(rs.getInt("id"));
                rol.setNombre(rs.getString("nombre"));
                rol.setCodigo(rs.getString("codigo"));
                rol.setFunciones(rs.getString("funciones"));
                rol.setEstado(rs.getInt("estado"));
                rol.setFregistro(rs.getTimestamp("fregistro"));
                rol.setFactualizado(rs.getTimestamp("factualizado"));
                listaRoles.add(rol);
            }
        } catch (Exception e) {
            System.out.println("Error en consultar Roles(controlador rol): " + e);
        }
        
        return listaRoles;
    }
    }
