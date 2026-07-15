package ups.edu.ec.bibleotecainterfaz.models;

import java.time.LocalDate;
import java.util.Objects;

public class Persona {

    private String cedula;
    private LocalDate fechaNacimiento;
    private String nombre;
    private String apellido;
    private String direccion;
    private boolean tieneDiscapacidad;

    public Persona(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Persona(String cedula, LocalDate fechaNacimiento, String nombre,
            String apellido, String direccion,
            boolean tieneDiscapacidad) {
        this.cedula = cedula;
        this.fechaNacimiento = fechaNacimiento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;

        this.tieneDiscapacidad = tieneDiscapacidad;

    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public LocalDate getFechaEdad() {
        return fechaNacimiento;
    }

    public void setFechaEdad(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public boolean isTieneDiscapacidad() {
        return tieneDiscapacidad;
    }

    public void setTieneDiscapacidad(boolean tieneDiscapacidad) {
        this.tieneDiscapacidad = tieneDiscapacidad;
    }

    @Override
    public String toString() {
        return "--DATOS DE LA PERSONA--" + "\n"
                + "cedula=" + cedula + "\n"
                + ", nacimiento=" + fechaNacimiento + "\n"
                + ", nombre=" + nombre + "\n"
                + ", apellido=" + apellido + "\n"
                + ", direccion=" + direccion + "\n"
                + ", tieneDiscapacidad=" + tieneDiscapacidad + "\n";
    }

    public boolean esMayorEdad() {
        return getEdad() >= 18;
    }

    public int getEdad() {
        if (fechaNacimiento == null) {
            return 0;
        }
        return java.time.Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

    public String obtenerNombreCompleto() {
        return nombre + " " + apellido;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.cedula);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Persona other = (Persona) obj;
        return Objects.equals(this.cedula, other.cedula);
    }

}
