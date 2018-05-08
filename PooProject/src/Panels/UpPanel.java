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
import java.awt.FlowLayout;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Loris_Clivaz
 *
 */
public class UpPanel extends JPanel{

	Date madate = new Date();
	JLabel date = new JLabel();
	
	

		//Constructeur qui définit la taille du panel ou il y aura la batterie l'heures ect...
	
		public UpPanel() 
		{
			this.setLayout(new FlowLayout(100,215,20));
			this.setPreferredSize(new Dimension(480, 50));
			this.setBackground(Color.BLACK);
			
			date.setSize(100, 100);
			date.setText(madate.getHours()+" : "+ madate.getMinutes()+" ");
			date.setForeground(Color.WHITE);
			
			this.add(date);
		}
	}

