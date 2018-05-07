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
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import Panels.MenuH1PanelGallery;








/**
 * @author Loris_Clivaza
 *
 */
public class GalleryPanel extends JPanel 
{

	MenuH1PanelGallery menuh1panel = new MenuH1PanelGallery("Gallery", "Gallery");

	
	


	public GalleryPanel()
	{
		

		//scanGalleryFolder();
		this.setPreferredSize(new Dimension(480, 40));
		this.setLayout(new BorderLayout());
		this.add(menuh1panel, BorderLayout.NORTH);


		// ajouter la methode deSerializeObject
		

	
		

	}

	
	
	


	
}