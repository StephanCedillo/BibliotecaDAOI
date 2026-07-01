/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ups.edu.ec.bibleotecainterfaz.controller;

/**
 *
 * @author stephancedillo
 */
public class UserController {

    private ActualizarUsuarioView actualizarUsuarioView;
    private BuscarUsuarioView buscarUsuarioView;
    private EliminarUsuarioView eliminarUsuarioView;
    private CrearUsuarioView crearUsuarioView;
    
    public UserController(ActualizarUsuarioView actualizarUsuarioView, BuscarUsuarioView buscarUsuarioView, EliminarUsuarioView eliminarUsuarioView, CrearUsuarioView crearUsuarioView;) {
        this.actualizarUsuarioView = actualizarUsuarioView;
        this.buscarUsuarioView= buscarUsuarioView;
        this.eliminarUsuarioView = eliminarUsuarioView;
        this.crearUsuarioView = crearUsuarioView;
        configurarEventos();
        cambioIdioma();
    }

    private void configurarEventos() {
        configurarEventosActualizarUsuario();
        configurarEventosBuscarUsuario();
        configurarEventosEliminarUsuario();
        configurarEventosCrearUsuario();

    }

    private void configurarEventosActualizarUsuario() {
        actualizarUsuarioView.getBtnBuscar().addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarAcrUsuario();
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
                 buscarElirUsuario();
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

    
    private void buscarAcrUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void actualizarUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void buscarElirUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void eliminarUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void crearUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void buscarUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void cambioIdioma() {
        cambioIdiomaActualizarUsuario();
        cambioIdiomaBuscarUsuario();
        cambioIdiomaEliminarUsuario();
        cambioIdiomaCrearUsuario();
    }

    private void cambioIdiomaCrearUsuario() {

    }

    private void cambioIdiomaEliminarUsuario() {

    }

    private void cambioIdiomaBuscarUsuario() {

    }

    private void cambioIdiomaActualizarUsuario() {

    }

    
}
