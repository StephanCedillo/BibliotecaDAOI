/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ups.edu.ec.bibleotecainterfaz.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.BoxLayout;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
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
            mostrarInformacion("Préstamo no encontrado", buscarPrestamoView);
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
        modelo.addColumn(cambioISBN);
        modelo.addColumn(cambioNombre);

        buscarPrestamoView.getTblLibrosBuscados().setModel(modelo);
        devolucionPrestamoView.getTblLibrosBuscados().setModel(modelo);
    }

    public void cargarDatos(List<Libro> libros) {
        modelo.setRowCount(0);
        for (Libro libro : libros) {
            Object[] fila = {libro.getISBN(), libro.getNombre()};
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
            mostrarInformacion("Préstamo no encontrado", devolucionPrestamoView);
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
                mostrarInformacion("Usuario no encontrado", crearPrestamoView);
                return;
            }

            prestamoTemporal = new Prestamo(usuario, true);
        }

        Libro libro = libroDAO.buscar(crearPrestamoView.getTxtISBN().getText());

        if (libro == null) {
            mostrarInformacion("Libro no encontrado", crearPrestamoView);
            return;
        }

        prestamoTemporal.agregarLibro(libro);

        crearPrestamoView.getTxtISBN().setText("");

        mostrarInformacion("Libro agregado al préstamo", crearPrestamoView);
    }

    private void devolucion() {

        if (prestamoSeleccionadoDevolucion == null) {
            mostrarInformacion("Debe buscar un préstamo primero", devolucionPrestamoView);
            return;
        }

        if (!confirmarAccion("¿Desea registrar la devolución del préstamo?", devolucionPrestamoView)) {
            return;
        }

        prestamoSeleccionadoDevolucion.registrarDevolucion();
        mostrarInformacion("Préstamo devuelto correctamente", devolucionPrestamoView);
    }

    private void crearPrestamo() {

        if (prestamoTemporal == null) {
            Usuario usuario = usuarioDAO.buscar(crearPrestamoView.getTxtCedula().getText());

            if (usuario == null) {
                mostrarInformacion("Usuario no encontrado", crearPrestamoView);
                return;
            }

            prestamoTemporal = new Prestamo(usuario, true);
        }

        Libro libro = libroDAO.buscar(crearPrestamoView.getTxtISBN().getText());

        if (libro == null) {
            mostrarInformacion("Libro no encontrado", crearPrestamoView);
            return;
        }

        prestamoTemporal.agregarLibro(libro);

        prestamoDAO.crear(prestamoTemporal);

        mostrarInformacion("Préstamo creado correctamente", crearPrestamoView);

        prestamoTemporal = null;
    }
    PrestamoView[] prestamoViewLista = null;


    private void listarPrestamos() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        prestamoViewLista = new PrestamoView[prestamoDAO.listar().size()];
        int i = 0;
        for (Prestamo prestamo : prestamoDAO.listar()) {
            PrestamoView temporal = cambiarPanel(prestamo);
            panel.add(temporal);
            prestamoViewLista[i]=temporal;
            i++;
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
        modelo.addColumn(cambioISBN);
        modelo.addColumn(cambioNombre);

        prestamoView.getTblLibrosBuscados().setModel(modelo);
        
         
        

    }

    public void cargarDatosPanel(PrestamoView prestamoView, List<Libro> libros) {

        DefaultTableModel modelo = (DefaultTableModel) prestamoView.getTblLibrosBuscados().getModel();

        modelo.setRowCount(0);

        for (Libro libro : libros) {
            modelo.addRow(new Object[]{
                libro.getISBN(),
                libro.getNombre()
            });
        }
        
    }

    public void cambioIdioma(ResourceBundle bundle) {
        cambioIdiomaDevolucionPrestamo(bundle);
        cambioIdiomaBuscarPrestamo(bundle);
        cambioIdiomaListarPrestamo(bundle);
        cambioIdiomaCrearPrestamo(bundle);
    }

    private void cambioIdiomaCrearPrestamo(ResourceBundle bundle) {
// ===== BOTONES =====
        crearPrestamoView.getBtnAceptar().setText(bundle.getString("btnAceptar"));
        crearPrestamoView.getBtnIngresarOtroLibro().setText(bundle.getString("btnIngresarOtroLibro"));

// ===== LABELS =====
        crearPrestamoView.getLblCedula().setText(bundle.getString("lblCedula"));
        crearPrestamoView.getLblISBN().setText(bundle.getString("lblISBN"));
        crearPrestamoView.getLblTituloCreacionPrestamo().setText(bundle.getString("lblTituloCrearPrestamo"));
    }

    private String cambioISBN = "ISBN";
    private String cambioNombre = "Titulo";

    private void cambioIdiomaListarPrestamo(ResourceBundle bundle) {

        // ===== BOTÓN =====
        listarPrestamoView.getBtnListarRegistro().setText(bundle.getString("btnListarPrestamo"));
        cambioISBN = bundle.getString("lblISBN");
        cambioNombre = bundle.getString("lblTitulo");
        
       

        for (int i = 0; i < prestamoViewLista.length; i++) {

            configurarTablaPanel(prestamoViewLista[i]);
            cambioPanelIdioma(prestamoViewLista[i],bundle);
        }

        configurarTabla();

    }
    private void cambioPanelIdioma(PrestamoView prestamoView,ResourceBundle bundle){
         prestamoView.getLblCedula3().setText(bundle.getString("lblCedula"));
        prestamoView.getLblEmail2().setText(bundle.getString("lblEmail"));
        prestamoView.getLblEstado2().setText(bundle.getString("lblEstado"));
        prestamoView.getLblFechaDevuelto().setText(bundle.getString("lblFechaDevuelto"));
        prestamoView.getLblFechaPedido().setText(bundle.getString("lblFechaPedido"));
     
        prestamoView.getLblID2().setText(bundle.getString("lblID"));
       
        prestamoView.getLblNombre().setText(bundle.getString("lblNombre"));
    }

    private void cambioIdiomaBuscarPrestamo(ResourceBundle bundle) {
// ===== BOTONES =====
        buscarPrestamoView.getBtnCedula().setText(bundle.getString("btnCedula"));
        buscarPrestamoView.getBtnID().setText(bundle.getString("btnID"));
        buscarPrestamoView.getBtnISBN().setText(bundle.getString("btnISBN"));

// ===== LABELS =====
        buscarPrestamoView.getLblBuscarPor().setText(bundle.getString("lblBuscarPor"));
        buscarPrestamoView.getLblCedula2().setText(bundle.getString("lblCedula"));
        buscarPrestamoView.getLblCedula3().setText(bundle.getString("lblCedula"));
        buscarPrestamoView.getLblEmail2().setText(bundle.getString("lblEmail"));
        buscarPrestamoView.getLblEstado2().setText(bundle.getString("lblEstado"));
        buscarPrestamoView.getLblFechaDevuelto().setText(bundle.getString("lblFechaDevuelto"));
        buscarPrestamoView.getLblFechaPedido().setText(bundle.getString("lblFechaPedido"));
        buscarPrestamoView.getLblID().setText(bundle.getString("lblID"));
        buscarPrestamoView.getLblID2().setText(bundle.getString("lblID"));
        buscarPrestamoView.getLblISBN3().setText(bundle.getString("lblISBN"));
        buscarPrestamoView.getLblNombre().setText(bundle.getString("lblNombre"));
        buscarPrestamoView.getLblTituloBusquedaPrestamo().setText(bundle.getString("lblTituloBuscarPrestamo"));
    }

    private void cambioIdiomaDevolucionPrestamo(ResourceBundle bundle) {
        // ===== BOTONES =====
        devolucionPrestamoView.getBtnCedula().setText(bundle.getString("btnCedula"));
        devolucionPrestamoView.getBtnDevolucion().setText(bundle.getString("btnDevolucion"));
        devolucionPrestamoView.getBtnID().setText(bundle.getString("btnID"));
        devolucionPrestamoView.getBtnISBN().setText(bundle.getString("btnISBN"));

// ===== LABELS =====
        devolucionPrestamoView.getLblCedula().setText(bundle.getString("lblCedula"));
        devolucionPrestamoView.getLblCedula2().setText(bundle.getString("lblCedula"));
        devolucionPrestamoView.getLblDevuelto().setText(bundle.getString("lblFechaDevuelto"));
        devolucionPrestamoView.getLblEstado().setText(bundle.getString("lblEstado"));
        devolucionPrestamoView.getLblGmail().setText(bundle.getString("lblEmail"));
        devolucionPrestamoView.getLblID().setText(bundle.getString("lblID"));
        devolucionPrestamoView.getLblID2().setText(bundle.getString("lblID"));
        devolucionPrestamoView.getLblISBN().setText(bundle.getString("lblISBN"));
        devolucionPrestamoView.getLblNombre().setText(bundle.getString("lblNombre"));
        devolucionPrestamoView.getLblPedido().setText(bundle.getString("lblFechaPedido"));
        devolucionPrestamoView.getLblTItuloDevolucionPrestamo().setText(bundle.getString("lblTituloDevolucionPrestamo"));
        devolucionPrestamoView.getLblTextoPorBuscar().setText(bundle.getString("lblBuscarPor"));
    }

    public void mostrarInformacion(String mensaje, JInternalFrame frame) {
        JOptionPane.showMessageDialog(frame, mensaje);
    }

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
