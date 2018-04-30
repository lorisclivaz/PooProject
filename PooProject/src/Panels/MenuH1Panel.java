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

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuH1Panel extends JPanel{
	
	JLabel titrePanel;
	Font globalFont = new Font("2.TimesRoman ",Font.BOLD,50);
	public MenuH1Panel(String titre)
		// TODO Auto-generated constructor stube
		{
			titrePanel = new JLabel(titre);
			titrePanel.setFont(globalFont);
			
			this.setPreferredSize(new Dimension(480, 78));
			this.setBackground(Color.decode("#DFDFDF"));
			
			//On met le titre au centre
			this.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.CENTER;					//Centrer-centrer
			this.add(titrePanel);
		}
}
