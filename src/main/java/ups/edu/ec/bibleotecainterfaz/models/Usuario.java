package ups.edu.ec.bibleotecainterfaz.models;

import java.time.LocalDate;
import java.util.Scanner;

public class Usuario extends Persona {

    private String email;
    private String contrasena;
    private Membresia membresia;

    public Usuario() {
        membresia = new Membresia();
    }

    public Usuario(String email, String contrasena, String cedula,
            int edad, String nombre, String apellido, String direccion,
            boolean estadoVivo, boolean tieneDiscapacidad, String genero) {
        super(cedula, edad, nombre, apellido, direccion, estadoVivo, tieneDiscapacidad, genero);
        this.email = email;
        this.contrasena = contrasena;
        this.membresia = membresia;
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
        return "-Usuario-" +"\n"+ 
            "email=" + email + "\n"+
            "contrasena=" + contrasena +"\n"+
             " " + resultado + " membresia=" + membresia + "\n";

    }

    public void agregarMembresia() {
        Scanner leer = new Scanner(System.in);

        if (isTieneDiscapacidad() || getEdad() > 60) {
            membresia = new Membresia("Especial");
        } else {
            System.out.println("\n--- INGRESAR DATOS DE MEMBRESIA ---");
            System.out.println("Usted quiere tener membresia?(S/N) ");
            boolean resultado = pedirSiNo();
            if (resultado) {
                System.out.println("--Menu-"+"\n"+"1.Corporativa"+"\n"+"2.Academica"+"\n"+"3.Estudiantil");
                System.out.println("Ingresa su tipo de membresia: ");
                int numero = leer.nextInt();
                
                String tipo="2";
                
                switch(numero){
                    case 1:
                        tipo="Corporativa";
                        break;
                    case 2:
                        tipo="Academica";
                        break;
                    case 3:
                         tipo="Estudiantil";
                        break;
                    default:
                        System.out.println("Usted ha ingresado mal los datos");
                }
                LocalDate fechaInicio = LocalDate.now();

                membresia = new Membresia(tipo);

            } else {
                membresia = new Membresia();
            }

        }

    }

    private boolean pedirSiNo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pedirSiNo'");
    }

    public void renovarMembresia() {
        membresia.renovar();
    }

}
