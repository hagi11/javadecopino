/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.Cliente;

import controlador.CtrCliente;
import java.util.ArrayList;
import modelo.MdlCliente;

/**
 *
 * @author hamme
 */
public class vstInformacionCliente extends javax.swing.JPanel {

    /**
     * Creates new form pnlInicio
     */
    MdlCliente clienteInfo = new MdlCliente();

    public vstInformacionCliente(MdlCliente cliente) {
        clienteInfo = cliente;
        initComponents();
        inicio();
    }

    public void inicio() {
        MostrarInformacionClientes();
    }

    ArrayList<MdlCliente> ListaClientes = new ArrayList(); //Array de la lista de clientes

    public void MostrarInformacionClientes() {
        lblTitulo.setText("Informacion de: " + clienteInfo.getNombre() + "");
        CtrCliente cliente = new CtrCliente();
        ListaClientes = cliente.MostrarInformacionCliente(clienteInfo.getId());
        for (int posicion = 0; posicion < ListaClientes.size(); posicion++) {
            txtTipoId.setText(ListaClientes.get(posicion).getTidenrificacion());
            txtIdentificacion.setText(Integer.toString(ListaClientes.get(posicion).getIdentificacion()));
            txtNombre.setText(ListaClientes.get(posicion).getNombre());
            txtApellido.setText(ListaClientes.get(posicion).getApellido());
            txtCorreo.setText(ListaClientes.get(posicion).getCorreo());
            txtTelefono.setText(ListaClientes.get(posicion).getTelefono());
            txtDireccion.setText(ListaClientes.get(posicion).getDireccion());
            txtCiudad.setText(ListaClientes.get(posicion).getCiudad().getCiudad());
        }
    }

