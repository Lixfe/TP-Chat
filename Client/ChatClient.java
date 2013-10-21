package Client;
import java.util.LinkedList;

import Partage.*;

public class ChatClient {

  //ATTRIBUTS
  public Integer numDernierMessage;
  protected String adresseServeur;

  //CONSTRUCTEUR
  public ChatClient() {
	  this.adresseServeur="";
	  this.numDernierMessage = 0;
  }

  
  //METHODES
  public void afficher(LinkedList<Message> msglist ) {
	  for (int  i = 0 ; i < msglist.size(); i++) {
		System.out.println(msglist.get(i).getContenu());
	}
  }

}