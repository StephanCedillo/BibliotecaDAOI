/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ups.edu.ec.bibleotecainterfaz.controller;

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


    private void configurarEvento() {
       
    }


    private void inicializarControllers() {
        libroController = new LibroController(actualizarLibroView, buscarLibroView, eliminarLibroView, crearLibroView,
                listarLibroView);

        userController = new UserController(actualizarUsuarioView, buscarUsuarioView, eliminarUsuarioView,
                crearUsuarioView, listarUsuarioView);
        prestamoController = new PrestamoController(devolucionPrestamoView, buscarPrestamoiew, crearPrestamoView,
                listarPrestamoView);
    }


}
