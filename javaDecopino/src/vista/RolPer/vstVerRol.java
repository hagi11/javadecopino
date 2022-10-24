/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.RolPer;

import vista.Usuario.*;
import componentes.ScrollBar;
import java.awt.Color;
import java.awt.Cursor;
import static java.awt.Cursor.HAND_CURSOR;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import componentes.TextPrompt;
import controlador.CtrRol;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.MdlRol;
import vista.vstMenu;

/**
 *
 * @author hamme
 */
public class vstVerRol extends javax.swing.JPanel {

    ArrayList<MdlRol> listaRol = new ArrayList();
    MdlRol rolSeleccionado = new MdlRol();
    boolean habilitar = false;
    private ImageIcon imagen;
    private Icon icon;
    private Color fondoInformacion = new Color(111, 111, 111);
    private Color fondoModificar = new Color(111, 111, 111);
    private Color fondoEliminar = new Color(111, 111, 111);

    private Color fondoHoldInformacion = new Color(255, 255, 255);
    private Color fondoHoldModificar = new Color(255, 255, 255);
    private Color fondoHoldEliminar = new Color(255, 255, 255);

    public vstVerRol() {
        initComponents();
        configuracionVista();
        inicio();
    }

    public void inicio() {
        llenarTabla();
        paginas();
    }

    public void paginas() {
        if (listaRol.size() <= 100) {
            btnListaAnterios.setEnabled(false);
            btnListaSiguiente.setEnabled(false);
        }
    }

    public void llenarTabla() {
        limpiarTabla();
        CtrRol ctrr = new CtrRol();
        listaRol = ctrr.consultar(txtBuscador.getText());
        for (int posicion = 0; posicion < listaRol.size(); posicion++) {
            tblRoles.setValueAt(posicion + 1, posicion, 0);
            tblRoles.setValueAt(listaRol.get(posicion).getNombre(), posicion, 1);
            tblRoles.setValueAt(listaRol.get(posicion).getCodigo(), posicion, 2);
            tblRoles.setValueAt(listaRol.get(posicion).getFunciones(), posicion, 3);
            tblRoles.setValueAt(listaRol.get(posicion).getFregistro().toString().substring(0, 11), posicion, 4);
        }
    }

    public void limpiarTabla() {
        for (int posicion = 0; posicion < listaRol.size(); posicion++) {
            tblRoles.setValueAt("", posicion, 0);
            tblRoles.setValueAt("", posicion, 1);
            tblRoles.setValueAt("", posicion, 2);
            tblRoles.setValueAt("", posicion, 3);
            tblRoles.setValueAt("", posicion, 4);
        }
    }

    public void seleccionarRol() {
        for (int posicion = 0; posicion < listaRol.size(); posicion++) {
            if (tblRoles.getSelectedRow() == posicion) {
                rolSeleccionado = listaRol.get(posicion);
                habilitar = true;
                pnlInformacion.setBackground(new Color(250, 250, 170));
                pnlModificar.setBackground(new Color(170, 225, 250));
                pnlEliminar.setBackground(new Color(250, 200, 170));

                fondoInformacion = new Color(250, 250, 170);
                fondoModificar = new Color(170, 225, 250);
                fondoEliminar = new Color(250, 200, 170);
            }
        }
        if (tblRoles.getSelectedRow() >= listaRol.size()) {
            habilitar = false;
            pnlInformacion.setBackground(new Color(111, 111, 111));
            pnlModificar.setBackground(new Color(111, 111, 111));
            pnlEliminar.setBackground(new Color(111, 111, 111));

            fondoInformacion = new Color(111, 111, 111);
            fondoModificar = new Color(111, 111, 111);
            fondoEliminar = new Color(111, 111, 111);
        }
    }

    public void configuracionVista() {
        String ruta = new String();
        ruta = "src/imagenes/icons/loupe.png";
        this.pintarImagen(this.lblImgBuscador, ruta);
        jScrollPane2.setVerticalScrollBar(new ScrollBar());
        TextPrompt PlaceHolderBuscador = new TextPrompt("Buscador", txtBuscador);
        pnlModificar.setBackground(fondoModificar);
        pnlEliminar.setBackground(fondoEliminar);
        pnlInformacion.setBackground(fondoInformacion);
    }

    private void pintarImagen(JLabel label, String ruta) {
        this.imagen = new ImageIcon(ruta);
        this.icon = new ImageIcon(this.imagen.getImage());
        label.setIcon(icon);
        this.repaint();
    }

    public void MouseOnBoton(JPanel panel, Color color) {
        panel.setCursor(new Cursor(HAND_CURSOR));
        if (habilitar) {
            panel.setBackground(color);
        }
    }

    public void MouseOutBoton(JPanel panel, Color color) {
        if (habilitar) {
            panel.setBackground(color);
        }
    }

