/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ups.edu.ec.bibleotecainterfaz.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ups.edu.ec.bibleotecainterfaz.view.*;

/**
 *
 * @author stephancedillo
 */
public class UserController {

    private ActualizarUsuarioView actualizarUsuarioView;
    private BuscarUsuarioView buscarUsuarioView;
    private EliminarUsuarioView eliminarUsuarioView;
    private CrearUsuarioView crearUsuarioView;
    private ListarUsuarioView listarUsuarioView;

    public UserController(ActualizarUsuarioView actualizarUsuarioView, BuscarUsuarioView buscarUsuarioView,
            EliminarUsuarioView eliminarUsuarioView, CrearUsuarioView crearUsuarioView,
            ListarUsuarioView listarUsuarioView) {
        this.actualizarUsuarioView = actualizarUsuarioView;
        this.buscarUsuarioView = buscarUsuarioView;
        this.eliminarUsuarioView = eliminarUsuarioView;
        this.crearUsuarioView = crearUsuarioView;
        this.listarUsuarioView = listarUsuarioView;
        configurarEventos();
        cambioIdioma();
    }

    private void configurarEventos() {
        configurarEventosActualizarUsuario();
        configurarEventosBuscarUsuario();
        configurarEventosEliminarUsuario();
        configurarEventosCrearUsuario();
        configurarEventosListarUsuario();
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

    private void configurarEventosListarUsuario() {
        listarUsuario();
    }

    private void buscarAcrUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void actualizarUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void buscarElirUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void eliminarUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void crearUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void buscarUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void listarUsuario() {

    }

    private void cambioIdioma() {
        cambioIdiomaActualizarUsuario();
        cambioIdiomaBuscarUsuario();
        cambioIdiomaEliminarUsuario();
        cambioIdiomaCrearUsuario();
        cambioIdiomaListarUsuario();

    }

    private void cambioIdiomaCrearUsuario() {

    }

    private void cambioIdiomaEliminarUsuario() {

    }

    private void cambioIdiomaBuscarUsuario() {

    }

    private void cambioIdiomaActualizarUsuario() {

    }

    private void cambioIdiomaListarUsuario() {

    }

}
