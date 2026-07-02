/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ups.edu.ec.bibleotecainterfaz.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;
import ups.edu.ec.bibleotecainterfaz.view.*;
import ups.edu.ec.bibleotecainterfaz.controller.*;
import ups.edu.ec.bibleotecainterfaz.dao.*;

/**
 *
 * @author stephancedillo
 */
public class PrincipalController {

    // =======PANTALLA PRINCIPAL========
    private PrincipalView principalView;

    // ======= LIBRO =========
    private ActualizarLibroView actualizarLibroView;
    private BuscarLibroView buscarLibroView;
    private EliminarLibroView eliminarLibroView;
    private CrearLibroView crearLibroView;
    private ListarLibroView listarLibroView;

    // ======= USUARIO =========
    private ActualizarUsuarioView actualizarUsuarioView;
    private BuscarUsuarioView buscarUsuarioView;
    private EliminarUsuarioView eliminarUsuarioView;
    private CrearUsuarioView crearUsuarioView;
    private ListarUsuarioView listarUsuarioView;

    // ======= PRESTAMO =========
    private DevolucionPrestamoView devolucionPrestamoView;
    private BuscarPrestamoView buscarPrestamoiew;
    private CrearPrestamoView crearPrestamoView;
    private ListarPrestamoView listarPrestamoView;

    // ======= CONTROLLERS LOGICA SEPARADA =========
    private LibroController libroController;
    private PrestamoController prestamoController;
    private UserController userController;

    // ======= DAO LOGICA SEPARADA =========
    private LibroDAO libroDAO;
    private UsuarioDAO usuarioDAO;
    private PrestamoDAO prestamoDAO;

    // Cambiar Constructor cuando el Alfonso acabe
    public PrincipalController(PrincipalView principalView, ActualizarLibroView actualizarLibroView,
            BuscarLibroView buscarLibroView, EliminarLibroView eliminarLibroView, CrearLibroView crearLibroView,
            ListarLibroView listarLibroView, ActualizarUsuarioView actualizarUsuarioView,
            BuscarUsuarioView buscarUsuarioView, EliminarUsuarioView eliminarUsuarioView,
            CrearUsuarioView crearUsuarioView, ListarUsuarioView listarUsuarioView,
            DevolucionPrestamoView devolucionPrestamoView, BuscarPrestamoView buscarPrestamoiew,
            CrearPrestamoView crearPrestamoView, ListarPrestamoView listarPrestamoView,
            LibroController libroController, PrestamoController prestamoController, UserController userController,
            LibroDAO libroDAO, UsuarioDAO usuarioDAO, PrestamoDAO prestamoDAO) {
        this.principalView = principalView;
        this.actualizarLibroView = actualizarLibroView;
        this.buscarLibroView = buscarLibroView;
        this.eliminarLibroView = eliminarLibroView;
        this.crearLibroView = crearLibroView;
        this.listarLibroView = listarLibroView;
        this.actualizarUsuarioView = actualizarUsuarioView;
        this.buscarUsuarioView = buscarUsuarioView;
        this.eliminarUsuarioView = eliminarUsuarioView;
        this.crearUsuarioView = crearUsuarioView;
        this.listarUsuarioView = listarUsuarioView;
        this.devolucionPrestamoView = devolucionPrestamoView;
        this.buscarPrestamoiew = buscarPrestamoiew;
        this.crearPrestamoView = crearPrestamoView;
        this.listarPrestamoView = listarPrestamoView;
        this.libroController = libroController;
        this.prestamoController = prestamoController;
        this.userController = userController;
        this.libroDAO = libroDAO;
        this.usuarioDAO = usuarioDAO;
        this.prestamoDAO = prestamoDAO;
        inicializarControllers();
        configurarEvento();
    }

    private final static Locale espanol = new Locale("es", "EC");
    private final static Locale ingles = new Locale("en", "US");
    private final static Locale ruso = new Locale("ru", "RU");
    private final static Locale aleman = new Locale("de", "DE");

    private void configurarEvento() {

        principalView.getMenuItemEspanol().addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarIdioma(espanol);
            }
        });
        principalView.getMenuItemIngles().addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarIdioma(ingles);
            }
        });
        
        principalView.getMenuItemRuso().addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarIdioma(ruso);
            }
        });
        principalView.getMenuItemAleman().addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarIdioma(aleman);
            }
        });

    }

    public void cambiarIdioma(Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle("i18n.mensajes", locale);
        cambiarIdiomaLocal(bundle);
        libroController.cambioIdioma(bundle);
        userController.cambioIdioma(bundle);
        prestamoController.cambioIdioma(bundle);
    }

    public void cambiarIdiomaLocal(ResourceBundle bundle) {
        // ===== MENUS =====
        principalView.getMenuLibro().setText(bundle.getString("menuLibro"));
        principalView.getMenuUsuarios().setText(bundle.getString("menuUsuarios"));
        principalView.getMenuRegistro().setText(bundle.getString("menuRegistro"));
        principalView.getMenuConfiguracion().setText(bundle.getString("menuConfiguracion"));

// ===== OPCIONES LIBROS =====
        principalView.getBtnCrearLibro().setText(bundle.getString("btnCrearLibro"));
        principalView.getBtnActualizarLibro().setText(bundle.getString("btnActualizarLibro"));
        principalView.getBtnEliminarLibro().setText(bundle.getString("btnEliminarLibro"));
        principalView.getBtnBuscarLibro().setText(bundle.getString("btnBuscarLibro"));
        principalView.getBtnListarLibro().setText(bundle.getString("btnListarLibro"));

// ===== OPCIONES USUARIOS =====
        principalView.getBtnCrearUsuario().setText(bundle.getString("btnCrearUsuario"));
        principalView.getBtnActualizarUsuario().setText(bundle.getString("btnActualizarUsuario"));
        principalView.getBtnEliminarUsuario().setText(bundle.getString("btnEliminarUsuario"));
        principalView.getBtnBuscarUsuario().setText(bundle.getString("btnBuscarUsuario"));
        principalView.getBtnListarUsuario().setText(bundle.getString("btnListarUsuario"));

// ===== OPCIONES PRÉSTAMOS =====
        principalView.getBtnCrearPrestamo().setText(bundle.getString("btnCrearPrestamo"));
        principalView.getBtnBuscarPrestamo().setText(bundle.getString("btnBuscarPrestamo"));
        principalView.getBtnListarPrestamo().setText(bundle.getString("btnListarPrestamo"));
        principalView.getBtnDevolucion().setText(bundle.getString("btnDevolucion"));

// ===== IDIOMAS =====
        principalView.getMenuItemEspanol().setText(bundle.getString("menuItemEspanol"));
        principalView.getMenuItemIngles().setText(bundle.getString("menuItemIngles"));
        principalView.getMenuItemAleman().setText(bundle.getString("menuItemAleman"));
        principalView.getMenuItemRuso().setText(bundle.getString("menuItemRuso"));

    }

    private void inicializarControllers() {
        libroController = new LibroController(actualizarLibroView, buscarLibroView, eliminarLibroView, crearLibroView,
                listarLibroView, libroDAO);

        userController = new UserController(actualizarUsuarioView, buscarUsuarioView, eliminarUsuarioView,
                crearUsuarioView, listarUsuarioView, usuarioDAO);
        prestamoController = new PrestamoController(devolucionPrestamoView, buscarPrestamoiew, crearPrestamoView,
                listarPrestamoView, prestamoDAO, usuarioDAO, libroDAO);
    }

}
