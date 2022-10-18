/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.RolPer;

import controlador.CtrRol;
import controlador.CtrValidador;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.MdlRecursos;
import modelo.MdlRol;
import modelo.MdlRolRecurso;
import vista.vstMenu;

/**
 *
 * @author hamme
 */
public class vstAgregarEditarRol extends javax.swing.JPanel {

    ArrayList<MdlRolRecurso> listaRolRecurso = new ArrayList();
    MdlRol direccion = new MdlRol();

    /**
     * Creates new form pnlInicio
     */
    public vstAgregarEditarRol(int opcion) {
        initComponents();

        if (opcion == 1) {
            ModoAgregar();
        } else {
            ModoEditar();
        }
        inicio();
    }

    public void ModoAgregar() {
        lblTituloRol.setText("Agregar Rol");
        btnActualizar.setVisible(false);
    }

    public void ModoEditar() {
        lblTituloRol.setText("Modificar Rol");
        btnCrear.setVisible(false);
    }

    public void inicio() {
        chbClienteAgregar.setEnabled(false);
        chbInventarioAgregar.setEnabled(false);
        chbInventarioEliminar.setEnabled(false);
    }

    public void validarInformacionRol() {
        boolean validadorFunciones = false;

        if (validarRolNombre()) {
            if (txaFunciones.getText().isEmpty()) {
                int opcion = JOptionPane.showConfirmDialog(null, "Esta apunto de crear el rol sin \nasignarle funciones desea continuar", "  Advertencia", 2);
                if (opcion == 0) {
                    //            CrearRol();
                    System.err.println("Crear rol");
                }
            }
            if (!txaFunciones.getText().isEmpty()) {
                if (validarRolFunciones()) {
                    //            CrearRol();
                    System.err.println("Crear rol");
                }
            }
        }
    }

    public boolean validarRolNombre() {
        CtrValidador ctrv = new CtrValidador();
        boolean confirmar = false;
        int validador = 0;
        String informacionError = "";
        CtrRol ctrr = new CtrRol();
        if (ctrv.validarTamano(txtNombreRol.getText(), 2, 31)) {
            if (ctrv.validarCaracteres(txtNombreRol.getText())) {
                if (!ctrr.rolExiste(txtNombreRol.getText())) {
                    informacionError = informacionError + "Este rol ya a sido creado\n";
                    txtNombreRol.setForeground(Color.red);
                } else {
                    txtNombreRol.setForeground(Color.black);
                    confirmar = true;
                }
            } else {
                txtNombreRol.setForeground(Color.red);
                informacionError = informacionError + "EL nombre no debe contener \ncaracteres especiales ni números";
            }
        } else {
            txtNombreRol.setForeground(Color.red);
            informacionError = informacionError + "El nombre debe contener entre 3 y 30 carácter \n";
        }
        if (confirmar == false) {
            JOptionPane.showMessageDialog(null, informacionError, "Error Creando el Rol", 1);
        }
        return confirmar;
    }

    public boolean validarRolFunciones() {
        boolean confirmar = false;
        CtrValidador validar = new CtrValidador();
        if(validar.validarCaracteres(txaFunciones.getText()) && validar.validarTamano(txaFunciones.getText(),1,200)){
            confirmar = true;
        }
        return confirmar;
    }

    public void CrearRol() {
        MdlRol rol = new MdlRol();
        rol.setFunciones(txaFunciones.getText());
        rol.setNombre(txtNombreRol.getText().toLowerCase());

        ObtenerDatosRecursos();
        boolean subir = false;
        CtrRol Ctrr = new CtrRol();
        subir = Ctrr.crearRolPer(listaRolRecurso, rol);

        if (subir == true) {
            JOptionPane.showMessageDialog(null, "Rol creado Exitosamente");
            vstVerRol panel = new vstVerRol();
            vstMenu.panelContenedor(panel);
        }
    }

