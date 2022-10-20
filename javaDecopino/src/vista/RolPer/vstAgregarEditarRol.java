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
    ArrayList<MdlRolRecurso> agregarRolRecurso = new ArrayList();
    MdlRol rolModificar = new MdlRol();
    int id = 0;

    /**
     * Creates new form pnlInicio
     */
    public vstAgregarEditarRol(int id) {
        initComponents();
        inicio();
        this.id = id;
        if (id == 0) {
            ModoAgregar();

        } else {
            ModoEditar(id);
        }
    }

    public void ModoAgregar() {
        lblTituloRol.setText("Agregar Rol");
        btnActualizar.setVisible(false);
    }

    public void ModoEditar(int id) {
        lblTituloRol.setText("Modificar Rol");
        btnCrear.setVisible(false);
        CtrRol ctrr = new CtrRol();
        rolModificar = ctrr.mostrarRol(id);
        llenarRol(rolModificar);
        llenarPermisos(rolModificar);
        condicionVerCliente();
        condicionVerInventario();
        condicionVerProveedor();
        condicionVerRoles();
        condicionVerUsuario();
        
        
    }

    public void llenarRol(MdlRol rol) {
        txtNombreRol.setText(rol.getNombre());
        txaFunciones.setText(rol.getFunciones());
    }

    public void llenarPermisos(MdlRol rol) {
        CtrRol ctrr = new CtrRol();
        listaRolRecurso = ctrr.mostrarRolRec(rol);

        asignaRolRecursoInventario(listaRolRecurso.get(0));
        asignaRolRecursoProveedor(listaRolRecurso.get(1));
        asignaRolRecursoCliente(listaRolRecurso.get(2));
        asignaRolRecursoRol(listaRolRecurso.get(3));
        asignaRolRecursoUsuario(listaRolRecurso.get(4));
        asignaRolRecursoDespacho(listaRolRecurso.get(5));
        asignaRolRecursoFactura(listaRolRecurso.get(6));
    }

    public void inicio() {
        chbClienteAgregar.setEnabled(false);
        chbDespachosAgregar.setEnabled(false);
        chbFacturasAgregar.setEnabled(false);
        chbFacturasEditar.setEnabled(false);

    }

    public void validarInformacionRol() {
        if (validarRolNombre()) {
            if (txaFunciones.getText().isEmpty()) {
                int opcion = JOptionPane.showConfirmDialog(null, "Esta apunto de crear el rol sin \nasignarle funciones desea continuar", "  Advertencia", 2);
                if (opcion == 0) {
                    if (id == 0) {
                        crearRol();
                    }
                    if (!(id == 0)) {
                        modificarRol();
                    }
                }
            }
            if (!txaFunciones.getText().isEmpty()) {
                if (validarRolFunciones()) {
                    if (id == 0) {
                        crearRol();
                    }
                    if (!(id == 0)) {
                        modificarRol();
                    }
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
        if (ctrv.validarTamano(txtNombreRol.getText().toLowerCase(), 2, 31)) {
            if (ctrv.validarCaracteres(txtNombreRol.getText().toLowerCase())) {
                if (id == 0) {
                    if (!ctrr.rolExiste(txtNombreRol.getText().toLowerCase())) {
                        informacionError = informacionError + "Este rol ya a sido creado\n";
                        txtNombreRol.setForeground(Color.red);
                    } else {
                        txtNombreRol.setForeground(Color.black);
                        confirmar = true;
                    }
                } else {
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
            JOptionPane.showMessageDialog(null, informacionError, "Error en la creanción del rol", 1);
        }
        return confirmar;
    }

    public boolean validarRolFunciones() {
        boolean confirmar = false;
        CtrValidador validar = new CtrValidador();
        if (validar.validarCaracteres(txaFunciones.getText().toLowerCase()) && validar.validarTamano(txaFunciones.getText().toLowerCase(), 1, 200)) {
            confirmar = true;
        }
        return confirmar;
    }

    public void crearRol() {

        MdlRol rol = new MdlRol();
        rol.setFunciones(txaFunciones.getText().toLowerCase());
        rol.setNombre(txtNombreRol.getText().toLowerCase());

        ObtenerDatosRecursos();
        boolean subir = false;
        CtrRol Ctrr = new CtrRol();
        subir = Ctrr.crearRolPer(agregarRolRecurso, rol);

        if (subir == true) {
            JOptionPane.showMessageDialog(null, "Rol creado Exitosamente");
            vstVerRol panel = new vstVerRol();
            vstMenu.panelContenedor(panel);
        }
    }

    public void modificarRol() {

        rolModificar.setFunciones(txaFunciones.getText().toLowerCase());
        rolModificar.setNombre(txtNombreRol.getText().toLowerCase());

        ObtenerDatosRecursos();
        boolean subir = false;
        CtrRol Ctrr = new CtrRol();
        
        subir = Ctrr.actualizarRolPer(agregarRolRecurso, rolModificar);

        if (subir == true) {
            JOptionPane.showMessageDialog(null, "rol Modificado Exitosamente");
            vstVerRol panel = new vstVerRol();
            vstMenu.panelContenedor(panel);
        }
    }

    public void ObtenerDatosRecursos() {
        ArrayList<MdlRecursos> listaRecursos = new ArrayList();
        CtrRol Ctrr = new CtrRol();

        listaRecursos = Ctrr.consultarRecursos();

        rolRecursoInventario(listaRecursos.get(0));
        rolRecursoProveedores(listaRecursos.get(1));
        rolRecursoCliente(listaRecursos.get(2));
        rolRecursoRoles(listaRecursos.get(3));
        rolRecursoUsuarios(listaRecursos.get(4));
        rolRecursoDespachos(listaRecursos.get(5));
        rolRecursoFacturas(listaRecursos.get(6));

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
        agregarRolRecurso.add(RolRecurso);
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
        agregarRolRecurso.add(RolRecurso);
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
        agregarRolRecurso.add(RolRecurso);
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
        agregarRolRecurso.add(RolRecurso);
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
        agregarRolRecurso.add(RolRecurso);
    }

    public void rolRecursoDespachos(MdlRecursos recurso) {
        MdlRolRecurso RolRecurso = new MdlRolRecurso();
        RolRecurso.setRecurso(recurso);
        RolRecurso.setCrear(0);
        RolRecurso.setEditar(0);
        RolRecurso.setLeer(0);
        RolRecurso.setMostrar(0);
        RolRecurso.setEliminar(0);
        if (chbDespachosAgregar.isSelected() == true) {
            RolRecurso.setCrear(1);
        }
        if (chbDespachosEditar.isSelected() == true) {
            RolRecurso.setEditar(1);
        }
        if (chbDespachosEliminar.isSelected() == true) {
            RolRecurso.setEliminar(1);
        }
        if (chbDespachosVer.isSelected() == true) {
            RolRecurso.setLeer(1);
        }
        if (chbDespachosMostrar.isSelected() == true) {
            RolRecurso.setMostrar(1);
        }
        agregarRolRecurso.add(RolRecurso);
    }

    public void rolRecursoFacturas(MdlRecursos recurso) {
        MdlRolRecurso RolRecurso = new MdlRolRecurso();
        RolRecurso.setRecurso(recurso);
        RolRecurso.setCrear(0);
        RolRecurso.setEditar(0);
        RolRecurso.setLeer(0);
        RolRecurso.setMostrar(0);
        RolRecurso.setEliminar(0);
        if (chbFacturasAgregar.isSelected() == true) {
            RolRecurso.setCrear(1);
        }
        if (chbFacturasEditar.isSelected() == true) {
            RolRecurso.setEditar(1);
        }
        if (chbFacturasEliminar.isSelected() == true) {
            RolRecurso.setEliminar(1);
        }
        if (chbFacturasVer.isSelected() == true) {
            RolRecurso.setLeer(1);
        }
        if (chbFacturasMostrar.isSelected() == true) {
            RolRecurso.setMostrar(1);
        }
        agregarRolRecurso.add(RolRecurso);
    }

    public void asignaRolRecursoCliente(MdlRolRecurso RolRecurso) {

        chbClienteAgregar.setSelected(false);
        chbClienteEditar.setSelected(false);
        chbClienteEliminar.setSelected(false);
        chbClienteMostrar.setSelected(false);
        chbClienteVer.setSelected(false);

        if (RolRecurso.getCrear() == 1) {
            chbClienteAgregar.setSelected(true);
        }
        if (RolRecurso.getEditar() == 1) {
            chbClienteEditar.setSelected(true);
        }
        if (RolRecurso.getEliminar() == 1) {
            chbClienteEliminar.setSelected(true);
        }
        if (RolRecurso.getMostrar() == 1) {
            chbClienteMostrar.setSelected(true);
        }
        if (RolRecurso.getLeer() == 1) {
            chbClienteVer.setSelected(true);
        }
    }

    public void asignaRolRecursoInventario(MdlRolRecurso RolRecurso) {

        chbInventarioAgregar.setSelected(false);
        chbInventarioEditar.setSelected(false);
        chbInventarioEliminar.setSelected(false);
        chbInventarioMostrar.setSelected(false);
        chbInventarioVer.setSelected(false);

        if (RolRecurso.getCrear() == 1) {
            chbInventarioAgregar.setSelected(true);
        }
        if (RolRecurso.getEditar() == 1) {
            chbInventarioEditar.setSelected(true);
        }
        if (RolRecurso.getEliminar() == 1) {
            chbInventarioEliminar.setSelected(true);
        }
        if (RolRecurso.getMostrar() == 1) {
            chbInventarioMostrar.setSelected(true);
        }
        if (RolRecurso.getLeer() == 1) {
            chbInventarioVer.setSelected(true);
        }
    }

    public void asignaRolRecursoRol(MdlRolRecurso RolRecurso) {

        chbRolesAgregar.setSelected(false);
        chbRolesEditar.setSelected(false);
        chbRolesEliminar.setSelected(false);
        chbRolesMostrar.setSelected(false);
        chbRolesVer.setSelected(false);

        if (RolRecurso.getCrear() == 1) {
            chbRolesAgregar.setSelected(true);
        }
        if (RolRecurso.getEditar() == 1) {
            chbRolesEditar.setSelected(true);
        }
        if (RolRecurso.getEliminar() == 1) {
            chbRolesEliminar.setSelected(true);
        }
        if (RolRecurso.getMostrar() == 1) {
            chbRolesMostrar.setSelected(true);
        }
        if (RolRecurso.getLeer() == 1) {
            chbRolesVer.setSelected(true);
        }
    }

    public void asignaRolRecursoProveedor(MdlRolRecurso RolRecurso) {

        chbProveedoresAgregar.setSelected(false);
        chbProveedoresEditar.setSelected(false);
        chbProveedoresEliminar.setSelected(false);
        chbProveedoresMostrar.setSelected(false);
        chbProveedoresVer.setSelected(false);

        if (RolRecurso.getCrear() == 1) {
            chbProveedoresAgregar.setSelected(true);
        }
        if (RolRecurso.getEditar() == 1) {
            chbProveedoresEditar.setSelected(true);
        }
        if (RolRecurso.getEliminar() == 1) {
            chbProveedoresEliminar.setSelected(true);
        }
        if (RolRecurso.getMostrar() == 1) {
            chbProveedoresMostrar.setSelected(true);
        }
        if (RolRecurso.getLeer() == 1) {
            chbProveedoresVer.setSelected(true);
        }
    }

    public void asignaRolRecursoUsuario(MdlRolRecurso RolRecurso) {

        chbUsuariosAgregar.setSelected(false);
        chbUsuariosEditar.setSelected(false);
        chbUsuariosEliminar.setSelected(false);
        chbUsuariosMostrar.setSelected(false);
        chbUsuariosVer.setSelected(false);

        if (RolRecurso.getCrear() == 1) {
            chbUsuariosAgregar.setSelected(true);
        }
        if (RolRecurso.getEditar() == 1) {
            chbUsuariosEditar.setSelected(true);
        }
        if (RolRecurso.getEliminar() == 1) {
            chbUsuariosEliminar.setSelected(true);
        }
        if (RolRecurso.getMostrar() == 1) {
            chbUsuariosMostrar.setSelected(true);
        }
        if (RolRecurso.getLeer() == 1) {
            chbUsuariosVer.setSelected(true);
        }
    }

    public void asignaRolRecursoDespacho(MdlRolRecurso RolRecurso) {

        chbDespachosAgregar.setSelected(false);
        chbDespachosEditar.setSelected(false);
        chbDespachosEliminar.setSelected(false);
        chbDespachosMostrar.setSelected(false);
        chbDespachosVer.setSelected(false);

        if (RolRecurso.getCrear() == 1) {
            chbDespachosAgregar.setSelected(true);
        }
        if (RolRecurso.getEditar() == 1) {
            chbDespachosEditar.setSelected(true);
        }
        if (RolRecurso.getEliminar() == 1) {
            chbDespachosEliminar.setSelected(true);
        }
        if (RolRecurso.getMostrar() == 1) {
            chbDespachosMostrar.setSelected(true);
        }
        if (RolRecurso.getLeer() == 1) {
            chbDespachosVer.setSelected(true);
        }
    }

    public void asignaRolRecursoFactura(MdlRolRecurso RolRecurso) {

        chbFacturasAgregar.setSelected(false);
        chbFacturasEditar.setSelected(false);
        chbFacturasEliminar.setSelected(false);
        chbFacturasMostrar.setSelected(false);
        chbFacturasVer.setSelected(false);

        if (RolRecurso.getCrear() == 1) {
            chbFacturasAgregar.setSelected(true);
        }
        if (RolRecurso.getEditar() == 1) {
            chbFacturasEditar.setSelected(true);
        }
        if (RolRecurso.getEliminar() == 1) {
            chbFacturasEliminar.setSelected(true);
        }
        if (RolRecurso.getMostrar() == 1) {
            chbFacturasMostrar.setSelected(true);
        }
        if (RolRecurso.getLeer() == 1) {
            chbFacturasVer.setSelected(true);
        }
    }

    public void confirmacionCancelacion() {

        int result = JOptionPane.showConfirmDialog(panelRound1, "Seguro que desea salir", "Cancelar", 2);
        if (result == 0 && id == 0) {
            vstRolPer panel = new vstRolPer();
            vstMenu.panelContenedor(panel);
        }
        if (result == 0 && !(id == 0)) {
            vstVerRol panel = new vstVerRol();
            vstMenu.panelContenedor(panel);
        }
    }

    public void condicionVerCliente() {
        if (!chbClienteVer.isSelected()) {
            chbClienteMostrar.setEnabled(false);
            chbClienteEditar.setEnabled(false);
            chbClienteEliminar.setEnabled(false);

            chbClienteMostrar.setSelected(false);
            chbClienteEditar.setSelected(false);
            chbClienteEliminar.setSelected(false);
        } else {
            chbClienteMostrar.setEnabled(true);
            chbClienteEditar.setEnabled(true);
            chbClienteEliminar.setEnabled(true);
        }
    }

    public void condicionVerInventario() {
        if (!chbInventarioVer.isSelected()) {
            chbInventarioMostrar.setEnabled(false);
            chbInventarioEditar.setEnabled(false);
            chbInventarioEliminar.setEnabled(false);

            chbInventarioMostrar.setSelected(false);
            chbInventarioEditar.setSelected(false);
            chbInventarioEliminar.setSelected(false);

        } else {
            chbInventarioMostrar.setEnabled(true);
            chbInventarioEditar.setEnabled(true);
            chbInventarioEliminar.setEnabled(true);
        }
    }

    public void condicionVerRoles() {
        if (!chbRolesVer.isSelected()) {
            chbRolesMostrar.setEnabled(false);
            chbRolesEditar.setEnabled(false);
            chbRolesEliminar.setEnabled(false);

            chbRolesMostrar.setSelected(false);
            chbRolesEditar.setSelected(false);
            chbRolesEliminar.setSelected(false);

        } else {
            chbRolesMostrar.setEnabled(true);
            chbRolesEditar.setEnabled(true);
            chbRolesEliminar.setEnabled(true);
        }
    }

    public void condicionVerProveedor() {
        if (!chbProveedoresVer.isSelected()) {
            chbProveedoresMostrar.setEnabled(false);
            chbProveedoresEditar.setEnabled(false);
            chbProveedoresEliminar.setEnabled(false);

            chbProveedoresMostrar.setSelected(false);
            chbProveedoresEditar.setSelected(false);
            chbProveedoresEliminar.setSelected(false);
        } else {
            chbProveedoresMostrar.setEnabled(true);
            chbProveedoresEditar.setEnabled(true);
            chbProveedoresEliminar.setEnabled(true);
        }
    }

    public void condicionVerUsuario() {
        if (!chbUsuariosVer.isSelected()) {
            chbUsuariosMostrar.setEnabled(false);
            chbUsuariosEditar.setEnabled(false);
            chbUsuariosEliminar.setEnabled(false);

            chbUsuariosMostrar.setSelected(false);
            chbUsuariosEditar.setSelected(false);
            chbUsuariosEliminar.setSelected(false);
        } else {
            chbUsuariosMostrar.setEnabled(true);
            chbUsuariosEditar.setEnabled(true);
            chbUsuariosEliminar.setEnabled(true);
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
        pnlUsuarios2 = new componentes.PanelRound();
        chbFacturasVer = new javax.swing.JCheckBox();
        chbFacturasAgregar = new javax.swing.JCheckBox();
        chbFacturasEditar = new javax.swing.JCheckBox();
        chbFacturasEliminar = new javax.swing.JCheckBox();
        chbFacturasMostrar = new javax.swing.JCheckBox();
        jSeparator10 = new javax.swing.JSeparator();
        jPanel10 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        pnlUsuarios3 = new componentes.PanelRound();
        chbDespachosVer = new javax.swing.JCheckBox();
        chbDespachosAgregar = new javax.swing.JCheckBox();
        chbDespachosEditar = new javax.swing.JCheckBox();
        chbDespachosEliminar = new javax.swing.JCheckBox();
        chbDespachosMostrar = new javax.swing.JCheckBox();
        jSeparator11 = new javax.swing.JSeparator();
        jPanel11 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
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
        jSeparator7 = new javax.swing.JSeparator();

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
                .addGap(22, 22, 22)
                .addComponent(lblPermisos, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 151, Short.MAX_VALUE)
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

        panelRound1.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 640, 30));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        pnlClientes.setBackground(new java.awt.Color(255, 255, 255));
        pnlClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlClientesMousePressed(evt);
            }
        });

        chbClienteVer.setSelected(true);
        chbClienteVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbClienteVerActionPerformed(evt);
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
                .addGap(18, 18, 18)
                .addComponent(lblCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Roles");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Proveedores");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlProveedoresLayout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        chbUsuariosEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbUsuariosEliminarActionPerformed(evt);
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

        pnlUsuarios2.setBackground(new java.awt.Color(255, 255, 255));
        pnlUsuarios2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlUsuarios2MousePressed(evt);
            }
        });

        chbFacturasVer.setSelected(true);
        chbFacturasVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbFacturasVerActionPerformed(evt);
            }
        });

        chbFacturasEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbFacturasEliminarActionPerformed(evt);
            }
        });

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Facturas");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(0, 5, Short.MAX_VALUE)
                .addComponent(jLabel13))
        );

        javax.swing.GroupLayout pnlUsuarios2Layout = new javax.swing.GroupLayout(pnlUsuarios2);
        pnlUsuarios2.setLayout(pnlUsuarios2Layout);
        pnlUsuarios2Layout.setHorizontalGroup(
            pnlUsuarios2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUsuarios2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlUsuarios2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlUsuarios2Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(128, 128, 128)
                        .addComponent(chbFacturasVer)
                        .addGap(65, 65, 65)
                        .addComponent(chbFacturasAgregar)
                        .addGap(65, 65, 65)
                        .addComponent(chbFacturasMostrar)
                        .addGap(65, 65, 65)
                        .addComponent(chbFacturasEditar)
                        .addGap(65, 65, 65)
                        .addComponent(chbFacturasEliminar)
                        .addGap(17, 17, 17))
                    .addComponent(jSeparator10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );
        pnlUsuarios2Layout.setVerticalGroup(
            pnlUsuarios2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUsuarios2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlUsuarios2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(chbFacturasMostrar)
                    .addComponent(chbFacturasEliminar)
                    .addComponent(chbFacturasEditar)
                    .addComponent(chbFacturasAgregar)
                    .addComponent(chbFacturasVer)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlUsuarios3.setBackground(new java.awt.Color(255, 255, 255));
        pnlUsuarios3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlUsuarios3MousePressed(evt);
            }
        });

        chbDespachosVer.setSelected(true);
        chbDespachosVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbDespachosVerActionPerformed(evt);
            }
        });

        chbDespachosEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbDespachosEliminarActionPerformed(evt);
            }
        });

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Despachos");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(0, 5, Short.MAX_VALUE)
                .addComponent(jLabel14))
        );

        javax.swing.GroupLayout pnlUsuarios3Layout = new javax.swing.GroupLayout(pnlUsuarios3);
        pnlUsuarios3.setLayout(pnlUsuarios3Layout);
        pnlUsuarios3Layout.setHorizontalGroup(
            pnlUsuarios3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUsuarios3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlUsuarios3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlUsuarios3Layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(128, 128, 128)
                        .addComponent(chbDespachosVer)
                        .addGap(65, 65, 65)
                        .addComponent(chbDespachosAgregar)
                        .addGap(65, 65, 65)
                        .addComponent(chbDespachosMostrar)
                        .addGap(65, 65, 65)
                        .addComponent(chbDespachosEditar)
                        .addGap(65, 65, 65)
                        .addComponent(chbDespachosEliminar)
                        .addGap(17, 17, 17))
                    .addComponent(jSeparator11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );
        pnlUsuarios3Layout.setVerticalGroup(
            pnlUsuarios3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUsuarios3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlUsuarios3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(chbDespachosMostrar)
                    .addComponent(chbDespachosEliminar)
                    .addComponent(chbDespachosEditar)
                    .addComponent(chbDespachosAgregar)
                    .addComponent(chbDespachosVer)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlRoles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlInventario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlUsuarios2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlUsuarios3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(pnlClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlInventario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlRoles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlUsuarios3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlUsuarios2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelRound1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 670, 400));

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

        panelRound1.add(pnlNombreRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 310, 40));

        pnlOpciones.setBackground(new java.awt.Color(255, 255, 255));

        btnCrear.setBackground(new java.awt.Color(0, 255, 153));
        btnCrear.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCrear.setText("Crear");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(255, 102, 102));
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
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(btnCancelar)
                .addGap(18, 18, 18)
                .addComponent(btnActualizar)
                .addGap(18, 18, 18)
                .addComponent(btnCrear)
                .addContainerGap())
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

        panelRound1.add(pnlOpciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 570, 320, 40));

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
                .addContainerGap(71, Short.MAX_VALUE)
                .addComponent(lblNombreRol1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelRound1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 30, 350, 110));
        panelRound1.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 605, 10));

        add(panelRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 760, 620));
    }// </editor-fold>//GEN-END:initComponents

    private void pnlClientesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlClientesMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlClientesMousePressed

    private void chbRolesEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbRolesEditarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chbRolesEditarActionPerformed

    private void chbRolesEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbRolesEliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chbRolesEliminarActionPerformed

    private void pnlRolesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlRolesMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlRolesMousePressed

    private void pnlProveedoresMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlProveedoresMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlProveedoresMousePressed

    private void pnlInventarioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlInventarioMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlInventarioMousePressed

    private void chbUsuariosEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbUsuariosEliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chbUsuariosEliminarActionPerformed

    private void pnlUsuariosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlUsuariosMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlUsuariosMousePressed

    private void txtNombreRolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreRolActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreRolActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        validarInformacionRol();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        confirmacionCancelacion();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        validarInformacionRol();
    }//GEN-LAST:event_btnCrearActionPerformed

    private void chbClienteVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbClienteVerActionPerformed
        condicionVerCliente();
    }//GEN-LAST:event_chbClienteVerActionPerformed

    private void chbInventarioVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbInventarioVerActionPerformed
        condicionVerInventario();
    }//GEN-LAST:event_chbInventarioVerActionPerformed

    private void chbRolesVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbRolesVerActionPerformed
        condicionVerRoles();
    }//GEN-LAST:event_chbRolesVerActionPerformed

    private void chbProveedoresVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbProveedoresVerActionPerformed
        condicionVerProveedor();
    }//GEN-LAST:event_chbProveedoresVerActionPerformed

    private void chbUsuariosVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbUsuariosVerActionPerformed
        condicionVerUsuario();
    }//GEN-LAST:event_chbUsuariosVerActionPerformed

    private void chbFacturasVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbFacturasVerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chbFacturasVerActionPerformed

    private void chbFacturasEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbFacturasEliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chbFacturasEliminarActionPerformed

    private void pnlUsuarios2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlUsuarios2MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlUsuarios2MousePressed

    private void chbDespachosVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbDespachosVerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chbDespachosVerActionPerformed

    private void chbDespachosEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbDespachosEliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chbDespachosEliminarActionPerformed

    private void pnlUsuarios3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlUsuarios3MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlUsuarios3MousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCrear;
    private javax.swing.JCheckBox chbClienteAgregar;
    private javax.swing.JCheckBox chbClienteEditar;
    private javax.swing.JCheckBox chbClienteEliminar;
    private javax.swing.JCheckBox chbClienteMostrar;
    private javax.swing.JCheckBox chbClienteVer;
    private javax.swing.JCheckBox chbDespachosAgregar;
    private javax.swing.JCheckBox chbDespachosEditar;
    private javax.swing.JCheckBox chbDespachosEliminar;
    private javax.swing.JCheckBox chbDespachosMostrar;
    private javax.swing.JCheckBox chbDespachosVer;
    private javax.swing.JCheckBox chbFacturasAgregar;
    private javax.swing.JCheckBox chbFacturasEditar;
    private javax.swing.JCheckBox chbFacturasEliminar;
    private javax.swing.JCheckBox chbFacturasMostrar;
    private javax.swing.JCheckBox chbFacturasVer;
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
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
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
    private javax.swing.JLabel lblMostrar;
    private javax.swing.JLabel lblNombreRol;
    private javax.swing.JLabel lblNombreRol1;
    private javax.swing.JLabel lblPermisos;
    private javax.swing.JLabel lblTituloRol;
    private javax.swing.JLabel lblVer;
    private componentes.PanelRound panelRound1;
    private componentes.PanelRound pnlClientes;
    private componentes.PanelRound pnlInventario;
    private componentes.PanelRound pnlNombreRol;
    private javax.swing.JPanel pnlOpciones;
    private componentes.PanelRound pnlProveedores;
    private componentes.PanelRound pnlRoles;
    private componentes.PanelRound pnlUsuarios;
    private componentes.PanelRound pnlUsuarios2;
    private componentes.PanelRound pnlUsuarios3;
    private javax.swing.JTextArea txaFunciones;
    private javax.swing.JTextField txtNombreRol;
    // End of variables declaration//GEN-END:variables
}
