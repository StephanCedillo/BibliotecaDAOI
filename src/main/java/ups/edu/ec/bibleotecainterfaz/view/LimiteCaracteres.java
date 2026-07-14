/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ups.edu.ec.bibleotecainterfaz.view;

/**
 *
 * @author stephancedillo
 */
import javax.swing.text.*;

public class LimiteCaracteres extends DocumentFilter {

    private final int limite;

    public LimiteCaracteres(int limite) {
        this.limite = limite;
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr)
            throws BadLocationException {
        if (text != null && fb.getDocument().getLength() + text.length() <= limite) {
            super.insertString(fb, offset, text, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
            throws BadLocationException {
        if (text != null && fb.getDocument().getLength() - length + text.length() <= limite) {
            super.replace(fb, offset, length, text, attrs);
        }
    }
}