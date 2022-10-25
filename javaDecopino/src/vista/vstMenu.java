/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.CtrLogin;
import vista.RolPer.vstRolPer;
import vista.Usuario.vstUsuario;
import vista.Proveedor.vstProveedor;

import vista.Cliente.vstVerCliente;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import modelo.MdlUsuario;

/**
 *
 * @author hamme
 */
public class vstMenu extends javax.swing.JFrame {

    private ImageIcon imagen;
    private Icon icon;
    private Color colorActivo = new Color(187, 189, 245);
    private Color colorInactivo = new Color(176, 157, 245);
    private Color colorSelecciondo = new Color(255, 197, 97);
    private Color colorFondoPerfil = new Color(0, 0, 0);
    private Color lblColor = new Color(255, 255, 255);
    private Color lblColorUser = new Color(255, 255, 255);
    private Font font = new Font("Serif", Font.BOLD, 15);
    private int numItem;
    public static MdlUsuario usuarioLogueado;

    public vstMenu() {
        initComponents();
        setLocationRelativeTo(this);
        ConfiguracionesVisuales();
    }

    public void ConfiguracionesVisuales() {
        inicio();
        imagenMenu();
        confTipografia();
    }

    public void inicio() {
        VstLogin vstl = new VstLogin();
        usuarioLogueado = vstl.getUsuario();
        numItem = 0;
        colorItemsMenu();
        vstInicio inicio = new vstInicio();
        panelContenedor(inicio);
        lblUsuarioNombre.setText(usuarioLogueado.getApellido());
        lblUsuarioRol.setText(usuarioLogueado.getRol().getNombre());
        String imgUsuario = usuarioLogueado.getNombre().substring(0, 1) + usuarioLogueado.getApellido().substring(0, 1);
        lblImgUsuario.setText(imgUsuario);
    }

    public void imagenMenu() { //Funcion para agregar las Imagenes Ustilizadas en el menu
        String ruta = new String();

//        ruta = "src/imagenes/borrar.jpeg";
//        this.pintarImagenEscalada(this.lblFotoPerfil, ruta);
//        ruta = "src/imagenes/fondos/hoja.jpg";
//        this.pintarImagen(this.lblmenu, ruta);
        ruta = "src/imagenes/fondos/banner.png";
        this.pintarImagen(this.lblLogo, ruta);

    }

    public void imagenItems() { //Funcion para agregar las Imagenes de todos lo assets
        String ruta = new String();
        ruta = "src/imagenes/icons/user.png";
        this.pintarImagen(this.lblItemImgCliente, ruta);
    }

    private void pintarImagen(JLabel label, String ruta) {
        this.imagen = new ImageIcon(ruta);
        this.icon = new ImageIcon(this.imagen.getImage());
        label.setIcon(icon);
        this.repaint();
    }

