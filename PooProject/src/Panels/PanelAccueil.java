/*
* Exercise W2Q3 - 2
* Author: Clivaz Loris
* Date creation: 30 avr. 2018
* 
*/
/**
 * 
 */
package Panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import Images.ImageFond;

/**
 * @author Loris_Clivaz
 *
 */
public class PanelAccueil extends JPanel
{

	ImageFond imagefond = new ImageFond();
	
	
	public PanelAccueil()
	{

		this.setLayout(new BorderLayout());
		this.setSize(440, 820);
		
		this.add(imagefond);
	}
}
