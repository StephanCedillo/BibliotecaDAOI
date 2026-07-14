/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ups.edu.ec.bibleotecainterfaz.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import ups.edu.ec.bibleotecainterfaz.models.Autor;

/**
 *
 * @author stephancedillo
 */
public class AutorDAOArchivo implements AutorDAO {

    private File rutaDirecion;
    private static final int TAM_NOMBRE = 10;
    private static final int TAM_APELLIDO = 15;
    private static final int TAM_REGISTRO = (TAM_NOMBRE + TAM_APELLIDO) * 2;

    public AutorDAOArchivo() {
        try {
            String home = System.getProperty("user.home"); // OBTIENE LA RUTA PRINCIPAL

            rutaDirecion = new File(home + File.separator + "Archivos" + File.separator + "Biblioteca");

            if (!rutaDirecion.exists()) {
                rutaDirecion.mkdirs();
            }

            File archivo = new File(rutaDirecion, "autores.txt");

            if (!archivo.exists()) {
                archivo.createNewFile();
            }

        } catch (IOException ex) {
            System.out.println("Error de lectura/escritura: " + ex.getMessage());
        }

    }

    @Override
    public void crear(Autor autor) {

        try (RandomAccessFile archivo = new RandomAccessFile(
                new File(rutaDirecion, "autores.txt"), "rw")) {

            archivo.seek(archivo.length());

            String nombre = completarTexto(autor.getNombre(), TAM_NOMBRE);
            String apellido = completarTexto(autor.getApellido(), TAM_APELLIDO);

            archivo.writeChars(nombre);
            archivo.writeChars(apellido);

        } catch (IOException e) {
            System.out.println("Error al crear: " + e.getMessage());
        }
    }

    private String completarTexto(String texto, int tamaño) {

        if (texto.length() > tamaño) {
            return texto.substring(0, tamaño);
        }

        while (texto.length() < tamaño) {
            texto += " ";
        }

        return texto;
    }

    @Override
    public Autor buscar(Autor autor) {

        try (RandomAccessFile archivo = new RandomAccessFile(
                new File(rutaDirecion, "autores.txt"), "r")) {

            while (archivo.getFilePointer() < archivo.length()) {

                String nombre = leerTexto(archivo, TAM_NOMBRE);
                String apellido = leerTexto(archivo, TAM_APELLIDO);

                if (nombre.trim().equals(autor.getNombre())
                        && apellido.trim().equals(autor.getApellido())) {

                    return new Autor(nombre.trim(), apellido.trim());
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    private String leerTexto(RandomAccessFile archivo, int tamaño)
            throws IOException {

        String texto = "";

        for (int i = 0; i < tamaño; i++) {
            texto += archivo.readChar();
        }

        return texto;
    }

    @Override
    public boolean actualizar(Autor autorOriginal, Autor autor) {

        try (RandomAccessFile archivo = new RandomAccessFile(
                new File(rutaDirecion, "autores.txt"), "rw")) {

            while (archivo.getFilePointer() < archivo.length()) {

                long posicion = archivo.getFilePointer();

                String nombre = leerTexto(archivo, TAM_NOMBRE);
                String apellido = leerTexto(archivo, TAM_APELLIDO);

                if (nombre.trim().equals(autorOriginal.getNombre())
                        && apellido.trim().equals(autorOriginal.getApellido())) {

                    archivo.seek(posicion);

                    archivo.writeChars(
                            completarTexto(autor.getNombre(), TAM_NOMBRE)
                    );

                    archivo.writeChars(
                            completarTexto(autor.getApellido(), TAM_APELLIDO)
                    );

                    return true;
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    @Override
    public List<Autor> listar() {

        List<Autor> lista = new ArrayList<>();

        try (RandomAccessFile archivo = new RandomAccessFile(
                new File(rutaDirecion, "autores.txt"), "r")) {

            while (archivo.getFilePointer() < archivo.length()) {

                String nombre = leerTexto(archivo, TAM_NOMBRE);
                String apellido = leerTexto(archivo, TAM_APELLIDO);

                lista.add(new Autor(
                        nombre.trim(),
                        apellido.trim()
                ));
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return lista;
    }

    public void crearListadoTemporal(int cantidad) {
    }

    @Override
    public boolean eliminar(Autor autor) {

        File archivoOriginal = new File(rutaDirecion, "autores.txt");
        File archivoTemporal = new File(rutaDirecion, "temp.txt");

        boolean eliminado = false;

        try (
                RandomAccessFile lectura = new RandomAccessFile(archivoOriginal, "r"); RandomAccessFile escritura = new RandomAccessFile(archivoTemporal, "rw")) {

            while (lectura.getFilePointer() < lectura.length()) {

                String nombre = leerTexto(lectura, TAM_NOMBRE);
                String apellido = leerTexto(lectura, TAM_APELLIDO);

                // Si encontramos el autor, no lo copiamos
                if (nombre.trim().equals(autor.getNombre())
                        && apellido.trim().equals(autor.getApellido())) {

                    eliminado = true;
                    continue;
                }

                escritura.writeChars(
                        completarTexto(nombre.trim(), TAM_NOMBRE)
                );

                escritura.writeChars(
                        completarTexto(apellido.trim(), TAM_APELLIDO)
                );
            }

        } catch (IOException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }

        // Reemplazar archivo original por el temporal
        if (eliminado) {

            if (archivoOriginal.delete()) {
                archivoTemporal.renameTo(archivoOriginal);
            }

        } else {
            archivoTemporal.delete();
        }

        return eliminado;
    }

 
}
