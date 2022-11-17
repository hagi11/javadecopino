/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.Cliente;

import controlador.CtrAuxiliares;
import controlador.CtrCliente;
import controlador.CtrLocaciones;
import java.awt.Color;
import java.awt.Cursor;
import static java.awt.Cursor.HAND_CURSOR;
import java.util.ArrayList;
import javafx.scene.control.ComboBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import modelo.MdlCiudad;
import modelo.MdlCliente;
import modelo.MdlDepartamento;
import vista.Usuario.*;
import vista.*;

/**
 *
 * @author hamme
 */
public class vstEditarCliente extends javax.swing.JPanel {

    private Color fondoInformacion = new Color(58, 185, 7);
    private Color fondoModificar = new Color(58, 185, 7);
    private Color fondoEliminar = new Color(255, 255, 255);

    private Color fondoHoldInformacion = new Color(58, 185, 7);
    private Color fondoHoldModificar = new Color(255, 255, 255);
    private Color fondoHoldEliminar = new Color(58, 185, 7);

    /**
     * Creates new form pnlInicio
     */
    MdlCliente clienteEditar = new MdlCliente();

    public vstEditarCliente(MdlCliente cliente) {
        clienteEditar = cliente;
        initComponents();
        inicio();
    }

    public void inicio() {
        MostrarClientes();
        MouseOutBoton(pnlModificar, fondoModificar);
        llenarComboTipoIdent();
        llenarComboDepa();
        llenarComboCiudad();
    }

    ArrayList<MdlCliente> ListaClientes = new ArrayList(); //Array de la lista de clientes
    
    public void MostrarClientes() {
        lblTitulo.setText("Editando a: " + clienteEditar.getNombre() + "");
        CtrCliente cliente = new CtrCliente();
        ListaClientes = cliente.MostrarInformacionCliente(clienteEditar.getId());
        for (int posicion = 0; posicion < ListaClientes.size(); posicion++) {
//            txtTipoId.setText(ListaClientes.get(posicion).getTidenrificacion());
            txtIdentificacion.setText(Integer.toString(ListaClientes.get(posicion).getIdentificacion()));
            txtNombre.setText(ListaClientes.get(posicion).getNombre());
            txtApellido.setText(ListaClientes.get(posicion).getApellido());
            txtCorreo.setText(ListaClientes.get(posicion).getCorreo());
            txtTelefono.setText(ListaClientes.get(posicion).getTelefono());
            txtDireccion.setText(ListaClientes.get(posicion).getDireccion());
            
        }

    }
    
    public void llenarComboTipoIdent() {
        CtrAuxiliares ctra = new CtrAuxiliares();
        ArrayList<String> listaTipoIdent = ctra.consultarTipoIdent();
        cmbTipoId.removeAllItems();
        for (int posicion = 0; posicion < listaTipoIdent.size(); posicion++) {
            cmbTipoId.addItem(listaTipoIdent.get(posicion));
        }
    }
    
    
    
     public void Modificar() {
        CtrCliente CTRcliente = new CtrCliente();
        MdlCliente cliente = new MdlCliente();
            cliente.setId(clienteEditar.getId());
            cliente.setIdPersona(clienteEditar.getIdPersona());
            cliente.setTidenrificacion(obtenerTipoIdent());
            cliente.setCiudad(obtenerCiudad());
            cliente.setIdentificacion(clienteEditar.getIdentificacion());
            cliente.setTelefono(txtTelefono.getText());
            cliente.setDireccion(txtDireccion.getText());
            cliente.setCorreo(txtCorreo.getText());
            cliente.setNombre(txtNombre.getText());
            cliente.setContrasenia(pswContrasenia.getText());
            cliente.setApellido(txtApellido.getText());
            CTRcliente.actualizar(cliente);
    }

    int ciudad = 0;
    ArrayList<MdlDepartamento> departamentos = new ArrayList();
    public void llenarComboDepa() {
        CtrLocaciones ctrl = new CtrLocaciones();
        departamentos = ctrl.consultarDepartamentos(82);
        cmbDepartamento.removeAllItems();
        for (int posicion = 0; posicion < departamentos.size(); posicion++) {
            cmbDepartamento.addItem(departamentos.get(posicion).getDepartamento());
        }
        cmbDepartamento.setSelectedItem("VALLE DEL CAUCA");
        habilidarLLenadoCiudad = 1;
    }
    int habilidarLLenadoCiudad = 0;
    public void llenarComboCiudad() {
        if (habilidarLLenadoCiudad == 1) {
            String depaSeleccionado = (String) cmbDepartamento.getSelectedItem();
            CtrLocaciones ctrl = new CtrLocaciones();
            int idDepartamento = 0;
            for (int posicion = 0; posicion < departamentos.size(); posicion++) {
                if (depaSeleccionado.equals(departamentos.get(posicion).getDepartamento())) {
                    idDepartamento = departamentos.get(posicion).getId();
                }
            }

            ArrayList<MdlCiudad> listaCiudad = ctrl.consultarCiudades(idDepartamento);
            cmbCiudad.removeAllItems();
            for (int posicion = 0; posicion < listaCiudad.size(); posicion++) {
                cmbCiudad.addItem(listaCiudad.get(posicion).getCiudad());
            }
            if (cmbDepartamento.getSelectedItem().toString().equals("VALLE DEL CAUCA")) {
                cmbCiudad.setSelectedItem("Calí");
            }

        }
    }
    
    
    public MdlCiudad obtenerCiudad() {
        MdlCiudad ciudad = new MdlCiudad();
        String ciudadNombre = cmbCiudad.getSelectedItem().toString();
        CtrLocaciones ctrl = new CtrLocaciones();
        ciudad = ctrl.mostrarCiudadNombre(ciudadNombre);
        return ciudad;
    }

