package ups.edu.ec.bibleotecainterfaz.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import ups.edu.ec.bibleotecainterfaz.dao.AutorDAO;
import ups.edu.ec.bibleotecainterfaz.enums.MensajeAutor;
import ups.edu.ec.bibleotecainterfaz.excepciones.CamposVaciosException;
import ups.edu.ec.bibleotecainterfaz.models.Autor;
import ups.edu.ec.bibleotecainterfaz.view.ActualizarAutorView;
import ups.edu.ec.bibleotecainterfaz.view.BuscarAutorView;
import ups.edu.ec.bibleotecainterfaz.view.CrearAutorView;
import ups.edu.ec.bibleotecainterfaz.view.EliminarAutorView;
import ups.edu.ec.bibleotecainterfaz.view.ListarAutorView;

/**
 *
 *
 * @author stephancedillo
 */
public class AutorController {

    private CrearAutorView crearAutorView;
    private ActualizarAutorView actualizarAutorView;
    private EliminarAutorView eliminarAutorView;
    private BuscarAutorView buscarAutorView;
    private ListarAutorView listarAutorView;
    private LibroController libroController;

    private AutorDAO autorDAO;

    private String[] mensajes = {
        "No se encontró el autor", // 0
        "Autor actualizado correctamente", // 1
        "No se pudo actualizar el autor", // 2
        "¿Está seguro de eliminar este autor?", // 3
        "Autor eliminado correctamente", // 4
        "No se pudo eliminar el autor", // 5
        "Autor creado correctamente", // 6
        "El nombre del autor no puede estar vacío.", // 7
        "El apellido del autor no puede estar vacío.", // 8
        "El nombre del autor no puede estar vacío para la actualización.", // 9
        "El apellido del autor no puede estar vacío para la actualización." // 10
    };

    public AutorController(
            CrearAutorView crearAutorView,
            ActualizarAutorView actualizarAutorView,
            EliminarAutorView eliminarAutorView,
            BuscarAutorView buscarAutorView,
            ListarAutorView listarAutorView,
            AutorDAO autorDAO,
            LibroController libroController
    ) {

        this.crearAutorView = crearAutorView;
        this.actualizarAutorView = actualizarAutorView;
        this.eliminarAutorView = eliminarAutorView;
        this.buscarAutorView = buscarAutorView;
        this.listarAutorView = listarAutorView;
        this.autorDAO = autorDAO;
        this.libroController = libroController;

        configurarEventos();

    }

