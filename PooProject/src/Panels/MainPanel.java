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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

import Images.IconBase;
import Images.ImageFond;



/**
 * @author Loris_Clivaz
 *
 */
public class MainPanel extends JPanel
{

	ImageFond imagefond = new ImageFond();
	
	//Ajout des icons
	IconBase iconcontact = new IconBase("images/icones/contact.png");
	IconBase icongallery = new IconBase("images/icones/gallery.png");
	IconBase iconpower = new IconBase("images/icones/power.png");
	IconBase iconmusic = new IconBase("images/icones/music.png");
	IconBase iconsettings = new IconBase("images/icones/settings.png");
	IconBase iconmail = new IconBase("images/icones/mail.png");
	IconBase iconyoutube = new IconBase("images/icones/youtube.png");
	IconBase iconfacebook = new IconBase("images/icones/facebook.png");
	IconBase iconinstagram = new IconBase("images/icones/instagram.png");
	IconBase iconagenda = new IconBase("images/icones/agenda.png");
	


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

	}
}
