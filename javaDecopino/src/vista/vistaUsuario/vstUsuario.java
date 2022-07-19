/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.vistaUsuario;

import vista.vstMenu;

/**
 *
 * @author hamme
 */
public class vstUsuario extends javax.swing.JPanel {

    /**
     * Creates new form pnlInicio
     */
    public vstUsuario() {
        initComponents();
    }
    
    public void verUsuario(){
        vstVerUsuario panel = new vstVerUsuario();
        vstMenu.panelContenedor(panel);
    }
    public void agregarUsuario(){
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

        pnlContenedorUsuario = new componentes.PanelRound();
        jLabel1 = new javax.swing.JLabel();
        pnlAgregarUsuario = new componentes.PanelRound();
        jLabel2 = new javax.swing.JLabel();
        pnlVerUsuario = new componentes.PanelRound();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(244, 244, 244));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlContenedorUsuario.setBackground(new java.awt.Color(255, 255, 255));
        pnlContenedorUsuario.setRoundBottomLeft(30);
        pnlContenedorUsuario.setRoundBottomRight(30);
        pnlContenedorUsuario.setRoundTopLeft(30);
        pnlContenedorUsuario.setRoundTopRight(30);
        pnlContenedorUsuario.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Usuarios");
        pnlContenedorUsuario.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 117, 91));

        pnlAgregarUsuario.setRoundBottomLeft(30);
        pnlAgregarUsuario.setRoundBottomRight(30);
        pnlAgregarUsuario.setRoundTopLeft(30);
        pnlAgregarUsuario.setRoundTopRight(30);
        pnlAgregarUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlAgregarUsuarioMousePressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Agregar usuarios");

        javax.swing.GroupLayout pnlAgregarUsuarioLayout = new javax.swing.GroupLayout(pnlAgregarUsuario);
        pnlAgregarUsuario.setLayout(pnlAgregarUsuarioLayout);
        pnlAgregarUsuarioLayout.setHorizontalGroup(
            pnlAgregarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAgregarUsuarioLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel2)
                .addContainerGap(38, Short.MAX_VALUE))
        );
        pnlAgregarUsuarioLayout.setVerticalGroup(
            pnlAgregarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAgregarUsuarioLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel2)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        pnlContenedorUsuario.add(pnlAgregarUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 90, 190, 90));

        pnlVerUsuario.setRoundBottomLeft(30);
        pnlVerUsuario.setRoundBottomRight(30);
        pnlVerUsuario.setRoundTopLeft(30);
        pnlVerUsuario.setRoundTopRight(30);
        pnlVerUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlVerUsuarioMousePressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Ver usuarios");

        javax.swing.GroupLayout pnlVerUsuarioLayout = new javax.swing.GroupLayout(pnlVerUsuario);
        pnlVerUsuario.setLayout(pnlVerUsuarioLayout);
        pnlVerUsuarioLayout.setHorizontalGroup(
            pnlVerUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVerUsuarioLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel3)
                .addContainerGap(66, Short.MAX_VALUE))
        );
        pnlVerUsuarioLayout.setVerticalGroup(
            pnlVerUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVerUsuarioLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel3)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        pnlContenedorUsuario.add(pnlVerUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 190, 90));

        add(pnlContenedorUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 520));
    }// </editor-fold>//GEN-END:initComponents

    private void pnlVerUsuarioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlVerUsuarioMousePressed
        verUsuario();
    }//GEN-LAST:event_pnlVerUsuarioMousePressed

    private void pnlAgregarUsuarioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlAgregarUsuarioMousePressed
        agregarUsuario();
    }//GEN-LAST:event_pnlAgregarUsuarioMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private componentes.PanelRound pnlAgregarUsuario;
    private componentes.PanelRound pnlContenedorUsuario;
    private componentes.PanelRound pnlVerUsuario;
    // End of variables declaration//GEN-END:variables
}
