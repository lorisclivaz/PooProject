/*
* Author : Vivian Bridy
* Date creation : 30 avr. 2018
*/
package Panels;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import ContactApp.ContactPanel;
import ContactApp.NewContact;
import Images.IconBase;

public class MenuH1Panel extends JPanel{
	
	JLabel titrePanel;
	Font globalFont = new Font("2.TimesRoman ",Font.BOLD,50);

	IconBase vide = new IconBase("",40,40);

	
	public MenuH1Panel(String titre, String nomClass, IconBase previous,IconBase create)
		// TODO Auto-generated constructor stube
		{
			titrePanel = new JLabel(titre);
			titrePanel.setFont(globalFont);
			
			this.setPreferredSize(new Dimension(480, 78));
			this.setBackground(Color.decode("#DFDFDF"));
			
			this.setLayout(new FlowLayout(FlowLayout.CENTER,10,8));
			if(nomClass.equals("ContactPanel")) {
				//On met le plus � gauche
				this.add(vide, BorderLayout.WEST);
			}else {
				this.add(previous, BorderLayout.WEST);
			}
			//On met le titre au centre
			this.add(titrePanel, BorderLayout.CENTER);

			//On met le plus � droite
			this.add(create, BorderLayout.EAST);
			
			
			
		}
	
}
