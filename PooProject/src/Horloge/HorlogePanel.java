/*
 * Exercise W2Q3 - 2
 * Author: Clivaz Loris
 * Date creation: 18 mai 2018
 * 
 */
/**
 * 
 */
package Horloge;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

/**
 * @author Loris_Clivaz
 *
 */
public class HorlogePanel extends JPanel
{



	public HorlogePanel() 
	{

		//Choix du layout et de la dimension du panel
		this.setPreferredSize(new Dimension(480, 40));
		this.setLayout(new BorderLayout());
		this.setBackground(Color.RED);

	}
}
