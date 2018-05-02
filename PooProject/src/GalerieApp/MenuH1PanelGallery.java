/*
 * Author : Vivian Bridy
 * Date creation : 30 avr. 2018
 */
package GalerieApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import Images.IconBase;

public class MenuH1PanelGallery extends JPanel{

	JLabel titrePanel;
	Font globalFont = new Font("2.TimesRoman ",Font.BOLD,50);
	IconBase create = new IconBase("images/icones/plus.png",40,40);
	IconBase previous = new IconBase("images/icones/left-arrow.png",40,40);
	IconBase vide = new IconBase("",40,40);

	private String nomPhoto;
	private String nomPhotoFinal;



	public MenuH1PanelGallery(String titre, String nomClass)
	// TODO Auto-generated constructor stube
	{
		titrePanel = new JLabel(titre);
		titrePanel.setFont(globalFont);

		this.setPreferredSize(new Dimension(480, 78));
		this.setBackground(Color.decode("#DFDFDF"));

		this.setLayout(new FlowLayout(FlowLayout.CENTER,10,8)); 	//61 est la valeur max
		if(nomClass.equals("ContactPanel")) {
			//On met le plus à gauche
			this.add(vide, BorderLayout.WEST);
		}else {
			this.add(previous, BorderLayout.WEST);
		}
		//On met le titre au centre
		this.add(titrePanel, BorderLayout.CENTER);

		//On met le plus à droite
		this.add(create, BorderLayout.EAST);

		//On met un listener sur le bouton
		create.addActionListener(new ClickCreate(nomClass));
		previous.addActionListener(new ClickPrevious());

	}

	//quand on clique sur le bouton previous

	class ClickPrevious implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {

			
		}

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
			FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG Images", "jpg", "png");
			choisir.setFileFilter(filter);
			choisir.showOpenDialog(MenuH1PanelGallery.this);
			File valeur = choisir.getSelectedFile();
			nomPhoto = choisir.getName(valeur);
			
			System.out.println(nomPhoto);
			try {
				serializeObject(valeur);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		private  void serializeObject(File valeur) throws IOException

		{

			try {
				
				File dossier = new File("serialisationPhoto"); 
				dossier.mkdir();
				
				FileOutputStream fichier = new FileOutputStream("serialisationPhoto/photo.ser");
				
				BufferedOutputStream bfichier = new  BufferedOutputStream(fichier);
				
				ObjectOutputStream ecrireobjet = new ObjectOutputStream(bfichier);
				
				ecrireobjet.writeObject(valeur);
			
				ecrireobjet.close();
				
				
			} catch (FileNotFoundException e) {

					e.printStackTrace();
			}
			
			System.out.println("J'ai été sérializé !!");
		}
		
	}
}