    public void PressBtnEliminar() {
        if (habilitar) {

            int result = JOptionPane.showConfirmDialog(null, "Seguro que desea eliminar este rol", "Eliminar", 2);
            if (result == 0) {
                CtrRol ctrr = new CtrRol();
                boolean borrar = ctrr.validarEliminarRol(rolSeleccionado.getId());

                if (borrar == true) {
                    JOptionPane.showMessageDialog(null, "Rol borrado Correctamente", "Información", 1);
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Él rol no pudo ser eliminado debido\na que hay usuario con este rol.", "Información", 1);
                }
            }
        } else {
            itemNoValido();
        }

    }

    public void PressBtnModificar() {
        if (habilitar) {
            vstAgregarEditarRol panel = new vstAgregarEditarRol(rolSeleccionado.getId());
            vstMenu.panelContenedor(panel);
        } else {
            itemNoValido();
        }
    }

    public void PressBtnInformacion() {
        if (habilitar) {
            vstInformacionRol panel = new vstInformacionRol(rolSeleccionado);
            vstMenu.panelContenedor(panel);
        } else {
            itemNoValido();
        }
    }
    
    public void buscar(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            llenarTabla();
        }
    }

    public void itemNoValido() {
        JOptionPane.showMessageDialog(null, "Seleccione un rol", "Error", 1);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlInformacion = new componentes.PanelRound();
        lblInformacion = new javax.swing.JLabel();
        pnlEliminar = new componentes.PanelRound();
        lblEliminar = new javax.swing.JLabel();
        pnlModificar = new componentes.PanelRound();
        lblModificar = new javax.swing.JLabel();
        pnlTabla = new componentes.PanelRound();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblRoles = new componentes.Tabla();
        pnlBuscador = new componentes.PanelRound();
        lblImgBuscador = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        txtBuscador = new javax.swing.JTextField();
        btnListaAnterios = new javax.swing.JButton();
        btnListaSiguiente = new javax.swing.JButton();

        setBackground(new java.awt.Color(244, 244, 244));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlInformacion.setBackground(new java.awt.Color(255, 255, 255));
        pnlInformacion.setRoundBottomLeft(30);
        pnlInformacion.setRoundBottomRight(30);
        pnlInformacion.setRoundTopLeft(30);
        pnlInformacion.setRoundTopRight(30);
        pnlInformacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlInformacionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlInformacionMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlInformacionMousePressed(evt);
            }
        });

        lblInformacion.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblInformacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInformacion.setText("Información");

        javax.swing.GroupLayout pnlInformacionLayout = new javax.swing.GroupLayout(pnlInformacion);
        pnlInformacion.setLayout(pnlInformacionLayout);
        pnlInformacionLayout.setHorizontalGroup(
            pnlInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInformacionLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(lblInformacion, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        pnlInformacionLayout.setVerticalGroup(
            pnlInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblInformacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        add(pnlInformacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 570, 220, 40));

        pnlEliminar.setBackground(new java.awt.Color(255, 255, 255));
        pnlEliminar.setPreferredSize(new java.awt.Dimension(220, 100));
        pnlEliminar.setRoundBottomLeft(30);
        pnlEliminar.setRoundBottomRight(30);
        pnlEliminar.setRoundTopLeft(30);
        pnlEliminar.setRoundTopRight(30);
        pnlEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlEliminarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlEliminarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlEliminarMousePressed(evt);
            }
        });

        lblEliminar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblEliminar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEliminar.setText("Eliminar");

        javax.swing.GroupLayout pnlEliminarLayout = new javax.swing.GroupLayout(pnlEliminar);
        pnlEliminar.setLayout(pnlEliminarLayout);
        pnlEliminarLayout.setHorizontalGroup(
            pnlEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEliminarLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(lblEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );
        pnlEliminarLayout.setVerticalGroup(
            pnlEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEliminarLayout.createSequentialGroup()
                .addComponent(lblEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        add(pnlEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 570, 220, 40));

        pnlModificar.setBackground(new java.awt.Color(255, 255, 255));
        pnlModificar.setPreferredSize(new java.awt.Dimension(220, 100));
        pnlModificar.setRoundBottomLeft(30);
        pnlModificar.setRoundBottomRight(30);
        pnlModificar.setRoundTopLeft(30);
        pnlModificar.setRoundTopRight(30);
        pnlModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlModificarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlModificarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlModificarMousePressed(evt);
            }
        });

        lblModificar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblModificar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblModificar.setText("Modificar");

        javax.swing.GroupLayout pnlModificarLayout = new javax.swing.GroupLayout(pnlModificar);
        pnlModificar.setLayout(pnlModificarLayout);
        pnlModificarLayout.setHorizontalGroup(
            pnlModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlModificarLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(lblModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );
        pnlModificarLayout.setVerticalGroup(
            pnlModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlModificarLayout.createSequentialGroup()
                .addComponent(lblModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        add(pnlModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 570, 220, 40));

        pnlTabla.setBackground(new java.awt.Color(255, 255, 255));
        pnlTabla.setRoundBottomLeft(30);
        pnlTabla.setRoundBottomRight(30);
        pnlTabla.setRoundTopLeft(30);
        pnlTabla.setRoundTopRight(30);
        pnlTabla.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane2.setBorder(null);

        tblRoles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "               #", "         Nombre", "                Codigo", "               Funciones", "           Fecha de registro"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblRoles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRolesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblRoles);
        if (tblRoles.getColumnModel().getColumnCount() > 0) {
            tblRoles.getColumnModel().getColumn(0).setResizable(false);
            tblRoles.getColumnModel().getColumn(0).setPreferredWidth(10);
            tblRoles.getColumnModel().getColumn(1).setResizable(false);
            tblRoles.getColumnModel().getColumn(1).setPreferredWidth(10);
            tblRoles.getColumnModel().getColumn(2).setResizable(false);
            tblRoles.getColumnModel().getColumn(3).setResizable(false);
            tblRoles.getColumnModel().getColumn(4).setResizable(false);
        }

        pnlTabla.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 740, 440));

        add(pnlTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 760, 460));

        pnlBuscador.setBackground(new java.awt.Color(255, 255, 255));
        pnlBuscador.setRoundBottomLeft(30);
        pnlBuscador.setRoundBottomRight(30);
        pnlBuscador.setRoundTopLeft(30);
        pnlBuscador.setRoundTopRight(30);
        pnlBuscador.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        pnlBuscador.add(lblImgBuscador, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 0, 40, 40));
        pnlBuscador.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 550, 10));

        txtBuscador.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtBuscador.setAutoscrolls(false);
        txtBuscador.setBorder(null);
        txtBuscador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscadorKeyReleased(evt);
            }
        });
        pnlBuscador.add(txtBuscador, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 550, 30));

        add(pnlBuscador, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 760, 40));

        btnListaAnterios.setText("<");
        add(btnListaAnterios, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 540, 40, 20));

        btnListaSiguiente.setText(">");
        add(btnListaSiguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 540, 40, 20));
    }// </editor-fold>//GEN-END:initComponents

    private void pnlEliminarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlEliminarMousePressed
        PressBtnEliminar();
    }//GEN-LAST:event_pnlEliminarMousePressed

    private void pnlEliminarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlEliminarMouseEntered
        MouseOnBoton(pnlEliminar, fondoHoldEliminar);

    }//GEN-LAST:event_pnlEliminarMouseEntered

    private void pnlModificarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlModificarMouseEntered
        MouseOnBoton(pnlModificar, fondoHoldModificar);
    }//GEN-LAST:event_pnlModificarMouseEntered

    private void pnlModificarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlModificarMousePressed
        PressBtnModificar();
    }//GEN-LAST:event_pnlModificarMousePressed

    private void pnlInformacionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlInformacionMouseEntered
        MouseOnBoton(pnlInformacion, fondoHoldInformacion);
    }//GEN-LAST:event_pnlInformacionMouseEntered

    private void pnlInformacionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlInformacionMouseExited
        MouseOutBoton(pnlInformacion, fondoInformacion);
    }//GEN-LAST:event_pnlInformacionMouseExited

    private void pnlModificarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlModificarMouseExited
        MouseOutBoton(pnlModificar, fondoModificar);
    }//GEN-LAST:event_pnlModificarMouseExited

    private void pnlEliminarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlEliminarMouseExited
        MouseOutBoton(pnlEliminar, fondoEliminar);
    }//GEN-LAST:event_pnlEliminarMouseExited

    private void pnlInformacionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlInformacionMousePressed
        PressBtnInformacion();
    }//GEN-LAST:event_pnlInformacionMousePressed

    private void tblRolesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRolesMouseClicked
        seleccionarRol();
    }//GEN-LAST:event_tblRolesMouseClicked

    private void txtBuscadorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscadorKeyReleased
        buscar(evt);
    }//GEN-LAST:event_txtBuscadorKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnListaAnterios;
    private javax.swing.JButton btnListaSiguiente;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblEliminar;
    private javax.swing.JLabel lblImgBuscador;
    private javax.swing.JLabel lblInformacion;
    private javax.swing.JLabel lblModificar;
    private componentes.PanelRound pnlBuscador;
    private componentes.PanelRound pnlEliminar;
    private componentes.PanelRound pnlInformacion;
    private componentes.PanelRound pnlModificar;
    private componentes.PanelRound pnlTabla;
    private componentes.Tabla tblRoles;
    private javax.swing.JTextField txtBuscador;
    // End of variables declaration//GEN-END:variables
}
