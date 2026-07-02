/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ups.edu.ec.bibleotecainterfaz.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import ups.edu.ec.bibleotecainterfaz.dao.UsuarioDAO;
import ups.edu.ec.bibleotecainterfaz.models.Membresia;
import ups.edu.ec.bibleotecainterfaz.models.Usuario;
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

        usuarioDAO.crearListadoTemporal(20);

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
    private String[] mensajes = {
    "Usuario no encontrado",                    // 0
    "Usuario actualizado correctamente",        // 1
    "No se pudo actualizar el usuario",         // 2
    "¿Está seguro de eliminar este usuario?",   // 3
    "Usuario eliminado correctamente",          // 4
    "Usuario creado correctamente"              // 5
};

    private void buscarActUsuario() {

        Usuario usuario = usuarioDAO.buscar(actualizarUsuarioView.getTxtCedula().getText());

        if (usuario == null) {
            mostrarInformacion(mensajes[0], actualizarUsuarioView);
            return;
        }

        actualizarUsuarioView.getTxtNombreBuscado().setText(usuario.getNombre());
        actualizarUsuarioView.getTxtApellidoBuscado().setText(usuario.getApellido());
        actualizarUsuarioView.getTxtEmailBuscado().setText(usuario.getEmail());
        actualizarUsuarioView.getTxtEdadBuscado().setText(String.valueOf(usuario.getEdad()));
        actualizarUsuarioView.getTxtDireccionBuscado().setText(usuario.getDireccion());

        actualizarUsuarioView.getComboBoxStringsMembresia().setSelectedItem(usuario.getMembresia().getTipoMembresia());

       

    }

    private void actualizarUsuario() {
        Usuario usuario = usuarioDAO.buscar(actualizarUsuarioView.getTxtCedula().getText());

        if (usuario == null) {
            mostrarInformacion(mensajes[0], actualizarUsuarioView);
            return;
        }
        Usuario nuevoUsuario = new Usuario(actualizarUsuarioView.getTxtEmailBuscado().getText(),
                actualizarUsuarioView.getTxtContrasenaBuscado().getText(),
                usuario.getCedula(),
                LocalDate.parse(
                        crearUsuarioView.getTxtFormattedDate().getText(),
                        formato),
                actualizarUsuarioView.getTxtNombreBuscado().getText(),
                actualizarUsuarioView.getTxtApellidoBuscado().getText(),
                actualizarUsuarioView.getTxtDireccionBuscado().getText(), usuario.isTieneDiscapacidad());
        nuevoUsuario.agregarMembresia(actualizarUsuarioView.getComboBoxStringsMembresia().getSelectedItem().toString());
        usuarioDAO.actualizar(nuevoUsuario);
        mostrarInformacion(mensajes[1], actualizarUsuarioView);

    }

    private void buscarEliminarUsuario() {
        Usuario usuario = usuarioDAO.buscar(eliminarUsuarioView.getTxtCedula().getText());

        if (usuario == null) {
            mostrarInformacion(mensajes[0], eliminarUsuarioView);
            return;
        }

        eliminarUsuarioView.getLblNombreBuscado().setText(usuario.getNombre() + " " + usuario.getApellido());
        eliminarUsuarioView.getTxtEmailBuscado().setText(usuario.getEmail());
        eliminarUsuarioView.getTxtEdadBuscado().setText(String.valueOf(usuario.getEdad()));
        eliminarUsuarioView.getTxtDireccionBuscado().setText(usuario.getDireccion());
        eliminarUsuarioView.getTxtFormatedFechaCaducidadBuscado()
                .setText(usuario.getMembresia().getFechaVencimiento().format(formato));
        eliminarUsuarioView.getTxtMembresiaBuscado().setText(usuario.getMembresia().getTipoMembresia());

      
    }

    private void eliminarUsuario() {
        Usuario usuario = usuarioDAO.buscar(eliminarUsuarioView.getTxtCedula().getText());

        if (usuario == null) {
            mostrarInformacion(mensajes[0], eliminarUsuarioView);
            return;
        }

        if (!confirmarAccion(mensajes[2], eliminarUsuarioView)) {
            return;
        }

        usuarioDAO.eliminar(usuario.getCedula());
        mostrarInformacion(mensajes[3], eliminarUsuarioView);
    }

    private void crearUsuario() {

        Usuario u = new Usuario(
                crearUsuarioView.getTxtEmail().getText(),
                crearUsuarioView.getTxtContraseña().getText(),
                crearUsuarioView.getTxtCedula().getText(),
                LocalDate.parse(
                        crearUsuarioView.getTxtFormattedDate().getText(),
                        formato
                ),
                crearUsuarioView.getTxtNombre().getText(),
                crearUsuarioView.getTxtApellido().getText(),
                crearUsuarioView.getTxtDireccion().getText(),
                false
        );
        u.agregarMembresia(crearUsuarioView.getComboBoxStringsMembresia().getSelectedItem().toString());
        usuarioDAO.crear(u);

        mostrarInformacion(mensajes[0], crearUsuarioView);
    }

    private void buscarUsuario() {
        Usuario usuario = usuarioDAO.buscar(buscarUsuarioView.getTxtCedula().getText());

        if (usuario == null) {
            mostrarInformacion(mensajes[0], buscarUsuarioView);
            return;
        }

        buscarUsuarioView.getLblTituloBuscado().setText(usuario.getNombre());
        buscarUsuarioView.getTxtEmailBuscado().setText(usuario.getEmail());
        buscarUsuarioView.getTxtEdadBuscado().setText(String.valueOf(usuario.getEdad()));
        buscarUsuarioView.getTxtDireccionBuscado().setText(usuario.getDireccion());
        buscarUsuarioView.getTxtFormatedFechaCaducidadBuscado()
                .setText(usuario.getMembresia().getFechaVencimiento().format(formato));
        buscarUsuarioView.getTxtMembresiaBuscado().setText(usuario.getMembresia().getTipoMembresia());

      
    }

    private void listarUsuario() {
        listarUsuarioView.cargarDatos(usuarioDAO.listar());
    }

    public void cambioIdioma(ResourceBundle bundle) {
        cambioIdiomaActualizarUsuario(bundle);
        cambioIdiomaBuscarUsuario(bundle);
        cambioIdiomaEliminarUsuario(bundle);
        cambioIdiomaCrearUsuario(bundle);
        cambioIdiomaListarUsuario(bundle);
        mensajes = bundle.getString("mensajesUsuario").split(",");
        

    }

    private void cambioIdiomaCrearUsuario(ResourceBundle bundle) {
        // ===== BOTONES =====
        crearUsuarioView.getBtnAceptar().setText(bundle.getString("btnAceptar"));

// ===== COMBO BOX =====
        crearUsuarioView.getComboBoxStringsMembresia().removeAllItems();
        for (String membresia : bundle.getString("comboBoxMembresia").split(",")) {
            crearUsuarioView.getComboBoxStringsMembresia().addItem(membresia.trim());
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

        actualizarUsuarioView.getComboBoxStringsMembresia().removeAllItems();
        String[] membresias = bundle.getString("comboBoxMembresia").split(",");
        for (String membresia : membresias) {
            actualizarUsuarioView.getComboBoxStringsMembresia().addItem(membresia.trim());
        }

    }

    private void cambioIdiomaListarUsuario(ResourceBundle bundle) {

        listarUsuarioView.getBtnListarUsuario().setText(bundle.getString("btnListarUsuario"));
        configurarTabla(bundle);
    }

    private void configurarTabla(ResourceBundle bundle) {
        String[] columnas = bundle.getString("columnasLibro").split(",");
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

    
}
