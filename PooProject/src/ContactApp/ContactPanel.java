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
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import GalerieApp.GalleryPanel;
import GalerieApp.Picture;
import GalerieApp.GalleryPanel.PhotoPanel;
import Images.IconBase;
import Images.ImageContact;
import Images.ImageFond;
import MainFrame.Frame;


public class ContactPanel extends JPanel{
		//Création frame
//		private Frame frame;
	
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
		
		public CardLayout getCardLayout() {
			return cardLayout;
		}


		public JPanel getTriPanel() {
			return triPanel;
		}

		//Création panel poue new contact
		NewContact newcontact = new NewContact();

		//On crée la scrollBar
		JScrollPane scroll = new  JScrollPane(allContact);
		
		
		public ContactPanel() {
			// TODO Auto-generated constructor stub
//			this.frame = frame;
			//Choix du layout et de la dimension du panel

			this.setPreferredSize(new Dimension(480, 40));
			this.setLayout(new BorderLayout());
			
			this.add(menuh1panel, BorderLayout.NORTH);
						
			allContact.setLayout(new GridLayout(10,1));

//			center.setLayout(new GridLayout(6,2,0,0));
			
			scroll.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));


			//Ajout du panel center à galleryPanelea
			this.add(triPanel, BorderLayout.CENTER);
			
			actualise();
			
			triPanel.add(newcontact, "newcontact");
			
			cardLayout.show(triPanel, "allcontact");

		}
		
		public void actualise() {
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
		
		private int id=0;
		
		public class Contact implements Serializable {
			private String nom,prenom,adresse,localite,telephone,mail,urlImage;
			
			public Contact(String nom,String prenom,String adresse,String localite,String mail,String telephone,String urlImage) {
				// TODO Auto-generated constructor stub
				setId();
				setNom(nom);
				setPrenom(prenom);
				setAdresse(adresse);
				setLocalite(localite);
				setTelephone(telephone);
				setMail(mail);
				setUrlImage(urlImage);
			}
			
			public int getId() {
				return id;
			}
			
			public void setId() {
				id++;
			}

			public String getNom() {
				return nom;
			}

			public void setNom(String nom) {
				this.nom = nom;
			}
			
			public String getUrlImage() {
				return urlImage;
			}

			public void setUrlImage(String urlImage) {
				this.urlImage = urlImage;
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
				System.out.println("contact "+contact.getNom()+" "+contact.getPrenom()+" cliqué");
				//j'ai accès au contact cliqué je dois donc afficher le panel modifcontact avec pour paramètre ce contact
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
		
		public void fillImg(ArrayList<String> listImage) {
			//On calcule le nombre d'image dans le dossier imagesgallery
			File dossier = new File("imagesgallery");
			File[] f = dossier.listFiles();
			String path;
			Contact current;
						
			for (int i = 0 ; i < f.length ; i++) {
			  if (f[i].isFile()) {
				path = f[i].getAbsolutePath();
				listImage.add(path);
			  }
			}
		}
		
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
			
			public NewContact() {
				// TODO Auto-generated constructor stub
				
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
				infosContact.add(textPhone);
				infosContact.add(vide2);
				enregistrement.addActionListener(new ClickEnregistrement());
				infosContact.add(enregistrement);
				
				//On affiche les infos
				this.add(infosContact);
				

				
			}
			
			class ClickImage implements ActionListener{

				ListIterator li;
				
				public ClickImage(ListIterator li) {
					// TODO Auto-generated constructor stub
					this.li = li;
				}
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
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
			
			class ClickEnregistrement implements ActionListener{

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					Contact contactEnCreation = new Contact(textNom.getText(),
															textPrenom.getText(),
															textAdresse.getText(),
															textLocalite.getText(),
															textMail.getText(),
															textPhone.getText(),
															imageContact.getUrl());
					System.out.println(imageContact.getUrl());
					//On remet tout à zérp
					textNom.setText("");
					textPrenom.setText("");
					textAdresse.setText("");
					textLocalite.setText("");
					textMail.setText("");
					textPhone.setText("");
					//On sérualise
					this.serializeObject(contactEnCreation);
					//On actualise la liste des contact et on affiche cette dernière
					actualiseContact();
					cardLayout.show(triPanel,"scroll");
					menuh1panel.setVisible(true);
					System.out.println("Sérialisation effectuée");
					
				}
				
				public void serializeObject(Contact contactEnCreation) {
					try {
						FileOutputStream fichier = new FileOutputStream("serialisation\\contact-"+contactEnCreation.getId()+".ser");
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
		
		public void actualiseContact() {
			allContact.removeAll();
			allContact.revalidate();
			File dossierActu = new File("serialisation");
			File[] fActu = dossierActu.listFiles();
			String path;
			Contact currentActu;
			for (int i = 0 ; i < fActu.length ; i++) {
			  if (fActu[i].isFile()) {
				path = fActu[i].getAbsolutePath();
				System.out.println(path);
				currentActu = deSerializeObject(path);
				FlatButton bouton = new FlatButton();
				bouton.addActionListener(new ClickContact(currentActu));
				bouton.setText(currentActu.getNom()+" "+currentActu.getPrenom());
				bouton.setPreferredSize(new Dimension(400,120));
				allContact.add(bouton);
			  }
			}
			
		}
		
		public class SaveButton extends JButton{
			public SaveButton(String text) {
				// TODO Auto-generated constructor stub
				super(text);
				setBackground(Color.LIGHT_GRAY);
			}
		}

		public class ModifContact extends JPanel{
			private ArrayList<String> listImage = new ArrayList<String>();
			private JPanel infosContact = new JPanel();
			public   CardLayout cardLayout;
			public  JPanel triPanel;
			private SaveButton enregistrement = new SaveButton("Modifier le contact !");
			
			//Champ du formulaire
			private ChampTextField textNom,textPrenom,textAdresse,textLocalite,textMail,textPhone;
			
			public ModifContact(Contact contact) {
				// TODO Auto-generated constructor stub
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
			
			class ClickImage implements ActionListener{

				ListIterator li;
				IconBase imageContact;
				
				public ClickImage(ListIterator li,IconBase imageContact) {
					// TODO Auto-generated constructor stub
					this.li = li;
					this.imageContact = imageContact;
				}
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
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
			
			class ClickEnregistrement implements ActionListener{
				
				Contact contact;
				IconBase imageContact;
				public ClickEnregistrement(Contact contact,IconBase imageContact) {
					// TODO Auto-generated constructor stub
					this.contact = contact;
					this.imageContact = imageContact;
				}
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					
					allContact.removeAll();
					allContact.revalidate();
					Contact contactEnCreation = new Contact(textNom.getText(),
															textPrenom.getText(),
															textAdresse.getText(),
															textLocalite.getText(),
															textMail.getText(),
															textPhone.getText(),
															imageContact.getUrl());
					//On supprime l'ancien contact
					File filedeleted = new File("serialisation\\contact-"+contact.getId()+".ser");
					filedeleted.delete();
					this.serializeObject(contactEnCreation);
					
					//On actualise la liste des contact et on affiche cette dernière
					actualiseContact();
					getCardLayout().show(getTriPanel(), "scroll");
					menuh1panel.setVisible(true);
					System.out.println("Modification effectuée");
					
				}
				
				public void serializeObject(Contact contactEnCreation) {
					try {
						FileOutputStream fichier = new FileOutputStream("serialisation\\contact-"+contactEnCreation.getId()+".ser");
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
		
		public class MenuH1PanelContact extends JPanel{

			//Définit un titre sur le panel avec une couleurs
			JLabel titrePanel;
			Font globalFont = new Font("2.TimesRoman ",Font.BOLD,50);
			
			//On instancie les icones pour les ajouters sur le panel
			IconBase create = new IconBase("images/icones/plus.png",40,40);
			IconBase previous = new IconBase("images/icones/left-arrow.png",40,40);
			IconBase vide = new IconBase("images/icones/left-arrow.png",40,40);
			IconBase delete = new IconBase("images/icones/delete-black.png",40,40);
			
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

				//On met le plus à droite
				if(nomClass.equals("NewContact"))
					this.remove(create);
				else
					this.add(create, BorderLayout.EAST);

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
				
				//Ajout de le l'icone delete
				if(nomClass.equals("ModifContact"))
					this.add(delete, BorderLayout.EAST);

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
					System.out.println("create cliqué");
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

		public  class PhotoPanel extends JPanel {

			private ImageFond imageFond ;

			private GalleryPanel photo;
			private  Picture image;
			private MouseAdapter ma;
			private String changeImage;

			


			JPanel up = new JPanel();

			IconBase previous = new IconBase("images/icones/left-arrow.png",40,40);
		
			IconBase delete = new IconBase("images/icones/delete.png",40,40);

			
			public PhotoPanel(GalleryPanel photo, Picture image) {

				this.photo = photo;
				this.image = image;

				BorderLayout fl = new BorderLayout();
				this.setLayout(fl);
				this.setBackground(Color.BLACK);

				up.setLayout(new BorderLayout());
				up.setBackground(Color.BLACK);
				this.add(up, BorderLayout.NORTH);

				up.add(previous, BorderLayout.WEST);
				up.add(delete, BorderLayout.EAST);

				//Swiper de photo grace au clique
				ma = new MouseAdapter() {
					private Point origin;

					@Override
					public void mousePressed(MouseEvent e) {
						origin = new Point(e.getPoint());
						
					}

					@Override
					public void mouseReleased(MouseEvent e) {
					}

					@Override
					public void mouseDragged(MouseEvent e) {
						if (origin != null) {
							int deltaX = origin.x - e.getX();
							if(deltaX>=200){
								changeImg(1);
								origin=null;
							}
							else if(deltaX<=-200){
								changeImg(-1);
								origin=null;
							}
						}
					}

					
				};

				
				this.addMouseListener(ma);
				this.addMouseMotionListener(ma);

			}
			private void changeImg(int i) {

				int idImg = photo.getVoisin(image)+i;
				ArrayList<Picture> list = photo.getListImg();
				if(idImg==-1){
					idImg=list.size()-1;
				}
				else if(idImg==list.size()){
					idImg=0;
				}
				image = list.get(idImg);
				PhotoPanel.this.revalidate();
				PhotoPanel.this.repaint();
				
			}
			
			
		

			public String getChangeImage() {
			return changeImage;
		}


			public void paintComponent(Graphics g) 
			{
				super.paintComponent(g);
				
				BufferedImage pic = image.getPicture();
				
				int nH = (int) (pic.getHeight() / ((double) pic.getWidth() / getWidth()));
				
				if (nH > getHeight()) 
				{
					int nW = (int) (pic.getWidth() / ((double) pic.getHeight() / getHeight()));
					int x = (getWidth() - nW) / 2;
					g.drawImage(pic, x, 0, nW, getHeight(), this);
				} 
				else 
				{

					int y = (getHeight() - nH) / 2;
					g.drawImage(pic, 0, y, getWidth(), nH, this);
				}

			}
			
			
		}
			
}