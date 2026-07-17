/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package ups.edu.ec.bibleotecainterfaz.enums;

/**
 *
 * @author stephancedillo
 */

public enum MensajeUsuario {
    USUARIO_NO_ENCONTRADO(0),
    USUARIO_ACTUALIZADO(1),
    ERROR_ACTUALIZAR(2),
    CONFIRMAR_ELIMINAR(3),
    USUARIO_ELIMINADO(4),
    USUARIO_CREADO(5),
    REQ_FECHA_NAC(6),
    REQ_CORREO(7),
    REQ_CONTRASENA(8),
    REQ_NOMBRE(9),
    REQ_APELLIDO(10),
    REQ_CEDULA(11),
    REQ_DIRECCION(12),
    REQ_NUEVA_FECHA(13),
    ERR_CORREO_INVALIDO(14),
    ERR_CORREO_SIN_COM(15),
    ERR_NOMBRE_ESP(16),
    ERR_APELLIDO_ESP(17),
    ERR_DIRECCION_ESP(18),
    ERR_LIMITE_EDAD(19),
    ERR_CEDULA_CONLETRA(20),
    ERR_CEDULA_INVALIDA(21);
    private final int indice;

    MensajeUsuario(int indice) {
        this.indice = indice;
    }

    /**
     * Extrae el texto correspondiente desde el arreglo generado por el split(",")
     */
    public String getTexto(String[] mensajesArray) {
        if (mensajesArray != null && indice < mensajesArray.length) {
            return mensajesArray[indice];
        }
        return "Mensaje no disponible"; // Fallback en caso de que el split falle
    }
}