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

import Images.IconContact;
import Images.IconGallery;
import Images.IconPower;
import Images.ImageFond;

/**
 * @author Loris_Clivaz
 *
 */
public class MainPanel extends JPanel
{

	ImageFond imagefond = new ImageFond();
	IconContact iconcontact = new IconContact();
	IconGallery icongallery = new IconGallery();
	IconPower iconpower = new IconPower();


	public MainPanel() 
	{

		this.setLayout(new BorderLayout());

		this.setPreferredSize(new Dimension(480, 40));
		this.setBackground(Color.RED);

		this.add(imagefond);

		imagefond.setLayout(new FlowLayout(30,40,20));

		
		imagefond.add(iconcontact);
		
		
		
		imagefond.add(icongallery);
		
		
		imagefond.add(iconpower);
		

	}
}
