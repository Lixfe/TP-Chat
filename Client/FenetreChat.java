package Client;

import javax.swing.*;
import java.awt.*;
import Serveur.ChatGestion;

import Partage.Message;
import java.util.*;

/**
 * Classe FenetreChat : cette classe permet l'affichage de l'environnement de chat.
 * Cet environnement contient un champ d'affichage des messages et un champ de saisie de texte.
 * 
 * @author felix
 *
 */
public class FenetreChat {
	
    private JFrame fenetre; 
    private JScrollPane scroll;
    private JTextArea affichage; 
    private JTextField champSaisie; 
    private JButton boutonEnvoyer;
    private JButton boutonQuitter;
    
    protected ChatGestion objetServeur;
    protected String id;
    protected int numeroMessage;
    
    /**
     * Constructeur FenetreChat par defaut : initialise les objets d'affichage.
     * Prend en argument l'objet serveur récupéré.
     */
	public FenetreChat(ChatGestion objServeur){
		
		this.objetServeur = objServeur;
		this.id = null;
		this.numeroMessage = 0;
		
		this.fenetre = new JFrame("Fenetre de chat");
		this.affichage = new JTextArea();
		this.scroll = new JScrollPane(this.affichage);
		this.champSaisie = new JTextField(50);
		this.boutonEnvoyer = new JButton("Envoyer");
		this.boutonQuitter = new JButton("Quitter");
		
		this.affichage.setLineWrap(true); 
		
	    Container conteneur = fenetre.getContentPane(); 
	    GridLayout disposition = new GridLayout(3, 1); 
	    JPanel boutons = new JPanel(); 
	    FlowLayout dispositionBoutons = new FlowLayout(); 
	    
	    boutons.setLayout(dispositionBoutons); 
	    boutons.add(this.boutonEnvoyer); 
	    boutons.add(this.boutonQuitter);
	    
	    conteneur.setLayout(disposition); 
	    conteneur.add(this.scroll); 
	    conteneur.add(this.champSaisie); 
	    conteneur.add(boutons); 
	    
	    this.fenetre.pack(); 
	    this.fenetre.setVisible(true); 
	    
	    GestionEnvoyer gestionEnvoi = new GestionEnvoyer(this); 
	    this.boutonEnvoyer.addActionListener(gestionEnvoi); 
	    
	    GestionQuitter gestionQuitter = new GestionQuitter(); 
	    this.boutonQuitter.addActionListener(gestionQuitter);
	}
	
	public String contenuChampSaisie() {
		String res = new String();
		res = this.champSaisie.getText();
		this.champSaisie.setText("");
		return res;
	}
	

	
	public void afficherTexte(String texte){
		this.affichage.append(texte);
		this.affichage.append("\r\n");
	}
	
	  /**
	   * Methode gestionEntree : cette methode va analyser le texte entré dans la fenetre graphique.
	   * Les mots clés vont etre repérés et les actions correspondantes déclenchées.
	   * @param messageEntre : message entré dans la fenetre graphique.
	   */
	  public void gestionEntree(String messageEntre) {
		  if (messageEntre.startsWith("connect "))
		  {
			  try{
				  this.id = this.objetServeur.connexion(messageEntre.substring(8));
				  this.afficherTexte("Connexion au chat avec l'id "+this.id);
			  }
			  catch (Exception e)
			  {
				  this.afficherTexte("Erreur lors de la connexion au chat");
			  }
		  }
		  
		  if (messageEntre.startsWith("send "))
		  {
			  try{
				  this.objetServeur.envoyerMessage(messageEntre.substring(5), this.id);
			  }
			  catch (Exception e)
			  {
				  this.afficherTexte("Erreur lors de l'envoi du message, id : "+this.id+" msg : "+messageEntre.substring(5));
			  }
		  }
		  
		  
		  if (messageEntre.startsWith("msg"))
		  {
			  try{
				  LinkedList<Message> listeMessage; 
				  listeMessage = this.objetServeur.recevoirMessage(this.numeroMessage);
				  for (Message msg : listeMessage) {
					this.afficherTexte(msg.getIdSender()+" : "+msg.getContenu());
					this.numeroMessage++;
				}
			  }
			  catch (Exception e)
			  {
				  this.afficherTexte("Erreur lors de la récupération des messages");
			  }
		  }
	  }
}


