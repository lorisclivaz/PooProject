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

import javax.swing.JLabel;
import javax.swing.JPanel;

import Images.IconBase;

public class MenuH1PanelGallery extends JPanel{

	JLabel titrePanel;
	Font globalFont = new Font("2.TimesRoman ",Font.BOLD,50);
	IconBase create = new IconBase("images/icones/plus.png",40,40);
	IconBase previous = new IconBase("images/icones/left-arrow.png",40,40);
	IconBase vide = new IconBase("",40,40);




	public MenuH1PanelGallery(String titre, String nomClass)
	// TODO Auto-generated constructor stube
	{
		titrePanel = new JLabel(titre);
		titrePanel.setFont(globalFont);

		this.setPreferredSize(new Dimension(480, 78));
		this.setBackground(Color.decode("#DFDFDF"));

		this.setLayout(new FlowLayout(FlowLayout.CENTER,10,8)); 	//61 est la valeur max
		if(nomClass.equals("ContactPanel")) {
			//On met le plus � gauche
			this.add(vide, BorderLayout.WEST);
		}else {
			this.add(previous, BorderLayout.WEST);
		}
		//On met le titre au centre
		this.add(titrePanel, BorderLayout.CENTER);

		//On met le plus � droite
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

			if(GalleryPanel.class != null)
			{
				System.out.println("salut");

			}

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

			
		}

		
	}
}
