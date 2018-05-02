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

import Images.IconBase;
import Panels.MenuH1PanelContact;
import Panels.MenuH1PanelGallery;

/**
 * @author Loris_Clivaz
 *
 */
public class ContactPanel extends JPanel
{
	private String titre = "Contacts";
	private ListePanel listepanel = new ListePanel();
	IconBase create = new IconBase("images/icones/plus.png",40,40);
	IconBase previous = new IconBase("images/icones/left-arrow.png",40,40);
	private MenuH1PanelContact menuh1panel = new MenuH1PanelContact(titre, getClass().getSimpleName());
	NewContact newcontact = new NewContact();

	
	public ContactPanel() {
		// TODO Auto-generated constructor stub
		this.setPreferredSize(new Dimension(480, 40));
		this.setBackground(Color.decode("#EFEFEF")); 
		
		//On affiche titre H1 dans le panel UP s
		this.setLayout(new BorderLayout());

		
		//On met un listener sur le bouton
		create.addActionListener(new ClickCreate());
		
		this.add(menuh1panel, BorderLayout.NORTH);
		
		//On affiche la liste de contact
		
		this.add(listepanel, BorderLayout.CENTER);
		
	}
	
	//quand on clique sur le bouton create
	class ClickCreate implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			System.out.println("Hello");
		}
		
	}
}
