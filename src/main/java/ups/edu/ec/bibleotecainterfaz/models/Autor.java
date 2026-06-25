package ups.edu.ec.bibleotecainterfaz.models;


import java.util.ArrayList;

/**
 *
 * @author stephancedillo
 */
public class Autor extends Persona {

    private ArrayList<Libro> libros;

    public Autor() {
        libros = new ArrayList<>();

    }

    public Autor(String cedula, int edad, String nombre, String apellido, 
            String direccion, boolean estadoVivo, boolean tieneDiscapacidad,
            String genero) {
        super(cedula, edad, nombre, apellido, direccion, estadoVivo, tieneDiscapacidad, genero);
        libros = new ArrayList<>();
    }

    @Override
    public String toString() {
         String resultado = super.toString(); 
         for (Libro libro : libros) {
            resultado += "- " + libro.estaDisponible() +"\n"+ " (Nombre: " + libro.getNombre() + ")\n";
        }
        return resultado;
    }

    public void agregarLibro(Libro libro) {
        if (libro != null) {
            this.libros.add(libro);
        }
    }

    public String mostrarLibrosPublicados() {
        if (libros.isEmpty()) {
            return "El autor no tiene libros registrados.";
        }

        String lista = "Libros de " + getNombre() + " " + getApellido() + ":\n";
        for (Libro libro : libros) {
            lista += "- " + libro.estaDisponible() + " (ISBN: " + libro.toString() + ")\n";
        }
        return lista;
    }

    public int contarLibros() {
        return this.libros.size();
    }

}
