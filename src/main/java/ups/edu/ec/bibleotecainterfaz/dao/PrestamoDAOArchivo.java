package ups.edu.ec.bibleotecainterfaz.dao;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import ups.edu.ec.bibleotecainterfaz.models.Libro;
import ups.edu.ec.bibleotecainterfaz.models.Prestamo;
import ups.edu.ec.bibleotecainterfaz.models.Usuario;

public class PrestamoDAOArchivo implements PrestamoDAO {

    private final File archivoPrestamo;

    private static final String NOMBRE_ARCHIVO = "prestamos.ups";

    private static final int TAM_CEDULA = 10;
    private static final int TAM_ISBN = 14;

    private static final int TAM_PRESTAMO = 125;

    private final UsuarioDAO usuarioDAO;

public PrestamoDAOArchivo(UsuarioDAO usuarioDAO, LibroDAO libroDAO) {

    this.usuarioDAO = usuarioDAO;

    File ruta = new File(
            System.getProperty("user.home")
            + File.separator + "Archivos"
            + File.separator + "Biblioteca"
    );

    if (!ruta.exists()) {
        ruta.mkdirs();
    }

    archivoPrestamo = new File(ruta, "prestamos.ups");

    try {
        if (!archivoPrestamo.exists()) {
            archivoPrestamo.createNewFile();
        }
    } catch (IOException e) {
        System.out.println(
                "No se pudo crear el archivo: "
                + e.getMessage()
        );
    }
}

    @Override
    public void crear(Prestamo prestamo) {

        try (RandomAccessFile archivo
                = new RandomAccessFile(archivoPrestamo, "rw")) {

            archivo.seek(archivo.length());

            escribirRegistro(archivo, prestamo);

        } catch (IOException e) {
            System.out.println(
                    "No se pudo crear el préstamo: "
                    + e.getMessage()
            );
        }
    }