    public String obtenerTipoIdent() {
        CtrAuxiliares ctra = new CtrAuxiliares();
        String tipoIdent = ctra.mostrarTipoIdentNombre(cmbTipoId.getSelectedItem().toString());
        return tipoIdent;
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
        panelRound2 = new componentes.PanelRound();
        lblTitulo = new javax.swing.JLabel();
        pnlTipoId = new componentes.PanelRound();
        lblTipoId = new javax.swing.JLabel();
        panelRound5 = new componentes.PanelRound();
        jSeparator4 = new javax.swing.JSeparator();
        jTextField4 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        cmbTipoId = new javax.swing.JComboBox();
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
        pnlContrasenia = new componentes.PanelRound();
        jSeparator8 = new javax.swing.JSeparator();
        lblContrasenia = new javax.swing.JLabel();
        pswContrasenia = new javax.swing.JPasswordField();
        pnlCiudad = new componentes.PanelRound();
        lblCiudad = new javax.swing.JLabel();
        panelRound11 = new componentes.PanelRound();
        jSeparator9 = new javax.swing.JSeparator();
        jTextField9 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cmbDepartamento = new javax.swing.JComboBox();
        pnlIdentificacion = new componentes.PanelRound();
        jSeparator10 = new javax.swing.JSeparator();
        txtIdentificacion = new javax.swing.JTextField();
        lblIdentificacion = new javax.swing.JLabel();
        pnlModificar = new componentes.PanelRound();
        lblModificar = new javax.swing.JLabel();
        pnlDirección = new componentes.PanelRound();
        jSeparator11 = new javax.swing.JSeparator();
        txtDireccion = new javax.swing.JTextField();
        lblDireccion = new javax.swing.JLabel();
        pnlCiudad1 = new componentes.PanelRound();
        lblCiudad1 = new javax.swing.JLabel();
        panelRound12 = new componentes.PanelRound();
        jSeparator12 = new javax.swing.JSeparator();
        jTextField10 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cmbCiudad = new javax.swing.JComboBox();

        setBackground(new java.awt.Color(244, 244, 244));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRound1.setBackground(new java.awt.Color(255, 255, 255));
        panelRound1.setRoundBottomLeft(30);
        panelRound1.setRoundBottomRight(30);
        panelRound1.setRoundTopLeft(30);
        panelRound1.setRoundTopRight(30);
        panelRound1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRound2.setBackground(new java.awt.Color(255, 255, 255));
        panelRound2.setRoundBottomLeft(30);
        panelRound2.setRoundBottomRight(30);
        panelRound2.setRoundTopLeft(30);
        panelRound2.setRoundTopRight(30);
        panelRound2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 24)); // NOI18N
        lblTitulo.setText("Editar Cliente");
        panelRound2.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 600, 60));

        pnlTipoId.setBackground(new java.awt.Color(255, 255, 255));
        pnlTipoId.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        cmbTipoId.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione su ciudad" }));
        cmbTipoId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoIdActionPerformed(evt);
            }
        });
        pnlTipoId.add(cmbTipoId, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 460, -1));

        panelRound2.add(pnlTipoId, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 700, 40));

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

        panelRound2.add(pnlNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 700, 40));

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

        panelRound2.add(pnlApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 690, 40));

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

        panelRound2.add(pnlCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 690, 40));

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

        panelRound2.add(pnlTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 690, 40));

        pnlContrasenia.setBackground(new java.awt.Color(255, 255, 255));
        pnlContrasenia.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        pnlContrasenia.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 460, 10));

        lblContrasenia.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 12)); // NOI18N
        lblContrasenia.setText("Contraseña");
        pnlContrasenia.add(lblContrasenia, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 20));

        pswContrasenia.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        pswContrasenia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pswContraseniaActionPerformed(evt);
            }
        });
        pnlContrasenia.add(pswContrasenia, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 6, 460, 30));

        panelRound2.add(pnlContrasenia, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 700, 40));

        pnlCiudad.setBackground(new java.awt.Color(255, 255, 255));
        pnlCiudad.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCiudad.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 12)); // NOI18N
        lblCiudad.setText("Departamento");
        pnlCiudad.add(lblCiudad, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 110, 20));

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

        cmbDepartamento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione Departamento" }));
        pnlCiudad.add(cmbDepartamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 460, -1));

        panelRound2.add(pnlCiudad, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 690, 40));

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

        panelRound2.add(pnlIdentificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 690, 40));

        pnlModificar.setBackground(new java.awt.Color(255, 255, 255));
        pnlModificar.setPreferredSize(new java.awt.Dimension(220, 100));
        pnlModificar.setRoundBottomLeft(30);
        pnlModificar.setRoundBottomRight(30);
        pnlModificar.setRoundTopLeft(30);
        pnlModificar.setRoundTopRight(30);
        pnlModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlModificarMouseClicked(evt);
            }
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

        panelRound2.add(pnlModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 560, 220, 40));

        pnlDirección.setBackground(new java.awt.Color(255, 255, 255));
        pnlDirección.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        pnlDirección.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 460, 10));

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

        panelRound2.add(pnlDirección, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 700, 40));

        pnlCiudad1.setBackground(new java.awt.Color(255, 255, 255));
        pnlCiudad1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCiudad1.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 12)); // NOI18N
        lblCiudad1.setText("Ciudad");
        pnlCiudad1.add(lblCiudad1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 110, 20));

        panelRound12.setBackground(new java.awt.Color(255, 255, 255));
        panelRound12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelRound12.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 460, 10));

        jTextField10.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 12)); // NOI18N
        jTextField10.setBorder(null);
        jTextField10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField10ActionPerformed(evt);
            }
        });
        panelRound12.add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 460, 20));

        jLabel8.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 12)); // NOI18N
        jLabel8.setText("Nombre");
        panelRound12.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 20));

        pnlCiudad1.add(panelRound12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 630, 40));

        cmbCiudad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione su ciudad" }));
        pnlCiudad1.add(cmbCiudad, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 460, -1));

        panelRound2.add(pnlCiudad1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 690, 40));

        panelRound1.add(panelRound2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 760, 620));

        add(panelRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 760, 620));
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

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

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField9ActionPerformed

    private void txtIdentificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdentificacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdentificacionActionPerformed

    private void pnlModificarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlModificarMouseEntered
        MouseOnBoton(pnlModificar, fondoHoldModificar);
    }//GEN-LAST:event_pnlModificarMouseEntered

    private void pnlModificarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlModificarMouseExited
        MouseOutBoton(pnlModificar, fondoModificar);
    }//GEN-LAST:event_pnlModificarMouseExited

    private void pnlModificarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlModificarMousePressed

    }//GEN-LAST:event_pnlModificarMousePressed

    private void pnlModificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlModificarMouseClicked
        // TODO add your handling code here:
        Modificar();
    }//GEN-LAST:event_pnlModificarMouseClicked

    private void txtDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionActionPerformed

    private void pswContraseniaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pswContraseniaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pswContraseniaActionPerformed

    private void jTextField10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField10ActionPerformed

    private void cmbTipoIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTipoIdActionPerformed

    public void MouseOnBoton(JPanel panel, Color color) {
        panel.setCursor(new Cursor(HAND_CURSOR));
        panel.setBackground(color);
    }

    public void MouseOutBoton(JPanel panel, Color color) {
        panel.setBackground(color);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbCiudad;
    private javax.swing.JComboBox cmbDepartamento;
    private javax.swing.JComboBox cmbTipoId;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JLabel lblApellido;
    private javax.swing.JLabel lblCiudad;
    private javax.swing.JLabel lblCiudad1;
    private javax.swing.JLabel lblContrasenia;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblIdentificacion;
    private javax.swing.JLabel lblModificar;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTipoId;
    private javax.swing.JLabel lblTitulo;
    private componentes.PanelRound panelRound1;
    private componentes.PanelRound panelRound11;
    private componentes.PanelRound panelRound12;
    private componentes.PanelRound panelRound2;
    private componentes.PanelRound panelRound5;
    private componentes.PanelRound pnlApellido;
    private componentes.PanelRound pnlCiudad;
    private componentes.PanelRound pnlCiudad1;
    private componentes.PanelRound pnlContrasenia;
    private componentes.PanelRound pnlCorreo;
    private componentes.PanelRound pnlDirección;
    private componentes.PanelRound pnlIdentificacion;
    private componentes.PanelRound pnlModificar;
    private componentes.PanelRound pnlNombre;
    private componentes.PanelRound pnlTelefono;
    private componentes.PanelRound pnlTipoId;
    private javax.swing.JPasswordField pswContrasenia;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtIdentificacion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
