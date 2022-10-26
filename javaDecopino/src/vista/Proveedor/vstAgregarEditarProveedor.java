/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.Proveedor;

import controlador.CtrAuxiliares;
import controlador.CtrLocaciones;
import controlador.CtrProveedor;
import controlador.CtrValidador;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import modelo.MdlCiudad;
import modelo.MdlDepartamento;
import modelo.MdlProveedor;
import vista.vstMenu;

/**
 *
 * @author hamme
 */
public class vstAgregarEditarProveedor extends javax.swing.JPanel {

    /**
     * Creates new form pnlInicio
     */
    int ciudad = 0;
    ArrayList<MdlDepartamento> departamentos = new ArrayList();
    MdlProveedor oldProveedor = new MdlProveedor();
    int TipoIdentificicacion = 0;
    int id = 0;
    int habilidarLLenadoCiudad = 0;

    public vstAgregarEditarProveedor(int id) {
        initComponents();
        llenarComboBoxs();
        this.id = id;
        if (id == 0) {
            ModoAgregar();

        } else {
            ModoEditar(id);
        }
    }

    public void ModoAgregar() {
        lblTitulo.setText("Agregar Proveedor");
        btnActulizar.setVisible(false);
    }

    public void ModoEditar(int id) {
        lblTitulo.setText("Editar Proveedor");
        btnAceptar.setVisible(false);
        CtrProveedor ctrp = new CtrProveedor();
        oldProveedor = ctrp.mostrarProveedor(id);
        llenarDatosProveedor(oldProveedor);
    }

    public void llenarComboBoxs() {
        llenarComboTipoIdent();
        llenarComboDepa();
        llenarComboCiudad();
    }

    public void llenarComboTipoIdent() {
        CtrAuxiliares ctra = new CtrAuxiliares();
        ArrayList<String> listaTipoIdent = ctra.consultarTipoIdent();
        cbbTipoIdent.removeAllItems();
        for (int posicion = 0; posicion < listaTipoIdent.size(); posicion++) {
            cbbTipoIdent.addItem(listaTipoIdent.get(posicion));
        }
    }

    public void llenarComboDepa() {
        CtrLocaciones ctrl = new CtrLocaciones();
        departamentos = ctrl.consultarDepartamentos(82);
        cbbDepartamento.removeAllItems();
        for (int posicion = 0; posicion < departamentos.size(); posicion++) {
            cbbDepartamento.addItem(departamentos.get(posicion).getDepartamento());
        }
        cbbDepartamento.setSelectedItem("VALLE DEL CAUCA");
        habilidarLLenadoCiudad = 1;
    }

    public void llenarComboCiudad() {
        if (habilidarLLenadoCiudad == 1) {
            String depaSeleccionado = (String) cbbDepartamento.getSelectedItem();
            CtrLocaciones ctrl = new CtrLocaciones();
            int idDepartamento = 0;
            for (int posicion = 0; posicion < departamentos.size(); posicion++) {
                if (depaSeleccionado.equals(departamentos.get(posicion).getDepartamento())) {
                    idDepartamento = departamentos.get(posicion).getId();
                }
            }

            ArrayList<MdlCiudad> listaCiudad = ctrl.consultarCiudades(idDepartamento);
            cbbCiudad.removeAllItems();
            for (int posicion = 0; posicion < listaCiudad.size(); posicion++) {
                cbbCiudad.addItem(listaCiudad.get(posicion).getCiudad());
            }
            if (cbbDepartamento.getSelectedItem().toString().equals("VALLE DEL CAUCA")) {
                cbbCiudad.setSelectedItem("Calí");
            }

        }
    }

