/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.Usuario;

import controlador.CtrAuxiliares;
import controlador.CtrContrasena;
import controlador.CtrLocaciones;
import controlador.CtrRol;
import controlador.CtrUsuario;
import controlador.CtrValidador;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.MdlCiudad;
import modelo.MdlDepartamento;
import modelo.MdlRol;
import modelo.MdlUsuario;
import vista.vstMenu;

/**
 *
 * @author hamme
 */
public class vstAgregarEditarUsuario extends javax.swing.JPanel {

    /**
     * Creates new form pnlInicio
     */
    int ciudad = 0;
    ArrayList<MdlDepartamento> departamentos = new ArrayList();
    int TipoIdentificicacion = 0;
    int id = 0;
    int habilidarLLenadoCiudad = 0;

    public vstAgregarEditarUsuario(int id) {
        initComponents();

//        CtrContrasena ctrc = new CtrContrasena();
//        String encrip = ctrc.hash("12345678");
//        System.out.println(encrip);
        this.id = id;
        if (id == 0) {
            ModoAgregar();

        } else {
            ModoEditar(id);
        }
    }

    public void ModoAgregar() {
        llenarComboBoxs();
        btnActulizar.setVisible(false);
    }

    public void ModoEditar(int id) {
        btnAceptar.setVisible(false);
    }

    public void llenarComboBoxs() {
        llenarComboRol();
        llenarComboTipoIdent();
        llenarComboDepa();
        llenarComboCiudad();
    }

