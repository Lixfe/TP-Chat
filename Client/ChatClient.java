package Client;

import java.util.LinkedList;
import java.rmi.*;
import Partage.*;
import Serveur.ChatGestion;

public class ChatClient {

  //ATTRIBUTS
  protected Integer numDernierMessage;
  protected String adresseServeur;

  //CONSTRUCTEUR
  public ChatClient() {
	  this.adresseServeur="";
	  this.numDernierMessage = 0;
  }

  

  
  //MAIN
  public static void main(String[] args) {
	//Initialisation du chat
	  ChatClient sessionChat = new ChatClient();
	

	  
	  try {
		  // Récupération d'un stub sur l'objet serveur.
		  ChatGestion objServeur = (ChatGestion) Naming.lookup("//Green:8000/ChatServeur");
		  System.out.println("Connecté au serveur");

		  //Lancement de la fenetre graphique
		  FenetreChat fenetre = new FenetreChat(objServeur);
		  
		  
		  
		  } catch (Exception exc) {
			  System.out.println("Impossible récupérer objet serveur");
		  }

  }
  
}