package Serveur;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.rmi.server.UnicastRemoteObject;
import java.net.*;
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;

import Partage.Message;

public class ChatServeur extends UnicastRemoteObject implements ChatGestion {
	
	private static final long serialVersionUID = 2674880711467464646L;

	protected LinkedList<String> listeConnectes;
	protected LinkedList<Message> listeMessages;

	public ChatServeur() throws RemoteException {
		this.listeConnectes = new LinkedList<String>();
		this.listeMessages = new LinkedList<Message>();
  };

  /**
   * Methode envoyerMessage : methode appelee par le client pour envoyer un message.
   * Le serveur lorsqu'il recoit le message, l'ajoute a la liste de messages.
   */
  public void envoyerMessage(String message,  String id) throws RemoteException{
	  int numero;

	  if (this.listeMessages.isEmpty())
	  {
		  numero = 1;
	  }
	  else
	  {
	  numero = this.listeMessages.getFirst().getNumeroMessage()+1;
	  }

	  Message nouveauMessage = new Message(id, numero, message);
	  this.listeMessages.addFirst(nouveauMessage);
  };

  /**
   * Methode connexion : ajoute un utilisateur a la liste des utilisateurs connectes. 
   * La methode renvoie le nom d'utilisateur effectivement pris en compte.
   * (Verification d'id identiques dans un deuxieme temps)
   */
  public String connexion(String idUtilisateur) throws RemoteException{
	  this.listeConnectes.addFirst(idUtilisateur);
	  return idUtilisateur;
  };

  /**
   * Methode getListeConnectes : renvoie la liste des personnes connectes
   */
  public LinkedList<String> getListeConnectes() throws RemoteException{
	  return this.listeConnectes;
  };


  /**
   * Methode deconnexion : supprime l'id de la liste des gens connectes
   * 
   */
  public void deconnexion(String id) throws RemoteException{
	 boolean res = this.listeConnectes.remove(id);

  };

  /**
   * Methode recevoirMessage  : renvoie au client la liste de tous les messages qu'il n'a pas encore recu.
   * On se base pour cela sur le numero du dernier message recu par le client.
   */
  public LinkedList<Message> recevoirMessage(int numeroMessage) throws RemoteException{
	  LinkedList<Message> res = new LinkedList<Message>();
	  if (this.listeMessages.getFirst().getNumeroMessage() == numeroMessage)
	  {
		  return res;
	  }
	  else
	  {
		  for (int i = 0; i < this.listeMessages.getFirst().getNumeroMessage() - numeroMessage; i++) {
			res.addFirst(this.listeMessages.get(i));
		  }
		  return res;		  
	  }
	  
  };
  
  
  public static void main(String[] args) {
	  // TODO Auto-generated method stub

	  int port; String URL;
	  try { // transformation d une chaine de caracteres en entier
		  //Integer I = new Integer(args[0]); port = I.intValue(); pour avoir en numero de port le paramètre du main
		  Integer I = new Integer(1097); port = I.intValue();
	  } catch (Exception ex) {
		  System.out.println(" Please enter: Server <port>"); return;
	  }

	  try {
		  // Creation du serveur de nom - rmiregistry
		  Registry registry = LocateRegistry.createRegistry(port);

		  // Creation d une instance de l objet serveur
		  ChatServeur serveurDeChat = new ChatServeur();	
		  // Calcul de l URL du serveur
		  URL = "//"+InetAddress.getLocalHost().getHostName()+":"+port+"/ChatServeur";
		
		  Naming.rebind(URL, serveurDeChat);
		  System.out.println(URL);
		  
	  } 

	  catch(java.security.AccessControlException e){
		  System.out.println("Accès au port "+port+" refusé");
		  System.exit(1);
	  }
	  catch(ExportException e){
		  System.out.println("Port déja utilisé");
	  }
	  catch (Exception e){
		  e.printStackTrace();
	  }

	  

  }

}