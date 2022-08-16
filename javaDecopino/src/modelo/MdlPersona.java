/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Timestamp;

/**
 *
 * @author hamme
 */
public class MdlPersona {

    private int id;
    private String tidenrificacion;
    private int identificacion;
    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
    private String direccion;
    private MdlCiudad ciudad;
    private int estado;
    private Timestamp fregistro;
    private Timestamp factualizado;

    public MdlPersona() {
    }

    public MdlPersona(int id, String tidenrificacion, int identificacion, String nombre, String apellido, String correo, String telefono, String direccion, MdlCiudad ciudad, int estado, Timestamp fregistro, Timestamp factualizado) {
        this.id = id;
        this.tidenrificacion = tidenrificacion;
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.estado = estado;
        this.fregistro = fregistro;
        this.factualizado = factualizado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTidenrificacion() {
        return tidenrificacion;
    }

    public void setTidenrificacion(String tidenrificacion) {
        this.tidenrificacion = tidenrificacion;
    }

    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public MdlCiudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(MdlCiudad ciudad) {
        this.ciudad = ciudad;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Timestamp getFregistro() {
        return fregistro;
    }

    public void setFregistro(Timestamp fregistro) {
        this.fregistro = fregistro;
    }

    public Timestamp getFactualizado() {
        return factualizado;
    }

    public void setFactualizado(Timestamp factualizado) {
        this.factualizado = factualizado;
    }
    
    
    
    
    

}

    