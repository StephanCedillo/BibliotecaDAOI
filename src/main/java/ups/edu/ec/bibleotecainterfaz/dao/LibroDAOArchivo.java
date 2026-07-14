/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ups.edu.ec.bibleotecainterfaz.dao;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import ups.edu.ec.bibleotecainterfaz.models.Autor;
import ups.edu.ec.bibleotecainterfaz.models.Libro;

/**
 *
 * @author stephancedillo
 */
public class LibroDAOArchivo implements LibroDAO {

    private final File archivoLibro;

    private static final int TAM_ISBN = 14;
    private static final int TAM_AUTOR = 25;
    private static final int TAM_NOMBRE = 10;
    private static final int TAM_GENERO = 16;
    private static final int TAM_IDIOMA = 12;

    public LibroDAOArchivo() {

        File ruta = new File(System.getProperty("user.home")
                + File.separator + "Archivos"
                + File.separator + "Biblioteca");

        if (!ruta.exists()) {
            ruta.mkdirs();
        }

        archivoLibro = new File(ruta, "libros.dat");

        try {
            if (!archivoLibro.exists()) {
                archivoLibro.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void crear(Libro libro) {

        try (RandomAccessFile archivo
                = new RandomAccessFile(archivoLibro, "rw")) {

            archivo.seek(archivo.length());

            archivo.writeChars(
                    completarTexto(libro.getISBN(), TAM_ISBN)
            );

            Autor autor = libro.getAutor();

            archivo.writeChars(
                    completarTexto(
                            autor.getNombre() + " " + autor.getApellido(),
                            TAM_AUTOR
                    )
            );

            archivo.writeChars(
                    completarTexto(libro.getNombre(), TAM_NOMBRE)
            );

            archivo.writeChars(
                    completarTexto(libro.getGenero(), TAM_GENERO)
            );

            archivo.writeBoolean(
                    libro.isSirestriccionEdad()
            );

            archivo.writeInt(
                    libro.getNumeroPaginas()
            );

            archivo.writeChars(
                    completarTexto(libro.getIdioma(), TAM_IDIOMA)
            );

            archivo.writeBoolean(
                    libro.isSiestadoDisponibilidad()
            );

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public Libro buscar(String ISBN) {

        try (RandomAccessFile archivo
                = new RandomAccessFile(archivoLibro, "r")) {

            while (archivo.getFilePointer() < archivo.length()) {

                String isbn = leerTexto(archivo, TAM_ISBN);

                String autorTexto = leerTexto(archivo, TAM_AUTOR);

                String nombre = leerTexto(archivo, TAM_NOMBRE);

                String genero = leerTexto(archivo, TAM_GENERO);

                boolean restriccion = archivo.readBoolean();

                int paginas = archivo.readInt();

                String idioma = leerTexto(archivo, TAM_IDIOMA);

                boolean disponible = archivo.readBoolean();

                if (isbn.trim().equals(ISBN)) {

                    String[] datosAutor = autorTexto.trim().split(" ");

                    Autor autor = new Autor(
                            datosAutor[0],
                            datosAutor.length > 1 ? datosAutor[1] : ""
                    );

                    Libro libro = new Libro(
                            isbn.trim(),
                            autor,
                            nombre.trim(),
                            genero.trim(),
                            restriccion,
                            paginas,
                            idioma.trim(),
                            disponible
                    );

                    return libro;
                }

            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public boolean actualizar(Libro libro) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean eliminar(String ISBN) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Libro> listar() {

        List<Libro> lista = new ArrayList<>();

        try (RandomAccessFile archivo
                = new RandomAccessFile(archivoLibro, "r")) {

            while (archivo.getFilePointer() < archivo.length()) {

                String isbn = leerTexto(archivo, TAM_ISBN);

                String autorTexto = leerTexto(archivo, TAM_AUTOR);

                String nombre = leerTexto(archivo, TAM_NOMBRE);

                String genero = leerTexto(archivo, TAM_GENERO);

                boolean restriccion = archivo.readBoolean();

                int paginas = archivo.readInt();

                String idioma = leerTexto(archivo, TAM_IDIOMA);

                boolean disponible = archivo.readBoolean();

                String[] a = autorTexto.trim().split(" ");

                Autor autor = new Autor(
                        a[0],
                        a.length > 1 ? a[1] : ""
                );

                lista.add(new Libro(
                        isbn.trim(),
                        autor,
                        nombre.trim(),
                        genero.trim(),
                        restriccion,
                        paginas,
                        idioma.trim(),
                        disponible
                ));

            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return lista;
    }

    @Override
    public void crearListadoTemporal(int cantidad) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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

    private String leerTexto(RandomAccessFile archivo, int tamaño)
            throws IOException {

        String texto = "";

        for (int i = 0; i < tamaño; i++) {
            texto += archivo.readChar();
        }

        return texto;
    }
}
