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
public class MdlProveedor extends MdlPersona{
    private int id;
    private int estado;
    private Timestamp fregistro;
    private Timestamp factualizado;

    public MdlProveedor() {
    }

    public MdlProveedor(int id, int estado, Timestamp fregistro, Timestamp factualizado) {
        this.id = id;
        this.estado = estado;
        this.fregistro = fregistro;
        this.factualizado = factualizado;
    }

    public MdlProveedor(int idPro, int estadoPro, Timestamp fregistroPro, Timestamp factualizadoPro, int id, String tidenrificacion, int identificacion, String nombre, String apellido, String correo, String telefono, String direccion, MdlCiudad ciudad, int estado, Timestamp fregistro, Timestamp factualizado) {
        super(id, tidenrificacion, identificacion, nombre, apellido, correo, telefono, direccion, ciudad, estado, fregistro, factualizado);
        this.id = idPro;
        this.estado = estadoPro;
        this.fregistro = fregistroPro;
        this.factualizado = factualizadoPro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
