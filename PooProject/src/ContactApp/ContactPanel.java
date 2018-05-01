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

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import Panels.MenuH1Panel;

/**
 * @author Loris_Clivaz
 *
 */
public class ContactPanel extends JPanel
{
	private String titre = "Contacts";
	private ListePanel listepanel = new ListePanel();
	private MenuH1Panel menuh1panel = new MenuH1Panel(titre, getClass().getSimpleName());
	private JScrollPane scrollPane = new JScrollPane(listepanel);

	
	public ContactPanel() {
		// TODO Auto-generated constructor stub
		this.setPreferredSize(new Dimension(480, 40));
		this.setBackground(Color.decode("#EFEFEF")); 
		
		//On affiche titre H1 dans le panel UP s
		this.setLayout(new BorderLayout());
		this.add(menuh1panel, BorderLayout.NORTH);
		
		//On affiche la liste de contact
		listepanel.setLayout(new FlowLayout());
		add(listepanel, BorderLayout.CENTER);
		scrollPane.setPreferredSize(new Dimension(30, 10));
	
		this.add(scrollPane,BorderLayout.EAST);
		
		
		
	}
}
