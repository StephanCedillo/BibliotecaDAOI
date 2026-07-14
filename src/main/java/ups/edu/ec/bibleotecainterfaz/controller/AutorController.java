
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ups.edu.ec.bibleotecainterfaz.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import ups.edu.ec.bibleotecainterfaz.dao.AutorDAO;
import ups.edu.ec.bibleotecainterfaz.excepciones.CamposVaciosException;
import ups.edu.ec.bibleotecainterfaz.models.Autor;
import ups.edu.ec.bibleotecainterfaz.view.ActualizarAutorView;
import ups.edu.ec.bibleotecainterfaz.view.BuscarAutorView;
import ups.edu.ec.bibleotecainterfaz.view.CrearAutorView;
import ups.edu.ec.bibleotecainterfaz.view.EliminarAutorView;
import ups.edu.ec.bibleotecainterfaz.view.ListarAutorView;

/**
 *
 * @author stephancedillo
 */
public class AutorController {

    private CrearAutorView crearAutorView;
    private ActualizarAutorView actualizarAutorView;
    private EliminarAutorView eliminarAutorView;
    private BuscarAutorView buscarAutorView;
    private ListarAutorView listarAutorView;
    private AutorDAO autorDAO;

    public AutorController(CrearAutorView crearAutorView, ActualizarAutorView actualizarAutorView,
            EliminarAutorView eliminarAutorView, BuscarAutorView buscarAutorView, ListarAutorView listarAutorView,
            AutorDAO autorDAO) {
        this.crearAutorView = crearAutorView;
        this.actualizarAutorView = actualizarAutorView;
        this.eliminarAutorView = eliminarAutorView;
        this.buscarAutorView = buscarAutorView;
        this.listarAutorView = listarAutorView;
        this.autorDAO = autorDAO;
        configurarEventos();

    }

    private String[] mensajes = {
            "No se encontró el autor",
            "Autor actualizado correctamente",
            "No se pudo actualizar el autor",
            "¿Está seguro de eliminar este autor?",
            "Autor eliminado correctamente",
            "No se pudo eliminar el autor",
            "Autor creado correctamente"
    };

    

    private void configurarEventos() {
        configurarEventosActualizarAutor();
        configurarEventosBuscarAutor();
        configurarEventosEliminarAutor();
        configurarEventosCrearAutor();
        configurarEventosListarAutor();

    }

