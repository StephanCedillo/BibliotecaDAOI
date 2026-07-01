package ups.edu.ec.bibleotecainterfaz.models;


import java.util.ArrayList;

/**
 *
 * @author stephancedillo
 */
public class Autor extends Persona {

    private ArrayList<Libro> libros;

   

    

    public Autor(String nombre, String apellido) {
        super(nombre, apellido);
        libros = new ArrayList<>();
    }
    

    @Override
    public String toString() {

       return super.getNombre() +" "+super.getApellido();
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
