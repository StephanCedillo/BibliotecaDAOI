
package ups.edu.ec.bibleotecainterfaz.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import ups.edu.ec.bibleotecainterfaz.models.Usuario;


public class UsuarioDAOMemoria implements UsuarioDAO{

     private List<Usuario> listaUsuarios;
   
    public UsuarioDAOMemoria() {
        listaUsuarios = new ArrayList<>();
    }

    @Override
    public void crear(Usuario usuario) {
      listaUsuarios.add(usuario);
    }

    @Override
    public Usuario buscar(String cedula) {
       for (Usuario usuario : listaUsuarios) {
            if (usuario.getCedula().equalsIgnoreCase(cedula)) {
                return usuario;
            }
       }
       return null;
    }

    @Override
    public boolean actualizar(Usuario usuario) {
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (listaUsuarios.get(i).getCedula().equalsIgnoreCase(usuario.getCedula())) {
                listaUsuarios.set(i, usuario); 
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean eliminar(String cedula) {
      Usuario usuarioEncontrado = buscar(cedula);
        if (usuarioEncontrado != null) {
            listaUsuarios.remove(usuarioEncontrado);
            return true;
        }
        return false;

    }

    @Override
    public List<Usuario> listar() {
        return listaUsuarios;
    }

    @Override
public void crearListadoTemporal(int cantidad) {

    listaUsuarios.clear();

    for (int i = 1; i <= cantidad; i++) {

        Usuario usuario = new Usuario(
                "usuario" + i + "@correo.com",
                "1234",
                String.valueOf(i),
                LocalDate.now().minusYears(18 + i),
                "Nombre" + i,
                "Apellido" + i,
                "Direccion " + i,
                i % 2 == 0
        );

        usuario.agregarMembresia(i % 2 == 0 ? "Premium" : "Basica");

        listaUsuarios.add(usuario);
    }
}
    
}
