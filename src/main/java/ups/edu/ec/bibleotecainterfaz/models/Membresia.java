package ups.edu.ec.bibleotecainterfaz.models;

import java.time.LocalDate;

public class Membresia {

    private String tipoMembresia;
    private LocalDate fechaInicio;
    private LocalDate fechaVencimiento;
    private LocalDate hoy = LocalDate.now();

    public Membresia() {
        this.tipoMembresia = "Normal";
        this.fechaInicio = LocalDate.now();
        renovar();

    }

    public Membresia(String tipoMembresia) {
        this.tipoMembresia = tipoMembresia;
        this.fechaInicio = hoy;
        renovar();

    }

    public Membresia(String tipoMembresia, LocalDate fechaInicio, LocalDate fechaVencimiento) {
        this.tipoMembresia = tipoMembresia;
        this.fechaInicio = fechaInicio;
        this.fechaVencimiento = fechaVencimiento;
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

    public int calcularCantidadLibros() {
        System.out.println("tipo = " + tipoMembresia);
        if (tipoMembresia.equalsIgnoreCase("Corporativa")) {
            return 10;
        } else if (tipoMembresia.equalsIgnoreCase("Academica")) {
            return 5;
        } else if (tipoMembresia.equalsIgnoreCase("Estudiantil")) {
            return 3;
        } else if (tipoMembresia.equalsIgnoreCase("Especial")) {
            return 3;
        } else {
            return 1;
        }
    }

    public boolean estaVigente() {

        return !LocalDate.now().isAfter(fechaVencimiento);
    }

    public void renovar() {
        fechaInicio = LocalDate.now();
        fechaVencimiento = fechaInicio.plusMonths(3);
    }

    public String obtenerDiasRestantes() {
        java.time.Period periodo = java.time.Period.between(LocalDate.now(), fechaVencimiento);
        int meses = periodo.getMonths();
        int dias = periodo.getDays();
        return "Los meses restantes son: " + meses + " y los días faltantes: " + dias;
    }

}
