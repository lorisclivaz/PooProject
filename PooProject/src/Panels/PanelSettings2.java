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
 * Classe responsable de l'affiche du PanelSettings2
 * 
 * @author Vivian
 *
 */
public class PanelSettings2 extends JPanel
{

	JLabel titrePanel;
	Font globalFont = new Font("2.TimesRoman ",Font.BOLD,50);
	
	IconBase vide = new IconBase("",40,40);

	public String nomPhoto;

	/**
	 * Constructeur de la classe PanelSettings2
	 * 
	 * @param titre : le titre de la page
	 * @param nomClass : le nom de la classe précédente
	 * @author Vivian
	 */
	public PanelSettings2(String titre, String nomClass)
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
		}
		//On met le titre au centre
		this.add(titrePanel, BorderLayout.CENTER);

	}
}
