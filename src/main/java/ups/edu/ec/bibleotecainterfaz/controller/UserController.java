package ups.edu.ec.bibleotecainterfaz.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import ups.edu.ec.bibleotecainterfaz.dao.UsuarioDAO;

import ups.edu.ec.bibleotecainterfaz.excepciones.CamposVaciosException;
import ups.edu.ec.bibleotecainterfaz.excepciones.ValidadorDato;
import ups.edu.ec.bibleotecainterfaz.models.Usuario;
import ups.edu.ec.bibleotecainterfaz.enums.MensajeUsuario;
import ups.edu.ec.bibleotecainterfaz.enums.TipoMembresia; 
import ups.edu.ec.bibleotecainterfaz.view.*;

/**
 *
 * @author stephancedillo
 */
public class UserController {

    private ActualizarUsuarioView actualizarUsuarioView;
    private BuscarUsuarioView buscarUsuarioView;
    private EliminarUsuarioView eliminarUsuarioView;
    private CrearUsuarioView crearUsuarioView;
    private ListarUsuarioView listarUsuarioView;

    DateTimeFormatter formato = DateTimeFormatter.ofPattern("d/M/yy");
    private UsuarioDAO usuarioDAO;

    // Arreglo inicial por defecto de membresías
    private String[] membresias = {
        "Normal", // 0
        "Corporativa", // 1
        "Académica", // 2
        "Estudiantil", // 3
        "Especial" // 4
    };

    // Arreglo inicial por defecto de mensajes
    private String[] mensajes = {
        "Usuario no encontrado", // 0
        "Usuario actualizado correctamente", //1
        "No se pudo actualizar el usuario", //2
        "¿Está seguro de eliminar este usuario?", //3
        "Usuario eliminado correctamente", // 4
        "Usuario creado correctamente",// 5
        "Debe ingresar su fecha de nacimiento", // 6
        "Debe ingresar un correo",// 7
        "Debe ingresar una contraseña", //8
        "Debe ingresar su nombre",// 9
        "Debe ingresar su apellido",//10
        "Debe ingresar su cedula",//11
        "Debe ingresar una dirreccion",//12
        "Debe ingresar una nueva fecha de nacimiento",//13
        "Correo Invalido",//14
        "correo Invalido sin Com al final",//15
        "Su nombre no puede tener caracteres especiales",//16
        "Su apellido no puede tener caracteres especiales",//17
        "Su dirrecion no puede tener caracteres especiales",//18
        "Limite de edad permitido superado",//19
        "Cedula invalida"//20
    };

    public UserController(
            ActualizarUsuarioView actualizarUsuarioView,
            BuscarUsuarioView buscarUsuarioView,
            EliminarUsuarioView eliminarUsuarioView,
            CrearUsuarioView crearUsuarioView,
            ListarUsuarioView listarUsuarioView,
            UsuarioDAO usuarioDAO) {

        this.actualizarUsuarioView = actualizarUsuarioView;
        this.buscarUsuarioView = buscarUsuarioView;
        this.eliminarUsuarioView = eliminarUsuarioView;
        this.crearUsuarioView = crearUsuarioView;
        this.listarUsuarioView = listarUsuarioView;
        this.usuarioDAO = usuarioDAO;

        configurarEventos();


        listarUsuario();
    }

    private void configurarEventos() {
        configurarEventosActualizarUsuario();
        configurarEventosBuscarUsuario();
        configurarEventosEliminarUsuario();
        configurarEventosCrearUsuario();
        configurarEventosListarUsuario();
    }

