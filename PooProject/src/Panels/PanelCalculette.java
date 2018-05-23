/*
* Author: Clivaz Loris & Vivian Bridy
* Date creation: 16 mai 2018
* 
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
 * Class responsable de l'affichage du PanelCalculette
 * 
 * @author Vivian
 *
 */
public class PanelCalculette extends JPanel
{

	JLabel titrePanel;
	Font globalFont = new Font("2.TimesRoman ",Font.BOLD,50);

	IconBase vide = new IconBase("",40,40);

	public String nomPhoto;

	/**
	 * Constructeur de la classe PanelCalculette
	 * 
	 * @param titre : le titre de la page
	 * @param nomClass : le nom de la classe précédente
	 * @author Vivian
	 */
	public PanelCalculette(String titre, String nomClass)
	// TODO Auto-generated constructor stube
	{
		titrePanel = new JLabel(titre);
		titrePanel.setFont(globalFont);

		this.setPreferredSize(new Dimension(480, 78));
		this.setBackground(Color.decode("#DFDFDF"));

		this.setLayout(new FlowLayout(FlowLayout.CENTER,10,8)); 	//61 est la valeur maxs
		if(nomClass.equals("ContactPanel")) {
			//On met le plus à gauche
			this.add(vide, BorderLayout.WEST);
		}else 
		//On met le titre au centre
		this.add(titrePanel, BorderLayout.CENTER);

	}
	
}
