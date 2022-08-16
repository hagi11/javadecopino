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
public class MdlDepartamento {

    private int id;
    private String departamento;
    private int pais;
    private int estado;
    private Timestamp fregistro;
    private Timestamp factualizado;

    public MdlDepartamento() {
    }

    public MdlDepartamento(int id, String departamento, int pais, int estado, Timestamp fregistro, Timestamp factualizado) {
        this.id = id;
        this.departamento = departamento;
        this.pais = pais;
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

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public int getPais() {
        return pais;
    }

    public void setPais(int pais) {
        this.pais = pais;
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
