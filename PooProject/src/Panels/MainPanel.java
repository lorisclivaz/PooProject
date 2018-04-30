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
import java.awt.Dimension;

import javax.swing.JPanel;

import Images.ImageFond;

/**
 * @author Loris_Clivaz
 *
 */
public class MainPanel extends JPanel
{

	ImageFond imagefond = new ImageFond();
	
	public MainPanel() 
	{

		this.setLayout(new BorderLayout());
		
	this.setPreferredSize(new Dimension(480, 40));
	this.setBackground(Color.RED);
		
	this.add(imagefond);
	
	}
}