    @Override
    public Prestamo buscarID(int id) {

        try (RandomAccessFile archivo
                = new RandomAccessFile(archivoPrestamo, "r")) {

            long cantidadRegistros
                    = archivo.length() / TAM_PRESTAMO;

            for (int i = 0; i < cantidadRegistros; i++) {

                archivo.seek(i * TAM_PRESTAMO);

                Prestamo prestamo = leerRegistro(archivo);

                if (prestamo.getId() == id) {
                    return prestamo;
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public Prestamo buscarISBN(String ISBN) {

        try (RandomAccessFile archivo
                = new RandomAccessFile(archivoPrestamo, "r")) {

            long cantidadRegistros
                    = archivo.length() / TAM_PRESTAMO;

            for (int i = 0; i < cantidadRegistros; i++) {

                archivo.seek(i * TAM_PRESTAMO);

                Prestamo prestamo = leerRegistro(archivo);

                for (Libro libro : prestamo.getLibro()) {

                    if (libro.getISBN().equals(ISBN)) {
                        return prestamo;
                    }
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public Prestamo buscarCedula(String cedula) {

        try (RandomAccessFile archivo
                = new RandomAccessFile(archivoPrestamo, "r")) {

            long cantidadRegistros
                    = archivo.length() / TAM_PRESTAMO;

            for (int i = 0; i < cantidadRegistros; i++) {

                archivo.seek(i * TAM_PRESTAMO);

                String cedulaActual = leerTexto(
                        archivo,
                        TAM_CEDULA
                ).trim();

                if (cedula.equals(cedulaActual)) {

                    archivo.seek(i * TAM_PRESTAMO);

                    return leerRegistro(archivo);
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public boolean devolucion(Prestamo prestamo) {

        try (RandomAccessFile archivo
                = new RandomAccessFile(archivoPrestamo, "rw")) {

            long cantidadRegistros
                    = archivo.length() / TAM_PRESTAMO;

            for (int i = 0; i < cantidadRegistros; i++) {

                long posicion = i * TAM_PRESTAMO;

                archivo.seek(posicion);

                Prestamo prestamoActual = leerRegistro(archivo);

                if (prestamoActual.getId() == prestamo.getId()) {

                    prestamoActual.registrarDevolucion();

                    archivo.seek(posicion);

                    escribirRegistro(
                            archivo,
                            prestamoActual
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
    public List<Prestamo> listar() {

        List<Prestamo> lista = new ArrayList<>();

        try (RandomAccessFile archivo
                = new RandomAccessFile(archivoPrestamo, "r")) {

            long cantidadRegistros
                    = archivo.length() / TAM_PRESTAMO;

            for (int i = 0; i < cantidadRegistros; i++) {

                archivo.seek(i * TAM_PRESTAMO);

                lista.add(
                        leerRegistro(archivo)
                );
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return lista;
    }

    @Override
    public void crearListadoTemporal(int cantidad) {

        throw new UnsupportedOperationException(
                "Not supported yet."
        );
    }

    private void escribirRegistro(
            RandomAccessFile archivo,
            Prestamo prestamo
    ) throws IOException {

        /*
         * CÉDULA DEL USUARIO
         */

        archivo.writeChars(
                completarTexto(
                        prestamo.getCedula(),
                        TAM_CEDULA
                )
        );

        /*
         * ISBN DE LOS LIBROS
         */

        List<Libro> libros = prestamo.getLibro();

        for (int i = 0; i < 3; i++) {

            if (i < libros.size()) {

                archivo.writeChars(
                        completarTexto(
                                libros.get(i).getISBN(),
                                TAM_ISBN
                        )
                );

            } else {

                archivo.writeChars(
                        completarTexto(
                                "",
                                TAM_ISBN
                        )
                );
            }
        }

        /*
         * ESTADO
         */

        archivo.writeBoolean(
                prestamo.isEstado()
        );

        /*
         * ID
         */

        archivo.writeInt(
                prestamo.getId()
        );

        /*
         * FECHA DEL PEDIDO
         */

        escribirFecha(
                archivo,
                prestamo.getFechaPedido()
        );

        /*
         * FECHA DE DEVOLUCIÓN
         */

        escribirFecha(
                archivo,
                prestamo.getFechaDevolucion()
        );
    }

  private Prestamo leerRegistro(
        RandomAccessFile archivo
) throws IOException {

    // CÉDULA DEL USUARIO
    String cedula = leerTexto(
            archivo,
            TAM_CEDULA
    ).trim();

    // BUSCAR EL USUARIO COMPLETO
    Usuario usuario = usuarioDAO.buscar(cedula);

    // LIBROS
    List<Libro> libros = new ArrayList<>();

    for (int i = 0; i < 3; i++) {

        String ISBN = leerTexto(
                archivo,
                TAM_ISBN
        ).trim();

        if (!ISBN.isEmpty()) {

            Libro libro = new Libro();

            libro.setISBN(ISBN);

            libros.add(libro);
        }
    }

    // ESTADO
    boolean estado = archivo.readBoolean();

    // ID
    int id = archivo.readInt();

    // FECHAS
    LocalDate fechaPedido = leerFecha(archivo);

    LocalDate fechaDevolucion = leerFecha(archivo);

    // CREAR EL PRÉSTAMO CON EL USUARIO ENCONTRADO
    Prestamo prestamo = new Prestamo(
            usuario,
            estado
    );

    prestamo.setId(id);

    prestamo.setFechaPedido(
            fechaPedido
    );

    prestamo.setFechaDevolucion(
            fechaDevolucion
    );

    // AGREGAR LOS LIBROS
    for (Libro libro : libros) {
        prestamo.getLibro().add(libro);
    }

    return prestamo;
}
    private String completarTexto(
            String texto,
            int tamaño
    ) {

        if (texto == null) {
            texto = "";
        }

        if (texto.length() > tamaño) {
            return texto.substring(0, tamaño);
        }

        while (texto.length() < tamaño) {
            texto += " ";
        }

        return texto;
    }

    private String leerTexto(
            RandomAccessFile archivo,
            int tamaño
    ) throws IOException {

        String texto = "";

        for (int i = 0; i < tamaño; i++) {

            texto += archivo.readChar();
        }

        return texto;
    }

    private void escribirFecha(
            RandomAccessFile archivo,
            LocalDate fecha
    ) throws IOException {

        if (fecha == null) {

            archivo.writeLong(0);

        } else {

            archivo.writeLong(
                    fecha.toEpochDay()
            );
        }
    }

    private LocalDate leerFecha(
            RandomAccessFile archivo
    ) throws IOException {

        long fecha = archivo.readLong();

        if (fecha == 0) {
            return null;
        }

        return LocalDate.ofEpochDay(fecha);
    }
}