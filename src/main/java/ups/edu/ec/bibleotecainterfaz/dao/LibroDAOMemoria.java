
package ups.edu.ec.bibleotecainterfaz.dao;

import java.util.ArrayList;
import java.util.List;

import ups.edu.ec.bibleotecainterfaz.models.Autor;
import ups.edu.ec.bibleotecainterfaz.models.Libro;

public class LibroDAOMemoria implements LibroDAO {

    private List<Libro> listaLibro;
    private List<Autor> listaAutor;

    public LibroDAOMemoria() {
        listaLibro = new ArrayList<>();

        listaAutor = new ArrayList<>();
    }

    @Override
    public void crear(Libro libro) {
        listaLibro.add(libro);

        Autor autorDelLibro = libro.getAutor();

        if (autorDelLibro != null && !listaAutor.contains(autorDelLibro)) {
            listaAutor.add(autorDelLibro);
        }

    }

    @Override
    public Libro buscar(String ISBN) {
        for (Libro libro : listaLibro) {
            if (libro.getISBN().equalsIgnoreCase(ISBN)) {
                return libro;
            }
        }
        return null;
    }

    @Override
    public boolean actualizar(Libro libro) {
        for (int i = 0; i < listaLibro.size(); i++) {
            if (listaLibro.get(i).getISBN().equalsIgnoreCase(libro.getISBN())) {
                listaLibro.set(i, libro);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean eliminar(String ISBN) {
        Libro libroEncontrado = buscar(ISBN);
        if (libroEncontrado != null) {
            listaLibro.remove(libroEncontrado);
            return true;
        }
        return false;

    }

    @Override
    public List<Libro> listar() {
        return listaLibro;
    }

    @Override
    public void crearAutor(Autor autor) {
        listaAutor.add(autor);
    }

    @Override
    public void crearListadoTemporal(int cantidad) {

        listaLibro.clear();

        for (int i = 1; i <= cantidad; i++) {

            Autor autor = new Autor(
                    "Autor" + i,
                    "Apellido" + i);

            Libro libro = new Libro(
                     String.valueOf(i),
                    autor,
                    "Libro " + i,
                    i % 2 == 0 ? "Novela" : "Ciencia",
                    i % 3 == 0,
                    100 + i * 20,
                    i % 2 == 0 ? "Español" : "Inglés",
                    true);

            listaLibro.add(libro);
        }
    }

    @Override
    public List<Autor> listarAutores() {
        return listaAutor;
    }

}
