/*
* Author : Vivian Bridy & Loris Clivaz
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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import Images.IconBase;
import MainFrame.Frame;
/***
 * Classe qui va g�rer toute l'application Contact
 * 
 * @author Vivian
 *
 */

public class ContactPanel extends JPanel
{
		Frame frame;
		Boolean isContact;
		Boolean isModifContact = false;
		ModifContact modifcontact;
		
		
		public Boolean getIsModifContact() {
			return isModifContact;
		}

		public void setIsModifContact(Boolean isModifContact) {
			this.isModifContact = isModifContact;
		}



		public void setModifcontact(ModifContact modifcontact) {
			this.modifcontact = modifcontact;
		}

		public Boolean getIsContact() {
			return isContact;
		}


		public void setIsContact(Boolean isContact) {
			this.isContact = isContact;
		}

		//Cr�ation du tableau de contact
		private ArrayList<Contact> listContact = new ArrayList<Contact>();

		//instanciation du panel du haut gallery
		MenuH1PanelContact menuh1panel = new MenuH1PanelContact("Contacts", "ContactPanel");
		
		//Cr�ation des panels  pour mettre les contacts
		JPanel center = new JPanel();
		JPanel allContact = new JPanel();
	
		//Gestion des panels dans les contacts
		private   CardLayout cardLayout = new CardLayout();
		private  JPanel triPanel = new JPanel(cardLayout);
		
		public CardLayout getCardLayout()
		{
			return cardLayout;
		}


		public JPanel getTriPanel()
		{
			return triPanel;
		}

		//Cr�ation panel poue new contact
		NewContact newcontact = new NewContact();

		//On cr�e la scrollBar
		JScrollPane scroll = new  JScrollPane(allContact);
		
		/**
		 * Constructeur de la classe ContactPanel
		 * - d�finit la taille du Panel et son layout
		 * - ajoute et affiche les diff�rents panels
		 * 
		 * @author Vivian
		 */
		public ContactPanel(Frame frame, boolean isContact)
		{
			this.frame = frame;
			this.isContact = isContact;
			//Choix du layout et de la dimension du panel

			this.setPreferredSize(new Dimension(480, 40));
			this.setLayout(new BorderLayout());
			
			this.add(menuh1panel, BorderLayout.NORTH);
			
			actualise();

			//Ajout du panel center � galleryPanelea
			this.add(triPanel, BorderLayout.CENTER);
			
			
			
			triPanel.add(newcontact, "newcontact");
			
			cardLayout.show(triPanel, "allcontact");

		}
		
		private int nombreFichier = 0;
		
		/**
		 * Setter qui d�finit le nombre de fichier contenu dans le dossier serialisation
		 * 
		 * @param nombreFichier le nombre de fichier
		 * @author Vivian
		 */
		
		private void setNombreFichier(int nombreFichier) {
			this.nombreFichier = nombreFichier;
		}


		/**
		 * Getter qui retourne le nombre de fichier contenu dans le dossier serialisation
		 * 
		 * @return : le nombre de fichier
		 * @author Vivian
		 */
		
		private int getNombreFichier() {
			return nombreFichier;
		}


		/**
		 * Permet d'actualiser la liste des contacts :
		 * - parcourt le dossier serialisation
		 * - cr�e un bouton par fichier
		 * - ajoute et affiche le panel
		 * 
		 * @author Vivian
		 */
		
		
		private void actualise()
		{
			//On calcule le nombre de contact dans le dossier serialisation
			File dossier = new File("serialisation");
			File[] f = dossier.listFiles();
			String path;
			Contact current;
			triPanel.remove(scroll);
			allContact = new JPanel();
			
			for (int i = 0 ; i < f.length ; i++) {
			  if (f[i].isFile()) {
				path = f[i].getAbsolutePath();
				current = deSerializeObject(path);
				FlatButton bouton = new FlatButton(current);
				bouton.addActionListener(new ClickContact(current));
				allContact.add(bouton);
				setNombreFichier(++nombreFichier);
			  }
			}
			
			if(nombreFichier>5)
			{
				allContact.setLayout(new GridLayout(getNombreFichier(),1));
			}
			else
			{
				allContact.setLayout(new GridLayout(5,1));
			}
			scroll = new JScrollPane(allContact);
			scroll.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
			
			triPanel.add(scroll, "scroll");
			
			
			
		}
		
