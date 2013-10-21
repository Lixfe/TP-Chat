package Serveur;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.net.InetAddress;
import java.rmi.*;

public class ServeurRMI {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int port; String URL;
		try { // transformation d une chaine de caracteres en entier
			Integer I = new Integer(args[0]); port = I.intValue();
		} catch (Exception ex) {
			System.out.println(" Please enter: Server <port>"); return;
		}
		
		try {
			// Creation du serveur de nom - rmiregistry
			Registry registry = LocateRegistry.createRegistry(port);
			//Security manager
			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new RMISecurityManager());
			}
			
			// Creation d une instance de l objet serveur
			ChatServeur serveurDeChat = new ChatServeur();	
			// Calcul de l URL du serveur
			URL = "//"+InetAddress.getLocalHost().getHostName()+":"+
					port+"/ChatServeur";
			Naming.rebind(URL, serveurDeChat);
		} 
		
		catch (Exception e){
			e.printStackTrace();
		}


  }

			

}
