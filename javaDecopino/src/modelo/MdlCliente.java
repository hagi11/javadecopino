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
public class MdlCliente extends MdlPersona {

    private int id;
    private String login;
    private String contrasenia;
    private int estado;
    private Timestamp fregistro;
    private Timestamp factualizado;

    public MdlCliente() {
    }

    public MdlCliente(int id, String login, String contrasenia, int estado, Timestamp fregistro, Timestamp factualizado) {
        this.id = id;
        this.login = login;
        this.contrasenia = contrasenia;
        this.estado = estado;
        this.fregistro = fregistro;
        this.factualizado = factualizado;
    }

    public MdlCliente(int idCli, String login, String contrasenia, int estadoCli, Timestamp fregistroCli, Timestamp factualizadoCli, int id, String tidenrificacion, int identificacion, String nombre, String apellido, String correo, String telefono, String direccion, MdlCiudad ciudad, int estado, Timestamp fregistro, Timestamp factualizado) {
        super(id, tidenrificacion, identificacion, nombre, apellido, correo, telefono, direccion, ciudad, estado, fregistro, factualizado);
        this.id = idCli;
        this.login = login;
        this.contrasenia = contrasenia;
        this.estado = estadoCli;
        this.fregistro = fregistroCli;
        this.factualizado = factualizadoCli;
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
