/*Vivian Bridy
* Date creation: 30 avr. 2018
* 
* On définit la couleur #EFEFEF comme étant la couleur de fond de base
* 
*/
/**
 * 
 */
package ContactApp;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import GalerieApp.MenuH1PanelGallery;
import Images.IconBase;
import Panels.MenuH1PanelContact;

/**
 * @author Loris_Clivaz
 *
 */
public class ContactPanel extends JPanel
{
	private String titre = "Contacts";

	
	//tri des panels static pour avoir accès dans le panel d'accueil
	public   CardLayout cardLayout = new CardLayout();
	public  JPanel triPanel = new JPanel(cardLayout);
	private MenuH1PanelContact menuh1panel = new MenuH1PanelContact(titre, getClass().getSimpleName());
	private ListePanel listepanel = new ListePanel(cardLayout,triPanel);
	NewContact newcontact = new NewContact(cardLayout,triPanel);
	
	public ContactPanel() {
		// TODO Auto-generated constructor stub
		this.setPreferredSize(new Dimension(480, 40));
		this.setBackground(Color.decode("#EFEFEF")); 
		
		//On affiche titre H1 dans le panel UP s
		this.setLayout(new BorderLayout());
		
//		this.add(menuh1panel, BorderLayout.NORTH);
		
		//On affiche la liste de contact
		this.add(triPanel, BorderLayout.CENTER);
		triPanel.add(listepanel, "listepanel");
		
		//On ajoute newcontact
		triPanel.add(newcontact, "newcontact");
		
		
//		menuh1panel.setCardLayout(cardLayout,triPanel);
		
	}

}
