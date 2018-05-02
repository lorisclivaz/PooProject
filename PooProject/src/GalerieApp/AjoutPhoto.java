/*
* Exercise W2Q3 - 2
* Author: Clivaz Loris
* Date creation: 1 mai 2018
* 
*/
/**
 * 
 */
package GalerieApp;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Images.IconBase;

/**
 * @author Loris_Clivaz
 *
 */
public class AjoutPhoto extends JPanel
{

	
	public AjoutPhoto()
	{

		this.setLayout(new GridLayout(0, 2, 7, 7));
		
		this.add(new IconBase("images/galerie/photo1.jpg", 150, 150));
		this.add(new IconBase("images/galerie/photo2.jpg", 150, 150));
		
	}
}
