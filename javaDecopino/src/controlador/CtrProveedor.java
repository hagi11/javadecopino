/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import conexion.Conexion;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.MdlProveedor;

/**
 *
 * @author HOME
 */
public class CtrProveedor {
    public ArrayList<MdlProveedor> consultar(String busqueda, int pagina) {
        ArrayList<MdlProveedor> listaProveedors = new ArrayList();

        Conexion conectar = new Conexion();
        
        String sql = "SELECT pro.id as idproveedor, pro.fregistro, "
                + "per.id as idpersona,per.tidentificacion, per.identificacion, per.nombre,per.apellido, per.telefono, per.correo, per.direccion, per.ciudad, per.fregistro "
                + "FROM mprproveedores as pro JOIN madpersonas as per on per.id=pro.persona "
                + "WHERE pro.estado = 1 && (per.nombre LIKE '%"+busqueda+"%' or per.apellido LIKE '%"+busqueda+"%' or "
                + "per.identificacion LIKE '%"+busqueda+"%') LIMIT 10 OFFSET "+((pagina-1)*10)+"";
        ResultSet rs;
        try {
            rs = conectar.consultar(sql);
            CtrLocaciones ctrl = new CtrLocaciones();
            while (rs.next()) {
                MdlProveedor proveedor = new MdlProveedor();
                //datos proveedor
                proveedor.setId(rs.getInt("idproveedor"));
                proveedor.setFregistro(rs.getTimestamp("fregistro"));
                
                //datos ciudad
                proveedor.setCiudad(ctrl.consultarCiudadId(rs.getInt("ciudad")));

                //datos persona
                proveedor.setIdPersona(rs.getInt("idpersona"));
                proveedor.setTidenrificacion(rs.getString("tidentificacion"));
                proveedor.setIdentificacion(rs.getInt("identificacion"));
                proveedor.setNombre(rs.getString("nombre"));
                proveedor.setApellido(rs.getString("apellido"));
                proveedor.setCorreo(rs.getString("correo"));
                proveedor.setDireccion(rs.getString("direccion"));
                proveedor.setTelefono(rs.getString("telefono"));

                listaProveedors.add(proveedor);
            }
        } catch (Exception e) {
            System.out.println("Error en consultar proveedores (controlador proveedores): " + e);
        }
        return listaProveedors;
    }

    public MdlProveedor mostrarProveedor(int id) {
        MdlProveedor proveedor = new MdlProveedor();

        Conexion conectar = new Conexion();        
        
        String sql = "SELECT pro.id as idproveedor, pro.fregistro, "
                + "per.id as idpersona,per.tidentificacion, per.identificacion, per.nombre,per.apellido, per.telefono, per.correo, per.direccion, per.ciudad, per.fregistro "
                + "FROM mprproveedores as pro "
                + "JOIN madpersonas as per on per.id=pro.persona "
                + "WHERE pro.estado = 1 && pro.id =" + id;
        ResultSet rs;
        try {
            rs = conectar.consultar(sql);
            CtrLocaciones ctrl = new CtrLocaciones();
            if (rs.next()) {
                //datos proveedor
                proveedor.setId(rs.getInt("idproveedor"));
                proveedor.setFregistro(rs.getTimestamp("fregistro"));
                //datos ciudad
                proveedor.setCiudad(ctrl.consultarCiudadId(rs.getInt("ciudad")));
                //datos persona
                proveedor.setIdPersona(rs.getInt("idpersona"));
                proveedor.setTidenrificacion(rs.getString("tidentificacion"));
                proveedor.setIdentificacion(rs.getInt("identificacion"));
                proveedor.setNombre(rs.getString("nombre"));
                proveedor.setApellido(rs.getString("apellido"));
                proveedor.setCorreo(rs.getString("correo"));
                proveedor.setDireccion(rs.getString("direccion"));
                proveedor.setTelefono(rs.getString("telefono"));
            }
        } catch (Exception e) {
            System.out.println("Error en consultar proveedor(controlador proveedor): " + e);
        }
        return proveedor;
    }

    public boolean crearPersonaProveedor(MdlProveedor proveedor) {
        boolean confirmacion = true;
        int idpersona = crearPersona(proveedor);
        if (idpersona != 0) {
            int idProveedor = crearproveedor(proveedor, idpersona);
            if (idProveedor == 0) {
               confirmacion = false;
            } 
        } else {
            confirmacion = false;
        }
        return confirmacion;
    }

    public int crearPersona(MdlProveedor proveedor) {
        int validar = 0;
        Conexion conectar = new Conexion();
        String sqlt;
        sqlt = "INSERT INTO `madpersonas`(`tidentificacion`, `identificacion`, `nombre`, `apellido`, `correo`, `telefono`, `direccion`, `ciudad`, `estado`) VALUES "
                + "('" + proveedor.getTidenrificacion() + "','" + proveedor.getIdentificacion() + "','" + proveedor.getNombre() + "','" + proveedor.getApellido() + "','" + proveedor.getCorreo() + "',"
                + "'" + proveedor.getTelefono() + "','" + proveedor.getDireccion() + "','" + proveedor.getCiudad().getId() + "',1)";
        try {
            if (conectar.ejecutar(sqlt)) {
                validar = generarId("madpersonas");
            }
        } catch (Exception e) {
            System.out.println("Error en Guardado de persona (controlador Proveedor): " + e);
        }
        return validar;
    }

