/*
* Exercise W2Q3 - 2
* Author: Clivaz Loris
* Date creation: 30 avr. 2018
* 
*/
/**
 * 
 */
package GalerieApp;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Panels.MenuH1Panel;


/**
 * @author Loris_Clivaz
 *
 */
public class GalleryPanel extends JPanel
{
	
	MenuH1Panel menuh1panel = new MenuH1Panel("Gallery", "galerie");
	
	private AjoutPhoto ajoutphoto = new AjoutPhoto();
	
	


	
	public GalleryPanel()
	{
		this.setPreferredSize(new Dimension(480, 40));
		this.setLayout(new BorderLayout());
		this.add(menuh1panel, BorderLayout.NORTH);
		
		this.add(ajoutphoto, BorderLayout.CENTER);
		
		
		
	
	}
}