    public void ObtenerDatosRecursos() {
        ArrayList<MdlRecursos> listaRecursos = new ArrayList();
        CtrRol Ctrr = new CtrRol();
        listaRecursos = Ctrr.consultarRecursos();

        rolRecursoCliente(listaRecursos.get(0));
        rolRecursoInventario(listaRecursos.get(1));
        rolRecursoProveedores(listaRecursos.get(2));
        rolRecursoRoles(listaRecursos.get(3));
        rolRecursoUsuarios(listaRecursos.get(4));

    }

    public void rolRecursoCliente(MdlRecursos recurso) {
        MdlRolRecurso RolRecurso = new MdlRolRecurso();
        RolRecurso.setRecurso(recurso);
        RolRecurso.setCrear(0);
        RolRecurso.setEditar(0);
        RolRecurso.setLeer(0);
        RolRecurso.setMostrar(0);
        RolRecurso.setEliminar(0);
        if (chbClienteAgregar.isSelected() == true) {
            RolRecurso.setCrear(1);
        }
        if (chbClienteEditar.isSelected() == true) {
            RolRecurso.setEditar(1);
        }
        if (chbClienteEliminar.isSelected() == true) {
            RolRecurso.setEliminar(1);
        }
        if (chbClienteVer.isSelected() == true) {
            RolRecurso.setLeer(1);
        }
        if (chbClienteMostrar.isSelected() == true) {
            RolRecurso.setMostrar(1);
        }
        listaRolRecurso.add(RolRecurso);
    }

    public void rolRecursoInventario(MdlRecursos recurso) {
        MdlRolRecurso RolRecurso = new MdlRolRecurso();
        RolRecurso.setRecurso(recurso);
        RolRecurso.setCrear(0);
        RolRecurso.setEditar(0);
        RolRecurso.setLeer(0);
        RolRecurso.setMostrar(0);
        RolRecurso.setEliminar(0);
        if (chbInventarioAgregar.isSelected() == true) {
            RolRecurso.setCrear(1);
        }
        if (chbInventarioEditar.isSelected() == true) {
            RolRecurso.setEditar(1);
        }
        if (chbInventarioEliminar.isSelected() == true) {
            RolRecurso.setEliminar(1);
        }
        if (chbInventarioVer.isSelected() == true) {
            RolRecurso.setLeer(1);
        }
        if (chbInventarioMostrar.isSelected() == true) {
            RolRecurso.setMostrar(1);
        }
        listaRolRecurso.add(RolRecurso);
    }

    public void rolRecursoRoles(MdlRecursos recurso) {
        MdlRolRecurso RolRecurso = new MdlRolRecurso();
        RolRecurso.setRecurso(recurso);
        RolRecurso.setCrear(0);
        RolRecurso.setEditar(0);
        RolRecurso.setLeer(0);
        RolRecurso.setMostrar(0);
        RolRecurso.setEliminar(0);
        if (chbRolesAgregar.isSelected() == true) {
            RolRecurso.setCrear(1);
        }
        if (chbRolesEditar.isSelected() == true) {
            RolRecurso.setEditar(1);
        }
        if (chbRolesEliminar.isSelected() == true) {
            RolRecurso.setEliminar(1);
        }
        if (chbRolesVer.isSelected() == true) {
            RolRecurso.setLeer(1);
        }
        if (chbRolesMostrar.isSelected() == true) {
            RolRecurso.setMostrar(1);
        }
        listaRolRecurso.add(RolRecurso);
    }

