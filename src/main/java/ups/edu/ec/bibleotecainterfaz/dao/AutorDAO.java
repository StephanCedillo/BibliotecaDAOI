/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ups.edu.ec.bibleotecainterfaz.dao;

import java.util.List;
import ups.edu.ec.bibleotecainterfaz.models.Autor;
import ups.edu.ec.bibleotecainterfaz.models.Libro;

/**
 *
 * @author stephancedillo
 */
public interface AutorDAO {
    void crear(Autor autor);
    Autor buscar(Autor autor);
    boolean actualizar(Autor autorOriginal, Autor autor);
    boolean eliminar(Autor autor);
    List<Autor> listar();
    void crearListadoTemporal(int cantidad);
   
}
