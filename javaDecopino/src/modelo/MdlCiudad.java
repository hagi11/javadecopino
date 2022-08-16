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
public class MdlCiudad {

    private int id;
    private String ciudad;
    private MdlDepartamento departamento;
    private int estado;
    private Timestamp fregistro;
    private Timestamp factualizado;

    public MdlCiudad() {
    }

    public MdlCiudad(int id, String ciudad, MdlDepartamento departamento, int estado, Timestamp fregistro, Timestamp factualizado) {
        this.id = id;
        this.ciudad = ciudad;
        this.departamento = departamento;
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

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public MdlDepartamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(MdlDepartamento departamento) {
        this.departamento = departamento;
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
