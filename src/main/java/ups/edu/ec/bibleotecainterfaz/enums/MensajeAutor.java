package ups.edu.ec.bibleotecainterfaz.enums;

/**
*

* @author stephancedillo
  */
  public enum MensajeAutor {

  AUTOR_NO_ENCONTRADO(0),
  AUTOR_ACTUALIZADO(1),
  ERROR_ACTUALIZAR(2),
  CONFIRMAR_ELIMINAR(3),
  AUTOR_ELIMINADO(4),
  ERROR_ELIMINAR(5),
  AUTOR_CREADO(6),
  REQ_NOMBRE(7),
  REQ_APELLIDO(8),
  REQ_NOMBRE_ACT(9),
  REQ_APELLIDO_ACT(10);

  private final int indice;

  MensajeAutor(int indice) {
  this.indice = indice;
  }

  public String getTexto(String[] mensajesArray) {

 
   if (mensajesArray != null && indice < mensajesArray.length) {
       return mensajesArray[indice];
   }

   return "Mensaje no disponible";


  }
  }
