/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import com.jtattoo.plaf.bernstein.BernsteinLookAndFeel;
import com.jtattoo.plaf.graphite.GraphiteLookAndFeel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.Panel;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author hamme
 */
public class vstMenu extends javax.swing.JFrame {

    private ImageIcon imagen;
    private Icon icon;
    private Color colorActivo = new Color(16, 97, 255);
    private Color colorInactivo = new Color(16, 97, 173);
    private Color colorSelecciondo = new Color(250,172,45);
    private Color colorFondoItems = new Color(201, 229, 255);
    private Color colorFondoPerfil = new Color(201, 229, 255);
    private Color lblColor = new Color(255, 255, 255);
    private Color lblColorUser = new Color(255, 255, 255);
    private Font font = new Font("Serif", Font.BOLD, 15);
    private int numItem;

    public vstMenu() {
        initComponents();
        ConfiguracionesVisuales();

    }

    public void ConfiguracionesVisuales() {
        inicio();
        imagenMenu();
        confTipografia();
    }
    
    public void inicio(){
        numItem = 0;
        itemColor();
        vstInicio inicio = new vstInicio(); 
        panelContenedor(inicio);
    }
    

    public void imagenMenu() { //Funcion para agregar las Imagenes Ustilizadas en el menu
        String ruta = new String();
    
        ruta = "src/imagenes/borrar.jpeg";
        this.pintarImagenEscalada(this.lblFotoPerfil, ruta);
        
        ruta = "src/imagenes/fondos/hoja.jpg";
        this.pintarImagen(this.lblmenu, ruta);

        
        ruta = "src/imagenes/fondos/banner.png";
        this.pintarImagen(this.lblLogo, ruta);

    }

    public void imagenItems() { //Funcion para agregar las Imagenes de todos lo assets
        String ruta = new String();
        ruta = "src/imagenes/icons/user.png";
        this.pintarImagen(this.lblItemImgCliente, ruta);
        
        
    
    }

    private void pintarImagen(JLabel lbl, String ruta) {
        this.imagen = new ImageIcon(ruta);
        this.icon = new ImageIcon(this.imagen.getImage());
        lbl.setIcon(icon);
        this.repaint();
    }

