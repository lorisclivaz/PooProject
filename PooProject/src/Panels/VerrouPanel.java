/*
* Exercise W2Q3 - 2
* Author: Clivaz Loris
* Date creation: 14 mai 2018
* 
*/
/**
 * 
 */
package Panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import Images.IconBase;
import Images.ImageFond;

/**
 * @author Loris_Clivaz
 *
 */
public class VerrouPanel extends JPanel
{

	ImageFond imagefond = new ImageFond();
	ImageFond imagefond2 = new ImageFond();
	IconBase lock = new IconBase("images/icones/padlock.png", 50,50);

	JPanel boutonlock = new JPanel();
	
	
	public VerrouPanel() 
	{

		this.setPreferredSize(new Dimension(480, 40));
		this.setBackground(Color.RED);
		this.setLayout(new BorderLayout());
		
		
		imagefond.setLayout(new BorderLayout());
		boutonlock.setLayout(null);
		
	
		
		
		
		

		this.add(imagefond);
	}
}
