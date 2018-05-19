/*
* Exercise W2Q3 - 2
* Author: Clivaz Loris
* Date creation: 19 mai 2018
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
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Images.IconBase;

/**
 * @author Loris_Clivaz
 *
 */
public class PanelCamera extends JPanel
{

	JLabel titrePanel;
	Font globalFont = new Font("2.TimesRoman ",Font.BOLD,50);

	IconBase vide = new IconBase("",40,40);

	public String nomPhoto;

	public PanelCamera(String titre, String nomClass)
	// TODO Auto-generated constructor stube
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

		//On met le plus � droite
		

		//On met un listener sur le bouton
//		create.addActionListener(new ClickCreate(nomClass));
//		previous.addActionListener(new ClickPrevious());





	}
}
