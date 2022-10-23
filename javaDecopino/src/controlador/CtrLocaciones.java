/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import conexion.Conexion;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.MdlCiudad;
import modelo.MdlDepartamento;

/**
 *
 * @author HOME
 */
public class CtrLocaciones {
    
    public ArrayList<MdlCiudad> consultarCiudades(int Id) {
        ArrayList<MdlCiudad> listaCiudades = new ArrayList();
        Conexion conectar = new Conexion();
        String sql = "SELECT * FROM madciudades where departamento="+ Id;
        ResultSet rs;
        try {
            rs = conectar.consultar(sql);
            while (rs.next()) {
                MdlCiudad ciudad = new MdlCiudad();
                
                ciudad.setId(rs.getInt("id"));
                ciudad.setCiudad(rs.getString("ciudad"));
                ciudad.setDepartamento(consultarDep(rs.getInt("departamento")));
                ciudad.setEstado(rs.getInt("estado"));
                ciudad.setFregistro(rs.getTimestamp("fregistro"));
                ciudad.setFactualizado(rs.getTimestamp("factualizado"));
                listaCiudades.add(ciudad);
            }
        } catch (Exception e) {
            System.out.println("Error en consultar Ciudades(controlador ciudad): " + e);
        }
        
        return listaCiudades;
    }
    
    public ArrayList<MdlCiudad> consultarTodasCiudades(){
        ArrayList<MdlCiudad> listaCiudades = new ArrayList();
        Conexion conectar = new Conexion();
        String sql = "SELECT * FROM madciudades where estado =1";
        ResultSet rs;
        try {
            rs = conectar.consultar(sql);
            while (rs.next()) {
                MdlCiudad ciudad = new MdlCiudad();
                
                ciudad.setId(rs.getInt("id"));
                ciudad.setCiudad(rs.getString("ciudad"));
                ciudad.setDepartamento(consultarDep(rs.getInt("departamento")));
                ciudad.setEstado(rs.getInt("estado"));
                ciudad.setFregistro(rs.getTimestamp("fregistro"));
                ciudad.setFactualizado(rs.getTimestamp("factualizado"));
                listaCiudades.add(ciudad);
            }
        } catch (Exception e) {
            System.out.println("Error en consultar Ciudades(controlador ciudad): " + e);
        }
        
        return listaCiudades;
    }
    
    
    
    
    
    public MdlDepartamento consultarDep(int Id) {
        MdlDepartamento Departamento = new MdlDepartamento();
        Conexion conectar = new Conexion();
        String sql = "SELECT * FROM departamentos where id ="+ Id;
        ResultSet rs;
        try {
            rs = conectar.consultar(sql);
            while (rs.next()) {
               
                
                Departamento.setId(rs.getInt("id"));
                Departamento.setDepartamento(rs.getString("departamento"));
                Departamento.setPais(rs.getInt("pais"));
                Departamento.setEstado(rs.getInt("estado"));
                Departamento.setFregistro(rs.getTimestamp("fregistro"));
                Departamento.setFactualizado(rs.getTimestamp("factualizado"));
            }
        } catch (Exception e) {
            System.out.println("Error en consultar Ciudades(controlador ciudad): " + e);
        }
        
        return Departamento;
    }
    
    
    public ArrayList<MdlDepartamento> consultarDepartamentos(int Id) {
        ArrayList<MdlDepartamento> listaDepartamentos = new ArrayList();
        Conexion conectar = new Conexion();
        String sql = "SELECT * FROM departamentos where pais=" + Id;
        ResultSet rs;
        try {
            rs = conectar.consultar(sql);
            while (rs.next()) {
                MdlDepartamento departamento = new MdlDepartamento();
                
                departamento.setId(rs.getInt("id"));
                departamento.setDepartamento(rs.getString("departamento"));
                departamento.setPais(rs.getInt("pais"));
                departamento.setEstado(rs.getInt("estado"));
                departamento.setFregistro(rs.getTimestamp("fregistro"));
                departamento.setFactualizado(rs.getTimestamp("factualizado"));
                listaDepartamentos.add(departamento);
            }
        } catch (Exception e) {
            System.out.println("Error en consultar Ciudades(controlador ciudad): " + e);
        }
        
        return listaDepartamentos;
    }
    
    
    
    
    public int consultarDepId(String Dep) {
        int Departamento = 0;
        Conexion conectar = new Conexion();
        String sql = "SELECT id FROM departamentos where departamento ='"+Dep+"'";
        ResultSet rs;
        try {
            rs = conectar.consultar(sql);
            if (rs.next()) {
                Departamento=(rs.getInt("id"));
            }
        } catch (Exception e) {
            System.out.println("Error en consultar Ciudades(controlador ciudad): " + e);
        }
        
        return Departamento;
    }

    public MdlCiudad mostrarCiudadNombre(String ciudadNombre) {
        MdlCiudad ciudad = new MdlCiudad();
        Conexion conectar = new Conexion();
        String sql = "SELECT * FROM madciudades where estado = 1 && ciudad ='"+ciudadNombre+"'";
        ResultSet rs;
        try {
            rs = conectar.consultar(sql);
            if (rs.next()) {
                ciudad.setId(rs.getInt("id"));
                ciudad.setCiudad(rs.getString("ciudad"));
                ciudad.setDepartamento(consultarDep(rs.getInt("departamento")));
                ciudad.setEstado(rs.getInt("estado"));
                ciudad.setFregistro(rs.getTimestamp("fregistro"));
                ciudad.setFactualizado(rs.getTimestamp("factualizado"));
            }
        } catch (Exception e) {
            System.out.println("Error en consultar Roles(controlador rol): " + e);
        }

        return ciudad;
    }

}