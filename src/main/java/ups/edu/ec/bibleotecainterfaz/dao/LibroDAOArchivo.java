
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
    private static final String NOMBRE_ARCHIVO = "libros.ups";
    private static final int TAM_ISBN = 14;
    private static final int TAM_AUTORNOMBRE = 10;
    private static final int TAM_AUTORAPELLIDO = 15;
    private static final int TAM_NOMBRE = 10;
    private static final int TAM_GENERO = 16;
    private static final int TAM_IDIOMA = 12;
    private static final int TAM_LIBRO = 160;

    public LibroDAOArchivo() {

        File ruta = new File(System.getProperty("user.home")
                + File.separator + "Archivos"
                + File.separator + "Biblioteca");

        if (!ruta.exists()) {
            ruta.mkdirs();
        }

        archivoLibro = new File(ruta, NOMBRE_ARCHIVO);

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
        try (RandomAccessFile archivo = new RandomAccessFile(archivoLibro, "rw")) {
            archivo.seek(archivo.length());
            escribirRegistro(archivo, libro);
        } catch (IOException e) {
            System.out.println("No se pudo crear el usuario" + e.getMessage());
        }
    }

    @Override
    public Libro buscar(String ISBN) {

        try (RandomAccessFile archivo = new RandomAccessFile(archivoLibro, "r")) {
            
            long tamArchivo = archivo.length();
            long totalRegistros = tamArchivo/TAM_LIBRO;
            for(int i = 0;i<totalRegistros;i++){
                long incioBusqueda = i*tamArchivo;
                
                String isbnActual = leerTexto(archivo,TAM_ISBN).trim();
                if(ISBN.equals(isbnActual)){
                    archivo.seek(incioBusqueda);
                    return leerRegistro(archivo);
                }
            }
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public boolean actualizar(Libro libro) {
        try (RandomAccessFile archivo = new RandomAccessFile(archivoLibro, "r")) {
            long tamArchivo = archivo.length();
            long totalRegistros = tamArchivo/TAM_LIBRO;
            
            for(int i = 0;i<totalRegistros;i++){
                
                long incioBusqueda = i*tamArchivo;
                String isbnActual = leerTexto(archivo,TAM_ISBN).trim();
                
                if(libro.getISBN().equals(isbnActual)){
                    archivo.seek(incioBusqueda);
                    escribirRegistro(archivo,libro);
                    return true;
                }
            }
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean eliminar(String ISBN) {
        File archivoOriginal = new File(archivoLibro, NOMBRE_ARCHIVO);
        File archivoTemporal = new File(archivoLibro, "temp.ups");
        boolean eliminado = false;
        try(RandomAccessFile lectura =  new RandomAccessFile(archivoOriginal,"r");RandomAccessFile escritura =  new RandomAccessFile(archivoTemporal,"rw")){
            
            long tamArchivo = lectura.length();
            long totalRegistros = tamArchivo/TAM_LIBRO;
            
            for(int i = 0;i < totalRegistros; i++){
                lectura.seek(i * TAM_LIBRO);
                Libro libroActual = leerRegistro(lectura);
                
                if(libroActual.getISBN().equals(ISBN)){
                    
                    eliminado = true;
                    continue;
                }
                
                escribirRegistro(escritura,libroActual);
                
            }        
        }catch(IOException e){
            System.out.println("Error al eliminar:  " + e.getMessage() );  
        }
        if(eliminado){
            
            if(archivoOriginal.delete()){
                
                if(!archivoTemporal.renameTo(archivoOriginal)){
                    
                    System.out.println("No se pudo renombrar el archivo");
                }
            }else{
                System.out.println("No se pudo elimnar el archvio original");
            }
        }else{
           archivoTemporal.delete();
        }
        return eliminado;
        
    }

    @Override
    public List<Libro> listar() {

        List<Libro> lista = new ArrayList<>();

        try (RandomAccessFile archivo = new RandomAccessFile(archivoLibro, "r")) {
            
            long tamArchivo = archivo.length();
            long numRegistros = tamArchivo/TAM_LIBRO;
            for(int i = 0; i < numRegistros; i++){
                archivo.seek(i * TAM_LIBRO);
                lista.add(leerRegistro(archivo));
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

    private void escribirRegistro(RandomAccessFile archivo, Libro libro) throws IOException {
        // DATOS LIBRO
        
        archivo.writeChars(completarTexto(libro.getISBN(),TAM_ISBN));
        
        // DATOS DEL AUTOR
        
        archivo.writeChars(completarTexto(libro.getAutor().getNombre(),TAM_AUTORNOMBRE));
        archivo.writeChars(completarTexto(libro.getAutor().getApellido(),TAM_AUTORAPELLIDO));
        
        // CONTINUACION DATOS LIBRO
        
        archivo.writeChars(completarTexto(libro.getNombre(),TAM_NOMBRE));
        archivo.writeChars(completarTexto(libro.getGenero(),TAM_GENERO));
        archivo.writeBoolean(libro.isSirestriccionEdad());
        archivo.writeInt(libro.getNumeroPaginas());
        archivo.writeChars(completarTexto(libro.getIdioma(),TAM_IDIOMA));
        archivo.writeBoolean(libro.isSiestadoDisponibilidad());
        
    }
    private Libro leerRegistro(RandomAccessFile archivo)throws IOException{
         // DATOS LIBRO
        String isbn = leerTexto(archivo,TAM_ISBN).trim();
        
        // AUTOR
        String nombre = leerTexto(archivo,TAM_AUTORNOMBRE).trim();
        String apellido = leerTexto(archivo,TAM_AUTORAPELLIDO).trim();
        Autor autor = new Autor(nombre,apellido);
        
        // CONTINUACION DATOS LIBROS
        String titulo = leerTexto(archivo,TAM_NOMBRE).trim();
        String genero = leerTexto(archivo,TAM_GENERO).trim();
        boolean restricciones= archivo.readBoolean();
        int numPag = archivo.readInt();
        String idioma = leerTexto(archivo,TAM_IDIOMA).trim();
        boolean disponible = archivo.readBoolean();
        return new Libro(isbn,autor,titulo,genero,restricciones,numPag,idioma,disponible);
    }
}

