/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ups.edu.ec.bibleotecainterfaz.dao;

import java.util.ArrayList;
import java.util.List;

import ups.edu.ec.bibleotecainterfaz.models.Libro;

/**
 *
 * @author stephancedillo
 */
public class LibroDAOMemoria implements LibroDAO{

     private List<Libro> listaLibro;
     
    @Override
    public void crear(Libro libro) {
       listaLibro = new ArrayList<>();
    }

    @Override
    public Libro buscar(String ISBN) {
        for (Libro libro : listaLibro) {
            if (libro.getISBN().equalsIgnoreCase(ISBN)) {
                return libro;
            }
        }
       return null;
    }

    @Override
    public boolean actualizar(Libro libro) {
        for (int i = 0; i < listaLibro.size(); i++) {
            Libro libroEncontrado = listaLibro.get(i);
            if (libroEncontrado.getISBN() == libro.getISBN()) {
                listaLibro.set(i, libro);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean eliminar(String ISBN) {
     Libro libroEncontrado = buscar(ISBN);
        if (libroEncontrado != null) {
            listaLibro.remove(libroEncontrado);
            return true;
        }
        return false;

    }

    @Override
    public List<Libro> listar() {
        return listaLibro;
    }

    // SECCION DE PRUEBAS
    @Override
    public void crearListadoTemporal(int cantidad) {
      
    }
    
}
