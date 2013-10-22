package Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe GestionEnvoyer : classe pour gérer l'appui sur le bouton envoyer.
 * @author felix
 *
 */
public class GestionEnvoyer implements ActionListener {
	private FenetreChat fenetre; 
	
	public GestionEnvoyer(FenetreChat fenetreDeChat){
		this.fenetre = fenetreDeChat;
	}
	
	public void actionPerformed(ActionEvent e) {
		this.fenetre.gestionEntree(this.fenetre.contenuChampSaisie());

	}

}
