/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ups.edu.ec.bibleotecainterfaz.dao;

import java.io.File;
import java.io.IOException;
import java.util.List;
import ups.edu.ec.bibleotecainterfaz.models.Prestamo;

/**
 *
 * @author stephancedillo
 */
public class PrestamoDAOArchivo implements PrestamoDAO{

    public PrestamoDAOArchivo() {
          try {
            String home = System.getProperty("user.home"); // OBTIENE LA RUTA PRINCIPAL

            File ruta = new File(home + File.separator + "Archivos" + File.separator + "Biblioteca");

            if (!ruta.exists()) {
                ruta.mkdirs(); // Crea toda la ruta si no existe
            }

            File archivo = new File(ruta, "prestamos.txt");

            if (!archivo.exists()) {
                archivo.createNewFile();
            }

        } catch (IOException ex) {
            System.out.println("Error de lectura/escritura: " + ex.getMessage());
        }
    }

    
    @Override
    public void crear(Prestamo prestamo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Prestamo buscarID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Prestamo buscarISBN(String ISBN) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Prestamo buscarCedula(String cedula) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean devolucion(Prestamo prestamo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Prestamo> listar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void crearListadoTemporal(int cantidad) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
