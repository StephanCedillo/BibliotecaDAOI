/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ups.edu.ec.bibleotecainterfaz.dao;

import java.util.List;
import ups.edu.ec.bibleotecainterfaz.models.Libro;
import ups.edu.ec.bibleotecainterfaz.models.Autor;

/**
 *
 * @author stephancedillo
 */
public interface LibroDAO {
    void crear(Libro libro);
    Libro buscar(String ISBN);
    void crearAutor(Autor autor);
    boolean actualizar(Libro libro);
    boolean eliminar(String ISBN);
    List<Libro> listar();
    List<Autor> listarAutores();
    void crearListadoTemporal(int cantidad);
    
}
