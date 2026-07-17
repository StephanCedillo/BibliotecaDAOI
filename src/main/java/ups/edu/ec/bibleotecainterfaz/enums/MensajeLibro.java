/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package ups.edu.ec.bibleotecainterfaz.enums;

/**
 *
 * @author stephancedillo
 */

public enum MensajeLibro {
    LIBRO_NO_ENCONTRADO(0),
    ERROR_ACTUALIZAR(1),
    CONFIRMAR_ELIMINAR(2),
    LIBRO_CREADO(3),
    LIBRO_ACTUALIZADO(4),
    ERR_NUM_PAGINAS(5),
    ERR_AUTOR_VALIDO(6),
    ERR_AUTOR_GENERO_VALIDOS(7),
    REQ_ISBN(8),
    REQ_TITULO(9),
    REQ_AUTOR(10),
    REQ_GENERO(11),
    REQ_IDIOMA(12),
    ERR_PAGINAS_MAYOR_CERO(13),
    REQ_TITULO_ACT(14),
    REQ_GENERO_ACT(15),
    REQ_IDIOMA_ACT(16),
    ERR_ISBN_LETTERS(17),
    ERR_ISBN_MENOR13(18),
    ERR_IBSN_NO_VALIDO(19);

    private final int indice;

    MensajeLibro(int indice) {
        this.indice = indice;
    }

    public String getTexto(String[] mensajesArray) {
        if (mensajesArray != null && indice < mensajesArray.length) {
            return mensajesArray[indice];
        }
        return "Mensaje no disponible";
    }
}