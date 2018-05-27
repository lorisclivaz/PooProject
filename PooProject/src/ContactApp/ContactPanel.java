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
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
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
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import GalerieApp.GalleryPanel;
import GalerieApp.Picture;
import GalerieApp.GalleryPanel.PhotoPanel;
import Images.IconBase;
import Images.ImageContact;
import Images.ImageFond;
import MainFrame.Frame;
/**
 * Classe qui va gérer toute l'application Contact
 * 
 * @author Vivian
 *
 */

public class ContactPanel extends JPanel
{
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
		
		public CardLayout getCardLayout()
		{
			return cardLayout;
		}


		public JPanel getTriPanel()
		{
			return triPanel;
		}

		//Création panel poue new contact
		NewContact newcontact = new NewContact();

		//On crée la scrollBar
		JScrollPane scroll = new  JScrollPane(allContact);
		
		/**
		 * Constructeur de la classe ContactPanel
		 * - définit la taille du Panel et son layout
		 * - ajoute et affiche les différents panels
		 * 
		 * @author Vivian
		 */
		public ContactPanel()
		{
			//Choix du layout et de la dimension du panel

			this.setPreferredSize(new Dimension(480, 40));
			this.setLayout(new BorderLayout());
			
			this.add(menuh1panel, BorderLayout.NORTH);
			
			actualise();

			//Ajout du panel center à galleryPanelea
			this.add(triPanel, BorderLayout.CENTER);
			
			
			
			triPanel.add(newcontact, "newcontact");
			
			cardLayout.show(triPanel, "allcontact");

		}
		
		private int nombreFichier = 0;
		
		/**
		 * Setter qui définit le nombre de fichier contenu dans le dossier serialisation
		 * 
		 * @return : le nombre de fichier
		 * @author Vivian
		 */
		
		public void setNombreFichier(int nombreFichier) {
			this.nombreFichier = nombreFichier;
		}


		/**
		 * Getter qui retourne le nombre de fichier contenu dans le dossier serialisation
		 * 
		 * @return : le nombre de fichier
		 * @author Vivian
		 */
		public int getNombreFichier() {
			return nombreFichier;
		}


		/**
		 * Permet d'actualiser la liste des contacts :
		 * - parcourt le dossier serialisation
		 * - crée un bouton par fichier
		 * - ajoute et affiche le panel
		 * 
		 * @author Vivian
		 */
		
		
		public void actualise()
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

			
			System.out.println("nombreFichier "+nombreFichier);
			
