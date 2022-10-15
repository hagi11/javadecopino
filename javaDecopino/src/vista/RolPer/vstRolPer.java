/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.vistaRolPer;

import vista.RolPer.vstAgregarEditarRol;
import vista.*;

/**
 *
 * @author hamme
 */
public class vstRolPer extends javax.swing.JPanel {

    /**
     * Creates new form pnlInicio
     */
    public vstRolPer() {
        initComponents();
    }
    
    public void verUsuario(){
        vstVerRol panel = new vstVerRol();
        vstMenu.panelContenedor(panel);
    }
    
    public void nuevoUsuario(){
        vstAgregarEditarRol panel = new vstAgregarEditarRol();
        vstMenu.panelContenedor(panel);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRound1 = new componentes.PanelRound();
        jLabel1 = new javax.swing.JLabel();
        pnlNuevoUsuario = new componentes.PanelRound();
        jLabel2 = new javax.swing.JLabel();
        pnlVerUsurios = new componentes.PanelRound();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(244, 244, 244));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRound1.setBackground(new java.awt.Color(255, 255, 255));
        panelRound1.setRoundBottomLeft(30);
        panelRound1.setRoundBottomRight(30);
        panelRound1.setRoundTopLeft(30);
        panelRound1.setRoundTopRight(30);
        panelRound1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Rol");
        panelRound1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 117, 91));

        pnlNuevoUsuario.setRoundBottomLeft(30);
        pnlNuevoUsuario.setRoundBottomRight(30);
        pnlNuevoUsuario.setRoundTopLeft(30);
        pnlNuevoUsuario.setRoundTopRight(30);
        pnlNuevoUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlNuevoUsuarioMousePressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Nuevo Rol");

        javax.swing.GroupLayout pnlNuevoUsuarioLayout = new javax.swing.GroupLayout(pnlNuevoUsuario);
        pnlNuevoUsuario.setLayout(pnlNuevoUsuarioLayout);
        pnlNuevoUsuarioLayout.setHorizontalGroup(
            pnlNuevoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNuevoUsuarioLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel2)
                .addContainerGap(77, Short.MAX_VALUE))
        );
        pnlNuevoUsuarioLayout.setVerticalGroup(
            pnlNuevoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNuevoUsuarioLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel2)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        panelRound1.add(pnlNuevoUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 130, 190, 90));

        pnlVerUsurios.setRoundBottomLeft(30);
        pnlVerUsurios.setRoundBottomRight(30);
        pnlVerUsurios.setRoundTopLeft(30);
        pnlVerUsurios.setRoundTopRight(30);
        pnlVerUsurios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlVerUsuriosMousePressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Ver Roles");

        javax.swing.GroupLayout pnlVerUsuriosLayout = new javax.swing.GroupLayout(pnlVerUsurios);
        pnlVerUsurios.setLayout(pnlVerUsuriosLayout);
        pnlVerUsuriosLayout.setHorizontalGroup(
            pnlVerUsuriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVerUsuriosLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel3)
                .addContainerGap(84, Short.MAX_VALUE))
        );
        pnlVerUsuriosLayout.setVerticalGroup(
            pnlVerUsuriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVerUsuriosLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel3)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        panelRound1.add(pnlVerUsurios, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 190, 90));

        add(panelRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 760, 620));
    }// </editor-fold>//GEN-END:initComponents

    private void pnlVerUsuriosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlVerUsuriosMousePressed
        verUsuario();
    }//GEN-LAST:event_pnlVerUsuriosMousePressed

    private void pnlNuevoUsuarioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlNuevoUsuarioMousePressed
        nuevoUsuario();
    }//GEN-LAST:event_pnlNuevoUsuarioMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private componentes.PanelRound panelRound1;
    private componentes.PanelRound pnlNuevoUsuario;
    private componentes.PanelRound pnlVerUsurios;
    // End of variables declaration//GEN-END:variables
}
