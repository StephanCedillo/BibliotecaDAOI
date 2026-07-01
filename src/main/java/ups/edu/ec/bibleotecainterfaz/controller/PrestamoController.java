/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ups.edu.ec.bibleotecainterfaz.controller;

import ups.edu.ec.bibleotecainterfaz.view.*;;

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

    }

    private void configurarEventosListarPrestamo() {

    }

    private void configurarEventosCrearPrestamo() {

    }

    private void configurarEventosBuscarPrestamo() {


    }

    private void buscarAcrPrestamo() {

    }

    private void DevolucionPrestamo() {

    }

    private void buscarElirPrestamo() {

    }

    private void ListarPrestamo() {

    }

    private void crearPrestamo() {

    }

    private void buscarPrestamo() {

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
