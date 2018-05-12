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
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import Images.IconBase;


/**
 * @author Loris_Clivaza
 *
 */
public class GalleryPanel extends JPanel 
{

	//Création du tableau d'objet pricture
	private ArrayList<Picture> listImg = new ArrayList<Picture>();

	//instanciation du panel du haut gallery
	PanelGallery menuh1panel = new PanelGallery("Gallery", "Gallery");

	//Création d'un panel central pour mettre les photos
	JPanel center = new JPanel();
	//Gestion des panels dans la gallery
	public   CardLayout cardLayout = new CardLayout();
	public  JPanel triPanel = new JPanel(cardLayout);


	public GalleryPanel()
	{
		
		
		//Choix du layout et de la dimension du panel
		this.setPreferredSize(new Dimension(480, 40));
		this.setLayout(new BorderLayout());
		this.add(menuh1panel, BorderLayout.NORTH);
		
		
		center.setLayout(new FlowLayout(12,12,12));

		

		//Ajout du panel center à galleryPanel
		this.add(triPanel, BorderLayout.CENTER);

		triPanel.add(center, "center");

		//Méthode qui va gérer les miniphotos dans la gallery
		actualisePhoto();

	

	}


	// Création des "boutons photos", miniatures
	public class minipicture extends JButton {

		private Picture pic;

		//Constructeur avec le lien 
		public minipicture(String path) {

			super();
			this.setIcon(new ImageIcon(path));
			this.setOpaque(false);
			this.setBorderPainted(false);
			this.setContentAreaFilled(false);
			this.setFocusPainted(false);

			try {
				this.pic = new Picture(path, ImageIO.read(new File(path)));
			} catch (IOException e) {

				e.printStackTrace();
			}

			//Choix de la dimension de l'image
			BufferedImage img = pic.getPicture();
			int nW = img.getWidth() / (img.getHeight() / 100);
			this.setPreferredSize(new Dimension(nW, 100));

			
			//Ajout de la minipicture dans le panel center
			center.add(this);
			listImg.add(pic);

		}

		@Override
		protected void paintComponent(Graphics g) {

			super.paintComponent(g);
			g.drawImage(pic.getPicture(), 0, 0, getWidth(), getHeight(), this);
		}

	}

	//Méthode qui va actualiser à chaque fois les images enregistrés
	public void actualisePhoto() {

		listImg.clear();
		File folder = new File("imagesgallery");
		if (!folder.exists()) {
			folder.mkdirs();
		}

		File[] photos = folder.listFiles();
		for (File photo : photos) {
			new minipicture(photo.getAbsolutePath());
		}

	}

	//Panel du haut dans la gallery avec le bouton d'ajout
	public class PanelGallery extends JPanel{


		JLabel titrePanel;
		Font globalFont = new Font("2.TimesRoman ",Font.BOLD,50);
		IconBase create = new IconBase("images/icones/plus.png",40,40);
		IconBase vide = new IconBase("",40,40);

		public String nomPhoto;

		//Constructeur
		public PanelGallery(String titre, String nomClass)
		{

			titrePanel = new JLabel(titre);
			titrePanel.setFont(globalFont);

			this.setPreferredSize(new Dimension(480, 78));
			this.setBackground(Color.decode("#DFDFDF"));

			this.setLayout(new FlowLayout(FlowLayout.CENTER,10,8)); 	
			if(nomClass.equals("ContactPanel")) {

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
		class ClickCreate implements ActionListener{

			String nomClass;

			public ClickCreate(String nomClass) {
				this.nomClass = nomClass;
			}

			@Override
			public void actionPerformed(ActionEvent e) {

				JFileChooser choisir = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("picture", "jpg", "png", "gif");
				choisir.setAcceptAllFileFilterUsed(false);
				choisir.setFileFilter(filter);

				int returnVal = choisir.showOpenDialog(PanelGallery.this);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = choisir.getSelectedFile();
					Picture.copy(file);
					minipicture newpic = new minipicture("imagesGallery/" + file.getName());
					revalidate();
					repaint();
				}
			}

		}



		

	}

}
