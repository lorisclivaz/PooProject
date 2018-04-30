/*Vivian Bridy
* Date creation: 30 avr. 2018
* 
* On d�finit la couleur #EFEFEF comme �tant la couleur de fond de base
* 
*/
/**
 * 
 */
package ContactApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

import Panels.MenuH1Panel;

/**
 * @author Loris_Clivaz
 *
 */
public class ContactPanel extends JPanel
{
	private String titre = "Contacts";
	MenuH1Panel menuh1panel = new MenuH1Panel(titre);
	ListePanel listepanel = new ListePanel();
	
	
	public ContactPanel() {
		// TODO Auto-generated constructor stub
		this.setPreferredSize(new Dimension(480, 40));
		this.setBackground(Color.decode("#EFEFEF")); 
		
		//On affiche titre H1 dans le panel UP s
		this.setLayout(new BorderLayout());
		this.add(menuh1panel, BorderLayout.NORTH);
		this.add(listepanel);
		
	}
}
