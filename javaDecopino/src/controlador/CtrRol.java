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
        String sql = "SELECT * FROM `madrecursos` WHERE estado = 1 ORDER BY id ASC";
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

                        confirmacion += 1;
                    }
                } catch (Exception e) {
                    System.out.println("Error en Guardado del Rol(controlador): " + e);
                }
            }
        }
        if (confirmacion == 7) {
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
        String sql = "SELECT * FROM madroles where estado = 1 && id =" + id;
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

    public MdlRecursos mostrarRecurso(int id) {
        MdlRecursos recurso = new MdlRecursos();
        Conexion conectar = new Conexion();
        String sql = "SELECT * FROM `madrecursos` WHERE `id` =" + id;
        ResultSet rs;
        try {
            rs = conectar.consultar(sql);

            if (rs.next()) {
                recurso.setId(rs.getInt("id"));
                recurso.setNombre(rs.getString("nombre"));
                recurso.setCodigo(rs.getString("codigo"));
                recurso.setDescripcion(rs.getString("descripcion"));
                recurso.setRuta(rs.getString("ruta"));
                recurso.setEstado(rs.getInt("estado"));
                recurso.setFregistro(rs.getTimestamp("fregistro"));
                recurso.setFactualizado(rs.getTimestamp("factualizado"));
            }
        } catch (Exception e) {
            System.out.println("Error en consultar Recursos(controlador rol): " + e);
        }

        return recurso;
    }

    public ArrayList<MdlRolRecurso> mostrarRolRec(MdlRol rol) {
        ArrayList<MdlRolRecurso> listaRolPer = new ArrayList();
        Conexion conectar = new Conexion();
        String sql = "SELECT * FROM `madrolrecursos` WHERE estado = 1 &&`rol`=" + rol.getId();
        ResultSet rs;
        try {
            rs = conectar.consultar(sql);

            while (rs.next()) {
                MdlRolRecurso rolRecurso = new MdlRolRecurso();
                rolRecurso.setId(rs.getInt("id"));
                rolRecurso.setRecurso(mostrarRecurso(rs.getInt("recurso")));
                rolRecurso.setCrear(rs.getInt("crear"));
                rolRecurso.setLeer(rs.getInt("leer"));
                rolRecurso.setEditar(rs.getInt("editar"));
                rolRecurso.setEliminar(rs.getInt("eliminar"));
                rolRecurso.setMostrar(rs.getInt("mostrar"));

                rolRecurso.setEstado(rs.getInt("estado"));
                rolRecurso.setFregistro(rs.getTimestamp("fregistro"));
                rolRecurso.setFactualizado(rs.getTimestamp("factualizado"));
                listaRolPer.add(rolRecurso);
            }
        } catch (Exception e) {
            System.out.println("Error en consultar Recursos(controlador rol): " + e);
        }
        return listaRolPer;
    }

    public boolean actualizarRolPer(ArrayList<MdlRolRecurso> listaRolRecurso, MdlRol rol) {
        boolean confirmacion = false;
        boolean validar1 = actualizarRol(rol);
        int validar2 = 0;
        if (validar1) {
            CtrUtilitario ctru = new CtrUtilitario();
            String fecha_actualizado = ctru.fechaHoy() + " " + ctru.horaHoy();

            for (int posicion = 0; posicion < listaRolRecurso.size(); posicion++) {

                Conexion conectar = new Conexion();
                String sqlt;
                sqlt = "UPDATE `madrolrecursos` SET `crear`='" + listaRolRecurso.get(posicion).getCrear() + "',`leer`='" + listaRolRecurso.get(posicion).getLeer() + "',`editar`='" + listaRolRecurso.get(posicion).getEditar() + "'"
                        + ",`mostrar`='" + listaRolRecurso.get(posicion).getMostrar() + "',`eliminar`='" + listaRolRecurso.get(posicion).getEliminar() + "',`factualizado`='" + fecha_actualizado + "' WHERE recurso='" + listaRolRecurso.get(posicion).getRecurso().getId() + "' && rol=" + rol.getId();
                try {
                    if (conectar.ejecutar(sqlt)) {
                        validar2 += 1;
                    }
                } catch (Exception e) {
                    System.out.println("Error en Guardado del Rol(controlador): " + e);
                }
            }
            if (validar2 == 7) {
                confirmacion = true;
            }
        }
        return confirmacion;

    }

    public boolean actualizarRol(MdlRol rol) {
        boolean confirmacion = false;
        Conexion conectar = new Conexion();
        rol.setCodigo(rol.getNombre().substring(0, 3).toUpperCase() + rol.getId() + "00");
        CtrUtilitario ctru = new CtrUtilitario();
        String fecha_actualizado = ctru.fechaHoy() + " " + ctru.horaHoy();
        String sqlt;

        sqlt = "UPDATE `madroles` SET `nombre`='" + rol.getNombre() + "',`codigo`='" + rol.getCodigo() + "',`funciones`='" + rol.getFunciones() + "',`factualizado`='" + fecha_actualizado + "' WHERE `id` =" + rol.getId();
        try {
            if (conectar.ejecutar(sqlt)) {
                System.out.println("Rol Actualizado");
                confirmacion = true;
            }
        } catch (Exception e) {
            System.out.println("Error en Guardado del Rol(controlador): " + e);
        }
        return confirmacion;
    }

    public boolean validarEliminarRol(int id) {
        boolean confirmar = false;
        Conexion conectar = new Conexion();

        String sql = "SELECT count(*) as numero FROM `madusuarioroles` WHERE `rol`="+id;
        ResultSet rs;
        try {
            rs = conectar.consultar(sql);
            if (rs.next()) {
                if (rs.getString("numero").toString().equals("0")) {
                    confirmar = eliminarRol(id);
                }
            }
        } catch (Exception e) {
            System.out.println("Error en validarEliminarRol (controlador rol): " + e);
        }

        return confirmar;
    }

    public boolean eliminarRol(int id) {
        boolean confirmar = false;
        boolean validar = false;
        CtrUtilitario ctru = new CtrUtilitario();
        String fecha_actualizado = ctru.fechaHoy() + " " + ctru.horaHoy();
        Conexion conectar = new Conexion();
        String sqlt;
        sqlt = "UPDATE `madrolrecursos` SET `crear`=0,`leer`=0,`editar`=0"
                + ",`mostrar`=0,`eliminar`=0,`estado`=0, `factualizado`='" + fecha_actualizado + "' WHERE  rol=" + id;
        try {
            if (conectar.ejecutar(sqlt)) {
                validar = true;
            }
        } catch (Exception e) {
            System.out.println("Error en Guardado del Rol(controlador): " + e);
        }
        if (validar) {
            sqlt = "UPDATE `madroles` SET `estado`=0 WHERE  id=" + id;
            try {
                if (conectar.ejecutar(sqlt)) {
                    confirmar = true;
                }
            } catch (Exception e) {
                System.out.println("Error en Guardado del Rol(controlador): " + e);
            }
        }
        return confirmar;
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
