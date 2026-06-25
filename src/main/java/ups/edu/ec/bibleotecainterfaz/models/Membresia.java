package ups.edu.ec.bibleotecainterfaz.models;

import java.time.LocalDate;

public class Membresia {
    
    String tipoMembresia;
    LocalDate fechaInicio;
    LocalDate fechaVencimiento;
    LocalDate hoy = LocalDate.now(); 
    public Membresia() {
        this.tipoMembresia="Normal";
        this.fechaInicio=LocalDate.now();
        renovar();
        
    }

    public Membresia(String tipoMembresia) {
        this.tipoMembresia = tipoMembresia;
        this.fechaInicio = hoy;
        renovar();

    }

    public String getTipoMembresia() {
        return tipoMembresia;
        
    }

    public void setTipoMembresia(String tipoMembresia) {
        this.tipoMembresia = tipoMembresia;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    @Override
    public String toString() {
        return "Membresia{" + "tipoMembresia=" + tipoMembresia + ", fechaInicio=" + fechaInicio + ", fechaVencimiento=" + fechaVencimiento + '}';
    }
    
    public int calcularCantidadLibros(){
        System.out.println("tipo = " + tipoMembresia);
        if(tipoMembresia.equalsIgnoreCase("Corporativa")){
            return 10;
        }else if(tipoMembresia.equalsIgnoreCase("Academica")){
            return 5;
        }else if(tipoMembresia.equalsIgnoreCase("Estudiantil")){
            return 3;
        } else if(tipoMembresia.equalsIgnoreCase("Especial")){
            return 3;
        }else{
            return 1;
        }
    }
    public boolean estaVigente(){
        return fechaVencimiento.isBefore(hoy);
    }
    public void renovar(){
        fechaInicio = hoy;
        fechaVencimiento = hoy;
        fechaVencimiento.plusMonths(3);
    }
    public String obtenerDiasRestantes(){
        int mesesRestantes = fechaVencimiento.getMonthValue() - fechaInicio.getMonthValue();
        int diasRestantes = fechaVencimiento.getDayOfMonth() - fechaInicio.getDayOfMonth();
        return "Los meses restantes son : " +  mesesRestantes + " y los dias faltantes : " + diasRestantes + " ";
    } 
    
    
    
    
}
