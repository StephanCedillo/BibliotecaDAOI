package ups.edu.ec.bibleotecainterfaz.models;

import java.time.LocalDate;
import java.util.Scanner;

public class Usuario extends Persona {

    private String email;
    private String contrasena;
    private Membresia membresia;

    public Usuario(String email, String contrasena, String cedula,
            LocalDate edad, String nombre, String apellido, String direccion, boolean tieneDiscapacidad) {
        super(cedula, edad, nombre, apellido, direccion, tieneDiscapacidad);
        this.email = email;
        this.contrasena = contrasena;
        membresia = new Membresia();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Membresia getMembresia() {
        return membresia;
    }

    public void setMembresia(Membresia membresia) {
        this.membresia = membresia;
    }

    @Override
    public String toString() {
        String resultado = super.toString();
        return "-Usuario-" + "\n" +
                "email=" + email + "\n" +
                "contrasena=" + contrasena + "\n" +
                " " + resultado + " membresia=" + membresia + "\n";

    }

    public void agregarMembresia(String tipo) {
        if (isTieneDiscapacidad() || getEdad() > 60) {
            membresia = new Membresia("Especial");
        } else {
            membresia = new Membresia(tipo);
        }

    }

    public void renovarMembresia() {
        membresia.renovar();
    }

}
