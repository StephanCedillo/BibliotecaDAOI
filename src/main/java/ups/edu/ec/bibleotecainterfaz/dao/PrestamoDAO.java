/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ups.edu.ec.bibleotecainterfaz.dao;

import java.util.List;

import ups.edu.ec.bibleotecainterfaz.models.Prestamo;


/**
 *
 * @author stephancedillo
 */
public interface PrestamoDAO {
    void crear(Prestamo prestamo);
    Prestamo buscarID(int cedula);
    Prestamo buscarISBN(String cedula);
    Prestamo buscarCedula(String cedula);
    boolean actualizar(Prestamo prestamo);
    boolean eliminar(String cedula);
    List<Prestamo> listar();
    void crearListadoTemporal(int cantidad);
}
