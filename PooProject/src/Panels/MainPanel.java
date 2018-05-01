/*
 * Exercise W2Q3 - 2
 * Author: Clivaz Loris
 * Date creation: 30 avr. 2018
 * 
 */
/**
 * 
 */
package Panels;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

import Images.IconBase;
import Images.ImageFond;
import MainFrame.Frame;



/**
 * @author Loris_Clivaz
 *
 */
public class MainPanel extends JPanel
{

	ImageFond imagefond = new ImageFond();
	
	//Ajout des icons
	IconBase iconcontact = new IconBase("images/icones/contact.png", 100,100);
	IconBase icongallery = new IconBase("images/icones/gallery.png", 100,100);
	IconBase iconpower = new IconBase("images/icones/power.png", 100,100);
	IconBase iconmusic = new IconBase("images/icones/music.png", 100,100);
	IconBase iconsettings = new IconBase("images/icones/settings.png", 100,100);
	IconBase iconmail = new IconBase("images/icones/mail.png", 100,100);
	IconBase iconyoutube = new IconBase("images/icones/youtube.png", 100,100);
	IconBase iconfacebook = new IconBase("images/icones/facebook.png", 100,100);
	IconBase iconinstagram = new IconBase("images/icones/instagram.png", 100,100);
	IconBase iconagenda = new IconBase("images/icones/agenda.png", 100,100);
	IconBase iconphoto = new IconBase("images/icones/photo.png", 100,100);
	IconBase iconhorloge = new IconBase("images/icones/horloge.png", 100,100);

	
	

	

	public MainPanel() 
	{
		

		this.setLayout(new BorderLayout());

		this.setPreferredSize(new Dimension(480, 40));
		

		this.add(imagefond);

		imagefond.setLayout(new FlowLayout(30,40,70));

		
		imagefond.add(iconcontact);
		imagefond.add(icongallery);
		imagefond.add(iconpower);
		imagefond.add(iconmusic);
		imagefond.add(iconsettings);
		imagefond.add(iconmail);
		imagefond.add(iconyoutube);
		imagefond.add(iconfacebook);
		imagefond.add(iconinstagram);		
		imagefond.add(iconagenda);
		imagefond.add(iconphoto);
		imagefond.add(iconhorloge);
		
		
		
		icongallery.addActionListener(new ClickGallery());
		iconcontact.addActionListener(new ClickContact());		
		
	}
	
	//Quand on clique sur l'icon contacts

	class ClickContact implements ActionListener{

		
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			System.out.println("cliqué");
			Frame.cardLayout.show(Frame.triPanel, "contactpanel");
		}
		
	}
	
	//Quand on clique sur l'icon gallery
	
	class ClickGallery implements ActionListener{

		
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{			
			
			Frame.cardLayout.show(Frame.triPanel, "gallerypanel");
			
		}
		
	}
}