    private void configurarEventosListarAutor() {
        listarAutorView.getBtnListarAutor().addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        listarAutor();
                    }
                });
        listarAutor();
    }

    private void configurarEventosActualizarAutor() {
        actualizarAutorView.getBtnBuscar().addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        buscarActAutor();
                    }
                });
        actualizarAutorView.getBtnActualizacion().addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        actualizarAutor();
                    }
                });
        actualizarAutores();
    }

    private void configurarEventosEliminarAutor() {
        eliminarAutorView.getBtnBuscar().addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        buscarElimAutor();
                    }
                });
        eliminarAutorView.getBtnEliminar().addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        eliminarAutor();
                    }
                });
        actualizarAutores();

    }

    private void configurarEventosCrearAutor() {
        crearAutorView.getBtnCrearAutor().addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        crearAutor();
                    }
                });

    }

    private void configurarEventosBuscarAutor() {
        buscarAutorView.getBtnBuscar().addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        buscarAutor();
                    }
                });
        actualizarAutores();
    }

    private void actualizarAutores() {
        actualizarAutorView.getComboBoxAutores1().removeAllItems();
        buscarAutorView.getComboBoxAutores1().removeAllItems();
        eliminarAutorView.getComboBoxAutores1().removeAllItems();
        if (autorDAO.listar().size() == 0) {
            return;
        }
        for (Autor autor : autorDAO.listar()) {
            actualizarAutorView.getComboBoxAutores1().addItem(autor);
            buscarAutorView.getComboBoxAutores1().addItem(autor);
            eliminarAutorView.getComboBoxAutores1().addItem(autor);

        }

    }

    private void buscarActAutor() {

        Autor autor = autorDAO.buscar((Autor) actualizarAutorView.getComboBoxAutores1().getSelectedItem());

        if (autor == null) {
            mostrarInformacion(mensajes[0], actualizarAutorView);
            return;
        }

        actualizarAutorView.getTxtNombreBuscado().setText(autor.getNombre());
        actualizarAutorView.getTxtApellidoBuscado().setText(autor.getApellido());

    }

   private void actualizarAutor() {
        try {
         
            Autor autor = autorDAO.buscar((Autor) actualizarAutorView.getComboBoxAutores1().getSelectedItem());

            if (autor == null) {
                mostrarInformacion(mensajes[0], actualizarAutorView);
                return;
            }

          
            String nuevoNombre = actualizarAutorView.getTxtNombreBuscado().getText();
            String nuevoApellido = actualizarAutorView.getTxtApellidoBuscado().getText();

           
            validarCamposActualizar(nuevoNombre, nuevoApellido);

         
            Autor autorOriginal = new Autor(autor.getNombre(), autor.getApellido());
            
            autor.setNombre(nuevoNombre);
            autor.setApellido(nuevoApellido);

          
            if (autorDAO.actualizar(autorOriginal, autor)) {
                mostrarInformacion(mensajes[1], actualizarAutorView); 
            } else {
                mostrarInformacion(mensajes[2], actualizarAutorView); 
            }

            actualizarAutores();
            listarAutor();

        } catch (CamposVaciosException ex) {
            mostrarInformacion(ex.getMessage(), actualizarAutorView);
        } catch (ClassCastException | NullPointerException ex) {
            mostrarInformacion("Debe seleccionar un autor válido de la lista antes de actualizar.", actualizarAutorView);
        }
    }

    private void buscarElimAutor() {
        Autor autor = autorDAO.buscar((Autor) eliminarAutorView.getComboBoxAutores1().getSelectedItem());

        if (autor == null) {
            mostrarInformacion(mensajes[0], eliminarAutorView);
            return;
        }

        eliminarAutorView.getTxtNombreBuscado().setText(autor.getNombre());
        eliminarAutorView.getTxtApellidoBuscado().setText(autor.getApellido());

    }

    private void eliminarAutor() {

        Autor autor = autorDAO.buscar(
                (Autor) eliminarAutorView.getComboBoxAutores1().getSelectedItem());

        if (autor == null) {
            mostrarInformacion(mensajes[0], eliminarAutorView);
            return;
        }

        if (!confirmarAccion(mensajes[3], eliminarAutorView)) {
            return;
        }

        if (autorDAO.eliminar(autor)) {
            mostrarInformacion(mensajes[4], eliminarAutorView);
        } else {
            mostrarInformacion(mensajes[5], eliminarAutorView);
        }

        eliminarAutorView.getTxtNombreBuscado().setText("");
        eliminarAutorView.getTxtApellidoBuscado().setText("");

        actualizarAutores();
        listarAutor();
    }

   private void crearAutor() {
        try {
         
            String nombre = crearAutorView.getTxtNombreAutor().getText();
            String apellido = crearAutorView.getTxtApellido().getText();

     
            validarCamposCrearAutor(nombre, apellido);

        
            autorDAO.crear(new Autor(nombre, apellido));

     
            mostrarInformacion(mensajes[6], crearAutorView); 
            
            crearAutorView.getTxtNombreAutor().setText("");
            crearAutorView.getTxtApellido().setText("");

            actualizarAutores();
            listarAutor();

        } catch (CamposVaciosException ex) {
           
            mostrarInformacion(ex.getMessage(), crearAutorView);
        }
    }

    private void buscarAutor() {
        Autor autor = autorDAO.buscar((Autor) buscarAutorView.getComboBoxAutores1().getSelectedItem());

        if (autor == null) {
            mostrarInformacion(mensajes[0], buscarAutorView);
            return;
        }
        buscarAutorView.getTxtNombreBuscado().setText(autor.getNombre());
        buscarAutorView.getTxtApellidoBuscado().setText(autor.getApellido());

    }

    private void listarAutor() {

        DefaultTableModel modelo = (DefaultTableModel) listarAutorView.getTblListarAutor().getModel();
        modelo.setRowCount(0);

        for (Autor autor : autorDAO.listar()) {

            modelo.addRow(new Object[] {
                    autor.getNombre(),
                    autor.getApellido() });
        }
    }

    public void cambioIdioma(ResourceBundle bundle) {
        cambioIdiomaActualizarAutor(bundle);
        cambioIdiomaBuscarAutor(bundle);
        cambioIdiomaEliminarAutor(bundle);
        cambioIdiomaCrearAutor(bundle);
        cambioIdiomaListarAutor(bundle);
        mensajes = bundle.getString("mensajesAutor").split(",");

    }

    private void cambioIdiomaCrearAutor(ResourceBundle bundle) {

    }

    private void cambioIdiomaEliminarAutor(ResourceBundle bundle) {

    }

    private void cambioIdiomaBuscarAutor(ResourceBundle bundle) {
    }

    private void cambioIdiomaActualizarAutor(ResourceBundle bundle) {
    }

    private void cambioIdiomaListarAutor(ResourceBundle bundle) {
    }

  
    private void configurarTabla(ResourceBundle bundle) {
        
    }
    
      public void validarCamposCrearAutor(String nombre, String apellido) throws CamposVaciosException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new CamposVaciosException("El nombre del autor no puede estar vacío.");
        }
        
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new CamposVaciosException("El apellido del autor no puede estar vacío.");
        }
    }

    public void validarCamposActualizar(String nombre, String apellido) throws CamposVaciosException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new CamposVaciosException("El nombre del autor no puede estar vacío para la actualización.");
        }
        
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new CamposVaciosException("El apellido del autor no puede estar vacío para la actualización.");
        }
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

   

    public CrearAutorView getCrearAutorView() {
        return crearAutorView;
    }

    public void setCrearAutorView(CrearAutorView crearAutorView) {
        this.crearAutorView = crearAutorView;
    }

    public ActualizarAutorView getActualizarAutorView() {
        return actualizarAutorView;
    }

    public void setActualizarAutorView(ActualizarAutorView actualizarAutorView) {
        this.actualizarAutorView = actualizarAutorView;
    }

    public EliminarAutorView getEliminarAutorView() {
        return eliminarAutorView;
    }

    public void setEliminarAutorView(EliminarAutorView eliminarAutorView) {
        this.eliminarAutorView = eliminarAutorView;
    }

    public BuscarAutorView getBuscarAutorView() {
        return buscarAutorView;
    }

    public void setBuscarAutorView(BuscarAutorView buscarAutorView) {
        this.buscarAutorView = buscarAutorView;
    }

    public ListarAutorView getListarAutorView() {
        return listarAutorView;
    }

    public void setListarAutorView(ListarAutorView listarAutorView) {
        this.listarAutorView = listarAutorView;
    }

    public AutorDAO getAutorDAO() {
        return autorDAO;
    }

    public void setAutorDAO(AutorDAO autorDAO) {
        this.autorDAO = autorDAO;
    }

    public String[] getMensajes() {
        return mensajes;
    }

    public void setMensajes(String[] mensajes) {
        this.mensajes = mensajes;
    }

}