    public void obtenerValidarInformacionProveedor() {
        MdlProveedor proveedor = new MdlProveedor();
        String informacionError = "Dato invalido en los campos:\n";
        boolean validarIdent = true;
        boolean validarNombre = true;
        boolean validarApellido = true;
        boolean validarTelefono = true;
        boolean validarCorreo = true;
        boolean validarDireccion = true;

        proveedor.setCiudad(obtenerCiudad());
        proveedor.setTidenrificacion(obtenerTipoIdent());
        proveedor.setIdentificacion(obtenerIdent());
        if (proveedor.getIdentificacion() == 0) {
            validarIdent = false;
            informacionError = informacionError + "Identificación\n";
        }
        proveedor.setNombre(obtenerNombre());
        if (proveedor.getNombre().isEmpty()) {
            validarNombre = false;
            informacionError = informacionError + "Nombre\n";
        }

        proveedor.setApellido(obtenerApellido());
        if (proveedor.getApellido().isEmpty()) {
            validarApellido = false;
            informacionError = informacionError + "Apellido\n";
        }

        proveedor.setTelefono(obtenerTelefono());
        if (proveedor.getTelefono().isEmpty()) {
            validarTelefono = false;
            informacionError = informacionError + "Telefono\n";
        }

        proveedor.setCorreo(obtenerCorreo());
        if (proveedor.getCorreo().isEmpty()) {
            validarCorreo = false;
            informacionError = informacionError + "Correo\n";
        }

        proveedor.setDireccion(obtenerDireccion());
        if (proveedor.getDireccion().isEmpty()) {
            validarDireccion = false;
            informacionError = informacionError + "Direccion\n";
        }

        

        

        if (id == 0) {
            if (validarIdent && validarNombre && validarApellido && validarTelefono && validarCorreo && validarDireccion) {
                crearProveedor(proveedor);
            } else {
                JOptionPane.showMessageDialog(null, informacionError, "Dato invalido", 1);
            }
        } else {
            editarProveedor(proveedor);
        }
    }

    public MdlCiudad obtenerCiudad() {
        MdlCiudad ciudad = new MdlCiudad();
        String ciudadNombre = cbbCiudad.getSelectedItem().toString();
        CtrLocaciones ctrl = new CtrLocaciones();
        ciudad = ctrl.mostrarCiudadNombre(ciudadNombre);
        return ciudad;
    }

    public String obtenerTipoIdent() {
        CtrAuxiliares ctra = new CtrAuxiliares();
        String tipoIdent = ctra.mostrarTipoIdentNombre(cbbTipoIdent.getSelectedItem().toString());
        return tipoIdent;
    }

    public int obtenerIdent() {
        txtIdentificacion.setForeground(Color.black);
        CtrValidador ctrv = new CtrValidador();
        int ident = 0;
        if (ctrv.validarTamano(txtIdentificacion.getText(), 7, 11)) {
            ident = Integer.parseInt(txtIdentificacion.getText());
        } else {
            txtIdentificacion.setForeground(Color.red);
        }
        return ident;
    }

    public String obtenerNombre() {
        txtNombre.setForeground(Color.black);
        CtrValidador ctrv = new CtrValidador();
        String nombre = "";
        txtNombre.setText(quitarEspacioInicial(txtNombre.getText().toLowerCase()));
        if (ctrv.validarCaracteres(txtNombre.getText()) && ctrv.validarTamano(txtNombre.getText(), 2, 45)) {
            nombre = txtNombre.getText();
        } else {
            txtNombre.setForeground(Color.red);
        }
        return nombre;
    }

    public String obtenerApellido() {
        txtApellido.setForeground(Color.black);
        CtrValidador ctrv = new CtrValidador();
        String Apellido = "";
        txtApellido.setText(quitarEspacioInicial(txtApellido.getText().toLowerCase()));
        if (ctrv.validarCaracteres(txtApellido.getText()) && ctrv.validarTamano(txtApellido.getText(), 2, 45)) {
            Apellido = txtApellido.getText();
        } else {
            txtApellido.setForeground(Color.red);
        }
        return Apellido;
    }

    public String obtenerTelefono() {
        txtTelefono.setForeground(Color.black);
        CtrValidador ctrv = new CtrValidador();
        String telefono = "";
        txtTelefono.setText(quitarEspacioInicial(txtTelefono.getText()));
        if (ctrv.validarTelefono(txtTelefono.getText())) {
            telefono = txtTelefono.getText();
        } else {
            txtTelefono.setForeground(Color.red);
        }
        return telefono;
    }

    public String obtenerCorreo() {
        txtCorreo.setForeground(Color.black);
        CtrValidador ctrv = new CtrValidador();
        String correo = "";
        txtCorreo.setText(quitarEspacioInicial(txtCorreo.getText()));
        if (ctrv.validarCorreo(txtCorreo.getText())) {
            correo = txtCorreo.getText();
        } else {
            txtCorreo.setForeground(Color.red);
        }
        return correo;
    }

