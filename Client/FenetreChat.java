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
    private JScrollPane scroll;
    private JTextArea affichage; 
    private JTextField champSaisie; 
    private JButton boutonEnvoyer;
    private JButton boutonQuitter;
    
    /**
     * Constructeur FenetreChat par defaut : initialise les objets d'affichage.
     */
	public FenetreChat(){
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
	
}


