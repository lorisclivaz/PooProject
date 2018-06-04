/*
 * Author: Clivaz Loris
 * Date creation: 11 mai 2018
 * 
 */
package Panels;

import java.awt.BorderLayout;
import Images.IconBase;

/**
 * Classe responsable de l'affichage du PanelGallery
 * 
 * @author Vivian
 *
 */
public class PanelGallery extends PanelSettings{

	IconBase create = new IconBase("images/icones/plus.png",40,40);
	IconBase previous = new IconBase("images/icones/left-arrow.png",40,40);

	/**
	 * Constructeur de la classe PanelGallery
	 * 
	 * @param titre : le titre de la page
	 * @param nomClass : le nom de la classe précédente
	 * @author Vivian
	 */
	
	public PanelGallery(String titre, String nomClass)

	{
		super(titre,nomClass);
		
		if(!nomClass.equals("ContactPanel"))
			this.add(previous, BorderLayout.WEST);
		
		//On met le plus à droite
		this.add(create, BorderLayout.EAST);

	}
}


