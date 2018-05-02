/*
* Author : Vivian Bridy
* 
* Date creation : 1 mai 2018
*/
package ContactApp;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JPanel;

import Images.IconBase;
import Panels.MenuH1PanelContact;
import Panels.MenuH1PanelGallery;
import ContactApp.ContactPanel;

public class NewContact extends JPanel 
{	
	
	IconBase create = new IconBase("images/icones/plus.png",40,40);
	IconBase previous = new IconBase("images/icones/left-arrow.png",40,40);
	private MenuH1PanelContact menuh1panel = new MenuH1PanelContact("Nouv. Contact", getClass().getSimpleName());
	private IconBase imageContact = new IconBase("images/photos/contact-1.png",480,300);
	private JPanel infosContact = new JPanel();
	public   CardLayout cardLayout;
	public  JPanel triPanel;
	
	private ChampLabel nom = new ChampLabel("Nom :","text");
	private ChampTextField textNom = new ChampTextField("");
	private ChampLabel prenom = new ChampLabel("Prénom :","text");
	private ChampTextField textPrenom = new ChampTextField("");
	private ChampLabel adresse = new ChampLabel("Adresse :","text");
	private ChampTextField textAdresse = new ChampTextField("");
	private ChampLabel vide = new ChampLabel("","text");
	private ChampLabel vide2 = new ChampLabel("","text");
	private ChampTextField textLocalite = new ChampTextField("");
	private ChampLabel mail = new ChampLabel("Email :","mail");
	private ChampTextField textMail = new ChampTextField("");
	private ChampLabel phone = new ChampLabel("Téléphone :","text");
	private ChampTextField textPhone = new ChampTextField("");
	
	private JButton enregistrement = new JButton("Sauver le contact !");
	
	public NewContact(CardLayout cardlayout,JPanel triPanel) {
		// TODO Auto-generated constructor stub
		this.cardLayout = cardlayout;
		this.triPanel = triPanel;
		
		this.setBackground(Color.decode("#EFEFEF")); 
		
		//On affiche titre H1 dans le panel UP
		this.add(menuh1panel);
		
		//On définit la taille de l'image et on l'implémentes
		this.add(imageContact);
		
		//On met les infos dans le gridpanel
		infosContact.setLayout(new GridLayout(7,2,10,10)); 		//(ligne,colonne,espace,espace)
		
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
		infosContact.add(vide2);
		enregistrement.addActionListener(new ClickEnregistrement());
		infosContact.add(enregistrement);
		
		//On affiche les infos
		this.add(infosContact);
		
		menuh1panel.setCardLayout(cardLayout,triPanel);
		
	}
	
	
	
	class ClickEnregistrement implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			Contact contactEnCreation = new Contact(textNom.getText(),
													textPrenom.getText(),
													textAdresse.getText()+"/"+textLocalite.getText(),
													textMail.getText(),
													textPhone.getText());
			this.serializeObject(contactEnCreation);
			System.out.println("Sérialisation effectuée");
			cardLayout.show(triPanel, "listepanel");
			
		}
		
		public void serializeObject(Contact contactEnCreation) {
			try {
				FileOutputStream fichier = new FileOutputStream("serialisation\\"+contactEnCreation.getNom()+"-"+contactEnCreation.getPrenom()+".ser");
				ObjectOutputStream oos = new ObjectOutputStream(fichier);
				oos.writeObject(contactEnCreation);
				oos.flush();
				oos.close();
			}
			catch (java.io.IOException e) {
				e.printStackTrace();
			}
		}
		
		public void deSerializeObject(Contact contactEnCreation) { 
			try {
				FileInputStream fichier = new FileInputStream("serialisation\\"+contactEnCreation.getNom()+"-"+contactEnCreation.getPrenom()+".ser");
				ObjectInputStream ois = new ObjectInputStream(fichier);
				Contact cs = (Contact) ois.readObject();
				System.out.println("Classe deserialisée : ");
				System.out.println("Nom : " + cs.getNom());
				System.out.println("Age : " + cs.getPrenom());
				System.out.println("Sexe : " + cs.getAdresse());
				System.out.println("Mot de passe : " + cs.getMail());
				System.out.println("Ville : " + cs.getTelephone());
			}
			catch (java.io.IOException e) {
				e.printStackTrace();
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
	}
}
