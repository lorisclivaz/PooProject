/*
* Author : Vivian Bridy
* Date creation : 30 avr. 2018
*/
package Panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Images.IconBase;

public class MenuH1Panel extends JPanel{
	
	JLabel titrePanel;
	Font globalFont = new Font("2.TimesRoman ",Font.BOLD,50);
	IconBase create = new IconBase("images/icones/plus.png",50,50);
	public MenuH1Panel(String titre)
		// TODO Auto-generated constructor stube
		{
			titrePanel = new JLabel(titre);
			titrePanel.setFont(globalFont);
			
			this.setPreferredSize(new Dimension(480, 78));
			this.setBackground(Color.decode("#DFDFDF"));
			
			this.setLayout(new GridBagLayout());
			//On met le titre au centre
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.CENTER;					//Centrer-centrer
			this.add(titrePanel, c);
			//On met le plus à droite
			GridBagConstraints d = new GridBagConstraints();
			d.insets = new Insets(0,0,0,20);
			this.add(create, d);
			
			
		}
}
