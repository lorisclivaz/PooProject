/*
 * Author : Vivian Bridy & Loris Clivaz
 * Date creation : 14 mai 2018
 */

package GalerieApp;

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
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import Images.IconBase;
import Images.ImageFond;
import MainFrame.Frame;
import MainFrame.Frame.SettingsPanel;

/**
 * Classe qui va gérer toute l'application Galerie
 * 
 * @author Loris
 *
 */

public class GalleryPanel extends JPanel 
{

	//Création du tableau d'objet pricture
	private ArrayList<Picture> listImg = new ArrayList<Picture>();

	//instanciation du panel du haut gallery
	PanelGallery panelgallery = new PanelGallery("Gallery", "Gallery");

	//Création des panels  pour mettre les photos
	JPanel center = new JPanel();
	JPanel photo = new JPanel();

	//Gestion des panels dans la gallery
	private   CardLayout cardLayout = new CardLayout();
	private  JPanel triPanel2 = new JPanel(cardLayout);

	//pour le scroll
	JScrollPane scroll = new  JScrollPane(center);

	ImageFond fond;
	SettingsPanel bool;
	Frame frame;

	/**
	 * Constructeur de la classe GalleryPanel
	 * 
	 * - Définit la taille du panel ainsi que son layout
	 * -Ajoute le panel avec le nom de l'application
	 * -Actualise la galerie en actualisant l'application
	 * 
	 * @param fond l'image de fond de la galerie
	 * @param bool boolean du panel réglage
	 * @param frame variables de frame
	 * @author Loris_Clivaz
	 */

	public GalleryPanel(ImageFond fond, SettingsPanel bool, Frame frame)
	{

		this.fond = fond;
		this.bool = bool;
		this.frame = frame;
		
		
	
		
		//Choix du layout et de la dimension du panel
		this.setPreferredSize(new Dimension(480, 40));
		this.setLayout(new BorderLayout());
		this.add(panelgallery, BorderLayout.NORTH);


		center.setLayout(new GridLayout(2,2,10,10));



		//Ajout du panel center à galleryPanel
		this.add(triPanel2, BorderLayout.CENTER);

		triPanel2.add(scroll, "scroll");
		triPanel2.add(photo, "photo");

		//Méthode qui va gérer les miniphotos dans la gallery
		actualisePhoto();

	}




	/**
	 *  Création des "boutons photos", miniatures
	 * 
	 * @author Loris_Clivaz
	 *
	 */

	private class MiniPhoto extends JButton {

		private Picture pic;

		private String path;


		/**
		 * Constructeur de la classe miniPicture
		 * 
		 * @param path : Récupération du chemin de l'image
		 * @author Loris
		 */

		public MiniPhoto(String path) 
		{

			super();
			this.path = path;
			this.setIcon(new ImageIcon(path));
			this.setOpaque(false);
			this.setBorderPainted(false);
			this.setContentAreaFilled(false);
			this.setFocusPainted(false);

			try {
				this.pic = new Picture(path, ImageIO.read(new File(path)));
			} catch (IOException e) 
			{

				e.printStackTrace();
			}

			//Choix de la dimension de l'image
			BufferedImage img = pic.getPicture();
			int nW = img.getWidth() / (img.getHeight() / 100);
			this.setPreferredSize(new Dimension(nW, 100));

			//Affichage du panel de l'image au moment du clique
			this.addActionListener(new ClickPhoto(path));

			//Ajout de la minipicture dans le panel center
			center.add(this);


			listImg.add(pic);

		}


		


		@Override
		public void paintComponent(Graphics g) 
		{

			super.paintComponent(g);
			g.drawImage(pic.getPicture(), 0, 0, getWidth(), getHeight(), this);
		}

	}



	/**
	 * Classe qui va gérer le clique sur le bouton image de la galerie ainsi que le chagement du fond d'ecran selon le boolean
	 * 
	 * @author Loris_Clivaz
	 *
	 */
	class ClickPhoto implements ActionListener
	{
		
		String path;
		public ClickPhoto(String path) 
		{

			this.path = path;
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(bool.isReglage() == true)
			{
				bool.setReglage(false);
				fond.setUrl(path);
				
				frame.getCardLayout().show(frame.getTriPanel(), "mainpanel");
				
			}

			else
			{
			panelgallery.setVisible(false);
			cardLayout.show(getTriPanel2(), "photo");

			//On va chercher la source du clique de l'image
			MiniPhoto minsource = (MiniPhoto) e.getSource();
			PhotoPanel photoPanel = new PhotoPanel(GalleryPanel.this, minsource.pic);

			//Création d'un panel pour la photo en grand
			photo.setLayout(new BorderLayout());

			//on fait un removeAll pour remettre à zéro l'objet instancié
			photo.removeAll();
			photo.add(photoPanel, BorderLayout.CENTER);


			}
		}


	}


	/**
	 * Classe qui va gérer le panel au moment du clique sur le bouton image
	 * 
	 * @author Loris_Clivaz
	 *
	 */

	public  class PhotoPanel extends JPanel {

		private ImageFond imageFond ;
		private GalleryPanel photo;
		private  Picture image;
		private MouseAdapter ma;
		private String changeImage;


		JPanel up = new JPanel();
		IconBase previous = new IconBase("images/icones/left-arrow.png",40,40);
		IconBase delete = new IconBase("images/icones/delete.png",40,40);


		/**
		 * Constructeur de la classe PhotoPanel
		 * 
		 * @param photo : Récupération de la photo de la galerie
		 * @param image : Récupération de la miniPicture
		 * @author Loris
		 */

