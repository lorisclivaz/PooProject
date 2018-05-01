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
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import Images.IconBase;
import Panels.MenuH1Panel;

/**
 * @author Loris_Clivaz
 *
 */
public class GalleryPanel extends JPanel
{

	MenuH1Panel menuh1panel = new MenuH1Panel("Gallery");
	
	IconBase retour = new IconBase("images/icones/retour.jpg", 50, 50);
	
	public GalleryPanel()
	{

		
		this.setLayout(new BorderLayout());

		this.setPreferredSize(new Dimension(480, 40));
		this.setBackground(Color.RED);
		
		this.add(menuh1panel, BorderLayout.NORTH);

	}
}
