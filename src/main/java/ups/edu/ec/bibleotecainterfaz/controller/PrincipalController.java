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

    //=======PANTALLA PRINCIPAL========
    private PrincipalView principalView;

    //======= LIBRO =========
    private CrearLibroView crearLibroView;
    private ActualizarLibroView actualizarLibroView;
    private EliminarLibroView eliminarLibroView;
    private BuscarLibroView buscarLibroView;

    //======= USUARIO =========
    private CrearUsuarioView crearUsuarioView;
    private ActualizarUsuarioView actualizarUsuarioView;
    private EliminarUsuarioView eliminarUsuarioView;
    private BuscarUsuarioView buscarUsuarioView;

    //======= PRESTAMO =========
    private CrearPrestamoView crearPrestamoView;
    private BuscarPrestamoView buscarPrestamoView;

    //======= CONTROLLERS LOGICA SEPARADA =========
    private LibroController libroController;
    private PrestamoController prestamoController;
    private UserController userController;

    //Cambiar Constructor cuando el Alfonso acabe 
    
    public PrincipalController(PrincipalView principalView, CrearLibroView crearLibroView, ActualizarLibroView actualizarLibroView, EliminarLibroView eliminarLibroView, BuscarLibroView buscarLibroView, CrearUsuarioView crearUsuarioView, ActualizarUsuarioView actualizarUsuarioView, EliminarUsuarioView eliminarUsuarioView, BuscarUsuarioView buscarUsuarioView, CrearPrestamoView crearPrestamoView, BuscarPrestamoView buscarPrestamoView) {
        this.principalView = principalView;
        this.crearLibroView = crearLibroView;
        this.actualizarLibroView = actualizarLibroView;
        this.eliminarLibroView = eliminarLibroView;
        this.buscarLibroView = buscarLibroView;
        this.crearUsuarioView = crearUsuarioView;
        this.actualizarUsuarioView = actualizarUsuarioView;
        this.eliminarUsuarioView = eliminarUsuarioView;
        this.buscarUsuarioView = buscarUsuarioView;
        this.crearPrestamoView = crearPrestamoView;
        this.buscarPrestamoView = buscarPrestamoView;
        inicializarControllers();
    }

    private void inicializarControllers() {
        libroController = new LibroController(actualizarLibroView, buscarLibroView, eliminarLibroView, crearLibroView);
        //INICIALIZAR PRESTAMO Y USUARIO
    }

}