    public String obtenerDireccion() {
        txtDireccion.setForeground(Color.black);
        CtrValidador ctrv = new CtrValidador();
        String direccion = "";
        txtDireccion.setText(quitarEspacioInicial(txtDireccion.getText()));
        if (ctrv.validarDireccion(txtDireccion.getText())) {
            direccion = txtDireccion.getText();
        } else {
            txtDireccion.setForeground(Color.red);
        }
        return direccion;
    }

    public void llenarDatosProveedor(MdlProveedor proveedor) {
        CtrAuxiliares ctra = new CtrAuxiliares();
        CtrLocaciones ctrl = new CtrLocaciones();
        cbbTipoIdent.setSelectedItem(ctra.mostrarTipoIdentId(proveedor.getTidenrificacion()));
        cbbDepartamento.setSelectedItem(proveedor.getCiudad().getDepartamento().getDepartamento());
        cbbCiudad.setSelectedItem(proveedor.getCiudad().getCiudad());

        txtIdentificacion.setText(String.valueOf(proveedor.getIdentificacion()));
        txtNombre.setText(proveedor.getNombre());
        txtApellido.setText(proveedor.getApellido());
        txtCorreo.setText(proveedor.getCorreo());
        txtDireccion.setText(proveedor.getDireccion());
        txtTelefono.setText(proveedor.getTelefono());

    }

    public void crearProveedor(MdlProveedor proveedor) {
        CtrProveedor ctrp = new CtrProveedor();
        String informacionError = "";
        boolean validar = true;

        if (!(ctrp.correNoExiste(proveedor.getCorreo()))) {
            txtCorreo.setForeground(Color.red);
            informacionError = "El correo ya exite\n";
            validar = false;
        }

        if (!(ctrp.identNoExiste(proveedor.getIdentificacion()))) {
            txtIdentificacion.setForeground(Color.red);
            informacionError = informacionError + "El numero de identificaion ya exite\n";
            validar = false;
        }
        if (validar) {
            boolean subir = ctrp.crearPersonaProveedor(proveedor);
            if (subir == true) {
                JOptionPane.showMessageDialog(null, "Proveedor creado exitosamente", "Informacion", 1);
                vstVerProveedor panel = new vstVerProveedor();
                vstMenu.panelContenedor(panel);
            }
        } else {
            JOptionPane.showMessageDialog(null, informacionError, "Error en la creanción del Proveedor", 1);
        }

    }

    public void editarProveedor(MdlProveedor proveedor) {
        CtrProveedor ctrp = new CtrProveedor();
        String informacionError = "";
        boolean validar = true;
        boolean subir = ctrp.editarPersonaProveedor(proveedor, oldProveedor.getIdPersona(), oldProveedor.getId());
        if (subir == true) {
            JOptionPane.showMessageDialog(null, "Proveedor Editado exitosamente", "Informacion", 1);
            MdlProveedor proveedorActualizado = ctrp.mostrarProveedor(id);
            vstInformacionProveedor panel = new vstInformacionProveedor(proveedorActualizado);
            vstMenu.panelContenedor(panel);
        } else {
            JOptionPane.showMessageDialog(null, "Error en la creanción del proveedor", "Informacion", 1);
        }
    }

    public void validarEntradaNumerica(JTextField txtVal) {
        CtrValidador ctrv = new CtrValidador();
        for (int posicion = 0; posicion < txtVal.getText().length(); posicion++) {
            if (!ctrv.validarNumero(txtVal.getText().substring(posicion, posicion + 1))) {
                txtVal.setText(txtVal.getText().substring(0, posicion));
                JOptionPane.showMessageDialog(null, "Solo permite el ingreso de números", "Información", 1);
            }
        }
    }

    public String quitarEspacioInicial(String texto) {
        String textoAjustado = texto;
        for (int posicion = 0; posicion < texto.length(); posicion++) {
            if (texto.substring(posicion, posicion + 1).equals(" ")) {
                textoAjustado = texto.substring(posicion + 1, texto.length());
            } else {
                break;
            }
        }
        return textoAjustado;
    }

