/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ups.edu.ec.bibleotecainterfaz.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import javax.swing.table.DefaultTableModel;
import ups.edu.ec.bibleotecainterfaz.dao.AutorDAO;

import ups.edu.ec.bibleotecainterfaz.dao.LibroDAO;
import ups.edu.ec.bibleotecainterfaz.excepciones.CamposVaciosException;
import ups.edu.ec.bibleotecainterfaz.models.Autor;
import ups.edu.ec.bibleotecainterfaz.models.Libro;
import ups.edu.ec.bibleotecainterfaz.enums.MensajeLibro;
import ups.edu.ec.bibleotecainterfaz.enums.Genero; 
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
    private AutorDAO autorDAO;

    public LibroController(ActualizarLibroView actualizarLibroView, BuscarLibroView buscarLibroView,
            EliminarLibroView eliminarLibroView, CrearLibroView crearLibroView, ListarLibroView listarLibroView,
            LibroDAO libroDAO,AutorDAO autorDAO) {
        this.actualizarLibroView = actualizarLibroView;
        this.buscarLibroView = buscarLibroView;
        this.eliminarLibroView = eliminarLibroView;
        this.crearLibroView = crearLibroView;
        this.listarLibroView = listarLibroView;
        this.libroDAO = libroDAO;
        this.autorDAO = autorDAO;
        configurarEventos();

        cargarGeneros(); 
        actualizarComboAutores();
  
        listarLibro();
    }

    private String restriccionEdad = "Si posee";
    private String restriccionEdadNo = "No posee";
    
    
    private String[] generos = {
            "Aventuras", // 0
            "Ciencia ficción", // 1
            "Fantasía", // 2
            "Terror", // 3
            "Romance", // 4
            "Misterio", // 5
            "Histórica", // 6
            "Policiaca", // 7
            "Distopía", // 8
            "Humor", // 9
            "Drama", // 10
            "Poesía" // 11
    };

    private String[] mensajes = {
            "No se encontro el libro", // 0
            "No se pudo actualizar el libro", // 1
            "¿Está seguro de eliminar este libro?", // 2
            "Libro creado correctamente", // 3
            "Libro actualizado correctamente", // 4
            "El número de páginas debe ser un valor numérico válido.", // 5
            "Asegúrese de seleccionar un autor válido.", // 6
            "Asegúrese de seleccionar un autor y género válidos.", // 7
            "El campo ISBN no puede estar vacío.", // 8
            "El título del libro no puede estar vacío.", // 9
            "Debe seleccionar un autor de la lista.", // 10
            "Debe seleccionar o ingresar un género válido.", // 11
            "El campo de idioma no puede estar vacío.", // 12
            "El número de páginas debe ser mayor a 0.", // 13
            "El título del libro no puede estar vacío para la actualización.", // 14
            "El campo de género no puede quedar vacío.", // 15
            "El campo de idioma no puede quedar vacío." // 16
    };

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
        Libro libro = libroDAO.buscar(actualizarLibroView.getTxtISBN().getText());

        if (libro == null) {
            mostrarInformacion(MensajeLibro.LIBRO_NO_ENCONTRADO.getTexto(mensajes), actualizarLibroView);
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
            mostrarInformacion(MensajeLibro.LIBRO_NO_ENCONTRADO.getTexto(mensajes), actualizarLibroView);
            return;
        }

        try {
            int numeroPaginas = Integer.parseInt(actualizarLibroView.getTxtNumeroPaginas().getText());

            validarCamposActualizar(
                actualizarLibroView.getTxtTituloBuscado().getText(),
                actualizarLibroView.getTxtGeneroBuscado().getText(),
                actualizarLibroView.getRadioButtonRestriccion().isSelected(),
                numeroPaginas,
                actualizarLibroView.getTxtIdiomaBuscado().getText()
            );

            libro.setNombre(actualizarLibroView.getTxtTituloBuscado().getText());
            libro.setGenero(actualizarLibroView.getTxtGeneroBuscado().getText());
            libro.setAutor((Autor) actualizarLibroView.getComboBoxAutores().getSelectedItem()); 
            libro.setSirestriccionEdad(actualizarLibroView.getRadioButtonRestriccion().isSelected());
            libro.setNumeroPaginas(numeroPaginas);
            libro.setIdioma(actualizarLibroView.getTxtIdiomaBuscado().getText());

            if (libroDAO.actualizar(libro)) {
                mostrarInformacion(MensajeLibro.LIBRO_ACTUALIZADO.getTexto(mensajes), actualizarLibroView);
            } else {
                mostrarInformacion(MensajeLibro.ERROR_ACTUALIZAR.getTexto(mensajes), actualizarLibroView); 
            }

        } catch (NumberFormatException ex) {
            mostrarInformacion(MensajeLibro.ERR_NUM_PAGINAS.getTexto(mensajes), actualizarLibroView);
        } catch (ClassCastException | NullPointerException ex) {
            mostrarInformacion(MensajeLibro.ERR_AUTOR_VALIDO.getTexto(mensajes), actualizarLibroView);
        } catch (CamposVaciosException ex2) {
            mostrarInformacion(ex2.getMessage(), actualizarLibroView);
        }
    }

    private void buscarElimLibro() {
        Libro libro = libroDAO.buscar(eliminarLibroView.getTxtISBN().getText());

        if (libro == null) {
            mostrarInformacion(MensajeLibro.LIBRO_NO_ENCONTRADO.getTexto(mensajes), eliminarLibroView);
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
            mostrarInformacion(MensajeLibro.LIBRO_NO_ENCONTRADO.getTexto(mensajes), eliminarLibroView);
            return;
        }

        if (!confirmarAccion(MensajeLibro.CONFIRMAR_ELIMINAR.getTexto(mensajes), eliminarLibroView)) {
            return;
        }

        libroDAO.eliminar(libro.getISBN());
    }

    private void crearLibro() {
        try {

            int numeroPaginas = Integer.parseInt(crearLibroView.getTxtNumeroPaginas().getText());

            validarCamposCrearLibro(
                    crearLibroView.getTxtISBN().getText(),
                    (Autor) crearLibroView.getComboBoxAutores().getSelectedItem(),
                    crearLibroView.getTxtNombre().getText(),
                    crearLibroView.getTxtComboGenero().getSelectedItem().toString(),
                    crearLibroView.getRadioButtonRestriccion().isSelected(),
                    numeroPaginas,
                    crearLibroView.getTxtIdioma().getText());

            libroDAO.crear(new Libro(
                    crearLibroView.getTxtISBN().getText(),
                    (Autor) crearLibroView.getComboBoxAutores().getSelectedItem(),
                    crearLibroView.getTxtNombre().getText(),
                    crearLibroView.getTxtComboGenero().getSelectedItem().toString(),
                    crearLibroView.getRadioButtonRestriccion().isSelected(),
                    numeroPaginas,
                    crearLibroView.getTxtIdioma().getText(),
                    false));

            mostrarInformacion(MensajeLibro.LIBRO_CREADO.getTexto(mensajes), crearLibroView);

        } catch (NumberFormatException ex) {
            mostrarInformacion(MensajeLibro.ERR_NUM_PAGINAS.getTexto(mensajes), crearLibroView);
        } catch (ClassCastException | NullPointerException ex) {
            mostrarInformacion(MensajeLibro.ERR_AUTOR_GENERO_VALIDOS.getTexto(mensajes), crearLibroView);
        } catch (CamposVaciosException ex2) {
            mostrarInformacion(ex2.getMessage(), crearLibroView);
        }
    }

    private void buscarLibro() {
        Libro libro = libroDAO.buscar(buscarLibroView.getTxtISBN().getText());

        if (libro == null) {
            mostrarInformacion(MensajeLibro.LIBRO_NO_ENCONTRADO.getTexto(mensajes), buscarLibroView);
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

            modelo.addRow(new Object[] {
                    libro.getNombre(),
                    libro.getISBN(),
                    libro.getAutor(),
                    libro.isSirestriccionEdad() ? restriccionEdad : restriccionEdadNo,
                    libro.getGenero(),
                    libro.getIdioma(), });
        }
    }

    public void cambioIdioma(ResourceBundle bundle) {
        // Actualizamos los arreglos globales antes de cambiar el texto de las vistas
        mensajes = bundle.getString("mensajesLibro").split(",");
        generos = bundle.getString("comboBoxGenero").split(",");

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

        // ===== COMBO BOX (Uso del Enum Genero) =====
        crearLibroView.getTxtComboGenero().removeAllItems();
        for (Genero generoEnum : Genero.values()) {
            crearLibroView.getTxtComboGenero().addItem(generoEnum.getTexto(generos));
        }
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

    public boolean confirmarAccion(String mensaje, JInternalFrame frame) {
        int opcion = JOptionPane.showConfirmDialog(
                frame,
                mensaje,
                "Confirmación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        return opcion == JOptionPane.YES_OPTION;
    }

    public void validarCamposCrearLibro(String ISBN, Autor autor, String nombre, String genero, boolean restriccion,
            int numeroPaginas, String idioma)
            throws CamposVaciosException {

        if (ISBN == null || ISBN.trim().isEmpty()) {
            throw new CamposVaciosException(MensajeLibro.REQ_ISBN.getTexto(mensajes));
        }

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new CamposVaciosException(MensajeLibro.REQ_TITULO.getTexto(mensajes));
        }

        if (autor == null) {
            throw new CamposVaciosException(MensajeLibro.REQ_AUTOR.getTexto(mensajes));
        }

        if (genero == null || genero.trim().isEmpty()) {
            throw new CamposVaciosException(MensajeLibro.REQ_GENERO.getTexto(mensajes));
        }

        if (idioma == null || idioma.trim().isEmpty()) {
            throw new CamposVaciosException(MensajeLibro.REQ_IDIOMA.getTexto(mensajes));
        }

        if (numeroPaginas <= 0) {
            throw new CamposVaciosException(MensajeLibro.ERR_PAGINAS_MAYOR_CERO.getTexto(mensajes));
        }
    }

    public void validarCamposActualizar(String nombre, String genero, boolean restriccion, int numeroPaginas, String idioma)
            throws CamposVaciosException {
        
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new CamposVaciosException(MensajeLibro.REQ_TITULO_ACT.getTexto(mensajes));
        }

        if (genero == null || genero.trim().isEmpty()) {
            throw new CamposVaciosException(MensajeLibro.REQ_GENERO_ACT.getTexto(mensajes));
        }

        if (idioma == null || idioma.trim().isEmpty()) {
            throw new CamposVaciosException(MensajeLibro.REQ_IDIOMA_ACT.getTexto(mensajes));
        }

        if (numeroPaginas <= 0) {
            throw new CamposVaciosException(MensajeLibro.ERR_PAGINAS_MAYOR_CERO.getTexto(mensajes));
        }
    }

    
    public void cargarGeneros() {
        crearLibroView.getTxtComboGenero().removeAllItems();
        for (Genero generoEnum : Genero.values()) {
            crearLibroView.getTxtComboGenero().addItem(generoEnum.getTexto(generos));
        }
    }
    public void actualizarComboAutores() {
        crearLibroView.getComboBoxAutores().removeAllItems();
        actualizarLibroView.getComboBoxAutores().removeAllItems();

        if (autorDAO == null) return;

        java.util.List<Autor> autores = autorDAO.listar();
        if (autores == null || autores.isEmpty()) {
            return;
        }

        for (Autor autor : autores) {
            crearLibroView.getComboBoxAutores().addItem(autor);
            actualizarLibroView.getComboBoxAutores().addItem(autor);
        }
    }
    
    public ActualizarLibroView getActualizarLibroView() {
        return actualizarLibroView;
    }

    public void setActualizarLibroView(ActualizarLibroView actualizarLibroView) {
        this.actualizarLibroView = actualizarLibroView;
    }

    public BuscarLibroView getBuscarLibroView() {
        return buscarLibroView;
    }

    public void setBuscarLibroView(BuscarLibroView buscarLibroView) {
        this.buscarLibroView = buscarLibroView;
    }

    public EliminarLibroView getEliminarLibroView() {
        return eliminarLibroView;
    }

    public void setEliminarLibroView(EliminarLibroView eliminarLibroView) {
        this.eliminarLibroView = eliminarLibroView;
    }

    public CrearLibroView getCrearLibroView() {
        return crearLibroView;
    }

    public void setCrearLibroView(CrearLibroView crearLibroView) {
        this.crearLibroView = crearLibroView;
    }

    public ListarLibroView getListarLibroView() {
        return listarLibroView;
    }

    public void setListarLibroView(ListarLibroView listarLibroView) {
        this.listarLibroView = listarLibroView;
    }

    public LibroDAO getLibroDAO() {
        return libroDAO;
    }

    public void setLibroDAO(LibroDAO libroDAO) {
        this.libroDAO = libroDAO;
    }

    public String getRestriccionEdad() {
        return restriccionEdad;
    }

    public void setRestriccionEdad(String restriccionEdad) {
        this.restriccionEdad = restriccionEdad;
    }

    public String getRestriccionEdadNo() {
        return restriccionEdadNo;
    }

    public void setRestriccionEdadNo(String restriccionEdadNo) {
        this.restriccionEdadNo = restriccionEdadNo;
    }

    public String[] getGeneros() {
        return generos;
    }

    public void setGeneros(String[] generos) {
        this.generos = generos;
    }

    public String[] getMensajes() {
        return mensajes;
    }

    public void setMensajes(String[] mensajes) {
        this.mensajes = mensajes;
    }
    public void setAutorDAO(AutorDAO autorDAO) {
        this.autorDAO = autorDAO;
        actualizarComboAutores(); // Sincroniza los combos inmediatamente con el nuevo DAO
    }

   
}