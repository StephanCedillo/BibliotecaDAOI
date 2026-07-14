package ups.edu.ec.bibleotecainterfaz.enums;

public enum Genero {
    AVENTURA(0), 
    CIENCIA_FICCION(1), 
    FANTASIA(2),
    TERROR(3), 
    ROMANCE(4), 
    MISTERIO(5), 
    HISTORICO(6),
    CRIMEN(7),
    DISTOPIA(8), 
    HUMOR(9), 
    DRAMA(10), 
    POESIA(11);

    private final int indice;

    Genero(int indice) {
        this.indice = indice;
    }

    public String getTexto(String[] generosArray) {
        if (generosArray != null && indice < generosArray.length) {
            return generosArray[indice];
        }
        return this.name(); // Fallback por si hay un error en el properties
    }
}