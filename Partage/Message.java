package Partage;

import java.io.Serializable;

public class Message  implements Serializable {

  private static final long serialVersionUID = 1L;

  protected String idSender;

  protected int numeroMessage;

  protected String contenu;
  
  /**
   * Constructeur d'un objet message
   * @param id : id de la personne envoyant le message
   * @param numero : numero a attribuer au message
   * @param contenu : texte composant le message
   */
 public Message (String id, int numero, String contenuMessage){
	  this.idSender = id;
	  this.numeroMessage = numero;
	  this.contenu = contenuMessage;
  }

 
 //GETTERS
  public String getContenu() {
  return this.contenu;
  }
  
  /**
   * Methode getNumeroMessage : getter pour obtenir le numero du message
   * @return
   */
  public int getNumeroMessage(){
	  return this.numeroMessage;
  }

  public String getIdSender() {
	return idSender;
  }
  
 
}