    public void rolRecursoProveedores(MdlRecursos recurso) {
        MdlRolRecurso RolRecurso = new MdlRolRecurso();
        RolRecurso.setRecurso(recurso);
        RolRecurso.setCrear(0);
        RolRecurso.setEditar(0);
        RolRecurso.setLeer(0);
        RolRecurso.setMostrar(0);
        RolRecurso.setEliminar(0);
        if (chbProveedoresAgregar.isSelected() == true) {
            RolRecurso.setCrear(1);
        }
        if (chbProveedoresEditar.isSelected() == true) {
            RolRecurso.setEditar(1);
        }
        if (chbProveedoresEliminar.isSelected() == true) {
            RolRecurso.setEliminar(1);
        }
        if (chbProveedoresVer.isSelected() == true) {
            RolRecurso.setLeer(1);
        }
        if (chbProveedoresMostrar.isSelected() == true) {
            RolRecurso.setMostrar(1);
        }
        listaRolRecurso.add(RolRecurso);
    }

    public void rolRecursoUsuarios(MdlRecursos recurso) {
        MdlRolRecurso RolRecurso = new MdlRolRecurso();
        RolRecurso.setRecurso(recurso);
        RolRecurso.setCrear(0);
        RolRecurso.setEditar(0);
        RolRecurso.setLeer(0);
        RolRecurso.setMostrar(0);
        RolRecurso.setEliminar(0);
        if (chbUsuariosAgregar.isSelected() == true) {
            RolRecurso.setCrear(1);
        }
        if (chbUsuariosEditar.isSelected() == true) {
            RolRecurso.setEditar(1);
        }
        if (chbUsuariosEliminar.isSelected() == true) {
            RolRecurso.setEliminar(1);
        }
        if (chbUsuariosVer.isSelected() == true) {
            RolRecurso.setLeer(1);
        }
        if (chbUsuariosMostrar.isSelected() == true) {
            RolRecurso.setMostrar(1);
        }
        listaRolRecurso.add(RolRecurso);
    }

