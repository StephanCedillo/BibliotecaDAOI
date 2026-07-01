/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ups.edu.ec.bibleotecainterfaz.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ups.edu.ec.bibleotecainterfaz.dao.LibroDAO;
import ups.edu.ec.bibleotecainterfaz.models.Autor;
import ups.edu.ec.bibleotecainterfaz.view.ActualizarLibroView;
import ups.edu.ec.bibleotecainterfaz.view.BuscarLibroView;
import ups.edu.ec.bibleotecainterfaz.view.EliminarLibroView;
import ups.edu.ec.bibleotecainterfaz.view.ListarLibroView;
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
    private ListarLibroView listarLibroView;

    // =========DAO =========
    
    private LibroDAO libroDAO;
    
    
    public LibroController(ActualizarLibroView actualizarLibroView, BuscarLibroView buscarLibroView,
            EliminarLibroView eliminarLibroView, CrearLibroView crearLibroView, ListarLibroView listarLibroView
        ,LibroDAO libroDAO) {
        this.actualizarLibroView = actualizarLibroView;
        this.buscarLibroView = buscarLibroView;
        this.eliminarLibroView = eliminarLibroView;
        this.crearLibroView = crearLibroView;
        this.listarLibroView = listarLibroView;
        this.libroDAO = libroDAO;
        configurarEventos();
        cambioIdioma();
    }

    private void configurarEventos() {
        configurarEventosActualizarLibro();
        configurarEventosBuscarLibro();
        configurarEventosEliminarLibro();
        configurarEventosCrearLibro();
        configurarEventosListarLibro();

    }

    private void configurarEventosListarLibro() {
       // listarLibro();
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
        actualizarAutores();
       

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

     private void actualizarAutores(){
         if(libroDAO.listarAutores().size() == 0){
             return;
         }
         for (Autor autor : libroDAO.listarAutores()) {
            crearLibroView.getComboBoxAutores().addItem(autor);
        }
        crearLibroView.getComboBoxAutores().addItem(new Autor("-1", 0, "No Encontrado", null, null, false, false, null));   
    }
     
    private void buscarActLibro() {
      
    }

    private void actualizarLibro() {
    
    }

    private void buscarElimLibro() {
    
    }

    private void eliminarLibro() {
  
    }

    private void crearLibro() {
    
    }

    private void buscarLibro() {
  
    }
    private void listarLibro() {
  
    }

    private void cambioIdioma() {
        cambioIdiomaActualizarLibro();
        cambioIdiomaBuscarLibro();
        cambioIdiomaEliminarLibro();
        cambioIdiomaCrearLibro();
        cambioIdiomaListarLibro();
    }

    private void cambioIdiomaCrearLibro() {

    }

    private void cambioIdiomaEliminarLibro() {

    }

    private void cambioIdiomaBuscarLibro() {

    }

    private void cambioIdiomaActualizarLibro() {

    }

    private void cambioIdiomaListarLibro() {

    }

}
