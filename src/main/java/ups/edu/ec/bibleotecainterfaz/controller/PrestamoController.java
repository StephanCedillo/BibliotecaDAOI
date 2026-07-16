package ups.edu.ec.bibleotecainterfaz.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
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
import ups.edu.ec.bibleotecainterfaz.enums.MensajePrestamo;
import ups.edu.ec.bibleotecainterfaz.view.*;

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
    private PrestamoView[] prestamoViewLista = null;
    private Prestamo prestamoSeleccionadoDevolucion = null;
    private Prestamo prestamoTemporal = null;
    private DefaultTableModel modelo;

    private String cambioISBN = "ISBN";
    private String cambioNombre = "Titulo";

    // Arreglo actualizado con todos los mensajes (9 en total)
    private String[] mensajes = {
        "Prestamo no encontrado", // 0
        "Usuario no encontrado", // 1
        "Libro no encontrado", // 2
        "Libro agregado al préstamo", // 3
        "Debe buscar un préstamo primero", // 4
        "¿Desea registrar la devolución del préstamo?", // 5
        "Préstamo devuelto correctamente", // 6
        "Préstamo creado correctamente", // 7
        "Ingrese un ID válido" // 8
    };

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
        crearPrestamoView.getLblbtnCedula().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                buscarUsuario();
            }
        });
        crearPrestamoView.getLblbtnLibro().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                buscarLibro();
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
            try {
                int id = Integer.parseInt(buscarPrestamoView.getTxtID().getText());
                prestamoSeleccionadoBuscar = prestamoDAO.buscarID(id);
            } catch (NumberFormatException ex) {
                mostrarInformacion(MensajePrestamo.ERR_ID_VALIDO.getTexto(mensajes), buscarPrestamoView);
                return;
            }
        } else {
            prestamoSeleccionadoBuscar = prestamoDAO.buscarISBN(buscarPrestamoView.getTxtISBN().getText());
        }

        if (prestamoSeleccionadoBuscar == null) {
            mostrarInformacion(MensajePrestamo.PRESTAMO_NO_ENCONTRADO.getTexto(mensajes), buscarPrestamoView);
            return;
        }

        buscarPrestamoView.getLblIDBuscado().setText(String.valueOf(prestamoSeleccionadoBuscar.getId()));

        // Validación de nulidad para Usuario
        Usuario usuario = prestamoSeleccionadoBuscar.getUsuario();
        if (usuario != null) {
            buscarPrestamoView.getTxtCedulaBuscado().setText(usuario.getCedula() != null ? usuario.getCedula() : "");
            buscarPrestamoView.getTxtGmailBuscado().setText(usuario.getEmail() != null ? usuario.getEmail() : "");
            buscarPrestamoView.getTxtNombreBuscado().setText((usuario.getNombre() != null ? usuario.getNombre() : "")
                    + " " + (usuario.getApellido() != null ? usuario.getApellido() : ""));
        }

        // Validación de nulidad para Fechas
        buscarPrestamoView.getTxtFechaPedidoBuscado().setText(
                prestamoSeleccionadoBuscar.getFechaPedido() != null ? prestamoSeleccionadoBuscar.getFechaPedido().toString() : "N/A");
        buscarPrestamoView.getTxtFechaDevueltoBuscado().setText(
                prestamoSeleccionadoBuscar.getFechaDevolucion() != null ? prestamoSeleccionadoBuscar.getFechaDevolucion().toString() : "N/A");

        if (prestamoSeleccionadoBuscar.isEstado()) {
            buscarPrestamoView.getPnlEstadoDevuelto().setBackground(new Color(0, 255, 0));
        } else {
            buscarPrestamoView.getPnlEstadoDevuelto().setBackground(new Color(255, 51, 51));
        }

        cargarDatos(prestamoSeleccionadoBuscar.getLibro());
    }

    private void configurarTabla() {
        modelo = new DefaultTableModel();
        modelo.addColumn(cambioISBN);
        modelo.addColumn(cambioNombre);

        buscarPrestamoView.getTblLibrosBuscados().setModel(modelo);
        devolucionPrestamoView.getTblLibrosBuscados().setModel(modelo);
    }

    public void cargarDatos(List<Libro> libros) {
        modelo.setRowCount(0);
        if (libros == null) {
            return;
        }

        for (Libro libro : libros) {
            if (libro != null) {
                Object[] fila = {libro.getISBN(), libro.getNombre()};
                modelo.addRow(fila);
            }
        }
    }

    private void buscarDevolucion(int i) {
        if (i == 1) {
            prestamoSeleccionadoDevolucion = prestamoDAO.buscarCedula(devolucionPrestamoView.getTxtICedula().getText());
        } else if (i == 2) {
            try {
                int id = Integer.parseInt(devolucionPrestamoView.getTxtID().getText()); // Corrección visual menor
                prestamoSeleccionadoDevolucion = prestamoDAO.buscarID(id);
            } catch (NumberFormatException ex) {
                mostrarInformacion(MensajePrestamo.ERR_ID_VALIDO.getTexto(mensajes), devolucionPrestamoView); // Corrección de vista
                return;
            }
        } else {
            prestamoSeleccionadoDevolucion = prestamoDAO.buscarISBN(devolucionPrestamoView.getTxtISBN().getText());
        }

        if (prestamoSeleccionadoDevolucion == null) {
            mostrarInformacion(MensajePrestamo.PRESTAMO_NO_ENCONTRADO.getTexto(mensajes), devolucionPrestamoView);
            return;
        }

        devolucionPrestamoView.getLblIDBuscado().setText(String.valueOf(prestamoSeleccionadoDevolucion.getId()));

        Usuario usuario = prestamoSeleccionadoDevolucion.getUsuario();
        if (usuario != null) {
            devolucionPrestamoView.getTxtCedulaBuscado().setText(usuario.getCedula() != null ? usuario.getCedula() : "");
            devolucionPrestamoView.getTxtGmailBuscado().setText(usuario.getEmail() != null ? usuario.getEmail() : "");
            devolucionPrestamoView.getTxtNombreBuscado().setText((usuario.getNombre() != null ? usuario.getNombre() : "")
                    + " " + (usuario.getApellido() != null ? usuario.getApellido() : ""));
        }

        devolucionPrestamoView.getTxtFechaPedidoBuscado().setText(
                prestamoSeleccionadoDevolucion.getFechaPedido() != null ? prestamoSeleccionadoDevolucion.getFechaPedido().toString() : "N/A");
        devolucionPrestamoView.getTxtFechaDevueltoBuscado().setText(
                prestamoSeleccionadoDevolucion.getFechaDevolucion() != null ? prestamoSeleccionadoDevolucion.getFechaDevolucion().toString() : "N/A");

        if (prestamoSeleccionadoDevolucion.isEstado()) {
            devolucionPrestamoView.getPnlEstadoDevuelto().setBackground(new Color(0, 255, 0));
        } else {
            devolucionPrestamoView.getPnlEstadoDevuelto().setBackground(new Color(255, 51, 51));
        }
        cargarDatos(prestamoSeleccionadoDevolucion.getLibro());
    }

    private void ingresarOtroLibro() {
        if (prestamoTemporal == null) {
            Usuario usuario = usuarioDAO.buscar(crearPrestamoView.getTxtCedula().getText());
            if (usuario == null) {
                mostrarInformacion(MensajePrestamo.USUARIO_NO_ENCONTRADO.getTexto(mensajes), crearPrestamoView);
                return;
            }
            prestamoTemporal = new Prestamo(usuario, true);
        }

        Libro libro = libroDAO.buscar(crearPrestamoView.getTxtISBN().getText());
        if (libro == null) {
            mostrarInformacion(MensajePrestamo.LIBRO_NO_ENCONTRADO.getTexto(mensajes), crearPrestamoView);
            return;
        }

        buscarLibro();
        prestamoTemporal.agregarLibro(libro);
        crearPrestamoView.getTxtISBN().setText("");
        mostrarInformacion(MensajePrestamo.LIBRO_AGREGADO.getTexto(mensajes), crearPrestamoView);
    }

    private void devolucion() {
        if (prestamoSeleccionadoDevolucion == null) {
            mostrarInformacion(MensajePrestamo.BUSCAR_PRESTAMO_PRIMERO.getTexto(mensajes), devolucionPrestamoView);
            return;
        }
        if (!confirmarAccion(MensajePrestamo.CONFIRMAR_DEVOLUCION.getTexto(mensajes), devolucionPrestamoView)) {
            return;
        }

        prestamoSeleccionadoDevolucion.registrarDevolucion();
        mostrarInformacion(MensajePrestamo.PRESTAMO_DEVUELTO.getTexto(mensajes), devolucionPrestamoView);
    }

    private void buscarUsuario() {
        Usuario usuario = usuarioDAO.buscar(crearPrestamoView.getTxtCedula().getText());
        if (usuario == null) {
            mostrarInformacion(MensajePrestamo.USUARIO_NO_ENCONTRADO.getTexto(mensajes), crearPrestamoView);
            return;
        }
        crearPrestamoView.getTxtUsuario().setText(usuario.getNombre() + " " + usuario.getApellido());
    }

    private void buscarLibro() {
        Libro libro = libroDAO.buscar(crearPrestamoView.getTxtISBN().getText());
        if (libro == null) {
            mostrarInformacion(MensajePrestamo.LIBRO_NO_ENCONTRADO.getTexto(mensajes), crearPrestamoView);
            return;
        }
        crearPrestamoView.getTxtLibro().setText(libro.getNombre());

    }