    public void confirmacionCancelacion() {
        for (int posicion = 0; posicion < listaRolRecurso.size(); posicion++) {
            System.out.println(listaRolRecurso.get(posicion));
        }
        int result = JOptionPane.showConfirmDialog(panelRound1, "Seguro que desea salir", "Cancelar", 2);
        if (result == 0) {
            vstRolPer panel = new vstRolPer();
            vstMenu.panelContenedor(panel);
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
        lblTituloRol = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblPermisos = new javax.swing.JLabel();
        lblMostrar = new javax.swing.JLabel();
        lblAgregar = new javax.swing.JLabel();
        lblEditar = new javax.swing.JLabel();
        lblEliminar = new javax.swing.JLabel();
        lblVer = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        pnlClientes = new componentes.PanelRound();
        chbClienteVer = new javax.swing.JCheckBox();
        chbClienteAgregar = new javax.swing.JCheckBox();
        chbClienteEditar = new javax.swing.JCheckBox();
        chbClienteEliminar = new javax.swing.JCheckBox();
        chbClienteMostrar = new javax.swing.JCheckBox();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        lblCliente = new javax.swing.JLabel();
        pnlRoles = new componentes.PanelRound();
        chbRolesVer = new javax.swing.JCheckBox();
        chbRolesAgregar = new javax.swing.JCheckBox();
        chbRolesEditar = new javax.swing.JCheckBox();
        chbRolesEliminar = new javax.swing.JCheckBox();
        chbRolesMostrar = new javax.swing.JCheckBox();
        jSeparator4 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        pnlProveedores = new componentes.PanelRound();
        chbProveedoresVer = new javax.swing.JCheckBox();
        chbProveedoresAgregar = new javax.swing.JCheckBox();
        chbProveedoresEditar = new javax.swing.JCheckBox();
        chbProveedoresEliminar = new javax.swing.JCheckBox();
        chbProveedoresMostrar = new javax.swing.JCheckBox();
        jSeparator5 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        pnlInventario = new componentes.PanelRound();
        chbInventarioVer = new javax.swing.JCheckBox();
        chbInventarioAgregar = new javax.swing.JCheckBox();
        chbInventarioEditar = new javax.swing.JCheckBox();
        chbInventarioEliminar = new javax.swing.JCheckBox();
        chbInventarioMostrar = new javax.swing.JCheckBox();
        jSeparator6 = new javax.swing.JSeparator();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        pnlUsuarios = new componentes.PanelRound();
        chbUsuariosVer = new javax.swing.JCheckBox();
        chbUsuariosAgregar = new javax.swing.JCheckBox();
        chbUsuariosEditar = new javax.swing.JCheckBox();
        chbUsuariosEliminar = new javax.swing.JCheckBox();
        chbUsuariosMostrar = new javax.swing.JCheckBox();
        jSeparator8 = new javax.swing.JSeparator();
        jPanel8 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        pnlNombreRol = new componentes.PanelRound();
        jSeparator2 = new javax.swing.JSeparator();
        txtNombreRol = new javax.swing.JTextField();
        lblNombreRol = new javax.swing.JLabel();
        pnlOpciones = new javax.swing.JPanel();
        btnCrear = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        lblNombreRol1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaFunciones = new javax.swing.JTextArea();

        setBackground(new java.awt.Color(244, 244, 244));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRound1.setBackground(new java.awt.Color(255, 255, 255));
        panelRound1.setRoundBottomLeft(30);
        panelRound1.setRoundBottomRight(30);
        panelRound1.setRoundTopLeft(30);
        panelRound1.setRoundTopRight(30);
        panelRound1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTituloRol.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblTituloRol.setText("Agregar o Modificar Rol");
        panelRound1.add(lblTituloRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 270, 91));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lblPermisos.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 12)); // NOI18N
        lblPermisos.setText("Permisos");

        lblMostrar.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 12)); // NOI18N
        lblMostrar.setText("Mostrar");

        lblAgregar.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 12)); // NOI18N
        lblAgregar.setText("Agregar");

        lblEditar.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 12)); // NOI18N
        lblEditar.setText("Editar");

        lblEliminar.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 12)); // NOI18N
        lblEliminar.setText("Eliminar");

        lblVer.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 12)); // NOI18N
        lblVer.setText("Ver");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(lblPermisos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 149, Short.MAX_VALUE)
                .addComponent(lblVer)
                .addGap(41, 41, 41)
                .addComponent(lblAgregar)
                .addGap(27, 27, 27)
                .addComponent(lblMostrar)
                .addGap(35, 35, 35)
                .addComponent(lblEditar)
                .addGap(33, 33, 33)
                .addComponent(lblEliminar)
                .addGap(19, 19, 19))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 14, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPermisos)
                    .addComponent(lblAgregar)
                    .addComponent(lblEditar)
                    .addComponent(lblEliminar)
                    .addComponent(lblVer)
                    .addComponent(lblMostrar)))
        );

        panelRound1.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, 640, 30));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        pnlClientes.setBackground(new java.awt.Color(255, 255, 255));
        pnlClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlClientesMousePressed(evt);
            }
        });

        chbClienteVer.setSelected(true);

        chbClienteAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbClienteAgregarActionPerformed(evt);
            }
        });

        chbClienteEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbClienteEditarActionPerformed(evt);
            }
        });

        chbClienteEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbClienteEliminarActionPerformed(evt);
            }
        });

        chbClienteMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbClienteMostrarActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        lblCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblCliente.setText("Clientes");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(lblCliente)
                .addGap(28, 28, 28))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 5, Short.MAX_VALUE)
                .addComponent(lblCliente))
        );

        javax.swing.GroupLayout pnlClientesLayout = new javax.swing.GroupLayout(pnlClientes);
        pnlClientes.setLayout(pnlClientesLayout);
        pnlClientesLayout.setHorizontalGroup(
            pnlClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlClientesLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(128, 128, 128)
                        .addComponent(chbClienteVer)
                        .addGap(65, 65, 65)
                        .addComponent(chbClienteAgregar)
                        .addGap(65, 65, 65)
                        .addComponent(chbClienteMostrar)
                        .addGap(65, 65, 65)
                        .addComponent(chbClienteEditar)
                        .addGap(65, 65, 65)
                        .addComponent(chbClienteEliminar)
                        .addGap(17, 17, 17))
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );
        pnlClientesLayout.setVerticalGroup(
            pnlClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(chbClienteMostrar)
                    .addComponent(chbClienteEliminar)
                    .addComponent(chbClienteEditar)
                    .addComponent(chbClienteAgregar)
                    .addComponent(chbClienteVer)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlRoles.setBackground(new java.awt.Color(255, 255, 255));
        pnlRoles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlRolesMousePressed(evt);
            }
        });

        chbRolesVer.setSelected(true);
        chbRolesVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbRolesVerActionPerformed(evt);
            }
        });

        chbRolesAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbRolesAgregarActionPerformed(evt);
            }
        });

        chbRolesEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbRolesEditarActionPerformed(evt);
            }
        });

        chbRolesEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbRolesEliminarActionPerformed(evt);
            }
        });

        chbRolesMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbRolesMostrarActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Roles");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel7)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 5, Short.MAX_VALUE)
                .addComponent(jLabel7))
        );

        javax.swing.GroupLayout pnlRolesLayout = new javax.swing.GroupLayout(pnlRoles);
        pnlRoles.setLayout(pnlRolesLayout);
        pnlRolesLayout.setHorizontalGroup(
            pnlRolesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRolesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlRolesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlRolesLayout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(128, 128, 128)
                        .addComponent(chbRolesVer)
                        .addGap(65, 65, 65)
                        .addComponent(chbRolesAgregar)
                        .addGap(65, 65, 65)
                        .addComponent(chbRolesMostrar)
                        .addGap(65, 65, 65)
                        .addComponent(chbRolesEditar)
                        .addGap(65, 65, 65)
                        .addComponent(chbRolesEliminar)
                        .addGap(17, 17, 17))
                    .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );
        pnlRolesLayout.setVerticalGroup(
            pnlRolesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRolesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlRolesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(chbRolesMostrar)
                    .addComponent(chbRolesEliminar)
                    .addComponent(chbRolesEditar)
                    .addComponent(chbRolesAgregar)
                    .addComponent(chbRolesVer)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlProveedores.setBackground(new java.awt.Color(255, 255, 255));
        pnlProveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlProveedoresMousePressed(evt);
            }
        });

        chbProveedoresVer.setSelected(true);
        chbProveedoresVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbProveedoresVerActionPerformed(evt);
            }
        });

        chbProveedoresAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbProveedoresAgregarActionPerformed(evt);
            }
        });

        chbProveedoresEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbProveedoresEditarActionPerformed(evt);
            }
        });

        chbProveedoresEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbProveedoresEliminarActionPerformed(evt);
            }
        });

        chbProveedoresMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbProveedoresMostrarActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Proveedores");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 5, Short.MAX_VALUE)
                .addComponent(jLabel8))
        );

        javax.swing.GroupLayout pnlProveedoresLayout = new javax.swing.GroupLayout(pnlProveedores);
        pnlProveedores.setLayout(pnlProveedoresLayout);
        pnlProveedoresLayout.setHorizontalGroup(
            pnlProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProveedoresLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlProveedoresLayout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(128, 128, 128)
                        .addComponent(chbProveedoresVer)
                        .addGap(65, 65, 65)
                        .addComponent(chbProveedoresAgregar)
                        .addGap(65, 65, 65)
                        .addComponent(chbProveedoresMostrar)
                        .addGap(65, 65, 65)
                        .addComponent(chbProveedoresEditar)
                        .addGap(65, 65, 65)
                        .addComponent(chbProveedoresEliminar)
                        .addGap(17, 17, 17))
                    .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );
        pnlProveedoresLayout.setVerticalGroup(
            pnlProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProveedoresLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(chbProveedoresMostrar)
                    .addComponent(chbProveedoresEliminar)
                    .addComponent(chbProveedoresEditar)
                    .addComponent(chbProveedoresAgregar)
                    .addComponent(chbProveedoresVer)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlInventario.setBackground(new java.awt.Color(255, 255, 255));
        pnlInventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlInventarioMousePressed(evt);
            }
        });

        chbInventarioVer.setSelected(true);
        chbInventarioVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbInventarioVerActionPerformed(evt);
            }
        });

        chbInventarioAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbInventarioAgregarActionPerformed(evt);
            }
        });

        chbInventarioEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbInventarioEditarActionPerformed(evt);
            }
        });

        chbInventarioEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbInventarioEliminarActionPerformed(evt);
            }
        });

        chbInventarioMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbInventarioMostrarActionPerformed(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Inventario");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 5, Short.MAX_VALUE)
                .addComponent(jLabel9))
        );

        javax.swing.GroupLayout pnlInventarioLayout = new javax.swing.GroupLayout(pnlInventario);
        pnlInventario.setLayout(pnlInventarioLayout);
        pnlInventarioLayout.setHorizontalGroup(
            pnlInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInventarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInventarioLayout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(128, 128, 128)
                        .addComponent(chbInventarioVer)
                        .addGap(65, 65, 65)
                        .addComponent(chbInventarioAgregar)
                        .addGap(65, 65, 65)
                        .addComponent(chbInventarioMostrar)
                        .addGap(65, 65, 65)
                        .addComponent(chbInventarioEditar)
                        .addGap(65, 65, 65)
                        .addComponent(chbInventarioEliminar)
                        .addGap(17, 17, 17))
                    .addComponent(jSeparator6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );
        pnlInventarioLayout.setVerticalGroup(
            pnlInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInventarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(chbInventarioMostrar)
                    .addComponent(chbInventarioEliminar)
                    .addComponent(chbInventarioEditar)
                    .addComponent(chbInventarioAgregar)
                    .addComponent(chbInventarioVer)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlUsuarios.setBackground(new java.awt.Color(255, 255, 255));
        pnlUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlUsuariosMousePressed(evt);
            }
        });

        chbUsuariosVer.setSelected(true);
        chbUsuariosVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbUsuariosVerActionPerformed(evt);
            }
        });

        chbUsuariosAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbUsuariosAgregarActionPerformed(evt);
            }
        });

        chbUsuariosEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbUsuariosEditarActionPerformed(evt);
            }
        });

        chbUsuariosEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbUsuariosEliminarActionPerformed(evt);
            }
        });

        chbUsuariosMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbUsuariosMostrarActionPerformed(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Usuarios");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 5, Short.MAX_VALUE)
                .addComponent(jLabel11))
        );

        javax.swing.GroupLayout pnlUsuariosLayout = new javax.swing.GroupLayout(pnlUsuarios);
        pnlUsuarios.setLayout(pnlUsuariosLayout);
        pnlUsuariosLayout.setHorizontalGroup(
            pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUsuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlUsuariosLayout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(128, 128, 128)
                        .addComponent(chbUsuariosVer)
                        .addGap(65, 65, 65)
                        .addComponent(chbUsuariosAgregar)
                        .addGap(65, 65, 65)
                        .addComponent(chbUsuariosMostrar)
                        .addGap(65, 65, 65)
                        .addComponent(chbUsuariosEditar)
                        .addGap(65, 65, 65)
                        .addComponent(chbUsuariosEliminar)
                        .addGap(17, 17, 17))
                    .addComponent(jSeparator8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );
        pnlUsuariosLayout.setVerticalGroup(
            pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUsuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(chbUsuariosMostrar)
                    .addComponent(chbUsuariosEliminar)
                    .addComponent(chbUsuariosEditar)
                    .addComponent(chbUsuariosAgregar)
                    .addComponent(chbUsuariosVer)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnlRoles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnlProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnlUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnlInventario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 605, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(pnlClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlInventario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlRoles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        panelRound1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 670, 350));

        pnlNombreRol.setBackground(new java.awt.Color(255, 255, 255));
        pnlNombreRol.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        pnlNombreRol.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 160, 10));

        txtNombreRol.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 12)); // NOI18N
        txtNombreRol.setBorder(null);
        txtNombreRol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreRolActionPerformed(evt);
            }
        });
        pnlNombreRol.add(txtNombreRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 160, 20));

        lblNombreRol.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 12)); // NOI18N
        lblNombreRol.setText("Nombre del rol");
        pnlNombreRol.add(lblNombreRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 20));

        panelRound1.add(pnlNombreRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 310, 40));

        pnlOpciones.setBackground(new java.awt.Color(255, 255, 255));

        btnCrear.setBackground(new java.awt.Color(0, 255, 153));
        btnCrear.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCrear.setText("Crear");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(255, 51, 51));
        btnCancelar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnActualizar.setBackground(new java.awt.Color(255, 255, 0));
        btnActualizar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlOpcionesLayout = new javax.swing.GroupLayout(pnlOpciones);
        pnlOpciones.setLayout(pnlOpcionesLayout);
        pnlOpcionesLayout.setHorizontalGroup(
            pnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOpcionesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancelar)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(btnActualizar)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(btnCrear)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlOpcionesLayout.setVerticalGroup(
            pnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlOpcionesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnActualizar)
                    .addComponent(btnCancelar)
                    .addComponent(btnCrear))
                .addContainerGap())
        );

        panelRound1.add(pnlOpciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 560, 320, 40));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        lblNombreRol1.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 12)); // NOI18N
        lblNombreRol1.setText("Funciones");

        txaFunciones.setColumns(20);
        txaFunciones.setLineWrap(true);
        txaFunciones.setRows(5);
        txaFunciones.setWrapStyleWord(true);
        txaFunciones.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txaFunciones.setDragEnabled(true);
        txaFunciones.setSelectionColor(new java.awt.Color(204, 204, 204));
        jScrollPane1.setViewportView(txaFunciones);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNombreRol1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblNombreRol1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 14, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelRound1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 60, 350, 110));

        add(panelRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 760, 620));
    }// </editor-fold>//GEN-END:initComponents

    private void chbClienteAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbClienteAgregarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chbClienteAgregarActionPerformed

    private void chbClienteEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbClienteEditarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chbClienteEditarActionPerformed

    private void chbClienteEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbClienteEliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chbClienteEliminarActionPerformed

    private void chbClienteMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbClienteMostrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chbClienteMostrarActionPerformed

    private void pnlClientesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlClientesMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlClientesMousePressed

    private void chbRolesVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbRolesVerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chbRolesVerActionPerformed

    private void chbRolesAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbRolesAgregarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chbRolesAgregarActionPerformed

    private void chbRolesEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbRolesEditarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chbRolesEditarActionPerformed

    private void chbRolesEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbRolesEliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chbRolesEliminarActionPerformed

    private void chbRolesMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbRolesMostrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chbRolesMostrarActionPerformed

    private void pnlRolesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlRolesMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlRolesMousePressed

    private void chbProveedoresVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbProveedoresVerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chbProveedoresVerActionPerformed

    private void chbProveedoresAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbProveedoresAgregarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chbProveedoresAgregarActionPerformed

    private void chbProveedoresEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbProveedoresEditarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chbProveedoresEditarActionPerformed

    private void chbProveedoresEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbProveedoresEliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chbProveedoresEliminarActionPerformed

    private void chbProveedoresMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbProveedoresMostrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chbProveedoresMostrarActionPerformed

    private void pnlProveedoresMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlProveedoresMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlProveedoresMousePressed

    private void chbInventarioVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbInventarioVerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chbInventarioVerActionPerformed

    private void chbInventarioAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbInventarioAgregarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chbInventarioAgregarActionPerformed

    private void chbInventarioEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbInventarioEditarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chbInventarioEditarActionPerformed

    private void chbInventarioEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbInventarioEliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chbInventarioEliminarActionPerformed

    private void chbInventarioMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbInventarioMostrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chbInventarioMostrarActionPerformed

    private void pnlInventarioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlInventarioMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlInventarioMousePressed

    private void chbUsuariosVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbUsuariosVerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chbUsuariosVerActionPerformed

    private void chbUsuariosAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbUsuariosAgregarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chbUsuariosAgregarActionPerformed

    private void chbUsuariosEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbUsuariosEditarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chbUsuariosEditarActionPerformed

    private void chbUsuariosEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbUsuariosEliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chbUsuariosEliminarActionPerformed

    private void chbUsuariosMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbUsuariosMostrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chbUsuariosMostrarActionPerformed

    private void pnlUsuariosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlUsuariosMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlUsuariosMousePressed

    private void txtNombreRolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreRolActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreRolActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        confirmacionCancelacion();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        validarInformacionRol();
    }//GEN-LAST:event_btnCrearActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCrear;
    private javax.swing.JCheckBox chbClienteAgregar;
    private javax.swing.JCheckBox chbClienteEditar;
    private javax.swing.JCheckBox chbClienteEliminar;
    private javax.swing.JCheckBox chbClienteMostrar;
    private javax.swing.JCheckBox chbClienteVer;
    private javax.swing.JCheckBox chbInventarioAgregar;
    private javax.swing.JCheckBox chbInventarioEditar;
    private javax.swing.JCheckBox chbInventarioEliminar;
    private javax.swing.JCheckBox chbInventarioMostrar;
    private javax.swing.JCheckBox chbInventarioVer;
    private javax.swing.JCheckBox chbProveedoresAgregar;
    private javax.swing.JCheckBox chbProveedoresEditar;
    private javax.swing.JCheckBox chbProveedoresEliminar;
    private javax.swing.JCheckBox chbProveedoresMostrar;
    private javax.swing.JCheckBox chbProveedoresVer;
    private javax.swing.JCheckBox chbRolesAgregar;
    private javax.swing.JCheckBox chbRolesEditar;
    private javax.swing.JCheckBox chbRolesEliminar;
    private javax.swing.JCheckBox chbRolesMostrar;
    private javax.swing.JCheckBox chbRolesVer;
    private javax.swing.JCheckBox chbUsuariosAgregar;
    private javax.swing.JCheckBox chbUsuariosEditar;
    private javax.swing.JCheckBox chbUsuariosEliminar;
    private javax.swing.JCheckBox chbUsuariosMostrar;
    private javax.swing.JCheckBox chbUsuariosVer;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JLabel lblAgregar;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblEditar;
    private javax.swing.JLabel lblEliminar;
    private javax.swing.JLabel lblInformacion;
    private javax.swing.JLabel lblInformacion1;
    private javax.swing.JLabel lblMostrar;
    private javax.swing.JLabel lblNombreRol;
    private javax.swing.JLabel lblNombreRol1;
    private javax.swing.JLabel lblPermisos;
    private javax.swing.JLabel lblTituloRol;
    private javax.swing.JLabel lblVer;
    private componentes.PanelRound panelRound1;
    private componentes.PanelRound pnlClientes;
    private componentes.PanelRound pnlInformacion;
    private componentes.PanelRound pnlInformacion1;
    private componentes.PanelRound pnlInventario;
    private componentes.PanelRound pnlNombreRol;
    private javax.swing.JPanel pnlOpciones;
    private componentes.PanelRound pnlProveedores;
    private componentes.PanelRound pnlRoles;
    private componentes.PanelRound pnlUsuarios;
    private javax.swing.JTextArea txaFunciones;
    private javax.swing.JTextField txtNombreRol;
    // End of variables declaration//GEN-END:variables
}
