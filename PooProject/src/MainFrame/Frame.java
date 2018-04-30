/*
* Frame
* Author: Clivaz Loris
* Date creation: 30 avr. 2018
* 
*/
/**
 * 
 */
package MainFrame;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Panels.BackPanel;
import Panels.MainPanel;
import Panels.PanelAccueil;
import Panels.UpPanel;

/**
 * @author Loris_Clivaz
 *
 */
public class Frame extends JFrame
{

	//Instanciation des panels
	UpPanel uppanel = new UpPanel();
	BackPanel backpanel = new BackPanel();
	MainPanel mainpanel = new MainPanel();
	PanelAccueil panelaccueil = new PanelAccueil();
	
	//Trier les panels
	private CardLayout cardLayout = new CardLayout();
	private JPanel contentPanel = new JPanel(cardLayout);
	
	
	
	public Frame() 
	{

		this.setSize(480, 860);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		
		//Gestion des panels
		contentPanel.add(panelaccueil, "panelaccueil");
		
		
		this.add(uppanel, BorderLayout.NORTH);
		
		this.add(backpanel, BorderLayout.SOUTH);
		
		this.add(mainpanel, BorderLayout.CENTER); //Ecran de base qui changera au fur et à mesure
		
		this.add(new PanelAccueil(), BorderLayout.CENTER);
		
//		this.add(contactpanel, BorderLayout.CENTER);
		
		
		
		
	}
	
}