private void crearPrestamo() {
    if (prestamoTemporal == null) {
        Usuario usuario = usuarioDAO.buscar(crearPrestamoView.getTxtCedula().getText());
        if (usuario == null) {
            mostrarInformacion(MensajePrestamo.USUARIO_NO_ENCONTRADO.getTexto(mensajes), crearPrestamoView);
            return;
        }
        buscarUsuario();
        prestamoTemporal = new Prestamo(usuario, true);
    }

    Libro libro = libroDAO.buscar(crearPrestamoView.getTxtISBN().getText());
    if (libro == null) {
        mostrarInformacion(MensajePrestamo.LIBRO_NO_ENCONTRADO.getTexto(mensajes), crearPrestamoView);
        return;
    }

  
    prestamoTemporal.agregarLibro(libro);
    

    prestamoDAO.crear(prestamoTemporal);
    mostrarInformacion(MensajePrestamo.PRESTAMO_CREADO.getTexto(mensajes), crearPrestamoView);

  
    if (prestamoTemporal.getLibro() != null) {
        for (Libro libroRecorrido : prestamoTemporal.getLibro()) {
            Libro libGuardado = libroDAO.buscar(libroRecorrido.getISBN());
            if (libGuardado != null) {
                libGuardado.prestar(); 
                libroDAO.actualizar(libGuardado); 
            }
        }
    }
    prestamoTemporal = null;
}
   
    private void listarPrestamos() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        List<Prestamo> listaPrestamos = prestamoDAO.listar();

        // Validación para evitar NullPointerException si la lista viene nula
        if (listaPrestamos != null) {
            prestamoViewLista = new PrestamoView[listaPrestamos.size()];
            int i = 0;
            for (Prestamo prestamo : listaPrestamos) {
                if (prestamo != null) {
                    PrestamoView temporal = cambiarPanel(prestamo);
                    panel.add(temporal);
                    prestamoViewLista[i] = temporal;
                    i++;
                }
            }
        } else {
            prestamoViewLista = new PrestamoView[0];
        }

        listarPrestamoView.getScrollPanePrestamos().setViewportView(panel);
    }

    private PrestamoView cambiarPanel(Prestamo prestamo) {
        PrestamoView prestamoView = new PrestamoView();
        configurarTablaPanel(prestamoView);

        prestamoView.getLblIDBuscado().setText(String.valueOf(prestamo.getId()));

        Usuario usuario = prestamo.getUsuario();
        if (usuario != null) {
            prestamoView.getTxtCedulaBuscado().setText(usuario.getCedula() != null ? usuario.getCedula() : "");
            prestamoView.getTxtGmailBuscado().setText(usuario.getEmail() != null ? usuario.getEmail() : "");
            prestamoView.getTxtNombreBuscado().setText((usuario.getNombre() != null ? usuario.getNombre() : "")
                    + " " + (usuario.getApellido() != null ? usuario.getApellido() : ""));
        }

        prestamoView.getTxtFechaPedidoBuscado().setText(
                prestamo.getFechaPedido() != null ? prestamo.getFechaPedido().toString() : "N/A");
        prestamoView.getTxtFechaDevueltoBuscado().setText(
                prestamo.getFechaDevolucion() != null ? prestamo.getFechaDevolucion().toString() : "N/A");

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
        if (libros == null) {
            return;
        }

        
      for (Libro libro : libros) {
          
    if (libro != null) { 
        Libro libroEncontrado = libroDAO.buscar((libro.getISBN()));

        System.out.println("ISBN: " + libro.getISBN());
        System.out.println("Nombre: " + libroEncontrado.getNombre());

        modelo.addRow(new Object[]{
            libro.getISBN(),
            libroEncontrado.getNombre()
        });
    }
}
    }

    public void cambioIdioma(ResourceBundle bundle) {
        if (bundle == null) {
            return; // Evitar NPE si el bundle no existe
        }
        try {
            cambioIdiomaDevolucionPrestamo(bundle);
            cambioIdiomaBuscarPrestamo(bundle);
            cambioIdiomaListarPrestamo(bundle);
            cambioIdiomaCrearPrestamo(bundle);

            String mensajesStr = bundle.getString("mensajesPrestamo");
            if (mensajesStr != null) {
                mensajes = mensajesStr.split(",");
            }
        } catch (Exception e) {
            System.err.println("Error al cambiar idioma: Faltan claves en el archivo de propiedades.");
        }
    }

    private void cambioIdiomaCrearPrestamo(ResourceBundle bundle) {
        crearPrestamoView.getBtnAceptar().setText(bundle.getString("btnAceptar"));
        crearPrestamoView.getBtnIngresarOtroLibro().setText(bundle.getString("btnIngresarOtroLibro"));
        crearPrestamoView.getLblCedula().setText(bundle.getString("lblCedula"));
        crearPrestamoView.getLblISBN().setText(bundle.getString("lblISBN"));
        crearPrestamoView.getLblTituloCreacionPrestamo().setText(bundle.getString("lblTituloCrearPrestamo"));
    }

    private void cambioIdiomaListarPrestamo(ResourceBundle bundle) {
        listarPrestamoView.getBtnListarRegistro().setText(bundle.getString("btnListarPrestamo"));
        cambioISBN = bundle.getString("lblISBN");
        cambioNombre = bundle.getString("lblTitulo");

        // Evitar NullPointerException si se cambia el idioma antes de abrir la vista
        if (prestamoViewLista != null) {
            for (int i = 0; i < prestamoViewLista.length; i++) {
                if (prestamoViewLista[i] != null) {
                    configurarTablaPanel(prestamoViewLista[i]);
                    cambioPanelIdioma(prestamoViewLista[i], bundle);
                }
            }
        }
        configurarTabla();
    }

    private void cambioPanelIdioma(PrestamoView prestamoView, ResourceBundle bundle) {
        prestamoView.getLblCedula3().setText(bundle.getString("lblCedula"));
        prestamoView.getLblEmail2().setText(bundle.getString("lblEmail"));
        prestamoView.getLblEstado2().setText(bundle.getString("lblEstado"));
        prestamoView.getLblFechaDevuelto().setText(bundle.getString("lblFechaDevuelto"));
        prestamoView.getLblFechaPedido().setText(bundle.getString("lblFechaPedido"));
        prestamoView.getLblID2().setText(bundle.getString("lblID"));
        prestamoView.getLblNombre().setText(bundle.getString("lblNombre"));
    }

    private void cambioIdiomaBuscarPrestamo(ResourceBundle bundle) {
        buscarPrestamoView.getBtnCedula().setText(bundle.getString("btnCedula"));
        buscarPrestamoView.getBtnID().setText(bundle.getString("btnID"));
        buscarPrestamoView.getBtnISBN().setText(bundle.getString("btnISBN"));
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
        devolucionPrestamoView.getBtnCedula().setText(bundle.getString("btnCedula"));
        devolucionPrestamoView.getBtnDevolucion().setText(bundle.getString("btnDevolucion"));
        devolucionPrestamoView.getBtnID().setText(bundle.getString("btnID"));
        devolucionPrestamoView.getBtnISBN().setText(bundle.getString("btnISBN"));
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

    public DevolucionPrestamoView getDevolucionPrestamoView() {
        return devolucionPrestamoView;
    }

    public BuscarPrestamoView getBuscarPrestamoView() {
        return buscarPrestamoView;
    }

    public CrearPrestamoView getCrearPrestamoView() {
        return crearPrestamoView;
    }

    public ListarPrestamoView getListarPrestamoView() {
        return listarPrestamoView;
    }

    public PrestamoDAO getPrestamoDAO() {
        return prestamoDAO;
    }

    public UsuarioDAO getUsuarioDAO() {
        return usuarioDAO;
    }

    public LibroDAO getLibroDAO() {
        return libroDAO;
    }

    public PrestamoView[] getPrestamoViewLista() {
        return prestamoViewLista;
    }

    public Prestamo getPrestamoSeleccionadoDevolucion() {
        return prestamoSeleccionadoDevolucion;
    }

    public Prestamo getPrestamoTemporal() {
        return prestamoTemporal;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public String getCambioISBN() {
        return cambioISBN;
    }

    public String getCambioNombre() {
        return cambioNombre;
    }

    public String[] getMensajes() {
        return mensajes;
    }

    public void setDevolucionPrestamoView(DevolucionPrestamoView devolucionPrestamoView) {
        this.devolucionPrestamoView = devolucionPrestamoView;
    }

    public void setBuscarPrestamoView(BuscarPrestamoView buscarPrestamoView) {
        this.buscarPrestamoView = buscarPrestamoView;
    }

    public void setCrearPrestamoView(CrearPrestamoView crearPrestamoView) {
        this.crearPrestamoView = crearPrestamoView;
    }

    public void setListarPrestamoView(ListarPrestamoView listarPrestamoView) {
        this.listarPrestamoView = listarPrestamoView;
    }

    public void setPrestamoDAO(PrestamoDAO prestamoDAO) {
        this.prestamoDAO = prestamoDAO;
    }

    public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public void setLibroDAO(LibroDAO libroDAO) {
        this.libroDAO = libroDAO;
    }

    public void setPrestamoViewLista(PrestamoView[] prestamoViewLista) {
        this.prestamoViewLista = prestamoViewLista;
    }

    public void setPrestamoSeleccionadoDevolucion(Prestamo prestamoSeleccionadoDevolucion) {
        this.prestamoSeleccionadoDevolucion = prestamoSeleccionadoDevolucion;
    }

    public void setPrestamoTemporal(Prestamo prestamoTemporal) {
        this.prestamoTemporal = prestamoTemporal;
    }

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }

    public void setCambioISBN(String cambioISBN) {
        this.cambioISBN = cambioISBN;
    }

    public void setCambioNombre(String cambioNombre) {
        this.cambioNombre = cambioNombre;
    }

    public void setMensajes(String[] mensajes) {
        this.mensajes = mensajes;
    }
    
}
