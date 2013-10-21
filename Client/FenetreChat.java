package Client;

import javax.swing.*;
import java.awt.*;

/**
 * Classe FenetreChat : cette classe permet l'affichage de l'environnement de chat.
 * Cet environnement contient un champ d'affichage des messages et un champ de saisie de texte.
 * 
 * @author felix
 *
 */
public class FenetreChat {
	
    private JFrame fenetre; 
    private JLabel affichage; 
    private JTextField champSaisie; 
    private JButton boutonEnvoyer;
    private JButton boutonQuitter;
    
    /**
     * Constructeur FenetreChat par defaut : initialise les objets d'affichage.
     */
	public FenetreChat(){
		this.fenetre = new JFrame("Fenetre de chat");
		this.affichage = new JLabel();
		this.champSaisie = new JTextField(100);
		this.boutonEnvoyer = new JButton("Envoyer");
		this.boutonQuitter = new JButton("Quitter");
		
		Container conteneur = fenetre.getContentPane(); 
	    FlowLayout disposition = new FlowLayout(); 
	    
	    conteneur.setLayout(disposition); 
	    conteneur.add(this.affichage); 
	    conteneur.add(this.champSaisie); 
	    conteneur.add(this.boutonEnvoyer);
	    conteneur.add(this.boutonQuitter);
	    
	    this.fenetre.pack(); 
	    this.fenetre.setVisible(true); 
	    
	    GestionEnvoyer gestionEnvoi = new GestionEnvoyer(this); 
	    this.boutonEnvoyer.addActionListener(gestionEnvoi); 
	    
	    GestionQuitter gestionQuitter = new GestionQuitter(); 
	    this.boutonQuitter.addActionListener(gestionQuitter);
	}
	
}


