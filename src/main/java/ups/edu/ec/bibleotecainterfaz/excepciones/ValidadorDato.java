/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ups.edu.ec.bibleotecainterfaz.excepciones;

/**
 *
 * @author stephancedillo
 */
public class ValidadorDato extends Exception{
    public ValidadorDato(){}
    public ValidadorDato(String mensaje){
        super(mensaje);
    }
}
