/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ups.edu.ec.bibleotecainterfaz.controller;

/**
 *
 * @author stephancedillo
 */
public class PrestamoController {
    
    private ActualizarPrestamoiew actualizarPrestamoiew;
    private BuscarPrestamoiew buscarPrestamoiew;
    private EliminarPrestamoiew eliminarPrestamoiew;
    private CrearPrestamoiew crearPrestamoioView;

    public PrestamoController(ActualizarPrestamoView actualizarPrestamoView, BuscarPrestamoView buscarPrestamoView, EliminarPrestamoView eliminarPrestamoView, CrearPrestamoView crearPrestamoView;) {
        this.actualizarPrestamoView = actualizarPrestamoView;
        this.buscarPrestamoView= buscarPrestamoView;
        this.eliminarPrestamoView = eliminarPrestamoView;
        this.crearPrestamoView = crearPrestamoView;
        configurarEventos();
        cambioIdioma();
    }

     private void configurarEventos() {
        configurarEventosActualizarPrestamo();
        configurarEventosBuscarPrestamo();
        configurarEventosEliminarPrestamo();
        configurarEventosCrearPrestamo();

    }

    private void configurarEventosActualizarPrestamo() {
        actualizarPrestamoView.getBtnBuscar().addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarAcrPrestamo();
            }
        });
        actualizarPrestamoView.getBtnActualizacion().addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarPrestamo();
            }
        });
    }

    private void configurarEventosEliminarPrestamo() {

        eliminarPrestamoView.getBtnBuscar().addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 buscarElirPrestamo();
            }
        });
        eliminarPrestamoView.getBtnEliminar().addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 eliminarPrestamo();
            }
        });
 
    }

    private void configurarEventosCrearPrestamo() {
        crearPrestamoView.getBtnAceptar().addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 crearPrestamo();
            }
        });
        
       
    }

    private void configurarEventosBuscarPrestamo() {
          buscarPrestamoView.getBtnBuscar().addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 buscarPrestamo();
            }
        });    
    }

    
    private void buscarAcrPrestamo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void actualizarPrestamo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void buscarElirPrestamo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void eliminarPrestamo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void crearPrestamo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void buscarPrestamo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void cambioIdioma() {
        cambioIdiomaActualizarPrestamo();
        cambioIdiomaBuscarPrestamo();
        cambioIdiomaEliminarPrestamo();
        cambioIdiomaCrearPrestamo();
    }

    private void cambioIdiomaCrearPrestamo() {

    }

    private void cambioIdiomaEliminarPrestamo() {

    }

    private void cambioIdiomaBuscarPrestamo() {

    }

    private void cambioIdiomaActualizarPrestamo() {

    }


}
