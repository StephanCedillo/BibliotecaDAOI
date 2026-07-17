
package ups.edu.ec.bibleotecainterfaz.dao;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import ups.edu.ec.bibleotecainterfaz.models.Membresia;
import ups.edu.ec.bibleotecainterfaz.models.Usuario;


public class UsuarioDAOArchivo implements UsuarioDAO {
    
    private File rutaDirecion;
    private static final String NOMBRE_ARCHIVO = "usuarios.ups";
    
    // Tamaños de los Strings texto
    private static final int TAM_CORREO = 15;
    private static final int TAM_CONTRASENA = 15;
    private static final int TAM_CEDULA = 10;
    private static final int TAM_NOMBRE = 10;
    private static final int TAM_APELLIDO = 10;
    private static final int TAM_DIRECCION = 25;
    private static final int TAM_TIPO = 12;
    private static final int TAM_USUARIO = 219; 

    public UsuarioDAOArchivo() {
        try {
            String home = System.getProperty("user.home"); 
            
            rutaDirecion = new File(home + File.separator + "Archivos" + File.separator + "Biblioteca");

            if (!rutaDirecion.exists()) {
                rutaDirecion.mkdirs();
            }

            File archivo = new File(rutaDirecion, NOMBRE_ARCHIVO);
            if (!archivo.exists()) {
                archivo.createNewFile();
            }
        } catch (IOException ex) {
            System.out.println("Error de lectura o escritura en constructor: " + ex.getMessage());
        }
    }

    @Override
    public void crear(Usuario usuario) {
        try (RandomAccessFile archivo = new RandomAccessFile(new File(rutaDirecion, NOMBRE_ARCHIVO), "rw")) {
            archivo.seek(archivo.length());
            escribirRegistro(archivo, usuario);
        } catch (IOException e) {
            System.out.println("Error al crear: " + e.getMessage());
        }
    }

