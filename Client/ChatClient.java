package Client;
import java.util.LinkedList;

import Partage.Message;

public class ChatClient {

  public Integer numDernierMessage;

  protected String adresseServeur;

  public void afficher(LinkedList<Message> msglist ) {
	  for (int i = msglist.size(); i >= 0 ; i--) {
		System.out.println(msglist.get(i).getContenu());
	}
  }

  public void ChatClient() {
  }

}