/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ups.edu.ec.bibleotecainterfaz.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
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

     // ======= AUTOR =========

    private CrearAutorView crearAutorView;
    private BuscarAutorView buscarAutorView;
    private EliminarAutorView eliminarAutorView;
    private ListarAutorView listarAutorView;
    private ActualizarAutorView actualizarAutorView;

    // ======= CONTROLLERS LOGICA SEPARADA =========
    private LibroController libroController;
    private PrestamoController prestamoController;
    private UserController userController;
     private AutorController autorController;

    // ======= DAO LOGICA SEPARADA =========
    private LibroDAO libroDAO;
    private UsuarioDAO usuarioDAO;
    private PrestamoDAO prestamoDAO;
    private AutorDAO autorDAO;

   
     
    public PrincipalController(PrincipalView principalView, ActualizarLibroView actualizarLibroView,
            BuscarLibroView buscarLibroView, EliminarLibroView eliminarLibroView, CrearLibroView crearLibroView,
            ListarLibroView listarLibroView, ActualizarUsuarioView actualizarUsuarioView,
            BuscarUsuarioView buscarUsuarioView, EliminarUsuarioView eliminarUsuarioView,
            CrearUsuarioView crearUsuarioView, ListarUsuarioView listarUsuarioView,
            DevolucionPrestamoView devolucionPrestamoView, BuscarPrestamoView buscarPrestamoiew,
            CrearPrestamoView crearPrestamoView, ListarPrestamoView listarPrestamoView, CrearAutorView crearAutorView,
            BuscarAutorView buscarAutorView, EliminarAutorView eliminarAutorView, ListarAutorView listarAutorView,
            ActualizarAutorView actualizarAutorView, LibroController libroController,
            PrestamoController prestamoController, UserController userController, AutorController autorController,
            LibroDAO libroDAO, UsuarioDAO usuarioDAO, PrestamoDAO prestamoDAO, AutorDAO autorDAO) {
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
        this.crearAutorView = crearAutorView;
        this.buscarAutorView = buscarAutorView;
        this.eliminarAutorView = eliminarAutorView;
        this.listarAutorView = listarAutorView;
        this.actualizarAutorView = actualizarAutorView;
        this.libroController = libroController;
        this.prestamoController = prestamoController;
        this.userController = userController;
        this.autorController = autorController;
        this.libroDAO = libroDAO;
        this.usuarioDAO = usuarioDAO;
        this.prestamoDAO = prestamoDAO;
        this.autorDAO = autorDAO;
         configurarEvento();
   
       
    }



    private final static Locale espanol = new Locale("es", "EC");
    private final static Locale ingles = new Locale("en", "US");
    private final static Locale ruso = new Locale("ru", "RU");
    private final static Locale aleman = new Locale("de", "DE");

    private void configurarEvento() {

        formatoGuardado();
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

    private void formatoGuardado() {
        // Se crea como JDialog 
        JDialog contenedor = new JDialog(principalView, "Seleccione tipo de almacenamiento", true);
        contenedor.setSize(554, 295);
        contenedor.setLocationRelativeTo(principalView);
        contenedor.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        PreguntaGuardado pregunta = new PreguntaGuardado();
        contenedor.add(pregunta);

        pregunta.getBtnArchivo().addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formatearArchivo();

                contenedor.dispose();
            }
        });

        pregunta.getBtnMemoria().addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                formatearMemoria();

                contenedor.dispose();
            }
        });

        contenedor.setVisible(true);
    }

    private void formatearMemoria() {
        libroDAO = new LibroDAOMemoria();
        usuarioDAO = new UsuarioDAOMemoria();
        prestamoDAO = new PrestamoDAOMemoria();
        autorDAO = new AutorDAOMemoria();
          libroController = new LibroController(actualizarLibroView, buscarLibroView, eliminarLibroView, crearLibroView,
                listarLibroView, libroDAO,autorDAO);

        userController = new UserController(actualizarUsuarioView, buscarUsuarioView, eliminarUsuarioView,
                crearUsuarioView, listarUsuarioView, usuarioDAO);
        prestamoController = new PrestamoController(devolucionPrestamoView, buscarPrestamoiew, crearPrestamoView,
                listarPrestamoView, prestamoDAO, usuarioDAO, libroDAO);
        autorController = new AutorController(
            crearAutorView, actualizarAutorView, eliminarAutorView, buscarAutorView, listarAutorView, 
            autorDAO, libroController);
         libroController.setLibroDAO(libroDAO);
    userController.setUsuarioDAO(usuarioDAO);
    prestamoController.setPrestamoDAO(prestamoDAO);
    autorController.setAutorDAO(autorDAO);
    libroController.setAutorDAO(autorDAO);
   
        
    }
