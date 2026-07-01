
package ups.edu.ec.bibleotecainterfaz.dao;

import java.util.ArrayList;
import java.util.List;

import ups.edu.ec.bibleotecainterfaz.models.Libro;
import ups.edu.ec.bibleotecainterfaz.models.Prestamo;


public class PrestamoDAOMemoria implements PrestamoDAO {

    private List<Prestamo> listaPrestamos;

    public PrestamoDAOMemoria() {
        listaPrestamos = new ArrayList<>();
    }

    @Override
    public void crear(Prestamo prestamo) {
        listaPrestamos.add(prestamo);
    }

    @Override
    public Prestamo buscarID(int id) {
        for (Prestamo prestamo : listaPrestamos) {
            if (prestamo.getId() == id) {
                return prestamo;
            }
        }
        return null;
    }

    @Override
    public Prestamo buscarISBN(String ISBN) {
        for (Prestamo prestamo : listaPrestamos) {
            for (Libro libro : prestamo.getLibro()) {
                if (libro.getISBN().equalsIgnoreCase(ISBN)) {
                    return prestamo;
                }
            }

        }
        return null;
    }

    @Override
    public Prestamo buscarCedula(String cedula) {
        for (Prestamo prestamo : listaPrestamos) {
            if (prestamo.getUsuario().getCedula().equalsIgnoreCase(cedula)) {
                return prestamo;
            }
        }
        return null;
    }

    @Override
    public boolean devolucion(Prestamo prestamo) {
        Boolean cambio =false;
        for (Libro libro : prestamo.getLibro()) {
           libro.devolver();
           cambio= true;
        }
        return cambio;
    }

    

    @Override
    public List<Prestamo> listar() {
       return listaPrestamos;
    }

    // PRUEBAS IMPLEMENTACION
    @Override
    public void crearListadoTemporal(int cantidad) {
       
    }



}
