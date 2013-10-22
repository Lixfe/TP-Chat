package Client;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.LinkedList;

import Partage.*;
import Serveur.* ;
import Serveur.* ;

public class TPChat {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//ChatServeur serveurtest = new ChatServeur();
		//serveurtest.main("1099");
		String url = "//localhost:1097/ChatServeur"; // premier parametre, indiquant l'url du serveur distant
		
		try {
			
			ChatGestion serveur = (ChatGestion) Naming.lookup(url);			
			serveur.envoyerMessage("test message", "testid");
			serveur.recevoirMessage(0);
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

}