    @Override
    public Usuario buscar(String cedula) {
        try (RandomAccessFile archivo = new RandomAccessFile(new File(rutaDirecion, NOMBRE_ARCHIVO), "r")) {
            long numRegistros = archivo.length() / TAM_USUARIO;

            for (int i = 0; i < numRegistros; i++) {
                long inicioRegistro = i * TAM_USUARIO;
                // ya que la busqueda esta basada en la cedula nos movemos al espacio donde esta esta
                int posCedula = (TAM_CORREO + TAM_CONTRASENA) * 2;
                
                archivo.seek(inicioRegistro + posCedula);
                String cedulaActual = leerTexto(archivo, TAM_CEDULA).trim();
                // si la cedula que encontro es igual a la que se buscaba
                // entonces lo leemos
                if (cedulaActual.equals(cedula.trim())) {
                    archivo.seek(inicioRegistro);
                    return leerRegistro(archivo);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al buscar: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean actualizar(Usuario usuario) { 
        try (RandomAccessFile archivo = new RandomAccessFile(new File(rutaDirecion, NOMBRE_ARCHIVO), "rw")) {
            long numRegistros = archivo.length() / TAM_USUARIO;
            
            for (int i = 0; i < numRegistros; i++) {
                long inicioRegistro = i * TAM_USUARIO;
                int posCedula = (TAM_CORREO + TAM_CONTRASENA) * 2;

                archivo.seek(inicioRegistro + posCedula);
                String cedulaActual = leerTexto(archivo, TAM_CEDULA).trim();

                if (cedulaActual.equals(usuario.getCedula().trim())) {
                    archivo.seek(inicioRegistro);
                    escribirRegistro(archivo, usuario);
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al actualizar: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean eliminar(String cedulaE) {
        File archivoOriginal = new File(rutaDirecion, NOMBRE_ARCHIVO);
        File archivoTemporal = new File(rutaDirecion, "temp.ups");
        boolean eliminado = false;

        try (RandomAccessFile lectura = new RandomAccessFile(archivoOriginal, "r"); RandomAccessFile escritura = new RandomAccessFile(archivoTemporal, "rw")) {

            long numRegistros = lectura.length() / TAM_USUARIO;

            for (int i = 0; i < numRegistros; i++) {
                lectura.seek(i * TAM_USUARIO);
                Usuario usuario = leerRegistro(lectura);

                if (usuario.getCedula().equals(cedulaE.trim())) {
                    eliminado = true;
                    // No lo escribimos en el temporal "SE ELIMINA"
                    continue;
                }
                escribirRegistro(escritura, usuario);
            }
        } catch (IOException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }

        if (eliminado) {
            if (archivoOriginal.delete()) {
                if (!archivoTemporal.renameTo(archivoOriginal)) {
                    System.out.println("No se pudo renombrar el archivo temporal.");
                }
            } else {
                System.out.println("No se pudo eliminar el archivo original.");
            }
        } else {
            archivoTemporal.delete();
        }

        return eliminado;
    }

    @Override
    public List<Usuario> listar() {
        List<Usuario> usuarios = new ArrayList<>();
        try (RandomAccessFile archivo = new RandomAccessFile(new File(rutaDirecion, NOMBRE_ARCHIVO), "r")) {
            long numRegistros = archivo.length() / TAM_USUARIO;
            
            for (int i = 0; i < numRegistros; i++) {
                archivo.seek((long) i * TAM_USUARIO);
                usuarios.add(leerRegistro(archivo));
            }
            return usuarios;
        } catch (IOException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }
        return usuarios;
    }

    @Override
    public void crearListadoTemporal(int cantidad) {
       
    }
    
    // --- METODOS  DE LECTURA Y ESCRITURA  ---

    private void escribirRegistro(RandomAccessFile archivo, Usuario usuario) throws IOException {
        // USUARIO
        archivo.writeChars(completarTexto(usuario.getEmail(), TAM_CORREO));
        archivo.writeChars(completarTexto(usuario.getContrasena(), TAM_CONTRASENA));
        archivo.writeChars(completarTexto(usuario.getCedula(), TAM_CEDULA));
        
        archivo.writeLong(usuario.getFechaEdad().toEpochDay());
        
        archivo.writeChars(completarTexto(usuario.getNombre(), TAM_NOMBRE));
        archivo.writeChars(completarTexto(usuario.getApellido(), TAM_APELLIDO));
        archivo.writeChars(completarTexto(usuario.getDireccion(), TAM_DIRECCION));
        
        archivo.writeBoolean(usuario.isTieneDiscapacidad());
        
        // MEMBRESIA
        archivo.writeChars(completarTexto(usuario.getMembresia().getTipoMembresia(), TAM_TIPO));
        archivo.writeLong(usuario.getMembresia().getFechaInicio().toEpochDay());
        archivo.writeLong(usuario.getMembresia().getFechaVencimiento().toEpochDay());
    }

    private Usuario leerRegistro(RandomAccessFile archivo) throws IOException {
        String email = leerTexto(archivo, TAM_CORREO).trim();
        String contrasena = leerTexto(archivo, TAM_CONTRASENA).trim();
        String cedula = leerTexto(archivo, TAM_CEDULA).trim();
        
        LocalDate edad = LocalDate.ofEpochDay(archivo.readLong());
        
        String nombre = leerTexto(archivo, TAM_NOMBRE).trim();
        String apellido = leerTexto(archivo, TAM_APELLIDO).trim();
        String direccion = leerTexto(archivo, TAM_DIRECCION).trim();
        
        boolean mayorEdad = archivo.readBoolean();
        
        String tipo = leerTexto(archivo, TAM_TIPO).trim();
        LocalDate inicio = LocalDate.ofEpochDay(archivo.readLong());
        LocalDate fin = LocalDate.ofEpochDay(archivo.readLong());
        
        Membresia membresia = new Membresia(tipo, inicio, fin);
        Usuario usuario = new Usuario(email, contrasena, cedula, edad, nombre, apellido, direccion, false);
        usuario.agregarMembresia(membresia.getTipoMembresia());
        return  usuario;
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
    
    private String leerTexto(RandomAccessFile archivo, int tamaño) throws IOException {
        String texto = "";
        for (int i = 0; i < tamaño; i++) {
            texto += archivo.readChar();
        }

        return texto;
    }
}