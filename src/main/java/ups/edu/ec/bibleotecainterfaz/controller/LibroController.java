/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ups.edu.ec.bibleotecainterfaz.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ups.edu.ec.bibleotecainterfaz.view.ActualizarLibroView;
import ups.edu.ec.bibleotecainterfaz.view.BuscarLibroView;
import ups.edu.ec.bibleotecainterfaz.view.EliminarLibroView;
import ups.edu.ec.bibleotecainterfaz.view.CrearLibroView;

/**
 *
 * @author stephancedillo
 */
public class LibroController {

    private ActualizarLibroView actualizarLibroView;
    private BuscarLibroView buscarLibroView;
    private EliminarLibroView eliminarLibroView;
    private CrearLibroView crearLibroView;
   

    public LibroController(ActualizarLibroView actualizarLibroView, BuscarLibroView buscarLibroView, EliminarLibroView eliminarLibroView, CrearLibroView crearLibroView) {
        this.actualizarLibroView = actualizarLibroView;
        this.buscarLibroView = buscarLibroView;
        this.eliminarLibroView = eliminarLibroView;
        this.crearLibroView = crearLibroView;
        configurarEventos();
        cambioIdioma();
    }

    private void configurarEventos() {
        configurarEventosActualizarLibro();
        configurarEventosBuscarLibro();
        configurarEventosEliminarLibro();
        configurarEventosCrearLibro();

    }

    private void configurarEventosActualizarLibro() {
        actualizarLibroView.getBtnBuscar().addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarActLibro();
            }
        });
        actualizarLibroView.getBtnActualizacion().addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarLibro();
            }
        });
    }

    private void configurarEventosEliminarLibro() {

        eliminarLibroView.getBtnBuscar().addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 buscarElimLibro();
            }
        });
        eliminarLibroView.getBtnEliminar().addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 eliminarLibro();
            }
        });
 
    }

    private void configurarEventosCrearLibro() {
        crearLibroView.getBtnAceptar().addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 crearLibro();
            }
        });
        
       
    }

    private void configurarEventosBuscarLibro() {
          buscarLibroView.getBtnBuscar().addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 buscarLibro();
            }
        });    
    }

    
    private void buscarActLibro() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void actualizarLibro() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void buscarElimLibro() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void eliminarLibro() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void crearLibro() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void buscarLibro() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void cambioIdioma() {
        cambioIdiomaActualizarLibro();
        cambioIdiomaBuscarLibro();
        cambioIdiomaEliminarLibro();
        cambioIdiomaCrearLibro();
    }

    private void cambioIdiomaCrearLibro() {

    }

    private void cambioIdiomaEliminarLibro() {

    }

    private void cambioIdiomaBuscarLibro() {

    }

    private void cambioIdiomaActualizarLibro() {

    }

}
