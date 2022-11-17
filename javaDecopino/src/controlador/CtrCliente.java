/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import conexion.Conexion;
import java.sql.Array;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.MdlCiudad;
import modelo.MdlCliente;

/**
 *
 * @author cdss2
 */
public class CtrCliente {
    CtrValidador validador = new CtrValidador();
    public ArrayList<MdlCliente> consultarCliente() {        //Consultar clientes desde la base de datos
        ArrayList<MdlCliente> listaClientes = new ArrayList();

        Conexion conectar = new Conexion();
        String sql = "SELECT * FROM mclclientes AS clientes \n"
                + "INNER JOIN madpersonas as personas ON clientes.persona = personas.id WHERE clientes.estado = 1 AND personas.estado = 1";

        ResultSet rs;
        try {
            rs = conectar.consultar(sql);
            while (rs.next()) {
                MdlCliente cliente = new MdlCliente();
                cliente.setId(rs.getInt("id"));
                cliente.setTidenrificacion(rs.getString("tidentificacion"));
                cliente.setIdentificacion(rs.getInt("identificacion"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setIdPersona(rs.getInt("persona"));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setTelefono(rs.getString("telefono"));
                listaClientes.add(cliente);
            }
        } catch (Exception e) {
            System.out.println("Error en consultar Clientes(controlador clientes): " + e);
        }
        return listaClientes;
    }

    public ArrayList<MdlCliente> MostrarInformacionCliente(int id) {        //Consultar clientes por id desde la base de datos
        ArrayList<MdlCliente> listaClientes = new ArrayList();

        Conexion conectar = new Conexion();
        String sql = "SELECT * FROM mclclientes AS clientes \n"
                + "INNER JOIN madpersonas as personas ON clientes.persona = personas.id WHERE clientes.id = " + id + "";

        ResultSet rs;
        try {
            rs = conectar.consultar(sql);
            while (rs.next()) {
                MdlCliente cliente = new MdlCliente();
                MdlCiudad ciudad = new MdlCiudad();
                cliente.setCiudad(consultarCiudad(rs.getInt("ciudad")));
                cliente.setId(rs.getInt("id"));
                cliente.setIdPersona(rs.getInt("persona"));
                cliente.setTidenrificacion(rs.getString("tidentificacion"));
                cliente.setIdentificacion(rs.getInt("identificacion"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setEstado(rs.getInt("estado"));
                cliente.setFregistro(rs.getTimestamp("fregistro"));
                cliente.setFactualizado(rs.getTimestamp("factualizado"));
                listaClientes.add(cliente);
            }
        } catch (Exception e) {
            System.out.println("Error en consultar Clientes(controlador clientes): " + e);
        }
        return listaClientes;
    }

    public MdlCiudad consultarCiudad(int id) {    //Consultar Ciudades por id desde la base de datos
        Conexion conectar = new Conexion();
        MdlCiudad ciudad = new MdlCiudad();
        String sql = "SELECT id, ciudad FROM madciudades as ciudad WHERE ciudad.id = " + id + ""; //busca ciudad segun el id
        ResultSet rs;
        try {
            rs = conectar.consultar(sql);
            while (rs.next()) {
                ciudad.setId(rs.getInt("id"));
                ciudad.setCiudad(rs.getString("ciudad")); // llenando el mdl ciudad para usarlo en el modelo cliente.         
            }
        } catch (Exception e) {
            System.out.println("Error en consultar ciudad(controlador clientes): " + e);
        }
        return ciudad;
    }

    public ArrayList<MdlCiudad> ConsultarTodasCiudades() {    //Consultar Ciudades desde la base de datos
        ArrayList<MdlCiudad> listaCiudades = new ArrayList();
        Conexion conectar = new Conexion();
        
        String sql = "SELECT id, ciudad FROM madciudades"; //busca ciudades
        ResultSet rs;
        try {
            rs = conectar.consultar(sql);
            while (rs.next()) {
                MdlCiudad ciudad = new MdlCiudad();
                ciudad.setId(rs.getInt("id"));
                ciudad.setCiudad(rs.getString("ciudad")); // llenando el mdl ciudad para usarlo en el modelo cliente.         
                listaCiudades.add(ciudad);
            }
        } catch (Exception e) {
            System.out.println("Error en consultar ciudad(controlador clientes): " + e);
        }
        return listaCiudades;
    }

    public void actualizar(MdlCliente cliente) {
        Conexion conectar = new Conexion();
        String sql1 = "UPDATE madpersonas as persona SET tidentificacion = " + cliente.getTidenrificacion() + ", identificacion = " + cliente.getIdentificacion() + ", nombre = '" + cliente.getNombre() + "', apellido = '" + cliente.getApellido() + "',"
                + " correo = '" + cliente.getCorreo() + "', telefono = '" + cliente.getTelefono() + "', direccion = '" + cliente.getDireccion() + "', ciudad = '"+cliente.getCiudad().getId()+"' \n"
                + "WHERE persona.id = " + cliente.getIdPersona()+ "";
        String sql2 = "UPDATE mclclientes as cliente SET login = '"+cliente.getCorreo()+"', contrasenia = '"+cliente.getContrasenia()+"' WHERE cliente.persona = '"+cliente.getIdPersona()+"' ";
        if (validador.validarCaracteresEspeciales(cliente.getNombre()) ==  false){
            JOptionPane.showMessageDialog(null, "El campo nombre esta incorrecto.");
        }
        else if (validador.validarCorreo(cliente.getCorreo()) ==  false){
            JOptionPane.showMessageDialog(null, "El correo no es correcto.");
        }
        else if (validador.validarTamano(cliente.getContrasenia(), 8, 255) ==  false){
            JOptionPane.showMessageDialog(null, "La contraseña debe tener como maximo 8 caracteres.");
        }
        else if (validador.validarTelefono(cliente.getTelefono()) ==  false){
            JOptionPane.showMessageDialog(null, "El numero de telefono no es valido.");
        }
        else{
        try { //Inicio Del Try del sql numero 2 
            if (conectar.ejecutar(sql1)) {
                System.out.println("Los datos se han actualizado.");
            } else {
                System.out.println("Error al actualizar datos.");
            }
        } catch (Exception e) {
            System.out.println("Error en actualizar clientes(controlador cliente - actualizar persona): " + e);
        }// Final Try del sql numero 1
        
         try { //Inicio Try del sql numero 2
            if (conectar.ejecutar(sql2)) {
                JOptionPane.showMessageDialog(null, "Se han actualizado los datos correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se han actualizado los datos.");
            }
        } catch (Exception e) {
            System.out.println("Error en actualizar clientes(controlador cliente - actualizar cliente): " + e);
        }
        }
    }
    
    
    
     public void Eliminar(MdlCliente cliente) {
        Conexion conectar = new Conexion();
        String sql = "UPDATE madpersonas as persona SET estado = 0 WHERE persona.id = '"+cliente.getIdPersona()+"' ";
        String sql2 = "UPDATE mclclientes as cliente SET estado = 0 WHERE cliente.persona = '"+cliente.getIdPersona()+"' ";
        try {
            if (conectar.ejecutar(sql)) {
                System.out.println("Los datos se han eliminado correctamente.");
            } else {
                System.out.println("No se han eliminado los datos.");
            }
        } catch (Exception e) {
            System.out.println("Error en eliminar clientes(controlador cliente - eliminar cliente): " + e);
        }
        try {
            if (conectar.ejecutar(sql2)) {
                JOptionPane.showMessageDialog(null, "Los datos se han eliminado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se han eliminado los datos.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en eliminar clientes(controlador cliente - eliminar cliente): " + e);
        }
        
    }
    
}
