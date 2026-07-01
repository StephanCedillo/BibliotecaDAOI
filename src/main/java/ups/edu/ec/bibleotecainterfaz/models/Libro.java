package ups.edu.ec.bibleotecainterfaz.models;

public class Libro {

    private String ISBN;
    private Autor autor;
    private String nombre;
    private String genero;
    private boolean sirestriccionEdad;
    private int numeroPaginas;
    private String idioma;
    private boolean siestadoDisponibilidad;


    public Libro(String ISBN, Autor autor, String nombre, String genero, boolean sirestriccionEdad, int numeroPaginas, String idioma, boolean siestadoDisponibilidad) {
        this.ISBN = ISBN;
        this.autor = autor;
        this.nombre = nombre;
        this.genero = genero;
        this.sirestriccionEdad = sirestriccionEdad;
        this.numeroPaginas = numeroPaginas;
        this.idioma = idioma;
        this.siestadoDisponibilidad = siestadoDisponibilidad;
    }


    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public boolean isSirestriccionEdad() {
        return sirestriccionEdad;
    }

    public void setSirestriccionEdad(boolean sirestriccionEdad) {
        this.sirestriccionEdad = sirestriccionEdad;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public boolean isSiestadoDisponibilidad() {
        return siestadoDisponibilidad;
    }

    public void setSiestadoDisponibilidad(boolean siestadoDisponibilidad) {
        this.siestadoDisponibilidad = siestadoDisponibilidad;
    }

    public Autor getAutor() {
        return autor;
    }
    

    @Override
    public String toString() {
        return "-Libro-" + "\n"
                + "ISBN=" + ISBN + "\n"
                + "autor=" + (autor != null ? autor.getNombre() : "N/A") + "\n"
                + "nombre=" + nombre + "\n"
                + "genero=" + genero + "\n"
                + "sirestriccionEdad=" + sirestriccionEdad + "\n"
                + "numeroPaginas=" + numeroPaginas + "\n"
                + "idioma=" + idioma + "\n"
                + "siestadoDisponibilidad=" + siestadoDisponibilidad + "\n";

    }

    public void prestar() {
        if (siestadoDisponibilidad) {
            this.siestadoDisponibilidad = false;
            System.out.println("El libro '" + nombre + "' ha sido prestado.");
        } else {
            System.out.println("El libro no está disponible para préstamo.");
        }
    }

    public void devolver() {
        this.siestadoDisponibilidad = true;
        System.out.println("El libro '" + nombre + "' ha sido devuelto y está disponible.");
    }

    public boolean estaDisponible() {
        return this.siestadoDisponibilidad;
    }

    public boolean tieneRestriccionEdad() {
        return this.sirestriccionEdad;
    }
    public void setAutor(Autor autor){
        this.autor = autor;
    }

}
