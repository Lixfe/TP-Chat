package Serveur;
import Partage.Message;
import java.rmi.*;
import java.util.*;

public interface ChatGestion extends Remote {

  public void envoyerMessage(String message,  String id) throws RemoteException;

  public String connexion(String id) throws RemoteException;

  public LinkedList<String> getListeConnectes() throws RemoteException;


  public void deconnexion(String id) throws RemoteException;

  public LinkedList<Message> recevoirMessage( int numeroMessage) throws RemoteException;

}