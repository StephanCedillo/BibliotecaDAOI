/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package ups.edu.ec.bibleotecainterfaz.enums;

/**
 *
 * @author stephancedillo
 */

public enum MensajePrestamo {
    PRESTAMO_NO_ENCONTRADO(0),
    USUARIO_NO_ENCONTRADO(1),
    LIBRO_NO_ENCONTRADO(2),
    LIBRO_AGREGADO(3),
    BUSCAR_PRESTAMO_PRIMERO(4),
    CONFIRMAR_DEVOLUCION(5),
    PRESTAMO_DEVUELTO(6),
    PRESTAMO_CREADO(7),
    ERR_ID_VALIDO(8);

    private final int indice;

    MensajePrestamo(int indice) {
        this.indice = indice;
    }

    public String getTexto(String[] mensajesArray) {
        if (mensajesArray != null && indice < mensajesArray.length) {
            return mensajesArray[indice];
        }
        return "Mensaje no disponible";
    }
}