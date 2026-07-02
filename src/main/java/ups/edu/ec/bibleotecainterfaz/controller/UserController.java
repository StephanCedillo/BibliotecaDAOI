/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ups.edu.ec.bibleotecainterfaz.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
        cambioIdioma();
        usuarioDAO.crearListadoTemporal(20);
        System.out.println(usuarioDAO.listar());
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
            System.out.println("Usuario no encontrado");
            return;
        }

        actualizarUsuarioView.getTxtNombreBuscado().setText(usuario.getNombre());
        actualizarUsuarioView.getTxtApellidoBuscado().setText(usuario.getApellido());
        actualizarUsuarioView.getTxtEmailBuscado().setText(usuario.getEmail());
        actualizarUsuarioView.getTxtEdadBuscado().setText(String.valueOf(usuario.getEdad()));
        actualizarUsuarioView.getTxtDireccionBuscado().setText(usuario.getDireccion());

        actualizarUsuarioView.getComboBoxStringsMembresia().setSelectedItem(usuario.getMembresia().getTipoMembresia());

        if (usuario.isTieneDiscapacidad()) {
            actualizarUsuarioView.getPnlEstadoDiscapacidadBuscado().setVisible(true);
        } else {
            actualizarUsuarioView.getPnlEstadoDiscapacidadBuscado().setVisible(false);
        }

    }

    private void actualizarUsuario() {
        Usuario usuario = usuarioDAO.buscar(actualizarUsuarioView.getTxtCedula().getText());

        if (usuario == null) {
            System.out.println("Usuario no encontrado");
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

    }

    private void buscarEliminarUsuario() {
        Usuario usuario = usuarioDAO.buscar(eliminarUsuarioView.getTxtCedula().getText());

        if (usuario == null) {
            System.out.println("Usuario no encontrado");
            return;
        }

        eliminarUsuarioView.getLblNombreBuscado().setText(usuario.getNombre() + " " + usuario.getApellido());
        eliminarUsuarioView.getTxtEmailBuscado().setText(usuario.getEmail());
        eliminarUsuarioView.getTxtEdadBuscado().setText(String.valueOf(usuario.getEdad()));
        eliminarUsuarioView.getTxtDireccionBuscado().setText(usuario.getDireccion());
        eliminarUsuarioView.getTxtFormatedFechaCaducidadBuscado()
                .setText(usuario.getMembresia().getFechaVencimiento().format(formato));
        eliminarUsuarioView.getTxtMembresiaBuscado().setText(usuario.getMembresia().getTipoMembresia());

        if (usuario.isTieneDiscapacidad()) {
            eliminarUsuarioView.getPnlEstadoDiscapacidadBuscado().setVisible(true);
        } else {
            eliminarUsuarioView.getPnlEstadoDiscapacidadBuscado().setVisible(false);
        }

    }

    private void eliminarUsuario() {
        Usuario usuario = usuarioDAO.buscar(eliminarUsuarioView.getTxtCedula().getText());

        if (usuario == null) {
            System.out.println("Usuario no encontrado");
            return;
        }
        usuarioDAO.eliminar(usuario.getCedula());
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
                crearUsuarioView.getRadioButtonDiscapacidad().isSelected()
        );
        u.agregarMembresia(crearUsuarioView.getComboBoxStringsMembresia().getSelectedItem().toString());
        usuarioDAO.crear(u);

        System.out.println("Creado");
    }

    private void buscarUsuario() {
        Usuario usuario = usuarioDAO.buscar(buscarUsuarioView.getTxtCedula().getText());

        if (usuario == null) {
            System.out.println("Usuario no encontrado");
            return;
        }

        buscarUsuarioView.getLblTituloBuscado().setText(usuario.getNombre());
        buscarUsuarioView.getTxtEmailBuscado().setText(usuario.getEmail());
        buscarUsuarioView.getTxtEdadBuscado().setText(String.valueOf(usuario.getEdad()));
        buscarUsuarioView.getTxtDireccionBuscado().setText(usuario.getDireccion());
        buscarUsuarioView.getTxtFormatedFechaCaducidadBuscado()
                .setText(usuario.getMembresia().getFechaVencimiento().format(formato));
        buscarUsuarioView.getTxtMembresiaBuscado().setText(usuario.getMembresia().getTipoMembresia());

        if (usuario.isTieneDiscapacidad()) {
            buscarUsuarioView.getPnlEstadoDiscapacidadBuscado().setVisible(true);
        } else {
            buscarUsuarioView.getPnlEstadoDiscapacidadBuscado().setVisible(false);
        }

    }

    private void listarUsuario() {
        listarUsuarioView.cargarDatos(usuarioDAO.listar());
    }

    private void cambioIdioma() {
        cambioIdiomaActualizarUsuario();
        cambioIdiomaBuscarUsuario();
        cambioIdiomaEliminarUsuario();
        cambioIdiomaCrearUsuario();
        cambioIdiomaListarUsuario();

    }

    private void cambioIdiomaCrearUsuario() {

    }

    private void cambioIdiomaEliminarUsuario() {

    }

    private void cambioIdiomaBuscarUsuario() {

    }

    private void cambioIdiomaActualizarUsuario() {

    }

    private void cambioIdiomaListarUsuario() {

    }

}