    private void actualizarAutores() {
        actualizarAutorView.getComboBoxAutores1().removeAllItems();
        buscarAutorView.getComboBoxAutores1().removeAllItems();
        eliminarAutorView.getComboBoxAutores1().removeAllItems();

        if (autorDAO.listar().isEmpty()) {
            if (libroController != null) {
                libroController.actualizarComboAutores();
            }
            return;
        }

        for (Autor autor : autorDAO.listar()) {
            actualizarAutorView.getComboBoxAutores1().addItem(autor);
            buscarAutorView.getComboBoxAutores1().addItem(autor);
            eliminarAutorView.getComboBoxAutores1().addItem(autor);
        }

        if (libroController != null) {
            libroController.actualizarComboAutores();
        }
    }

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
        }
        );

        listarAutor();

    }

    private void listarAutor() {

        DefaultTableModel modelo
                = (DefaultTableModel) listarAutorView
                        .getTblListarAutor()
                        .getModel();

        modelo.setRowCount(0);

        for (Autor autor : autorDAO.listar()) {

            modelo.addRow(
                    new Object[]{
                        autor.getNombre(),
                        autor.getApellido()
                    }
            );
        }
    }

    private void configurarEventosActualizarAutor() {

        actualizarAutorView.getBtnBuscar().addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarActAutor();
            }
        }
        );

        actualizarAutorView.getBtnActualizacion().addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarAutor();
            }
        }
        );

        actualizarAutores();

    }

    private void configurarEventosEliminarAutor() {

        eliminarAutorView.getBtnBuscar().addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarElimAutor();
            }
        }
        );

        eliminarAutorView.getBtnEliminar().addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarAutor();
            }
        }
        );

        actualizarAutores();

    }

    private void configurarEventosCrearAutor() {

        crearAutorView.getBtnCrearAutor().addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearAutor();
            }
        }
        );

    }

    private void configurarEventosBuscarAutor() {

        buscarAutorView.getBtnBuscar().addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarAutor();
            }
        }
        );

        actualizarAutores();

    }

    private void buscarActAutor() {

        try {

            Autor autor = autorDAO.buscar(
                    (Autor) actualizarAutorView
                            .getComboBoxAutores1()
                            .getSelectedItem()
            );

            if (autor == null) {

                mostrarInformacion(
                        MensajeAutor.AUTOR_NO_ENCONTRADO
                                .getTexto(mensajes),
                        actualizarAutorView
                );

                return;
            }

            actualizarAutorView
                    .getTxtNombreBuscado()
                    .setText(autor.getNombre());

            actualizarAutorView
                    .getTxtApellidoBuscado()
                    .setText(autor.getApellido());

        } catch (ClassCastException | NullPointerException ex) {

            mostrarInformacion(
                    MensajeAutor.AUTOR_NO_ENCONTRADO
                            .getTexto(mensajes),
                    actualizarAutorView
            );
        }

    }

    private void actualizarAutor() {

        try {

            Autor autor = autorDAO.buscar(
                    (Autor) actualizarAutorView
                            .getComboBoxAutores1()
                            .getSelectedItem()
            );

            if (autor == null) {

                mostrarInformacion(
                        MensajeAutor.AUTOR_NO_ENCONTRADO
                                .getTexto(mensajes),
                        actualizarAutorView
                );

                return;
            }

            String nuevoNombre
                    = actualizarAutorView
                            .getTxtNombreBuscado()
                            .getText();

            String nuevoApellido
                    = actualizarAutorView
                            .getTxtApellidoBuscado()
                            .getText();

            validarCamposActualizar(
                    nuevoNombre,
                    nuevoApellido
            );

            Autor autorOriginal = new Autor(
                    autor.getNombre(),
                    autor.getApellido()
            );

            autor.setNombre(nuevoNombre);
            autor.setApellido(nuevoApellido);

            if (autorDAO.actualizar(
                    autorOriginal,
                    autor
            )) {

                mostrarInformacion(
                        MensajeAutor.AUTOR_ACTUALIZADO
                                .getTexto(mensajes),
                        actualizarAutorView
                );

            } else {

                mostrarInformacion(
                        MensajeAutor.ERROR_ACTUALIZAR
                                .getTexto(mensajes),
                        actualizarAutorView
                );
            }

            actualizarAutores();
            listarAutor();

        } catch (CamposVaciosException ex) {

            mostrarInformacion(
                    ex.getMessage(),
                    actualizarAutorView
            );

        } catch (ClassCastException | NullPointerException ex) {

            mostrarInformacion(
                    MensajeAutor.AUTOR_NO_ENCONTRADO
                            .getTexto(mensajes),
                    actualizarAutorView
            );
        }

    }

    private void buscarElimAutor() {

        try {

            Autor autor = autorDAO.buscar(
                    (Autor) eliminarAutorView
                            .getComboBoxAutores1()
                            .getSelectedItem()
            );

            if (autor == null) {

                mostrarInformacion(
                        MensajeAutor.AUTOR_NO_ENCONTRADO
                                .getTexto(mensajes),
                        eliminarAutorView
                );

                return;
            }

            eliminarAutorView
                    .getTxtNombreBuscado()
                    .setText(autor.getNombre());

            eliminarAutorView
                    .getTxtApellidoBuscado()
                    .setText(autor.getApellido());

        } catch (ClassCastException | NullPointerException ex) {

            mostrarInformacion(
                    MensajeAutor.AUTOR_NO_ENCONTRADO
                            .getTexto(mensajes),
                    eliminarAutorView
            );
        }

    }

    private void eliminarAutor() {

        try {

            Autor autor = autorDAO.buscar(
                    (Autor) eliminarAutorView
                            .getComboBoxAutores1()
                            .getSelectedItem()
            );

            if (autor == null) {

                mostrarInformacion(
                        MensajeAutor.AUTOR_NO_ENCONTRADO
                                .getTexto(mensajes),
                        eliminarAutorView
                );

                return;
            }

            if (!confirmarAccion(
                    MensajeAutor.CONFIRMAR_ELIMINAR
                            .getTexto(mensajes),
                    eliminarAutorView
            )) {

                return;
            }

            if (autorDAO.eliminar(autor)) {

                mostrarInformacion(
                        MensajeAutor.AUTOR_ELIMINADO
                                .getTexto(mensajes),
                        eliminarAutorView
                );

            } else {

                mostrarInformacion(
                        MensajeAutor.ERROR_ELIMINAR
                                .getTexto(mensajes),
                        eliminarAutorView
                );
            }

            eliminarAutorView
                    .getTxtNombreBuscado()
                    .setText("");

            eliminarAutorView
                    .getTxtApellidoBuscado()
                    .setText("");

            actualizarAutores();
            listarAutor();

        } catch (ClassCastException | NullPointerException ex) {

            mostrarInformacion(
                    MensajeAutor.AUTOR_NO_ENCONTRADO
                            .getTexto(mensajes),
                    eliminarAutorView
            );
        }

    }

    private void crearAutor() {

        try {

            String nombre
                    = crearAutorView
                            .getTxtNombreAutor()
                            .getText();

            String apellido
                    = crearAutorView
                            .getTxtApellido()
                            .getText();

            validarCamposCrearAutor(
                    nombre,
                    apellido
            );

            autorDAO.crear(
                    new Autor(
                            nombre,
                            apellido
                    )
            );

            mostrarInformacion(
                    MensajeAutor.AUTOR_CREADO
                            .getTexto(mensajes),
                    crearAutorView
            );

            crearAutorView
                    .getTxtNombreAutor()
                    .setText("");

            crearAutorView
                    .getTxtApellido()
                    .setText("");

            actualizarAutores();
            listarAutor();

        } catch (CamposVaciosException ex) {

            mostrarInformacion(
                    ex.getMessage(),
                    crearAutorView
            );
        }

    }

    private void buscarAutor() {

        try {

            Autor autor = autorDAO.buscar(
                    (Autor) buscarAutorView
                            .getComboBoxAutores1()
                            .getSelectedItem()
            );

            if (autor == null) {

                mostrarInformacion(
                        MensajeAutor.AUTOR_NO_ENCONTRADO
                                .getTexto(mensajes),
                        buscarAutorView
                );

                return;
            }

            buscarAutorView
                    .getTxtNombreBuscado()
                    .setText(autor.getNombre());

            buscarAutorView
                    .getTxtApellidoBuscado()
                    .setText(autor.getApellido());

        } catch (ClassCastException | NullPointerException ex) {

            mostrarInformacion(
                    MensajeAutor.AUTOR_NO_ENCONTRADO
                            .getTexto(mensajes),
                    buscarAutorView
            );
        }

    }

    public void cambioIdioma(ResourceBundle bundle) {

        mensajes = bundle.getString("mensajesAutor").split(",");

        cambioIdiomaActualizarAutor(bundle);
        cambioIdiomaBuscarAutor(bundle);
        cambioIdiomaEliminarAutor(bundle);
        cambioIdiomaCrearAutor(bundle);
        cambioIdiomaListarAutor(bundle);

    }

    private void cambioIdiomaCrearAutor(
            ResourceBundle bundle
    ) {

        crearAutorView
                .getBtnCrearAutor()
                .setText(
                        bundle.getString("btnCrearAutor")
                );

        crearAutorView.getLblNombre2()
                .setText(
                        bundle.getString("lblNombre")
                );

        crearAutorView
                .getLblApellido()
                .setText(
                        bundle.getString("lblApellido")
                );
        crearAutorView.getLblTituloCrearAutor().setText(bundle.getString("lblTituloCrearAutor"));
        crearAutorView.getLblPreguntaExistenciaAutor().setText(bundle.getString("lblAutorNoEnSistema"));

    }

    private void cambioIdiomaEliminarAutor(
            ResourceBundle bundle
    ) {

        eliminarAutorView
                .getBtnBuscar()
                .setText(
                        bundle.getString("btnBuscar")
                );

        eliminarAutorView
                .getBtnEliminar()
                .setText(
                        bundle.getString("btnEliminar")
                );
        
        eliminarAutorView.getLblTituloEliminarAutor().setText(bundle.getString("lblTituloEliminarAutor"));
        eliminarAutorView.getLblAutorBuscado().setText(bundle.getString("lblAutor"));

        eliminarAutorView
                .getLblNombre()
                .setText(
                        bundle.getString("lblNombre")
                );

        eliminarAutorView
                .getLblApellido()
                .setText(
                        bundle.getString("lblApellido")
                );

    }

    private void cambioIdiomaBuscarAutor(
            ResourceBundle bundle
    ) {

        buscarAutorView
                .getBtnBuscar()
                .setText(
                        bundle.getString("btnBuscar")
                );

        buscarAutorView
                .getLblNombre()
                .setText(
                        bundle.getString("lblNombre")
                );

        buscarAutorView
                .getLblApellido()
                .setText(
                        bundle.getString("lblApellido")
                );
        buscarAutorView.getLblTituloBusquedaAutor().setText(bundle.getString("lblTituloActualizarAutor"));
        buscarAutorView.getLblAutor().setText(bundle.getString("lblAutor"));

    }

    private void cambioIdiomaActualizarAutor(
            ResourceBundle bundle
    ) {

        actualizarAutorView
                .getBtnBuscar()
                .setText(
                        bundle.getString("btnBuscar")
                );

        actualizarAutorView
                .getBtnActualizacion()
                .setText(
                        bundle.getString("btnActualizacion")
                );

        actualizarAutorView
                .getLblNombre()
                .setText(
                        bundle.getString("lblNombre")
                );

        actualizarAutorView
                .getLblApellido()
                .setText(
                        bundle.getString("lblApellido")
                );
        actualizarAutorView.getLblTituloActualizarAutor().setText(bundle.getString("lblTituloActualizarAutor"));
        actualizarAutorView.getLblAutor().setText(bundle.getString("lblAutor"));

    }

    private void cambioIdiomaListarAutor(
            ResourceBundle bundle
    ) {

        listarAutorView
                .getBtnListarAutor()
                .setText(
                        bundle.getString("btnListarAutor")
                );

        configurarTabla(bundle);

    }

    private void configurarTabla(ResourceBundle bundle) {

        String[] columnas = bundle
                .getString("columnasAutor")
                .split(",");

        DefaultTableModel modelo
                = new DefaultTableModel(columnas, 0);

        listarAutorView
                .getTblListarAutor()
                .setModel(modelo);
    }

    public void validarCamposCrearAutor(
            String nombre,
            String apellido
    ) throws CamposVaciosException {

        if (nombre == null || nombre.trim().isEmpty()) {

            throw new CamposVaciosException(
                    MensajeAutor.REQ_NOMBRE
                            .getTexto(mensajes)
            );
        }

        if (apellido == null || apellido.trim().isEmpty()) {

            throw new CamposVaciosException(
                    MensajeAutor.REQ_APELLIDO
                            .getTexto(mensajes)
            );
        }

    }

    public void validarCamposActualizar(
            String nombre,
            String apellido
    ) throws CamposVaciosException {

        if (nombre == null || nombre.trim().isEmpty()) {

            throw new CamposVaciosException(
                    MensajeAutor.REQ_NOMBRE_ACT
                            .getTexto(mensajes)
            );
        }

        if (apellido == null || apellido.trim().isEmpty()) {

            throw new CamposVaciosException(
                    MensajeAutor.REQ_APELLIDO_ACT
                            .getTexto(mensajes)
            );
        }

    }

    public void mostrarInformacion(
            String mensaje,
            JInternalFrame frame
    ) {

        JOptionPane.showMessageDialog(
                frame,
                mensaje
        );

    }

    public boolean confirmarAccion(
            String mensaje,
            JInternalFrame frame
    ) {

        int opcion = JOptionPane.showConfirmDialog(
                frame,
                mensaje,
                "Confirmación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        return opcion == JOptionPane.YES_OPTION;

    }

    public CrearAutorView getCrearAutorView() {
        return crearAutorView;
    }

    public void setCrearAutorView(
            CrearAutorView crearAutorView
    ) {
        this.crearAutorView = crearAutorView;
    }

    public ActualizarAutorView getActualizarAutorView() {
        return actualizarAutorView;
    }

    public void setActualizarAutorView(
            ActualizarAutorView actualizarAutorView
    ) {
        this.actualizarAutorView = actualizarAutorView;
    }

    public EliminarAutorView getEliminarAutorView() {
        return eliminarAutorView;
    }

    public void setEliminarAutorView(
            EliminarAutorView eliminarAutorView
    ) {
        this.eliminarAutorView = eliminarAutorView;
    }

    public BuscarAutorView getBuscarAutorView() {
        return buscarAutorView;
    }

    public void setBuscarAutorView(
            BuscarAutorView buscarAutorView
    ) {
        this.buscarAutorView = buscarAutorView;
    }

    public ListarAutorView getListarAutorView() {
        return listarAutorView;
    }

    public void setListarAutorView(
            ListarAutorView listarAutorView
    ) {
        this.listarAutorView = listarAutorView;
    }

    public AutorDAO getAutorDAO() {
        return autorDAO;
    }

    public void setAutorDAO(
            AutorDAO autorDAO
    ) {
        this.autorDAO = autorDAO;
    }

    public String[] getMensajes() {
        return mensajes;
    }

    public void setMensajes(
            String[] mensajes
    ) {
        this.mensajes = mensajes;
    }

}
