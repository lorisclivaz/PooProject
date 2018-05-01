/*
* Author : Vivian Bridy
* Date creation : 30 avr. 2018
*/
package ContactApp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Images.IconBase;
import Panels.MenuH1Panel;

public class DetailContactPanel extends JPanel
{
	private MenuH1Panel menuh1panel = new MenuH1Panel("Bridy Vivian");
	private IconBase imageContact = new IconBase("images/photos/photo8.jpg",480,300);
	private JPanel infosContact = new JPanel();
	
	private ChampLabel nom = new ChampLabel("Nom :","text");
	private ChampTextField textNom = new ChampTextField("Bridy");
	private ChampLabel prenom = new ChampLabel("Prénom :","");
	
	public DetailContactPanel() {
		// TODO Auto-generated constructor stub
		this.setPreferredSize(new Dimension(480, 40));
		this.setBackground(Color.decode("#EFEFEF")); 
		
		//On affiche titre H1 dans le panel UP s
		this.add(menuh1panel);
		
		//On définit la taille de l'image et on l'implémente
		this.add(imageContact);
		
		//On met les infos dans le gridpanel
		infosContact.setLayout(new GridLayout(1,2,10,10));
		
		//On ajoute dans les infos contacts
		infosContact.add(nom);
		infosContact.add(textNom);
		
		//On affiche les infos
		this.add(infosContact);
		
		
	}
}