private void formatearArchivo() {
    libroDAO = new LibroDAOArchivo();
    usuarioDAO = new UsuarioDAOArchivo();
    prestamoDAO = new PrestamoDAOArchivo(usuarioDAO,libroDAO);
    autorDAO = new AutorDAOArchivo();
    
 libroController = new LibroController(actualizarLibroView, buscarLibroView, eliminarLibroView, crearLibroView,
                listarLibroView, libroDAO,autorDAO);

        userController = new UserController(actualizarUsuarioView, buscarUsuarioView, eliminarUsuarioView,
                crearUsuarioView, listarUsuarioView, usuarioDAO);
        prestamoController = new PrestamoController(devolucionPrestamoView, buscarPrestamoiew, crearPrestamoView,
                listarPrestamoView, prestamoDAO, usuarioDAO, libroDAO);
        autorController = new AutorController(
            crearAutorView, actualizarAutorView, eliminarAutorView, buscarAutorView, listarAutorView, 
            autorDAO, libroController);
    libroController.setAutorDAO(autorDAO);
    libroController.setLibroDAO(libroDAO);
    userController.setUsuarioDAO(usuarioDAO);
    prestamoController.setPrestamoDAO(prestamoDAO);
    autorController.setAutorDAO(autorDAO);
    
        
}
    

    

    public void cambiarIdioma(Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle("i18n.mensajes", locale);
        cambiarIdiomaLocal(bundle);
        libroController.cambioIdioma(bundle);
        userController.cambioIdioma(bundle);
        prestamoController.cambioIdioma(bundle);
        autorController.cambioIdioma(bundle);
    }

    public void cambiarIdiomaLocal(ResourceBundle bundle) {
        // ===== MENUS =====
        principalView.getMenuLibro().setText(bundle.getString("menuLibro"));
        principalView.getMenuUsuarios().setText(bundle.getString("menuUsuarios"));
        principalView.getMenuRegistro().setText(bundle.getString("menuRegistro"));
        principalView.getMenuConfiguracion().setText(bundle.getString("menuConfiguracion"));
        principalView.getMenuAutor().setText(bundle.getString("menuAutor"));
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
        
// ===== OPCIONES AUTOR =====
        principalView.getBtnCrearAutor().setText(bundle.getString("btnCrearAutor"));
        principalView.getBtnActualizarAutor().setText(bundle.getString("btnActualizarAutor"));
        principalView.getBtnEliminarAutor().setText(bundle.getString("btnEliminarAutor"));
        principalView.getBtnBuscarAutor().setText(bundle.getString("btnBuscarAutor"));
        principalView.getBtnListarAutor().setText(bundle.getString("btnListarAutor"));

// ===== IDIOMAS =====
        principalView.getMenuItemEspanol().setText(bundle.getString("menuItemEspanol"));
        principalView.getMenuItemIngles().setText(bundle.getString("menuItemIngles"));
        principalView.getMenuItemAleman().setText(bundle.getString("menuItemAleman"));
        principalView.getMenuItemRuso().setText(bundle.getString("menuItemRuso"));

    }



}
