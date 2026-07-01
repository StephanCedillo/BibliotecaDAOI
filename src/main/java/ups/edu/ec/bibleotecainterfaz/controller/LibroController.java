/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ups.edu.ec.bibleotecainterfaz.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ups.edu.ec.bibleotecainterfaz.dao.LibroDAO;
import ups.edu.ec.bibleotecainterfaz.models.Autor;
import ups.edu.ec.bibleotecainterfaz.models.Libro;
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
            EliminarLibroView eliminarLibroView, CrearLibroView crearLibroView, ListarLibroView listarLibroView,
            LibroDAO libroDAO) {
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
        crearLibroView.getBtnCrearAutor().addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        crearAutor();
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

    private void actualizarAutores() {
        crearLibroView.getComboBoxAutores().removeAllItems();
        actualizarLibroView.getComboBoxAutores().removeAllItems();
        if (libroDAO.listarAutores().size() == 0) {
            return;
        }
        for (Autor autor : libroDAO.listarAutores()) {
            crearLibroView.getComboBoxAutores().addItem(autor);
            actualizarLibroView.getComboBoxAutores().addItem(autor);
        }

    }

    private void buscarActLibro() {

        Libro libro = libroDAO.buscar(actualizarLibroView.getTxtISBN().getText());

        if (libro == null) {
            System.out.println("Libro no encontrado");
            return;
        }
        // =======CAMBIOS EN TODAS LAS VARIABLES ===========

        
        actualizarLibroView.getLblTituloBuscado().setText(libro.getNombre());
        actualizarLibroView.getTxtISBNBuscado().setText(libro.getISBN());
        actualizarLibroView.getComboBoxAutores().setSelectedItem(libro.getAutor());
        actualizarLibroView.getRadioButtonRestriccion().setSelected(libro.isSirestriccionEdad());
        actualizarLibroView.getTxtIdiomaBuscado().setText(libro.getIdioma());
        actualizarLibroView.getTxtGeneroBuscado().setText(libro.getGenero());
        actualizarLibroView.getTxtNumeroPaginas().setText(String.valueOf(libro.getNumeroPaginas()));

        if (libro.estaDisponible()) {
            actualizarLibroView.getPnlEstado().setBackground(new Color(0, 255, 0));
        } else {
            actualizarLibroView.getPnlEstado().setBackground(new Color(255, 51, 51));
        }

    }

    private void actualizarLibro() {
        Libro libro = libroDAO.buscar(actualizarLibroView.getTxtISBN().getText());

        if (libro == null) {
            System.out.println("Libro no encontrado");
            return;
        }

        libro.setNombre(actualizarLibroView.getTxtTituloBuscado().getText());
        libro.setGenero(actualizarLibroView.getTxtGeneroBuscado().getText());
        libro.setAutor((Autor) actualizarLibroView.getComboBoxAutores().getSelectedItem());
        libro.setSirestriccionEdad(actualizarLibroView.getRadioButtonRestriccion().isSelected());
        libro.setNumeroPaginas(Integer.parseInt(actualizarLibroView.getTxtNumeroPaginas().getText()));
        libro.setIdioma(actualizarLibroView.getTxtIdiomaBuscado().getText());

        if (libroDAO.actualizar(libro)) {
            System.out.println("Actualizado");
        } else {
            System.out.println("No se pudo actualizar");
        }
    }

    private void buscarElimLibro() {
        Libro libro = libroDAO.buscar(eliminarLibroView.getTxtISBN().getText());

        if (libro == null) {
            System.out.println("Libro no encontrado");
            return;
        }

        // =======CAMBIOS EN TODAS LAS VARIABLES ===========

        eliminarLibroView.getLblTituloBuscado().setText(libro.getNombre());
        eliminarLibroView.getTxtISBNBuscado().setText(libro.getISBN());
        eliminarLibroView.getTxtAutorBuscado().setText(libro.getAutor().toString());

        eliminarLibroView.getTxtRestriccionEdadBuscada()
                .setText(libro.isSirestriccionEdad() ? restriccionEdad : restriccionEdadNo);
        eliminarLibroView.getTxtIdiomaBuscado().setText(libro.getIdioma());
        eliminarLibroView.getTxtGeneroBuscado().setText(libro.getGenero());
        eliminarLibroView.getTxtNumeroPaginas().setText(String.valueOf(libro.getNumeroPaginas()));

        if (libro.estaDisponible()) {
            eliminarLibroView.getPnlEstado().setBackground(new Color(0, 255, 0));
        } else {
            eliminarLibroView.getPnlEstado().setBackground(new Color(255, 51, 51));
        }
    }


    /// ================ HACER ALFONSO ================
    private void eliminarLibro() {
        Libro libro = libroDAO.buscar(eliminarLibroView.getTxtISBN().getText());

        if (libro == null) {
            System.out.println("Libro no encontrado");
            return;
        }
        libroDAO.eliminar(libro.getISBN());
        // IMPLEMENTAR ELIMINACION DE LIBRO EN LA VISTA
        // PARA QUE EL USUARIO ESTE SEGURO SI QUIERE ELIMINAR EL LIBRO
    }

    private void crearLibro() {
        libroDAO.crear(new Libro(crearLibroView.getTxtISBN().getText(),
                (Autor) crearLibroView.getComboBoxAutores().getSelectedItem(),
                crearLibroView.getTxtNombre().getText(),
                crearLibroView.getTxtGenero().getText(),
                crearLibroView.getRadioButtonRestriccion().isSelected(),
                Integer.parseInt(crearLibroView.getTxtNumeroPaginas().getText()),
                crearLibroView.getTxtIdioma().getText(),
                false));

        System.out.println("Creado");
    }

    private String restriccionEdad = "Si posee";
    private String restriccionEdadNo = "No posee";

    private void buscarLibro() {
        Libro libro = libroDAO.buscar(buscarLibroView.getTxtISBN().getText());

        if (libro == null) {
            System.out.println("Libro no encontrado");
            return;
        }
        // =======CAMBIOS EN TODAS LAS VARIABLES ===========

        buscarLibroView.getLblTituloBuscado().setText(libro.getNombre());
        buscarLibroView.getTxtISBNBuscado().setText(libro.getISBN());
        buscarLibroView.getTxtAutorBuscado().setText(libro.getAutor().toString());

        buscarLibroView.getTxtRestriccionEdadBuscada()
                .setText(libro.isSirestriccionEdad() ? restriccionEdad : restriccionEdadNo);
        buscarLibroView.getTxtIdiomaBuscado().setText(libro.getIdioma());
        buscarLibroView.getTxtGeneroBuscado().setText(libro.getGenero());
        buscarLibroView.getTxtNumeroPaginas().setText(String.valueOf(libro.getNumeroPaginas()));

        if (libro.estaDisponible()) {
            buscarLibroView.getPnlEstado().setBackground(new Color(0, 255, 0));
        } else {
            buscarLibroView.getPnlEstado().setBackground(new Color(255, 51, 51));
        }

    }

    private void listarLibro() {

    }

    private void crearAutor() {
        libroDAO.crearAutor(
                new Autor(crearLibroView.getTxtNombreAutor().getText(),
                        crearLibroView.getTxtApellido().getText()));
        actualizarAutores();
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
