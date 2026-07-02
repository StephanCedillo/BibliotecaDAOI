/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ups.edu.ec.bibleotecainterfaz.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import ups.edu.ec.bibleotecainterfaz.dao.*;
import ups.edu.ec.bibleotecainterfaz.models.Libro;
import ups.edu.ec.bibleotecainterfaz.models.Prestamo;
import ups.edu.ec.bibleotecainterfaz.models.Usuario;
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

    private PrestamoDAO prestamoDAO;
    private UsuarioDAO usuarioDAO;
    private LibroDAO libroDAO;

    public PrestamoController(DevolucionPrestamoView devolucionPrestamoView, BuscarPrestamoView buscarPrestamoiew,
            CrearPrestamoView crearPrestamoView, ListarPrestamoView listarPrestamoView, PrestamoDAO prestamoDAO,
            UsuarioDAO usuarioDAO, LibroDAO libroDAO) {
        this.devolucionPrestamoView = devolucionPrestamoView;
        this.buscarPrestamoView = buscarPrestamoiew;
        this.crearPrestamoView = crearPrestamoView;
        this.listarPrestamoView = listarPrestamoView;
        this.prestamoDAO = prestamoDAO;
        this.usuarioDAO = usuarioDAO;
        this.libroDAO = libroDAO;
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
                        buscarDevolucion(1);
                    }
                });
        devolucionPrestamoView.getBtnID().addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        buscarDevolucion(2);
                    }
                });
        devolucionPrestamoView.getBtnISBN().addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        buscarDevolucion(3);
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
        listarPrestamoView.getBtnListarRegistro().addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        listarPrestamos();
                    }
                });
        listarPrestamos();

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
        configurarTabla();
        buscarPrestamoView.getBtnCedula().addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        buscar(1);
                    }
                });
        buscarPrestamoView.getBtnID().addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        buscar(2);
                    }
                });
        buscarPrestamoView.getBtnISBN().addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        buscar(3);
                    }
                });

    }

    private void buscar(int i) {
        Prestamo prestamoSeleccionadoBuscar = null;
        if (i == 1) {
            prestamoSeleccionadoBuscar = prestamoDAO.buscarCedula(buscarPrestamoView.getTxtICedula().getText());
        } else if (i == 2) {
            prestamoSeleccionadoBuscar = prestamoDAO
                    .buscarID(Integer.parseInt(buscarPrestamoView.getTxtID().getText()));
        } else {
            prestamoSeleccionadoBuscar = prestamoDAO.buscarISBN(buscarPrestamoView.getTxtISBN().getText());
        }

        if (prestamoSeleccionadoBuscar == null) {
            System.out.println("Préstamo no encontrado");
            return;
        }
        buscarPrestamoView.getLblIDBuscado().setText(String.valueOf((char) prestamoSeleccionadoBuscar.getId()));
        buscarPrestamoView.getTxtCedulaBuscado().setText(prestamoSeleccionadoBuscar.getUsuario().getCedula());
        buscarPrestamoView.getTxtGmailBuscado().setText(prestamoSeleccionadoBuscar.getUsuario().getEmail());
        buscarPrestamoView.getTxtNombreBuscado().setText(prestamoSeleccionadoBuscar.getUsuario().getNombre()
                + prestamoSeleccionadoBuscar.getUsuario().getApellido());

        buscarPrestamoView.getTxtFechaPedidoBuscado()
                .setText(prestamoSeleccionadoBuscar.getFechaDevolucion().toString());
        buscarPrestamoView.getTxtFechaDevueltoBuscado()
                .setText(prestamoSeleccionadoBuscar.getFechaDevolucion().toString());

        if (prestamoSeleccionadoBuscar.isEstado()) {
            buscarPrestamoView.getPnlEstadoDevuelto().setBackground(new Color(0, 255, 0));
        } else {
            buscarPrestamoView.getPnlEstadoDevuelto().setBackground(new Color(255, 51, 51));
        }
        cargarDatos(prestamoSeleccionadoBuscar.getLibro());

    }

    private DefaultTableModel modelo;

    private void configurarTabla() {
        modelo = new DefaultTableModel();
        modelo.addColumn("ISBN");
        modelo.addColumn("Titulo");

        buscarPrestamoView.getTblLibrosBuscados().setModel(modelo);
        devolucionPrestamoView.getTblLibrosBuscados().setModel(modelo);
    }

    public void cargarDatos(List<Libro> libros) {
        modelo.setRowCount(0);
        for (Libro libro : libros) {
            Object[] fila = { libro.getISBN(), libro.getNombre() };
            modelo.addRow(fila);
        }

    }

    private Prestamo prestamoSeleccionadoDevolucion = null;

    private void buscarDevolucion(int i) {
        if (i == 1) {
            prestamoSeleccionadoDevolucion = prestamoDAO.buscarCedula(devolucionPrestamoView.getTxtICedula().getText());
        } else if (i == 2) {
            prestamoSeleccionadoDevolucion = prestamoDAO
                    .buscarID(Integer.parseInt(devolucionPrestamoView.getTxtID().getText()));
        } else {
            prestamoSeleccionadoDevolucion = prestamoDAO.buscarISBN(devolucionPrestamoView.getTxtISBN().getText());
        }

        if (prestamoSeleccionadoDevolucion == null) {
            System.out.println("Préstamo no encontrado");
            return;
        }
        devolucionPrestamoView.getLblIDBuscado().setText(String.valueOf((char) prestamoSeleccionadoDevolucion.getId()));
        devolucionPrestamoView.getTxtCedulaBuscado().setText(prestamoSeleccionadoDevolucion.getUsuario().getCedula());
        devolucionPrestamoView.getTxtGmailBuscado().setText(prestamoSeleccionadoDevolucion.getUsuario().getEmail());
        devolucionPrestamoView.getTxtNombreBuscado().setText(prestamoSeleccionadoDevolucion.getUsuario().getNombre()
                + prestamoSeleccionadoDevolucion.getUsuario().getApellido());

        devolucionPrestamoView.getTxtFechaPedidoBuscado()
                .setText(prestamoSeleccionadoDevolucion.getFechaPedido().toString());
        devolucionPrestamoView.getTxtFechaDevueltoBuscado()
                .setText(prestamoSeleccionadoDevolucion.getFechaDevolucion().toString());

        if (prestamoSeleccionadoDevolucion.isEstado()) {
            devolucionPrestamoView.getPnlEstadoDevuelto().setBackground(new Color(0, 255, 0));
        } else {
            devolucionPrestamoView.getPnlEstadoDevuelto().setBackground(new Color(255, 51, 51));
        }
        cargarDatos(prestamoSeleccionadoDevolucion.getLibro());
    }

    private Prestamo prestamoTemporal = null;

    private void ingresarOtroLibro() {

        if (prestamoTemporal == null) {
            Usuario usuario = usuarioDAO.buscar(crearPrestamoView.getTxtCedula().getText());

            if (usuario == null) {
                System.out.println("Usuario no encontrado");
                return;
            }

            prestamoTemporal = new Prestamo(usuario, true);
        }

        Libro libro = libroDAO.buscar(crearPrestamoView.getTxtISBN().getText());

        if (libro == null) {
            System.out.println("Libro no encontrado");
            return;
        }

        prestamoTemporal.agregarLibro(libro);

        crearPrestamoView.getTxtISBN().setText("");

        System.out.println("Libro agregado al préstamo");
    }

    private void devolucion() {
        if (prestamoSeleccionadoDevolucion == null) {
            System.out.println("PRODUCTO NO SELECCIONADO");
            return;
        }
        prestamoSeleccionadoDevolucion.registrarDevolucion();
    }

    private void crearPrestamo() {

        if (prestamoTemporal == null) {
            Usuario usuario = usuarioDAO.buscar(crearPrestamoView.getTxtCedula().getText());

            if (usuario == null) {
                System.out.println("Usuario no encontrado");
                return;
            }

            prestamoTemporal = new Prestamo(usuario, true);
        }

        Libro libro = libroDAO.buscar(crearPrestamoView.getTxtISBN().getText());

        if (libro == null) {
            System.out.println("Libro no encontrado");
            return;
        }

        prestamoTemporal.agregarLibro(libro);

        prestamoDAO.crear(prestamoTemporal);
        System.out.println(prestamoTemporal);

        System.out.println("Préstamo creado");

        prestamoTemporal = null;
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

    private void listarPrestamos() {
        JPanel panel = new JPanel();
panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

for (Prestamo prestamo : prestamoDAO.listar()) {
    panel.add(cambiarPanel(prestamo));
}

listarPrestamoView.getScrollPanePrestamos().setViewportView(panel);

    }

    private PrestamoView cambiarPanel(Prestamo prestamo) {

        PrestamoView prestamoView = new PrestamoView();
        configurarTablaPanel(prestamoView);

        prestamoView.getLblIDBuscado().setText(String.valueOf(prestamo.getId()));
        prestamoView.getTxtCedulaBuscado().setText(prestamo.getUsuario().getCedula());
        prestamoView.getTxtGmailBuscado().setText(prestamo.getUsuario().getEmail());
        prestamoView.getTxtNombreBuscado()
                .setText(prestamo.getUsuario().getNombre() + " " + prestamo.getUsuario().getApellido());

        prestamoView.getTxtFechaPedidoBuscado().setText(prestamo.getFechaPedido().toString());
        prestamoView.getTxtFechaDevueltoBuscado().setText(prestamo.getFechaDevolucion().toString());

        if (prestamo.isEstado()) {
            prestamoView.getPnlEstadoDevuelto().setBackground(new Color(0, 255, 0));
        } else {
            prestamoView.getPnlEstadoDevuelto().setBackground(new Color(255, 51, 51));
        }
        cargarDatosPanel(prestamoView, prestamo.getLibro());

        return prestamoView;

    }

    private void configurarTablaPanel(PrestamoView prestamoView) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ISBN");
        modelo.addColumn("Titulo");

        prestamoView.getTblLibrosBuscados().setModel(modelo);
    }

    public void cargarDatosPanel(PrestamoView prestamoView, List<Libro> libros) {

        DefaultTableModel modelo = (DefaultTableModel) prestamoView.getTblLibrosBuscados().getModel();

        modelo.setRowCount(0);

        for (Libro libro : libros) {
            modelo.addRow(new Object[] {
                    libro.getISBN(),
                    libro.getNombre()
            });
        }
    }

}