			triPanel.add(scroll, "scroll");
			
			
			
		}
		
		/**
		 * Méthode permettant de déserialiser un contact
		 * 
		 * @param path : le chemin vers le fichier sérialisé
		 * @return : le contact déserialisé qui sera retourné
		 * 
		 * @author Vivian
		 */
		
		
		public Contact deSerializeObject(String path)
		{

			try {
				FileInputStream fichier = new FileInputStream(path);
				ObjectInputStream ois = new ObjectInputStream(fichier);
				Contact cs = (Contact)ois.readObject();
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
		
		public String lecture()
		{
			String resultat="";
			File dossier = new File("src/ContactApp");
			dossier.mkdir();
			
			File fichier = new File(dossier,"id.txt");
			
			try {
			
				FileReader read = new FileReader(fichier);
				BufferedReader bfread = new BufferedReader(read);
				resultat = bfread.readLine();
				
			} catch (IOException e) {

				e.printStackTrace();
			}
			
			
			return resultat;
			
		}
//		private int id=0;	//Variable qui va stocker le numéro du contact en création

		private int id=Integer.parseInt(lecture());	//Variable qui va stocker le numéro du contact en création
		
		/**
		 * Classe contact qui sera sérialisée et qui contient les variables suivantes
		 * - nom
		 * - prénom
		 * - adresse
		 * - localite
		 * - telephone
		 * - mail
		 * - urlImage
		 * 
		 * Chaque variable possède son getter et setter
		 * 
		 * @author Vivian
		 */
		
		public class Contact implements Serializable 
		{
			private String nom,prenom,adresse,localite,telephone,mail,urlImage;
			
			public Contact(String nom,String prenom,String adresse,String localite,String mail,String telephone,String urlImage)
			{
				setId();
				setNom(nom);
				setPrenom(prenom);
				setAdresse(adresse);
				setLocalite(localite);
				setTelephone(telephone);
				setMail(mail);
				setUrlImage(urlImage);
			}
			
			public void ecriture(int id)
			{
				
				
				File dossier = new File("src/ContactApp");
				dossier.mkdir();
				
				File fichier = new File(dossier,"id.txt");
			
				
				try {
					fichier.createNewFile();
					
					
					FileWriter ecriture = new FileWriter(fichier);
					BufferedWriter bfwrite = new BufferedWriter(ecriture);
					bfwrite.write(Integer.toString(id)); 
					bfwrite.close();
				
					
				} catch (IOException e) {

					e.printStackTrace();
				}
				
				
			}
			
			public int getId()
			{
				return id;
			}
			
			public void setId()
			{
				id++;
				this.ecriture(id);
			}

			public String getNom()
			{
				return nom;
			}

			public void setNom(String nom)
			{
				this.nom = nom;
			}
			
			public String getUrlImage()
			{
				return urlImage;
			}

			public void setUrlImage(String urlImage)
			{
				this.urlImage = urlImage;
			}

			public String getPrenom()
			{
				return prenom;
			}

			public void setPrenom(String prenom)
			{
				this.prenom = prenom;
			}

			public String getAdresse()
			{
				return adresse;
			}

			public void setAdresse(String adresse)
			{
				this.adresse = adresse;
			}
			
			public String getLocalite()
			{
				return localite;
			}

			public void setLocalite(String localite)
			{
				this.localite = localite;
			}

			public String getTelephone()
			{
				return telephone;
			}

			public void setTelephone(String telephone)
			{
				this.telephone = telephone;
			}

			public String getMail()
			{
				return mail;
			}

			public void setMail(String mail)
			{
				this.mail = mail;
			}
		}
		
		/**
		 * 
		 * ActionListener qui se déclenche quand on clique sur un contact
		 * On affiche la fiche du contact conserné
		 * 
		 * @author Vivian
		 *
		 */
		public class ClickContact implements ActionListener
		{

			Contact contact;
			
			/**
			 * Contructeur de la class ClickContact
			 * 
			 * @param contact : le contact clické
			 * @author Vivian
			 */
			public ClickContact(Contact contact)
			{
				this.contact = contact;
			}
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				System.out.println("contact "+contact.getNom()+" "+contact.getPrenom()+" cliqué");
				//j'ai accès au contact cliqué je dois donc afficher le panel modifcontact avec pour paramètre ce contact
				ModifContact modifcontact = new ModifContact(contact);
				triPanel.add(modifcontact,"modifcontact");
				cardLayout.show(triPanel, "modifcontact");
				menuh1panel.setVisible(false);
			}
			
		}
		
		/**
		 * 
		 * Classe FlatButton utilisée pour les boutons contact
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
			FlatButton bouton; 			//On récupère la variable du bouton survolé
			
			/**
			 * Constructeur de la classe MouseMovement
			 * 
			 * @param bouton : le bouton survolé
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
		 * Classe ChampLabel utilisée pour afficher le champ d'informations d'un contact
		 * 
		 * @author Vivian
		 *
		 */
		
		public class ChampLabel extends JLabel
		{
			
			Font globalFontH2 = new Font("2.TimesRoman ",Font.BOLD,20);
			
			/**
			 * Constructeur de la classe ChampLabel
			 * 
			 * @param nom : le nom du champ du contact
			 * @param type : le type de donnée voulue dans ce champ
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
		 * Classe ChampTextField qui permet d'écrire le champ du contact
		 * 
		 * @author Vivian
		 *
		 */
		public class ChampTextField extends JTextField
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
		 * Méthode qui remplit un tableau avec toutes les urls des images de la galerie
		 * 
		 * @param listImage : la liste des url des images
		 * @author Vivian
		 */
		
		public void fillImg(ArrayList<String> listImage)
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
		 * Méthode qui contrôle si un numéro de téléphone est valide
		 * 
		 * @param phone : le numéro de téléphone à vérifier
		 * @return : on retourne si oui ou non le numéro est vérifié
		 */
		public boolean checkPhone(String phone)
		{
			if(Pattern.matches("[0-9]{10}", phone)==true)
				return true;
			return false;
		}
		
		/**
		 * Méthode qui contrôle si un mail est valide
		 * 
		 * @param mail : le mail à vérifier
		 * @return : on retourne si oui ou non le mail est verifié
		 */
		
		public boolean checkMail(String mail)
		{
			if(Pattern.matches("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$", mail)==true)
				return true;
			return false;
		}
		
		/**
		 * 
		 * Classe qui est appelée lors de la création d'un nouveau contact
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
			
			private SaveButton enregistrement = new SaveButton("Sauver le contact !");
			
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
				
				//On définit la taille de l'image et on l'implémentes
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
			 * KeyListener qui est activé lorsque l'on touche ENTER
			 * 
			 * @author Vivian
			 *
			 */
			
			public class PressedEnter implements KeyListener
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
						System.out.println("touche ENTER");
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
			 * ActionListener qui est appelé lors d'un click sur l'image d'un contact
			 * 
			 * @author Vivian
			 *
			 */
			
			class ClickImage implements ActionListener
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
					// TODO Auto-generated method stub
						if (li.hasNext())
						{
								imageContact.setUrl((String)li.next()); 	//On caste en String
								System.out.println("image cliqué");
						}else 
						{
							for(int i=0;i<listImage.size();i++)
							{
								imageContact.setUrl((String)li.previous());	//on revient au début
								System.out.println("on revient au début");
							}
						}

				}
				
			}
			
			/**
			 * 
			 * ActionListener qui est actif lors d'un click sur le bouton enregistrement d'un contact
			 * 
			 * @author Vivian
			 *
			 */
			
			class ClickEnregistrement implements ActionListener
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
						System.out.println("un champ est vide");
						textNom.setText("ERREUR");
						
						return;
					}
					
					//Check du prenom
					if(prenomSaisi.equals("") || prenomSaisi.equals("ERREUR"))
					{
						System.out.println("un champ est vide");
						textPrenom.setText("ERREUR");
						
						return;
					}
					
					//Check de l'adresse
					if(adresseSaisi.equals("") || adresseSaisi.equals("ERREUR") || adresseSaisi.length()>18)
					{
						System.out.println("un champ est vide ou trop long");
						textAdresse.setText("ERREUR");
						
						return;
					}
					
					//Check de la localité
					if(localiteSaisi.equals("") || localiteSaisi.equals("ERREUR") || localiteSaisi.length()>18)
					{
						System.out.println("un champ est vide ou trop long");
						textLocalite.setText("ERREUR");
						
						return;
					}
					
					//Check du mail
					if(!checkMail(mailSaisi) || mailSaisi.equals("ERREUR") || mailSaisi.length()>18)
					{
						System.out.println("problème dans la saisie du mail ou trop long");
						textMail.setText("ERREUR");
						
						return;
					}
					
					//Check du phone
					if(!checkPhone(phoneSaisi) || phoneSaisi.equals("ERREUR"))
					{
						System.out.println("problème dans la saisie du téléphone");
						textPhone.setText("ERREUR");
						
						return;
					}
					
					String checklongeur = nomSaisi +" "+prenomSaisi;
					
					//Check longueur nom/prénom
					if(checklongeur.length() > 12)
					{
						System.out.println("problème de longueur de la châine");
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
					System.out.println(imageContact.getUrl());
					//On remet tout à zérp
					textNom.setText("");
					textPrenom.setText("");
					textAdresse.setText("");
					textLocalite.setText("");
					textMail.setText("");
					textPhone.setText("");
					imageContact.setUrl("images/photos/contact-1.png");
					//On sérialise
					this.serializeObject(contactEnCreation);
					//On actualise la liste des contact et on affiche cette dernière
					actualiseContact();
					cardLayout.show(triPanel,"scroll");
					menuh1panel.setVisible(true);
					System.out.println("Sérialisation effectuée");
					
				}
				
				/**
				 * 
				 * Méthode qui va sérialiser un contact
				 * 
				 * @param contactEnCreation : le contact qui va être sérialisé
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
					}
					catch (java.io.IOException e)
					{
						e.printStackTrace();
					}
				}
				
			}
		}
		
		/**
		 * Méthode qui va actualiser l'affichage de la liste de contact
		 * 
		 * @author Vivian
		 */
		
		public void actualiseContact() 
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

			
			System.out.println("nombreFichier "+nombreFichier);
			
			triPanel.add(scroll, "scroll");
			

		}
		
		/**
		 * Classe qui définit le look du bouton enregistrer
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
			public   CardLayout cardLayout;
			public  JPanel triPanel;
			private SaveButton enregistrement = new SaveButton("Modifier le contact !");
			
			//Champ du formulaire
			private ChampTextField textNom,textPrenom,textAdresse,textLocalite,textMail,textPhone;
			
			/**
			 * 
			 * Constructeur de la classe ModifContact
			 * 
			 * @param contact : le contact affiché et modifiable
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
				IconBase imageContact = new IconBase(urlImage,480,300);
				
				//On remplit le menuh1
				MenuH1PanelContact menuh1panel2 = new MenuH1PanelContact(contact.getPrenom(), getClass().getSimpleName(),contact);
				
				//On affiche titre H1 dans le panel UP
				this.add(menuh1panel2);
				
				//On définit la taille de l'image et on l'implémentes
				this.add(imageContact);
				imageContact.addActionListener(new ClickImage(li,imageContact));
				
				//On met les infos dans le gridpanel
				infosContact.setLayout(new GridLayout(7,2,10,10)); 		//(ligne,colonne,espace,espace)
				
				//On remplit les champs avec les infos
				ChampLabel nom = new ChampLabel("Nom :","text");
				ChampTextField textNom = new ChampTextField(contact.getNom());
				this.textNom = textNom;
				ChampLabel prenom = new ChampLabel("Prénom :","text");
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
				ChampLabel phone = new ChampLabel("Téléphone :","text");
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
			 * KeyListener qui est activé lorsque l'on touche ENTER
			 * 
			 * @author Vivian
			 *
			 */
			
			public class PressedEnter implements KeyListener
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
						System.out.println("touche ENTER");
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
			 * ActionListener qui se déclenche lors d'un click sur l'image du contact
			 * 
			 * @author Vivian
			 *
			 */
			
			class ClickImage implements ActionListener
			{

				ListIterator li;
				IconBase imageContact;
				
				/**
				 * Constructeur de la classe ClickImage
				 * 
				 * @param li : la liste des images à disposition du contact
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
						if (li.hasNext()) {
								imageContact.setUrl((String)li.next()); 	//j'essaie de caster en force
								System.out.println("image cliqué");
						}else {
							for(int i=0;i<listImage.size();i++) {
								imageContact.setUrl((String)li.previous());	//on revient au début
								System.out.println("on revient au début");
							}
						}

				}
				
			}
			
			/**
			 * ActionListener déclenché par le click du bouton enregistrement
			 * 
			 * @author Vivian
			 *
			 */
			
			class ClickEnregistrement implements ActionListener
			{
				Contact contact;
				IconBase imageContact;
				
				/**
				 * Constructeur de la classe CLickEnregistrement
				 * 
				 * @param contact : le contact qui est modifié
				 * @param imageContact : l'image du contact modifié
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
						System.out.println("un champ est vide");
						textNom.setText("ERREUR");
						
						return;
					}
					
					//Check du prenom
					if(prenomSaisi.equals("") || prenomSaisi.equals("ERREUR"))
					{
						System.out.println("un champ est vide");
						textPrenom.setText("ERREUR");
						
						return;
					}
					
					//Check de l'adresse
					if(adresseSaisi.equals("") || adresseSaisi.equals("ERREUR"))
					{
						System.out.println("un champ est vide");
						textAdresse.setText("ERREUR");
						
						return;
					}
					
					//Check de la localité
					if(localiteSaisi.equals("") || localiteSaisi.equals("ERREUR"))
					{
						System.out.println("un champ est vide");
						textLocalite.setText("ERREUR");
						
						return;
					}
					
					//Check du mail
					if(!checkMail(mailSaisi) || mailSaisi.equals("ERREUR"))
					{
						System.out.println("problème dans la saisie du mail");
						textMail.setText("ERREUR");
						
						return;
					}
					
					//Check du phone
					if(!checkPhone(phoneSaisi) || phoneSaisi.equals("ERREUR"))
					{
						System.out.println("problème dans la saisie du téléphone");
						textPhone.setText("ERREUR");
						
						return;
					}
					
					String checklongeur = nomSaisi +" "+prenomSaisi;
					
					//Check longueur nom/prénom
					if(checklongeur.length() > 12)
					{
						System.out.println("problème de longueur de la châine");
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
					System.out.println("suppression contact "+contact.getId());
					
					
					File filedeleted = new File("serialisation\\contact-"+contact.getId()+".ser");
					
					
					delete(filedeleted);
					
					
					this.serializeObject(contactEnCreation);
					
					

					
					//On actualise la liste des contact et on affiche cette dernière
					actualiseContact();
					getCardLayout().show(getTriPanel(), "scroll");
					menuh1panel.setVisible(true);
					System.out.println("Modification effectuée");
					
				}
				
				
				/**
				 * Méthode permettant de delete un fichier
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
				 * Méthode qui va sérialisé un contact
				 * 
				 * @param contactEnCreation : le contact qui va être sérialisé
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
		
		public class MenuH1PanelContact extends JPanel
		{

			//Définit un titre sur le panel avec une couleurs
			JLabel titrePanel;
			Font globalFont = new Font("2.TimesRoman ",Font.BOLD,50);
			
			//On instancie les icones pour les ajouters sur le panel
			IconBase create = new IconBase("images/icones/plus.png",40,40);
			IconBase previous = new IconBase("images/icones/left-arrow.png",40,40);
			IconBase vide = new IconBase("images/icones/left-arrow.png",40,40);
			IconBase delete = new IconBase("images/icones/delete-black.png",40,40);
			
			String titre, nomClass;

			/**
			 * Constructeur de la classe MenuH1PanelContact avec deux paramètres
			 * 
			 * @param titre : le titre du panel afficher
			 * @param nomClass : le nom de la classe utilisée
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

				//On met le plus à droite
				if(nomClass.equals("NewContact"))
					this.remove(create);
				else
					this.add(create, BorderLayout.EAST);

				//On met un listener sur le bouton
				create.addActionListener(new ClickCreate());
				previous.addActionListener(new ClickPrevious());

			}
			
			/**
			 * Constructeur de la classe MenuH1PanelContact avec trois paramètres
			 * 
			 * @param titre : le titre du panel afficher
			 * @param nomClass : le nom de la classe utilisée
			 * @param contact : le contact affiché
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
			 * ActionListener qui est déclenché par le click sur le bouton précédent
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
			 * ActionListener qui est déclenché par le click sur le bouton create
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
					System.out.println("create cliqué");
				}
			}
			
			/**
			 * ActionListener qui est déclenché par le click sur le bouton delete
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
				 * @param contact : le contact supprimé
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
					//On actualise la liste des contact et on affiche cette dernière
					actualiseContact();
					cardLayout.show(triPanel, "scroll");
					menuh1panel.setVisible(true);
					System.out.println("delete cliqué");
				}
			}
		}			
}