    public int crearproveedor(MdlProveedor proveedor, int idPersona) {
        int validar = 0;
        Conexion conectar = new Conexion();
        String sqlt;
        sqlt = "INSERT INTO `mprproveedores`(`persona`, `estado`) VALUES "
                + "(" + idPersona + ",1)";
        try {
            if (conectar.ejecutar(sqlt)) {
                validar = generarId("mprproveedores");
            }
        } catch (Exception e) {
            System.out.println("Error en Guardado del proveedor(controlador proveedor): " + e);
        }
        return validar;
    }

    public boolean editarPersonaProveedor(MdlProveedor proveedor, int idPersona, int idProveedor) {
        boolean confirmar = false;
        CtrUtilitario ctru = new CtrUtilitario();
        String fecha_actualizado = ctru.fechaHoy() + " " + ctru.horaHoy();

        boolean validar = editarPersona(proveedor, idPersona, fecha_actualizado);
        if (validar) {
            if(editarProveedor(proveedor, idProveedor, fecha_actualizado)) {
              confirmar = true;  
            }
        }
        return confirmar;
    }

    public boolean editarPersona(MdlProveedor proveedor, int idPersona, String fechaActualizado) {
        boolean validar = false;
        Conexion conectar = new Conexion();
        String sqlt;
        sqlt = "UPDATE `madpersonas` SET `tidentificacion`='" + proveedor.getTidenrificacion() + "',`identificacion`=" + proveedor.getIdentificacion() + ",`nombre`='" + proveedor.getNombre() + "',`"
                + "apellido`='" + proveedor.getApellido() + "',`correo`='" + proveedor.getCorreo() + "',`telefono`='" + proveedor.getTelefono() + "',`direccion`='" + proveedor.getDireccion() + "',"
                + "`ciudad`=" + proveedor.getCiudad().getId() + ",`factualizado`='" + fechaActualizado + "' WHERE id=" + idPersona;
        try {
            if (conectar.ejecutar(sqlt)) {
                validar = true;
            }
        } catch (Exception e) {
            System.out.println("Error en editar Persona (controlador proveedor): " + e);
        }
        return validar;
    }

    public boolean editarProveedor(MdlProveedor proveedor, int idProveedor, String fechaActualizado) {
        boolean validar = false;
        Conexion conectar = new Conexion();
        String sqlt = "UPDATE `mprproveedores` SET `factualizado`='" + fechaActualizado + "' WHERE id=" + idProveedor;
        
        try {
            if (conectar.ejecutar(sqlt)) {
                validar = true;
            }
        } catch (Exception e) {
            System.out.println("Error en editar Proveedor (controlador proveedor): " + e);
        }
        return validar;
    }

    public boolean validarEliminarProveedor(int idProveedor, int idpersona) {
        boolean confirmar = false;
        if(eliminarPersona(idpersona)){
            if(eliminarProveedor(idProveedor)){
                confirmar = true;
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
            System.out.println("Error en eliminar Persona (controlador proveedor): " + e);
        }
        return validar;
    }
    
    public boolean eliminarProveedor(int idProveedor) {
        boolean validar = false;
        Conexion conectar = new Conexion();
        String sqlt;
        sqlt = "UPDATE `mprproveedores` SET `estado`= 0  WHERE id=" + idProveedor;
        try {
            if (conectar.ejecutar(sqlt)) { 
                validar = true;
            }
        } catch (Exception e) {
            System.out.println("Error en eliminar proveedor (controlador proveedor): " + e);
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

    public boolean correNoExiste(String proveedorCorreo) {
        boolean validar = false;
        Conexion conectar = new Conexion();
        String sql = "SELECT count(`nombre`) as numero FROM `madpersonas` WHERE correo = '" + proveedorCorreo + "'";
        ResultSet rs;
        try {
            rs = conectar.consultar(sql);
            if (rs.next()) {
                if (rs.getString("numero").toString().equals("0")) {
                    validar = true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error en consultar si el proveedor existe(controlador proveedor): " + e);
        }
        return validar;
    }

    public boolean identNoExiste(int proveedorIdent) {
        boolean validar = false;
        Conexion conectar = new Conexion();
        String sql = "SELECT count(`nombre`) as numero FROM `madpersonas` WHERE identificacion = '" + proveedorIdent + "'";
        ResultSet rs;
        try {
            rs = conectar.consultar(sql);
            if (rs.next()) {
                if (rs.getString("numero").toString().equals("0")) {
                    validar = true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error en consultar si el identificacion existe(controlador proveedor): " + e);
        }
        return validar;
    }

    
    
}
