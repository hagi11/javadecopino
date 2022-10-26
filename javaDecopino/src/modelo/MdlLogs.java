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
public class MdlLogs {

    private int id;
    private MdlUsuario usuario;
    private int estado;
    private Timestamp fregistro;
    private Timestamp factualizado;

    public MdlLogs() {
    }

    public MdlLogs(int id, MdlUsuario usuario, int estado, Timestamp fregistro, Timestamp factualizado) {
        this.id = id;
        this.usuario = usuario;
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

    public MdlUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(MdlUsuario usuario) {
        this.usuario = usuario;
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
