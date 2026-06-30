/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ups.edu.ec.bibleotecainterfaz.dao;

import java.util.List;
import ups.edu.ec.bibleotecainterfaz.models.Libro;

/**
 *
 * @author stephancedillo
 */
public interface LibroDAO {
    void crear(Libro libro);
    Libro buscar(String ISBN);
    boolean actualizar(Libro libro);
    boolean eliminar(String ISBN);
    List<Libro> listar();
    void crearListadoTemporal(int cantidad);
    
}
