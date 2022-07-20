/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.vistaUsuario;


import vista.*;

/**
 *
 * @author hamme
 */
public class vstMostrarInformacionUsuario extends javax.swing.JPanel {

    /**
     * Creates new form pnlInicio
     */
    public vstMostrarInformacionUsuario() {
        initComponents();
    }
    
    public void verLocaciones(){
        vstVerUsuario panel = new vstVerUsuario();
        vstMenu.panelContenedor(panel);
    }
    
    public void agregarLocacion(){
        vstAgregarEditarUsuario panel = new vstAgregarEditarUsuario();
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
        pnlAgregarLocacion = new componentes.PanelRound();
        jLabel2 = new javax.swing.JLabel();
        pnlVerLocaciones = new componentes.PanelRound();
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
        jLabel1.setText("Usuario Informacion");
        panelRound1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 310, 91));

        pnlAgregarLocacion.setRoundBottomLeft(30);
        pnlAgregarLocacion.setRoundBottomRight(30);
        pnlAgregarLocacion.setRoundTopLeft(30);
        pnlAgregarLocacion.setRoundTopRight(30);
        pnlAgregarLocacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlAgregarLocacionMousePressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Agregar Locacion");

        javax.swing.GroupLayout pnlAgregarLocacionLayout = new javax.swing.GroupLayout(pnlAgregarLocacion);
        pnlAgregarLocacion.setLayout(pnlAgregarLocacionLayout);
        pnlAgregarLocacionLayout.setHorizontalGroup(
            pnlAgregarLocacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAgregarLocacionLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel2)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        pnlAgregarLocacionLayout.setVerticalGroup(
            pnlAgregarLocacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAgregarLocacionLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel2)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        panelRound1.add(pnlAgregarLocacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 130, 190, 90));

        pnlVerLocaciones.setRoundBottomLeft(30);
        pnlVerLocaciones.setRoundBottomRight(30);
        pnlVerLocaciones.setRoundTopLeft(30);
        pnlVerLocaciones.setRoundTopRight(30);
        pnlVerLocaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlVerLocacionesMousePressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Ver Locaciones");

        javax.swing.GroupLayout pnlVerLocacionesLayout = new javax.swing.GroupLayout(pnlVerLocaciones);
        pnlVerLocaciones.setLayout(pnlVerLocacionesLayout);
        pnlVerLocacionesLayout.setHorizontalGroup(
            pnlVerLocacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVerLocacionesLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel3)
                .addContainerGap(49, Short.MAX_VALUE))
        );
        pnlVerLocacionesLayout.setVerticalGroup(
            pnlVerLocacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVerLocacionesLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel3)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        panelRound1.add(pnlVerLocaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 190, 90));

        add(panelRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 760, 620));
    }// </editor-fold>//GEN-END:initComponents

    private void pnlVerLocacionesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlVerLocacionesMousePressed
        verLocaciones();
    }//GEN-LAST:event_pnlVerLocacionesMousePressed

    private void pnlAgregarLocacionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlAgregarLocacionMousePressed
        agregarLocacion();
    }//GEN-LAST:event_pnlAgregarLocacionMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private componentes.PanelRound panelRound1;
    private componentes.PanelRound pnlAgregarLocacion;
    private componentes.PanelRound pnlVerLocaciones;
    // End of variables declaration//GEN-END:variables
}
