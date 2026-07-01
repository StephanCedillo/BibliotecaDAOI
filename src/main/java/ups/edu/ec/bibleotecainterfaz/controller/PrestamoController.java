/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ups.edu.ec.bibleotecainterfaz.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ups.edu.ec.bibleotecainterfaz.view.*;

;

/**
 *
 * @author stephancedillo
 */
public class PrestamoController {

    private DevolucionPrestamoView devolucionPrestamoView;
    private BuscarPrestamoView buscarPrestamoView;
    private CrearPrestamoView crearPrestamoView;
    private ListarPrestamoView listarPrestamoView;

    public PrestamoController(DevolucionPrestamoView devolucionPrestamoView, BuscarPrestamoView buscarPrestamoiew,
            CrearPrestamoView crearPrestamoView, ListarPrestamoView listarPrestamoView) {
        this.devolucionPrestamoView = devolucionPrestamoView;
        this.buscarPrestamoView = buscarPrestamoiew;
        this.crearPrestamoView = crearPrestamoView;
        this.listarPrestamoView = listarPrestamoView;
        configurarEventos();
        cambioIdioma();
    }

    private void configurarEventos() {
        configurarEventosDevolucionPrestamo();
        configurarEventosBuscarPrestamo();
        configurarEventosListarPrestamo();
        configurarEventosCrearPrestamo();

    }

    private void configurarEventosDevolucionPrestamo() {
         devolucionPrestamoView.getBtnCedula().addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarCedulaPrestamo();
            }
        });
        devolucionPrestamoView.getBtnID().addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarIDPrestamo();
            }
        });
        devolucionPrestamoView.getBtnISBN().addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarISBNPrestamo();
            }
        });
        
        devolucionPrestamoView.getBtnDevolucion().addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 devolucion();
            }
        });
       
               
    }

    private void configurarEventosListarPrestamo() {

    }

    private void configurarEventosCrearPrestamo() {
        crearPrestamoView.getBtnAceptar().addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               crearPrestamo();
            }
        });
        crearPrestamoView.getBtnIngresarOtroLibro().addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ingresarOtroLibro();
            }
        });
        

    }

    private void configurarEventosBuscarPrestamo() {
        buscarPrestamoView.getBtnCedula().addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarCedulaPrestamo();
            }
        });
        buscarPrestamoView.getBtnID().addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarIDPrestamo();
            }
        });
        buscarPrestamoView.getBtnISBN().addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarISBNPrestamo();
            }
        });
        

    }

 
    private void buscarCedulaPrestamo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void buscarIDPrestamo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void buscarISBNPrestamo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void ingresarOtroLibro() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void devolucion() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void crearPrestamo() {

    }


    private void cambioIdioma() {
        cambioIdiomaDevolucionPrestamo();
        cambioIdiomaBuscarPrestamo();
        cambioIdiomaListarPrestamo();
        cambioIdiomaCrearPrestamo();
    }

    private void cambioIdiomaCrearPrestamo() {

    }

    private void cambioIdiomaListarPrestamo() {

    }

    private void cambioIdiomaBuscarPrestamo() {

    }

    private void cambioIdiomaDevolucionPrestamo() {

    }


}
