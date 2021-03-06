/*
* Author : Vivian Bridy
* Date creation : 25 mai 2018
*/
package Panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Images.IconBase;

/**
 * Classe asbtract responsable des Panels
 * 
 * @author Vivian
 *
 */

public abstract class Panels extends JPanel{

	JLabel titrePanel;
	Font globalFont = new Font("2.TimesRoman ",Font.BOLD,50);

	IconBase vide = new IconBase("",40,40);
	
	public String nomPhoto;

	/**
	 * Constructeur de la classe abstract Panels
	 * 
	 * @param titre : le titre de la page
	 * @param nomClass : le nom de la classe pr�c�dente
	 * @author Vivian
	 */
	public Panels(String titre, String nomClass)
	{
		titrePanel = new JLabel(titre);
		titrePanel.setFont(globalFont);

		this.setPreferredSize(new Dimension(480, 78));
		this.setBackground(Color.decode("#DFDFDF"));

		this.setLayout(new FlowLayout(FlowLayout.CENTER,10,8)); 	//61 est la valeur maxs
		if(nomClass.equals("ContactPanel")) {
			//On met le plus � gauche
			this.add(vide, BorderLayout.WEST);
		}
		//On met le titre au centre
		this.add(titrePanel, BorderLayout.CENTER);
	}
	
}
