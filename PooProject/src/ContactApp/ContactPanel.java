/*
* Author : Vivian Bridy
* Date creation : 14 mai 2018
*/
package ContactApp;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import GalerieApp.Picture;
import Images.IconBase;


public class ContactPanel extends JPanel{
		//Cr�ation du tableau de contact
		private ArrayList<Contact> listContact = new ArrayList<Contact>();

		//instanciation du panel du haut gallery
		MenuH1PanelContact menuh1panel = new MenuH1PanelContact("Contacts", "ContactPanel");
		
		//Cr�ation des panels  pour mettre les contacts
		JPanel center = new JPanel();
		JPanel allContact = new JPanel();
	
		//Gestion des panels dans les contacts
		public   CardLayout cardLayout = new CardLayout();
		public  JPanel triPanel = new JPanel(cardLayout);
		
		//Cr�ation panel poue new contact
		NewContact newcontact = new NewContact();

		//On cr�e la scrollBar
		JScrollPane scroll = new  JScrollPane(allContact);
		
		public ContactPanel() {
			// TODO Auto-generated constructor stub
			//Choix du layout et de la dimension du panel

			this.setPreferredSize(new Dimension(480, 40));
			this.setLayout(new BorderLayout());
			
			this.add(menuh1panel, BorderLayout.NORTH);
			
			allContact.setLayout(new GridLayout(0,1));

			center.setLayout(new GridLayout(6,2,0,0));
			
			scroll.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));


			//Ajout du panel center � galleryPanelea
			this.add(triPanel, BorderLayout.CENTER);
			
			//On calcule le nombre de contact dans le dossier serialisation
			File dossier = new File("serialisation");
			File[] f = dossier.listFiles();
			String path;
			Contact current;
			int nombreFichier = 0;
			for (int i = 0 ; i < f.length ; i++) {
			  if (f[i].isFile()) {
				path = f[i].getAbsolutePath();
				current = deSerializeObject(path);
				FlatButton bouton = new FlatButton();
				bouton.addActionListener(new ClickContact(current));
				bouton.setText(current.getNom()+" "+current.getPrenom());
				bouton.setPreferredSize(new Dimension(400,120));
				allContact.add(bouton);
			  }
			}
			
			

			triPanel.add(scroll, "scroll");
			triPanel.add(newcontact, "newcontact");
			
			cardLayout.show(triPanel, "allcontact");

		}
		
		
		public Contact deSerializeObject(String path) {

			try {
				FileInputStream fichier = new FileInputStream(path);
				ObjectInputStream ois = new ObjectInputStream(fichier);
				Contact cs = (Contact)ois.readObject();
				return cs;
			}
			catch (java.io.IOException e) {
				e.printStackTrace();
				return null;
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			}
		}

		public class Contact implements Serializable {
			private String nom,prenom,adresse,localite,telephone,mail;
			
			public Contact(String nom,String prenom,String adresse,String localite,String telephone,String mail) {
				// TODO Auto-generated constructor stub
				setNom(nom);
				setPrenom(prenom);
				setAdresse(adresse);
				setLocalite(localite);
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
			
			public String getLocalite() {
				return localite;
			}

			public void setLocalite(String localite) {
				this.localite = localite;
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
		
		public class ClickContact implements ActionListener{

			Contact contact;
			public ClickContact(Contact contact) {
				// TODO Auto-generated constructor stub
				this.contact = contact;
			}
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("contact "+contact.getNom()+" "+contact.getPrenom()+" cliqu�");
				//j'ai acc�s au contact cliqu� je dois donc afficher le panel modifcontact avec pour param�tre ce contact
				ModifContact modifcontact = new ModifContact(contact);
				triPanel.add(modifcontact,"modifcontact");
				cardLayout.show(triPanel, "modifcontact");
				menuh1panel.setVisible(false);
			}
			
		}
		
		public class FlatButton extends JButton {
			public FlatButton() {
				// TODO Auto-generated constructor stub
				this.setBackground(Color.WHITE);
				this.setFont(new Font("2.TimesRoman ",Font.BOLD,50));
				this.addMouseListener(new MouseMovement(this));
			}
		}
		
		public class MouseMovement implements MouseListener{

			FlatButton bouton;
			public MouseMovement(FlatButton bouton) {
				// TODO Auto-generated constructor stub
				this.bouton = bouton;
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				bouton.setBackground(Color.LIGHT_GRAY);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				bouton.setBackground(Color.WHITE);
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
			
		}
		
		public class ChampLabel extends JLabel {
			
			Font globalFontH2 = new Font("2.TimesRoman ",Font.BOLD,20);
			
			public ChampLabel(String nom,String type) {
				// TODO Auto-generated constructor stubs
				super(nom);
				//Design des champs
				this.setPreferredSize(new Dimension(200,30));
				this.setFont(globalFontH2);
			
			}
		}

		public class ChampTextField extends JTextField{
			
			Font globalFontH2 = new Font("2.TimesRoman ",Font.BOLD,20);
			
			public ChampTextField(String text) {
				// TODO Auto-generated constructor stubs
				this.setText(text);
				this.setFont(globalFontH2);
			}
		}
		
		public class NewContact extends JPanel 
		{	
			
			IconBase create = new IconBase("images/icones/plus.png",40,40);
			IconBase previous = new IconBase("images/icones/left-arrow.png",40,40);
			private MenuH1PanelContact menuh1panel2 = new MenuH1PanelContact("Nouv. Contact", getClass().getSimpleName());
			private IconBase imageContact = new IconBase("images/photos/contact-1.png",480,300);
			private JPanel infosContact = new JPanel();
			public   CardLayout cardLayout;
			public  JPanel triPanel;
			
			private ChampLabel nom = new ChampLabel("Nom :","text");
			private ChampTextField textNom = new ChampTextField("");
			private ChampLabel prenom = new ChampLabel("Pr�nom :","text");
			private ChampTextField textPrenom = new ChampTextField("");
			private ChampLabel adresse = new ChampLabel("Adresse :","text");
			private ChampTextField textAdresse = new ChampTextField("");
			private ChampLabel vide = new ChampLabel("","text");
			private ChampLabel vide2 = new ChampLabel("","text");
			private ChampTextField textLocalite = new ChampTextField("");
			private ChampLabel mail = new ChampLabel("Email :","mail");
			private ChampTextField textMail = new ChampTextField("");
			private ChampLabel phone = new ChampLabel("T�l�phone :","text");
			private ChampTextField textPhone = new ChampTextField("");
			
			private JButton enregistrement = new JButton("Sauver le contact !");
			
			public NewContact() {
				// TODO Auto-generated constructor stub
				
				this.setBackground(Color.decode("#EFEFEF")); 
				
				//On affiche titre H1 dans le panel UP
				this.add(menuh1panel2);
				
				//On d�finit la taille de l'image et on l'impl�mentes
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
				

				
			}
			
			
			
			class ClickEnregistrement implements ActionListener{

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					Contact contactEnCreation = new Contact(textNom.getText(),
															textPrenom.getText(),
															textAdresse.getText(),
															textLocalite.getText(),
															textMail.getText(),
															textPhone.getText());
					this.serializeObject(contactEnCreation);
					System.out.println("S�rialisation effectu�e");
					
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
				
			}
		}

		public class ModifContact extends JPanel{
//			IconBase create = new IconBase("images/icones/plus.png",40,40);
//			IconBase previous = new IconBase("images/icones/left-arrow.png",40,40);
			
			private IconBase imageContact = new IconBase("images/photos/contact-1.png",480,300);
			private JPanel infosContact = new JPanel();
			public   CardLayout cardLayout;
			public  JPanel triPanel;
			private JButton enregistrement = new JButton("Sauver le contact !");
			
			//Champ du formulaire
			private ChampTextField textNom,textPrenom,textAdresse,textLocalite,textMail,textPhone;
			
			public ModifContact(Contact contact) {
				// TODO Auto-generated constructor stub
				this.setBackground(Color.decode("#EFEFEF")); 
				
				//On remplit le menuh1
				MenuH1PanelContact menuh1panel2 = new MenuH1PanelContact(contact.getPrenom(), getClass().getSimpleName());
				
				//On affiche titre H1 dans le panel UP
				this.add(menuh1panel2);
				
				//On d�finit la taille de l'image et on l'impl�mentes
				this.add(imageContact);
				
				//On met les infos dans le gridpanel
				infosContact.setLayout(new GridLayout(7,2,10,10)); 		//(ligne,colonne,espace,espace)
				
				//On remplit les champs avec les infos
				ChampLabel nom = new ChampLabel("Nom :","text");
				ChampTextField textNom = new ChampTextField(contact.getNom());
				this.textNom = textNom;
				ChampLabel prenom = new ChampLabel("Pr�nom :","text");
				ChampTextField textPrenom = new ChampTextField(contact.getPrenom());
				this.textPrenom = textPrenom;
				ChampLabel adresse = new ChampLabel("Adresse :","text");
				ChampTextField textAdresse = new ChampTextField(contact.getAdresse());
				this.textAdresse = textAdresse;
				ChampLabel vide = new ChampLabel("","text");
				ChampLabel vide2 = new ChampLabel("","text");
				ChampTextField textLocalite = new ChampTextField("");
				this.textLocalite = textLocalite;
				ChampLabel mail = new ChampLabel("Email :","mail");
				ChampTextField textMail = new ChampTextField(contact.getMail());
				this.textMail = textMail;
				ChampLabel phone = new ChampLabel("T�l�phone :","text");
				ChampTextField textPhone = new ChampTextField(contact.getTelephone());
				this.textPhone = textPhone;
				
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
			}
			
			
			
			class ClickEnregistrement implements ActionListener{

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					Contact contactEnCreation = new Contact(textNom.getText(),
															textPrenom.getText(),
															textAdresse.getText(),
															textLocalite.getText(),
															textMail.getText(),
															textPhone.getText());
					this.serializeObject(contactEnCreation);
					System.out.println("S�rialisation effectu�e");
					
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
						System.out.println("Classe deserialis�e : ");
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
		
		public class MenuH1PanelContact extends JPanel{

			//D�finit un titre sur le panel avec une couleurs
			JLabel titrePanel;
			Font globalFont = new Font("2.TimesRoman ",Font.BOLD,50);
			
			//On instancie les icones pour les ajouters sur le panel
			IconBase create = new IconBase("images/icones/plus.png",40,40);
			IconBase previous = new IconBase("images/icones/left-arrow.png",40,40);
			IconBase vide = new IconBase("images/icones/left-arrow.png",40,40);
			IconBase delete = new IconBase("images/icones/delete.png",40,40);
			
			String titre, nomClass;

			public MenuH1PanelContact(String titre, String nomClass)
			// TODO Auto-generated constructor stube
			{
				this.titre = titre;
				this.nomClass = nomClass;
				titrePanel = new JLabel(getTitre());
				titrePanel.setFont(globalFont);

				this.setPreferredSize(new Dimension(480, 78));
				this.setBackground(Color.decode("#DFDFDF"));

				this.setLayout(new FlowLayout(FlowLayout.CENTER,10,8)); 	//61 est la valeur max
				if(nomClass.equals("ContactPanel")) {
//					this.add(vide, BorderLayout.WEST);
				}else {
					this.add(previous, BorderLayout.WEST);
				}
				//On met le titre au centre
				this.add(titrePanel, BorderLayout.CENTER);

				//On met le plus � droite
				if(nomClass.equals("NewContact"))
					this.remove(create);
				else
					this.add(create, BorderLayout.EAST);
				
				//Ajout de le l'icone delete
				if(nomClass.equals("ModifContact")) {
					this.add(delete, BorderLayout.EAST);
				}

				//On met un listener sur le bouton
				create.addActionListener(new ClickCreate());
				previous.addActionListener(new ClickPrevious());

			}
			
			public MenuH1PanelContact(String titre, String nomClass, Contact contact)
			// TODO Auto-generated constructor stube
			{
				this.titre = titre;
				this.nomClass = nomClass;
				titrePanel = new JLabel(getTitre());
				titrePanel.setFont(globalFont);

				this.setPreferredSize(new Dimension(480, 78));
				this.setBackground(Color.decode("#DFDFDF"));

				this.setLayout(new FlowLayout(FlowLayout.CENTER,10,8)); 	//61 est la valeur max
				if(nomClass.equals("ContactPanel")) {
//					this.add(vide, BorderLayout.WEST);
				}else {
					this.add(previous, BorderLayout.WEST);
				}
				//On met le titre au centre
				this.add(titrePanel, BorderLayout.CENTER);

				//On met le plus � droite
				if(nomClass.equals("NewContact"))
					this.remove(create);
				else
					this.add(create, BorderLayout.EAST);
				
				//Ajout de le l'icone delete
				if(nomClass.equals("ModifContact")) {
					this.remove(create);
					this.add(delete, BorderLayout.EAST);
				}

				//On met un listener sur le bouton
				create.addActionListener(new ClickCreate());
				previous.addActionListener(new ClickPrevious());
				delete.addActionListener(new ClickDelete(contact));

			}

			public void setTitre(String titre) {
				this.titre = titre;
			}
			
			public String getTitre() {
				return titre;
			}
			
			//quand on clique sur le bouton previous
			
			class ClickPrevious implements ActionListener
			{

				@Override
				public void actionPerformed(ActionEvent e) {
					cardLayout.show(triPanel, "scroll");
					menuh1panel.setVisible(true);
					
				}
				
			}

			//quand on clique sur le bouton create
			class ClickCreate implements ActionListener{

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					cardLayout.show(triPanel, "newcontact");
					menuh1panel.setVisible(false);
					System.out.println("create cliqu�");
				}
			}
			
			//quand on clique sur le bouton delete
			class ClickDelete implements ActionListener{

				Contact contact;
				public ClickDelete(Contact contact) {
					// TODO Auto-generated constructor stub
					this.contact = contact;
				}
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					File delete = new File("serialisation\\"+contact.getNom()+"-"+contact.getPrenom()+".ser");
					delete.delete();
					cardLayout.show(triPanel, "scroll");
					menuh1panel.setVisible(true);
					System.out.println("delete cliqu�");
				}
			}
		}
}