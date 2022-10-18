/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import conexion.Conexion;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.MdlRecursos;
import modelo.MdlRol;
import modelo.MdlRolRecurso;

/**
 *
 * @author hamme
 */
public class CtrRol {

    public ArrayList<MdlRol> consultar() {
        ArrayList<MdlRol> listaRoles = new ArrayList();

        Conexion conectar = new Conexion();
        String sql = "SELECT * FROM `madroles` WHERE estado = 1";
        

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

    public ArrayList<MdlRecursos> consultarRecursos() {
        ArrayList<MdlRecursos> listaRecursos = new ArrayList();
        Conexion conectar = new Conexion();
        String sql = "SELECT * FROM madrecursos where estado=1";
        ResultSet rs;
        try {
            rs = conectar.consultar(sql);
            while (rs.next()) {
                MdlRecursos recurso = new MdlRecursos();
                recurso.setId(rs.getInt("id"));
                recurso.setNombre(rs.getString("nombre"));
                recurso.setCodigo(rs.getString("codigo"));
                recurso.setDescripcion(rs.getString("descripcion"));
                recurso.setRuta(rs.getString("ruta"));
                recurso.setEstado(rs.getInt("estado"));
                recurso.setFregistro(rs.getTimestamp("fregistro"));
                recurso.setFactualizado(rs.getTimestamp("factualizado"));
                listaRecursos.add(recurso);
            }
        } catch (Exception e) {
            System.out.println("Error en consultar Recursos(controlador rol): " + e);
        }

        return listaRecursos;
    }

    public boolean crearRolPer(ArrayList<MdlRolRecurso> listaRolRecurso, MdlRol rol) {
        int confirmacion = 0;
        boolean validar = false;
        boolean validar2 = false;
        rol.setId(generarId("madroles") + 1);
        rol.setCodigo(rol.getNombre().substring(0, 3).toUpperCase() + rol.getId() + "00");
        validar2 = crearRol(rol);
        rol = mostrarRol(rol.getId());
        if (validar2 == true) {
            for (int posicion = 0; posicion < listaRolRecurso.size(); posicion++) {
                listaRolRecurso.get(posicion).setRol(rol);
                Conexion conectar = new Conexion();
                String sqlt;
                sqlt = "INSERT INTO `madrolrecursos`(`recurso`, `rol`, `crear`, `leer`, `editar`, `mostrar`, `eliminar`, `estado`) VALUES "
                        + "('" + listaRolRecurso.get(posicion).getRecurso().getId() + "','" + listaRolRecurso.get(posicion).getRol().getId() + "','" + listaRolRecurso.get(posicion).getCrear()
                        + "','" + listaRolRecurso.get(posicion).getLeer() + "','" + listaRolRecurso.get(posicion).getEditar() + "','" + listaRolRecurso.get(posicion).getMostrar()
                        + "','" + listaRolRecurso.get(posicion).getEliminar() + "','1')";
                try {
                    if (conectar.ejecutar(sqlt)) {
                        System.out.println("Permisos asignados al Rol exitosamente");
                        confirmacion += 1;
                    }
                } catch (Exception e) {
                    System.out.println("Error en Guardado del Rol(controlador): " + e);
                }
            }
        }

        System.out.println(confirmacion);
        if (confirmacion == 5) {
            validar = true;
        }
        return validar;
    }

    public boolean crearRol(MdlRol rol) {
        boolean confirmacion = false;
        Conexion conectar = new Conexion();
        String sqlt;
        sqlt = "INSERT INTO `madroles`(`nombre`, `codigo`, `funciones`, `estado`) "
                + " VALUES ('" + rol.getNombre() + "','" + rol.getCodigo() + "','" + rol.getFunciones() + "','1')";
        try {
            if (conectar.ejecutar(sqlt)) {
                System.out.println("Rol Guardado");
                confirmacion = true;
            }
        } catch (Exception e) {
            System.out.println("Error en Guardado del Rol(controlador): " + e);
        }
        return confirmacion;
    }

    public MdlRol mostrarRol(int id) {
        MdlRol rol = new MdlRol();
        Conexion conectar = new Conexion();
        String sql = "SELECT * FROM madroles where id =" + id;
        ResultSet rs;
        try {
            rs = conectar.consultar(sql);
            if (rs.next()) {
                rol.setId(rs.getInt("id"));
                rol.setNombre(rs.getString("nombre"));
                rol.setCodigo(rs.getString("codigo"));
                rol.setFunciones(rs.getString("funciones"));
                rol.setEstado(rs.getInt("estado"));
                rol.setFregistro(rs.getTimestamp("fregistro"));
                rol.setFactualizado(rs.getTimestamp("factualizado"));
            }
        } catch (Exception e) {
            System.out.println("Error en consultar Roles(controlador rol): " + e);
        }

        return rol;
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

    public boolean rolExiste(String rolNombre) {
        boolean validar = false;
        Conexion conectar = new Conexion();
        String sql = "SELECT count(`nombre`) as numero FROM madroles WHERE nombre = '" + rolNombre + "'";
        ResultSet rs;
        try {
            rs = conectar.consultar(sql);
            if (rs.next()) {
                if (rs.getString("numero").toString().equals("0")) {
                    validar = true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error en consultar si el rol existe(controlador rol): " + e);
        }
        return validar;
    }
}