    private void configurarEventosActualizarUsuario() {
        actualizarUsuarioView.getBtnBuscar().addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarActUsuario();
            }
        });
        actualizarUsuarioView.getBtnActualizacion().addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarUsuario();
            }
        });
    }

    private void configurarEventosEliminarUsuario() {

        eliminarUsuarioView.getBtnBuscar().addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarEliminarUsuario();
            }
        });
        eliminarUsuarioView.getBtnEliminar().addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarUsuario();
            }
        });

    }

    private void configurarEventosCrearUsuario() {
        crearUsuarioView.getBtnAceptar().addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearUsuario();
            }
        });

    }

    private void configurarEventosBuscarUsuario() {
        buscarUsuarioView.getBtnBuscar().addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarUsuario();
            }
        });
    }

    private void configurarEventosListarUsuario() {
        listarUsuarioView.getBtnListarUsuario().addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarUsuario();
            }
        });
        listarUsuario();
    }

    private void buscarActUsuario() {
        Usuario usuario = usuarioDAO.buscar(actualizarUsuarioView.getTxtCedula().getText());

        if (usuario == null) {
            mostrarInformacion(MensajeUsuario.USUARIO_NO_ENCONTRADO.getTexto(mensajes), actualizarUsuarioView);
            return;
        }

        actualizarUsuarioView.getTxtNombreBuscado().setText(usuario.getNombre());
        actualizarUsuarioView.getTxtApellidoBuscado().setText(usuario.getApellido());
        actualizarUsuarioView.getTxtEmailBuscado().setText(usuario.getEmail());
        actualizarUsuarioView.getTxtDireccionBuscado().setText(usuario.getDireccion());
        actualizarUsuarioView.getComboBoxStringsMembresia().setSelectedItem(usuario.getMembresia().getTipoMembresia());
    }

    private void actualizarUsuario() {
        Usuario usuario = usuarioDAO.buscar(actualizarUsuarioView.getTxtCedula().getText());

        if (usuario == null) {
            mostrarInformacion(MensajeUsuario.USUARIO_NO_ENCONTRADO.getTexto(mensajes), actualizarUsuarioView);
            return;
        }
        try {
            //========== VALIDADORES =============
            campoVacioActualizar();
            validadorEmail(actualizarUsuarioView.getTxtEmailBuscado().getText());
            validadorCaracteresExEnActualizar();
            soloNumerosCedula(actualizarUsuarioView.getTxtCedula().getText());
            validadorFechaNac(new java.sql.Date(actualizarUsuarioView.getjCalendarNuevaFecha().getDate().getTime()).toLocalDate());
            //====================================

            Usuario nuevoUsuario = new Usuario(actualizarUsuarioView.getTxtEmailBuscado().getText(),
                    actualizarUsuarioView.getTxtContrasenaBuscado().getText(),
                    usuario.getCedula(),
                    LocalDate.parse(
                            new java.sql.Date(actualizarUsuarioView.getjCalendarNuevaFecha().getDate().getTime())
                                    .toLocalDate().format(formato),
                            formato),
                    actualizarUsuarioView.getTxtNombreBuscado().getText(),
                    actualizarUsuarioView.getTxtApellidoBuscado().getText(),
                    actualizarUsuarioView.getTxtDireccionBuscado().getText(), usuario.isTieneDiscapacidad());
            nuevoUsuario.agregarMembresia(actualizarUsuarioView.getComboBoxStringsMembresia().getSelectedItem().toString());
            usuarioDAO.actualizar(nuevoUsuario);
            mostrarInformacion(MensajeUsuario.USUARIO_ACTUALIZADO.getTexto(mensajes), actualizarUsuarioView);

        } catch (CamposVaciosException e) {
            mostrarInformacion(e.getMessage(), actualizarUsuarioView);

        } catch (ValidadorDato e) {
            mostrarInformacion(e.getMessage(), actualizarUsuarioView);

        } catch (NullPointerException e) {
            mostrarInformacion("Debe seleccionar una fecha y una membresía.", actualizarUsuarioView);

        } catch (IllegalArgumentException e) {
            mostrarInformacion("Los datos ingresados no son válidos.", actualizarUsuarioView);
        }
    }

    private void buscarEliminarUsuario() {
         try{
            soloNumerosCedula(eliminarUsuarioView.getTxtCedula().getText());
            Usuario usuario = usuarioDAO.buscar(eliminarUsuarioView.getTxtCedula().getText());

            if (usuario == null) {
                mostrarInformacion(MensajeUsuario.USUARIO_NO_ENCONTRADO.getTexto(mensajes), eliminarUsuarioView);
                return;
            }

            eliminarUsuarioView.getLblNombreBuscado().setText(usuario.getNombre() + " " + usuario.getApellido());
            eliminarUsuarioView.getTxtEmailBuscado().setText(usuario.getEmail());
            eliminarUsuarioView.getTxtEdadBuscado().setText(String.valueOf(usuario.getEdad()));
            eliminarUsuarioView.getTxtDireccionBuscado().setText(usuario.getDireccion());
            eliminarUsuarioView.getTxtFormatedFechaCaducidadBuscado()
                    .setText(usuario.getMembresia().getFechaVencimiento().format(formato));
            eliminarUsuarioView.getTxtMembresiaBuscado().setText(usuario.getMembresia().getTipoMembresia());
        }catch(ValidadorDato e){
            mostrarInformacion(e.getMessage(),eliminarUsuarioView);
        }
    }

    private void eliminarUsuario() {
            
        Usuario usuario = usuarioDAO.buscar(eliminarUsuarioView.getTxtCedula().getText());

        if (usuario == null) {
            mostrarInformacion(MensajeUsuario.USUARIO_NO_ENCONTRADO.getTexto(mensajes), eliminarUsuarioView);
            return;
        }

        if (!confirmarAccion(MensajeUsuario.CONFIRMAR_ELIMINAR.getTexto(mensajes), eliminarUsuarioView)) {
            return;
        }

        usuarioDAO.eliminar(usuario.getCedula());
        mostrarInformacion(MensajeUsuario.USUARIO_ELIMINADO.getTexto(mensajes), eliminarUsuarioView);
            
    }

    private void crearUsuario() {
        try {
            //======= VALIDADORES ================
            campoVacioUsuario();
            validadorEmail(crearUsuarioView.getTxtEmail().getText());
            validadorCaracteresExEnCrear();
            validadorFechaNac(new java.sql.Date(crearUsuarioView.getjCalendarFechaNac().getDate().getTime()).toLocalDate());
            soloNumerosCedula(crearUsuarioView.getTxtCedula().getText());
            //====================================
            if (actualizarUsuarioView.getComboBoxStringsMembresia().getSelectedItem() == null) {
                throw new CamposVaciosException("Debe seleccionar una membresía."); 
            }
            Usuario u = new Usuario(
                    crearUsuarioView.getTxtEmail().getText(),
                    crearUsuarioView.getTxtContraseña().getText(),
                    crearUsuarioView.getTxtCedula().getText(),
                    LocalDate.parse(
                            new java.sql.Date(crearUsuarioView.getjCalendarFechaNac().getDate().getTime())
                                    .toLocalDate().format(formato),
                            formato),
                    crearUsuarioView.getTxtNombre().getText(),
                    crearUsuarioView.getTxtApellido().getText(),
                    crearUsuarioView.getTxtDireccion().getText(),
                    false
            );
            u.agregarMembresia(crearUsuarioView.getComboBoxStringsMembresia().getSelectedItem().toString());
            usuarioDAO.crear(u);

            mostrarInformacion(MensajeUsuario.USUARIO_CREADO.getTexto(mensajes), crearUsuarioView);
        } catch (CamposVaciosException e) {
            mostrarInformacion(e.getMessage(), actualizarUsuarioView);

        } catch (ValidadorDato e) {
            mostrarInformacion(e.getMessage(), actualizarUsuarioView);

        } catch (NullPointerException e) {
            mostrarInformacion("Debe seleccionar una fecha y una membresía.", actualizarUsuarioView);

        } catch (IllegalArgumentException e) {
            mostrarInformacion("Los datos ingresados no son válidos.", actualizarUsuarioView);
        }
    }

    private void buscarUsuario() {
        try{
            soloNumerosCedula(buscarUsuarioView.getTxtCedula().getText());
            
            Usuario usuario = usuarioDAO.buscar(buscarUsuarioView.getTxtCedula().getText());

            if (usuario == null) {
                mostrarInformacion(MensajeUsuario.USUARIO_NO_ENCONTRADO.getTexto(mensajes), buscarUsuarioView);
                return;
            }

            buscarUsuarioView.getLblTituloBuscado().setText(usuario.getNombre());
            buscarUsuarioView.getTxtEmailBuscado().setText(usuario.getEmail());
            buscarUsuarioView.getTxtEdadBuscado().setText(String.valueOf(usuario.getEdad()));
            buscarUsuarioView.getTxtDireccionBuscado().setText(usuario.getDireccion());
            buscarUsuarioView.getTxtFormatedFechaCaducidadBuscado()
                    .setText(usuario.getMembresia().getFechaVencimiento().format(formato));
            buscarUsuarioView.getTxtMembresiaBuscado().setText(usuario.getMembresia().getTipoMembresia());
        }catch(ValidadorDato valid){
            mostrarInformacion(valid.getMessage(),buscarUsuarioView);
        }
        
    }

    private void listarUsuario() {
        listarUsuarioView.cargarDatos(usuarioDAO.listar());
    }

    public void cambioIdioma(ResourceBundle bundle) {
        // Actualizamos los arreglos globales antes de modificar las vistas
        mensajes = bundle.getString("mensajesUsuario").split(",");
        membresias = bundle.getString("comboBoxMembresia").split(",");

        cambioIdiomaActualizarUsuario(bundle);
        cambioIdiomaBuscarUsuario(bundle);
        cambioIdiomaEliminarUsuario(bundle);
        cambioIdiomaCrearUsuario(bundle);
        cambioIdiomaListarUsuario(bundle);
    }

    private void cambioIdiomaCrearUsuario(ResourceBundle bundle) {
        // ===== BOTONES =====
        crearUsuarioView.getBtnAceptar().setText(bundle.getString("btnAceptar"));

        // ===== COMBO BOX (Uso del Enum TipoMembresia) =====
        crearUsuarioView.getComboBoxStringsMembresia().removeAllItems();
        for (TipoMembresia membresiaEnum : TipoMembresia.values()) {
            crearUsuarioView.getComboBoxStringsMembresia().addItem(membresiaEnum.getTexto(membresias));
        }

        // ===== LABELS =====
        crearUsuarioView.getLblApellido().setText(bundle.getString("lblApellido"));
        crearUsuarioView.getLblCedula().setText(bundle.getString("lblCedula"));
        crearUsuarioView.getLblContraseña().setText(bundle.getString("lblContraseña"));
        crearUsuarioView.getLblDireccion().setText(bundle.getString("lblDireccion"));
        crearUsuarioView.getLblEmail().setText(bundle.getString("lblEmail"));
        crearUsuarioView.getLblFechaNacimiento().setText(bundle.getString("lblFechaNacimiento"));
        crearUsuarioView.getLblMembresia().setText(bundle.getString("lblMembresia"));
        crearUsuarioView.getLblNombre().setText(bundle.getString("lblNombre"));
        crearUsuarioView.getLblTituloCreacionUsuario().setText(bundle.getString("lblTituloCrearUsuario"));
    }

    private void cambioIdiomaEliminarUsuario(ResourceBundle bundle) {
        // ===== BOTONES =====
        eliminarUsuarioView.getBtnBuscar().setText(bundle.getString("btnBuscar"));
        eliminarUsuarioView.getBtnEliminar().setText(bundle.getString("btnEliminar"));

        // ===== LABELS =====
        eliminarUsuarioView.getLblCedula().setText(bundle.getString("lblCedula"));
        eliminarUsuarioView.getLblDireccion().setText(bundle.getString("lblDireccion"));
        eliminarUsuarioView.getLblEdad().setText(bundle.getString("lblEdad"));
        eliminarUsuarioView.getLblEmail().setText(bundle.getString("lblEmail"));
        eliminarUsuarioView.getLblFechaCaducidad().setText(bundle.getString("lblFechaCaducidad"));
        eliminarUsuarioView.getLblMembresia().setText(bundle.getString("lblMembresia"));
        eliminarUsuarioView.getLblNombreBuscado().setText(bundle.getString("lblNombre"));
        eliminarUsuarioView.getLblTituloBusquedaUsuario().setText(bundle.getString("lblTituloEliminarUsuario"));
    }

    private void cambioIdiomaBuscarUsuario(ResourceBundle bundle) {
        // ===== BOTONES =====
        buscarUsuarioView.getBtnBuscar().setText(bundle.getString("btnBuscar"));

        // ===== LABELS =====
        buscarUsuarioView.getLblCedula().setText(bundle.getString("lblCedula"));
        buscarUsuarioView.getLblDireccion().setText(bundle.getString("lblDireccion"));
        buscarUsuarioView.getLblEdad().setText(bundle.getString("lblEdad"));
        buscarUsuarioView.getLblEmail().setText(bundle.getString("lblEmail"));
        buscarUsuarioView.getLblFechaCaducidad().setText(bundle.getString("lblFechaCaducidad"));
        buscarUsuarioView.getLblMembresia().setText(bundle.getString("lblMembresia"));
        buscarUsuarioView.getLblTituloBuscado().setText(bundle.getString("lblNombre"));
        buscarUsuarioView.getLblTituloBusquedaUsuario().setText(bundle.getString("lblTituloBuscarUsuario"));
    }

    private void cambioIdiomaActualizarUsuario(ResourceBundle bundle) {
        actualizarUsuarioView.getBtnActualizacion().setText(bundle.getString("btnActualizacion"));
        actualizarUsuarioView.getBtnBuscar().setText(bundle.getString("btnBuscar"));

        actualizarUsuarioView.getLblApellido().setText(bundle.getString("lblApellido"));
        actualizarUsuarioView.getLblCedula().setText(bundle.getString("lblCedula"));
        actualizarUsuarioView.getLblContrasena().setText(bundle.getString("lblContraseña"));
        actualizarUsuarioView.getLblDireccion().setText(bundle.getString("lblDireccion"));

        actualizarUsuarioView.getLblEdad().setText(bundle.getString("lblEdad"));
        actualizarUsuarioView.getLblEmail().setText(bundle.getString("lblEmail"));
        actualizarUsuarioView.getLblMembresia().setText(bundle.getString("lblMembresia"));
        actualizarUsuarioView.getLblNombre().setText(bundle.getString("lblNombre"));
        actualizarUsuarioView.getLblRenovar().setText(bundle.getString("lblRenovar"));
        actualizarUsuarioView.getLblTituloBusquedaUsuario().setText(bundle.getString("lblTituloActualizarUsuario"));

        // ===== COMBO BOX (Uso del Enum TipoMembresia) =====
        actualizarUsuarioView.getComboBoxStringsMembresia().removeAllItems();
        for (TipoMembresia membresiaEnum : TipoMembresia.values()) {
            actualizarUsuarioView.getComboBoxStringsMembresia().addItem(membresiaEnum.getTexto(membresias));
        }
    }

    private void cambioIdiomaListarUsuario(ResourceBundle bundle) {
        listarUsuarioView.getBtnListarUsuario().setText(bundle.getString("btnListarUsuario"));
        configurarTabla(bundle);
    }

    private void configurarTabla(ResourceBundle bundle) {
        String[] columnas = bundle.getString("columnasUsuario").split(",");
        listarUsuarioView.setModelo(new DefaultTableModel(columnas, 0));
        listarUsuarioView.getTblListarUsuario().setModel(listarUsuarioView.getModelo());
    }

    public void mostrarInformacion(String mensaje, JInternalFrame frame) {
        JOptionPane.showMessageDialog(frame, mensaje);
    }

    //IMPLEMENTACION EN ELIMINAR
    public boolean confirmarAccion(String mensaje, JInternalFrame frame) {
        int opcion = JOptionPane.showConfirmDialog(
                frame,
                mensaje,
                "Confirmación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        return opcion == JOptionPane.YES_OPTION;
    }

    // === VERIFICADORES DE CAMPOS VACIOS ===
    // CAMPOS VACIOS EN CREAR USUARIO
    private void campoVacioUsuario() throws CamposVaciosException {
        if (crearUsuarioView.getjCalendarFechaNac().getDate() == null) {
            throw new CamposVaciosException(MensajeUsuario.REQ_FECHA_NAC.getTexto(mensajes));
        }

        Map<String, String> campos = new LinkedHashMap<>();

        campos.put(crearUsuarioView.getTxtEmail().getText(), MensajeUsuario.REQ_CORREO.getTexto(mensajes));
        campos.put(crearUsuarioView.getTxtContraseña().getText(), MensajeUsuario.REQ_CONTRASENA.getTexto(mensajes));
        campos.put(crearUsuarioView.getTxtNombre().getText(), MensajeUsuario.REQ_NOMBRE.getTexto(mensajes));
        campos.put(crearUsuarioView.getTxtApellido().getText(), MensajeUsuario.REQ_APELLIDO.getTexto(mensajes));
        campos.put(crearUsuarioView.getTxtCedula().getText(), MensajeUsuario.REQ_CEDULA.getTexto(mensajes));
        campos.put(crearUsuarioView.getTxtDireccion().getText(), MensajeUsuario.REQ_DIRECCION.getTexto(mensajes));

        for (Map.Entry<String, String> campo : campos.entrySet()) {
            if (campo.getKey() == null || campo.getKey().trim().isEmpty()) {
                throw new CamposVaciosException(campo.getValue());
            }
        }
    }

    // CAMPOS VACIOS EN ACTUALIZAR USUARIO
    private void campoVacioActualizar() throws CamposVaciosException {
        if (actualizarUsuarioView.getjCalendarNuevaFecha().getDate() == null) {
            throw new CamposVaciosException(MensajeUsuario.REQ_NUEVA_FECHA.getTexto(mensajes));
        }

        Map<String, String> campos = new LinkedHashMap<>();

        campos.put(actualizarUsuarioView.getTxtEmailBuscado().getText(), MensajeUsuario.REQ_CORREO.getTexto(mensajes));
        campos.put(actualizarUsuarioView.getTxtContrasenaBuscado().getText(), MensajeUsuario.REQ_CONTRASENA.getTexto(mensajes));
        campos.put(actualizarUsuarioView.getTxtNombreBuscado().getText(), MensajeUsuario.REQ_NOMBRE.getTexto(mensajes));
        campos.put(actualizarUsuarioView.getTxtApellidoBuscado().getText(), MensajeUsuario.REQ_APELLIDO.getTexto(mensajes));
        campos.put(actualizarUsuarioView.getTxtDireccionBuscado().getText(), MensajeUsuario.REQ_DIRECCION.getTexto(mensajes));

        for (Map.Entry<String, String> campo : campos.entrySet()) {
            if (campo.getKey() == null || campo.getKey().trim().isEmpty()) {
                throw new CamposVaciosException(campo.getValue());
            }
        }
    }

    // ======== VERIFICADOR DE DATOS ============

    // VALIDADOR DE EMAIL
    private void validadorEmail(String email) throws ValidadorDato {
        if (!email.contains("@")) {
            throw new ValidadorDato(MensajeUsuario.ERR_CORREO_INVALIDO.getTexto(mensajes));
        }
        if (!email.endsWith(".com")) {
            throw new ValidadorDato(MensajeUsuario.ERR_CORREO_SIN_COM.getTexto(mensajes));
        }
    }

    // VALIDADOR PARA NO USAR CARACTERES ESPECIALES
    // VALIDADOR PARA CREAR
    private void validadorCaracteresExEnCrear() throws ValidadorDato {

        Map<String, String> caracterEx = new LinkedHashMap<>();

        caracterEx.put(crearUsuarioView.getTxtNombre().getText(), MensajeUsuario.ERR_NOMBRE_ESP.getTexto(mensajes));
        caracterEx.put(crearUsuarioView.getTxtApellido().getText(), MensajeUsuario.ERR_APELLIDO_ESP.getTexto(mensajes));
        caracterEx.put(crearUsuarioView.getTxtDireccion().getText(), MensajeUsuario.ERR_DIRECCION_ESP.getTexto(mensajes));

        for (Map.Entry<String, String> campo : caracterEx.entrySet()) {

            String texto = campo.getKey();

            for (int i = 0; i < texto.length(); i++) {

                char c = texto.charAt(i);

                if (!Character.isLetter(c) && c != ' ') {
                    throw new ValidadorDato(campo.getValue());
                }
            }
        }
    }

    // VALIDADOR PARA ACTUALIZAR
    private void validadorCaracteresExEnActualizar() throws ValidadorDato {

        Map<String, String> caracterEx = new LinkedHashMap<>();

        caracterEx.put(actualizarUsuarioView.getTxtNombreBuscado().getText(), MensajeUsuario.ERR_NOMBRE_ESP.getTexto(mensajes));
        caracterEx.put(actualizarUsuarioView.getTxtApellidoBuscado().getText(), MensajeUsuario.ERR_APELLIDO_ESP.getTexto(mensajes));
        caracterEx.put(actualizarUsuarioView.getTxtDireccionBuscado().getText(), MensajeUsuario.ERR_DIRECCION_ESP.getTexto(mensajes));

        for (Map.Entry<String, String> campo : caracterEx.entrySet()) {

            String texto = campo.getKey();

            for (int i = 0; i < texto.length(); i++) {

                char c = texto.charAt(i);

                if (!Character.isLetter(c) && c != ' ') {
                    throw new ValidadorDato(campo.getValue());
                }
            }
        }
    }

    // VALIDADOR DE FECHA NACIMIENTO
    private void validadorFechaNac(LocalDate fechaIngresada) throws ValidadorDato {
        int añoIngresado = fechaIngresada.getYear();
        int añoActual = LocalDate.now().getYear() - 3;
        if (añoIngresado > añoActual) {
            throw new ValidadorDato(MensajeUsuario.ERR_LIMITE_EDAD.getTexto(mensajes));
        }
    }
    // SOLO NUMEROS EN CEDULA
    private void soloNumerosCedula(String cedula) throws ValidadorDato{
        for(int i = 0;i < cedula.length();i ++){
            
            char c = cedula.charAt(i);
            if(!Character.isDigit(c)){
                throw new ValidadorDato(MensajeUsuario.ERR_CEDULA_CONLETRA.getTexto(mensajes));
            }
        }
    }

    public ActualizarUsuarioView getActualizarUsuarioView() {
        return actualizarUsuarioView;
    }

    public void setActualizarUsuarioView(ActualizarUsuarioView actualizarUsuarioView) {
        this.actualizarUsuarioView = actualizarUsuarioView;
    }

    public BuscarUsuarioView getBuscarUsuarioView() {
        return buscarUsuarioView;
    }

    public void setBuscarUsuarioView(BuscarUsuarioView buscarUsuarioView) {
        this.buscarUsuarioView = buscarUsuarioView;
    }

    public EliminarUsuarioView getEliminarUsuarioView() {
        return eliminarUsuarioView;
    }

    public void setEliminarUsuarioView(EliminarUsuarioView eliminarUsuarioView) {
        this.eliminarUsuarioView = eliminarUsuarioView;
    }

    public CrearUsuarioView getCrearUsuarioView() {
        return crearUsuarioView;
    }

    public void setCrearUsuarioView(CrearUsuarioView crearUsuarioView) {
        this.crearUsuarioView = crearUsuarioView;
    }

    public ListarUsuarioView getListarUsuarioView() {
        return listarUsuarioView;
    }

    public void setListarUsuarioView(ListarUsuarioView listarUsuarioView) {
        this.listarUsuarioView = listarUsuarioView;
    }

    public DateTimeFormatter getFormato() {
        return formato;
    }

    public void setFormato(DateTimeFormatter formato) {
        this.formato = formato;
    }

    public UsuarioDAO getUsuarioDAO() {
        return usuarioDAO;
    }

    public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public String[] getMembresias() {
        return membresias;
    }

    public void setMembresias(String[] membresias) {
        this.membresias = membresias;
    }

    public String[] getMensajes() {
        return mensajes;
    }

    public void setMensajes(String[] mensajes) {
        this.mensajes = mensajes;
    }
    
    
}