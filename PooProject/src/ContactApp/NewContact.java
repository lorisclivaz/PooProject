/*
* Author : Vivian Bridy
* 
* Date creation : 1 mai 2018
*/
package ContactApp;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import Images.IconBase;
import Panels.MenuH1Panel;

public class NewContact extends JPanel 
{	
	
	IconBase create = new IconBase("images/icones/plus.png",40,40);
	IconBase previous = new IconBase("images/icones/left-arrow.png",40,40);
	private MenuH1Panel menuh1panel = new MenuH1Panel("Nouv. Contact", getClass().getSimpleName(),previous,create);
	private IconBase imageContact = new IconBase("images/photos/contact-1.png",480,300);
	private JPanel infosContact = new JPanel();
	
	private ChampLabel nom = new ChampLabel("Nom :","text");
	private ChampTextField textNom = new ChampTextField("");
	private ChampLabel prenom = new ChampLabel("Prénom :","text");
	private ChampTextField textPrenom = new ChampTextField("");
	private ChampLabel adresse = new ChampLabel("Adresse :","text");
	private ChampTextField textAdresse = new ChampTextField("");
	private ChampLabel vide = new ChampLabel("","text");
	private ChampTextField textLocalite = new ChampTextField("");
	private ChampLabel mail = new ChampLabel("Email :","mail");
	private ChampTextField textMail = new ChampTextField("");
	private ChampLabel phone = new ChampLabel("Téléphone :","text");
	private ChampTextField textPhone = new ChampTextField("");
	
	
	public NewContact() {
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
