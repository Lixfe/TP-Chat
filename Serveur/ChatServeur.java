package Serveur;

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
   * Methode envoyerMessage : méthode appelée par le client pour envoyer un message.
   * Le serveur lorsqu'il reçoit le message, l'ajoute à la liste de messages.
   */
  public void envoyerMessage(String message,  String id) throws RemoteException{
	  int numero;
	  numero = this.listeMessages.getFirst().getNumeroMessage();
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

}