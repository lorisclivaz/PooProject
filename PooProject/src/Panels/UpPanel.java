/*
* Exercise W2Q3 - 2
* Author: Clivaz Loris
* Date creation: 2 mai 2018
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

/**
 * @author Loris_Clivaz
 *
 */
public class UpPanel extends JPanel{

	

		//Constructeur qui définit la taille du panel ou il y aura la batterie l'heures ect...
	
		public UpPanel() 
		{
			this.setLayout(new BorderLayout());
			this.setPreferredSize(new Dimension(480, 50));
			this.setBackground(Color.BLACK);
		}
	}