    public void bloquearCampos() {
        for (int posicion = 0; posicion < ListaClientes.size(); posicion++) {
            txtTipoId.setEditable(false);
            txtIdentificacion.disable();
            txtNombre.disable();
            txtApellido.disable();
            txtCorreo.disable();
            txtTelefono.disable();
            txtDireccion.disable();
            txtCiudad.disable();
        }
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
        lblTitulo = new javax.swing.JLabel();
        pnlTipoId = new componentes.PanelRound();
        jSeparator3 = new javax.swing.JSeparator();
        txtTipoId = new javax.swing.JTextField();
        lblTipoId = new javax.swing.JLabel();
        panelRound5 = new componentes.PanelRound();
        jSeparator4 = new javax.swing.JSeparator();
        jTextField4 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        pnlNombre = new componentes.PanelRound();
        jSeparator2 = new javax.swing.JSeparator();
        txtNombre = new javax.swing.JTextField();
        lblNombre = new javax.swing.JLabel();
        pnlApellido = new componentes.PanelRound();
        jSeparator5 = new javax.swing.JSeparator();
        txtApellido = new javax.swing.JTextField();
        lblApellido = new javax.swing.JLabel();
        pnlCorreo = new componentes.PanelRound();
        jSeparator6 = new javax.swing.JSeparator();
        txtCorreo = new javax.swing.JTextField();
        lblCorreo = new javax.swing.JLabel();
        pnlTelefono = new componentes.PanelRound();
        jSeparator7 = new javax.swing.JSeparator();
        txtTelefono = new javax.swing.JTextField();
        lblTelefono = new javax.swing.JLabel();
        pnlDirección = new componentes.PanelRound();
        jSeparator8 = new javax.swing.JSeparator();
        txtDireccion = new javax.swing.JTextField();
        lblDireccion = new javax.swing.JLabel();
        pnlCiudad = new componentes.PanelRound();
        jSeparator1 = new javax.swing.JSeparator();
        txtCiudad = new javax.swing.JTextField();
        lblCiudad = new javax.swing.JLabel();
        panelRound11 = new componentes.PanelRound();
        jSeparator9 = new javax.swing.JSeparator();
        jTextField9 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        pnlIdentificacion = new componentes.PanelRound();
        jSeparator10 = new javax.swing.JSeparator();
        txtIdentificacion = new javax.swing.JTextField();
        lblIdentificacion = new javax.swing.JLabel();

        setBackground(new java.awt.Color(244, 244, 244));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRound1.setBackground(new java.awt.Color(255, 255, 255));
        panelRound1.setRoundBottomLeft(30);
        panelRound1.setRoundBottomRight(30);
        panelRound1.setRoundTopLeft(30);
        panelRound1.setRoundTopRight(30);
        panelRound1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 18)); // NOI18N
        lblTitulo.setText("Cliente Informacion");
        panelRound1.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 690, 90));

        pnlTipoId.setBackground(new java.awt.Color(255, 255, 255));
        pnlTipoId.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        pnlTipoId.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 460, 10));

        txtTipoId.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 12)); // NOI18N
        txtTipoId.setBorder(null);
        txtTipoId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTipoIdActionPerformed(evt);
            }
        });
        pnlTipoId.add(txtTipoId, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 460, 20));

        lblTipoId.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 12)); // NOI18N
        lblTipoId.setText("Tipo Identificación");
        pnlTipoId.add(lblTipoId, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 160, 20));

        panelRound5.setBackground(new java.awt.Color(255, 255, 255));
        panelRound5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelRound5.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 460, 10));

        jTextField4.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 12)); // NOI18N
        jTextField4.setBorder(null);
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        panelRound5.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 460, 20));

        jLabel11.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 12)); // NOI18N
        jLabel11.setText("Identificacion");
        panelRound5.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 20));

        pnlTipoId.add(panelRound5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 630, 40));

        panelRound1.add(pnlTipoId, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 700, 40));

        pnlNombre.setBackground(new java.awt.Color(255, 255, 255));
        pnlNombre.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        pnlNombre.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 460, 10));

        txtNombre.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 12)); // NOI18N
        txtNombre.setBorder(null);
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        pnlNombre.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 460, 20));

        lblNombre.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 12)); // NOI18N
        lblNombre.setText("Nombre");
        pnlNombre.add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 20));

        panelRound1.add(pnlNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 700, 40));

        pnlApellido.setBackground(new java.awt.Color(255, 255, 255));
        pnlApellido.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        pnlApellido.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 460, 10));

        txtApellido.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 12)); // NOI18N
        txtApellido.setBorder(null);
        txtApellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidoActionPerformed(evt);
            }
        });
        pnlApellido.add(txtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 460, 20));

        lblApellido.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 12)); // NOI18N
        lblApellido.setText("Apellido");
        pnlApellido.add(lblApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 20));

        panelRound1.add(pnlApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 690, 40));

        pnlCorreo.setBackground(new java.awt.Color(255, 255, 255));
        pnlCorreo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        pnlCorreo.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 460, 10));

        txtCorreo.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 12)); // NOI18N
        txtCorreo.setBorder(null);
        txtCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCorreoActionPerformed(evt);
            }
        });
        pnlCorreo.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 460, 20));

        lblCorreo.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 12)); // NOI18N
        lblCorreo.setText("Correo");
        pnlCorreo.add(lblCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 20));

        panelRound1.add(pnlCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 690, 40));

        pnlTelefono.setBackground(new java.awt.Color(255, 255, 255));
        pnlTelefono.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        pnlTelefono.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 460, 10));

        txtTelefono.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 12)); // NOI18N
        txtTelefono.setBorder(null);
        txtTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoActionPerformed(evt);
            }
        });
        pnlTelefono.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 460, 20));

        lblTelefono.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 12)); // NOI18N
        lblTelefono.setText("Telefono");
        pnlTelefono.add(lblTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 20));

        panelRound1.add(pnlTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 690, 40));

        pnlDirección.setBackground(new java.awt.Color(255, 255, 255));
        pnlDirección.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        pnlDirección.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 460, 10));

        txtDireccion.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 12)); // NOI18N
        txtDireccion.setBorder(null);
        txtDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionActionPerformed(evt);
            }
        });
        pnlDirección.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 460, 20));

        lblDireccion.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 12)); // NOI18N
        lblDireccion.setText("Dirección");
        pnlDirección.add(lblDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 20));

        panelRound1.add(pnlDirección, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 700, 40));

        pnlCiudad.setBackground(new java.awt.Color(255, 255, 255));
        pnlCiudad.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        pnlCiudad.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 460, 10));

        txtCiudad.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 12)); // NOI18N
        txtCiudad.setBorder(null);
        txtCiudad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCiudadActionPerformed(evt);
            }
        });
        pnlCiudad.add(txtCiudad, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 460, 20));

        lblCiudad.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 12)); // NOI18N
        lblCiudad.setText("Ciudad");
        pnlCiudad.add(lblCiudad, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 110, 20));

        panelRound11.setBackground(new java.awt.Color(255, 255, 255));
        panelRound11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelRound11.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 460, 10));

        jTextField9.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 12)); // NOI18N
        jTextField9.setBorder(null);
        jTextField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField9ActionPerformed(evt);
            }
        });
        panelRound11.add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 460, 20));

        jLabel7.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 12)); // NOI18N
        jLabel7.setText("Nombre");
        panelRound11.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 20));

        pnlCiudad.add(panelRound11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 630, 40));

        panelRound1.add(pnlCiudad, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 470, 690, 40));

        pnlIdentificacion.setBackground(new java.awt.Color(255, 255, 255));
        pnlIdentificacion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        pnlIdentificacion.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 460, 10));

        txtIdentificacion.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 12)); // NOI18N
        txtIdentificacion.setBorder(null);
        txtIdentificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdentificacionActionPerformed(evt);
            }
        });
        pnlIdentificacion.add(txtIdentificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 460, 20));

        lblIdentificacion.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 12)); // NOI18N
        lblIdentificacion.setText("Identificacion");
        pnlIdentificacion.add(lblIdentificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 20));

        panelRound1.add(pnlIdentificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 690, 40));

        add(panelRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 760, 620));
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidoActionPerformed

    private void txtCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoActionPerformed

    private void txtTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoActionPerformed

    private void txtDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void txtTipoIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTipoIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTipoIdActionPerformed

    private void txtCiudadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCiudadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCiudadActionPerformed

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField9ActionPerformed

    private void txtIdentificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdentificacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdentificacionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JLabel lblApellido;
    private javax.swing.JLabel lblCiudad;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblIdentificacion;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTipoId;
    private javax.swing.JLabel lblTitulo;
    private componentes.PanelRound panelRound1;
    private componentes.PanelRound panelRound11;
    private componentes.PanelRound panelRound5;
    private componentes.PanelRound pnlApellido;
    private componentes.PanelRound pnlCiudad;
    private componentes.PanelRound pnlCorreo;
    private componentes.PanelRound pnlDirección;
    private componentes.PanelRound pnlIdentificacion;
    private componentes.PanelRound pnlNombre;
    private componentes.PanelRound pnlTelefono;
    private componentes.PanelRound pnlTipoId;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtCiudad;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtIdentificacion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTipoId;
    // End of variables declaration//GEN-END:variables
}
