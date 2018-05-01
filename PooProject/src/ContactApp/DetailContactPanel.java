/*
* Author : Vivian Bridy
* Date creation : 30 avr. 2018
*/
package ContactApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import Panels.MenuH1Panel;

public class DetailContactPanel extends JPanel
{
	private MenuH1Panel menuh1panel = new MenuH1Panel("Bridy Vivian");
	
	private JPanel testImage = new JPanel();
	
	public DetailContactPanel() {
		// TODO Auto-generated constructor stub
		this.setPreferredSize(new Dimension(480, 40));
		this.setBackground(Color.decode("#EFEFEF")); 
		
		//On affiche titre H1 dans le panel UP s
		this.setLayout(new BorderLayout());
		this.add(menuh1panel, BorderLayout.NORTH);
		
		//On définit la taille de l'image et on l'implémente
//		testImage.setPreferredSize(new Dimension(480,40));
		testImage.setSize(480, 40);
		testImage.setBackground(Color.BLUE);
		this.add(testImage);
		
		//On met les infos
	}
}
