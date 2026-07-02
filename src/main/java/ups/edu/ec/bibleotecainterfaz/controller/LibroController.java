/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ups.edu.ec.bibleotecainterfaz.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import javax.swing.table.DefaultTableModel;

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

        libroDAO.crearListadoTemporal(10);
        listarLibro();
    }

    private void configurarEventos() {
        configurarEventosActualizarLibro();
        configurarEventosBuscarLibro();
        configurarEventosEliminarLibro();
        configurarEventosCrearLibro();
        configurarEventosListarLibro();

    }

    private void configurarEventosListarLibro() {

        listarLibroView.getBtnListarLibro().addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarLibro();
            }
        });

        listarLibro();
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
            mostrarInformacion("Libro no encontrado", actualizarLibroView);
            return;
        }

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
            mostrarInformacion("Libro no encontrado", actualizarLibroView);
            return;
        }

        libro.setNombre(actualizarLibroView.getTxtTituloBuscado().getText());
        libro.setGenero(actualizarLibroView.getTxtGeneroBuscado().getText());
        libro.setAutor((Autor) actualizarLibroView.getComboBoxAutores().getSelectedItem());
        libro.setSirestriccionEdad(actualizarLibroView.getRadioButtonRestriccion().isSelected());
        libro.setNumeroPaginas(Integer.parseInt(actualizarLibroView.getTxtNumeroPaginas().getText()));
        libro.setIdioma(actualizarLibroView.getTxtIdiomaBuscado().getText());

        if (libroDAO.actualizar(libro)) {
            mostrarInformacion("Libro actualizado correctamente", actualizarLibroView);
        } else {
            mostrarInformacion("No se pudo actualizar el libro", actualizarLibroView);
        }
    }

    private void buscarElimLibro() {
        Libro libro = libroDAO.buscar(eliminarLibroView.getTxtISBN().getText());

        if (libro == null) {
            mostrarInformacion("Libro no encontrado", eliminarLibroView);
            return;
        }

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

    private void eliminarLibro() {
        Libro libro = libroDAO.buscar(eliminarLibroView.getTxtISBN().getText());

        if (libro == null) {
            mostrarInformacion("Libro no encontrado", eliminarLibroView);
            return;
        }

        if (!confirmarAccion("¿Está seguro de eliminar este libro?", eliminarLibroView)) {
            return;
        }

        libroDAO.eliminar(libro.getISBN());
        mostrarInformacion("Libro eliminado correctamente", eliminarLibroView);
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

        mostrarInformacion("Libro creado correctamente", crearLibroView);
    }

    private String restriccionEdad = "Si posee";
    private String restriccionEdadNo = "No posee";

    private void buscarLibro() {
        Libro libro = libroDAO.buscar(buscarLibroView.getTxtISBN().getText());

        if (libro == null) {
            mostrarInformacion("Libro no encontrado", buscarLibroView);
            return;
        }

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

        DefaultTableModel modelo = (DefaultTableModel) listarLibroView.getTblListarLibro().getModel();
        modelo.setRowCount(0); // Limpia la tabla

        for (Libro libro : libroDAO.listar()) {

            modelo.addRow(new Object[]{
                libro.getISBN(),
                libro.getNombre(),
                libro.getAutor(),
                libro.getGenero(),
                libro.getNumeroPaginas(),
                libro.getIdioma(),
                libro.isSirestriccionEdad() ? restriccionEdad : restriccionEdadNo,
                libro.estaDisponible() ? "Disponible" : "Prestado"
            });
        }
    }

    private void crearAutor() {
        libroDAO.crearAutor(
                new Autor(crearLibroView.getTxtNombreAutor().getText(),
                        crearLibroView.getTxtApellido().getText()));
        actualizarAutores();
    }

    public void cambioIdioma(ResourceBundle bundle) {
        cambioIdiomaActualizarLibro(bundle);
        cambioIdiomaBuscarLibro(bundle);
        cambioIdiomaEliminarLibro(bundle);
        cambioIdiomaCrearLibro(bundle);
        cambioIdiomaListarLibro(bundle);
    }

    private void cambioIdiomaCrearLibro(ResourceBundle bundle) {
        // ===== BOTONES =====
        crearLibroView.getBtnAceptar().setText(bundle.getString("btnAceptar"));
        crearLibroView.getBtnCrearAutor().setText(bundle.getString("btnCrearAutor"));

// ===== LABELS =====
        crearLibroView.getLblApellido().setText(bundle.getString("lblApellido"));
        crearLibroView.getLblAutor().setText(bundle.getString("lblAutor"));
        crearLibroView.getLblGenero().setText(bundle.getString("lblGenero"));
        crearLibroView.getLblISBN().setText(bundle.getString("lblISBN"));
        crearLibroView.getLblIdioma().setText(bundle.getString("lblIdioma"));
        crearLibroView.getLblNombre().setText(bundle.getString("lblNombre"));
        crearLibroView.getLblNombre2().setText(bundle.getString("lblNombre"));
        crearLibroView.getLblNumeroPagina().setText(bundle.getString("lblNumeroPaginas"));
        crearLibroView.getLblPreguntaExistenciaAutor().setText(bundle.getString("lblPreguntaExistenciaAutor"));
        crearLibroView.getLblTituloCreacionLibro().setText(bundle.getString("lblTituloCrearLibro"));

// ===== RADIO BUTTON =====
        crearLibroView.getRadioButtonRestriccion().setText(bundle.getString("radioButtonRestriccion"));
    }

    private void cambioIdiomaEliminarLibro(ResourceBundle bundle) {
        // ===== BOTONES =====
        eliminarLibroView.getBtnBuscar().setText(bundle.getString("btnBuscar"));
        eliminarLibroView.getBtnEliminar().setText(bundle.getString("btnEliminar"));

// ===== LABELS =====
        eliminarLibroView.getLblAutor().setText(bundle.getString("lblAutor"));
        eliminarLibroView.getLblEstado().setText(bundle.getString("lblEstado"));
        eliminarLibroView.getLblGenero().setText(bundle.getString("lblGenero"));
        eliminarLibroView.getLblISBN().setText(bundle.getString("lblISBN"));
        eliminarLibroView.getLblISBN2().setText(bundle.getString("lblISBN"));
        eliminarLibroView.getLblIdioma().setText(bundle.getString("lblIdioma"));
        eliminarLibroView.getLblNumeroPaginas().setText(bundle.getString("lblNumeroPaginas"));
        eliminarLibroView.getLblRestriccionEdad().setText(bundle.getString("lblRestriccionEdad"));
        eliminarLibroView.getLblTituloBuscado().setText(bundle.getString("lblTitulo"));
        eliminarLibroView.getLblTituloBusquedaLibro().setText(bundle.getString("lblTituloBuscarLibro"));
    }

    private void cambioIdiomaBuscarLibro(ResourceBundle bundle) {
// ===== BOTONES =====
        buscarLibroView.getBtnBuscar().setText(bundle.getString("btnBuscar"));

// ===== LABELS =====
        buscarLibroView.getLblAutor().setText(bundle.getString("lblAutor"));
        buscarLibroView.getLblEstado().setText(bundle.getString("lblEstado"));
        buscarLibroView.getLblGenero().setText(bundle.getString("lblGenero"));
        buscarLibroView.getLblISBN().setText(bundle.getString("lblISBN"));
        buscarLibroView.getLblISBN2().setText(bundle.getString("lblISBN"));
        buscarLibroView.getLblIdioma().setText(bundle.getString("lblIdioma"));
        buscarLibroView.getLblNumeroPaginas().setText(bundle.getString("lblNumeroPaginas"));
        buscarLibroView.getLblRestriccionEdad().setText(bundle.getString("lblRestriccionEdad"));
        buscarLibroView.getLblTituloBuscado().setText(bundle.getString("lblTitulo"));
        buscarLibroView.getLblTituloBusquedaLibro().setText(bundle.getString("lblTituloBuscarLibro"));
    }

    private void cambioIdiomaActualizarLibro(ResourceBundle bundle) {
        // ===== BOTONES =====
        actualizarLibroView.getBtnActualizacion().setText(bundle.getString("btnActualizacion"));
        actualizarLibroView.getBtnBuscar().setText(bundle.getString("btnBuscar"));

// ===== LABELS =====
        actualizarLibroView.getLblAutor().setText(bundle.getString("lblAutor"));
        actualizarLibroView.getLblEstado().setText(bundle.getString("lblEstado"));
        actualizarLibroView.getLblGenero().setText(bundle.getString("lblGenero"));
        actualizarLibroView.getLblISBN().setText(bundle.getString("lblISBN"));
        actualizarLibroView.getLblISBN2().setText(bundle.getString("lblISBN"));
        actualizarLibroView.getLblIdioma().setText(bundle.getString("lblIdioma"));
        actualizarLibroView.getLblNumeroPaginas().setText(bundle.getString("lblNumeroPaginas"));
        actualizarLibroView.getLblRestriccionEdad().setText(bundle.getString("lblRestriccionEdad"));
        actualizarLibroView.getLblTitulo().setText(bundle.getString("lblTitulo"));
        actualizarLibroView.getLblTituloBusquedaLibro().setText(bundle.getString("lblTituloBusquedaLibro"));

    }

    private void cambioIdiomaListarLibro(ResourceBundle bundle) {
        listarLibroView.getBtnListarLibro().setText(bundle.getString("btnListarLibro"));
        configurarTabla(bundle);
    }

    private void configurarTabla(ResourceBundle bundle) {
        String[] columnas = bundle.getString("columnasLibro").split(",");
        listarLibroView.setModelo(new DefaultTableModel(columnas, 0));
        listarLibroView.getTblListarLibro().setModel(listarLibroView.getModelo());
    }

    public void mostrarInformacion(String mensaje, JInternalFrame frame) {
        JOptionPane.showMessageDialog(frame, mensaje);
    }
    //IMPLEMENTACION EN ELIMINAR

    public boolean confirmarAccion(String mensaje, JInternalFrame frame) {
        int opcion = JOptionPane.showConfirmDialog(
                frame,
                mensaje,
                "Confirmación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        return opcion == JOptionPane.YES_OPTION;
    }
}