		/**
		 * M�thode permettant de d�serialiser un contact
		 * 
		 * @param path : le chemin vers le fichier s�rialis�
		 * @return : le contact d�serialis� qui sera retourn�
		 * 
		 * @author Vivian
		 */
		
		
		private Contact deSerializeObject(String path)
		{

			try {
				FileInputStream fichier = new FileInputStream(path);
				ObjectInputStream ois = new ObjectInputStream(fichier);
				Contact cs = (Contact)ois.readObject();
				fichier.close();
				ois.close();
				return cs;
			}
			catch (java.io.IOException e)
			{
				e.printStackTrace();
				return null;
			}
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
				return null;
			}
		}
		
		
		
		/**
		 * 
		 * ActionListener qui se d�clenche quand on clique sur un contact
		 * On affiche la fiche du contact consern�
		 * 
		 * @author Vivian
		 *
		 */
		
		private class ClickContact implements ActionListener
		{

			Contact contact;
		
			/**
			 * Contructeur de la class ClickContact
			 * 
			 * @param contact : le contact click�
			 * @author Vivian
			 */
			
			public ClickContact(Contact contact)
			{
				this.contact = contact;
			}
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				//j'ai acc�s au contact cliqu� je dois donc afficher le panel modifcontact avec pour param�tre ce contact
				ModifContact modifcontact = new ModifContact(contact);
				setModifcontact(modifcontact);
				triPanel.add(modifcontact,"modifcontact");
				cardLayout.show(triPanel, "modifcontact");
				menuh1panel.setVisible(false);
			}
			
			
			
		}
		
		/**
		 * 
		 * Classe FlatButton utilis�e pour les boutons contact
		 * 
		 * @author Vivian
		 *
		 */
		
		public class FlatButton extends JButton 
		{
			String remplissage;
			/**
			 * Constructeur de la class FlatButton
			 * 
			 * @param contact le contact qui va �tre affich� dans le bouton
			 * @author Vivian
			 */
			public FlatButton(Contact contact) 
			{
				//petite image
				IconBase miniPhoto = new IconBase(contact.getUrlImage(), 100, 100);
				miniPhoto.addActionListener(new ClickContact(contact));
				miniPhoto.addMouseListener(new MouseMovement(this));
							
				//text
				JLabel text = new JLabel();
				text.setSize(new Dimension(300,100));
				remplissage = contact.getNom()+" "+contact.getPrenom();
				
				text.setText(remplissage);
				text.setFont(new Font("2.TimesRoman ",Font.BOLD,50));
				
				this.setLayout(new FlowLayout(FlowLayout.LEADING));
				this.add(miniPhoto);
				this.add(text);
				this.setBackground(Color.WHITE);
				this.addMouseListener(new MouseMovement(this));
			}
			
		
		}
		
		/**
		 * 
		 * Classe MouseMovement qui change la couleur du bouton contact :
		 * - quand on passe dessus
		 * - quand on ne passe plus dessus
		 * 
		 * @author Vivian
		 *
		 */
		
		public class MouseMovement implements MouseListener
		{
			FlatButton bouton; 			//On r�cup�re la variable du bouton survol�
			
			/**
			 * Constructeur de la classe MouseMovement
			 * 
			 * @param bouton : le bouton survol�
			 * @author Vivian
			 */
			public MouseMovement(FlatButton bouton)
			{
				this.bouton = bouton;
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0)
			{
				bouton.setBackground(Color.LIGHT_GRAY);
			}

			@Override
			public void mouseExited(MouseEvent arg0)
			{
				bouton.setBackground(Color.WHITE);
			}

			@Override
			public void mousePressed(MouseEvent arg0)
			{
			}

			@Override
			public void mouseReleased(MouseEvent arg0)
			{
			}
			
		}
		
		/**
		 * 
		 * Classe ChampLabel utilis�e pour afficher le champ d'informations d'un contact
		 * 
		 * @author Vivian
		 *
		 */
		
		private class ChampLabel extends JLabel
		{
			
			Font globalFontH2 = new Font("2.TimesRoman ",Font.BOLD,20);
			
			/**
			 * Constructeur de la classe ChampLabel
			 * 
			 * @param nom : le nom du champ du contact
			 * @param type : le type de donn�e voulue dans ce champ
			 * 
			 * @author Vivian
			 */
			public ChampLabel(String nom,String type)
			{
				// TODO Auto-generated constructor stubs
				super(nom);
				//Design des champs
				this.setPreferredSize(new Dimension(200,30));
				this.setFont(globalFontH2);
			
			}
		}

		/**
		 * 
		 * Classe ChampTextField qui permet d'�crire le champ du contact
		 * 
		 * @author Vivian
		 *
		 */
		private class ChampTextField extends JTextField
		{
			
			Font globalFontH2 = new Font("2.TimesRoman ",Font.BOLD,20);
			
			/**
			 * Constructeur de la classe ChampTextField
			 * 
			 * 
			 * @param text : le text du champ
			 * @author Vivian
			 */
			public ChampTextField(String text)
			{
				// TODO Auto-generated constructor stubs
				this.setText(text);
				this.setFont(globalFontH2);
			}
		}
		
		/**
		 * 
		 * M�thode qui remplit un tableau avec toutes les urls des images de la galerie
		 * 
		 * @param listImage : la liste des url des images
		 * @author Vivian
		 */
		
		private void fillImg(ArrayList<String> listImage)
		{
			//On calcule le nombre d'image dans le dossier imagesgallery
			File dossier = new File("imagesgallery");
			File[] f = dossier.listFiles();
			String path;
			Contact current;
						
			for (int i = 0 ; i < f.length ; i++)
			{
			  if (f[i].isFile()) 
			  {
				path = f[i].getAbsolutePath();
				listImage.add(path);
			  }
			}
		}
		
		/**
		 * M�thode qui contr�le si un num�ro de t�l�phone est valide
		 * 
		 * @param phone : le num�ro de t�l�phone � v�rifier
		 * @return : on retourne si oui ou non le num�ro est v�rifi�
		 */
		private boolean checkPhone(String phone)
		{
			if(Pattern.matches("[0-9]{10}", phone)==true)
				return true;
			return false;
		}
		
		/**
		 * M�thode qui contr�le si un mail est valide
		 * 
		 * @param mail : le mail � v�rifier
		 * @return : on retourne si oui ou non le mail est verifi�
		 */
		
		private boolean checkMail(String mail)
		{
			if(Pattern.matches("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$", mail)==true)
				return true;
			return false;
		}
		
		
		
		public NewContact getNewcontact() {
			return newcontact;
		}


		/**
		 * 
		 * Classe qui est appel�e lors de la cr�ation d'un nouveau contact
		 * 
		 * @author Vivian
		 *
		 */
		
		public class NewContact extends JPanel 
		{	
			private ArrayList<String> listImage = new ArrayList<String>();
			
			IconBase create = new IconBase("images/icones/plus.png",40,40);
			IconBase previous = new IconBase("images/icones/left-arrow.png",40,40);
			private MenuH1PanelContact menuh1panel2 = new MenuH1PanelContact("Nouv. Contact", getClass().getSimpleName());
			String urlImage = "images/photos/contact-1.png";
			private IconBase imageContact = new IconBase(urlImage,480,300);
			private JPanel infosContact = new JPanel();
			
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
			
			private SaveButton enregistrement = new SaveButton("Sauver le contact !");
			
			public IconBase getImageContact() {
				return imageContact;
			}

			/**
			 * Constructeur de la classe NewContact
			 * 
			 * @author Vivian
			 */
			
			public NewContact() 
			{
				this.setBackground(Color.decode("#EFEFEF")); 
				
				//On affiche titre H1 dans le panel UP
				this.add(menuh1panel2);
				
				//On remplit le tableau des liens d'images
				fillImg(listImage);
				ListIterator li = listImage.listIterator();
				
				//On d�finit la taille de l'image et on l'impl�mentes
				this.add(imageContact);
				imageContact.addActionListener(new ClickImage(li));
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
				textPhone.addKeyListener(new PressedEnter(enregistrement));
				infosContact.add(textPhone);
				infosContact.add(vide2);
				enregistrement.addActionListener(new ClickEnregistrement());
				infosContact.add(enregistrement);
				
				//On affiche les infos
				this.add(infosContact);
			}
			
			/**
			 * KeyListener qui est activ� lorsque l'on touche ENTER
			 * 
			 * @author Vivian
			 *
			 */
			
			private class PressedEnter implements KeyListener
			{
				SaveButton enregistrement;
				public PressedEnter(SaveButton enregistrement)
				{
					this.enregistrement = enregistrement;
				}
				
				@Override
				public void keyPressed(KeyEvent e) 
				{
					
					if(e.getKeyCode() == 10)
					{
						enregistrement.doClick();
					}
					    

				}

				@Override
				public void keyReleased(KeyEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void keyTyped(KeyEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
			}
			
			/**
			 * 
			 * ActionListener qui est appel� lors d'un click sur l'image d'un contact
			 * 
			 * @author Vivian
			 *
			 */
			
			private class ClickImage implements ActionListener
			{

				ListIterator li;
				
				/**
				 * 
				 * Constructeur de la class ClickImage
				 * 
				 * @param li : la liste des images 
				 * @author Vivian
				 */
				
				public ClickImage(ListIterator li)
				{
					// TODO Auto-generated constructor stub
					this.li = li;
				}
				
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					
					frame.getCardLayout().show(frame.getTriPanel(),"gallerypanel");
					isContact = true;
					
				}
				
			}
			
			/**
			 * 
			 * ActionListener qui est actif lors d'un click sur le bouton enregistrement d'un contact
			 * 
			 * @author Vivian
			 *
			 */
			
			private class ClickEnregistrement implements ActionListener
			{

				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					String nomSaisi = textNom.getText().trim();
					String prenomSaisi = textPrenom.getText().trim();
					String adresseSaisi = textAdresse.getText().trim();
					String localiteSaisi = textLocalite.getText().trim();
					String phoneSaisi = textPhone.getText().trim();
					String mailSaisi = textMail.getText().trim();
					
					//Check du nom
					if(nomSaisi.equals("") || nomSaisi.equals("ERREUR"))
					{
						textNom.setText("ERREUR");
						
						return;
					}
					
					//Check du prenom
					if(prenomSaisi.equals("") || prenomSaisi.equals("ERREUR"))
					{
						textPrenom.setText("ERREUR");
						
						return;
					}
					
					//Check de l'adresse
					if(adresseSaisi.equals("") || adresseSaisi.equals("ERREUR") || adresseSaisi.length()>18)
					{
						textAdresse.setText("ERREUR");
						
						return;
					}
					
					//Check de la localit�
					if(localiteSaisi.equals("") || localiteSaisi.equals("ERREUR") || localiteSaisi.length()>18)
					{
						textLocalite.setText("ERREUR");
						
						return;
					}
					
					//Check du mail
					if(!checkMail(mailSaisi) || mailSaisi.equals("ERREUR") || mailSaisi.length()>18)
					{
						textMail.setText("ERREUR");
						
						return;
					}
					
					//Check du phone
					if(!checkPhone(phoneSaisi) || phoneSaisi.equals("ERREUR"))
					{
						textPhone.setText("ERREUR");
						
						return;
					}
					
					String checklongeur = nomSaisi +" "+prenomSaisi;
					
					//Check longueur nom/pr�nom
					if(checklongeur.length() > 12)
					{
						textNom.setText("TROP LONG");
						textPrenom.setText("TROP LONG");
						
						return;
					}
					
					Contact contactEnCreation = new Contact(nomSaisi,
															prenomSaisi,
															adresseSaisi,
															localiteSaisi,
															mailSaisi,
															phoneSaisi,
															imageContact.getUrl());
					//On remet tout � z�rp
					textNom.setText("");
					textPrenom.setText("");
					textAdresse.setText("");
					textLocalite.setText("");
					textMail.setText("");
					textPhone.setText("");
					imageContact.setUrl("images/photos/contact-1.png");
					//On s�rialise
					this.serializeObject(contactEnCreation);
					//On actualise la liste des contact et on affiche cette derni�re
					actualiseContact();
					cardLayout.show(triPanel,"scroll");
					menuh1panel.setVisible(true);					
				}
				
				/**
				 * 
				 * M�thode qui va s�rialiser un contact
				 * 
				 * @param contactEnCreation : le contact qui va �tre s�rialis�
				 * @author Vivian
				 */
				
				private void serializeObject(Contact contactEnCreation)
				{
					try
					{
						FileOutputStream fichier = new FileOutputStream("serialisation\\contact-"+contactEnCreation.getId()+".ser");
						ObjectOutputStream oos = new ObjectOutputStream(fichier);
						oos.writeObject(contactEnCreation);
						oos.flush();
						oos.close();
						fichier.close();
					}
					catch (java.io.IOException e)
					{
						e.printStackTrace();
					}
				}
				
			}
		}
		
		/**
		 * M�thode qui va actualiser l'affichage de la liste de contact
		 * 
		 * @author Vivian
		 */
		
		private void actualiseContact() 
		{	
			//On calcule le nombre de contact dans le dossier serialisation
			allContact.removeAll();
			allContact.revalidate();
			
			File dossier = new File("serialisation");
			File[] f = dossier.listFiles();
			String path;
			Contact current;
			triPanel.remove(scroll);
			allContact = new JPanel();
			nombreFichier = 0;
			for (int i = 0 ; i < f.length ; i++) {
			  if (f[i].isFile()) {
				path = f[i].getAbsolutePath();
				current = deSerializeObject(path);
				FlatButton bouton = new FlatButton(current);
				bouton.addActionListener(new ClickContact(current));
				allContact.add(bouton);
				setNombreFichier(++nombreFichier);
			  }
			}
			
			
			if(nombreFichier>5)
			{
				allContact.setLayout(new GridLayout(getNombreFichier(),1));
			}
			else
			{
				allContact.setLayout(new GridLayout(5,1));
			}
			scroll = new JScrollPane(allContact);
			scroll.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
			
			triPanel.add(scroll, "scroll");
			

		}
		
		/**
		 * Classe qui d�finit le look du bouton enregistrer
		 * 
		 * @author Vivian
		 *
		 */
		
		public class SaveButton extends JButton
		{
			public SaveButton(String text)
			{
				// TODO Auto-generated constructor stub
				super(text);
				setBackground(Color.LIGHT_GRAY);
			}
		}
		
		public ModifContact getModifcontact() {
			return modifcontact;
		}

		/**
		 * Classe responsable d'afficher un contact et de permettre sa modification
		 * 
		 * @author Vivian
		 *
		 */
		
		public class ModifContact extends JPanel
		{
			private ArrayList<String> listImage = new ArrayList<String>();
			private JPanel infosContact = new JPanel();
			private CardLayout cardLayout;
			private JPanel triPanel;
			private SaveButton enregistrement = new SaveButton("Modifier le contact !");

			//Champ du formulaire
			private ChampTextField textNom,textPrenom,textAdresse,textLocalite,textMail,textPhone;
			
			IconBase imageContact;
			
			public IconBase getImageContact() {
				return imageContact;
			}

			/**
			 * 
			 * Constructeur de la classe ModifContact
			 * 
			 * @param contact : le contact affich� et modifiable
			 * @author Vivian
			 */
			
			public ModifContact(Contact contact) 
			{
				this.setBackground(Color.decode("#EFEFEF")); 
				
				//On remplit le tableau des liens d'images
				fillImg(listImage);
				ListIterator li = listImage.listIterator();
				
				//On affiche l'image courante du contact
				String urlImage = contact.getUrlImage();
				imageContact = new IconBase(urlImage,480,300);
				
				
				
				//On remplit le menuh1
				MenuH1PanelContact menuh1panel2 = new MenuH1PanelContact(contact.getPrenom(), getClass().getSimpleName(),contact);
				
				//On affiche titre H1 dans le panel UP
				this.add(menuh1panel2);
				
				//On d�finit la taille de l'image et on l'impl�mentes
				this.add(imageContact);
				imageContact.addActionListener(new ClickImage(li,imageContact));
				
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
				ChampTextField textLocalite = new ChampTextField(contact.getLocalite());
				this.textLocalite = textLocalite;
				ChampLabel mail = new ChampLabel("Email :","mail");
				ChampTextField textMail = new ChampTextField(contact.getMail());
				this.textMail = textMail;
				ChampLabel phone = new ChampLabel("T�l�phone :","text");
				ChampTextField textPhone = new ChampTextField(contact.getTelephone());
				this.textPhone = textPhone;
				this.textPhone.addKeyListener(new PressedEnter(enregistrement));
				
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
				enregistrement.addActionListener(new ClickEnregistrement(contact,imageContact));
				infosContact.add(enregistrement);
				
				//On affiche les infos
				this.add(infosContact);
			}
			
			
			
			/**
			 * KeyListener qui est activ� lorsque l'on touche ENTER
			 * 
			 * @author Vivian
			 *
			 */
			
			private class PressedEnter implements KeyListener
			{
				SaveButton enregistrement;
				public PressedEnter(SaveButton enregistrement)
				{
					this.enregistrement = enregistrement;
				}
				
				@Override
				public void keyPressed(KeyEvent e) 
				{
					
					if(e.getKeyCode() == 10)
					{
						enregistrement.doClick();
					}
					    

				}

				@Override
				public void keyReleased(KeyEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void keyTyped(KeyEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
			}
			
			/**
			 * ActionListener qui se d�clenche lors d'un click sur l'image du contact
			 * 
			 * @author Vivian
			 *
			 */
			
			private class ClickImage implements ActionListener
			{

				ListIterator li;
				IconBase imageContact;
				
				/**
				 * Constructeur de la classe ClickImage
				 * 
				 * @param li : la liste des images � disposition du contact
				 * @param imageContact : l'image actuelle du contact
				 * @author Vivian
				 */
				
				public ClickImage(ListIterator li,IconBase imageContact)
				{
					this.li = li;
					this.imageContact = imageContact;
				}
				
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
//						if (li.hasNext()) {
//								imageContact.setUrl((String)li.next()); 	//j'essaie de caster en force
//						}else {
//							for(int i=0;i<listImage.size();i++) {
//								imageContact.setUrl((String)li.previous());	//on revient au d�but
//							}
//						}
					frame.getCardLayout().show(frame.getTriPanel(),"gallerypanel");
					isModifContact = true;

				}
				
			}
			
			/**
			 * ActionListener d�clench� par le click du bouton enregistrement
			 * 
			 * @author Vivian
			 *
			 */
			
			private class ClickEnregistrement implements ActionListener
			{
				Contact contact;
				IconBase imageContact;
				
				/**
				 * Constructeur de la classe CLickEnregistrement
				 * 
				 * @param contact : le contact qui est modifi�
				 * @param imageContact : l'image du contact modifi�
				 * @author Vivian
				 */
				
				public ClickEnregistrement(Contact contact,IconBase imageContact)
				{
					this.contact = contact;
					this.imageContact = imageContact;
				}
				
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					

					String nomSaisi = textNom.getText().trim();
					String prenomSaisi = textPrenom.getText().trim();
					String adresseSaisi = textAdresse.getText().trim();
					String localiteSaisi = textLocalite.getText().trim();
					String phoneSaisi = textPhone.getText().trim();
					String mailSaisi = textMail.getText().trim();
					
					//Check du nom
					if(nomSaisi.equals("") || nomSaisi.equals("ERREUR"))
					{
						textNom.setText("ERREUR");
						
						return;
					}
					
					//Check du prenom
					if(prenomSaisi.equals("") || prenomSaisi.equals("ERREUR"))
					{
						textPrenom.setText("ERREUR");
						
						return;
					}
					
					//Check de l'adresse
					if(adresseSaisi.equals("") || adresseSaisi.equals("ERREUR"))
					{
						textAdresse.setText("ERREUR");
						
						return;
					}
					
					//Check de la localit�
					if(localiteSaisi.equals("") || localiteSaisi.equals("ERREUR"))
					{
						textLocalite.setText("ERREUR");
						
						return;
					}
					
					//Check du mail
					if(!checkMail(mailSaisi) || mailSaisi.equals("ERREUR"))
					{
						textMail.setText("ERREUR");
						
						return;
					}
					
					//Check du phone
					if(!checkPhone(phoneSaisi) || phoneSaisi.equals("ERREUR"))
					{
						textPhone.setText("ERREUR");
						
						return;
					}
					
					String checklongeur = nomSaisi +" "+prenomSaisi;
					
					//Check longueur nom/pr�nom
					if(checklongeur.length() > 12)
					{
						textNom.setText("TROP LONG");
						textPrenom.setText("TROP LONG");
						
						return;
					}
					
					allContact.removeAll();
					allContact.revalidate();
					
					Contact contactEnCreation = new Contact(nomSaisi,
															prenomSaisi,
															adresseSaisi,
															localiteSaisi,
															mailSaisi,
															phoneSaisi,
															imageContact.getUrl());
					//On supprime l'ancien contact					
					
					File filedeleted = new File("serialisation\\contact-"+contact.getId()+".ser");
					
					
					delete(filedeleted);
					
					
					this.serializeObject(contactEnCreation);
					
					

					
					//On actualise la liste des contact et on affiche cette derni�re
					actualiseContact();
					getCardLayout().show(getTriPanel(), "scroll");
					menuh1panel.setVisible(true);
					
				}
				
				
				/**
				 * M�thode permettant de delete un fichier
				 * 
				 * @param url : chemin du fichier
				 * obliger d'ordonner en methode sinon la suppression bug
				 * @author Loris
				 */
				public void delete(File url)
				{
					url.delete();
				}
				
				/**
				 * M�thode qui va s�rialis� un contact
				 * 
				 * @param contactEnCreation : le contact qui va �tre s�rialis�
				 * @author Vivian
				 */
				
				public void serializeObject(Contact contactEnCreation)
				{
					try
					{
						FileOutputStream fichier = new FileOutputStream("serialisation\\contact-"+contactEnCreation.getId()+".ser");
						ObjectOutputStream oos = new ObjectOutputStream(fichier);
						oos.writeObject(contactEnCreation);
						oos.flush();
						oos.close();
						fichier.close();
					}
					catch (java.io.IOException e) 
					{
						e.printStackTrace();
					}
				}
			}
		}
		
		/**
		 * Classe reponsable de l'affichage du menu en haut de chaque panel
		 * 
		 * @author Vivian
		 *
		 */
		
		private class MenuH1PanelContact extends JPanel
		{

			//D�finit un titre sur le panel avec une couleurs
			JLabel titrePanel;
			Font globalFont = new Font("2.TimesRoman ",Font.BOLD,50);
			
			//On instancie les icones pour les ajouters sur le panel
			IconBase create = new IconBase("images/icones/plus.png",40,40);
			IconBase previous = new IconBase("images/icones/left-arrow.png",40,40);
			IconBase vide = new IconBase("images/icones/left-arrow.png",40,40);
			IconBase delete = new IconBase("images/icones/delete-black.png",40,40);
			
			String titre, nomClass;

			/**
			 * Constructeur de la classe MenuH1PanelContact avec deux param�tres
			 * 
			 * @param titre : le titre du panel afficher
			 * @param nomClass : le nom de la classe utilis�e
			 * @author Vivian
			 */
			
			public MenuH1PanelContact(String titre, String nomClass)
			{
				this.titre = titre;
				this.nomClass = nomClass;
				titrePanel = new JLabel(getTitre());
				titrePanel.setFont(globalFont);

				this.setPreferredSize(new Dimension(480, 78));
				this.setBackground(Color.decode("#DFDFDF"));

				this.setLayout(new FlowLayout(FlowLayout.CENTER,10,8)); 	//61 est la valeur max
				if(nomClass.equals("ContactPanel"))
				{
				}else 
				{
					this.add(previous, BorderLayout.WEST);
				}
				//On met le titre au centre
				this.add(titrePanel, BorderLayout.CENTER);

				//On met le plus � droite
				if(nomClass.equals("NewContact"))
					this.remove(create);
				else
					this.add(create, BorderLayout.EAST);

				//On met un listener sur le bouton
				create.addActionListener(new ClickCreate());
				previous.addActionListener(new ClickPrevious());

			}
			
			/**
			 * Constructeur de la classe MenuH1PanelContact avec trois param�tres
			 * 
			 * @param titre : le titre du panel afficher
			 * @param nomClass : le nom de la classe utilis�e
			 * @param contact : le contact affich�
			 * @author Vivian
			 */
			
			public MenuH1PanelContact(String titre, String nomClass, Contact contact)
			{
				this.titre = titre;
				this.nomClass = nomClass;
				titrePanel = new JLabel(getTitre());
				titrePanel.setFont(globalFont);

				this.setPreferredSize(new Dimension(480, 78));
				this.setBackground(Color.decode("#DFDFDF"));

				this.setLayout(new FlowLayout(FlowLayout.CENTER,10,8)); 	//61 est la valeur max
				if(nomClass.equals("ContactPanel"))
				{
				}else
				{
					this.add(previous, BorderLayout.WEST);
				}
				//On met le titre au centre
				this.add(titrePanel, BorderLayout.CENTER);
				
				//Ajout de le l'icone delete
				if(nomClass.equals("ModifContact"))
					this.add(delete, BorderLayout.EAST);

				//On met un listener sur le bouton
				create.addActionListener(new ClickCreate());
				previous.addActionListener(new ClickPrevious());
				delete.addActionListener(new ClickDelete(contact));

			}

			public void setTitre(String titre)
			{
				this.titre = titre;
			}
			
			public String getTitre() 
			{
				return titre;
			}
			
			/**
			 * ActionListener qui est d�clench� par le click sur le bouton pr�c�dent
			 * 
			 * @author Vivian
			 *
			 */
			
			class ClickPrevious implements ActionListener
			{

				@Override
				public void actionPerformed(ActionEvent e)
				{
					cardLayout.show(triPanel, "scroll");
					menuh1panel.setVisible(true);
					
				}
				
			}

			/**
			 * ActionListener qui est d�clench� par le click sur le bouton create
			 * 
			 * @author Vivian
			 *
			 */
			
			class ClickCreate implements ActionListener
			{

				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					cardLayout.show(triPanel, "newcontact");
					menuh1panel.setVisible(false);
				}
			}
			
			/**
			 * ActionListener qui est d�clench� par le click sur le bouton delete
			 * 
			 * @author Vivian
			 *
			 */
			
			class ClickDelete implements ActionListener
			{

				Contact contact;
				
				/**
				 * Constructeur de la classe ClickDelete
				 * 
				 * @param contact : le contact supprim�
				 * @author Vivian
				 */
				public ClickDelete(Contact contact)
				{
					this.contact = contact;
				}
				
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					// TODO Auto-generated method stub
					allContact.removeAll();
					allContact.revalidate();
					File filedeleted = new File("serialisation\\contact-"+contact.getId()+".ser");
					filedeleted.delete();
					//On actualise la liste des contact et on affiche cette derni�re
					actualiseContact();
					cardLayout.show(triPanel, "scroll");
					menuh1panel.setVisible(true);
				}
			}
		}			
}