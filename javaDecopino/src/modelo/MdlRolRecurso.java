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
public class MdlRolRecurso {

    private int id;
    private MdlRecursos recurso;
    private MdlRol rol;
    private int crear;
    private int leer;
    private int editar;
    private int mostrar;
    private int eliminar;
    private int estado;
    private Timestamp fregistro;
    private Timestamp factualizado;

    public MdlRolRecurso() {
    }

    public MdlRolRecurso(int id, MdlRecursos recurso, MdlRol rol, int crear, int leer, int editar, int mostrar, int eliminar, int estado, Timestamp fregistro, Timestamp factualizado) {
        this.id = id;
        this.recurso = recurso;
        this.rol = rol;
        this.crear = crear;
        this.leer = leer;
        this.editar = editar;
        this.mostrar = mostrar;
        this.eliminar = eliminar;
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

    public MdlRecursos getRecurso() {
        return recurso;
    }

    public void setRecurso(MdlRecursos recurso) {
        this.recurso = recurso;
    }

    public MdlRol getRol() {
        return rol;
    }

    public void setRol(MdlRol rol) {
        this.rol = rol;
    }

    public int getCrear() {
        return crear;
    }

    public void setCrear(int crear) {
        this.crear = crear;
    }

    public int getLeer() {
        return leer;
    }

    public void setLeer(int leer) {
        this.leer = leer;
    }

    public int getEditar() {
        return editar;
    }

    public void setEditar(int editar) {
        this.editar = editar;
    }

    public int getMostrar() {
        return mostrar;
    }

    public void setMostrar(int mostrar) {
        this.mostrar = mostrar;
    }

    public int getEliminar() {
        return eliminar;
    }

    public void setEliminar(int eliminar) {
        this.eliminar = eliminar;
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
