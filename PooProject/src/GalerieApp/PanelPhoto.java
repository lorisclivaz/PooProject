/*
* PanelPhoto
* Author: Clivaz Loris
* Date creation: 12 mai 2018
* 
*/

package GalerieApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;


/**
 * @author Loris_Clivaz
 *
 */
public class PanelPhoto extends JPanel
{

	PanelUpPhoto panelupphoto = new PanelUpPhoto();
	
	public PanelPhoto() 
	{

		this.setPreferredSize(new Dimension(480, 730));
		this.setBackground(Color.RED);
		this.setLayout(new BorderLayout());

		this.setVisible(true);
		
		this.add(panelupphoto, BorderLayout.NORTH);

	
	}
}



