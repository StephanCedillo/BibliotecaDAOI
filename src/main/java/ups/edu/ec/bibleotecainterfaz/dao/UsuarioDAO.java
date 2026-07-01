/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ups.edu.ec.bibleotecainterfaz.dao;

import java.util.List;

import ups.edu.ec.bibleotecainterfaz.models.Usuario;

/**
 *
 * @author stephancedillo
 */
public interface UsuarioDAO {
    void crear(Usuario usuario);
    Usuario buscar(String cedula);
    boolean actualizar(Usuario usuario);
    boolean eliminar(String cedula);
    List<Usuario> listar();
    void crearListadoTemporal(int cantidad);
    
}
