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
	private ChampLabel prenom = new ChampLabel("Prénom :","text");
	private ChampTextField textPrenom = new ChampTextField("Vivian");
	private ChampLabel adresse = new ChampLabel("Adresse :","text");
	private ChampTextField textAdresse = new ChampTextField("Route de Fellina 3");
	private ChampLabel vide = new ChampLabel("","text");
	private ChampTextField textLocalite = new ChampTextField("1873 Val-d'Illiez");
	private ChampLabel mail = new ChampLabel("Email :","mail");
	private ChampTextField textMail = new ChampTextField("vbridy2@gmail.com");
	private ChampLabel phone = new ChampLabel("Téléphone :","text");
	private ChampTextField textPhone = new ChampTextField("0798323581");
	
	
	public DetailContactPanel() {
		// TODO Auto-generated constructor stub
		this.setBackground(Color.decode("#EFEFEF")); 
		
		//On affiche titre H1 dans le panel UP s
		this.add(menuh1panel);
		
		//On définit la taille de l'image et on l'implémentes
		this.add(imageContact);
		
		//On met les infos dans le gridpanel
		infosContact.setLayout(new GridLayout(6,2,10,10)); 		//(ligne,colonne,espace,espace)
		
		//On ajoute dans les infos contacts
		infosContact.add(nom);
		infosContact.add(textNom);
		infosContact.add(prenom);
		infosContact.add(textPrenom);
		infosContact.add(adresse);
		infosContact.add(textAdresse);
		infosContact.add(vide);
		infosContact.add(textLocalite);
		infosContact.add(mail);
		infosContact.add(textMail);
		infosContact.add(phone);
		infosContact.add(textPhone);
		
		//On affiche les infos
		this.add(infosContact);
		
		
	}
}