    private void pintarImagenEscalada(JLabel lbl, String ruta) {
        this.imagen = new ImageIcon(ruta);
        this.icon = new ImageIcon(this.imagen.getImage().getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_DEFAULT));
        lbl.setIcon(icon);
        this.repaint();
    }

    public void itemColor() {
        pnlItemClientes.setBackground(colorInactivo);
        pnlItemUsuarios.setBackground(colorInactivo);
        pnlItemProductos.setBackground(colorInactivo);
        pnlItemLocaciones.setBackground(colorInactivo);
        pnlItemRolPer.setBackground(colorInactivo);
        pnlItemProveedores.setBackground(colorInactivo);
        
        pnlContenedorItems.setBackground(colorFondoItems);
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
        itemColor();
        panel.setBackground(color);
        
    }

    
    public void confTipografia() {

        lblItemCliente.setFont(font);
        lblItemCliente.setForeground(lblColor);
        lblItemUsuario.setFont(font);
        lblItemUsuario.setForeground(lblColor);
        lblItemProveedores.setFont(font);
        lblItemProveedores.setForeground(lblColor);
        lblItemProductos.setFont(font);
        lblItemProductos.setForeground(lblColor);
        lblItemRolPer.setFont(font);
        lblItemRolPer.setForeground(lblColor);
        lblItemLocaciones.setFont(font);
        lblItemLocaciones.setForeground(lblColor);

        lblUsuarioNombre.setFont(font);
        lblUsuarioNombre.setForeground(lblColorUser);
        lblUsuarioRol.setFont(font);
        lblUsuarioRol.setForeground(lblColorUser);

    }
    
    private void panelContenedor(JPanel panel){
        panel.setSize(700,520);
        panel.setLocation(0,0);
        
        pnlContenedor.removeAll();
        pnlContenedor.add(panel,BorderLayout.CENTER);
        pnlContenedor.revalidate();
        pnlContenedor.repaint();
        
    }
        
    public void ItemBtnUsuario(){
        seleccionItem(pnlItemUsuarios, colorSelecciondo, 1);
        vstUsuario panel = new vstUsuario(); 
        panelContenedor(panel);
    }
    
    public void ItemBtnCliente(){
        seleccionItem(pnlItemClientes, colorSelecciondo, 2);
        vstCliente panel = new vstCliente(); 
        panelContenedor(panel);
    }
    public void ItemBtnProveedor(){
        seleccionItem(pnlItemProveedores, colorSelecciondo, 3);
        vstProveedor panel = new vstProveedor(); 
        panelContenedor(panel);
    }
    public void ItemBtnLocacion(){
        seleccionItem(pnlItemLocaciones, colorSelecciondo, 4);
        vstLocaciones panel = new vstLocaciones(); 
        panelContenedor(panel);
    }
    public void ItemBtnRolPer(){
        seleccionItem(pnlItemRolPer, colorSelecciondo, 5);
        vstRolPer panel = new vstRolPer(); 
        panelContenedor(panel);
    }
    public void ItemBtnProducto(){
        seleccionItem(pnlItemProductos, colorSelecciondo, 6);
        vstProducto panel = new vstProducto(); 
        panelContenedor(panel);
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
        pnlMenu = new javax.swing.JPanel();
        pnlContenedorItems = new componentes.PanelRound();
        pnlItemUsuarios = new javax.swing.JPanel();
        lblItemImgUsuario = new javax.swing.JLabel();
        lblItemUsuario = new javax.swing.JLabel();
        pnlItemClientes = new javax.swing.JPanel();
        lblItemImgCliente = new javax.swing.JLabel();
        lblItemCliente = new javax.swing.JLabel();
        pnlItemProveedores = new javax.swing.JPanel();
        lblItemImgProveedores = new javax.swing.JLabel();
        lblItemProveedores = new javax.swing.JLabel();
        pnlItemLocaciones = new javax.swing.JPanel();
        lblItemImgLocaciones = new javax.swing.JLabel();
        lblItemLocaciones = new javax.swing.JLabel();
        pnlItemRolPer = new javax.swing.JPanel();
        lblItemImgRolPer = new javax.swing.JLabel();
        lblItemRolPer = new javax.swing.JLabel();
        pnlItemProductos = new javax.swing.JPanel();
        lblItemImgProductos = new javax.swing.JLabel();
        lblItemProductos = new javax.swing.JLabel();
        pnlLogo = new componentes.PanelRound();
        lblLogo = new javax.swing.JLabel();
        pnlPerfil = new componentes.PanelRound();
        lblFotoPerfil = new javax.swing.JLabel();
        btnCerrarSesion = new javax.swing.JButton();
        lblUsuarioNombre = new javax.swing.JLabel();
        lblUsuarioRol = new javax.swing.JLabel();
        lblmenu = new javax.swing.JLabel();
        pnlContenedor = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName("menu"); // NOI18N
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlPrincipal.setBackground(new java.awt.Color(244, 244, 244));
        pnlPrincipal.setForeground(new java.awt.Color(255, 255, 255));
        pnlPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlMenu.setBackground(new java.awt.Color(255, 255, 255));
        pnlMenu.setPreferredSize(new java.awt.Dimension(240, 540));
        pnlMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlContenedorItems.setBackground(new java.awt.Color(153, 153, 153));
        pnlContenedorItems.setRoundBottomLeft(30);
        pnlContenedorItems.setRoundBottomRight(30);
        pnlContenedorItems.setRoundTopLeft(30);
        pnlContenedorItems.setRoundTopRight(30);
        pnlContenedorItems.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        pnlItemUsuarios.add(lblItemImgUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 30));

        lblItemUsuario.setFont(new java.awt.Font("Perpetua", 1, 20)); // NOI18N
        lblItemUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblItemUsuario.setText("Usuarios");
        pnlItemUsuarios.add(lblItemUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 80, 20));

        pnlContenedorItems.add(pnlItemUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, -1, -1));

        pnlItemClientes.setBackground(new java.awt.Color(255, 204, 0));
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
        pnlItemClientes.add(lblItemImgCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 30, 30));

        lblItemCliente.setFont(new java.awt.Font("Poor Richard", 1, 20)); // NOI18N
        lblItemCliente.setForeground(new java.awt.Color(255, 255, 255));
        lblItemCliente.setText("Clientes");
        pnlItemClientes.add(lblItemCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 70, 20));

        pnlContenedorItems.add(pnlItemClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, -1, -1));

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
        pnlItemProveedores.add(lblItemImgProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 30, 30));

        lblItemProveedores.setFont(new java.awt.Font("Poor Richard", 1, 20)); // NOI18N
        lblItemProveedores.setForeground(new java.awt.Color(255, 255, 255));
        lblItemProveedores.setText("Proveedores");
        pnlItemProveedores.add(lblItemProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 110, 20));

        pnlContenedorItems.add(pnlItemProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 220, 30));

        pnlItemLocaciones.setBackground(new java.awt.Color(255, 204, 0));
        pnlItemLocaciones.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pnlItemLocaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlItemLocacionesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlItemLocacionesMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlItemLocacionesMousePressed(evt);
            }
        });
        pnlItemLocaciones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblItemImgLocaciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icons/user.png"))); // NOI18N
        pnlItemLocaciones.add(lblItemImgLocaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 30, 30));

        lblItemLocaciones.setFont(new java.awt.Font("Poor Richard", 1, 20)); // NOI18N
        lblItemLocaciones.setForeground(new java.awt.Color(255, 255, 255));
        lblItemLocaciones.setText("Locaciones");
        pnlItemLocaciones.add(lblItemLocaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 110, 20));

        pnlContenedorItems.add(pnlItemLocaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 220, 30));

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
        pnlItemRolPer.add(lblItemImgRolPer, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 30, 30));

        lblItemRolPer.setFont(new java.awt.Font("Poor Richard", 1, 20)); // NOI18N
        lblItemRolPer.setForeground(new java.awt.Color(255, 255, 255));
        lblItemRolPer.setText("Roles y Permisos");
        pnlItemRolPer.add(lblItemRolPer, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 150, 20));

        pnlContenedorItems.add(pnlItemRolPer, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 220, 30));

        pnlItemProductos.setBackground(new java.awt.Color(255, 204, 0));
        pnlItemProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pnlItemProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlItemProductosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlItemProductosMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlItemProductosMousePressed(evt);
            }
        });
        pnlItemProductos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblItemImgProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icons/user.png"))); // NOI18N
        pnlItemProductos.add(lblItemImgProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 30, 30));

        lblItemProductos.setFont(new java.awt.Font("Poor Richard", 1, 20)); // NOI18N
        lblItemProductos.setForeground(new java.awt.Color(255, 255, 255));
        lblItemProductos.setText("Productos");
        pnlItemProductos.add(lblItemProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 110, 20));

        pnlContenedorItems.add(pnlItemProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 220, 30));

        pnlMenu.add(pnlContenedorItems, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 220, 280));

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

        pnlMenu.add(pnlLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 100));

        pnlPerfil.setBackground(new java.awt.Color(102, 102, 102));
        pnlPerfil.setRoundBottomLeft(30);
        pnlPerfil.setRoundBottomRight(30);
        pnlPerfil.setRoundTopLeft(30);
        pnlPerfil.setRoundTopRight(30);
        pnlPerfil.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblFotoPerfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar.jpeg"))); // NOI18N
        lblFotoPerfil.setText("jLabel1");
        lblFotoPerfil.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlPerfil.add(lblFotoPerfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 70, 80));

        btnCerrarSesion.setText("Cerrar Sesión");
        pnlPerfil.add(btnCerrarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, -1, -1));

        lblUsuarioNombre.setText("Usuario");
        pnlPerfil.add(lblUsuarioNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 70, 20));

        lblUsuarioRol.setText("Rol");
        pnlPerfil.add(lblUsuarioRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 40, 20));

        pnlMenu.add(pnlPerfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 220, 100));
        pnlMenu.add(lblmenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 540));

        pnlPrincipal.add(pnlMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 550));
        pnlPrincipal.add(pnlContenedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 700, 520));

        getContentPane().add(pnlPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 540));

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

    private void pnlItemLocacionesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlItemLocacionesMouseEntered
        inColor(pnlItemLocaciones, colorActivo, 4);
    }//GEN-LAST:event_pnlItemLocacionesMouseEntered

    private void pnlItemLocacionesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlItemLocacionesMouseExited
        outColor(pnlItemLocaciones, colorInactivo, 4);
    }//GEN-LAST:event_pnlItemLocacionesMouseExited

    private void pnlItemLocacionesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlItemLocacionesMousePressed
        ItemBtnLocacion();
    }//GEN-LAST:event_pnlItemLocacionesMousePressed

    private void pnlItemRolPerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlItemRolPerMouseEntered
        inColor(pnlItemRolPer, colorActivo, 5);
    }//GEN-LAST:event_pnlItemRolPerMouseEntered

    private void pnlItemRolPerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlItemRolPerMouseExited
        outColor(pnlItemRolPer, colorInactivo, 5);
    }//GEN-LAST:event_pnlItemRolPerMouseExited

    private void pnlItemRolPerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlItemRolPerMousePressed
        ItemBtnRolPer();
                
    }//GEN-LAST:event_pnlItemRolPerMousePressed

    private void pnlItemProductosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlItemProductosMouseEntered
        inColor(pnlItemProductos, colorActivo, 6);
    }//GEN-LAST:event_pnlItemProductosMouseEntered

    private void pnlItemProductosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlItemProductosMouseExited
        outColor(pnlItemProductos, colorInactivo, 6);
    }//GEN-LAST:event_pnlItemProductosMouseExited

    private void pnlItemProductosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlItemProductosMousePressed
        ItemBtnProducto();
    }//GEN-LAST:event_pnlItemProductosMousePressed

    private void lblLogoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLogoMousePressed
        inicio();
    }//GEN-LAST:event_lblLogoMousePressed

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
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JLabel lblFotoPerfil;
    private javax.swing.JLabel lblItemCliente;
    private javax.swing.JLabel lblItemImgCliente;
    private javax.swing.JLabel lblItemImgLocaciones;
    private javax.swing.JLabel lblItemImgProductos;
    private javax.swing.JLabel lblItemImgProveedores;
    private javax.swing.JLabel lblItemImgRolPer;
    private javax.swing.JLabel lblItemImgUsuario;
    private javax.swing.JLabel lblItemLocaciones;
    private javax.swing.JLabel lblItemProductos;
    private javax.swing.JLabel lblItemProveedores;
    private javax.swing.JLabel lblItemRolPer;
    private javax.swing.JLabel lblItemUsuario;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblUsuarioNombre;
    private javax.swing.JLabel lblUsuarioRol;
    private javax.swing.JLabel lblmenu;
    public static javax.swing.JPanel pnlContenedor;
    private componentes.PanelRound pnlContenedorItems;
    private javax.swing.JPanel pnlItemClientes;
    private javax.swing.JPanel pnlItemLocaciones;
    private javax.swing.JPanel pnlItemProductos;
    private javax.swing.JPanel pnlItemProveedores;
    private javax.swing.JPanel pnlItemRolPer;
    private javax.swing.JPanel pnlItemUsuarios;
    private componentes.PanelRound pnlLogo;
    private javax.swing.JPanel pnlMenu;
    private componentes.PanelRound pnlPerfil;
    private javax.swing.JPanel pnlPrincipal;
    // End of variables declaration//GEN-END:variables
}