    public void cancer() {
        int result = JOptionPane.showConfirmDialog(panelRound1, "Seguro que desea salir", "Cancelar", 2);
        if (result == 0 && id == 0) {
            vstProveedor panel = new vstProveedor();
            vstMenu.panelContenedor(panel);
        }
        if (result == 0 && !(id == 0)) {
            vstVerProveedor panel = new vstVerProveedor();
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
        lblTitulo = new javax.swing.JLabel();
        panelRound2 = new componentes.PanelRound();
        panelRound3 = new componentes.PanelRound();
        jSeparator1 = new javax.swing.JSeparator();
        txtNombre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        panelRound4 = new componentes.PanelRound();
        jSeparator2 = new javax.swing.JSeparator();
        txtIdentificacion = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        panelRound6 = new componentes.PanelRound();
        jSeparator4 = new javax.swing.JSeparator();
        txtApellido = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        panelRound5 = new componentes.PanelRound();
        jSeparator3 = new javax.swing.JSeparator();
        txtTelefono = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        panelRound7 = new componentes.PanelRound();
        jSeparator5 = new javax.swing.JSeparator();
        txtCorreo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        panelRound8 = new componentes.PanelRound();
        jSeparator6 = new javax.swing.JSeparator();
        txtDireccion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        panelRound12 = new componentes.PanelRound();
        jLabel11 = new javax.swing.JLabel();
        cbbTipoIdent = new javax.swing.JComboBox<>();
        panelRound11 = new componentes.PanelRound();
        jLabel10 = new javax.swing.JLabel();
        cbbDepartamento = new javax.swing.JComboBox<>();
        cbbCiudad = new javax.swing.JComboBox<>();
        panelRound9 = new componentes.PanelRound();
        btnActulizar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setBackground(new java.awt.Color(244, 244, 244));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRound1.setBackground(new java.awt.Color(255, 255, 255));
        panelRound1.setRoundBottomLeft(30);
        panelRound1.setRoundBottomRight(30);
        panelRound1.setRoundTopLeft(30);
        panelRound1.setRoundTopRight(30);
        panelRound1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 24)); // NOI18N
        lblTitulo.setText("Agregar Proveedor");
        panelRound1.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, 30));

        panelRound2.setBackground(new java.awt.Color(204, 204, 204));
        panelRound2.setForeground(new java.awt.Color(255, 51, 51));
        panelRound2.setRoundBottomLeft(30);
        panelRound2.setRoundBottomRight(30);
        panelRound2.setRoundTopLeft(30);
        panelRound2.setRoundTopRight(30);
        panelRound2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRound3.setBackground(new java.awt.Color(255, 255, 255));
        panelRound3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelRound3.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 460, 10));

        txtNombre.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 12)); // NOI18N
        txtNombre.setBorder(null);
        panelRound3.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 460, 20));

        jLabel1.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 12)); // NOI18N
        jLabel1.setText("Nombre");
        panelRound3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 20));

        panelRound2.add(panelRound3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 630, 40));

        panelRound4.setBackground(new java.awt.Color(255, 255, 255));
        panelRound4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelRound4.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 460, 10));

        txtIdentificacion.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 12)); // NOI18N
        txtIdentificacion.setBorder(null);
        txtIdentificacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdentificacionKeyReleased(evt);
            }
        });
        panelRound4.add(txtIdentificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 460, 20));

        jLabel3.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 12)); // NOI18N
        jLabel3.setText("Identificacion");
        panelRound4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 20));

        panelRound2.add(panelRound4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 630, 40));

        panelRound6.setBackground(new java.awt.Color(255, 255, 255));
        panelRound6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelRound6.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 460, 10));

        txtApellido.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 12)); // NOI18N
        txtApellido.setBorder(null);
        panelRound6.add(txtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 460, 20));

        jLabel5.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 12)); // NOI18N
        jLabel5.setText("Apellido");
        panelRound6.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 20));

        panelRound2.add(panelRound6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 630, -1));

        panelRound5.setBackground(new java.awt.Color(255, 255, 255));
        panelRound5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelRound5.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 460, 10));

        txtTelefono.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 12)); // NOI18N
        txtTelefono.setBorder(null);
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyReleased(evt);
            }
        });
        panelRound5.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 460, 20));

        jLabel4.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 12)); // NOI18N
        jLabel4.setText("Telefono");
        panelRound5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 20));

        panelRound2.add(panelRound5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 630, 40));

        panelRound7.setBackground(new java.awt.Color(255, 255, 255));
        panelRound7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelRound7.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 460, 10));

        txtCorreo.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 12)); // NOI18N
        txtCorreo.setBorder(null);
        panelRound7.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 460, 20));

        jLabel6.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 12)); // NOI18N
        jLabel6.setText("Correo");
        panelRound7.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 20));

        panelRound2.add(panelRound7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 630, 40));

        panelRound8.setBackground(new java.awt.Color(255, 255, 255));
        panelRound8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelRound8.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 460, 10));

        txtDireccion.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 12)); // NOI18N
        txtDireccion.setBorder(null);
        panelRound8.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 460, 20));

        jLabel7.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 12)); // NOI18N
        jLabel7.setText("Dirección");
        panelRound8.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 20));

        panelRound2.add(panelRound8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 630, 40));

        panelRound12.setBackground(new java.awt.Color(255, 255, 255));
        panelRound12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 12)); // NOI18N
        jLabel11.setText("Tipo de identificacion");
        panelRound12.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 160, 20));

        cbbTipoIdent.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        panelRound12.add(cbbTipoIdent, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, -1, 20));

        panelRound2.add(panelRound12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 630, 40));

        panelRound11.setBackground(new java.awt.Color(255, 255, 255));
        panelRound11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 12)); // NOI18N
        jLabel10.setText("Localidad");
        panelRound11.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 80, 20));

        cbbDepartamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbDepartamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbDepartamentoActionPerformed(evt);
            }
        });
        panelRound11.add(cbbDepartamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 160, 20));

        cbbCiudad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        panelRound11.add(cbbCiudad, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 160, 20));

        panelRound2.add(panelRound11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, 630, 40));

        panelRound9.setBackground(new java.awt.Color(255, 255, 255));
        panelRound9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnActulizar.setBackground(new java.awt.Color(255, 255, 0));
        btnActulizar.setText("Actualizar");
        btnActulizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActulizarActionPerformed(evt);
            }
        });
        panelRound9.add(btnActulizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        btnAceptar.setBackground(new java.awt.Color(0, 255, 153));
        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
        panelRound9.add(btnAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, -1, -1));

        btnCancelar.setBackground(new java.awt.Color(255, 51, 51));
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        panelRound9.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, -1, -1));

        panelRound2.add(panelRound9, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 510, 310, 40));

        panelRound1.add(panelRound2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 680, 570));

        add(panelRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 760, 620));
    }// </editor-fold>//GEN-END:initComponents

    private void btnActulizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActulizarActionPerformed
        obtenerValidarInformacionProveedor();
    }//GEN-LAST:event_btnActulizarActionPerformed

    private void cbbDepartamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbDepartamentoActionPerformed
        // TODO add your handling code here:
        llenarComboCiudad();
    }//GEN-LAST:event_cbbDepartamentoActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        obtenerValidarInformacionProveedor();        // TODO add your handling code here:
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void txtIdentificacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdentificacionKeyReleased
        validarEntradaNumerica(txtIdentificacion);
    }//GEN-LAST:event_txtIdentificacionKeyReleased

    private void txtTelefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyReleased
        validarEntradaNumerica(txtTelefono);
    }//GEN-LAST:event_txtTelefonoKeyReleased

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        cancer();
    }//GEN-LAST:event_btnCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnActulizar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JComboBox<String> cbbCiudad;
    private javax.swing.JComboBox<String> cbbDepartamento;
    private javax.swing.JComboBox<String> cbbTipoIdent;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JLabel lblTitulo;
    private componentes.PanelRound panelRound1;
    private componentes.PanelRound panelRound11;
    private componentes.PanelRound panelRound12;
    private componentes.PanelRound panelRound2;
    private componentes.PanelRound panelRound3;
    private componentes.PanelRound panelRound4;
    private componentes.PanelRound panelRound5;
    private componentes.PanelRound panelRound6;
    private componentes.PanelRound panelRound7;
    private componentes.PanelRound panelRound8;
    private componentes.PanelRound panelRound9;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtIdentificacion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
