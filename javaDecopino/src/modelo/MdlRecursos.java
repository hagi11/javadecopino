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
public class MdlRecursos {

    private int id;
    private String nombre;
    private String codigo;
    private String descripcion;
    private String ruta;
    private int estado;
    private Timestamp fregistro;
    private Timestamp factualizado;

    public MdlRecursos() {
    }

    public MdlRecursos(int id, String nombre, String codigo, String descripcion, String ruta, int estado, Timestamp fregistro, Timestamp factualizado) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.ruta = ruta;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
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
