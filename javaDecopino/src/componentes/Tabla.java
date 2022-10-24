/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import componentes.DefaultTableCellHeaderRenderer;

/**
 *
 * @author hamme
 */
public class Tabla extends JTable{
    public Tabla(){
        setShowHorizontalLines(true);
        setGridColor(new Color(230,230,230));
        setRowHeight(40);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setDefaultRenderer(new DefaultTableCellHeaderRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                TablaCabecera cabecera = new TablaCabecera(o +"");
                return cabecera;
            }
           
        });
        setDefaultRenderer(Object.class, new DefaultTableCellHeaderRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean selected, boolean bln1, int i, int i1) {
                
                    Component com = super.getTableCellRendererComponent(jtable, o, selected, bln1, i, i1); 
                    com.setBackground(Color.WHITE);
                    setBorder(noFocusBorder);
                    if(selected){
                        com.setForeground(new Color(13,113,182));
                    }else{
                        com.setForeground(new Color(102,102,102));
                    }
                    return com;
                
            }
            
            
        });
    }
    
}
