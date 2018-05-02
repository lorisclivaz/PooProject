/*
 * Author : Vivian Bridy
 * Date creation : 30 avr. 2018
 */
package Panels;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import ContactApp.ContactPanel;
import ContactApp.NewContact;
import GalerieApp.GalleryPanel;
import Images.IconBase;

public class MenuH1PanelContact extends JPanel{

	JLabel titrePanel;
	Font globalFont = new Font("2.TimesRoman ",Font.BOLD,50);
	IconBase create = new IconBase("images/icones/plus.png",40,40);
	IconBase previous = new IconBase("images/icones/left-arrow.png",40,40);
	IconBase vide = new IconBase("images/icones/left-arrow.png",40,40);
	String titre;
	CardLayout cardlayout = new CardLayout();
	JPanel triPanel = new JPanel(cardlayout);

	public MenuH1PanelContact(String titre, String nomClass)
	// TODO Auto-generated constructor stube
	{
		this.titre = titre;
		titrePanel = new JLabel(getTitre());
		titrePanel.setFont(globalFont);

		this.setPreferredSize(new Dimension(480, 78));
		this.setBackground(Color.decode("#DFDFDF"));

		this.setLayout(new FlowLayout(FlowLayout.CENTER,10,8)); 	//61 est la valeur max
		if(nomClass.equals("ContactPanel")) {
			this.add(vide, BorderLayout.WEST);
		}else {
			this.add(previous, BorderLayout.WEST);
		}
		//On met le titre au centre
		this.add(titrePanel, BorderLayout.CENTER);

		//On met le plus � droite
		this.add(create, BorderLayout.EAST);

		//On met un listener sur le bouton
		create.addActionListener(new ClickCreate());
		previous.addActionListener(new ClickPrevious());

	}

	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	public String getTitre() {
		return titre;
	}
	
	public void setCardLayout(CardLayout cardlayout, JPanel triPanel) {
		this.cardlayout = cardlayout;
		this.triPanel = triPanel;
	}
	
	//quand on clique sur le bouton previous
	
	class ClickPrevious implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			cardlayout.show(triPanel, "listepanel");
			
		}
		
	}

	//quand on clique sur le bouton create
	class ClickCreate implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			cardlayout.show(triPanel, "newcontact");
		}
	}
}