    private void pintarImagenEscalada(JLabel lbl, String ruta) {
        this.imagen = new ImageIcon(ruta);
        this.icon = new ImageIcon(this.imagen.getImage().getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_DEFAULT));
        lbl.setIcon(icon);
        this.repaint();
    }

    public void colorItemsMenu() {
        pnlItemClientes.setBackground(colorInactivo);
        pnlItemUsuarios.setBackground(colorInactivo);

        pnlItemRolPer.setBackground(colorInactivo);
        pnlItemProveedores.setBackground(colorInactivo);

        pnlPerfil.setBackground(colorFondoPerfil);

    }

    public void inColor(JPanel panel, Color color, int numPanel) {
        if (numItem != numPanel) {
            panel.setBackground(color);
        }
    }

    public void outColor(JPanel panel, Color color, int numPanel) {
        if (numItem != numPanel) {
            panel.setBackground(color);
        }
    }

    public void seleccionItem(JPanel panel, Color color, int numPanel) {
        numItem = numPanel;
        colorItemsMenu();
        panel.setBackground(color);

    }

    public void confTipografia() {

        lblItemCliente.setFont(font);
        lblItemCliente.setForeground(lblColor);
        lblItemUsuario.setFont(font);
        lblItemUsuario.setForeground(lblColor);
        lblItemProveedores.setFont(font);
        lblItemProveedores.setForeground(lblColor);
        lblItemRolPer.setFont(font);
        lblItemRolPer.setForeground(lblColor);

        lblUsuarioNombre.setFont(font);
        lblUsuarioNombre.setForeground(lblColorUser);
        lblUsuarioRol.setFont(font);
        lblUsuarioRol.setForeground(lblColorUser);

    }

    public static void panelContenedor(JPanel panel) {
        panel.setSize(760, 620);
        panel.setLocation(0, 0);

        vstMenu.pnlContenedor.removeAll();
        vstMenu.pnlContenedor.add(panel, BorderLayout.CENTER);
        vstMenu.pnlContenedor.revalidate();
        vstMenu.pnlContenedor.repaint();

    }

    public void ItemBtnUsuario() {
        seleccionItem(pnlItemUsuarios, colorSelecciondo, 1);
        vstUsuario panel = new vstUsuario();
        panelContenedor(panel);
    }

    public void ItemBtnCliente() {
        seleccionItem(pnlItemClientes, colorSelecciondo, 2);
        vstVerCliente panel = new vstVerCliente();
        panelContenedor(panel);
    }

    public void ItemBtnProveedor() {
        seleccionItem(pnlItemProveedores, colorSelecciondo, 3);
        vstProveedor panel = new vstProveedor();
        panelContenedor(panel);
    }

    public void ItemBtnRolPer() {
        seleccionItem(pnlItemRolPer, colorSelecciondo, 4);
        vstRolPer panel = new vstRolPer();
        panelContenedor(panel);
    }

    public void cerrarSesion(int modo) {
        if(modo == 0){
        int result = JOptionPane.showConfirmDialog(null, "Seguro que desea cerrar sesión", "Cerrar Sesion", 2);
        if (result == 0) {
            CtrLogin ctrl = new CtrLogin();
            if (ctrl.cerrarSesion(usuarioLogueado.getId())) {
                dispose();
                VstLogin login = new VstLogin();
                login.setVisible(true);
            }
        }
        }else{
            CtrLogin ctrl = new CtrLogin();
            ctrl.cerrarSesion(usuarioLogueado.getId());
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

        pnlPrincipal = new javax.swing.JPanel();
        pnlMenu4 = new componentes.pnlMenu();
        pnlItemUsuarios = new javax.swing.JPanel();
        lblItemImgUsuario = new javax.swing.JLabel();
        lblItemUsuario = new javax.swing.JLabel();
        pnlItemClientes = new javax.swing.JPanel();
        lblItemImgCliente = new javax.swing.JLabel();
        lblItemCliente = new javax.swing.JLabel();
        pnlItemProveedores = new javax.swing.JPanel();
        lblItemImgProveedores = new javax.swing.JLabel();
        lblItemProveedores = new javax.swing.JLabel();
        pnlItemRolPer = new javax.swing.JPanel();
        lblItemImgRolPer = new javax.swing.JLabel();
        lblItemRolPer = new javax.swing.JLabel();
        pnlLogo = new componentes.PanelRound();
        lblLogo = new javax.swing.JLabel();
        pnlPerfil = new componentes.PanelRound();
        lblUsuarioNombre = new javax.swing.JLabel();
        lblUsuarioRol = new javax.swing.JLabel();
        btnCerrarSesion = new componentes.PanelRound();
        jLabel1 = new javax.swing.JLabel();
        pnlImgUsuario = new componentes.PanelRound();
        lblImgUsuario = new javax.swing.JLabel();
        pnlContenedor = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(1024, 640));
        setName("menu"); // NOI18N
        setResizable(false);
        setSize(new java.awt.Dimension(1024, 640));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlPrincipal.setBackground(new java.awt.Color(244, 244, 244));
        pnlPrincipal.setForeground(new java.awt.Color(255, 255, 255));
        pnlPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlMenu4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlItemUsuarios.setBackground(new java.awt.Color(255, 204, 0));
        pnlItemUsuarios.setPreferredSize(new java.awt.Dimension(220, 30));
        pnlItemUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlItemUsuariosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlItemUsuariosMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlItemUsuariosMousePressed(evt);
            }
        });
        pnlItemUsuarios.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblItemImgUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icons/user.png"))); // NOI18N
        pnlItemUsuarios.add(lblItemImgUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, -1, 30));

        lblItemUsuario.setFont(new java.awt.Font("Perpetua", 1, 20)); // NOI18N
        lblItemUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblItemUsuario.setText("Usuarios");
        pnlItemUsuarios.add(lblItemUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 80, 20));

        pnlMenu4.add(pnlItemUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 220, -1));

        pnlItemClientes.setBackground(getBackground());
        pnlItemClientes.setPreferredSize(new java.awt.Dimension(220, 30));
        pnlItemClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlItemClientesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlItemClientesMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlItemClientesMousePressed(evt);
            }
        });
        pnlItemClientes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblItemImgCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icons/user.png"))); // NOI18N
        pnlItemClientes.add(lblItemImgCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 30, 30));

        lblItemCliente.setFont(new java.awt.Font("Poor Richard", 1, 20)); // NOI18N
        lblItemCliente.setForeground(new java.awt.Color(255, 255, 255));
        lblItemCliente.setText("Clientes");
        pnlItemClientes.add(lblItemCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 70, 20));

        pnlMenu4.add(pnlItemClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 220, -1));

        pnlItemProveedores.setBackground(new java.awt.Color(255, 204, 0));
        pnlItemProveedores.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pnlItemProveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlItemProveedoresMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlItemProveedoresMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlItemProveedoresMousePressed(evt);
            }
        });
        pnlItemProveedores.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblItemImgProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icons/user.png"))); // NOI18N
        pnlItemProveedores.add(lblItemImgProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 30, 30));

        lblItemProveedores.setFont(new java.awt.Font("Poor Richard", 1, 20)); // NOI18N
        lblItemProveedores.setForeground(new java.awt.Color(255, 255, 255));
        lblItemProveedores.setText("Proveedores");
        pnlItemProveedores.add(lblItemProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 110, 20));

        pnlMenu4.add(pnlItemProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 220, 30));

        pnlItemRolPer.setBackground(new java.awt.Color(255, 204, 0));
        pnlItemRolPer.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pnlItemRolPer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlItemRolPerMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlItemRolPerMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlItemRolPerMousePressed(evt);
            }
        });
        pnlItemRolPer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblItemImgRolPer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icons/user.png"))); // NOI18N
        pnlItemRolPer.add(lblItemImgRolPer, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 30, 30));

        lblItemRolPer.setFont(new java.awt.Font("Poor Richard", 1, 20)); // NOI18N
        lblItemRolPer.setForeground(new java.awt.Color(255, 255, 255));
        lblItemRolPer.setText("Roles");
        pnlItemRolPer.add(lblItemRolPer, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 140, 20));

        pnlMenu4.add(pnlItemRolPer, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 220, 30));

        pnlLogo.setRoundBottomLeft(30);
        pnlLogo.setRoundBottomRight(30);
        pnlLogo.setRoundTopLeft(30);
        pnlLogo.setRoundTopRight(30);
        pnlLogo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblLogo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblLogo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblLogoMousePressed(evt);
            }
        });
        pnlLogo.add(lblLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 100));

        pnlMenu4.add(pnlLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 100));

        pnlPerfil.setBackground(new java.awt.Color(102, 102, 102));
        pnlPerfil.setRoundBottomLeft(30);
        pnlPerfil.setRoundBottomRight(30);
        pnlPerfil.setRoundTopLeft(30);
        pnlPerfil.setRoundTopRight(30);
        pnlPerfil.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblUsuarioNombre.setText("Usuario");
        pnlPerfil.add(lblUsuarioNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 70, 20));

        lblUsuarioRol.setText("Rol");
        pnlPerfil.add(lblUsuarioRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 100, 20));

        btnCerrarSesion.setRoundBottomLeft(10);
        btnCerrarSesion.setRoundBottomRight(10);
        btnCerrarSesion.setRoundTopLeft(10);
        btnCerrarSesion.setRoundTopRight(10);
        btnCerrarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnCerrarSesionMousePressed(evt);
            }
        });
        btnCerrarSesion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Cerrar Sesión");
        btnCerrarSesion.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 20));

        pnlPerfil.add(btnCerrarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 90, 20));

        pnlImgUsuario.setRoundBottomLeft(30);
        pnlImgUsuario.setRoundBottomRight(30);
        pnlImgUsuario.setRoundTopLeft(30);
        pnlImgUsuario.setRoundTopRight(30);

        lblImgUsuario.setBackground(new java.awt.Color(204, 204, 204));
        lblImgUsuario.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lblImgUsuario.setForeground(new java.awt.Color(255, 153, 51));
        lblImgUsuario.setText("HG");
        pnlImgUsuario.add(lblImgUsuario);

        pnlPerfil.add(pnlImgUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 60, 60));

        pnlMenu4.add(pnlPerfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 530, 220, 100));

        pnlPrincipal.add(pnlMenu4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 640));

        pnlContenedor.setBackground(new java.awt.Color(244, 244, 244));
        pnlPrincipal.add(pnlContenedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 760, 620));

        getContentPane().add(pnlPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1020, 640));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void pnlItemUsuariosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlItemUsuariosMouseEntered
        // TODO add your handling code here:
        inColor(pnlItemUsuarios, colorActivo, 1);
    }//GEN-LAST:event_pnlItemUsuariosMouseEntered

    private void pnlItemUsuariosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlItemUsuariosMouseExited
        // TODO add your handling code here:
        outColor(pnlItemUsuarios, colorInactivo, 1);
    }//GEN-LAST:event_pnlItemUsuariosMouseExited

    private void pnlItemUsuariosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlItemUsuariosMousePressed
        // TODO add your handling code here:
        ItemBtnUsuario();
    }//GEN-LAST:event_pnlItemUsuariosMousePressed

    private void pnlItemClientesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlItemClientesMouseEntered
        // TODO add your handling code here:
        inColor(pnlItemClientes, colorActivo, 2);
    }//GEN-LAST:event_pnlItemClientesMouseEntered

    private void pnlItemClientesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlItemClientesMouseExited
        // TODO add your handling code here:
        outColor(pnlItemClientes, colorInactivo, 2);
    }//GEN-LAST:event_pnlItemClientesMouseExited

    private void pnlItemClientesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlItemClientesMousePressed
        // TODO add your handling code here:
        ItemBtnCliente();
    }//GEN-LAST:event_pnlItemClientesMousePressed

    private void pnlItemProveedoresMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlItemProveedoresMouseEntered
        inColor(pnlItemProveedores, colorActivo, 3);
    }//GEN-LAST:event_pnlItemProveedoresMouseEntered

    private void pnlItemProveedoresMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlItemProveedoresMousePressed
        ItemBtnProveedor();
    }//GEN-LAST:event_pnlItemProveedoresMousePressed

    private void pnlItemProveedoresMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlItemProveedoresMouseExited
        outColor(pnlItemProveedores, colorInactivo, 3);
    }//GEN-LAST:event_pnlItemProveedoresMouseExited

    private void pnlItemRolPerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlItemRolPerMouseEntered
        inColor(pnlItemRolPer, colorActivo, 4);
    }//GEN-LAST:event_pnlItemRolPerMouseEntered

    private void pnlItemRolPerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlItemRolPerMouseExited
        outColor(pnlItemRolPer, colorInactivo, 4);
    }//GEN-LAST:event_pnlItemRolPerMouseExited

    private void pnlItemRolPerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlItemRolPerMousePressed
        ItemBtnRolPer();

    }//GEN-LAST:event_pnlItemRolPerMousePressed

    private void lblLogoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLogoMousePressed
        inicio();
    }//GEN-LAST:event_lblLogoMousePressed

    private void btnCerrarSesionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarSesionMousePressed
        cerrarSesion(0);
    }//GEN-LAST:event_btnCerrarSesionMousePressed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        cerrarSesion(1);
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(vstMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vstMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vstMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vstMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                 try {
//                    UIManager.setLookAndFeel(new GraphiteLookAndFeel());
//                } catch (UnsupportedLookAndFeelException ex) {
//                    Logger.getLogger(vstMenu.class.getName()).log(Level.SEVERE, null, ex);
//                }
                new vstMenu().setVisible(true);
            }
        });

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private componentes.PanelRound btnCerrarSesion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblImgUsuario;
    private javax.swing.JLabel lblItemCliente;
    private javax.swing.JLabel lblItemImgCliente;
    private javax.swing.JLabel lblItemImgProveedores;
    private javax.swing.JLabel lblItemImgRolPer;
    private javax.swing.JLabel lblItemImgUsuario;
    private javax.swing.JLabel lblItemProveedores;
    private javax.swing.JLabel lblItemRolPer;
    private javax.swing.JLabel lblItemUsuario;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblUsuarioNombre;
    private javax.swing.JLabel lblUsuarioRol;
    public static javax.swing.JPanel pnlContenedor;
    private componentes.PanelRound pnlImgUsuario;
    private javax.swing.JPanel pnlItemClientes;
    private javax.swing.JPanel pnlItemProveedores;
    private javax.swing.JPanel pnlItemRolPer;
    private javax.swing.JPanel pnlItemUsuarios;
    private componentes.PanelRound pnlLogo;
    private componentes.pnlMenu pnlMenu4;
    private componentes.PanelRound pnlPerfil;
    private javax.swing.JPanel pnlPrincipal;
    // End of variables declaration//GEN-END:variables
}
