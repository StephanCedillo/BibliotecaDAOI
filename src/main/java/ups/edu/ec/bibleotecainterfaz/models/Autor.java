package ups.edu.ec.bibleotecainterfaz.models;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author stephancedillo
 */
public class Autor extends Persona {

    public Autor(String nombre, String apellido) {
        super(nombre, apellido);

    }

    @Override
    public String toString() {

        return super.getNombre() + " " + super.getApellido();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNombre(), getApellido());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Autor other = (Autor) obj;
        return Objects.equals(getNombre(), other.getNombre())
                && Objects.equals(getApellido(), other.getApellido());
    }

}
