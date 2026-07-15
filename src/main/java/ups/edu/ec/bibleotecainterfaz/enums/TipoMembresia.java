package ups.edu.ec.bibleotecainterfaz.enums;

public enum TipoMembresia {
    NORMAL(0),
    CORPORATIVA(1),
    ACADEMICA(2),
    ESTUDIANTIL(3),
    ESPECIAL(4);

    private final int indice;

    TipoMembresia(int indice) {
        this.indice = indice;
    }

    public String getTexto(String[] membresiasArray) {
        if (membresiasArray != null && indice < membresiasArray.length) {
            return membresiasArray[indice];
        }
        return this.name(); 
    }
}