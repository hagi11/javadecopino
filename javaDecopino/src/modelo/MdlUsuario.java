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
public class MdlUsuario extends MdlPersona {

    private int id;
    private String login;
    private String contrasenia;
    private int estado;
    private MdlRol rol;
    private Timestamp fregistro;
    private Timestamp factualizado;

    public MdlUsuario() {
    }

    public MdlUsuario(int id, String login, String contrasenia, int estado, MdlRol rol, Timestamp fregistro, Timestamp factualizado) {
        this.id = id;
        this.login = login;
        this.contrasenia = contrasenia;
        this.estado = estado;
        this.rol = rol;
        this.fregistro = fregistro;
        this.factualizado = factualizado;
    }

    public MdlUsuario(int idUsu, String login, String contrasenia, int estadoUsu, MdlRol rol, Timestamp fregistroUsu, Timestamp factualizadoUsu, int id, String tidenrificacion, int identificacion, String nombre, String apellido, String correo, String telefono, String direccion, MdlCiudad ciudad, int estado, Timestamp fregistro, Timestamp factualizado) {
        super(id, tidenrificacion, identificacion, nombre, apellido, correo, telefono, direccion, ciudad, estado, fregistro, factualizado);
        this.id = idUsu;
        this.login = login;
        this.contrasenia = contrasenia;
        this.estado = estadoUsu;
        this.rol = rol;
        this.fregistro = fregistroUsu;
        this.factualizado = factualizadoUsu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public MdlRol getRol() {
        return rol;
    }

    public void setRol(MdlRol rol) {
        this.rol = rol;
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
