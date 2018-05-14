/*
* Author : Vivian Bridy
* Date creation : 14 mai 2018
*/
package ContactApp;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import GalerieApp.Picture;
import Panels.MenuH1PanelContact;
import GalerieApp.GalleryPanel.PanelGallery;
import GalerieApp.GalleryPanel.minipicture;

public class ContactPanel extends JPanel{
		//Création du tableau de contact
		private ArrayList<Contact> listContact = new ArrayList<Contact>();

		//instanciation du panel du haut gallery
		MenuH1PanelContact menuh1panel = new MenuH1PanelContact("Contacts", "ContactPanel");
		
		//Création des panels  pour mettre les contacts
		JPanel center = new JPanel();
		JPanel allContact = new JPanel();

		//Gestion des panels dans les contacts
		public   CardLayout cardLayout = new CardLayout();
		public  JPanel triPanel = new JPanel(cardLayout);

		//On crée la scrollBar
		JScrollPane scroll = new  JScrollPane(allContact);
		
		public ContactPanel() {
			// TODO Auto-generated constructor stub
			//Choix du layout et de la dimension du panel

			this.setPreferredSize(new Dimension(480, 40));
			this.setLayout(new BorderLayout());
			this.add(menuh1panel, BorderLayout.NORTH);


			center.setLayout(new GridLayout(6,2,0,0));
			
			scroll.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));


			//Ajout du panel center à galleryPanelea
			this.add(triPanel, BorderLayout.CENTER);
			
			
			for(int i=0;i<10;i++) {
				//On ajoute des contacts à listContact
				listContact.add(new Contact("Bridy", "Vivian", "Route de Fellina 3", "0798434456", "vbridy2@gmail.com"));
				JButton boutonTest = new JButton();
				boutonTest.setPreferredSize(new Dimension(400,100));
				boutonTest.setText(listContact.get(0).getNom());
				allContact.add(boutonTest);
			}
			
//			triPanel.add(scroll, "scroll");
			triPanel.add(allContact, "allcontact");
			
			cardLayout.show(triPanel, "allcontact");

			
		}
		
		public class Contact implements Serializable {
			private String nom,prenom,adresse,telephone,mail;
			
			public Contact(String nom,String prenom,String adresse,String telephone,String mail) {
				// TODO Auto-generated constructor stub
				setNom(nom);
				setPrenom(prenom);
				setAdresse(adresse);
				setTelephone(telephone);
				setMail(mail);
			}

			public String getNom() {
				return nom;
			}

			public void setNom(String nom) {
				this.nom = nom;
			}

			public String getPrenom() {
				return prenom;
			}

			public void setPrenom(String prenom) {
				this.prenom = prenom;
			}

			public String getAdresse() {
				return adresse;
			}

			public void setAdresse(String adresse) {
				this.adresse = adresse;
			}

			public String getTelephone() {
				return telephone;
			}

			public void setTelephone(String telephone) {
				this.telephone = telephone;
			}

			public String getMail() {
				return mail;
			}

			public void setMail(String mail) {
				this.mail = mail;
			}
		}
		

}

