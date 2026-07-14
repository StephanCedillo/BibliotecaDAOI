package ups.edu.ec.bibleotecainterfaz.dao;

import java.util.ArrayList;
import java.util.List;

import ups.edu.ec.bibleotecainterfaz.models.Autor;


public class AutorDAOMemoria implements AutorDAO{
     private List<Autor> listaAutor;
     public AutorDAOMemoria() {
        listaAutor = new ArrayList<>();
    }

    @Override
    public void crear(Autor Autor) {
        listaAutor.add(Autor);
    }

    @Override
    public Autor buscar(Autor autor) {
        for (Autor autorBus : listaAutor) {
            if (autorBus == autor) {
                return autorBus;
            }
        }
        return null;
    }

    @Override
    public boolean actualizar(Autor autorOriginal, Autor autor) {

        for (int i = 0; i < listaAutor.size(); i++) {
            if (listaAutor.get(i).getNombre().equalsIgnoreCase(autorOriginal.getNombre()) && listaAutor.get(i).getApellido().equalsIgnoreCase(autorOriginal.getApellido())) {
                listaAutor.set(i, autor);
                return true;
            }
        }
        return false;
    }


    @Override
    public List<Autor> listar() {
        return listaAutor;
    }

   

   
    public void crearListadoTemporal(int cantidad) {

        listaAutor.clear();

        for (int i = 1; i <= cantidad; i++) {

            Autor autor = new Autor(
                    "Autor" + i,
                    "Apellido" + i);


            listaAutor.add(autor);
        }
    }

    @Override

public boolean eliminar(Autor autor) {

    for (int i = 0; i < listaAutor.size(); i++) {

        if (listaAutor.get(i) == autor) {
            listaAutor.remove(i);
            return true;
        }
    }

    return false;
}

   

}