    public void llenarComboRol() {
        CtrRol ctrr = new CtrRol();
        ArrayList<MdlRol> listaRoles = ctrr.consultar();
        cbbRol.removeAllItems();
        for (int posicion = 0; posicion < listaRoles.size(); posicion++) {
            cbbRol.addItem(listaRoles.get(posicion).getNombre());
        }
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

    public void obtenerValidarInformacionUsuario() {
        MdlUsuario usuario = new MdlUsuario();
        String informacionError = "Dato invalido en los campos:\n";
        boolean validarIdent = true;
        boolean validarNombre = true;
        boolean validarApellido = true;
        boolean validarTelefono = true;
        boolean validarCorreo = true;
        boolean validarDireccion = true;
        boolean validarContra = true;

        usuario.setRol(obtenerRol());
        usuario.setCiudad(obtenerCiudad());
        usuario.setTidenrificacion(obtenerTipoIdent());
        usuario.setIdentificacion(obtenerIdent());
        if (usuario.getIdentificacion() == 0) {
            validarIdent = false;
            informacionError = informacionError + "Identificación\n";
        }
        usuario.setNombre(obtenerNombre());
        if (usuario.getNombre().isEmpty()) {
            validarNombre = false;
            informacionError = informacionError + "Nombre\n";
        }

        usuario.setApellido(obtenerApellido());
        if (usuario.getApellido().isEmpty()) {
            validarApellido = false;
            informacionError = informacionError + "Apellido\n";
        }

        usuario.setTelefono(obtenerTelefono());
        if (usuario.getTelefono().isEmpty()) {
            validarTelefono = false;
            informacionError = informacionError + "Telefono\n";
        }

        usuario.setCorreo(obtenerCorreo());
        if (usuario.getCorreo().isEmpty()) {
            validarCorreo = false;
            informacionError = informacionError + "Correo\n";
        }

        usuario.setDireccion(obtenerDireccion());
        if (usuario.getDireccion().isEmpty()) {
            validarDireccion = false;
            informacionError = informacionError + "Direccion\n";
        }

        usuario.setContrasenia(obtenerContrasenia());
        if (usuario.getContrasenia().isEmpty()) {
            validarContra = false;
            informacionError = informacionError + "Contraseña o ConfContraseña\n";
        }

        if (validarIdent && validarNombre && validarApellido && validarTelefono && validarCorreo && validarContra && validarDireccion) {
            crearUsuario(usuario);
        } else {
            JOptionPane.showMessageDialog(null, informacionError, "Dato invalido", 1);
        }
    }

    public MdlRol obtenerRol() {
        MdlRol rol = new MdlRol();
        String rolNombre = cbbRol.getSelectedItem().toString();
        CtrRol ctrr = new CtrRol();
        rol = ctrr.mostrarRolNombre(rolNombre);
        return rol;
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

    public String obtenerContrasenia() {
        txtContrasena.setForeground(Color.black);
        txtConfirContrasena.setForeground(Color.black);
        CtrValidador ctrv = new CtrValidador();
        String contrasenia = "";
        if (ctrv.validarContrasenia(txtContrasena.getText())) {
            if (txtContrasena.getText().equals(txtConfirContrasena.getText())) {
                contrasenia = txtContrasena.getText();
            } else {
                txtConfirContrasena.setForeground(Color.red);
            }
        } else {
            txtContrasena.setForeground(Color.red);
        }

        return contrasenia;
    }

    public void crearUsuario(MdlUsuario usuario) {
        CtrUsuario ctru = new CtrUsuario();
        String informacionError = "";
        boolean validar = true;

        if (!(ctru.correNoExiste(usuario.getCorreo()))) {
            txtCorreo.setForeground(Color.red);
            informacionError = "El correo ya exite\n";
            validar = false;
        }

        if (!(ctru.identNoExiste(usuario.getIdentificacion()))) {
            txtIdentificacion.setForeground(Color.red);
            informacionError = informacionError + "El numero de identificaion ya exite\n";
            validar = false;
        }
        if (validar) {
            boolean subir = ctru.crearPersonaUsuario(usuario);
            if (subir == true) {
                JOptionPane.showMessageDialog(null, "Usuario creado exitosamente", "Informacion", 1);
                vstVerUsuario panel = new vstVerUsuario();
                vstMenu.panelContenedor(panel);
            }
        } else {
            JOptionPane.showMessageDialog(null, informacionError, "Error en la creanción del usuario", 1);
        }

    }

    public void validarEntradaNumericaIdent() {
        CtrValidador ctrv = new CtrValidador();
        for (int posicion = 0; posicion < txtIdentificacion.getText().length(); posicion++) {
            if (!ctrv.validarNumero(txtIdentificacion.getText().substring(posicion, posicion + 1))) {
                txtIdentificacion.setText(txtIdentificacion.getText().substring(0, posicion));
                JOptionPane.showMessageDialog(null, "Solo permite el ingreso de números", "Infomracion", 1);
            }
        }
    }

    public void validarEntradaNumericaTelefono() {
        CtrValidador ctrv = new CtrValidador();
        for (int posicion = 0; posicion < txtTelefono.getText().length(); posicion++) {
            if (!ctrv.validarNumero(txtTelefono.getText().substring(posicion, posicion + 1))) {
                txtTelefono.setText(txtTelefono.getText().substring(0, posicion));
                JOptionPane.showMessageDialog(null, "Solo permite el ingreso de números", "Infomracion", 1);
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
        panelRound10 = new componentes.PanelRound();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        txtContrasena = new javax.swing.JPasswordField();
        panelRound12 = new componentes.PanelRound();
        jLabel11 = new javax.swing.JLabel();
        cbbTipoIdent = new javax.swing.JComboBox<>();
        cbbRol = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        panelRound11 = new componentes.PanelRound();
        jLabel10 = new javax.swing.JLabel();
        cbbDepartamento = new javax.swing.JComboBox<>();
        cbbCiudad = new javax.swing.JComboBox<>();
        panelRound9 = new componentes.PanelRound();
        btnActulizar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        panelRound13 = new componentes.PanelRound();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        txtConfirContrasena = new javax.swing.JPasswordField();

        setBackground(new java.awt.Color(244, 244, 244));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRound1.setBackground(new java.awt.Color(255, 255, 255));
        panelRound1.setRoundBottomLeft(30);
        panelRound1.setRoundBottomRight(30);
        panelRound1.setRoundTopLeft(30);
        panelRound1.setRoundTopRight(30);
        panelRound1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 24)); // NOI18N
        lblTitulo.setText("Agregar Usuario");
        panelRound1.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, 30));

        panelRound2.setBackground(new java.awt.Color(255, 255, 255));
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
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        panelRound3.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 460, 20));

        jLabel1.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 12)); // NOI18N
        jLabel1.setText("Nombre");
        panelRound3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 20));

        panelRound2.add(panelRound3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 630, 40));

        panelRound4.setBackground(new java.awt.Color(255, 255, 255));
        panelRound4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelRound4.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 460, 10));

        txtIdentificacion.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 12)); // NOI18N
        txtIdentificacion.setBorder(null);
        txtIdentificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdentificacionActionPerformed(evt);
            }
        });
        txtIdentificacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdentificacionKeyReleased(evt);
            }
        });
        panelRound4.add(txtIdentificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 460, 20));

        jLabel3.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 12)); // NOI18N
        jLabel3.setText("Identificacion");
        panelRound4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 20));

        panelRound2.add(panelRound4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 630, 40));

        panelRound6.setBackground(new java.awt.Color(255, 255, 255));
        panelRound6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelRound6.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 460, 10));

        txtApellido.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 12)); // NOI18N
        txtApellido.setBorder(null);
        txtApellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidoActionPerformed(evt);
            }
        });
        panelRound6.add(txtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 460, 20));

        jLabel5.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 12)); // NOI18N
        jLabel5.setText("Apellido");
        panelRound6.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 20));

        panelRound2.add(panelRound6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 630, -1));

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

        panelRound2.add(panelRound5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 630, 40));

        panelRound7.setBackground(new java.awt.Color(255, 255, 255));
        panelRound7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelRound7.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 460, 10));

        txtCorreo.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 12)); // NOI18N
        txtCorreo.setBorder(null);
        txtCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCorreoActionPerformed(evt);
            }
        });
        panelRound7.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 460, 20));

        jLabel6.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 12)); // NOI18N
        jLabel6.setText("Correo");
        panelRound7.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 20));

        panelRound2.add(panelRound7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 630, 40));

        panelRound8.setBackground(new java.awt.Color(255, 255, 255));
        panelRound8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelRound8.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 460, 10));

        txtDireccion.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 12)); // NOI18N
        txtDireccion.setBorder(null);
        txtDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionActionPerformed(evt);
            }
        });
        panelRound8.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 460, 20));

        jLabel7.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 12)); // NOI18N
        jLabel7.setText("Dirección");
        panelRound8.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 20));

        panelRound2.add(panelRound8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 630, 40));

        panelRound10.setBackground(new java.awt.Color(255, 255, 255));
        panelRound10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelRound10.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 460, 10));

        jLabel9.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 12)); // NOI18N
        jLabel9.setText("Contraseña");
        panelRound10.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 100, 20));

        txtContrasena.setBorder(null);
        panelRound10.add(txtContrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 460, 20));

        panelRound2.add(panelRound10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 630, 40));

        panelRound12.setBackground(new java.awt.Color(255, 255, 255));
        panelRound12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 12)); // NOI18N
        jLabel11.setText("Tipo de identificacion");
        panelRound12.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 160, 20));

        cbbTipoIdent.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        panelRound12.add(cbbTipoIdent, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, -1, 20));

        cbbRol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbRolActionPerformed(evt);
            }
        });
        panelRound12.add(cbbRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, -1, 20));

        jLabel13.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 12)); // NOI18N
        jLabel13.setText("Rol");
        panelRound12.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 40, 20));

        panelRound2.add(panelRound12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 630, 40));

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

        panelRound2.add(panelRound11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 650, 40));

        panelRound9.setBackground(new java.awt.Color(255, 255, 255));
        panelRound9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnActulizar.setBackground(new java.awt.Color(255, 255, 0));
        btnActulizar.setText("Actualizar");
        btnActulizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActulizarActionPerformed(evt);
            }
        });
        panelRound9.add(btnActulizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, -1, -1));

        btnAceptar.setBackground(new java.awt.Color(0, 255, 153));
        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
        panelRound9.add(btnAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, -1, -1));

        btnCancelar.setBackground(new java.awt.Color(255, 51, 51));
        btnCancelar.setText("Cancelar");
        panelRound9.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, -1, -1));

        panelRound2.add(panelRound9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 510, 630, 40));

        panelRound13.setBackground(new java.awt.Color(255, 255, 255));
        panelRound13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelRound13.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, 440, 10));

        jLabel12.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 12)); // NOI18N
        jLabel12.setText("Conf. Contraseña");
        panelRound13.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 20));

        txtConfirContrasena.setBorder(null);
        panelRound13.add(txtConfirContrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 440, 20));

        panelRound2.add(panelRound13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, 630, 40));

        panelRound1.add(panelRound2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 680, 570));

        add(panelRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 760, 620));
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtIdentificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdentificacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdentificacionActionPerformed

    private void txtApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidoActionPerformed

    private void txtCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoActionPerformed

    private void txtDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionActionPerformed

    private void btnActulizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActulizarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnActulizarActionPerformed

    private void cbbRolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbRolActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbRolActionPerformed

    private void cbbDepartamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbDepartamentoActionPerformed
        // TODO add your handling code here:
        llenarComboCiudad();
    }//GEN-LAST:event_cbbDepartamentoActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        obtenerValidarInformacionUsuario();        // TODO add your handling code here:
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void txtIdentificacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdentificacionKeyReleased
        validarEntradaNumericaIdent();
    }//GEN-LAST:event_txtIdentificacionKeyReleased

    private void txtTelefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyReleased
        validarEntradaNumericaTelefono();
    }//GEN-LAST:event_txtTelefonoKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnActulizar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JComboBox<String> cbbCiudad;
    private javax.swing.JComboBox<String> cbbDepartamento;
    private javax.swing.JComboBox<String> cbbRol;
    private javax.swing.JComboBox<String> cbbTipoIdent;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JLabel lblTitulo;
    private componentes.PanelRound panelRound1;
    private componentes.PanelRound panelRound10;
    private componentes.PanelRound panelRound11;
    private componentes.PanelRound panelRound12;
    private componentes.PanelRound panelRound13;
    private componentes.PanelRound panelRound2;
    private componentes.PanelRound panelRound3;
    private componentes.PanelRound panelRound4;
    private componentes.PanelRound panelRound5;
    private componentes.PanelRound panelRound6;
    private componentes.PanelRound panelRound7;
    private componentes.PanelRound panelRound8;
    private componentes.PanelRound panelRound9;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JPasswordField txtConfirContrasena;
    private javax.swing.JPasswordField txtContrasena;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtIdentificacion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
