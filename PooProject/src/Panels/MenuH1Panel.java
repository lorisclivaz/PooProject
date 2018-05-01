/*
* Author : Vivian Bridy
* Date creation : 30 avr. 2018
*/
package Panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import Images.IconBase;

public class MenuH1Panel extends JPanel{
	
	JLabel titrePanel;
	Font globalFont = new Font("2.TimesRoman ",Font.BOLD,50);
	IconBase create = new IconBase("images/icones/plus.png",40,40);
	IconBase previous = new IconBase("images/icones/left-arrow.png",40,40);
	public MenuH1Panel(String titre)
		// TODO Auto-generated constructor stube
		{
			titrePanel = new JLabel(titre);
			titrePanel.setFont(globalFont);
			
			this.setPreferredSize(new Dimension(480, 78));
			this.setBackground(Color.decode("#DFDFDF"));
			
			this.setLayout(new FlowLayout(FlowLayout.CENTER,61,8)); 	//61 est la valeur max
			//On met le plus à gauche
			this.add(previous, BorderLayout.WEST);
			//On met le titre au centre
			this.add(titrePanel, BorderLayout.CENTER);
			//On met le plus à droite
			this.add(create, BorderLayout.EAST);
			
			
			
		}
}
