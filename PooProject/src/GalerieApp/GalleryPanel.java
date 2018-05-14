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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import Images.IconBase;





/**
 * @author Loris_Clivaz
 *
 */
public class GalleryPanel extends JPanel 
{

	//Cr�ation du tableau d'objet pricture
	private ArrayList<Picture> listImg = new ArrayList<Picture>();

	//instanciation du panel du haut gallery
	PanelGallery menuh1panel = new PanelGallery("Gallery", "Gallery");

	//Cr�ation des panels  pour mettre les photos
	JPanel center = new JPanel();
	JPanel photo = new JPanel();

	//Gestion des panels dans la gallery
	public   CardLayout cardLayout = new CardLayout();
	public  JPanel triPanel2 = new JPanel(cardLayout);

	JScrollPane scroll = new  JScrollPane(center);


	public GalleryPanel()
	{


		//Choix du layout et de la dimension du panel

		this.setPreferredSize(new Dimension(480, 40));
		this.setLayout(new BorderLayout());
		this.add(menuh1panel, BorderLayout.NORTH);


		center.setLayout(new GridLayout(2,4,20,20));


		scroll.getHorizontalScrollBar();
		scroll.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));


		//Ajout du panel center � galleryPanel
		this.add(triPanel2, BorderLayout.CENTER);

		triPanel2.add(scroll, "scroll");
		triPanel2.add(photo, "photo");

		//M�thode qui va g�rer les miniphotos dans la gallery
		
		actualisePhoto();




	}




	public JPanel getTriPanel2() {
		return triPanel2;
	}
	public void setTriPanel2(JPanel triPanel) {
		this.triPanel2 = triPanel;
	}




	// Cr�ation des "boutons photos", miniatures
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

			this.addActionListener(new ClickPhoto());
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

	//Action sur la clique de l'image dans la gallery
	class ClickPhoto implements ActionListener
	{



		@Override
		public void actionPerformed(ActionEvent e)
		{

			menuh1panel.setVisible(false);
			cardLayout.show(getTriPanel2(), "photo");

			minipicture minsource = (minipicture) e.getSource();
			System.out.println(minsource.pic.getPath());
			PhotoPanel photoPanel = new PhotoPanel(GalleryPanel.this, minsource.pic);
			
			//Cr�ation d'un panel pour la photo en grand
			photo.setLayout(new BorderLayout());
			
			//on fait un removeAll pour remettre � z�ro l'objet instanci�
			photo.removeAll();
			photo.add(photoPanel, BorderLayout.CENTER);

			System.out.println("j'ai cliqu�!!");

		}


	}
	
	
	public class PhotoPanel extends JPanel {

		
		private GalleryPanel photo;
		public  Picture image;
		private MouseAdapter ma;
		
		
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
			
			

		}
		
		class ClickDelete implements ActionListener
		{

			
			@Override
			public void actionPerformed(ActionEvent e) 
			{

				System.out.println("delete !!");

				image.delete();
				removeChild(GalleryPanel.this);
				
				
				
			}
			
			
		}
		
		
		public void removeChild(JPanel PaneltoRemove) {
			scroll.remove(PaneltoRemove);
			
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

				cardLayout.show(getTriPanel2(), "scroll");
				
				//il faut reprendre le panel de la gallery de base

				System.out.println("J'ai cliqu�");
			}
			
		}
		
		
		
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			BufferedImage pic = image.getPicture();
			int nH = (int) (pic.getHeight() / ((double) pic.getWidth() / getWidth()));
			if (nH > getHeight()) {
				int nW = (int) (pic.getWidth() / ((double) pic.getHeight() / getHeight()));
				int x = (getWidth() - nW) / 2;
				g.drawImage(pic, x, 0, nW, getHeight(), this);
			} else {

				int y = (getHeight() - nH) / 2;
				g.drawImage(pic, 0, y, getWidth(), nH, this);
			}

		}
	}
	
	


	//M�thode qui va actualiser � chaque fois les images enregistr�s
	public void actualisePhoto() {

		center.removeAll();
		
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

				//On met le plus � gauche
				this.add(vide, BorderLayout.WEST);
			}else 

				//On met le titre au centre
				this.add(titrePanel, BorderLayout.CENTER);

			//On met le plus � droite
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
