package ups.edu.ec.bibleotecainterfaz.models;

import java.time.LocalDate;

public class Persona {
    private String cedula;
    private LocalDate edad;
    private String nombre;
    private String apellido;
    private String direccion;
    private boolean tieneDiscapacidad;
   

    
    

    
    public Persona(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Persona(String cedula, LocalDate edad, String nombre, 
            String apellido, String direccion,
            boolean tieneDiscapacidad) {
        this.cedula = cedula;
        this.edad = edad;
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
        return edad;
    }

    public void setFechaEdad(LocalDate edad) {
        this.edad = edad;
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
        return "--DATOS DE LA PERSONA--" +"\n" +
                "cedula=" + cedula + "\n" +
                ", edad=" + edad + "\n" +
                ", nombre=" + nombre +"\n" +
                ", apellido=" + apellido + "\n" +
                ", direccion=" + direccion + "\n" +
                ", tieneDiscapacidad=" + tieneDiscapacidad +"\n" ;
    }
    
    public boolean  esMayorEdad(){
        return getEdad()>=18;
    }
    public int getEdad(){
        return LocalDate.now().getYear() - edad.getYear();
    }
    public String obtenerNombreCompleto(){
        return nombre + " " + apellido;
    }
}
