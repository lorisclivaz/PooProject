/*
 * PanelGallery
 * Author: Clivaz Loris
 * Date creation: 30 avr. 2018
 * 
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
import java.io.File;
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




/**
 * explication
 * 
 * @author Loris_Clivaz
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

	/**
	 * Constructeur de la classe GalleryPanel
	 * 
	 * @author Loris_Clivaz
	 */
	public GalleryPanel()
	{

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


	


	// Création des "boutons photos", miniatures
	private class minipicture extends JButton {

		private Picture pic;

		/**
		 * EXPLICTATION
		 * 
		 * @param path : le chemin de la mini
		 * @author Loris_Clivaz
		 */
		public minipicture(String path) 
		{

			super();
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

			this.addActionListener(new ClickPhoto());
			
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

	//Action sur la clique de l'image dans la gallery
	class ClickPhoto implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{

			panelgallery.setVisible(false);
			cardLayout.show(getTriPanel2(), "photo");

			minipicture minsource = (minipicture) e.getSource();
			System.out.println(e.getSource());
			PhotoPanel photoPanel = new PhotoPanel(GalleryPanel.this, minsource.pic);

			//Création d'un panel pour la photo en grand
			photo.setLayout(new BorderLayout());

			//on fait un removeAll pour remettre à zéro l'objet instancié
			photo.removeAll();
			photo.add(photoPanel, BorderLayout.CENTER);


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

		class ClickDelete implements ActionListener
		{


			@Override
			public void actionPerformed(ActionEvent e) 
			{

				image.delete();
				removeChild(GalleryPanel.this);

			}
		}

		//On remove la minipicture dans la gallery
		private void removeChild(JPanel PaneltoRemove) {

			panelgallery.setVisible(true);
			cardLayout.show(triPanel2, "scroll");


			actualisePhoto();
			this.revalidate();
			this.repaint();

		}

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




	//Méthode qui va actualiser à chaque fois les images enregistrés
	private void actualisePhoto() {

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
			
			new minipicture(photo.getAbsolutePath());
		}

		//photos.length;
	}

	//Panel du haut dans la gallery avec le bouton d'ajout
	private class PanelGallery extends JPanel{


		JLabel titrePanel;
		Font globalFont = new Font("2.TimesRoman ",Font.BOLD,50);
		IconBase create = new IconBase("images/icones/plus.png",40,40);
		IconBase vide = new IconBase("",40,40);

		private String nomPhoto;

		//Constructeur
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
			create.addActionListener(new ClickCreate(nomClass));

		}


		//quand on clique sur le bouton create
		class ClickCreate implements ActionListener
		{

			String nomClass;

			public ClickCreate(String nomClass) 
			{
				this.nomClass = nomClass;
			}

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
					Picture.copy(file);
					minipicture newpic = new minipicture("imagesGallery/" + file.getName());
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
