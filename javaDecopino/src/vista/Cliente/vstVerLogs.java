/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.Cliente;

import componentes.ScrollBar;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import componentes.TextPrompt;
import controlador.CtrAuxiliares;
import controlador.CtrLogin;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import modelo.MdlLogs;

/**
 *
 * @author hamme
 */
public class vstVerLogs extends javax.swing.JPanel {

    ArrayList<MdlLogs> listaLogs = new ArrayList();
    private ImageIcon imagen;
    private Icon icon;
    int pagina = 1;
    int pagMax = 1;

    public vstVerLogs() {
        initComponents();
        configuracionVista();
        inicio();
    }

    public void inicio() {
        numeroPaginas();
        llenarTabla();
    }

    public void numeroPaginas() {
        CtrAuxiliares ctra = new CtrAuxiliares();
        float numRegistros = (float) ctra.contarRegistros("madsesion WHERE plataforma = 4") / 10;
        pagMax = (int) Math.floor(numRegistros);
    }

    public void llenarTabla() {
        limpiarTabla();
        CtrLogin ctrl = new CtrLogin();
        lblPagina.setText("Pag " + pagina + " de " + pagMax);
        listaLogs = ctrl.consultarLogs(txtBuscador.getText(), pagina);

        for (int posicion = 0; posicion < listaLogs.size(); posicion++) {
            if (posicion < 9) {
                tblLogs.setValueAt((pagina - 1) + "" + (posicion + 1), posicion, 0);
            } else {
                tblLogs.setValueAt((pagina) + "0", posicion, 0);
            }
            tblLogs.setValueAt(listaLogs.get(posicion).getUsuario().getIdentificacion(), posicion, 1);
            tblLogs.setValueAt(listaLogs.get(posicion).getUsuario().getApellido(), posicion, 2);
            tblLogs.setValueAt(listaLogs.get(posicion).getFregistro(), posicion, 3);
            if (listaLogs.get(posicion).getFactualizado() == null) {
                tblLogs.setValueAt("En sesion", posicion, 4);
            } else {
                tblLogs.setValueAt(listaLogs.get(posicion).getFactualizado(), posicion, 4);
            }
        }
    }

    public void limpiarTabla() {
        for (int posicion = 0; posicion < 11; posicion++) {
            tblLogs.setValueAt("", posicion, 0);
            tblLogs.setValueAt("", posicion, 1);
            tblLogs.setValueAt("", posicion, 2);
            tblLogs.setValueAt("", posicion, 3);
            tblLogs.setValueAt("", posicion, 4);
        }
    }

    public void configuracionVista() {
        String ruta = new String();
        ruta = "src/imagenes/icons/loupe.png";
        this.pintarImagen(this.lblImgBuscador, ruta);
        jScrollPane2.setVerticalScrollBar(new ScrollBar());
        TextPrompt PlaceHolderBuscador = new TextPrompt("Buscador", txtBuscador);
    }

    private void pintarImagen(JLabel label, String ruta) {
        this.imagen = new ImageIcon(ruta);
        this.icon = new ImageIcon(this.imagen.getImage());
        label.setIcon(icon);
        this.repaint();
    }

    public void buscar(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            llenarTabla();
            pagina = 1;
        }
    }

    public void cambiarPagina(int accion) {
        if (accion == 0) {
            if (pagina > 1) {
                pagina = pagina - 1;
                llenarTabla();
            }
        } else {
            if (pagina < pagMax) {
                pagina = pagina + 1;
                llenarTabla();
            }
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

        pnlTabla = new componentes.PanelRound();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblLogs = new componentes.Tabla();
        pnlBuscador = new componentes.PanelRound();
        lblImgBuscador = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        txtBuscador = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        btnListaAnterios = new javax.swing.JButton();
        btnListaSiguiente = new javax.swing.JButton();
        lblPagina = new javax.swing.JLabel();

        setBackground(new java.awt.Color(244, 244, 244));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlTabla.setBackground(new java.awt.Color(255, 255, 255));
        pnlTabla.setRoundBottomLeft(30);
        pnlTabla.setRoundBottomRight(30);
        pnlTabla.setRoundTopLeft(30);
        pnlTabla.setRoundTopRight(30);
        pnlTabla.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane2.setBorder(null);

        tblLogs.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null}
            },
            new String [] {
                "#", "Identificación", "Apellido", "Ingreso", "Salida"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblLogs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLogsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblLogs);
        if (tblLogs.getColumnModel().getColumnCount() > 0) {
            tblLogs.getColumnModel().getColumn(0).setResizable(false);
            tblLogs.getColumnModel().getColumn(0).setPreferredWidth(1);
            tblLogs.getColumnModel().getColumn(1).setResizable(false);
            tblLogs.getColumnModel().getColumn(2).setResizable(false);
            tblLogs.getColumnModel().getColumn(3).setResizable(false);
            tblLogs.getColumnModel().getColumn(4).setResizable(false);
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

        jPanel1.setBackground(new java.awt.Color(244, 244, 244));

        btnListaAnterios.setText("Anterior");
        btnListaAnterios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnListaAnteriosMousePressed(evt);
            }
        });

        btnListaSiguiente.setText("Siguiente");
        btnListaSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListaSiguienteActionPerformed(evt);
            }
        });

        lblPagina.setBackground(new java.awt.Color(244, 244, 244));
        lblPagina.setText("Pag 1 de 1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnListaAnterios)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(lblPagina)
                .addGap(41, 41, 41)
                .addComponent(btnListaSiguiente)
                .addGap(29, 29, 29))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnListaAnterios, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnListaSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPagina))
                .addContainerGap())
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 550, 330, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void tblLogsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLogsMouseClicked

    }//GEN-LAST:event_tblLogsMouseClicked

    private void txtBuscadorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscadorKeyReleased
        buscar(evt);
    }//GEN-LAST:event_txtBuscadorKeyReleased

    private void btnListaAnteriosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnListaAnteriosMousePressed
        cambiarPagina(0);
    }//GEN-LAST:event_btnListaAnteriosMousePressed

    private void btnListaSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListaSiguienteActionPerformed
        cambiarPagina(1);
    }//GEN-LAST:event_btnListaSiguienteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnListaAnterios;
    private javax.swing.JButton btnListaSiguiente;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblImgBuscador;
    private javax.swing.JLabel lblPagina;
    private componentes.PanelRound pnlBuscador;
    private componentes.PanelRound pnlTabla;
    private componentes.Tabla tblLogs;
    private javax.swing.JTextField txtBuscador;
    // End of variables declaration//GEN-END:variables
}