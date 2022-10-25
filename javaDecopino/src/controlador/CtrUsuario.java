/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import conexion.Conexion;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.MdlUsuario;

/**
 *
 * @author hamme
 */
public class CtrUsuario {

    public ArrayList<MdlUsuario> consultar(String busqueda) {
        ArrayList<MdlUsuario> listaUsuarios = new ArrayList();

        Conexion conectar = new Conexion();
        
        String sql = "SELECT usu.id as idusuario, usu.login, usu.contrasenia,usu.fregistro, "
                + "per.id as idpersona,per.tidentificacion, per.identificacion, per.nombre,per.apellido, per.telefono, per.direccion, per.ciudad, per.fregistro,"
                + " rol.rol as rol FROM mususuarios as usu JOIN madpersonas as per on per.id=usu.persona "
                + "JOIN madusuarioroles as rol on usu.id=rol.usuario  WHERE usu.estado = 1 && (per.nombre LIKE '%"+busqueda+"%' or per.apellido LIKE '%"+busqueda+"%' or per.identificacion LIKE '%"+busqueda+"%')";
        ResultSet rs;
        try {
            rs = conectar.consultar(sql);
            CtrRol ctrr = new CtrRol();
            CtrLocaciones ctrl = new CtrLocaciones();
            while (rs.next()) {
                MdlUsuario usuario = new MdlUsuario();
                //datos usuario
                usuario.setId(rs.getInt("idusuario"));
                usuario.setLogin(rs.getString("login"));
                usuario.setContrasenia(rs.getString("contrasenia"));
                usuario.setFregistro(rs.getTimestamp("fregistro"));

                //datos rol
                usuario.setRol(ctrr.mostrarRol(rs.getInt("rol")));

                //datos ciudad
                usuario.setCiudad(ctrl.consultarCiudadId(rs.getInt("ciudad")));

                //datos persona
                usuario.setIdPersona(rs.getInt("idpersona"));
                usuario.setTidenrificacion(rs.getString("tidentificacion"));
                usuario.setIdentificacion(rs.getInt("identificacion"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setCorreo(rs.getString("login"));
                usuario.setDireccion(rs.getString("direccion"));
                usuario.setTelefono(rs.getString("telefono"));

                listaUsuarios.add(usuario);
            }
        } catch (Exception e) {
            System.out.println("Error en consultar Roles(controlador rol): " + e);
        }
        return listaUsuarios;
    }

    public MdlUsuario mostrarUsuario(int id) {
        MdlUsuario usuario = new MdlUsuario();

        Conexion conectar = new Conexion();
        String sql = "SELECT usu.id as idusuario, usu.login,usu.fregistro, "
                + "per.id as idpersona,per.tidentificacion, per.identificacion, per.nombre,per.apellido, per.telefono, per.direccion, per.ciudad, per.fregistro,"
                + " rol.rol as rol FROM mususuarios as usu JOIN madpersonas as per on per.id=usu.persona "
                + "JOIN madusuarioroles as rol on usu.id=rol.usuario  WHERE usu.estado = 1 && usu.id =" + id;
        ResultSet rs;
        try {
            rs = conectar.consultar(sql);
            CtrRol ctrr = new CtrRol();
            CtrLocaciones ctrl = new CtrLocaciones();
            if (rs.next()) {
                //datos usuario
                usuario.setId(rs.getInt("idusuario"));
                usuario.setLogin(rs.getString("login"));
                usuario.setFregistro(rs.getTimestamp("fregistro"));

                //datos rol
                usuario.setRol(ctrr.mostrarRol(rs.getInt("rol")));

                //datos ciudad
                usuario.setCiudad(ctrl.consultarCiudadId(rs.getInt("ciudad")));

                //datos persona
                usuario.setIdPersona(rs.getInt("idpersona"));
                usuario.setTidenrificacion(rs.getString("tidentificacion"));
                usuario.setIdentificacion(rs.getInt("identificacion"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setCorreo(rs.getString("login"));
                usuario.setDireccion(rs.getString("direccion"));
                usuario.setTelefono(rs.getString("telefono"));
            }
        } catch (Exception e) {
            System.out.println("Error en consultar Roles(controlador rol): " + e);
        }
        return usuario;
    }

    public boolean crearPersonaUsuario(MdlUsuario usuario) {
        boolean confirmacion = true;
        int idpersona = crearPersona(usuario);
        if (idpersona != 0) {
            int idUsuario = crearusuario(usuario, idpersona);
            if (idUsuario != 0) {
                if (!(asignarRol(usuario, idUsuario))) {
                    confirmacion = false;
                }
            } else {
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
                + "('" + usuario.getTidenrificacion() + "','" + usuario.getIdentificacion() + "','" + usuario.getNombre() + "','" + usuario.getApellido() + "','" + usuario.getCorreo() + "',"
                + "'" + usuario.getTelefono() + "','" + usuario.getDireccion() + "','" + usuario.getCiudad().getId() + "',1)";
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
                + "('" + usuario.getCorreo() + "','" + usuario.getContrasenia() + "'," + idPersona + ",1)";
        try {
            if (conectar.ejecutar(sqlt)) {
                validar = generarId("mususuarios");
            }
        } catch (Exception e) {
            System.out.println("Error en Guardado del usuario(controlador usuario): " + e);
        }
        return validar;
    }

    public boolean asignarRol(MdlUsuario usuario, int idUsuario) {
        boolean validar = false;
        Conexion conectar = new Conexion();
        String sqlt;
        sqlt = "INSERT INTO `madusuarioroles`(`usuario`, `rol`, `estado`) VALUES "
                + "(" + idUsuario + "," + usuario.getRol().getId() + ",1)";
        try {
            if (conectar.ejecutar(sqlt)) {
                validar = true;
            }
        } catch (Exception e) {
            System.out.println("Error en Guardado del usuario(controlador usuario): " + e);
        }
        return validar;
    }

    public boolean editarPersonaUsuario(MdlUsuario usuario, int idPersona, int idUsuario, int cambiarContrasenia ) {
        boolean confirmar = false;
        CtrUtilitario ctru = new CtrUtilitario();
        String fecha_actualizado = ctru.fechaHoy() + " " + ctru.horaHoy();

        boolean validar = editarPersona(usuario, idPersona, fecha_actualizado);
        if (validar) {
            if (editarUsuario(usuario, idUsuario, fecha_actualizado,cambiarContrasenia)) {
                if (editarUsuarioRol(usuario, idUsuario, fecha_actualizado)) {
                    confirmar = true;
                }
            }
        }
        return confirmar;
    }

    public boolean editarPersona(MdlUsuario usuario, int idPersona, String fechaActualizado) {
        boolean validar = false;
        Conexion conectar = new Conexion();
        String sqlt;
        sqlt = "UPDATE `madpersonas` SET `tidentificacion`='" + usuario.getTidenrificacion() + "',`identificacion`=" + usuario.getIdentificacion() + ",`nombre`='" + usuario.getNombre() + "',`"
                + "apellido`='" + usuario.getApellido() + "',`correo`='" + usuario.getCorreo() + "',`telefono`='" + usuario.getTelefono() + "',`direccion`='" + usuario.getDireccion() + "',"
                + "`ciudad`=" + usuario.getCiudad().getId() + ",`factualizado`='" + fechaActualizado + "' WHERE id=" + idPersona;
        try {
            if (conectar.ejecutar(sqlt)) {
                validar = true;
            }
        } catch (Exception e) {
            System.out.println("Error en editar Persona (controlador usuario): " + e);
        }
        return validar;
    }

    public boolean editarUsuario(MdlUsuario usuario, int idUsuario, String fechaActualizado, int cambiarContrasenia) {
        boolean validar = false;
        Conexion conectar = new Conexion();
        CtrContrasena ctrc = new CtrContrasena();
        usuario.setContrasenia(ctrc.hash(usuario.getContrasenia()));
        String sqlt;
        if (cambiarContrasenia == 1) {
            sqlt = "UPDATE `mususuarios` SET `login`='" + usuario.getCorreo() + "',`contrasenia`='" + usuario.getContrasenia() + "',`factualizado`='" + fechaActualizado + "' WHERE id=" + idUsuario;
        } else {
            sqlt = "UPDATE `mususuarios` SET `login`='" + usuario.getCorreo() + "',`factualizado`='" + fechaActualizado + "' WHERE id=" + idUsuario;
        }
        try {
            if (conectar.ejecutar(sqlt)) {
                validar = true;
            }
        } catch (Exception e) {
            System.out.println("Error en editar Usuario (controlador usuario): " + e);
        }
        return validar;
    }

    public boolean editarUsuarioRol(MdlUsuario usuario, int idUsuario, String fechaActualizado) {
        boolean validar = false;
        Conexion conectar = new Conexion();
        String sqlt;

        sqlt = "UPDATE `madusuarioroles` SET `usuario`=" + idUsuario + ",`rol`=" + usuario.getRol().getId() + ","
                + "`factualizado`='" + fechaActualizado + "' WHERE `estado` = 1 && `usuario` = " + idUsuario;
        try {
            if (conectar.ejecutar(sqlt)) {
                validar = true;
            }
        } catch (Exception e) {
            System.out.println("Error en editar Usuario Rol(controlador usuario): " + e);
        }
        return validar;
    }

    public boolean validarEliminarUsuario(int idUsuario, int idpersona) {
        boolean confirmar = false;
        if(eliminarPersona(idpersona)){
            if(eliminarUsuario(idUsuario)){
                if(eliminarUsuarioRol(idUsuario)){
                    confirmar= true;
                }
            }
        }
        return confirmar;
    }
    
    public boolean eliminarPersona(int idPersona) {
        boolean validar = false;
        Conexion conectar = new Conexion();
        String sqlt;
        sqlt = "UPDATE `madpersonas` SET `estado`= 0  WHERE id=" + idPersona;
        try {
            if (conectar.ejecutar(sqlt)) {
                validar = true;
            }
        } catch (Exception e) {
            System.out.println("Error en eliminar Persona (controlador usuario): " + e);
        }
        return validar;
    }
    
    public boolean eliminarUsuario(int idUsuario) {
        boolean validar = false;
        Conexion conectar = new Conexion();
        String sqlt;
        sqlt = "UPDATE `mususuarios` SET `estado`= 0  WHERE id=" + idUsuario;
        try {
            if (conectar.ejecutar(sqlt)) { 
                validar = true;
            }
        } catch (Exception e) {
            System.out.println("Error en eliminar usuario (controlador usuario): " + e);
        }
        return validar;
    }
    
    public boolean eliminarUsuarioRol(int idUsuario) {
        boolean validar = false;
        Conexion conectar = new Conexion();
        String sqlt;
        sqlt = "UPDATE `madusuarioroles` SET `estado`= 0  WHERE usuario=" + idUsuario;
        try {
            if (conectar.ejecutar(sqlt)) {
                validar = true;
            }
        } catch (Exception e) {
            System.out.println("Error en eliminar usuario rol (controlador usuario): " + e);
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
