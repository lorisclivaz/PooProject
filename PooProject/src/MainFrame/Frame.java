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

import javax.swing.JFrame;
import javax.swing.JPanel;

import Panels.BackPanel;
import Panels.GalleryPanel;
import Panels.MainPanel;
import Panels.UpPanel;

/**
 * @author Loris_Clivaz
 *
 */
public class Frame extends JFrame
{
	//Ajout des panels
	GalleryPanel gallerypanel = new GalleryPanel();
	UpPanel uppanel = new UpPanel();
	BackPanel backpanel = new BackPanel();
	MainPanel mainpanel = new MainPanel();
	
	
	
	//tri des panels static pour avoir accès dans le panel d'accueil
	public  static CardLayout cardLayout = new CardLayout();
	public static JPanel triPanel = new JPanel(cardLayout);
	
	
	
	
	public Frame() 
	{

		
		
		this.setSize(480, 860);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
		
		this.setLayout(new BorderLayout());
		
		this.add(uppanel, BorderLayout.NORTH);

		this.add(backpanel, BorderLayout.SOUTH);
		
		this.add(triPanel);
		
		triPanel.add(mainpanel, "mainpanel");
		triPanel.add(gallerypanel, "gallerypanel");
		
		
		
		}
	
	
	
	
}