		public PhotoPanel(GalleryPanel photo, Picture image) {

			this.photo = photo;
			this.image = image;

			BorderLayout fl = new BorderLayout();

			this.setLayout(fl);
			this.setBackground(Color.BLACK);

			//Ajout du panel up en haut
			up.setLayout(new BorderLayout());
			up.setBackground(Color.BLACK);
			this.add(up, BorderLayout.NORTH);

			//Ajout des deux images pour revenir sur la galerie ou supprimer l'image
			up.add(previous, BorderLayout.WEST);
			up.add(delete, BorderLayout.EAST);

			//On ajout les actions sur les images au moment du clique
			previous.addActionListener(new ClickPrevious());
			delete.addActionListener(new ClickDelete());

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

		/**
		 * Méthode qui permet le swipe d'image par rapport au tableau d'objet
		 * 
		 * @param i : La position dans le tableau
		 */
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


		/**
		 * 
		 * Classe qui va permettre de delete une image 
		 * 
		 * @author loris
		 *
		 */

		class ClickDelete implements ActionListener
		{


			@Override
			public void actionPerformed(ActionEvent e) 
			{

				image.delete();
				removeChild(GalleryPanel.this);

			}
		}


		/**
		 * Méthode qui va réactualiser la galerie en ayant supprmié l'image
		 * 
		 * @param PaneltoRemove le panel utilisé
		 * @author Vivian et Loris
		 */	

		private void removeChild(JPanel PaneltoRemove) {

			panelgallery.setVisible(true);
			cardLayout.show(triPanel2, "scroll");


			actualisePhoto();
			this.revalidate();
			this.repaint();

		}


		/**
		 * 
		 * Classe qui va permettre de revenir sur le panel de la galerie
		 * 
		 * @author loris
		 *
		 */

		class ClickPrevious implements ActionListener
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				panelgallery.setVisible(true);

				cardLayout.show(getTriPanel2(), "scroll");

			}

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


	/**
	 * Méthode qui va actualiser à chaque fois les images enregistrés
	 * 
	 */	

	private void actualisePhoto() 
	{

		//Après problème de suppression, remove all actualise à chaque fois la gallery
		center.removeAll();
		listImg.clear();

		//On va chercher dans le fichier
		File folder = new File("imagesgallery");
		if (!folder.exists()) 
		{
			folder.mkdirs();
		}

		File[] photos = folder.listFiles();
		for (File photo : photos) 
		{

			new MiniPhoto(photo.getAbsolutePath());
		}

		
	}
	
	
	/**
	 * 
	 * Classe PanelGallery qui va gérer le panel du haut de la galerie
	 * 
	 * @author loris
	 *
	 */

	private class PanelGallery extends JPanel{


		JLabel titrePanel;
		Font globalFont = new Font("2.TimesRoman ",Font.BOLD,50);
		IconBase create = new IconBase("images/icones/plus.png",40,40);
		IconBase vide = new IconBase("",40,40);

		private String nomPhoto;

		/**
		 * Constructeur de la classe PanelGallery
		 * 
		 * @param titre : titre en gras
		 * @param nomClass : nom de la classe
		 * 
		 * @author Loris
		 */
		
		public PanelGallery(String titre, String nomClass)
		{

			titrePanel = new JLabel(titre);
			titrePanel.setFont(globalFont);

			this.setPreferredSize(new Dimension(480, 78));
			this.setBackground(Color.decode("#DFDFDF"));
			this.setLayout(new FlowLayout(FlowLayout.CENTER,10,8)); 

			if(nomClass.equals("ContactPanel")) 
			{

				//On met le plus à gauche
				this.add(vide, BorderLayout.WEST);
			}else 

				//On met le titre au centre
				this.add(titrePanel, BorderLayout.CENTER);

			//On met le plus à droite
			this.add(create, BorderLayout.EAST);



			//On met un listener sur le bouton
			create.addActionListener(new ClickCreate());

		}
		
		private String lecture()
		{
			String resultat="";
			File dossier = new File("src/ContactApp");
			dossier.mkdir();
			
			File fichier = new File(dossier,"id.txt");
			
			try {
			
				FileReader read = new FileReader(fichier);
				BufferedReader bfread = new BufferedReader(read);
				resultat = bfread.readLine();
				read.close();
				bfread.close();
				
			} catch (IOException e) {

				e.printStackTrace();
			}
			
			
			return resultat;
			
		}
		

		/**
		 * 
		 * Classe ClickCreate qui va gérer l'ajout de l'image
		 * 
		 * @author loris
		 *
		 */
		
		class ClickCreate implements ActionListener
		{



			@Override
			public void actionPerformed(ActionEvent e) 
			{

				JFileChooser choisir = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("picture", "jpg", "png", "gif");
				choisir.setAcceptAllFileFilterUsed(false);
				choisir.setFileFilter(filter);

				int returnVal = choisir.showOpenDialog(PanelGallery.this);

				if (returnVal == JFileChooser.APPROVE_OPTION)
				{
					File file = choisir.getSelectedFile();
					String temp = file.getName();
					
//					String tabTemp[] = temp.split("\\.");
//				
//					
//					File destination = new File("imagesgallery/"+tabTemp[0]+"-"+lecture()+"."+tabTemp[1]);
//					file.renameTo(destination);
					String newName = Picture.copy(file);
					
					MiniPhoto newpic = new MiniPhoto("imagesgallery/"+newName);
					revalidate();
					repaint();
				}
			}
		}
	}

	
	public JPanel getTriPanel2() {
		return triPanel2;
	}
	public void setTriPanel2(JPanel triPanel) {
		this.triPanel2 = triPanel;
	}
	public ArrayList<Picture> getListImg() {
		return listImg;
	}

	public int getVoisin(Picture actu){
		return listImg.indexOf(actu);
	}


}
