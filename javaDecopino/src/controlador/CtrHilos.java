/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import conexion.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hamme
 */
public class CtrHilos extends Thread {

    public CtrHilos(String str) {
        super(str);
    }

    public void run() {
        Conexion conectar = new Conexion();
        String sql = "SELECT id FROM `madparametros` WHERE `id` = 1";
        ResultSet rs;
        int i = 0;
        
        while (true) {
            try {
                Thread.sleep(60000);
                rs = conectar.consultar(sql);
                while (rs.next()) {
                    i = (rs.getInt("id"));
                }
            } catch (InterruptedException e) {

            } catch (SQLException ex) {

            }
        }
    }

//    public static void main(String[] args) {
//        new CtrHilos("Pepe").start();
//        new CtrHilos("Juan").start();
//        System.out.println("Termina thread main");
//    }
}
